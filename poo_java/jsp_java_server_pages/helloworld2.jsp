<%@ page contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1" 
    import="java.util.Date, java.text.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Pagina JSP Ola Mundo</title> <%-- Define o título que aparece na aba do navegador --%>
    </head>
    <body>
        <h1>
            <%-- Início de um bloco de scriptlet Java --%>
            <% 
                out.println("Ola Mundo"); // Imprime "Ola Mundo" no HTML
            %> 
            <br> <%-- Insere uma quebra de linha no HTML --%>
            <%= new Date() %> <%-- Imprime a data e hora atuais --%>
        </h1>
    </body>
</html>

