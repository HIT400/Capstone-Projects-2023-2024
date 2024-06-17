const pool = require("../config/db_config");

function trackOrder(order_data){
    const sql = "INSERT INTO market_place_order (user_id,order_id,status,seller_name,date_ordered) VALUES (?,?,?,?,?)";
    const data = [order_data.user_id, order_data.idchicksforsale, order_data.status,order_data.seller_name, order_data.date_ordered];

    return new Promise((resolve,reject)=>{
        try {
            pool.query(sql,data,(err)=>{
                if(err){
                    reject(err);
                }else{
                    resolve("Success");
                }
            })
        } catch (error) {
            reject(error);
        }
    })


}

module.exports = {
    trackOrder
}