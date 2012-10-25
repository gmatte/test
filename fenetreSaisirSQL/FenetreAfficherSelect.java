package fenetreSaisirSQL;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FenetreAfficherSelect extends JFrame{
	
	private JPanel panel;
	
	private JLabel id;
	private String num;
	private String nom2;
	private String prenom2;
	
	
	public FenetreAfficherSelect(){
		panel = new JPanel();
		
		
		this.setTitle("Liste des pilotes de canadaire !");
		this.setLocation(450, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setResizable(false);
		
		String sql = "SELECT * FROM pilotes";
		try {
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
			
			Statement st = con.createStatement();
			
			this.getContentPane().add(panel);
			panel.setLayout(new GridLayout(15, 1));
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()){
				num = rs.getString("numPilote");
				nom2 = rs.getString("nomPilote");
				prenom2 = rs.getString("prenomPilote");
				 System.out.println("Pilote : "+num+" "+nom2+" "+prenom2);
			//	id = new JLabel("id : "+num+". Nom : "+nom2+". Prénom : "+prenom2);
				panel.add(new JLabel("id : "+num+". Nom : "+nom2+". Prénom : "+prenom2));
			}
			rs.close();
			
		} 
		
		catch (SQLException e) {
			System.out.println("Erreur !\n"+e);			
		}
		
		
		

		
		//this.getContentPane().add(panel);
	this.setVisible(true);
		
		
		
	}

}
