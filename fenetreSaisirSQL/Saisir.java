package fenetreSaisirSQL;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class Saisir extends JFrame implements ActionListener {
		
	private JPanel panelGlobal;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	
	private JButton valider;
	private JButton voir;
	
	private JTextField id;
	private JLabel textId;
	private JTextField nom;
	private JLabel textNom;
	private JTextField prenom;
	private JLabel textPrenom;
	
	public Saisir(){
		
		panelGlobal = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		
		valider = new JButton("Valider");
		voir = new JButton("Voir les pilotes");
		
		id = new JTextField();
		textId = new JLabel("Saisir un id");
		id.setPreferredSize(new Dimension(150, 30));
		
		nom = new JTextField();
		textNom = new JLabel("Saisir un nom");
		nom.setPreferredSize(new Dimension(150, 30));
		
		prenom = new JTextField();
		textPrenom = new JLabel("Saisir un prénom");
		prenom.setPreferredSize(new Dimension(150, 30));
		
		this.setTitle("Saisie SQL !");
		this.setLocation(450, 350);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(350, 250);
		this.setResizable(false);
		
		panel1.setLayout(new BorderLayout());
		panel1.add(textId, BorderLayout.WEST);
		panel1.add(id, BorderLayout.EAST);
		
		panel2.setLayout(new BorderLayout());
		panel2.add(textNom, BorderLayout.WEST);
		panel2.add(nom, BorderLayout.EAST);
		
		panel3.setLayout(new BorderLayout());
		panel3.add(textPrenom, BorderLayout.WEST);
		panel3.add(prenom, BorderLayout.EAST);
		
		panel4.setLayout(new FlowLayout());
		panel4.add(valider);
		panel4.add(voir);
		
		panel5.setLayout(new BorderLayout());
		panel5.add(panel1, BorderLayout.NORTH);
		panel5.add(panel2, BorderLayout.CENTER);
		panel5.add(panel3, BorderLayout.SOUTH);
		
		panelGlobal.setLayout(new BorderLayout());
		panelGlobal.add(panel4, BorderLayout.SOUTH);
		panelGlobal.add(panel5, BorderLayout.NORTH);
		
		valider.addActionListener(this);
		voir.addActionListener(this);
		
		this.getContentPane().add(panelGlobal);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == valider){
			Connection con = null;
			try {
				Class.forName("org.postgresql.Driver");	
				con = DriverManager.getConnection("jdbc:postgresql:lesCanadaires", "gmatte", "AaSr03*");
			} 
			
			catch (ClassNotFoundException e) {
				System.out.println("Driver non chargé !"+e);
			}
			
			catch(SQLException sqlError){
				System.out.println("Problème de connexion !"+sqlError);
			}
			
			try {
				int identifiant = Integer.parseInt(id.getText());
				Statement st = con.createStatement();
				st.executeUpdate("INSERT INTO pilotes VALUES('"+identifiant+"', '"+nom.getText()+"', '"+prenom.getText()+"')");
			} 
			
			catch (SQLException e) {
				System.out.println("Erreur ! "+e);			
			}
		System.out.println("Bravo. Vous avez envoyé un pilote");
		}
		
		if(event.getSource() == voir){
			FenetreAfficherSelect fen = new FenetreAfficherSelect();
 
		}
	}
	

}
