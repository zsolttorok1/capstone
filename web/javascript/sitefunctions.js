/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// Get the form
var form = document.getElementById('formcenter');

// Get the button that opens the form
var btn = document.getElementById("addbutton");

// When the user clicks the button, open the form 
btn.onclick = function() {
    form.style.display = "block";
};

// When the user clicks anywhere outside of the form, close it
window.onclick = function(event) {
    if (event.target === form) {
        form.style.display = "none";
    }
};