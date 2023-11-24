package org.lessons.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
	
	private static final String url = "jdbc:mysql://localhost:8889/db-nations";
	private static final String user = "root";
	private static final String pws = "root";

	public static void main(String[] args) {
		
		milestone2();

	}
	
	private static void milestone2() {
		
		try (Connection con = DriverManager.getConnection(url, user, pws)) {  
		  
		  final String sql = " SELECT countries.name AS nazione, countries.country_id, regions.name AS regione, continents.name AS continente "
			  			   + " FROM countries "
			  			   + " 	JOIN regions "
			  			   + "		ON countries.region_id = regions.region_id"
			  			   + "	JOIN continents"
			  			   + "		ON regions.continent_id = continents.continent_id"
			  			   + "	ORDER BY countries.name ASC"
			  			   + " ; ";		  
		  
		  try(PreparedStatement ps = con.prepareStatement(sql)){
			  
		    try(ResultSet rs = ps.executeQuery()){
		    	
		    	while(rs.next()) {
		    		
		    		String nazione = rs.getString(1);
		    		int countryId = rs.getInt(2);
		    		String regione = rs.getString(3);
		    		String continente = rs.getString(4);
		    		
		    		System.out.println(nazione + " - " + countryId + " - " + regione + " - " + continente);
		    		
		    	}
		    }
		  }
		  
		} catch (Exception e) {
			
			System.out.println("Error in db: " + e.getMessage());
		}
	}

}
