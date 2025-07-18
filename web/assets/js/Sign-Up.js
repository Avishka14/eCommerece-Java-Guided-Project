async function SignUp() {

    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;


    const user = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
    };

    const userJSON = JSON.stringify(user);

    const response = await fetch(
            "SignUp",
            {
                method: "POST",
                body: userJSON,
                header: {
                    "Content-Type": "application/json"
                }
            });

    if (response.ok) {
        const json = await response.json();

        if (json.status) {
            window.location = "verify-account.html";
        } else { //when status false
            document.getElementById("message").innerHTML = json.message;
        }

    } else {
        document.getElementById("message").innerHTML = "Registration failed. Please try again";
    }

}