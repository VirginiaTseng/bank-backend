package com.virginia.tools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//import org.apache.log4j.Logger;


/**
 * 数据库连接相关操作
 */
public class ConnFactory
{
	// 创建 Logger 实例
    private static final Logger log = LoggerFactory.getLogger(ConnFactory.class);

//	private static Logger log = Logger.getLogger("ConnFactory");

	public static Map<String, Connection> DAO_MAP;

	public static Connection getConnection(String prefix) throws Exception{
		if(null == DAO_MAP)	{
			DAO_MAP = new HashMap<String, Connection>();
		}

		log.info("开始获取数据源ID为" + prefix + "的链接");
		Connection conn = DAO_MAP.get(prefix);

		if(!checkConnValid(conn))	{
			log.info("开始连接数据源ID为" + prefix + "的数据库");
			System.out.println("开始连接"+prefix+"数据库");
			try	{
				Class.forName(RunConfigPropTools.getProperty(prefix+"driverClassName")).newInstance();
				Properties properties = new Properties();
				properties.put("charSet", "gb2312");
				properties.put("user", RunConfigPropTools.getProperty(prefix+"username"));  
				properties.put("password", RunConfigPropTools.getProperty(prefix+"password")); 
				conn = DriverManager.getConnection(RunConfigPropTools.getProperty(prefix+"url"), properties);
				DAO_MAP.put(prefix, conn);
				System.out.println(prefix+"数据库连接成功");
			} catch(Exception e) {
				log.error("连接数据库失败！数据源ID为：" + prefix, e);
				System.out.println(prefix+"数据库连接失败");
				throw new Exception(e);
			}
		}
		// 手动提交
		conn.setAutoCommit(false);
		return conn;
	}
	
	/**
	 * 校验数据库链接是否还有效 
	 * 当取了connection之后很久不关闭也不操作时，有可能导致数据库将连接收回，但是客户端不知道的情况
	 * 数据库收回连接后，客户端的connection实例不为null，isClose也是false，但是执行sql会报通信错误
	 * 每次校验connection是否有效时，先执行一条sql语句，如果成功了说明连接有效，否则说明连接失效
	 * @param conn
	 * @return   连接有效时返回true
	 */
	private static boolean checkConnValid(Connection conn){
		Statement st = null;
		if(conn != null){
			try{
				st = conn.createStatement();
				st.executeQuery("select 1 from dual");
				return true;
			}catch (Exception e) {
				try{
					conn.close();
				}catch (Exception e1) {
				}
			}finally {
				if(st != null){
					try{
						st.close();
					}catch (Exception e) {
					}
				}
			}
		}
		return false;
	}
	

	
	
	/**
	 * 根据sql返回特定结果
	 * @param sql sql语句
	 * @param dataBaseId 数据库标识
	 * @return 结果(String)
	 */
	public static synchronized String findSqlValue(String sql, String dataBaseId ,String checkValue) {
		log.info("执行表查询开始："+dataBaseId+"--"+sql);
		String value = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection(dataBaseId);
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				value = rs.getString(checkValue);
			}
			conn.commit();
		}
		catch(Exception e){
			log.error("执行表查询出现异常：", e);
		}
		finally	{
			close(rs, pstmt);
		}
		log.info("执行表查询结束");
		return value;
	}
	
	

	
	/**
	 * 关闭结果集ResultSet
	 * 关闭 Statement
	 * @param rs
	 * @param st
	 * @throws SQLException 
	 */
	public static void close(ResultSet rs, Statement st){
		try{
			if (rs != null) 
				rs.close(); 
        } catch (SQLException e) {
        	log.error("关闭结果集错误", e);
        } finally {
        	try {
                if (st != null) 
                    st.close(); 
            } catch (SQLException e){
            	log.error("关闭Statement错误", e);
            } 
        }

	}
	/**
	 * 关闭结果集ResultSet
	 * 关闭 Statement
	 * 关闭 连接
	 * @param rs
	 * @param st
	 *  @param conn
	 */
	
	public static void close(ResultSet rs, Statement st, Connection conn){
		try{
			if (rs != null) 
				rs.close(); 
        } catch (SQLException e) {
        	log.error("关闭结果集错误", e);
        } 
        finally {
        	try {
                if (st != null) 
                    st.close(); 
            } catch (SQLException e) {
            	log.error("关闭Statement错误", e);
            } finally {
                try {
                    if (conn != null) 
                        conn.close(); 
                } catch (SQLException e) {
                	log.error("关闭连接错误", e);
                }
            }
        }
	 }
	
	/**
	 * 根据sql查找
	 * @param sql sql语句
	 * @param dataBaseId 数据库标识
	 * @return List<Map<名称，值>>
	 */
	public static synchronized List<Map<String, String>> findSql(String sql, String dataBaseId) {
		log.info("执行表查询开始："+dataBaseId+"--"+sql);
		List<String> list = new ArrayList<String>();
		List<Map<String, String>> listArray = new ArrayList<Map<String, String>>();
		
		if(!ToolsUtil.isEmpty(sql) && !ToolsUtil.isEmpty(dataBaseId)){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection(dataBaseId);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				ResultSetMetaData rsd = rs.getMetaData();
				
				for(int i = 0; i < rsd.getColumnCount(); i++){
				  	list.add(rsd.getColumnName(i + 1));
				}
				    
				while(rs.next()){
				   	Map<String, String> nameValue = new LinkedHashMap<String, String>();
					for(String name : list)	{
						if (rs.getString(name)!=null) 
							nameValue.put(name.trim().toUpperCase(), rs.getString(name).trim());
						else 
							nameValue.put(name.trim().toUpperCase(), null);
					}
					listArray.add(nameValue);
				}
				conn.commit();
			}
			catch(Exception e){
				log.error("执行表查询出现异常：", e);
			}
			finally	{
				close(rs, pstmt);
			}
		}
		log.info("执行表查询结束");
		return listArray;
	}

	/**
	 *  使用预编译查询数据库
	 * @param sql          预编译sql语句
	 * @param dataBaseId   数据库标识
	 * @param types        预编译参数的类型
	 * @param values       预编译参数的值
	 * @return
	 */
	public static synchronized List<Map<String, Object>> findSql(String sql, String dataBaseId, int[] types, Object[] values) {
		log.info("执行表查询开始："+dataBaseId+"--"+sql);
		List<Map<String, Object>> listArray = new ArrayList<Map<String, Object>>();
		
		if(!ToolsUtil.isEmpty(sql) && !ToolsUtil.isEmpty(dataBaseId)){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection(dataBaseId);
				pstmt = conn.prepareStatement(sql);
				
				// 入参
				for(int i = 0; i < types.length; i++){
					int type = types[i];
					Object value = values[i];
					if(value == null){
						pstmt.setNull(i+1, type);
						continue;
					}
					
					switch(type){
					case Types.CHAR:
					case Types.VARCHAR:
					case Types.NVARCHAR:
						pstmt.setString(i+1, String.valueOf(value));
						break;
					case Types.BIGINT:
						pstmt.setLong(i+1, (Long)value);
						break;
					case Types.INTEGER:
						pstmt.setInt(i+1, (Integer)value);
						break;
					case Types.SMALLINT:
						pstmt.setShort(i+1, (Short)value);
						break;
					case Types.DOUBLE:
						pstmt.setDouble(i+1, (Double)value);
						break;
					case Types.DECIMAL:
						pstmt.setBigDecimal(i+1, (BigDecimal)value);
						break;
					case Types.FLOAT:
						pstmt.setFloat(i+1, (Float)value);
						break;
					case Types.TIME:
						pstmt.setTime(i+1, (java.sql.Time)value);
						break;
					case Types.TIMESTAMP:
						pstmt.setTimestamp(i+1, (Timestamp)value);
						break;
					case Types.DATE:
						pstmt.setDate(i+1, (java.sql.Date)value);
						break;
						default:
							pstmt.setObject(i+1, value);
					}
				}
				
				// 查数据
				rs = pstmt.executeQuery();
				
				// 整编
				ResultSetMetaData rsd = rs.getMetaData();
				while(rs.next()){
				   	Map<String, Object> nameValue = new HashMap<String, Object>();
					for(int i = 1; i <= rsd.getColumnCount(); i++){
					  	String name = rsd.getColumnName(i);
						nameValue.put(name.trim().toUpperCase(), rs.getObject(i));
					}
					listArray.add(nameValue);
				}
				conn.commit();
			}catch(Exception e){
				log.error("执行表查询出现异常：", e);
			}finally{
				close(rs, pstmt);
			}
		}
		log.info("执行表查询结束");
		return listArray;
	}
	
	private static String getDataType(int type,int scale){
        String dataType="";

        switch(type){
            case Types.LONGVARCHAR: //-1
                dataType="Long";
                break;
            case Types.CHAR:    //1
                dataType="Character";
                break;
            case Types.NUMERIC: //2
                switch(scale){
                    case 0:
                        dataType="Number";
                        break;
                    case -127:
                        dataType="Float";
                        break;
                    default:
                        dataType="Number";
                }
                break;
            case Types.VARCHAR:  //12
                dataType="String";
                break;
            case Types.DATE:  //91
                dataType="Date";
                break;
            case Types.TIMESTAMP: //93
                dataType="Date";
                break;
            case Types.BLOB :
                dataType="Blob";
                break;
            default:
                dataType="String";
        }
        return dataType;
   }
	
	public static synchronized List<Map<String, String>> findSql(String sql, String dataBaseId,List nameList, List typeList) {
		log.info("执行表查询开始："+dataBaseId+"--"+sql);
		List<String> list = new ArrayList<String>();
		List<Map<String, String>> listArray = new ArrayList<Map<String, String>>();
		
		if(!ToolsUtil.isEmpty(sql) && !ToolsUtil.isEmpty(dataBaseId)){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = getConnection(dataBaseId);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				ResultSetMetaData rsd = rs.getMetaData();
				
				for(int i = 0; i < rsd.getColumnCount(); i++){
				  	list.add(rsd.getColumnName(i + 1));
				  	typeList.add(getDataType(rsd.getColumnType(i+1),rsd.getScale(i+1)));
				}
				    
				while(rs.next()){
				   	Map<String, String> nameValue = new LinkedHashMap<String, String>();
					//for(String name : list)	{
					for(int i=0; i<list.size(); i++) {
						String name=list.get(i);
						if(typeList.get(i).equals("Date")) {
							if(rs.getTimestamp(name)!=null) {
								nameValue.put(name.trim().toUpperCase(), rs.getTimestamp(name).toString());
							}else {
								nameValue.put(name.trim().toUpperCase(), null);
							}
						}else {
							if (rs.getString(name)!=null) { 
								nameValue.put(name.trim().toUpperCase(), rs.getString(name).trim());
							}else { 
								nameValue.put(name.trim().toUpperCase(), null);
							}
						}
					}
					listArray.add(nameValue);
				}
				conn.commit();
			}
			catch(Exception e){
				log.error("执行表查询出现异常：", e);
			}
			finally	{
				close(rs, pstmt);
			}
		}
		log.info("执行表查询结束");
		
		nameList=list;
		return listArray;
	}
	
	/**
	 * 批量至数据库执行
	 * @param sql sql语句
	 * @param dataBaseId 数据库标识
	 * @param vualeList 预编译值序列
	 * @return true成功，false失败
	 */
	public static synchronized boolean batchExcute(String dataBaseId, String sql, List<List<String>> vualeList){
		log.info("当前批量执行SQL为"+dataBaseId+"--"+sql+"--数量："+vualeList.size());
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean exeresult = false;//标志执行结果
				
		try{
			conn = ConnFactory.getConnection(dataBaseId);
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0;i<vualeList.size();i++){
				List<String> iList = vualeList.get(i);
				for(int j=1;j<=ToolsUtil.strNums(sql, '?');j++){
					if(iList.size()>=j){
						if(iList.get(j-1)==null) {
							pstmt.setString(j, null);
						}else {
							if(iList.get(j-1).contains("'")){
								pstmt.setString(j, iList.get(j-1).substring(1, 
										iList.get(j-1).length()-1));
							}
							else {
								pstmt.setString(j, iList.get(j-1));
							}
						}
					}
					else {//？个数多余值的个数
						pstmt.setString(j, null);
					}
				}
				
				pstmt.addBatch();
				if((i%500==0 && i>0)||i==vualeList.size()-1){
					pstmt.executeBatch();
		    		conn.commit();
		    		pstmt.clearBatch();
		    	}
			}
			exeresult = true;
		}
		catch (Exception e) {
			log.error("批量执行目标表sql异常", e);
			if (null != conn){
				try	{
					conn.rollback();
				}
				catch(SQLException e1){
					log.error("批量执行目标表sql回滚错误", e1);
				}
			}
		}
		finally{
			close(rs, pstmt);
		}
		log.info("当前批量执行SQL为"+dataBaseId+"完成");
		return exeresult;
	}
	
	public static synchronized boolean batchExcute(String dataBaseId, String sql, List<List<Object>> vualeList, List<String> typeList){
		log.info("当前批量执行SQL为"+dataBaseId+"--"+sql+"--数量："+vualeList.size());
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean exeresult = false;//标志执行结果
				
		try{
			conn = ConnFactory.getConnection(dataBaseId);
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0;i<vualeList.size();i++){
				List<Object> iList = vualeList.get(i);
				for(int j=1;j<=ToolsUtil.strNums(sql, '?');j++){
					if(iList.size()>=j){
						if(iList.get(j-1)==null) {
							pstmt.setString(j, null);
						}else {
							if(typeList.get(j-1).equals("Date")) {//时间类型判断
								if(iList.get(j-1)!=null) {
									pstmt.setTimestamp(j, Timestamp.valueOf((String)(iList.get(j-1))));
								}else {
									pstmt.setTimestamp(j,null);
								}
							}else if(typeList.get(j-1).equals("Number")) {
								if(iList.get(j-1)!=null) {
									pstmt.setLong(j, (Long)(iList.get(j-1)));
								}else {
									pstmt.setLong(j,0l);
								}
							}else if(typeList.get(j-1).equals("Float")) {
								if(iList.get(j-1)!=null) {
									pstmt.setFloat(j, (Float)(iList.get(j-1)));
								}else {
									pstmt.setFloat(j,0f);
								}
							}else if(typeList.get(j-1).equals("String")) {
								String s=(String)(iList.get(j-1));
								if(s.contains("'")){
									int len=s.length();
									pstmt.setString(j, s.substring(1, len-1));
								}else {
									pstmt.setString(j, s);
								}
							}else {
								pstmt.setString(j, (String)(iList.get(j-1)));
							}
							
							
						}
					}
					else {//？个数多余值的个数
						pstmt.setString(j, null);
					}
				}
				
				pstmt.addBatch();
				if((i%500==0 && i>0)||i==vualeList.size()-1){
					pstmt.executeBatch();
		    		conn.commit();
		    		pstmt.clearBatch();
		    	}
			}
			exeresult = true;
		}
		catch (Exception e) {
			log.error("批量执行目标表sql异常", e);
			if (null != conn){
				try	{
					conn.rollback();
				}
				catch(SQLException e1){
					log.error("批量执行目标表sql回滚错误", e1);
				}
			}
		}
		finally{
			close(rs, pstmt);
		}
		log.info("当前批量执行SQL为"+dataBaseId+"完成");
		return exeresult;
	}
	

	public static synchronized boolean executeSql(String dataBaseId, String sql, List<Object> values, List<String> typeList){
		log.info("当前批量执行SQL为"+dataBaseId+"--"+sql +"--- params: " + values.toString());
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnFactory.getConnection(dataBaseId);
			pstmt = conn.prepareStatement(sql);

			for(int j=1;j<=ToolsUtil.strNums(sql, '?');j++){
				if(values.size()>=j){
					if(values.get(j-1)==null) {
						pstmt.setString(j, null);
					}else {
						if(typeList.get(j-1).equals("Date")) {//时间类型判断
							if(values.get(j-1)!=null) {
								pstmt.setTimestamp(j, Timestamp.valueOf((String)(values.get(j-1))));
							}else {
								pstmt.setTimestamp(j,null);
							}
						}else if(typeList.get(j-1).equals("Number")) {
							if(values.get(j-1)!=null) {
								pstmt.setLong(j, (Long)(values.get(j-1)));
							}else {
								pstmt.setLong(j,0l);
							}
						}else if(typeList.get(j-1).equals("Float")) {
							if(values.get(j-1)!=null) {
								pstmt.setFloat(j, (Float)(values.get(j-1)));
							}else {
								pstmt.setFloat(j,0f);
							}
						}else if(typeList.get(j-1).equals("String")) {
							String s=(String)(values.get(j-1));
							if(s.contains("'")){
								int len=s.length();
								pstmt.setString(j, s.substring(1, len-1));
							}else {
								pstmt.setString(j, s);
							}
						}else {
							pstmt.setString(j, (String)(values.get(j-1)));
						}
						
						
					}
				}else {//？个数多余值的个数
					pstmt.setString(j, null);
				}
			}

			pstmt.execute();
    		conn.commit();
    		return true;
		}catch (Exception e) {
			log.error("单行执行目标表sql异常", e);
			if (null != conn){
				try	{
					conn.rollback();
				}catch(SQLException e1){
					log.error("单行执行目标表sql回滚错误", e1);
				}
			}
		}finally{
			close(rs, pstmt);
		}
		return false;
	}
	
	
	public static synchronized boolean batchExcute(String dataBaseId, String sql, List<List<String>> vualeList,  List<String> nameList, List<String> typeList){
		log.info("当前批量执行SQL为"+dataBaseId+"--"+sql+"--数量："+vualeList.size());
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean exeresult = false;//标志执行结果
				
		try{
			conn = ConnFactory.getConnection(dataBaseId);
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0;i<vualeList.size();i++){
				List<String> iList = vualeList.get(i);
				for(int j=1;j<=ToolsUtil.strNums(sql, '?');j++){
					if(iList.size()>=j){
						if(iList.get(j-1)==null) {
							pstmt.setString(j, null);
						}else {
							if(iList.get(j-1).contains("'")){
								pstmt.setString(j, iList.get(j-1).substring(1, 
										iList.get(j-1).length()-1));
							}
							else {
								if(typeList.get(j-1).equals("Date")) {//TODO:时间类型判断
									if(iList.get(j-1)!=null) {
										pstmt.setTimestamp(j, Timestamp.valueOf(iList.get(j-1)));
									}else {
										pstmt.setTimestamp(j,null);
									}
								}else {
									pstmt.setString(j, iList.get(j-1));
								}
							}
						}
					}
					else {//？个数多余值的个数
						pstmt.setString(j, null);
					}
				}
				
				pstmt.addBatch();
				if((i%500==0 && i>0)||i==vualeList.size()-1){
					pstmt.executeBatch();
		    		conn.commit();
		    		pstmt.clearBatch();
		    	}
			}
			exeresult = true;
		}
		catch (Exception e) {
			log.error("批量执行目标表sql异常", e);
			if (null != conn){
				try	{
					conn.rollback();
				}
				catch(SQLException e1){
					log.error("批量执行目标表sql回滚错误", e1);
				}
			}
		}
		finally{
			close(rs, pstmt);
		}
		log.info("当前批量执行SQL为"+dataBaseId+"完成");
		return exeresult;
	}
	
	/**
	 * 执行sql语句
	 * @param sql sql语句
	 * @param dataBaseId 数据库标识
	 * @return true成功，false失败
	 */
	public static synchronized boolean excuteSql(String sql, String dataBaseId){
		log.info("开始操作目标表 sql=" + sql);
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			conn = ConnFactory.getConnection(dataBaseId);

			if(sql.contains("insert")){
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
			}
			else {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
			}
			conn.commit();
			
			log.info("操作目标表 sql完成");
			flag = true;
		}
		catch(Exception e){
			log.error("执行目标表sql异常", e);
			if (null != conn){
				try	{
					conn.rollback();
				}
				catch(SQLException e1){
					// TODO Auto-generated catch block
					log.error("执行目标表sql回滚错误", e1);
				}
			}
		}
		finally	{
			close(rs, pstmt);
		}
		return flag;
	}
	

}
