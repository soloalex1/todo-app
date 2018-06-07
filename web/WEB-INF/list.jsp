<%-- 
    Document   : list
    Created on : 21/05/2018, 19:01:19
    Author     : Rafael Notebook
--%>

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
        <link rel="stylesheet" href="./style/normalize.css" type="text/css">
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" 
              integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <link rel="stylesheet" href="./style/main.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script type="application/javascript" src="list-page-listeners.js"></script>
        <script type="application/javascript" src="functions.js"></script>
        <title>to-do app</title>
    </head>
    <body>
        <%
            String user = (String) request.getAttribute("user");
        %>
        <header>
               <h1>to-do app</h1> 
        </header>
        <div id="task-list-box">
            <span>Olá, <%=user%></span>
            </br>
	    <%
		if (user != null) {
		    UserDAO ud = new UserDAO();
		    User u = ud.getUser(user);
		    ArrayList<Task> t = u.getTaskList();
                    if (!t.isEmpty()) {
                        for (int i =0; i < t.size(); i++) {%>
                            <div class="task"><span class="task-text"> <%=t.get(i).getTitle()%></span><span class="task-remove"> X </span></div>
                       <%}
                    }
		}
	    %>
            <input id="task-add-field" type="text" name="taskname" placeholder="Nova tarefa"/>            
        </div>
    </body>
</html>
