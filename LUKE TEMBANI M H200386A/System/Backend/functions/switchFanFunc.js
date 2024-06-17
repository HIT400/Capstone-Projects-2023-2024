const api = require("./api_config");


function switchFan(pin,status) {
    let data = {
        "GPIO_PIN":pin,
        "STATUS":status
    }

    console.log("fan action")

    api.post("/trigger",data).then((response)=>{

        if(response){
            console.log("response",response);
            console.log("Trigger Successful");
        }else{
            console.log("Trigger Failed");
        }
    })
    .catch((error)=>{
        console.log("Error Occurred",error);
    })

}

module.exports = {
    switchFan
};