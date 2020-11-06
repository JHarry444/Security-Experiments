"use strict";

document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault();

    fetch("http://localhost:8080/register", {
        method: "POST",
        body: JSON.stringify({
            username: this.username.value,
            password: this.password.value
        }),
        mode: "cors",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(response => {
        if (response.status === 201) {
            return response.text();
        } else {
            return;
        }
    }).then(text => {
        if (text) {
              alert(`New user: ${text}`);
              window.location = "./login.html";
        }
    }).catch(err => console.error(err));
});