
//Add Form Modal
var modal = document.getElementById('myModal');
var btn = document.getElementById("myBtn");

//Form Add Phone
var btnPhone = document.getElementById("btnPhone");

//dont remove these comments please <------- PLEASE

//Add Modal Form
// When the user clicks the button, open the modal 
if (btn !== null) {
    btn.onclick = function() {
    document.body.classList.add("modal_on");
    };
}

//close on anywhere click
window.onclick = function(event) {
    if (event.target.classList.contains("modal")) {
        modalFormSubmitted();
    }
};

function modalFormSubmitted() {
    document.body.classList.remove("modal_on");
}

//Add Phone Number Text Fields when clicked
function addNumberInput(e) {
    var targetedElement = e.parentNode;
    
    var inputTag = document.createElement("input");
    inputTag.setAttribute("type", "text" );
    inputTag.setAttribute("name", "phoneNumberList[]");
    targetedElement.appendChild(inputTag);
    
    var inputTag = document.createElement("div");
    inputTag.setAttribute("class", "phoneMinus" );
    inputTag.setAttribute("onClick", "javasctipt:deleteNumberInput(this)");
    targetedElement.appendChild(inputTag);
    
    var brTag = document.createElement("br");
    targetedElement.appendChild(brTag);
};

//Delete Phone Number Text Fields when clicked
function deleteNumberInput(e) {
    var targetedElement = e;
    
    console.log(targetedElement);
    
    var textField = targetedElement.previousElementSibling;
    var brTag = targetedElement.nextElementSibling;
    textField.remove();
    brTag.remove();
    targetedElement.remove();
    
};

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
