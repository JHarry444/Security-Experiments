"use strict";

document.getElementById("testButton").addEventListener("click", function () {
    fetch("http://localhost:8080/test", {
        credentials: "include"
    })
        .then(response => {
            debugger;
            if (response.status == 401) {
                window.location = "./html/login.html";
            }
            return response.text();
        })
        .then(text => {
            alert(text)
        }).catch(error => {
            debugger;
        });
})