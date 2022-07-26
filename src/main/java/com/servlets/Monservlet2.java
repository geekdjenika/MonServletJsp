package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.list.User;

/**
 * Servlet implementation class Monservlet2
 */
@WebServlet("/monservlet2")
public class Monservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Monservlet2() {
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
		
		String pseudo = request.getParameter("pseudo");
		String mot_de_passe = request.getParameter("mot_de_passe");
		
		
        if(login(pseudo,mot_de_passe).getPseudo()==pseudo||login(pseudo,mot_de_passe).getMot_de_passe()==mot_de_passe) {
        	RequestDispatcher rq = request.getRequestDispatcher("Accueil.jsp");
        	rq.forward(request, response);
        } else {
        	RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
		
		
	}
	public User login(String pseudo, String motdepasse) {
		User user=null;
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
            PreparedStatement requette=con.prepareStatement("SELECT * FROM utilisateurs WHERE pseudo = ? and motDePasse = ?;");
            requette.setString(1, pseudo);
			requette.setString(2, motdepasse);
			ResultSet result = requette.executeQuery();
			
			if (result.next()) {
				user = new User();
				user.setNom(result.getString("nom"));
				user.setPseudo(result.getString("prenom"));
				user.setPseudo(result.getString("pseudo"));
				user.setEmail(result.getString("email"));
			}
			
			result.close();
			requette.close();
			return user;
            
        } catch(SQLException e) {
        	System.out.println(e.getMessage());
        }
        return user;
	}

}
