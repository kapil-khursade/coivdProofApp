// opening and closing the form
function openCloseform(x){
    document.getElementById(x).classList.toggle('formCnt');
    document.querySelector('body').classList.toggle('stopScroll');
}


// booking appointment
let bookappont = document.querySelector('#booking>form');

// fetching centers
const getCentersList = async () =>{
    let p = await fetch('http://localhost:8880/admin/allVacineCenter')
    let response = await p.json();

    if(response.message==null){
        for(i=0; i<response.length; i++){
            document.getElementById("centerBo").innerHTML+='<option value="'+response[i].id+'">'+ response[i].centerName+'</option>';
        }
    }
}

getCentersList();

// fetching inventory
function showVaccine(){
    const getInvenotryCen = async () =>{
        let p = await fetch('http://localhost:8880/admin/getInvenotry/'+bookappont.center.value);
        let response = await p.json();

        document.getElementById("vaccineBo").innerHTML=null;
        document.getElementById("vaccineBo").innerHTML='<option value="">Select Vaccine</option>';
        
        for(i=0; i<response.length; i++){
            document.getElementById("vaccineBo").innerHTML+='<option value="'+response[i].vaccine.id+'">'+ response[i].vaccine.vaccineName+' Availble: '+ response[i].quantity+'</option>';
        }
    }
    getInvenotryCen();
}


bookappont.onsubmit = function(event){
    event.preventDefault();
    let app = JSON.parse(localStorage.getItem("curLogin"));

    const addAppointment = async () => {
        let Obj = {
            'idcard':{
                'id':app.id
            },
            'doseNo':bookappont.deoseNo.value,
            'vaccine':{
                'id': bookappont.vaccine.value
            },
            'center':{
                'id':bookappont.center.value
            },
            'bookingDate':bookappont.bookdate.value,
            'slot':bookappont.slot.valueOf
          }

          console.log(Obj);

            let options = {
                method: "POST",
                headers: {
                        "Content-type": "application/json"
                },
                body: JSON.stringify(Obj),
            }
            let p = await fetch('http://localhost:8880/applicant/addAppointment', options)
            let response = await p.json()
            alert("Appointment Added");
            window.location.reload();
    }

    addAppointment();

};



let Obj = null;
// loding information;
window.onload = (event) => {
   Obj = JSON.parse(localStorage.getItem("curLogin"));

    if(Obj==null){
        window.location.href="index.html";
    }

    document.querySelector("#profInfo>ul>li:nth-child(2)>span").innerText = Obj.id;
    document.querySelector("#profInfo>ul>li:nth-child(3)>span").innerText = Obj.name;
    document.querySelector("#profInfo>ul>li:nth-child(4)>span").innerText = Obj.mobileno;
    document.querySelector("#profInfo>ul>li:nth-child(5)>span").innerText = new Date(Obj.dob).toLocaleDateString();
    document.querySelector("#profInfo>ul>li:nth-child(6)>span").innerText = Obj.gender;
    document.querySelector("#profInfo>ul>li:nth-child(7)>span").innerText = Obj.address;
    document.querySelector("#profInfo>ul>li:nth-child(8)>span").innerText = Obj.city;
    document.querySelector("#profInfo>ul>li:nth-child(9)>span").innerText = Obj.pincode;
    console.log(Obj);

    // fetching dose status
    const  getDOSEstatus = async() => {

        let p = await fetch('http://localhost:8880/applicant/getDoseStatus/'+Obj.id);
        let response = await p.json();
        
        if(response.dose1==true){
            document.querySelector("#vaccineStatus>div:first-child").style.backgroundColor="green";
            document.querySelector("#vaccineStatus>div:first-child").innerText+="\nTaken On "+'\n'+response.dose1Date.join("-");
            document.getElementById("doseNoBo").innerHTML=null;
            document.getElementById("doseNoBo").innerHTML='<option value="">Select DoseNo</option>'+
            '<option value="2">Dose 2</option>'
        }
        
        if(response.dose2==true){
            document.querySelector("#booking>form>input:nth-child(8)").value="You Are Fully Vaccinated";
            document.querySelector("#booking>form>input:nth-child(8)").style.backgroundColor ="green";
            document.querySelector("#booking>form>input:nth-child(8)").style.width="auto";
            document.querySelector("#booking>form>input:nth-child(8)").style.cursor="not-allowed"
            document.querySelector("#booking>form>input:nth-child(8)").disabled = true;
            document.querySelector("#vaccineStatus>div:nth-child(2)").style.backgroundColor="green";
            document.querySelector("#vaccineStatus>div:nth-child(2)").innerText+="\nTaken On "+'\n'+response.dose2Date.join("-");
        }
        
    }

    getDOSEstatus();


    // Appointments
    const getAppointments = async() =>{
        let p = await fetch('http://localhost:8880/applicant/getAppointments/'+Obj.id);
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
};

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

// delete user

function deleteUser(){
    let out = confirm("Deleting user id "+ Obj.id);

        const deleteApplicant = async () => {

            let urlf = "http://localhost:8880/applicant/delete/"+Obj.id;
            console.log(urlf);
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

// Loging out applicant
function logOutApp(){

    let out = confirm("Are You Sure Want To Log Out");

    if(out){
        localStorage.removeItem("curLogin");   
        window.location.href="index.html";
    }
}
