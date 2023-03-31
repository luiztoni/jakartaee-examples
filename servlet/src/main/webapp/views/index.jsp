<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:template>
<jsp:body>
    <c:choose>
        <c:when test="${not empty products}">
            <br>
            <br>
            <div class="row">
                <div class="input-field col s6">
                    <input placeholder="Filter by name..." id="text" type="text" onkeyup="filterTable()" class="validate">
                    <label for="text">Search</label>
                </div>
            </div>
            <table class="striped" id="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${products}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.description}</td>
                            <td>${p.price}</td>
                            <td><a id="${p.id}" onclick="event.preventDefault(); getForm('edit-form',id);"><i class="material-icons">edit</i></a></td>
                            <td><a id="${p.id}" onclick="event.preventDefault(); getForm('delete-form',id);"><i class="material-icons">delete</i></a></td>
                        	<td><a id="${p.id}" href="/admin/product/show/${p.id}"><i class="material-icons">pageview</i></a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <form id="edit-form"  action="/admin/product/edit/" method="POST">
                <input type="hidden" name="token" value="">
            </form>
            <form id="delete-form" action="/admin/product/delete/" method="POST">
                <input type="hidden" name="token" value="">
                <input type="hidden" name="_method" value="DELETE">
            </form>
        </c:when>
        <c:otherwise>
            <p style="text-align:center"> Create a product! </p>
        </c:otherwise>
    </c:choose>
    <br>
    <br>
    <a class="btn green" href="#modal1" name="action" onclick="show_modal()">New
        <i class="material-icons right">add</i>
    </a>

    <!-- Modal Structure -->
    <div id="modal1" class="modal">
        <div class="modal-content">
            <h5>New</h5>
            <div class="row">
            <form action="/admin/product/store" method="POST" class="col s12">
                <div class="row">
                <div class="input-field col s6">
                    <input id="name" type="text" name="name" class="validate">
                    <label for="icon_prefix">Name</label>
                </div>

                <div class="input-field col s6">
                    <input id="description" name="description" type="text" class="validate">
                    <label for="description" data-error="wrong" data-success="right">Descrption</label>
                </div>
                </div>
                  <div class="row">
                
                <div class="input-field col s6">
                    <input id="price" name="price" type="number" class="validate">
                    <label for="price" data-error="wrong" data-success="right">Preco</label>
                </div>
                </div>
                <div class="modal-footer">
                    <a href="#!" class="modal-action modal-close btn-flat">Cancelar</a>
                    <button class="btn waves-effect waves-light" type="submit">Enviar</button>
                </div>
            </form>
            </div>
        </div>
    </div>
    <br>
    <br>
</jsp:body>
</t:template>
