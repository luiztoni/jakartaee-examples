<!DOCTYPE html>
<%@page import="java.util.*"%>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="/assets/materialize.min.css">
	<link rel="stylesheet" href="/assets/main.css">
	<title>App</title>
</head>

<body>
	<nav>
		<div class="nav-wrapper green">
			<a href="#" class="brand-logo center">
				<% if (request.getAttribute("welcome") != null) {
            	    out.print(request.getAttribute("welcome"));
            	 } else {
            	    out.print("Cocada");
            	 } %>
            </a>
		</div>
	</nav>
	<main>
		<div class="container-center">
			<div class="row valign-wrapper">
				<div class="col s12">
					<div class="card white hoverable">
						<div class="card-content">
							<span class="card-title">Logar</span>
							<div>
								<form method="post" action="/login">
									<label for="email" class="">Nome</label>
									<input id="email" class="" type="text" name="email">
									<label for="password" class="">Senha</label>
									<input id="password" class="" type="password" name="password">
									<input class="btn btn-large green" type="submit" value="Start">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer class="page-footer green">
		<div class="footer-copyright">
            <div class="container">
                Company
                <a class="grey-text text-lighten-4 right" href="#!">about</a>
            </div>
        </div>
	</footer>
    <script src="/assets/materialize.min.js"></script>
	<% if (request.getAttribute("message") != null) { %>
	    <script> M.toast({html: '<% out.print(request.getAttribute("message")); %>'});</script>
	<% } %>
</body>
</html>
