const api = require("./api_config");

function sendSwtRequest(request_data){
    api.post("/trigger",request_data).then((response)=>{
        console.log("Rapberyy server",response.data);
    })
    .catch((error)=>{
        console.log(error);
    })
}


module.exports = {
    sendSwtRequest
}