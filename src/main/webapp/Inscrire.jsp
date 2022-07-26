<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
        <center>
            <h1>Veuillez vous inscrire !</h1>
        </center>
        <div>
        	<% if(request.getAttribute("erreur") != null){
        		
        		%>
        		<center><div>Tous les champs doivent être remplis</div></center>
        		
        		<%
        	} %>
            <form action="monservlet" method="POST">
                <center>
                    <table>
                        <tr>
                            <td><label>Nom</label></td>
                            <td><input type="text" name="nom" placeholder="monnom" value="<% if(request.getAttribute("name") != null) out.print(request.getAttribute("name")); %>"></td>
                        </tr>
                        <tr>
                            <td><label>Prénom</label></td>
                            <td><input type="text" name="prenom" placeholder="monprenom" value="<% if(request.getAttribute("name") != null) out.print(request.getAttribute("prenom")); %>"></td>
                        </tr>
                        <tr>
                            <td><label>Pseudo</label></td>
                            <td><input type="text" name="pseudo" placeholder="monpseudo" value="<% if(request.getAttribute("name") != null) out.print(request.getAttribute("pseudo")); %>"></td>
                        </tr>
                        <tr>
                            <td><label>Email</label></td>
                            <td><input type="email" name="email" placeholder="xyz@exemple.co" value="<% if(request.getAttribute("name") != null) out.print(request.getAttribute("email")); %>"></td>
                        </tr>
                        <tr>
                            <td><label>Mot de passe</label></td>
                            <td><input type="password" name="motdepasse" placeholder="unique1234" value="<% if(request.getAttribute("name") != null) out.print(request.getAttribute("mdp")); %>"></td>
                        </tr>
                        <tr>
                            <td><label>Confirmer le mot de passe</label></td>
                            <td><input type="password" name="motdepasseconfirme" placeholder="unique1234" ></td>
                        </tr>
                        <%-- Retour à la ligne --%>
                        <tr><td></br></td></tr>
                        <tr>
                            <td colspan="2" style="text-align: center"><input type="submit" value="S'inscrire"></td>
                        </tr>
                    </table>
                    
                </center>
            </form>
            <% if(request.getAttribute("erreurmdp")!=null) {
            	%>
            	<center><div class="mdp">Les mots de passe ne correspondent pas !</div></center>
            	<%}%>
        </div>
    </body>
</html>

<style>

body{
 background-color:#ffff;	
}
label,h1{
	color:black;
}
.mdp{
	color:red;
}

table{
	background-color:#f0f0f0;
}

</style>
