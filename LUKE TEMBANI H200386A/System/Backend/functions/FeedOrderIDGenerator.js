
function generateFeedOrderId(username){
    let time_stamp = Date.now();
    return `${username}_${time_stamp}`;
}

module.exports = {
    generateFeedOrderId
}