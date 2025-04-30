<%@ page contentType="text/html; charset=ISO-8859-1" 
         pageEncoding="ISO-8859-1"
         import="java.util.*, java.text.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Página JSP - Hello World</title>
</head>
<body>
    <h1>Hello World!</h1>

    <%
        // Obtém a data atual
        Date hoje = new Date();

        // Formata a data completa no padrão dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(hoje);

        // Exibe a data formatada na página
        out.println("<p>Hoje é: " + dataFormatada + "</p>");
    %>
</body>
</html>

