<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">  
  <img class="mb-4" src="plogo.png" style="width:25px;"  alt="">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
 <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ajouter</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="/tpWebBd6/ajouterjoueur">Ajouter un joueur</a>
          <a class="dropdown-item" href="/tpWebBd6/ajoutertournoi">Ajouter un tournoi</a>
          <a class="dropdown-item" href="/tpWebBd6/ajoutermatch">Ajouter un  match</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Lister</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="/tpWebBd6/listerjoueur">Lister les joueurs</a>
          <a class="dropdown-item" href="/tpWebBd6/listertournoi">Lister les tournois</a>
          <a class="dropdown-item" href="/tpWebBd6/listermatch">Lister les  matchs</a>
        </div>
      </li>
	
       <li class="nav-item">
	    <form class="form-inline my-2 my-lg-0" action="listerjoueur" method="post">      
         <button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="Deconnexion">Deconnexion</button>
         </form>
      </li>
     
    </ul>
    <form class="form-inline my-2 my-lg-0" action="listerjoueur" method="post">
      <input class="form-control mr-sm-2" type="text" name="txtsearch" placeholder="Search" aria-label="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="Rechercher">Rechercher</button>
    </form>
  </div>
</nav>
