"use strict";

document.getElementById("testButton").addEventListener("click", function () {
    fetch("http://localhost:8080/user", {
        credentials: "include"
    })
        .then(response => {
            if (response.status == 401) {
                window.location = "./html/login.html";
                return;
            }
            return response.text();
        })
        .then(text => {
            alert(text || 'ERROR')
        }).catch(error => console.error(error));
})