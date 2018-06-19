/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createTask(event, elem) {
    // Enter is pressed
    if (event.keyCode === 13) {
        var element = elem;
        var parent = elem.parentNode;
        var text = element.value;
        if (text.length > 0) {
            //Delete and copy element.
            var elementcopy = element;
            parent.removeChild(element);

            //Then, create new task element
            var task = document.createElement("div");
            task.className = "task";
            
            var taskcheck = document.createElement("input");
            taskcheck.className = "task-checkbox";
            taskcheck.type = "checkbox";
                    
            var text = document.createElement("span");
            text.className = "task-title";
            
            var x = document.createElement("span");
            x.className = "task-remove";
            x.innerHTML = " X ";
            
            var tasktext = document.createTextNode(elementcopy.value);
            
            task.appendChild(taskcheck);
            text.appendChild(tasktext);
            task.appendChild(text);
            task.appendChild(x);
            parent.appendChild(task);
            
            taskcheck.addEventListener("click", function(){ crossTask(text); }, false);
            x.addEventListener("click", function(){ removeTask(x); }, false);

            //And a new add-task-field element
            elementcopy.value = "";
            parent.appendChild(elementcopy);

        }
    }
};

function crossTask(elem) {
    elem.innerHTML = elem.innerHTML.strike();
};

function removeTask(elem) {
    elem.parentNode.parentNode.removeChild(elem.parentNode);
};

function saveTaskList(username) {
    var data = "";
    var tasks = $(".task");
    
    $(tasks).each( function(index) {
        if (index === 0) {
            data += '<?xml version="1.0" encoding="UTF-8"?>';
            data += "<tasks>";
        }
        data += "<task>";
        data += "<title>"+ $(this).children(".task-title").text().trim() +"</title>";
        data += "<description> test </description>";
        data += "<stat> false </stat>";
        data += "</task>";
        if (index === tasks.length - 1) {
            data += "</tasks>";
        }
    });
    
    console.log(data);
    
    //send to database
    $.ajax({
        type: "post",
        url: "tasks",
        data: { 
            "userLogin":username,
            "tasks":data
        }
    });
    
}