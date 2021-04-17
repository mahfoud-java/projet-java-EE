<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">


  <head>
    <!-- Required meta tags -->
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="starter-template.css">
    <title>Liste Matchs</title>
  </head>
  
  
  
  <body>


<div class="container">
 <%@ include file="menu.jsp"%>
 

<c:if test="${!empty sessionScope.login }">
     <p class="lead" style = "text-align : right;color : green;">Vous êtes sur la session : ${sessionScope.login}</p>
</c:if>

    <h1 style="text-align : center; ">Liste des Matchs</h1>
<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
<a class="btn btn-primary" href="/tpWebBd6/ajoutermatch" role="button">Ajouter un match</a>
</div>

<table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:5%">#</th>
      <th scope="col" style="width:5%">Année</th>
      <th scope="col" style="width:5%">Type</th>
      <th scope="col" style="width:15%">Tournoi</th>
      <th scope="col" style="width:15%">Vainqueur</th>
      <th scope="col" style="width:15%">Finaliste</th>
	  <th scope="col" style="width:40%"></th>
	
    </tr>
  </thead>
<tbody>


   <c:forEach var="match" items="${ listeMatchs }">
   
   	<form action="modifiermatch" method="get">
   		<tr>
		       <th scope="row"><c:out value="${ match.id }" /></th>
		      <td><c:out value="${match.epreuve.annee}" /></td>  
		      <td><c:out value="${match.epreuve.typeEpreuve}" /> </td>
		      <td><c:out value="${ match.epreuve.tournoi.nom}" /></td>
		      <td><c:out value="${ match.joueurV.nom} ${ match.joueurV.prenom}" /></td> 
		      <td><c:out value="${ match.joueurF.nom } ${ match.joueurF.prenom }" /></td>     
			  <td>
	 		  <input type ="hidden" name="idmatch" value="${match.id}">
	   		  <button type="submit" name="action" value="modifier" class="btn btn-outline-primary" >Modifier</button>
	   		  <button type="submit"  name="action" value="supprimer" class="btn btn-outline-warning">Supprimer</button>
	   		  </td>
	  </tr>
    </form>
   </c:forEach>
      
  </tbody>

</table>
</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>


