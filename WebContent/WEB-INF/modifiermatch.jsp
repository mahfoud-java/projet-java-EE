<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <title>Modifier un match</title>
  </head>
  <body>


<main role="main" class="container">
    <%@ include file="menu.jsp"%> 
    <c:if test="${!empty sessionScope.login }">
     <p class="lead" style = "text-align : right;color : green;">Vous êtes sur la session : ${sessionScope.login}</p>
</c:if>
  <div class="starter-template">
    <h1>Modifier un match</h1>
  </div>


   <div style="width:40%; margin:auto;"> 

<form class="needs-validation" method="post" action="modifiermatch" novalidate >
  <div class="form-row">
    <div class="col-md-4 mb-3">
      <label for="validationCustom01">Année</label>
      <input type="text" class="form-control" style="width:400px;" id="validationCustom01" name="txtAnnee" value="<c:out value="${match.epreuve.annee}"/>" required>

    </div>
   
  <div class="form-row">
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Type</label>
      <select class="custom-select" id="validationCustom04" name="txtType" style="width:400px;" required>
        
        <option value="F" <c:if test="${match.epreuve.typeEpreuve == 'F'}" >selected</c:if>>Femme</option>
		<option value="H" <c:if test="${match.epreuve.typeEpreuve == 'H'}" >selected</c:if>>Homme</option>
      </select>

    </div>  
    </div>
   
    <div class="form-row">
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Selectionner Tournoi</label>
     <select class="custom-select" id="validationCustom04" name="idTournoi" style="width:400px;" required>
       
           	
           	<c:forEach var="tournoi" items="${ listeTournois }">
  
   			 <option value="<c:out value="${ tournoi.id}"/>" <c:if test="${match.epreuve.tournoi.nom == tournoi.nom }" >selected</c:if>> <c:out value="${ tournoi.nom }"/></option>
   			
	 
   			</c:forEach>
 
 </select>
    </div>  
    </div>
    
                <div class="form-row">
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Selectionner Finaliste</label>
     <select class="custom-select" id="validationCustom04" name="idJoueurF" style="width:400px;" required>
       
           	
           	<c:forEach var="joueur" items="${ listeJoueurs }">
  
   			 <option value="<c:out value="${ joueur.id }"/>" <c:if test="${match.joueurF.id == joueur.id }" >selected</c:if>> <c:out value="${joueur.nom }  ${joueur.prenom }"/></option>
   			
	 
   			</c:forEach>
 
 </select>
    </div>  
    </div>
 
    
        <div class="form-row">
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Selectionner Vainqueur</label>
     <select class="custom-select" id="validationCustom04" name="idJoueurV" style="width:400px;" required>
       
           	
           	<c:forEach var="joueur" items="${ listeJoueurs }">
  
   			 <option value="<c:out value="${ joueur.id }"/>" <c:if test="${match.joueurV.id == joueur.id }" >selected</c:if>> <c:out value="${joueur.nom }  ${joueur.prenom }"/></option>
   			
	 
   			</c:forEach>
 
 </select>
    </div>  
    </div>
    

     
  
  <button class="btn btn-primary center" type="submit">Submit form</button>
  </div>
</form>

   </div>

</main><!-- /.container -->

<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>


    