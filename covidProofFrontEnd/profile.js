// ACCESSING THE OPTIONS OF THE CENTERS

// Selecting centre

function showCity(x){

    let n = document.getElementById(x).value;

    let citesOption = document.getElementById("centerCity");
    citesOption.innerHTML=null;

    if(n==0){
        citesOption.innerHTML = '<option value="0">Karol Bagh</option>'+
                                '<option value="1">Sarojini</option>'+
                                '<option value="2">Janpath</option>';
    }else if(n==1){
        citesOption.innerHTML = '<option value="3">Banglru</option>'+
                                '<option value="4">Belgavi</option>'+
                                '<option value="5">Hampi</option>';
    }
    else if(n==2){
        citesOption.innerHTML = '<option value="6">Mumbai</option>'+
                                '<option value="7">Nagpur</option>'+
                                '<option value="8">Pune</option>';
    }
    else if(n==3){
        citesOption.innerHTML = '<option value="9">Chainai</option>'+
                                '<option value="10">Vellor</option>'+
                                '<option value="11">Mahabalipuram</option>';
    }else if(n==4){
        citesOption.innerHTML = '<option value="12">Kolkatta</option>'+
                                '<option value="13">Hubli</option>'+
                                '<option value="14">Darjling</option>';
    }
}

// showing centers
function showCenter(x){

    let n = document.getElementById(x).value;

    let centerName = document.getElementById("centerName");
    centerName.innerHTML=null;

    let centers = [
        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital'],
        ['PNB_Hospital', 'Sham_Vidya_Mandir', 'Veer_Clinic'],
        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital'],

        ['Ced_Hostital', 'Munciple_School', 'RT_Hospital'],
        ['Kp_Hospital', 'DD_Schhol', 'Namo_Hospital'],
        ['Shiv_Hostital', 'Kalam_Vidya_Mandir', 'Dev_Clinic'],

        ['Seva_hopital', 'ST_Hospital', 'City_Hospital'],
        ['Kp_Hostital', 'Sham_Vidya_Mandir', 'Veer Clinic'],
        ['City_Hospital', 'Government_Schhol', 'MP_Hospital'],

        ['Ps_Hospital', 'Rao_Vidya_Mandir', 'Tilak_Clinic'],
        ['Shivaji_clinic', 'Pm_Schhol', 'JP_Hospital'],
        ['Pratap_Hostital', 'Gandhi_Vidya_Mandir', 'City_Hospital'],

        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital'],
        ['PNB_Hostital', 'Sham_Vidya_Mandir', 'Veer Clinic'],
        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital']
    ];


    centerName.innerHTML = '<option value="14">'+ centers[n][0]+ '</option>'+
                              '<option value="14">'+ centers[n][1]+ '</option>'+
                              '<option value="14">'+ centers[n][2]+ '</option>';

}

// Seacrching slot

function showCitySer(x){

    let n = document.getElementById(x).value;

    let citesOption = document.getElementById("centerCitySer");
    citesOption.innerHTML=null;

    if(n==0){
        citesOption.innerHTML = '<option value="0">Karol Bagh</option>'+
                                '<option value="1">Sarojini</option>'+
                                '<option value="2">Janpath</option>';
    }else if(n==1){
        citesOption.innerHTML = '<option value="3">Banglru</option>'+
                                '<option value="4">Belgavi</option>'+
                                '<option value="5">Hampi</option>';
    }
    else if(n==2){
        citesOption.innerHTML = '<option value="6">Mumbai</option>'+
                                '<option value="7">Nagpur</option>'+
                                '<option value="8">Pune</option>';
    }
    else if(n==3){
        citesOption.innerHTML = '<option value="9">Chainai</option>'+
                                '<option value="10">Vellor</option>'+
                                '<option value="11">Mahabalipuram</option>';
    }else if(n==4){
        citesOption.innerHTML = '<option value="12">Kolkatta</option>'+
                                '<option value="13">Hubli</option>'+
                                '<option value="14">Darjling</option>';
    }
}

// showing centers
function showCenterSer(x){

    let n = document.getElementById(x).value;

    let centerName = document.getElementById("centerNameSer");
    centerName.innerHTML=null;

    let centers = [
        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital'],
        ['PNB_Hospital', 'Sham_Vidya_Mandir', 'Veer_Clinic'],
        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital'],

        ['Ced_Hostital', 'Munciple_School', 'RT_Hospital'],
        ['Kp_Hospital', 'DD_Schhol', 'Namo_Hospital'],
        ['Shiv_Hostital', 'Kalam_Vidya_Mandir', 'Dev_Clinic'],

        ['Seva_hopital', 'ST_Hospital', 'City_Hospital'],
        ['Kp_Hostital', 'Sham_Vidya_Mandir', 'Veer Clinic'],
        ['City_Hospital', 'Government_Schhol', 'MP_Hospital'],

        ['Ps_Hospital', 'Rao_Vidya_Mandir', 'Tilak_Clinic'],
        ['Shivaji_clinic', 'Pm_Schhol', 'JP_Hospital'],
        ['Pratap_Hostital', 'Gandhi_Vidya_Mandir', 'City_Hospital'],

        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital'],
        ['PNB_Hostital', 'Sham_Vidya_Mandir', 'Veer Clinic'],
        ['DPS_Kb', 'ORN_Schhol', 'JRL_Hospital']
    ];


    centerName.innerHTML = '<option value="14">'+ centers[n][0]+ '</option>'+
                              '<option value="14">'+ centers[n][1]+ '</option>'+
                              '<option value="14">'+ centers[n][2]+ '</option>';

}


// opening and closing the form
function openCloseform(x){
    document.getElementById(x).classList.toggle('formCnt');
    document.querySelector('body').classList.toggle('stopScroll');
}

// bppking appointmenr
let bookappont = document.querySelector('#booking>form');

bookappont.addEventListener(onsubmit, function(){
let Obj = {
    'dose':bookappont.dose.value,
    'vaccine':bookappont.vaccine.value,
    'centerID':bookappont.vaccine.center,
    'dateOfBooking':bookappont.dateOfBooking.value,
    'slot':bookappont.slot.value
}

});


// serching slot
let slotSearch = document.querySelector('#serachSlot>form');

slotSearch.addEventListener(onsubmit, function(event){
    event.preventDefault();
    openCloseform('slotDisplay');
}); 


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
};

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
