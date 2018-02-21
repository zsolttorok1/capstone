/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var form = document.getElementById('formcenter2');
var addbuttons = document.getElementById('addbuttons');
var listbuttons = document.getElementById('listbuttons');
//document.getElementById("listbutton").addEventListener("click", displayDate(this));
var cancel = document.getElementById('cancel');
var btn = document.getElementById("addbutton");
var itembtn = document.getElementById("listbutton");



// When the user clicks the button, open the form 
btn.onclick = function() {
    form.style.display = "flex";
    addbuttons.style.display = "flex";
    btn.style.display = "none";
};
/**
itembtn.onclick = function() {
    listbuttons.style.display = "none";
    listbuttons.style.display = "flex";
};
*/
cancel.onclick = function() {
    form.style.display = "none";
    addbuttons.style.display = "none";
    btn.style.display = "flex";
};

var rowWrapper = document.getElementsByClassName("rowWrapper");
for(var i = 0; i < rowWrapper.length; i++){
    rowWrapper[i].addEventListener("click", displayOptions, false);
}

function displayOptions(e) {
    console.log(e.target);
    var newtarget = e.target;
    var allRows = document.getElementsByClassName("listOptions");
    var allRows2 = document.getElementsByClassName("row");
    //close all items
    for(var i = 0; i < allRows.length; i++){
        allRows2[i].style.backgroundColor = "white";
        allRows[i].style.display = "none";
    }
    //display current item
    newtarget.getElementsByClassName("listOptions")[0].style.display = "flex";
    newtarget.getElementsByClassName("row")[0].style.backgroundColor = "lightgray";
}