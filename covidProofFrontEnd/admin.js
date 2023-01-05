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
let vaccines = [];
function vaccDetail(){
    // fetching the data
    const getVaccineList = async () => {
 
        let p = await fetch('http://localhost:8880/admin/allVaccines')
        let response = await p.json()
        // appending the data
        vaccines=response;

        document.getElementById("vaccDet").innerHTML=null;

        for(i=0; i<response.length; i++){
        let row = '<tr><td>' + Number(i+1) + '</td><td>' +
        response[i].vaccineName + '</td><td>' + response[i].price + '</td><td>' +
        response[i].description + '</td><td onclick="deleteVacc('+response[i].id+')">' + "Delete" + '</td></tr>';
        document.getElementById("vaccDet").innerHTML+=(row);
        }

        // shoving vaccine select oprions in add inventory
        document.getElementById("invenVaccine").innerHTML=null;
        document.getElementById("invenVaccine").innerHTML='<option value="">'+ "Select Vaccine" +'</option>'; 
        if(response.length>0){
            for(i=0; i<response.length; i++){
                document.getElementById("invenVaccine").innerHTML+=
                '<option value="'+ response[i].id +'">'+ response[i].vaccineName +'</option>'; 
            }
        }else{
            document.getElementById("invenVaccine").innerHTML='<tr>'+response.message+'</tr>';
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
           vaccDetail();
         }
        ).catch(err => console.log(err));
    }
    
    if(out){
        deleteVaccine();
    }
}

// appendig the center data
centerDetail();
function centerDetail(){
    
    const getCentersList = async () =>{
        let p = await fetch('http://localhost:8880/admin/allVacineCenter')
        let response = await p.json();

        document.getElementById("centDet").innerHTML=null;

        if(response.message==null){
            
            for(i=0; i<response.length; i++){
                let row = '<tr><td>' + Number(i+1) + '</td><td>' +
                response[i].centerName + '</td><td>' + response[i].city + '</td><td>' +
                response[i].state +'</td><td>' + response[i].pincode + 
                '</td><td onclick="showInventory('+response[i].id+')">'+
                "Show Inventory"+ '</td><td>' +
                response[i].totalDoses +
                '</td><td onclick="deleteCenter('+response[i].id+')">'+ "Delete" + '</td></tr>';
        
                document.getElementById("centDet").innerHTML+=(row);
            }

                // shoving vaccine select oprions in add inventory
                document.getElementById("invenCenter").innerHTML=null;
                document.getElementById("invenCenter").innerHTML='<option value="">'+ "Select Center" +'</option>'; 
                if(response.length>0){
                    for(i=0; i<response.length; i++){
                        document.getElementById("invenCenter").innerHTML+=
                        '<option value="'+ response[i].id +'">'+ response[i].centerName +'</option>'; 
                    }
                }
        }else{
            document.getElementById("centDet").innerHTML='<tr>'+response.message+'</tr>';
        }
    
    }

    getCentersList();
}

// showing center inventory
function showInventory(centerId){

    const getInvenotryCen = async () =>{
        let p = await fetch('http://localhost:8880/admin/getInvenotry/'+centerId);
        let response = await p.json();

        if(response.length>0){
            document.getElementById("inveBody").innerHTML=null;
            for(i=0; i<response.length; i++){
    
                let row = '<tr><td>' + Number(i+1) + '</td><td>' +
                response[i].vaccine.vaccineName + '</td><td>' +
                response[i].quantity + '</td><td onclick="deleteInventory('+response[i].id+')">' +
                "Delete" + '</td></tr>'; 
    
                document.getElementById("inveBody").innerHTML+=row;
            }
        }
    }

    getInvenotryCen();
    openCloseform('getInvenotry');
}

// Deleteing the inventory
function deleteInventory(id){
    const deleteInventoryFun = async () => {
        let urlf = "http://localhost:8880/admin/deleteInventory/"+id;
        console.log(urlf);
        fetch(urlf, {
        method: 'DELETE'
        })
        .then(res => 
         {
           if(res.ok)alert("Center Deleted With Id "+centerID);
           window.location.reload();
         }
        ).catch(err => console.log(err));
    }

    if(confirm("Are You Want To Delete This Inventory")){
        deleteInventoryFun();
    }
}

// delteing the cneter
function deleteCenter(centerID){
    const deleteCenterfun = async () => {
        let urlf = "http://localhost:8880/admin/deleteCenter/"+centerID;
        console.log(urlf);
        fetch(urlf, {
        method: 'DELETE'
        })
        .then(res => 
         {
           if(res.ok)alert("Center Deleted With Id "+centerID);
           centerDetail();
         }
        ).catch(err => console.log(err));
    }

    if(confirm("You Want To Delete Center")){
        deleteCenterfun();
    }
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
        if(response.message==null){
            for(i=0; i<response.length; i++){
                let row = '<tr><td>' + Number(i+1) + '</td><td>' +
                response[i].name + '</td><td>' + 
                response[i].mobileno + '</td><td>' +
                response[i].city + '</td><td onclick="showDoseStatus('+response[i].id+')">' +
                "Show Status"+'</td><td onclick="showAppointments('+response[i].id+')">' +
                'Show Appointments'+'</td><td onclick="deleteUser('+response[i].id+')">' + 
                "Delete" + '</td></tr>';
                document.getElementById("appliDet").innerHTML+=(row);
                }
        }else{
            document.getElementById("appliDet").innerHTML='<tr>'+response.message+'</tr>';
        }
    }
    getApplicantList();

}

// showing appointments
function showAppointments(id){

    openCloseform('appointment');
    
    const getAppointments = async() =>{
        let p = await fetch('http://localhost:8880/applicant/getAppointments/'+id);
        let response = await p.json();
        if(response.length>0){
            document.getElementById("appointDetails").innerHTML=null;
            for(i=0; i<response.length; i++){
                let row = '<tr><td>' + response[i].doseNo + '</td><td>' +
                response[i].center.centerName + '</td><td>' + 
                response[i].bookingDate.join("-") + '</td><td>' +
                response[i].slot + '</td><td>' +
                response[i].vaccine.vaccineName+
                '</td><td onclick="deleteAppointment('+response[i].id+')">' + 
                "Cancel" + '</td></tr>';

                document.getElementById("appointDetails").innerHTML+=row;
            }

        }
    }

    getAppointments();
}

// cancel appointment
function deleteAppointment(id){

    const cancelAppoint = async() =>{
        let urlf = "http://localhost:8880/applicant/deleteAppointment/"+id;
        console.log(urlf);
        fetch(urlf, {
        method: 'DELETE'
        })
        .then(res => 
         {
            if(res.ok){
                alert("Appointment Canceled"); 
                window.location.reload();
            }else{
                alert("Can't Delete the account, Try Later");
            }
         }).catch(err => console.log(err));
    }

    if(confirm("Do You Want To Cancel The Appointment")){
        cancelAppoint();
    }

 }


// fetching dose status
function showDoseStatus(id){
    openCloseform('doseStatus');
     // fetching dose status
     const  getDOSEstatus = async() => {

        let p = await fetch('http://localhost:8880/applicant/getDoseStatus/'+id);
        let response = await p.json();
    
        if(response.dose1){
            document.querySelector("#doseStatus>div>h4:nth-child(3)>span").innerText=" Taken ";
            document.querySelector("#doseStatus>div>h4:nth-child(3)").style.backgroundColor="green";
            document.querySelector("#doseStatus>div>h4:nth-child(4)>span").innerText=response.dose1Date.join("-");
            document.querySelector("#doseStatus>div>h4:nth-child(4)").style.backgroundColor="green";
        }

        if(response.dose2){
            document.querySelector("#doseStatus>div>h4:nth-child(5)>span").innerText=" Taken ";
            document.querySelector("#doseStatus>div>h4:nth-child(5)").style.backgroundColor="green";
            document.querySelector("#doseStatus>div>h4:nth-child(6)>span").innerText=response.dose2Date.join("-");
            document.querySelector("#doseStatus>div>h4:nth-child(6)").style.backgroundColor="green";
        }

        let but = document.getElementById("upDoSta");
        but.innerText="Update Dose";
        but.onclick = async() => {

            let out = confirm("You Want To Update Dose Status?");
            if(out){
                let options = {
                    method: "PATCH",
                    headers: {
                            "Content-type": "application/json"
                    }
                }
                let p = await fetch('http://localhost:8880/applicant/updateDose/'+id, options)
                let response = await p.json()
        
                alert(response.message);
            }
        }

    }

    getDOSEstatus();
}

// delete user

function deleteUser(id){
    let out = confirm("Deleting user id "+ id);

        const deleteApplicant = async () => {

            let urlf = "http://localhost:8880/applicant/delete/"+id;

            fetch(urlf, {
            method: 'DELETE'
            })
            .then(res => 
             {
                if(res.ok){
                    showApplicant();
                    alert("Deleting the account!");
                    showApplicant();
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


// aading the center
let addCentForm = document.querySelector("#addCent>form");
addCentForm.onsubmit = function(event){

    event.preventDefault();

    const addCenters = async () => {

        let options = {
            method: "POST",
            headers: {
                    "Content-type": "application/json"
            },
            body: JSON.stringify(
                {
                    "centerName": addCentForm.name.value,
                    "city": addCentForm.city.value,
                    "state": addCentForm.state.value,
                    "pincode": addCentForm.pin.value,
                }
            ),
            }
            let p = await fetch('http://localhost:8880/admin/addCenter', options)
            let response = await p.json();
            alert(response.message);
            centerDetail();
    }

    addCenters();
    
}

// Adding the inventory of the center
let addInveForm = document.querySelector("#addInven>form");
addInveForm.onsubmit = function(event){
    event.preventDefault();

    const addInventory = async() =>{
        let options = {
            method: "PUT",
            headers: {
                    "Content-type": "application/json"
            },
            body: JSON.stringify(
                {
                    "vaccine": {
                        "id" : addInveForm.vaccine.value
                    },
                    "quantity": addInveForm.quantity.value
                }
            ),
            }

            let p = await fetch('http://localhost:8880/admin/addCenterInventory/'+addInveForm.center.value, options)
            let response = await p.json();
            vaccDetail();
            alert(response.message);
    }

    addInventory();
}
