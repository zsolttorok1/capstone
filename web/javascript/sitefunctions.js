
//Add Form Modal
var modal = document.getElementById('myModal');
var btn = document.getElementById("myBtn");

//Form Add Phone
var btnPhone = document.getElementById("btnPhone");

//Object Buttons
var listbuttons = document.getElementById('listbuttons');
var cancel = document.getElementById('cancel');
var itembtn = document.getElementById("listbutton");

//TO DO Edit Button in View Pages
var editbtn = document.getElementById("editIcon");
var editInputs = document.getElementsByClassName("contentBodyInput");


//dont remove these comments please <------- PLEASE

//Add Modal Form
// When the user clicks the button, open the modal 
if (btn !== null) {
    btn.onclick = function() {
    modal.style.display = "block";
};
}


//Add Phone Number Text Fields when clicked
function addNumberInput() {
    var parent = document.getElementById("phoneField");
    console.log(parent);
    
        var input = document.createElement("input");
                    input.type = "text";
                    input.name = "phoneNumberList[]";
                    //parent.appendChild(input, parent.lastChild);
                    document.getElementById("btnPhone").appendChild(input); 
};

//close on anywhere click
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

//Object highlight
if (cancel !== null) {
    cancel.onclick = function() {
        itembtn.style.display = "none";
        btn.style.display = "flex";
    };
}
var rowWrapper = document.getElementsByClassName("rowWrapper");
for(var i = 0; i < rowWrapper.length; i++){
    rowWrapper[i].addEventListener("click", displayOptions, false);
}

function displayOptions(e) {
    console.log(e.target);
    var newtarget = e.target;
    var allRows = document.getElementsByClassName("listOptions");
    if (allRows.length === 0) {
        allRows = document.getElementsByClassName("listOptionsSearch");
    }
    var allRows2 = document.getElementsByClassName("row");
    if (allRows2.length === 0) {
        allRows2 = document.getElementsByClassName("rowSearch");
        
        for(var i = 0; i < allRows.length; i++){
        allRows2[i].style.backgroundColor = "white";
        allRows[i].style.display = "none";
        }
        //display current item
        newtarget.getElementsByClassName("listOptionsSearch")[0].style.display = "flex";
        newtarget.getElementsByClassName("rowSearch")[0].style.backgroundColor = "#e3e8ed";
        return;
    }
    //close all items
    for(var i = 0; i < allRows.length; i++){
        allRows2[i].style.backgroundColor = "white";
        allRows[i].style.display = "none";
    }
    //display current item
    newtarget.getElementsByClassName("listOptions")[0].style.display = "flex";
    newtarget.getElementsByClassName("row")[0].style.backgroundColor = "#e3e8ed";
}

// NavBar
function openNav() {
    document.getElementById("mySidenav").style.display = "block";
}

function closeNav() {
    document.getElementById("mySidenav").style.display = "none";
}

function closeMessage() {
    window.setTimeout(function() {
        document.getElementById("message").style.visibility = "hidden"; 
    }, 500);
   
    document.body.classList.remove("trigger_message");
}

function closeForm(e) {
    var tableElement = e.parentNode.parentNode;
    tableElement.classList.add("slide");
}
