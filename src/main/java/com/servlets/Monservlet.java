package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.list.*;

/**
 * Servlet implementation class Monservlet
 */
@WebServlet("/monservlet")
public class Monservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        String mot_de_passe = request.getParameter("motdepasse");
        String confirmation = request.getParameter("motdepasseconfirme");
        
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
            
            while(nom.matches("")||prenom.matches("")||pseudo.matches("")||email.matches("")||mot_de_passe.matches("")||confirmation.matches("")) {
            	request.setAttribute("erreur", true);
            	request.setAttribute("name", nom);
            	request.setAttribute("prenom", prenom);
            	request.setAttribute("pseudo", pseudo);
            	request.setAttribute("email", email);
            	request.setAttribute("mdp", mot_de_passe);
            	request.setAttribute("mdpconf", confirmation);
            	RequestDispatcher rd = request.getRequestDispatcher("Inscrire.jsp");
                rd.forward(request, response);
            }
            
            if(!mot_de_passe.matches(confirmation)) {
            	request.setAttribute("erreurmdp", true);
            	request.setAttribute("name", nom);
            	request.setAttribute("prenom", prenom);
            	request.setAttribute("pseudo", pseudo);
            	request.setAttribute("email", email);
            	request.setAttribute("mdp", mot_de_passe);
            	//request.setAttribute("mdpconf", confirmation);
            	RequestDispatcher rd = request.getRequestDispatcher("Inscrire.jsp");
                rd.forward(request, response);
            }
            
            String sql = "INSERT INTO djenika.inscrire(nom,prenom,pseudo,email,motdepasse) value (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, pseudo);
            ps.setString(4, email);
            ps.setString(5, mot_de_passe);
            
            ps.executeUpdate();
            
            request.setAttribute("prenom", prenom);
            request.setAttribute("name", nom);
            RequestDispatcher rd = request.getRequestDispatcher("Accueil.jsp");
            rd.forward(request, response);
            
        } catch(SQLException e) {
        	System.out.println("Erreur "+e.getMessage());
        }
        //List<User> maliste= userList();
        
        
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
