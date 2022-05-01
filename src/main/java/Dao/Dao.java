package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Asiakas;

public class Dao {
	private Connection con=null;
	private ResultSet rs = null;
	private PreparedStatement stmtPrep=null; 
	private String sql;
	private String db ="Asiakaat.sqlite";
	
	private Connection yhdista(){
    	Connection con = null;    	
    	String path = System.getProperty("catalina.base");    	
    	path = path.substring(0, path.indexOf(".metadata")).replace("\\", "/"); 
    	String url = "jdbc:sqlite:"+path+db;    	
    	try {	       
    		Class.forName("org.sqlite.JDBC");
	        con = DriverManager.getConnection(url);	
	        System.out.println("Yhteys avattu.");
	     }catch (Exception e){	
	    	 System.out.println("Yhteyden avaus epäonnistui.");
	        e.printStackTrace();	         
	     }
	     return con;
	}
	
	public ArrayList<Asiakas> listaaKaikki(){
		ArrayList<Asiakas> myynti = new ArrayList<Asiakas>();
		sql = "SELECT * FROM asiakas";       
		try {
			con=yhdista();
			if(con!=null){ 
				stmtPrep = con.prepareStatement(sql);        		
        		rs = stmtPrep.executeQuery();   
				if(rs!=null){ 			
					while(rs.next()){
						Asiakas asiakas = new Asiakas();
						asiakas.setEtunimi(rs.getString(1));
						asiaks.setSukunimi(rs.getString(2));
						asiakas.setSposti(rs.getString(3));	
						asiakas.setPuhelin(rs.getInt(4));	
						asiakas.add(asiakas);
					}					
				}				
			}	
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return myynti;
	}
	
	public ArrayList<Myynti> listaaKaikki(String hakusana){
		ArrayList<Myynti> asiakas = new ArrayList<Myynti>();
		sql = "SELECT * FROM asiakas WHERE etunimi LIKE ? or sukunimi LIKE ? or sposti LIKE ?";      
		try {
			con=yhdista();
			if(con!=null){ 
				stmtPrep = con.prepareStatement(sql);
				stmtPrep.setString(1, "%" + hakusana + "%");
				stmtPrep.setString(2, "%" + hakusana + "%");   
				stmtPrep.setString(3, "%" + hakusana + "%");  
        		rs = stmtPrep.executeQuery();   
				if(rs!=null){ 				
					while(rs.next()){
						Asiakas asiakas = new Asiakas();
						asiakas.setEtunii(rs.getString(1));
						asiakas.setSukunimi(rs.getString(2));
						asiakas.setSposti(rs.getString(3));	
						asiakas.setPuhelin(rs.getInt(4));	
						asiakas.add(asiakas);
					}					
				}				
			}	
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return asiakas;
	}
	
	public boolean lisaaAsiakas(Asiakas asiakas){
		boolean paluuArvo=true;
		sql="INSERT INTO asiakas VALUES(?,?,?,?)";						  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, asiakas.getEtunimi());
			stmtPrep.setString(2, asiakas.getSukunimi());
			stmtPrep.setString(3, asiakas.getSposti());
			stmtPrep.setInt(4, asiakas.getPuhelin());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	public boolean poistaAuto(String rekNo){ 
		boolean paluuArvo=true;
		sql="DELETE FROM asiakas WHERE asiakas=?";						  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, etunimi);			
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}	


	public Dao() {
		
	}

}
