import api from "./api.config";


function postStock(stock){
    return new Promise((resolve,reject)=>{
        api.post("/add_stock",stock).then((response)=>{
            if(response.status === 200){
                resolve("Success");
            }
        })
        .catch((error)=>{
            reject(error);
        })
    })
}

export default postStock;