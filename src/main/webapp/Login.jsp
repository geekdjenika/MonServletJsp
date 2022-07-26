<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>
        <center>
            <h1>Connectez-vous !</h1>
        </center>
        <div>
            <form action="monservlet2" method="POST">
                <center>
                    <table>
                        <tr>
                            <td>Pseudo</td>
                            <td><input type="pseudo" name="pseudo" placeholder="monpseudo"></td>
                        </tr>
                        <tr>
                            <td>Mot de passe</td>
                            <td><input type="password" name="mot_de_passe" placeholder="unique1234"></td>
                        </tr>
                        <tr><td></br></td></tr>
                        <tr>
                            <td colspan="2" style="text-align: center"><input type="submit" value="Connexion"></td>
                        </tr>
                    </table>
                </center>
            </form>
        </div>
    </body>
</html>