/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {
    document.getElementById("add-task-field").addEventListener("keydown", function(e) {
        // Enter is pressed
        if (e.keyCode === 13) {
            var element = this;
            var parent = this.parentNode;
            var text = element.value;
            if (text.length > 0) {
                window.console.log(text);
                /*var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange=function()
                {
                    if (xmlhttp.readyState===4 && xmlhttp.status===200)
                    {
                        document.getElementById("add-task-field").innerHTML=xmlhttp.responseText;
                    }
                }*/
                
                //Delete and copy element.
                var elementcopy = element;
                parent.removeChild(element);
                
                //Then, create new task element
                var task = document.createElement("div");
                var tasktext = document.createTextNode(elementcopy.value);
                task.appendChild(tasktext);
                parent.appendChild(task);
                
                //And a new add-task-field element
                elementcopy.value = "";
                parent.appendChild(elementcopy);
                
            }
        }
    }, false);
}
