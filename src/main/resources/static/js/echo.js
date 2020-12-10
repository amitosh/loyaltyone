
function makeEchoCall() {
    var xhttp;
    xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("echoText").setAttribute("value", xhttp.responseText);
        }
    };

    var txt = document.getElementById("inputText").value;
    var url = "/echotext?text=" + txt;
    xhttp.open("GET", url, true);
    xhttp.send();
}

function makeCall() {
    var xhttp;
    xhttp=new XMLHttpRequest();
    var userName = document.getElementById("userName").value;
    var txt = document.getElementById("inputText").value;
    var params = "userName=" + userName + "&text=" + txt;
    var url = "/save";
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send(params);

    getUserPosts();
    getCityDetails();
    getAllPosts();
}

function getUserPosts()
{
    var xhttp;
    xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("userPosts").innerHTML  = xhttp.responseText;
        }
    };

    var userName = document.getElementById("userName").value;
    var url = "/userposts?userName=" + userName;
    xhttp.open("GET", url, true);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send();
 }

function getCityDetails()
{
    var xhttp;
    xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("location").innerHTML  = xhttp.responseText;
        }
    };

    var city = document.getElementById("city").value;
    var url = "/latlong?city=" + city;
    xhttp.open("GET", url, true);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send();
 }

function getAllPosts()
{
    var xhttp;
    xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("allPosts").innerHTML  = xhttp.responseText;
        }
    };

    var url = "/posts";
    xhttp.open("GET", url, true);
    xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhttp.send();
 }