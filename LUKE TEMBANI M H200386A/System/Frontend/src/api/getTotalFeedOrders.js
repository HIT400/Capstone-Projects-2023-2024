import api from "./api.config";

function getTotalFeedOrders(company_name){
    return new Promise((resolve,reject)=>{
        api.post("/feed_orders_count",{company_name}).then((response)=>{
            if(response.status === 200 && response.data){
                console.log(response.data.total);
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


export default getTotalFeedOrders;