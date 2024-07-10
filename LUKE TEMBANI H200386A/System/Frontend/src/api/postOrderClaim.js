import api from "./api.config";

function claimChickenOrder(order_data){
    console.log("CLIENT", order_data);

    return new Promise((resolve,reject)=>{
        api.post("/update_mp_order",order_data).then((response)=>{

            console.log(response);

            if(response.status === 200){
                resolve("updated");
            }else{
                resolve("error");
            }

        }).catch((err)=>{
            reject(err);
        })
    })
}

export default claimChickenOrder;
