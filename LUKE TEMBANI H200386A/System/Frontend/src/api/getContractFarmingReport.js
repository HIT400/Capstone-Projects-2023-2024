import api from "./api.config";


function getReportData(supplier,full_name){
    return new Promise((resolve,reject)=>{
        api.post("/contract/farming/report/data",{supplier,full_name}).then((response)=>{
            if(response.status === 200 && response.data.message === "success"){
                resolve(response.data.report);
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

export default getReportData;