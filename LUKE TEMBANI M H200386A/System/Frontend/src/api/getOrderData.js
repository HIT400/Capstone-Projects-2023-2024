import api from "./api.config";

function getOrderDetails(order_id){


    return new Promise((resolve,reject)=>{
        api.post("/order_data",{order_id}).then((response)=>{

            if(response.status === 200 && (response.data).length !== 0){
                resolve(response.data)
                
            }else{
                resolve("Failed")
            }
    
            })
            .catch((error)=>{
                console.log(error);
                reject(error);
            })
    })

}


export default getOrderDetails;