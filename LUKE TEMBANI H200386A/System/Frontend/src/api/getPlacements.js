import api from "./api.config";


function getPlacements(companyName){
    return new Promise((resolve,reject)=>{
        try {
            api.post("/company_placements",{companyName}).then((response)=>{
               if(response.status === 200 && response.data !== "No placements found!"){
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

export default getPlacements;