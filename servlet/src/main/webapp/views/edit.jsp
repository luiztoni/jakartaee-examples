<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="/assets/materialize.min.css">
	<link rel="stylesheet" href="/assets/main.css">
    <title>Cocada</title>
</head>
 <body>
    <nav>
        <div class="nav-wrapper green">
            <a href="#!" class="brand-logo center">Edit Product</a>
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
    <br>
    <div class="row container">
        <form action="/admin/product/update/${product.id}" method="POST">
            <div class="row">
                <input id="name" type="text" name="name" class="validate" value="${product.name}">
            </div>
            <div class="row">
                <input id="description" type="text" name="description" class="validate" value="${product.description}">
            </div>
            <div class="row">
                <input id="price" type="text" name="price" class="validate" value="${product.price}">
            </div>
            <div class="row">
                <input type="hidden" name="token" value="">
            </div>
            <input type="hidden" name="_method" value="PUT">
            <button class="btn waves-effect waves-light" type="submit">Enviar</button>
        </form>
    </div>
</main>
    <footer class="page-footer green">
        <div class="footer-copyright" id="pagefooter">
            <div class="container">
                Company <a class="grey-text text-lighten-4 right" href="#!">about</a>
            </div>
        </div>
    </footer>
    <script src="/assets/materialize.min.js"></script>
    <script src="/assets/main.js"></script>
  </body>
</html>


