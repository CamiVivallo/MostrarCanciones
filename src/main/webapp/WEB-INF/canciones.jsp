<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de canciones</title>
</head>
<body>

    <h1>Lista de canciones</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Título</th>
                <th>Artista</th>
                <th>Detalle</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="cancion" items="${listaCanciones}">
                <tr>
                    <td>
                        <c:out value="${cancion.titulo}" />
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${cancion.artista != null}">
                                <c:out value="${cancion.artista.nombre}" />
                                <c:out value="${cancion.artista.apellido}" />
                            </c:when>

                            <c:otherwise>
                                Sin artista asignado
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <a href="/canciones/detalle/${cancion.id}">
                            Detalle
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>

    <a href="/canciones/formulario/agregar/0">
        Agregar canción
    </a>

    <br>
    <br>

    <a href="/artistas">
        Ir a artistas
    </a>

</body>
</html>