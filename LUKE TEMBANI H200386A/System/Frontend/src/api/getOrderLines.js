import api from "./api.config";


function getOrderLines(feed_order_id){
    return new Promise((resolve,reject)=>{
        api.post("/order_details",{feed_order_id}).then((response)=>{
            console.log("liness",response.data);
            resolve(response.data);
        })
        .catch((error)=>{
            console.log(error);
            reject(error);
        })
    })

}

export default getOrderLines;