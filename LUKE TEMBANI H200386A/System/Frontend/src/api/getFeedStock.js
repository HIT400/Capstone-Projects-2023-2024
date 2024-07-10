import api from "./api.config";

function getStock(company_name,stock_type){
    return new Promise((resolve,reject)=>{
        api.post("/stock",{company_name,stock_type}).then((response)=>{
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


export default getStock;