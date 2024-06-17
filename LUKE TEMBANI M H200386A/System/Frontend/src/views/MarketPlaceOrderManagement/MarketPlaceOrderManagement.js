import { Button, Table, TableBody, TableCell, TableHead, TableRow, useTheme } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation } from "react-router";
import getOrderDetails from "src/api/getOrderData";
import PageContainer from "src/components/container/PageContainer";
import DashboardCard from "src/components/shared/DashboardCard";

const MarketPlaceOrderManagement = () => {
    const [orderDetails,setOrderDetails] = useState(null);

    const theme = useTheme();

    const getOrderDataFunc = async(order_id)=>{
        let results = await getOrderDetails(order_id)
        setOrderDetails(results[0]);
        console.log("order data",results[0]);
        // console.log("det",orderDetails.number_of_birds); 
    }

    useEffect(()=>{
        getOrderDataFunc(location.state.order_id)
    },[]);

    

    const location = useLocation();

    console.log("ORDER SENT *****",location.state);


    const cancelOrder = ()=>{
       let status = window.confirm("Cancel Order ?");
    }

    const payOrder = ()=>{
        let status = window.confirm("Pay Order ?");
    }

    const contactSeller = ()=>{
        let status = window.confirm("Contact Seller ?");
    }

    return ( 
        <div>
        
        <PageContainer title="Market Place Order Management">
        

        <DashboardCard title={"Market Place Order Management"}>

        <Table>

        <TableHead>

        <TableRow>
        <TableCell>Number Of Birds</TableCell>
        <TableCell>Weight {"(Kg)"}</TableCell>
        <TableCell>Price Per Bird</TableCell>
        <TableCell>Location</TableCell>
        <TableCell>Total Price US$</TableCell>
        </TableRow>
        
        
        </TableHead>


        <TableBody>
        
        {
            orderDetails !== null ? (
                <TableRow>
                <TableCell>{orderDetails.number_of_birds}</TableCell>
                <TableCell>{orderDetails.weight_of_birds}</TableCell>
                <TableCell>{orderDetails.price}</TableCell>
                <TableCell>{orderDetails.location}</TableCell>
                <TableCell>{(orderDetails.number_of_birds) * (orderDetails.price)}</TableCell>
                </TableRow>
            
            ):(
                <TableRow>
                <TableCell>Loading</TableCell>
                </TableRow>
            
            )
        }

        
        <TableRow>

        <TableCell><Button style={{background:"green"}} onClick={()=>payOrder()} variant="contained">Pay Order</Button></TableCell>

        <TableCell><Button style={{background:"red"}} onClick={()=>cancelOrder()} variant="contained">Cancel Order</Button></TableCell>

        <TableCell><Button style={{background:theme.palette.mf.main}} onClick={()=>contactSeller()} variant="contained">Contact Seller</Button></TableCell>
        
        </TableRow>
        
        </TableBody>
        
        
        </Table>
        
        
        </DashboardCard>
        
        
        </PageContainer>
        
        </div>
     );
}
 
export default MarketPlaceOrderManagement;