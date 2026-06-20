<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html lang="es">

        <head>
            <meta charset="UTF-8">
            <title>Detalle de la canción</title>
        </head>

        <body>

            <h1>Detalle de la canción</h1>

            <c:choose>

                <c:when test="${cancion != null}">

                    <p>
                        <strong>ID:</strong>
                        <c:out value="${cancion.id}" />
                    </p>

                    <p>
                        <strong>Título:</strong>
                        <c:out value="${cancion.titulo}" />
                    </p>

                    <p>
                        <strong>Artista:</strong>
                        <c:out value="${cancion.artista}" />
                    </p>

                    <p>
                        <strong>Álbum:</strong>
                        <c:out value="${cancion.album}" />
                    </p>

                    <p>
                        <strong>Género:</strong>
                        <c:out value="${cancion.genero}" />
                    </p>

                    <p>
                        <strong>Idioma:</strong>
                        <c:out value="${cancion.idioma}" />
                    </p>

                    <p>
                        <strong>Fecha de creación:</strong>
                        <c:out value="${cancion.fechaCreacion}" />
                    </p>

                    <p>
                        <strong>Fecha de actualización:</strong>
                        <c:out value="${cancion.fechaActualizacion}" />
                    </p>

                </c:when>

                <c:otherwise>
                    <p>La canción no existe.</p>
                </c:otherwise>

            </c:choose>

            <a href="/canciones/formulario/editar/${cancion.id}">
                Actualizar canción
            </a>

            <form action="/canciones/eliminar/${cancion.id}" method="POST">

                <input type="hidden" name="_method" value="DELETE">

                <button type="submit">
                    Eliminar canción
                </button>

            </form>

            <br>
            <br>
            <a href="/canciones">
                Volver a lista de canciones
            </a>

        </body>

        </html>