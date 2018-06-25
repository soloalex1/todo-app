function createTask() {
    
    const addTaskField = $("#task-add-field");
    const taskListBox = $("#task-list-box");
    let newTaskTitle = null;
    
    if (addTaskField.has("value")) {
	
	let text = addTaskField.val();
	if (text.length > 0) {
	    if (text.length <= 75) {
		newTaskTitle = text;
	    } else {
		alert("Quantidade máxima de caracteres excedida.")
	    }
	}
    }
    
    if (newTaskTitle !== null) {
	
	let newElement = "";
	newElement += '<div class="task">';
        newElement += '<input class="task-checkbox" type="checkbox">';
	newElement += '<span class="task-title">';
	newElement += newTaskTitle;
	newElement += "</span>";
	newElement += '<span class="task-remove"> X </span>';
	newElement += "</div>";
	
	addTaskField.before(newElement);
	
	$(".task").last().children(".task-checkbox").click(function(e) {
	    let parentTask = $(this).parent();
	    crossTask(parentTask);
	});
	
	$(".task").last().children(".task-remove").click(function(e) {
	    let parentTask = $(this).parent();
	    removeTask(parentTask);
	});
	
	colorTaskBackground($(".task").last());
	
	addTaskField.val("");
	addTaskField.focus();
    }
};

function colorTaskBackground(task) {
    if ($(task).find('.task-checkbox').is(':checked')) {
	$(task).css('background-color', '#d9f8d8');
    } else {
	$(task).css('background-color', '#f5a2b6');
    }
}

function crossTask(task) {
    /*
     * Cruza o titulo do elemento task apontado. Se já estiver cruzado, descruza.
     */
    if (task.find(".task-checkbox").is(":checked"))
	$(task).css("background-color", "#d9f8d8")
    else if (!task.find(".task-checkbox").is(":checked"))
	$(task).css("background-color", "#f5a2b6")
};

function removeTask(task) {
    /*
     * Remove o elemento task apontado
     */
    if (task.hasClass("task")) {
	task.remove();
    }
};

function tasksToXML(tasks_e) {
    let data = "";
    
    data += '<?xml version="1.0" encoding="UTF-8"?>';
    data += "<tasks>";
	    
    $(tasks_e).each(function(index) {
        data += "<task>";
        data += "<title>"+ $(this).find(".task-title").text().trim() +"</title>";
        data += "<description>"+ $(this).find(".task-title").text().trim() +"</description>";
	
	let stat = "false";
	if ($(this).find(".task-checkbox").is(":checked")) {
	    stat = "true";
	}
	
        data += "<stat>"+ stat +"</stat>";
        data += "</task>";
    });
    
    data += "</tasks>";
    return data;
};

function saveTaskList() {
    /*
     * Lê os elementos .task da página, transforma numa string com formatação XML e envia para o 
     * servlet.
     */
    
    const tasks = tasksToXML($(".task"));
    
    /* Realiza chamada AJAX tipo POST para o servlet tasks, a data como parametro. 
     * Data vai formatada como XML, e é processada no servlet.
     * Dentro do servlet ele pega o usuário salvo na session para autenticar o envio.
     */
    
    console.log(tasks);
    
    if (tasks.length > 0) {
	$.ajax({
	    type: "POST",
	    url: "tasks",
	    scriptCharset: "utf-8",
	    contentType: "application/x-www-form-urlencoded; charset=UTF-8",

	    data: {
		"tasks": tasks
	    },
	    beforeSend: function(x) {
		console.log("sending...");
		$('#side_bar_save').html('...');
		$('#side_bar_save').attr("disabled", "disabled");
	    },
	    success: function(x) {
		console.log("success");
		alert("Tarefas salvas!");
		$('#side_bar_save').html('Salvar');
		$('#side_bar_save').html('Salvar');
		$('#side_bar_save').removeAttr('disabled');
	    },
	    error: function (x) {
		console.log("error saving data");
		alert("Erro salvando tarefas");
		$('#side_bar_save').html('Salvar');
		$('#side_bar_save').removeAttr('disabled');
	    }
	});
	console.log("task data sent");
    }
    
};
