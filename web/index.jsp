<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet"> 
        <link rel="stylesheet" href="./style/normalize.css" type="text/css">
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" 
              integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <link rel="stylesheet" href="./style/main.css" type="text/css">
		<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <title>to-do app</title>
    </head>
    <body id="signin-body">
        <%
            // recuperando todos os cookies da requisição
            Cookie[] cookies = request.getCookies();
            
            // utilizando strings vazias para referenciar login e checkbox
            String login = "";
            String checkbox = "";
            
            // obtendo o valor dos cookies remember e userlogin (definidos em LoginServlet)
            for(Cookie c : cookies){
                if(c.getName().equals("remember")){
                    checkbox = c.getValue();
                }
                if(c.getName().equals("userlogin")){
                    login = c.getValue();
                }
            }
        %>
        <header>
            <h1>to-do app</h1> 
        </header>
        <section id="signin">
	    <% 
                // se houver algum aviso, renderiza
		if (request.getAttribute("warning") != null) { %>
		<h4><%=request.getAttribute("warning")%></h4>
		<%}
	    %>
            <form class="pure-form pure-form-aligned" action="main" method="post">
                <fieldset>
                    <div class="pure-control-group">
                        <label for="name">username</label>
                        <input id="name" type="text" placeholder="ex.: avilar" name="username" value="<%= login %>">
<!--                        <span class="pure-form-message-inline">este campo é obrigatório.</span>-->
                    </div>
                    <div class="pure-control-group">
                        <label for="password">password</label>
                        <input id="password" type="password" placeholder="ex.: avilar123" name="senha">
                    </div>
                    <label for="remember" class="pure-checkbox">
                        <input id="remember" type="checkbox" value="true" name="checkbox" checked="<%= (checkbox.equals("true") ? "true" : "false") %>"> remember me
                    </label>
                    <div class="pure-control-group signin-button">
                        <button type="submit" class="pure-button pure-button-primary">Login</button>
                    </div>
                </fieldset>
            </form>
        </section>
    </body>
</html>
