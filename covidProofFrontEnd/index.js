// opening and closing the form
function openCloseform(x){
    document.getElementById(x).classList.toggle('formCnt');
    document.querySelector('body').classList.toggle('stopScroll');
}

// applicant registration
let regiForm = document.querySelector('#registration>form');
regiForm.onsubmit = function(event){
    event.preventDefault()
    const registerApplicant = async () => {
        let options = {
                method: "POST",
                headers: {
                        "Content-type": "application/json"
                },
                body: JSON.stringify({
                    "name": regiForm.name.value,
                    "gender": regiForm.gender.value,
                    "dob":regiForm.Dob.value,
                    "address": regiForm.address.value,
                    "city": regiForm.city.value,
                    "pincode": regiForm.pin.value,
                    "mobileno":regiForm.mobile.value,
                    "panNo":regiForm.pan.value,
                    "adharNo":regiForm.adhar.value
                }),
        }

        let urlf = "http://localhost:8880/applicant/register";
        let p = await fetch(urlf, options)
        let response = await p.json()
        if(response.mobileno!=null){
            alert(response.name+" Registered");
        }else{
            alert(response.message);
        }
        window.location.reload();
    }
    registerApplicant();
}

// application login
let appliLogForm = document.querySelector("#login>form");
appliLogForm.onsubmit = function(event){
    event.preventDefault();

    const logInApplicant = async () => {
        let options = {
                method: "POST",
                headers: {
                        "Content-type": "application/json"
                },
                body: JSON.stringify({
                    "mobileno": appliLogForm.mobile.value,
                    "dob": appliLogForm.dob.value
                }),
        }
        let p = await fetch("http://localhost:8880/applicant/login", options)
        let response = await p.json()
        if(response.message!=undefined){
            alert(response.message);
            window.location.reload();
        }else{
            localStorage.setItem("curLogin", JSON.stringify(response));
            alert("Login Successfull");
            window.location.href="profile.html";
        }
        
    }
    logInApplicant();

}



// Admin login
let adminForm = document.querySelector('#admin>form');

adminForm.onsubmit = function(event){
    event.preventDefault();
    let Obj = {
        'mobileno':adminForm.mobile.value,
        'password':adminForm.password.value
    }

    const logInAdmin = async () => {
        let options = {
                method: "POST",
                headers: {
                        "Content-type": "application/json"
                },
                body: JSON.stringify(Obj),
        }
        let p = await fetch('http://localhost:8880/admin/login', options)
        let response = await p.json()
        if(response.message!=undefined){
            alert(response.message)
        }else{
            localStorage.setItem("curLogin", JSON.stringify(response));
            alert("Login Successfull");
            window.location.href="admin.html";
        }
    }
    logInAdmin();
}

// Admin Registration
let adminRegi = document.querySelector('#adminRegi>form');

adminRegi.onsubmit = function(event){
    event.preventDefault();
    let Obj = {
        'name':adminRegi.name.value,
        'mobileno':adminRegi.mobile.value,
        'password':adminRegi.password.value
    }

    const registerAdmin = async () => {
        let options = {
                method: "POST",
                headers: {
                        "Content-type": "application/json"
                },
                body: JSON.stringify(Obj),
        }
        let p = await fetch('http://localhost:8880/admin/register', options)
        let response = await p.json()
        if(response.mobileno!=null){
            alert(response.name+" Registered");
        }else{
            alert(response.message);
        }
        window.location.reload();
    }
    registerAdmin();
}



