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
                String username = (String) request.getAttribute("username");
                User user = null;
                if (username != null) {
                    UserDAO ud = new UserDAO();
                    user = ud.getUser(username);
                    session.setAttribute("username", username);
                }
            %>
        <div id="up-content">
            <header>
                   <h1>To-Do App</h1> 
                   
            </header>
            <main id="content-wrapper">
                <div class="welcome-box">
                    <span id="user-welcome">Olá, <%=username%></span>
                    <div>
                        <button id="side_bar_save" onclick="saveTaskList()"> Salvar </button>
                        <span id="side_bar_logout"><a href="logout"> Logout </a></span>
                        
                    </div>
                </div>
                <section id="task-list-box">
                    
                    </br>
                    <%
                        ArrayList<Task> t = user.getTaskList();
                        if (!t.isEmpty()) {
                            for (int i =0; i < t.size(); i++) {%>
                            <div class="task" id="<%=t.get(i).getID()%>">
                                <input class="task-checkbox" type="checkbox" <% if (t.get(i).getStat()==true) {%> checked <%}%>>
                                <% if (t.get(i).getStat()==true) {%>
                                <%}%>
                                <span class="task-title "><%=t.get(i).getTitle()%></span>
                                
                                <span class="task-remove"> X </span>
                                
                            </div>
                            <hr>
                           <%}
                        }
                    %>
                    <input id="task-add-field" type="text" name="taskname" maxlength="75" placeholder="Nova tarefa (máximo 75 caracteres)"/>
                </section>
            </main>
        </div>
        <footer>
            <h5>Oi alexandre, o avilar é tcholão xD</h5>
        </footer>
    </body>
</html>
