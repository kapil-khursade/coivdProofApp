// opening and closing the form
function openCloseform(x){
    document.getElementById(x).classList.toggle('formCnt');
    document.querySelector('body').classList.toggle('stopScroll');
}

// loading info
// loding information;
let Obj = null;
window.onload = (event) => {
    Obj = JSON.parse(localStorage.getItem("curLogin"));
 
     if(Obj==null){
         window.location.href="index.html";
     }
 
     document.querySelector("#adminInfo>ul>li:nth-child(2)>span").innerText = Obj.id;
     document.querySelector("#adminInfo>ul>li:nth-child(3)>span").innerText = Obj.name;
     document.querySelector("#adminInfo>ul>li:nth-child(4)>span").innerText = Obj.mobileno;
 };


// Appending vaccine detail table
vaccDetail();
function vaccDetail(){
    // fetching the data
    const getVaccineList = async () => {
 
        let p = await fetch('http://localhost:8880/admin/allVaccines')
        let response = await p.json()
        // appending the data

        document.getElementById("vaccDet").innerHTML=null;

        for(i=0; i<response.length; i++){
        let row = '<tr><td>' + Number(i+1) + '</td><td>' +
        response[i].vaccineName + '</td><td>' + response[i].price + '</td><td>' +
        response[i].description + '</td><td onclick="deleteVacc('+response[i].id+')">' + "Delete" + '</td></tr>';
        document.getElementById("vaccDet").innerHTML+=(row);
        }
    }
    getVaccineList();

}

// deleting the vaccine
function deleteVacc(id){
   
    let out = confirm("You Want To Delete Vaccine");

    const deleteVaccine = async () => {
        let urlf = "http://localhost:8880/admin/deleteVaccine/"+id;
        console.log(urlf);
        fetch(urlf, {
        method: 'DELETE'
        })
        .then(res => 
         {
           alert(res.message);
           console.log(res)
         }
        ).catch(err => console.log(err));
    }
    
    if(out){
        deleteVaccine();
        vaccDetail();
    }
}

// appendig the center data
centerDetail();
function centerDetail(){
    
    const getCentersList = async () =>{
        let p = await fetch('http://localhost:8880/admin/allVacineCenter')
        let response = await p.json();

        document.getElementById("centDet").innerHTML=null;
        for(i=0; i<response.length; i++){

        let row = '<tr><td>' + Number(i+1) + '</td><td>' +
        response[i].centerName + '</td><td>' + response[i].city + '</td><td>' +
        response[i].state +'</td><td>' + response[i].pincode + 
        '</td><td onclick="showInventory('+response[i].center_ID+')">' + 
        "Show Inventory" + '</td><td onclick="deleteCenter('+response[i].center_ID+')">'+ "Delete" + '</td></tr>';

        document.getElementById("centDet").innerHTML+=(row);
        }
    }

    getCentersList();
}

// showing center inventory
function showInventory(centerId){
    openCloseform('inventoryDisplay');
}

// delteing the cneter
function deleteCenter(centerID){
    alert(centerID+ " deleted");
}


// adding the vaccine
let addVaccine = document.querySelector("#addVacc>form");

addVaccine.onsubmit =  function(event){
    event.preventDefault();

    let Obj = {
        'vaccineName':addVaccine.name.value,
        'price':addVaccine.price.value,
        'description': addVaccine.decr.value 
    }

    const addVaccineform = async () => {

        let options = {
            method: "POST",
            headers: {
                    "Content-type": "application/json"
            },
            body: JSON.stringify(Obj),
        }
        let p = await fetch('http://localhost:8880/admin/addVaccine', options)
        let response = await p.json()

        alert(response.message);
        vaccDetail();
    }

    addVaccineform();
};

// Updating addmin password
let upAdPasForm = document.querySelector("#upAdPass>form");
upAdPasForm.onsubmit = function(event){
    event.preventDefault();
    
    const updatePasswordAdmin = async () => {
        let options = {
            method: "PUT",
            headers: {
                    "Content-type": "application/json"
            },
            body: JSON.stringify(
                {
                    "mobileno": Obj.mobileno,
                    "password": upAdPasForm.oldPass.value,
                    "newPassword": upAdPasForm.newPass.value
                }
            ),
    }
    let p = await fetch('http://localhost:8880/admin/update', options)
    let response = await p.json()
   
    alert(response.message);
    window.location.reload();
    }

    updatePasswordAdmin();
};

// logout admin
function logOutApp(){

    let out = confirm("Are You Sure Want To Log Out");

    if(out){
        localStorage.removeItem("curLogin");   
        window.location.href="index.html";
    }
}

// deleting the admin
function deleteUser(){
    let out = confirm("Deleting user id "+ Obj.id);

        const deleteApplicant = async () => {

            let urlf = "http://localhost:8880/admin/delete/"+Obj.id;

            fetch(urlf, {
            method: 'DELETE'
            })
            .then(res => 
             {
                if(res.ok){
                    alert("Deleting the account!");
                localStorage.removeItem("curLogin");   
                window.location.href="index.html";
                }else{
                    alert("Can't Delete the account, Try Later");
                }
             }
                ).catch(err => console.log(err));
        }

        if(out){
            deleteApplicant();
          }
}


//Applicant showing
showApplicant();
function showApplicant(){

    const getApplicantList = async () => {
 
        let p = await fetch('http://localhost:8880/admin/allApplicant')
        let response = await p.json()
        // appending the data
        document.getElementById("appliDet").innerHTML=null;
        for(i=0; i<response.length; i++){
        let row = '<tr><td>' + Number(i+1) + '</td><td>' +
        response[i].name + '</td><td>' + 
        response[i].mobileno + '</td><td>' +
        response[i].city + '</td><td onclick="deleteUser('+response[i].id+')">' + 
        "Delete" + '</td></tr>';
        document.getElementById("appliDet").innerHTML+=(row);
        }
    }
    getApplicantList();
}

// delete user

function deleteUser(id){
    let out = confirm("Deleting user id "+ id);

        const deleteApplicant = async () => {

            let urlf = "http://localhost:8880/applicant/delete/"+id;
            console.log(urlf);
            fetch(urlf, {
            method: 'DELETE'
            })
            .then(res => 
             {
                if(res.ok){
                    showApplicant();
                    alert("Deleting the account!");
                }else{
                    alert("Can't Delete the account, Try Later");
                }
             }
                ).catch(err => console.log(err));
        }

        if(out){
            deleteApplicant();
          }
}