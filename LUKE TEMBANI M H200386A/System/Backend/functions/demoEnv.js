const { broadCastTemp } = require("./MarketPlaceWebSocket");

function generateEnvData(){
      
        setInterval(() => {
            let temp = getData(0,40);
            let humidity = getData(10,90);
            let gas = getData(200,500);
        
            let data = {
                username: 'Luke_Tembani',
                capture_time: Date.now(),
                temperature: temp,
                humidity: humidity,
                gas
              }
        
            console.log("DEMO DATA",data);
            broadCastTemp(data);
            data = {

            }
          }, 5000);
      


}


function getData(max, min){
    return Math.floor(Math.random() * (max-min+1) + min);
}


module.exports ={
    generateEnvData
}