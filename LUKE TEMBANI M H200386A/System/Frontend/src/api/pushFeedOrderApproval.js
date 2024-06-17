import api from "./api.config";

function pushFeedApproval(notification_data){
    api.post("/push_notification",notification_data).then((response)=>{
        console.log(response);
    })
}


export default pushFeedApproval;