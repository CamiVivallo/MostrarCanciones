<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isErrorPage="true" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar canción</title>
</head>
<body>

    <h1>Editar canción</h1>

    <form:form
        action="/canciones/procesa/editar/${cancion.id}"
        method="POST"
        modelAttribute="cancion">

        <input
            type="hidden"
            name="_method"
            value="PUT"
        >

        <div>
            <form:label path="titulo">
                Título:
            </form:label>

            <form:input
                path="titulo"
                type="text"
            />

            <form:errors path="titulo" />
        </div>

        <br>

        <div>
            <label for="idArtista">
                Artista:
            </label>

            <select
                id="idArtista"
                name="idArtista"
                required>

                <option value="">
                    Selecciona un artista
                </option>

                <c:forEach
                    var="artista"
                    items="${listaArtistas}">

                    <c:choose>

                        <c:when test="${cancion.artista != null && cancion.artista.id == artista.id}">
                            <option
                                value="${artista.id}"
                                selected>

                                <c:out value="${artista.nombre}" />
                                <c:out value="${artista.apellido}" />
                            </option>
                        </c:when>

                        <c:otherwise>
                            <option value="${artista.id}">
                                <c:out value="${artista.nombre}" />
                                <c:out value="${artista.apellido}" />
                            </option>
                        </c:otherwise>

                    </c:choose>

                </c:forEach>

            </select>
        </div>

        <br>

        <div>
            <form:label path="album">
                Álbum:
            </form:label>

            <form:input
                path="album"
                type="text"
            />

            <form:errors path="album" />
        </div>

        <br>

        <div>
            <form:label path="genero">
                Género:
            </form:label>

            <form:input
                path="genero"
                type="text"
            />

            <form:errors path="genero" />
        </div>

        <br>

        <div>
            <form:label path="idioma">
                Idioma:
            </form:label>

            <form:input
                path="idioma"
                type="text"
            />

            <form:errors path="idioma" />
        </div>

        <br>

        <button type="submit">
            Actualizar canción
        </button>

    </form:form>

    <br>

    <a href="/canciones">
        Volver a lista de canciones
    </a>

</body>
</html>