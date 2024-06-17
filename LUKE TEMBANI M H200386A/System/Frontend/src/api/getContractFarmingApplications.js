import api from "./api.config";

function getApplication(supplier_name){
    return new Promise((resolve,reject)=>{
        api.post("/contract/farming/applications",supplier_name).then((response)=>{
            console.log(response.data);
            if(response.status === 200 && response.data.message === "success"){
                resolve(response.data.applications);
            }else{
                resolve("failed");
            }
        })
        .catch((error)=>{
            console.log(error);
            if(error.response.status === 404){
                resolve("failed");
            }else{
                resolve("failed");
            }
        })
    })
}


export default getApplication;