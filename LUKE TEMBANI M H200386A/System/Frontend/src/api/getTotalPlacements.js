import api from "./api.config";

function getTotalPlacements(company_name){
    return new Promise((resolve,reject)=>{
        api.post("/placements_count",{company_name}).then((response)=>{
            if(response.status === 200 && response.data){
                resolve(response.data.total);
            }else{
                resolve([]);
            }
        })
        .catch((error)=>{
            console.log(error);
            reject(error);
        })
    })
}


export default getTotalPlacements;