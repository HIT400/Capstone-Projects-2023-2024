const webSocket = require("ws");
const clients = {};
const {v4 : uuidv4} = require("uuid");
const wss = new webSocket.Server({port:8823});

wss.on("connection", function(connection){
    let userId = uuidv4();
    clients[userId] = connection;
})

function broadCastUpdate(time_stamp) {
    for(let userId in clients){
        let client = clients[userId];
        client.send("Update"+time_stamp);
    }
}

function broadCastTemp(weather_data){
    for(let userId in clients){
        let client = clients[userId];
        let data = JSON.stringify(weather_data);
        client.send(data);
    }
}


module.exports = {
    broadCastUpdate,
    broadCastTemp

}