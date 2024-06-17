import api from "./api.config";

function updateFeedOrder(status, feed_order_id){

    return new Promise((resolve, reject)=>{
        api.post("/update_feed_order",{status,feed_order_id}).then((response)=>{
            try {
                if(response.data === "Success" && response.status === 200){
                    resolve("Success");
                }else{
                    resolve("Failed");
                }
            } catch (error) {
                reject(error);
            }
        })
    })
}
export default updateFeedOrder;



