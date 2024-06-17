require("dotenv").config();
const pool = require("../config/db_config");
const bcryptjs = require("bcryptjs");
const { sendPushNotification } = require("../functions/PushNotificationService");
const { broadCastUpdate, broadCastTemp } = require("../functions/MarketPlaceWebSocket");
const { generateFeedOrderId } = require("../functions/FeedOrderIDGenerator");
const { trackOrder } = require("../functions/trackMarketPlaceOrder");
const { generateCode } = require("../functions/resetCode");
const { sendSwtRequest } = require("../functions/postSwitchRequest");
const { generateEnvData } = require("../functions/demoEnv");
const { controller } = require("../functions/EnvironmentController");
const router = require("express").Router();


router.get("/",(req,res)=>{
    return res.status(200).json({message:"App is live"})
});


//GET APPLICATIONS
router.post("/contract/farming/applications",(req,res)=>{
    const companyID = req.body;    
    const sql = "SELECT * FROM contract_farming_applications WHERE supplier_name = ?";
    try {
        pool.query(sql,[companyID.supplier_name],(err,results)=>{
            if(err){
                console.log(err);
                return res.status(500).json({err});
            }else{
                if(results.length>0){
                    return res.status(200).json({message:"success",applications:results});
                }else{
                    return res.status(404).json({message:"No applications found"});
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }

})

router.get("/buying_companies",(req,res)=>{
    const sql = "SELECT * FROM buying_companies";
    pool.query(sql,(err,results)=>{
        if(err){
            return res.status(500).send(err);
        }else{
            if(results.length >0){
                return res.status(200).send(results);
                
            }else{
                return res.status(201).send("No company found!");
            }
        }
    })
})

//User Registration
router.post("/register", (req,res)=>{
    const user = req.body;
    const sql = "INSERT INTO users (fullname,username,password,address,contact) VALUES (?,?,?,?,?)";
    const check = "SELECT * FROM users WHERE username = ?";
    let password = bcryptjs.hashSync(user.password,10);
    const data = [user.fullname, user.username,password,user.address, user.contact];

    try {

        pool.query(check,[user.username],(err,results)=>{
            if(err){
                return res.status(500).json({error:err});
            }else{
                if(results.length>0){
                    console.log("duplicate");
                    res.status(200).json({message:"username already registered"});
                }else{
                    pool.query(sql,data,(err)=>{
                        if(err){
                            res.status(500).json({error:err});
                        }else{
                            res.status(200).json({message:"user registered"});
                
                        }
                    })
                }

            }
        })
    } catch (error) {

        res.status(500).json({error});
        
    }



})

//User Login
router.post("/login",(req,res)=>{
    const user = req.body;
    const login = "SELECT * FROM users WHERE username = ?";
    const data = [user.username];

    try {
        pool.query(login,data,(err,results)=>{
            if(err){
                return res.status(500).json({error:err});
            }else{
                if(results.length>0){
                    let passwordStatus = bcryptjs.compareSync(user.password,results[0].password);
    
                    if(passwordStatus){
                        //generateEnvData();
                        res.status(200).json({message:"User Authenticated"});
                    }else{
                        return res.status(403).json({message:"User not authenticated"});
                    }
    
                }else{
                    res.status(404).json({message:"No user found !"});
                }
    
                
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }

})

//Company Registration
router.post("/company_register",(req,res)=>{
    const user = req.query;
    const sql = "INSERT INTO company (companyname,username,password,address,contact,role) VALUES (?,?,?,?,?,?)";
    const check = "SELECT * FROM company WHERE username = ?";
    let password = bcryptjs.hashSync(user.password,10);
    const data = [user.company_name, user.username,password,user.address, user.contact_number, user.role];

    try {

        pool.query(check,[user.username],(err,results)=>{
            if(err){
                return res.status(500).json({error:err});
            }else{
                if(results.length>0){
                    res.status(200).json({message:"Username already registered, Try another"});
                }else{
                    pool.query(sql,data,(err)=>{
                        if(err){
                            res.status(500).json({error:err});
                        }else{
                            res.status(200).json({message:"User registered"});
                
                        }
                    })
                }

            }
        })
    } catch (error) {

        res.status(500).json({error});
        
    }
})


//Company Login
router.post("/company_login",(req,res)=>{
    const user = req.query;
    const login = "SELECT * FROM company WHERE username = ?";
    const data = [user.username];

    try {
        pool.query(login,data,(err,results)=>{
            if(err){
                return res.status(500).json({error:err});
            }else{
                if(results.length>0){

                    let passwordStatus = bcryptjs.compareSync(user.password,results[0].password);
    
                    if(passwordStatus){
                        res.status(200).json({message:"User Authenticated",results});
                    }else{
                        
                        return res.status(200).json({message:"User not authenticated"});
                    }
    
                }else{
                    res.status(200).json({message:"No user found !"});
                }
    
                
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }

})

//Add Data FROM Mobile
router.post("/contract/farming/report",(req,res)=>{
    const reportData = req.body;
    console.log(reportData);
    const sql = "INSERT INTO contract_farming_reporting(full_name,mortality,stage,growers,finisher,starter,supplier,date) VALUES (?,?,?,?,?,?,?,?)";
    const data = [reportData.full_name, reportData.mortality, reportData.stage, reportData.growers, reportData.finisher, reportData.starter, reportData.supplier, new Date()];

    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                console.log(err);
                return res.status(500).json({err});
            }else{
                if(results.affectedRows === 1){
                    return res.status(200).json({message:"success"});
                }else{
                    return res.status(400).json({message:"Bad request"});
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }
});

//Get Report Data For Web
router.post("/contract/farming/report/data",(req,res)=>{
    const companyID = req.body;
    console.log(companyID);
    const sql = "SELECT * FROM contract_farming_reporting WHERE full_name = ? AND supplier = ? ORDER BY date DESC";
    const data = [companyID.full_name, companyID.supplier];
    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                console.log(err);
                return res.status(500).json({err});
            }else{
                if(results.length>0){
                    console.log(results);
                    return res.status(200).json({message:"success", report:results});
                }else{
                    return res.status(404).json({message:"Not found"});
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }
})
//Add Chicks Placement
router.post("/add_placement",(req,res)=>{
    const chicks = req.body;
    const last_id_sql = "SELECT * FROM chicks_placement ORDER BY idchicks_placement DESC LIMIT 1";
    const sql = "INSERT INTO chicks_placement (company_name, chicks_breed, chicks_quantity,unit_price,total_price,customer_username,placement_date,placement_status) VALUES (?,?,?,?,?,?,?,?)";
   
    try {
        pool.query(last_id_sql,(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length != 0 ){
                    let placement_id = results[0].idchicks_placement+1;
                    const data = [chicks.supplier, chicks.chicks_breed, chicks.chicks_number,chicks.unit_price,chicks.total_price, chicks.customer_name, chicks.placement_date, "Pending"];
                    pool.query(sql,data,(err)=>{
                        if(err){
                            return res.status(500).json({error:err});
                        }else{
                            return res.status(200).json({
                                placement_id
                                
                            })
                        }
                    })
                }else{
                    let placement_id = 1;
                    const data = [chicks.supplier, chicks.chicks_breed, chicks.chicks_number,chicks.unit_price,chicks.total_price, chicks.customer_name, chicks.placement_date, "Pending"];
                    pool.query(sql,data,(err)=>{
                        if(err){
                            return res.status(500).json({error:err});
                        }else{
                            return res.status(200).json({
                                placement_id
                            })
                        }
                    })
                }

            }
        })


    } catch (error) {
        res.status(500).json({error:error});
    }

});


router.post("/update/contract/farming/request",(req,res)=>{
    const updateData = req.body;
    const sql = "UPDATE contract_farming_applications SET status = ? WHERE idcontract_farming_applications = ?";
    const data = [updateData.status, updateData.id];
    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                console.log(err);
                return res.status(500).json({err});
            }else{
                if(results.affectedRows === 1){
                    return res.status(200).json({message:"success"});
                }else{
                    return res.status(400).json({message:"Bad request"});
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});   
    }
})

//Get User Placements
router.post("/placements",(req,res)=>{
    const sql = "SELECT * FROM chicks_placement WHERE customer_username = ?";
    const {username} = req.body;

    try {
        pool.query(sql,[username],(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length > 0){
                    return res.status(200).send(results)
                }else{
                    return res.status(200).send([]);
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }


});

//Web Hook For Raspberry Pi Integration
router.post("/rasp_weather_hook",(req,res)=>{
    const weather_data = req.body;
    console.log("RASPBERRY PI",weather_data);
    broadCastTemp(weather_data);
    
    controller(weather_data.temperature,weather_data.humidity,320);


    const sql = "INSERT INTO weather_data (customer_username,capture_time,temperature,humidity) VALUES (?,?,?,?)";
    const data = [weather_data.username, weather_data.capture_time,weather_data.temperature, weather_data.humidity];
    const check = "SELECT isIntegrated FROM users WHERE username = ?";

    try {
        pool.query(check,[weather_data.username],(err,results)=>{
            if(err){
                console.log(err);
                return res.status(500).json({error:err});
            }else{
                if(results.length>0){
                    if(results[0].isIntegrated === 1){
                        pool.query(sql,data,(err)=>{
                            if(err){
                                return res.status(500).json({error:err});
                            }else{
                                console.log("Data Logged Successfully");
                                return res.status(200).json({message:"Weather Data Logged Successfully"});
                            }
                        })}
                    else{
                        return res.status(200).json({message:"User not authenticated to log environment weather data"});
                    }
                }else{
                    console.log("User not found")
                    return res.status(200).json({message:"User not found!"});   
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }
})

//Webhook
router.post("/procure",(req,res)=>{
    const procument = req.body;

    const sql = "SELECT * FROM company_stock WHERE company_name = ? AND type = ?";
    const data = [procument.company_name, procument.type];


    try {
        pool.query(sql,data,(err,result)=>{
            if(err){
                return res.status(500).json({error:err});
    
            }else{
                if(result.length>0){
                    return res.status(200).send(result);
                }else{
                    return res.status(200).json({message:"No Stock Found"});
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }



})

//Turn Lights On
router.get("/components",(req,res)=>{

    const sql = "SELECT * FROM components";

    try {
        pool.query(sql,(err,results)=>{
            if(err){
                return res.status(500).json(err);
            }else{
                if(results.length >0){
                    return res.status(200).send(results);
                }else{
                    return res.status(200).send("No components Found!");
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }



})

//Chicks 4 Sale
router.post("/sell_chicks",(req,res)=>{
    const order = req.body;
    const sql = "INSERT INTO chicksforsale(customer_username, number_of_birds, weight_of_birds, location,price, date) VALUES (?,?,?,?,?,?)";
    const data = [order.customer_username, order.number_of_birds, order.weight_of_birds, order.location,order.price, order.date];
    try {
    pool.query(sql,data,(err)=>{
        if(err){
            console.log(err);
            return res.status(500).json({err});
        }else{
            let time_stamp = Date.now();
            broadCastUpdate(time_stamp); // Call this method to trigger market place re-render to subscribed clients
            return res.status(200).send("Saved");
        }
    })
    } catch (error) {
        return res.status(500).json({error});
        
    }
})

//Get Chicks from Supplier
router.post("/get_chicks",(req,res)=>{
    const chicks_data = req.body;
    const sql = "SELECT product_name,quantity,usd_price FROM company_stock WHERE company_name =? AND type = ? AND quantity > 10"; //can be zero
    const data = [chicks_data.company_name, chicks_data.stock_type];

    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length>0){
                    return res.status(200).send(results);
                }else{
                    return res.status(200).send([]);
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }


})


//Add New Vet
router.post("/add_vet",(req,res)=>{
    const vet = req.body;
    const sql = "INSERT INTO veterinarians (vet_name, vet_contact, vet_location) VALUES (?,?,?)";
    const data = [vet.vet_name, vet.vet_contact, vet.vet_location];
    try {
        pool.query(sql,data,(err)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                return res.status(200).send("Saved");
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }


})


//Send Push Notification
router.post("/push_notification",(req,res)=>{
    let notification_data = req.body;
    let title = notification_data.title;
    let body = notification_data.body;
    let status = notification_data.status;
    let placement_id = notification_data.placement_id;
    let feed_order_id = notification_data.feed_order_id;
    let deviceUsername = notification_data.username;
    const sql = "SELECT fcm_token FROM users WHERE username = ?";
    const data = [deviceUsername];

    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length>0){
                    let deviceToken = results[0].fcm_token;
                    if (feed_order_id){
                        sendPushNotification(deviceToken,title,body,feed_order_id,status);
                    }else{
                        sendPushNotification(deviceToken,title,body,placement_id,status);
                    }
                      return res.status(200).send("Ok");
    
                }else{
                    return res.status(200).send("No FCM Token Found");
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }



   
   
})

//Update Firebase Token - Mobile Users
router.post("/update_fcm_token",(req,res)=>{
    const user = req.body;
    let token = user.token;
    let username = user.username;

    const sql = "UPDATE users SET fcm_token = ? WHERE username = ?";
    const check_sql = "SELECT * FROM users WHERE username = ?";
    const data = [token, username];

    try {
        pool.query(check_sql,[username],(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length ===1){
                    pool.query(sql,data,(err)=>{
                        if(err){
                            return res.status(500).send(err);
                        }else{
                            return res.status(200).send("Success");
                        }
                    })
                }else{
                    return res.status(500).send("User Undefined!");
                }
            }
        })
    } catch (error) {
     return res.status(500).send(error);   
    }

})


//Get Veterinarians
router.get("/veterinarians",(req,res)=>{
    const sql = "SELECT * FROM veterinarians";
    pool.query(sql,(err,results)=>{
        if(err){
            return res.status(500).send(err);
        }else{
            if(results.length > 0){
                return res.status(200).send(results);
            }else{
                return res.status(200).send("No veterinarian found!");
            }
        }
    })
})


//Update Chicks Placements
router.post("/update_placement",(req,res)=>{
    const placement_update = req.body;
    const sql = "UPDATE chicks_placement SET placement_status = ? WHERE idchicks_placement = ?";
    const data = [placement_update.status, placement_update.placement_id];
    try {

        if(placement_update.placement_id){

            pool.query(sql,data,(err)=>{
                if(err){
                    return res.status(500).send(err);
                }else{
                    return res.status(200).send("Success");
                }
            })
        }else{
            return res.status(500).json({error:"Placement ID Not Specified!"});
        }

    } catch (error) {
        return res.status(500).send(error);
    }
})

//Update Feed Orders
router.post("/update_feed_order",(req,res)=>{
    const feed_update = req.body;
    const sql = "UPDATE chicken_feed_orders SET status = ? WHERE feed_order_id = ?";
    const data = [feed_update.status, feed_update.feed_order_id];
    try {

        if(feed_update.feed_order_id){

            pool.query(sql,data,(err)=>{
                if(err){
                    return res.status(500).send(err);
                }else{
                    return res.status(200).send("Success");
                }
            })
        }else{
            return res.status(500).json({error:"Feed Order ID Not Specified!"});
        }

    } catch (error) {
        return res.status(500).send(error);
    }
})


//Get Company Placement Orders
router.post("/company_placements",(req,res)=>{
    const request = req.body;
    const sql = "SELECT * FROM chicks_placement WHERE company_name = ?";
    const data = [request.companyName];

    try {    
    pool.query(sql,data,(err,results)=>{
        if(err){
            return res.status(500).send(err);

        }else{

            if(results.length >0){
                return res.status(200).send(results);
            }else{
                return res.status(200).send("No placements found!");
            }
        }
    })
    } catch (error) {
        return res.status(500).send(error);
    }


})

//Get Chicks For Sale
router.post("/birds_for_sale",(req,res)=>{
    const all_sql = "SELECT * FROM chicksforsale WHERE status != ? ORDER BY date DESC";
    let status = "claimed";
  
    try {

                pool.query(all_sql,[status],(err,results)=>{
                    if(err){
                        return res.status(500).send(err);
                    }else{
                        if(results.length> 0){
                            return res.status(200).send(results);
                        }else{
                            return res.status(200).send([]);
                        }

                    }
                })
            } catch (error) {
                return res.status(500).send(error);
            
    } 
})

//Get Chicken Feed Suppliers
router.get("/feed_suppliers",(req,res)=>{
    const sql = "SELECT companyname FROM company WHERE has_feed = ?";
    const data = [1]

    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length >0){
                    return res.status(200).send(results);
                }else{
                    return res.status(200).send("No Suppliers Found!");
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }


})

//Get Company Stock
router.post("/feed_stock",(req,res)=>{
    const company = req.body;
    const sql = "SELECT * FROM company_stock WHERE company_name = ? AND type = ?";
    const data = [company.company_name, company.type];


    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                return res.status(500).send(err);
    
            }else{
                if(results.length>0){
                    return res.status(200).send(results);
                }else{
                    return res.status(200).send([]);
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }


})


//SWITCH RASPBERRY PI COMPONENTS
router.post("/switch_component",(req,res)=>{
    const light = req.body;
    const updateData = "SELECT * FROM components";

    if(light.component_status === '1'){

        let request_data = {
            "GPIO_PIN": light.gpio_pin,
            "STATUS":"HIGH"
          }
         sendSwtRequest(request_data);

    }else if(light.component_status === '0'){

        let request_data = {
            "GPIO_PIN": light.gpio_pin,
            "STATUS":"LOW"
          }

       sendSwtRequest(request_data);
    }


    const sql = "UPDATE components SET component_status = ? WHERE component_id = ?";
    let new_status = light.component_status == '1' ? '0' : '1';
    const data = [new_status, light.component_id];

try {
    pool.query(sql,data,(err)=>{
        if(err){
            return res.status(500).send(err);
        }else{
            pool.query(updateData,(err,results)=>{

                if(err){
                    return res.status(500).send(err);
                }else{
                    return res.status(200).send(results);
                }

            })
        }
    })
} catch (error) {
    return res.status(500).send(error);
}

    


})

//GET USER CHICKS IN MARKET
router.post("/birds_on_sale",(req,res)=>{
    const sql = "SELECT * FROM chicksforsale WHERE customer_username = ? AND status != 'claimed'";
    const {username} = req.body;
    console.log(username);
    try {
        pool.query(sql,[username],(err,results)=>{
            if(err){
                return res.status(500).send(err)
            }else{
                console.log(results);
                return res.status(200).send(results);
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }

})

//Company Update API
router.post("/cancel_sale_order",(req,res)=>{
    const sql = "DELETE FROM chicksforsale WHERE idchicksforsale = ? AND customer_username = ?";
    const {idchicksforsale, username} = req.body;
    const updatedData = "SELECT * FROM chicksforsale WHERE customer_username = ? AND status != 'claimed'";

    try {
        pool.query(sql,[idchicksforsale,username],(err)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                pool.query(updatedData,[username],(err,results)=>{
                    if(err){
                        return res.status(500).send(err);
                    }else{
                        return res.status(200).send(results);
                    }
                })
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }



    
})

//Company Update API
router.post("/update_company",(req,res)=>{
    const {update_data} = req.body;
    let {idcompany} = req.body;
    let sub_query_master = "";
    let fieldsData = [];
    let update_keys = Object.keys(update_data);

    if(idcompany){
            for (let i = 0; i<update_keys.length;i++){
        if(i === (update_keys.length-1)){
            let sub_query = `${update_keys[i]} = ?`;
            sub_query_master += sub_query;
            fieldsData.push(update_data[update_keys[i]]);
        }else{
            let sub_query = `${update_keys[i]} = ?,`;
            sub_query_master += sub_query;
            fieldsData.push(update_data[update_keys[i]]);
        }

    }
    fieldsData.push(idcompany);
    const sql = `Update company SET ${sub_query_master} WHERE idcompany = ?`;
    console.log(sql,fieldsData);

    pool.query(sql,fieldsData,(err)=>{
        if(err){
            console.log(err);
        }else{
            return res.status(200).send("OK");
        }
    })
    }else{
        return res.status(403).send("No Company Id Specified!");
    }
});


//UPDATE MARKET PLACE SALE ORDER
router.post("/update_mp_order",(req,res)=>{
    const order_data = req.body;
    const sql = "UPDATE chicksforsale SET status = ? WHERE idchicksforsale = ?";
    const check_sql = "SELECT * FROM chicksforsale WHERE idchicksforsale = ?";
    const data = [order_data.status, order_data.idchicksforsale];

    try {
        if(order_data.status && order_data.idchicksforsale){
            pool.query(check_sql,[order_data.idchicksforsale],(err,results)=>{
                if(err){
                    return res.status(500).send(err);
                }else{
                    if(results.length === 1){
                        pool.query(sql,data,async(err)=>{
                            if(err){
                                return res.status(500).send(err);
                            }else{
                                let time_stamp = Date.now();
                                broadCastUpdate(time_stamp); // Call this method to trigger market place re-render to subscribed clients
                                let save_order = await trackOrder(order_data);
                                if(save_order === "Success"){
                                    return res.status(200).send("Success");
                                }else{
                                    return res.status(500).send("Error");
                                }
                                
                            }
                        })
                    }else{
                        return res.status(405).send("Order not found");
                    }
                }
            })

        }else{
            return res.status(203).send("Invalid Request Body");
        }
    } catch (error) {
        return res.status(500).send(error);
    }

});

//PASSWORD RESET
router.post("/password_reset",(req,res)=>{
    const {email} = req.body;
    const sql = "SELECT email FROM company WHERE email = ?";
    const sqlCode = "UPDATE company SET pass_reset = ? WHERE email = ?";
    const data = [email];

    pool.query(sql,data,(err,results)=>{
        if(err){
            return res.status(500).json({error:err});
        }else{
            
            if(results.length === 1){
                let code = generateCode();

                pool.query(sqlCode,[code,email],(err)=>{
                    if(err){
                        return res.status(500).json({error:err});
                    }else{
                        //Send Email With Code : code
                        //Start Timer To Clear Code In DD
                        console.log(code);

                        return res.status(200).json({message:"Password Reset Successful"});
                    }
                })


            }
        }
    })
})

//GET ORDER DETAILS
router.post("/order_data",(req,res)=>{
    const {order_id} = req.body;
    const sql = "SELECT * FROM chicksforsale WHERE idchicksforsale = ?";
    const data = [order_id];

    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length>0){
                 return res.status(200).send(results);   
                }else{
                    return res.status(200).send("No Data Found");
                }
            }
        })
    } catch (error) {
        
    }
})

//Feed Order API
router.post("/add_feed_order",(req,res)=>{
    const order = req.body;
    console.log(order);
    let feed_order_id = generateFeedOrderId(order.orderData.customer_username);
    const last_id_sql = "SELECT feed_order_id FROM chicken_feed_orders ORDER BY idchicken_feed_orders DESC LIMIT 1";
    const sql = "INSERT INTO chicken_feed_orders (feed_order_id, customer_name, feed_total_price,company_name,date_ordered) VALUES (?,?,?,?,?)";
    const sql_order_lines = "INSERT INTO feed_order_lines (feed_order_id, feed_type,feed_quantity, feed_amount) VALUES (?,?,?,?)";
    const order_data = [feed_order_id, order.orderData.customer_username, order.orderData.feed_total_price,order.orderData.company_name, order.orderData.date_ordered];
    try {
        pool.query(sql,order_data,(err)=>{
            if(err){
                console.log(err);
                return res.status(500).send(err);
            }else{
                for(let i = 0; i < order.orderLinesList.length; i++){
                    let order_line_item = [feed_order_id, order.orderLinesList[i].feed_type, order.orderLinesList[i].feed_quantity, order.orderLinesList[i].feed_amount];
                    pool.query(sql_order_lines,order_line_item,(err)=>{
                        if(err){
                            return res.status(500).send("Error",err);
                        }else{
                            return;
                        }
                    })
                }
                pool.query(last_id_sql,(err,results)=>{
                    if(err){
                        return res.status(500).json({message:err});
                    }else{
                        return res.status(200).json({message:"Success",feed_order_id:results[0].feed_order_id});
                    }
                })
            }
        })
    } catch (error) {
        return res.status(500).send("Error",error);        
    }
});


//Retrieve Order Headers
router.post("/order_details",(req,res)=>{
    const order = req.body;
    const sql = "SELECT * FROM chicken_feed_orders WHERE feed_order_id = ?";
    const sql_lines = "SELECT * FROM feed_order_lines WHERE feed_order_id = ?";
    const data = [order.feed_order_id];
    let order_data = {};
    pool.query(sql,data,(err,results)=>{
        if(err){
            return res.status(500).send(err);
        }else{
            if(results.length >0 ){
                order_data.order_data = results;
                pool.query(sql_lines,data,(err,results)=>{
                    if(err){
                        return res.status(500).send(err);
                    }else{
                        if(results.length>0){
                            order_data.order_lines = results;
                            return res.status(200).send(order_data)
                        }else{
                            return res.status(203).send("No lines found for order id"+ " "+order.feed_order_id);
                        }
                    }
                })

            }else{
                return res.status(203).send("No order found with id"+" "+order.feed_order_id);
            }
        }
    })
});


//COMPANY STOCK UPDATE API
router.post("/update_stock",(req,res)=>{
    const {qty,company_name,product_name,feed_type} = req.body;
    const update_qty_sql = "UPDATE company_stock SET quantity = quantity + ? WHERE company_name = ? AND product_name = ? AND type = ?";
    const data = [qty,company_name,product_name,feed_type];

    try {

        if(qty == "" || company_name == "" || product_name == "" || feed_type == ""){
            return res.status(200).json({message:"Missing Fields in Request Body"});
        }else{
            pool.query(update_qty_sql,data,(err)=>{
                if(err){
                    console.log(err);
                    return res.status(500).send(err);
                }else{
                    return res.status(200).send("Stock Updated")
                }
            })
        }

    } catch (error) {
        console.log(error);
        return res.status(200).json({error});
    }


    

});


//GET COMPANY FEED ORDERS
router.post("/company_orders",(req,res)=>{
    const company = req.body;
    const sql = "SELECT * FROM chicken_feed_orders WHERE company_name = ?";
    try {
        
    pool.query(sql,[company.company_name],(err,results)=>{
        if(err){
            return res.status(500).send(err);
        }else{
            if(results.length>0){
                return res.status(200).send(results);
            }else{
                return res.status(203).send("No Orders Found!");
            }
        }
    })
    } catch (error) {
       return res.status(500).send(error); 
    }

});

//Web Inventory Management
//Add Stock
router.post("/add_stock",(req,res)=>{
    const stock = req.body;
    const sql = "INSERT INTO company_stock (company_name, product_name, quantity, usd_price, type) VALUES (?,?,?,?,?)";
    const hasFeedSql = "UPDATE company SET has_feed = 1 WHERE companyname = ?";
    const data = [stock.company_name, stock.product_name, stock.quantity, stock.usd_price, stock.type];
    pool.query(sql,data,(err)=>{
        if(err){
            return res.status(500).send(err);
        }else{
            pool.query(hasFeedSql,[stock.company_name],(err,results)=>{
                if(err){
                    console.log(err);
                }else{
                    console.log(results);
                    return res.status(200).send("Success");
                }
            })
            
        }
    })

})

//Retrieve Stock - Feed & Chicks
router.post("/stock",(req,res)=>{
    const data = req.body;
    const sql = "SELECT * FROM company_stock WHERE company_name = ? AND type = ?";
    
    try {
        pool.query(sql,[data.company_name, data.stock_type],(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length>0){
                    return res.status(200).send(results);
                }else{
                    return res.status(203).send("No Stock Found!");
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }
});


//Retrieve Number Of Pending Placements
router.post("/placements_count",(req,res)=>{
    const company = req.body;
    const sql = "SELECT COUNT(*) AS total FROM chicks_placement WHERE company_name = ? AND placement_status = 'Pending'";
    
    try {
        pool.query(sql,[company.company_name],(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length>0){
                    return res.status(200).json({total:results[0]["total"]})
                }else{
                    return res.status(404).send("No Placements Found!");
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }
});


//Retrieve Feed Orders
router.post("/feed_orders_count",(req,res)=>{
    const company = req.body;
    const sql = "SELECT COUNT(*) AS total FROM chicken_feed_orders WHERE company_name = ?";
    
    try {
        pool.query(sql,[company.company_name],(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                if(results.length>0){
                    return res.status(200).json({total:results[0]["total"]})
                }else{
                    return res.status(404).send("No Placements Found!");
                }
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }
});


//MARKET PLACE APIs
//Get Orders - Market Place
router.post("/market_place_orders",(req,res)=>{
    const user_data = req.body;
    const sql = "SELECT * FROM market_place_order WHERE user_id = ?";

    try {
        pool.query(sql,[user_data.user_data],(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{

                if(results.length>0){
                return res.status(200).send(results);
                }else{
                    return res.status(200).send([]);
                }
                
            }
        });
    } catch (error) {
        
    }





})

//GET MARKET PLACE ORDERS
router.post("/get_orders",(req,res)=>{
    const {username} = req.body;
    const sql = "SELECT * FROM chicken_feed_orders WHERE customer_name = ?";
    const data = [username];
    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                return res.status(500).send(err);
            }else{
                console.log(results);
                return res.status(200).send(results);
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }
})

//Claimed Orders
router.post("/claimed_orders",(req,res)=>{
    const {username} = req.body;
    const sql = "SELECT * FROM chicksforsale cfs JOIN market_place_order mpo WHERE cfs.idchicksforsale = mpo.order_id && cfs.customer_username = ? ORDER BY mpo.date_ordered DESC;"

    try {
        pool.query(sql,[username],(err,results)=>{
            if(err){
                return res.status(500).json({error:err});
            }else{
                if(results.length>0){
                    console.log(results);
                    return res.status(200).send(results);
                }else{
                    return res.status(200).send([]);
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }


})


//UPDATE MARKET PLACE ORDER
router.post("/update_market_order",(req,res)=>{
    const {claim_order_id, status} = req.body;
    const sql = "UPDATE market_place_order SET status = ? WHERE order_id = ?";

    try {
        pool.query(sql,[status,claim_order_id],(err)=>{
            if(err){
                return res.status(500).json({message:"Something went wrong"}, err);
            }else{
                return res.status(200).json({message:"Successfully Updated"});
            }
        })
    } catch (error) {
        return res.status(500).send(error);
    }
});


router.post("/apply/contract/farming",(req,res)=>{
    const appData = req.body;
    console.log(appData);
    const sql = "INSERT INTO contract_farming_applications (full_name,supplier_name,status,date) VALUES (?,?,?,?)";
    const data = [appData.full_name, appData.supplier,"pending",new Date()];
    try {
        pool.query(sql,data,(err,results)=>{
            if(err){
                console.log(err);
                return res.status(500).json({err});
            }else{
                if(results.affectedRows === 1){
                    return res.status(200).json({message:"success"});
                }else{
                    return res.status(400).json({message:"Bad request"});
                }
            }
        })
    } catch (error) {
        return res.status(500).json({error});
    }
});


router.post("/gas/signal",(req,res)=>{
    const data = req.body;

    console.log(data);

    let request_data = {
        "GPIO_PIN": 21,
        "STATUS":"LOW"
      }
   sendSwtRequest(request_data);

   setTimeout(()=>{
    let request_data = {
        "GPIO_PIN": 21,
        "STATUS":"HIGH"
      }
      console.log("turn off fan after 10 seconds!");
   sendSwtRequest(request_data);
   },10000);


    return res.status(200).json({message:"success"});
});

module.exports = router;