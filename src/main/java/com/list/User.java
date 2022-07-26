package com.list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String nom;
    private String prenom;
    private String pseudo;
    private String email;
    private String mot_de_passe;
    
    public User() {}
    
	public User(String nom, String prenom, String pseudo, String email, String mot_de_passe) {
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
		this.email = email;
		this.mot_de_passe = mot_de_passe;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
    
	public List<User> userList() {
		List<User> users=new ArrayList<User>();
		Statement requette = null;
		ResultSet resultat = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
        	Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/djenika","root","");
            System.out.println("Félicitation vous êtes connecté !");
            
            requette = con.createStatement();
            resultat = requette.executeQuery("SELECT nom,prenom,pseudo,email FROM djenika.inscrire");
            while(resultat.next()) {
				
				User user=new User();
				user.setNom(resultat.getString("nom"));
				user.setPrenom(resultat.getString("prenom"));
				user.setPseudo(resultat.getString("pseudo"));
				user.setEmail(resultat.getString("email"));
				users.add(user);
			}
            if(resultat!=null) {
				resultat.close();
			}
			if(requette!=null) {
				requette.close();
			}
			if(con!=null) {
				con.close();
			}
			return users;
        } catch(SQLException e) {
        	System.out.println("Erreur "+e.getMessage());
        }
        return users;
		
	}

}
