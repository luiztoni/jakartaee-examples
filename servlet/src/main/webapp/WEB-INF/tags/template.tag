<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="/assets/materialize.min.css">
	<link rel="stylesheet" href="/assets/main.css">
	<script src="/assets/materialize.min.js"></script>
    <script src="/assets/main.js"></script>
    <title>HeroApp</title>
</head>
 <body>
    <nav>
        <div class="nav-wrapper green">
            <a href="#!" class="brand-logo center">Projeto Livraria</a>
            <ul class="right hide-on-med-and-down">
                <li>
                     <a class="btn-small btn-floating red" onclick="event.preventDefault(); document.getElementById('logout-form').submit();"><i class="material-icons">settings_power</i></a>
                     <form id="logout-form"  action="/auth/logout" method="POST">
                         <input type="hidden" name="token" value="">
                     </form>
                </li>
            </ul>
        </div>
    </nav>
     <main>
         <div class="container" id="body">
            <jsp:doBody/>
          </div>
     </main>
    <footer class="page-footer green">
        <div class="footer-copyright" id="pagefooter">
            <div class="container">
                Company <a class="grey-text text-lighten-4 right" href="#!">about</a>
            </div>
        </div>
    </footer>
  </body>
</html>
