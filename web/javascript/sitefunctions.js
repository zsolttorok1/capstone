/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var form = document.getElementById('formcenter2');
var buttons = document.getElementById('addbuttons');
var cancel = document.getElementById('cancel');
var btn = document.getElementById("addbutton");

// When the user clicks the button, open the form 
btn.onclick = function() {
    form.style.display = "flex";
    buttons.style.display = "flex";
    btn.style.display = "none";
};

cancel.onclick = function() {
    form.style.display = "none";
    buttons.style.display = "none";
    btn.style.display = "flex";
}

// When the user clicks anywhere outside of the form, close it
window.onclick = function(event) {
    if (event.target === form) {
        form.style.display = "none";
    }
};