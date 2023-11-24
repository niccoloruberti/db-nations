package org.lessons.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	private static final String url = "jdbc:mysql://localhost:8889/db-nations";
	private static final String user = "root";
	private static final String pws = "root";

	public static void main(String[] args) {
		
//		milestone2_3();
		bonus();

	}
	
//	private static void milestone2_3() {
//		
//		Scanner in = new Scanner(System.in);
//		
//		System.out.println("Che regione vuoi ricercare?");
//		
//		String userSearch = in.nextLine();
//		
//		in.close();
//		
//		try (Connection con = DriverManager.getConnection(url, user, pws)) {  
//		  
//		  final String sql = " SELECT countries.name AS nazione, countries.country_id, regions.name AS regione, continents.name AS continente "
//			  			   + " FROM countries "
//			  			   + " 	JOIN regions "
//			  			   + "		ON countries.region_id = regions.region_id"
//			  			   + "	JOIN continents"
//			  			   + "		ON regions.continent_id = continents.continent_id"
//			  			   + " WHERE countries.name LIKE '%" + userSearch + "%'"
//			  			   + "	ORDER BY countries.name ASC"
//			  			   + " ; ";		  
//		  
//		  try(PreparedStatement ps = con.prepareStatement(sql)){
//			  
//		    try(ResultSet rs = ps.executeQuery()){
//		    	
//		    	while(rs.next()) {
//		    		
//		    		String nazione = rs.getString(1);
//		    		int countryId = rs.getInt(2);
//		    		String regione = rs.getString(3);
//		    		String continente = rs.getString(4);
//		    		
//		    		System.out.println(nazione + " - " + countryId + " - " + regione + " - " + continente);
//		    		
//		    	}
//		    }
//		  }
//		  
//		} catch (Exception e) {
//			
//			System.out.println("Error in db: " + e.getMessage());
//		}
//	}
	
	private static void bonus() {
	
	Scanner in = new Scanner(System.in);
	
	System.out.println("Che regione vuoi ricercare?");
	
	String userSearch = in.nextLine();
	
	try (Connection con = DriverManager.getConnection(url, user, pws)) {  
	  
	  final String sql = " SELECT countries.name AS nazione, countries.country_id, regions.name AS regione, continents.name AS continente "
		  			   + " FROM countries "
		  			   + " 	JOIN regions "
		  			   + "		ON countries.region_id = regions.region_id"
		  			   + "	JOIN continents"
		  			   + "		ON regions.continent_id = continents.continent_id"
		  			   + " WHERE countries.name LIKE '%" + userSearch + "%'"
		  			   + "	ORDER BY countries.name ASC"
		  			   + " ; ";		  
	  
	  try(PreparedStatement ps = con.prepareStatement(sql)){
		  
	    try(ResultSet rs = ps.executeQuery()){
	    	
	    	System.out.println(" ");
	    	
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
	
	System.out.print("\n" + "inserisci l'id di una delle country:");
	
	String userIdStr = in.nextLine();
	
	int userId = Integer.valueOf(userIdStr);
	
	in.close();
	
	try (Connection con = DriverManager.getConnection(url, user, pws)) {  
		  
		  final String sql = " SELECT languages.language "
			  			   + " FROM languages "
			  			   + " 	JOIN country_languages "
			  			   + "		ON languages.language_id = country_languages.language_id "
			  			   + "	WHERE country_languages.country_id = " + userId
			  			   + " ; ";
		  
		  final String sql2 = "SELECT country_stats.year AS anno, country_stats.population AS popolazione, country_stats.gdp AS GDP "
				   			+ "FROM country_stats "
				   			+ "WHERE country_stats.country_id = " + userId + " "
				   			+ "ORDER BY country_stats.year DESC "
				   			+ "LIMIT 1;";
		  
		  try(PreparedStatement ps = con.prepareStatement(sql)){
			  
		    try(ResultSet rs = ps.executeQuery()){
		    	
		    	System.out.print("\n" + "lingue: ");
		    	
		    	while(rs.next()) {
		    		
		    		String lingua = rs.getString(1);
		    		
		    		System.out.print(lingua + ", ");
		    		
		    	}
		    }
		  }
		  
		  try(PreparedStatement ps = con.prepareStatement(sql2)){
			  
			    try(ResultSet rs = ps.executeQuery()){
			    	
			    	if (rs.next()) {
				    	System.out.println("\n");
				    	System.out.println("Statistiche più recenti:");
				    	System.out.println("Year: " + rs.getInt(1));
				    	System.out.println("Popolazione: " + rs.getInt(2));
				    	System.out.println("GDP: " + rs.getString(3));
			    	}

			    	
			    }
			  }
		  
		} catch (Exception e) {
			
			System.out.println("Error in db: " + e.getMessage());
		}
}

}
