function validateForm(e) {

    const firstNameValue = document.getElementById("firstName").value.trim();
    const lastNameValue = document.getElementById("lastName").value.trim();
    const mobileNumberValue = document.getElementById("phoneNumber").value.trim();
    const emailValue = document.getElementById("email").value.trim();
    const genderValue = document.getElementById("gender").value.trim();
    const msg1 = document.getElementById("msg1");

    if (firstNameValue === "") {
        msg1.innerHTML = "First Name must be filled out"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false;
    }

    if (/\d/.test(firstNameValue)) {
        msg1.innerHTML = "First Name must be character"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false
    }
    if (lastNameValue === "") {
        msg1.innerHTML = "Last Name must be filled out"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false;
    }

    if (/\d/.test(lastNameValue)) {
        msg1.innerHTML = " Last Name must be character"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false
    }

    if (mobileNumberValue === "") {
        msg1.innerHTML = "Mobile Number must be filled out"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false;
    }

    if (isNaN(mobileNumberValue)) {
        msg1.innerHTML = "Mobile Number must be Digits"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false
    }

    if (mobileNumberValue.length < 10) {
        msg1.innerHTML = "Mobile Number must have 10 digits"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false
    }

    if (mobileNumberValue.length > 10) {
        msg1.innerHTML = "Mobile Number must have only 10 digits"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false
    }

    if (genderValue === "") {
        msg1.innerHTML = "Gender must be filled out"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false;
    }
    if (emailValue === "") {
        msg1.innerHTML = "Email must be filled out"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false;
    }

    var emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!emailRegex.test(emailValue)) {
        msg1.innerHTML = "Email must be a valid email address"
        setTimeout(function () {
            msg1.innerHTML = ""
        }, 5000)
        e.preventDefault()
        return false;
    }


    return true;
}

const form = document.getElementById("form")


form.addEventListener('submit', e => {
    // e.preventDefault()
    validateForm(e)
    // if (validateForm(e)) {
    //     // fetch(scriptURL, { method: 'POST', body: new FormData(form) })
    //
    // }
})
