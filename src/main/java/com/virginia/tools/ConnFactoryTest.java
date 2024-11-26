package com.virginia.tools;

import java.util.List;
import java.util.Map;

public class ConnFactoryTest {


	public static void main(String[] args){
		try {
			RunConfigPropTools.load();
			  String sql = "SELECT w.winery_name, c.country_name, r.region_name, va.viticultural_area_name " + 
	            		"FROM Winery w " + 
	            		"JOIN Viticultural_area va ON w.viticultural_area_id = va.viticultural_area_id " + 
	            		"JOIN Region r ON va.region_id = r.region_id " + 
	            		"JOIN Country c ON r.country_id = c.country_id " + 
	            		"JOIN Portfolio p ON w.winery_id = p.winery_id " + 
	            		"JOIN Wine_type wt ON p.wine_type_id = wt.wine_type_id " + 
	            		"WHERE c.country_name = 'USA' " + 
	            		"  AND wt.wine_type_name = 'Merlot' " + 
	            		"  AND p.in_season_flag = 1 " + 
	            		"  AND w.offering_tours_flag = 1";
			List<Map<String, String>> list = ConnFactory.findSql(sql, "target.") ;
			
			System.out.println(list.size());
			for(Map<String,String> record: list) {
				System.out.println(record.get("w.winery_name"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
