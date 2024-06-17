import { Button, Stack, Table, TableBody, TableCell, TableRow } from "@mui/material";
import { useLocation } from "react-router";
import pushFeedApproval from "src/api/pushFeedOrderApproval";
import updateFeedOrder from "src/api/updateFeedOrderStatus";

const FeedOrderLines = () => {


    const location = useLocation();
    const orderData = location.state;

    const orderheader = orderData.order_data[0];
    const orderLines = orderData.order_lines;


    const updateFeedOrderFunc = async(status, feed_order_id, username)=>{

        if(status === "Approved"){

            let result = window.confirm("Approve Order ?");

            if(result){
                let result = await updateFeedOrder(status, feed_order_id);

                if(result === "Success"){
                    window.alert("Feed Order Updated Successfully");
        
                    
                    let notification_data = {
                        "username":username,
                        "title":"Modern Farmer",
                        "body":"Your Feed Order has been Approved",
                        "feed_order_id":feed_order_id,
                        "status":"accepted"
                    }
        
                    pushFeedApproval(notification_data);
                    
                
                }else{
                    alert('Something went wrong while updating order, please retry!');
                }
            }

        }else{
            let result = window.confirm("Revoke Feed Order ?");

            if(result){
                let result = await updateFeedOrder(status, feed_order_id);

                if(result === "Success"){
                    window.alert("Feed Order Revoked Successfully");
        
                    
                    let notification_data = {
                        "username":username,
                        "title":"Modern Farmer",
                        "body":"Your Feed Order has been Revoked",
                        "feed_order_id":feed_order_id,
                        "status":"revoked"
                    }
        
                    pushFeedApproval(notification_data);
                    
                
                }else{
                    alert('Something went wrong while updating order, please retry!');
                }
            }
        }



    }

    return ( 
        <>

        <div className="container">
        <h4>Order Header</h4>
        <Table>
        <TableRow>
        <TableCell>Order Id</TableCell>
        <TableCell>Customer Name</TableCell>
        <TableCell>Total Price - USD</TableCell>
        <TableCell>Date Ordered</TableCell>
        </TableRow>

        <TableBody>
        <TableRow>
        <TableCell>{orderheader.feed_order_id}</TableCell>
        <TableCell>{orderheader.customer_name}</TableCell>
        <TableCell>{orderheader.feed_total_price}</TableCell>
        <TableCell>{orderheader.date_ordered}</TableCell>
        </TableRow>
        
        </TableBody>
    
        </Table>

        </div>


        <div>
        <h4>Order Lines</h4>

        <Table>

        <TableRow>
        <TableCell>Feed Type</TableCell>
        <TableCell>Quantity</TableCell>
        <TableCell>Amount</TableCell>
        </TableRow>


        <TableBody>

        {
            orderLines.length !== 0 ? (

                orderLines.map((orderLine)=>(
                    <TableRow key={orderLine.idfeed_order_lines}>
                    <TableCell>{orderLine.feed_type}</TableCell>
                    <TableCell>{orderLine.feed_quantity}</TableCell>
                    <TableCell>{orderLine.feed_amount}</TableCell>
                    </TableRow>
                ))
            )
            :
            (
                <TableRow>
                <TableCell>No Order Lines Found!</TableCell>
                </TableRow>
            )
        }
        
        
        </TableBody>
        
        
        </Table>

        <Stack direction="row" spacing={5}>
        <Button style={{background:"green"}} onClick={()=>updateFeedOrderFunc("Approved",orderheader.feed_order_id,orderheader.customer_name)} variant="contained">Approve</Button>
        <Button onClick={()=>updateFeedOrderFunc("Revoked",orderheader.feed_order_id,orderheader.customer_name)} variant="contained" style={{backgroundColor:"red"}}>Revoke</Button>
        </Stack>

        
  

        </div>


        
        
        
        </>
     );
}
 
export default FeedOrderLines;