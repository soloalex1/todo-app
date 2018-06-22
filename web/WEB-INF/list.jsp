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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <link rel="stylesheet" href="./style/main.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
        <script type="application/javascript" src="list-page-listeners.js"></script>
        <script type="application/javascript" src="functions.js"></script>
        <title>to-do app</title>
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
        <header>
	    <span id="user-welcome"><%=username%></span>
	    <button id="task-save" class="pure-button pure-button-primary" onclick='saveTaskList()'> Salvar </button>
	    <a id="logout-button" class="pure-button" href="logout"> Logout </a>
        </header>
	<main id="content-wrapper">
	    <section id="task-list-box">
		</br>
		<%
		    ArrayList<Task> t = user.getTaskList();
		    if (!t.isEmpty()) {
			for (int i =0; i < t.size(); i++) {%>
			<div class="task" id="<%=t.get(i).getID()%>">
			    <input class="task-checkbox" type="checkbox" <% if (t.get(i).getStat()==true) {%> checked <%}%>>
			    <% if (t.get(i).getStat()==true) {%>
			    <s>
			    <%}%>
			    <span class="task-title"><%=t.get(i).getTitle()%></span>
			    <% if (t.get(i).getStat()==true) {%>
			    </s>
			    <%}%>
			    <span class="task-remove"> X </span>
			</div>
		       <%}
		    }
		%>
		<input id="task-add-field" type="text" name="taskname" maxlength="75" placeholder="Nova tarefa (máximo 75 caracteres)"/>
	    </section>
	</main>
    </body>
</html>
