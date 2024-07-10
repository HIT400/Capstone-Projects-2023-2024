require("dotenv").config();

const { switchFan } = require("./switchFanFunc");
const {switchInfraLight} = require("./switchLight");


const optimumTemp = process.env.OP_TEMPERATURE; 
const optimumGasLevel = parseFloat(process.env.OP_GAS);
const optimumHumidity = parseFloat(process.env.OP_HUMIDITY);

const normalTempRange = {
    min: 30,max: 34
};
const normalGasRange = {
    min: 300, max: 500
};
const normalHumidityRange = {
    min: 40,max: 60
};

function controller(temperature, humidity, gas) {
    //Temperature Level
    console.log("CONTROL",temperature,humidity,gas);

    if (temperature> optimumTemp) {
        switchFan(21,"LOW");
        switchInfraLight(26,"HIGH");
    } else if (temperature < optimumTemp) {
        switchInfraLight(26,"LOW");
        switchFan(21,"HIGH");
    } else if (temperature >= normalTempRange.min && temperature <= normalTempRange.max) {
        switchInfraLight(26,"HIGH");
        switchFan(21,"HIGH");
    }

    //Humidity Levels
    if (humidity.level > optimumHumidity) {
        switchFan(21,"LOW");
    } else if (humidity.level < optimumHumidity) {
        switchFan(21,"HIGH");
    } else if (humidity.level >= normalHumidityRange.min && humidity.level <= normalHumidityRange.max) {
        // Normal Range
    }
    //Gas Level
    if (gas.level > optimumGasLevel) {
        switchFan(21,"LOW");
    } else if (gas.level < optimumGasLevel) {
        switchFan(21,"HIGH");
    } else if (gas.level >= normalGasRange.min && gas.level <= normalGasRange.max) {
        //Normal Range
    }
}

module.exports = {
    controller
}
