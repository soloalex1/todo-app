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
    <body>
        <header>
               <h1>to-do app</h1> 
        </header>
        <section id="signin">
            <form class="pure-form pure-form-aligned" action="login" method="post">
                <fieldset>
                    <div class="pure-control-group">
                        <input id="name" type="text" placeholder="username" name="username">
                    </div>
                    <div class="pure-control-group">
                        <input id="password" type="password" placeholder="password" name="senha">
                    </div>
                    <button type="submit" class="pure-button pure-button-primary">Login</button>
                    </div>
                </fieldset>
            </form>
        </section>
    </body>
</html>