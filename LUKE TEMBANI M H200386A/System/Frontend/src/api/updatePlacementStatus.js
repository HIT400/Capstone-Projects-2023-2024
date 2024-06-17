import api from "./api.config";

function updatePlacementStatus(status, placement_id){

    return new Promise((resolve, reject)=>{
        api.post("/update_placement",{status,placement_id}).then((response)=>{

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


export default updatePlacementStatus;