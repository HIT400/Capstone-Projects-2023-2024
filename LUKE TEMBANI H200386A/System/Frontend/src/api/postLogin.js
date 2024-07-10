import api from "./api.config";

function loginUser(username, password){

    return new Promise((resolve,reject)=>{
        api.post("/company_login",null,{params:{username,password}}).then((response)=>{
            try {
                if(response.status === 200 && response.data.message === "User Authenticated"){
                    localStorage.setItem("COMPANY_NAME",response.data.results[0].companyname);
                    resolve("Authenticated");
                }else{
                    resolve("Failed to login");
                }
            } catch (error) {
                reject(error);
            }
        })
    })
}


export default loginUser;