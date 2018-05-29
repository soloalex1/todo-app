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
            var text = document.createElement("span");
            text.className = "task-text";
            var x = document.createElement("span");
            x.className = "task-remove";
            x.innerHTML = " X ";
            x.style = "color: red";
            var tasktext = document.createTextNode(elementcopy.value);
            text.appendChild(tasktext);
            task.appendChild(text);
            task.appendChild(x);
            parent.appendChild(task);
            tasktext.addEventListener("click", function(){ crossTask(tasktext); }, false);
            x.addEventListener("click", function(){ removeTask(x); }, false);

            //And a new add-task-field element
            elementcopy.value = "";
            parent.appendChild(elementcopy);

        }
    }
};

function crossTask(elem) {
    elem.innerHTML = elem.innerHTML.strike();
    console.log("benis click");
};

function removeTask(elem) {
    elem.parentNode.remove(elem);
    console.log("benis remove click");
};