package fenetreSaisirSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AfficherConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");	
			con = DriverManager.getConnection("jdbc:postgresql:lesCanadaires", "gmatte", "AaSr03*");
		} 
		
		catch (ClassNotFoundException e) {
			System.out.println("Driver non chargé !\n"+e);
		}
		
		catch(SQLException sqlError){
			System.out.println("Problème de connexion !\n"+sqlError);
		}
		
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM pilotes";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int num = rs.getInt("numPilote");
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				System.out.println("Pilote : "+num+" "+nom+" "+prenom);
			}
			rs.close();
			
		} 
		
		catch (SQLException e) {
			System.out.println("Erreur !\n"+e);			
		}
		
	}

}
