"use strict";

document.getElementById("loginForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const urlencoded = new URLSearchParams();
    urlencoded.append("username", this.username.value);
    urlencoded.append("password", this.password.value);

    fetch("http://localhost:8080/login", {
        method: "POST",
        body: urlencoded,
        credentials: "include",
        mode: "cors",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        }
    }).then(response => {
        if(response.status === 200) {
            window.location = "../index.html";    
        }
    }).catch(error => {
        debugger;
    });
});