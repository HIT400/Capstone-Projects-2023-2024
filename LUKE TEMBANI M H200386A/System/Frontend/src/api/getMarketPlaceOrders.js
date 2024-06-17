import api from "./api.config";

function getOrdersMarketPlace(user_data){

    return new Promise((resolve,reject)=>{
        api.post("/market_place_orders",{user_data}).then((response)=>{
            console.log(response);
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


export default getOrdersMarketPlace;
