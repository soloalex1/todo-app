<%@page import="model.Task"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UserDAO"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700" rel="stylesheet"> 
        <link rel="stylesheet" href="style/main.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script type="application/javascript" src="list-page-listeners.js"></script>
        <script type="application/javascript" src="functions.js"></script>
        <title>To-Do App</title>
    </head>
    <body>
        <%
                String username = (String) session.getAttribute("username");
                User user = null;
                if (username != null) {
                    UserDAO ud = new UserDAO();
                    user = ud.getUser(username);
                }
        %>
        <div id="up-content">
            <header>
                   <h1>To-Do App </h1> 
            </header>
            <main id="content-wrapper">
                <div id="upload-flex">
                    <div id="big-picture">
                        <img src="<%=user.getPicture()%>">
                    </div>
                    
                    <form action="upload" id="pic-flex" enctype="multipart/form-data" method="post">
                        <input class="input-hidden" type="text" name="username" value="<%=username%>">
                        <input class="input-hidden" type="text" name="picture" value="<%=user.getPicture()%>">
                        <div>
                            <label id="selecao-label" for="selecao-button">Selecionar um arquivo:</label>
                            <input id="selecao-button" class="input-hidden" type="file" name="arquivo" value="" />
                            <span id='file-name'></span>
                        </div>
                        <div>
                            <input class="menu-button" type="submit" value="Enviar" />
                            <button class="menu-button" onclick="login">Retornar</button>
                        </div>
                    </form>

                </div>
            </main>
        </div>
        <footer>
            <h5>Oi alexandre, o avilar é tcholão xD</h5>
        </footer>
    </body>
</html>
