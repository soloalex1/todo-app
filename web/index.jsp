<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet"> 
        <link rel="stylesheet" href="style/main.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <title>To-Do App</title>
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
        <div id="up-content">
            <header>
                <h1>To-Do App</h1> 
            </header>
            <section id="signin">
                
                <form action="main" method="post">
                    <!--coloquei a alerta do login aqui dentro, mas ele não tá atrapalhando nada, só para questão de layout mesmo--> 
                    <% 
                    // se houver algum aviso, renderiza
                    if (request.getAttribute("warning") != null) { %>
                    <p><%=request.getAttribute("warning")%></p>
                    <%}
                    %>
                    <fieldset>
                        <div class="input-info">
                            <label for="name">Username</label>
                            <input id="name" type="text" placeholder="ex.: avilar" name="username" value="<%= login %>">
                        </div>
                        <div class="input-info">
                            <label for="password">Password</label>
                            <input id="password" type="password" placeholder="ex.: avilar123" name="senha">
                        </div>
                        <label for="remember" class="">
                            <input id="remember" type="checkbox" value="true" name="checkbox" checked="<%= (checkbox.equals("true") ? "true" : "false") %>"> Remember me!
                        </label>
                        <div class="">
                            <button type="submit" class="input-sub">Login</button>
                        </div>
                    </fieldset>
                </form>
            </section>
        </div>
        <footer>
            <h5>Oi alexandre. eu, guilherme candido, declaro atraves desta mensagem meu desejo e paixão eterna e inigualável por jesus. amem xd</h5>
        </footer>
    </body>
</html>