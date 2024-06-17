import api from "./api.config";

function RegisterUser(reg_data){

    return new Promise((resolve,reject)=>{
        api.post("/company_register",null,{params:reg_data}).then((response)=>{
            try {
                if(response.status === 200 && response.data.message === "User registered"){
                    resolve("Registered");
                }else{
                    resolve("Failed to register");
                }
            } catch (error) {
                reject(error);
            }
        })
    })
}


export default RegisterUser;