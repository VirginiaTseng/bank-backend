package com.virginia.db.wine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Winery {

    public static void main(String args[]) {
        String url = "jdbc:mysql://127.0.0.1:3306/winedb?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
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
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(
                        rs.getString("winery_name") + " | " +
                        rs.getString("country_name") + " | " +
                        rs.getString("region_name") + " | " +
                        rs.getString("viticultural_area_name") + " | " );
                
            }
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
