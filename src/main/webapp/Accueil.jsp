<%@page import="com.list.User"%>
<%@page import="java.util.List"%>
<%@page import="com.servlets.Monservlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenu</title>
</head>
<body>
<center>
	<h3>
		Bienvenu <% out.print(request.getAttribute("name")+" "+request.getAttribute("prenom"));  %>
	</h3>
	<!-- <a href="Login.jsp">Deconnexion</a> -->
	<a href="Login.jsp"><input type="submit", value="Se deconnecter"></a>
</center>

</body>
<footer>
	<center>
		<table>
		<tr>
			<%
			
			Monservlet ms=new Monservlet();
			List<User> maliste= ms.userList();
			for(int i=0;i<maliste.size();i++) {
	        	out.println(maliste.get(i).getNom());
	        	out.println("||");
	        	out.println(maliste.get(i).getPrenom());
	        	out.println("||");
	        	out.println(maliste.get(i).getEmail());
	        	out.println("||");
	        	out.println(maliste.get(i).getPseudo());
	        	out.println();%> </br> <%
	        } %>
		</tr>
	</table>
	
	</center>
</footer>
</html>