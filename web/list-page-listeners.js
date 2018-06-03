/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function() {
    document.getElementById("task-add-field").addEventListener("keydown", function(event) { createTask(event, this); }, false);
    
    document.querySelectorAll(".task-text").forEach(function (elem) {
        elem.addEventListener("click", function(){ crossTask(elem); }, false);
    });
    
    document.querySelectorAll(".task-remove").forEach(function (elem) {
        elem.addEventListener("click", function(){ removeTask(elem); }, false);
    });
};
