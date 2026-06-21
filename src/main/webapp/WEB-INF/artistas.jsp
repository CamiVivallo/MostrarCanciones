<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de artistas</title>
</head>
<body>

    <h1>Lista de artistas</h1>

    <ul>
        <c:forEach var="artista" items="${listaArtistas}">
            <li>
                <a href="/artistas/detalle/${artista.id}">
                    <c:out value="${artista.nombre}" />
                    <c:out value="${artista.apellido}" />
                </a>
            </li>
        </c:forEach>
    </ul>

    <a href="/artistas/formulario/agregar/0">
        Agregar artista
    </a>

    <br>
    <br>

    <a href="/canciones">
        Ir a canciones
    </a>

</body>
</html>