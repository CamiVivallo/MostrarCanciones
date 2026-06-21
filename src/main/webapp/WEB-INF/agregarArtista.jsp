<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar artista</title>
</head>
<body>

    <h1>Agregar un nuevo artista</h1>

    <form:form
        action="/artistas/procesa/agregar"
        method="POST"
        modelAttribute="artista">

        <div>
            <form:label path="nombre">
                Nombre:
            </form:label>

            <form:input
                path="nombre"
                type="text"
            />

            <form:errors path="nombre" />
        </div>

        <br>

        <div>
            <form:label path="apellido">
                Apellido:
            </form:label>

            <form:input
                path="apellido"
                type="text"
            />

            <form:errors path="apellido" />
        </div>

        <br>

        <div>
            <form:label path="biografia">
                Biografía:
            </form:label>

            <form:textarea
                path="biografia"
                rows="5"
                cols="40"
            />

            <form:errors path="biografia" />
        </div>

        <br>

        <button type="submit">
            Agregar artista
        </button>

    </form:form>

    <br>

    <a href="/artistas">
        Volver a lista de artistas
    </a>

</body>
</html>