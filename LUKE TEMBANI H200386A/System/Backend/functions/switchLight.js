const api = require("./api_config");

function switchInfraLight(pin,status) {
    console.log("light action");
    let data = {
        "GPIO_PIN":pin,
        "STATUS":status
    }

    api.post("/trigger",data).then((response)=>{

        if(response.data === "Done"){
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
    switchInfraLight
}

