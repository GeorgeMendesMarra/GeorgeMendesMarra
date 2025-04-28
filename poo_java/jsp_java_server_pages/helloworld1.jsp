<%@ page contentType="text/html; charset=UTF-8" language="java" %> <%-- Diretiva que define o tipo de conteúdo e linguagem --%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8"> <%-- Define o conjunto de caracteres da página para evitar problemas com acentuação --%>
    <title>Página JSP - Hello World</title> <%-- Título exibido na aba do navegador --%>
</head>
<body>
    <h1>
        <%-- Scriptlet Java para imprimir texto na página --%>
        <% 
            out.println("Hello World"); 
        %>
    </h1>
</body>
</html>
