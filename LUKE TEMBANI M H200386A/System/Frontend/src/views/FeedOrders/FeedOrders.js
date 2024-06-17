import { Button, Table, TableBody, TableCell, TableHead, TableRow, useTheme } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import getOrderLines from "src/api/getOrderLines";
import getOrdersList from "src/api/getOrders";
import PageContainer from "src/components/container/PageContainer";
import DashboardCard from "src/components/shared/DashboardCard";

const FeedOrders = () => {

    const company_name = localStorage.getItem("COMPANY_NAME");
    const [orders,setOrders] = useState([]);
    const nav = useNavigate();

    const theme = useTheme();


    useEffect(()=>{
        getOrders(company_name);
    },[])


    const getOrders = async(company_name) =>{
        let ordersList = await getOrdersList(company_name) 
        setOrders(ordersList);
        console.log("OL*****",ordersList);
    }


    const manageOrder = async(feed_order_id)=>{
        let orderLines = await getOrderLines(feed_order_id);
        nav("/dashboard/feed-order-lines",{state:orderLines});
        
    }


    return ( 
        <>

        <PageContainer description="Feed Orders Table">
        
        <DashboardCard title="Feed Orders">

        <Table>
        
        <TableHead>

        <TableRow>

        <TableCell>Order Id</TableCell>
        <TableCell>Customer Name</TableCell>
        <TableCell>Total Price - USD</TableCell>
        <TableCell>Date Ordered</TableCell>
        <TableCell>Status</TableCell>
        <TableCell></TableCell>
        
        </TableRow>
        </TableHead>


        <TableBody>

        {
            orders.length !== 0 ?(

                orders.map((order)=>(
                    <TableRow key={order.idchicken_feed_orders}>
                    <TableCell>{order.feed_order_id}</TableCell>
                    <TableCell>{order.customer_name}</TableCell>
                    <TableCell>{order.feed_total_price}</TableCell>
                    <TableCell>{order.date_ordered}</TableCell>
                    <TableCell>{order.status}</TableCell>
                    <TableCell><Button variant="contained" style={{background:theme.palette.mf.main}} onClick={()=>manageOrder(order.feed_order_id)}>Process</Button></TableCell>
                    </TableRow>
                ))

            ):(

                <TableRow>
                <TableCell>No Feed Orders Found</TableCell>
                </TableRow>
                
            )
        }


        
        </TableBody>
        
        </Table>

        
        </DashboardCard>
        
        </PageContainer>
    
        
        </>
     );
}
 
export default FeedOrders;