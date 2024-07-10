import api from "./api.config";

function getChickensOnSale(){
    return new Promise((resolve, reject)=>{
        try {
            api.post("/birds_for_sale").then((response)=>{
                if(response.status === 200 && response.data){
                    resolve(response.data);
                }else{
                    resolve([]);
                }            
            })
        } catch (error) {
            reject(error);
        }
    })
}
export default getChickensOnSale;



