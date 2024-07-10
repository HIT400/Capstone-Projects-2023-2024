import api from "./api.config";
function updateStatus(updateData){
    return new Promise((resolve,reject)=>{
        api.post("/update/contract/farming/request",updateData).then((response)=>{
            if(response.status === 200 && response.data.message === "success"){
                resolve("success");
            }else{
                resolve('failed');
            }
        })
        .catch((error)=>{
            console.log(error);
            resolve("failed");
        })
    })
}


export default updateStatus;