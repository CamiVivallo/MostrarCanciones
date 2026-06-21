<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle del artista</title>
</head>
<body>

    <h1>Detalle del artista</h1>

    <c:choose>

        <c:when test="${artista != null}">

            <p>
                <strong>ID:</strong>
                <c:out value="${artista.id}" />
            </p>

            <p>
                <strong>Nombre:</strong>
                <c:out value="${artista.nombre}" />
            </p>

            <p>
                <strong>Apellido:</strong>
                <c:out value="${artista.apellido}" />
            </p>

            <p>
                <strong>Biografía:</strong>
                <c:out value="${artista.biografia}" />
            </p>

            <p>
                <strong>Fecha de creación:</strong>
                <c:out value="${artista.fechaCreacion}" />
            </p>

            <p>
                <strong>Fecha de actualización:</strong>
                <c:out value="${artista.fechaActualizacion}" />
            </p>

            <h2>Canciones del artista</h2>

            <c:choose>

                <c:when test="${not empty artista.canciones}">
                    <ul>
                        <c:forEach
                            var="cancion"
                            items="${artista.canciones}">

                            <li>
                                <a href="/canciones/detalle/${cancion.id}">
                                    <c:out value="${cancion.titulo}" />
                                </a>
                            </li>

                        </c:forEach>
                    </ul>
                </c:when>

                <c:otherwise>
                    <p>Este artista todavía no tiene canciones.</p>
                </c:otherwise>

            </c:choose>

        </c:when>

        <c:otherwise>
            <p>El artista no existe.</p>
        </c:otherwise>

    </c:choose>

    <a href="/artistas">
        Volver a lista de artistas
    </a>

</body>
</html>