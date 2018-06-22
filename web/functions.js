/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createTask() {
    
    var addTaskField = $("#task-add-field");
    var taskListBox = $("#task-list-box");
    var newTaskTitle = null;
    
    if (addTaskField.has("value")) {
	
	var text = addTaskField.val();
	if (text.length > 0) {
	    newTaskTitle = text;
	}
    }
    
    if (newTaskTitle !== null) {
	
	var newElement = "";
	newElement += '<div class="task">';
    newElement += '<input class="task-checkbox" type="checkbox">';
	newElement += '<span class="task-title">';
	newElement += newTaskTitle;
	newElement += "</span>";
	newElement += '<span class="task-remove"> X </span>';
	newElement += "</div>";
	
	addTaskField.before(newElement);
	
	$(".task").last().children(".task-checkbox").click(function(e) {
	    var parentTask = $(this).parent();
	    crossTask(parentTask);
	});
	
	$(".task").last().children(".task-remove").click(function(e) {
	    var parentTask = $(this).parent();
	    removeTask(parentTask);
	});
	
	addTaskField.val("");
	addTaskField.focus();
    }
};

function crossTask(task) {
    /*
     * Cruza o titulo do elemento task apontado. Se já estiver cruzado, descruza.
     */
    if (task.find(".task-checkbox").is(":checked"))
	task.find(".task-title").wrap("<s></s>");
    else if (task.find(".task-title").parent().is("s"))
	task.find(".task-title").unwrap();
};

function removeTask(task) {
    /*
     * Remove o elemento task apontado
     */
    if (task.hasClass("task")) {
	task.remove();
    }
};

function saveTaskList() {
    /*
     * Lê os elementos .task da página, transforma numa string com formatação XML e envia para o 
     * servlet junto ao username.
     */
    
    var data = "";
    var tasks = $(".task");
    
    $(tasks).each( function(index) {
	
        if (index === 0) {
            data += '<?xml version="1.0" encoding="UTF-8"?>';
            data += "<tasks>";
        }
	
        data += "<task>";
        data += "<title>"+ $(this).find(".task-title").text().trim() +"</title>";
        data += "<description>"+ $(this).find(".task-title").text().trim() +"</description>";
	
	var stat = "false";
	if ($(this).find(".task-checkbox").is(":checked")) {
	    stat = "true";
	}
	
        data += "<stat>"+ stat +"</stat>";
        data += "</task>";
        if (index === tasks.length - 1) {
            data += "</tasks>";
        }
    });
    
    /* Realiza chamada AJAX tipo POST para o servlet tasks, enviando userLogin e data como parametros.
     * userLogin é o usuário no qual serão adicionadas as tasks.
     * data vai formatada como XML, e é processada no servlet.
     */   
	    
    if (data.length > 0) {
	$.ajax({
	    type: "POST",
	    url: "tasks",
	    data: {
		"tasks": data
	    }
	});
	console.log("data sent");
    }
    
}