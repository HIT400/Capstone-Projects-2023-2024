const admin = require("firebase-admin");
const serviceAccount = require("../modern_farmer_service_account.json");

const firebaseAdmin = admin.initializeApp({
    credential: admin.credential.cert(serviceAccount)
})

async function sendPushNotification(devicePushToken, title, body,placement_id, status){

    try {
        await firebaseAdmin.messaging().send({
            token:devicePushToken,
            notification:{
                title,
                body
            },
            data:{
                "placement_id":`PL_${placement_id}`,
                "status":status
            }
        })
    } catch (error) {
        console.log(error);
    }
}


module.exports = {
    sendPushNotification
}