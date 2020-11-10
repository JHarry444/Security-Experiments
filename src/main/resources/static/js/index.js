"use strict";

document.getElementById("testButton").addEventListener("click", function () {
    fetch("http://localhost:8080/user", {
        credentials: "include",
        headers: {
            'Authorization': sessionStorage.getItem('jwt')
        }
    })
        .then(response => {
            debugger;
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