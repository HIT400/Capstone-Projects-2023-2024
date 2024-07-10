import api from "./api.config";

function getOrdersList(company_name){
    return new Promise((resolve,reject)=>{
        api.post("/company_orders",{company_name}).then((response)=>{
            if(response.status === 200 && response.data){
                resolve(response.data);
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


export default getOrdersList;