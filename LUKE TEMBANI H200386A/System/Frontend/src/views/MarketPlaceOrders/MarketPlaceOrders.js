import { Button, Table, TableBody, TableCell, TableHead, TableRow, useTheme } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import getOrdersMarketPlace from "src/api/getMarketPlaceOrders";
import PageContainer from "src/components/container/PageContainer";
import DashboardCard from "src/components/shared/DashboardCard";

const MarketPlaceOrders = () => {
    const theme = useTheme();
    const [orders,setOrders] = useState([]);
    let username = localStorage.getItem("Username");
    const navigate = useNavigate();

    const getOrders = async()=>{
        let orders = await getOrdersMarketPlace(username);
        console.log("Orders King", orders);
        setOrders(orders);  
    }


    const processOrder = (orderData)=>{
        navigate("/dashboard/market-place-order-management",{state:orderData})
    }

    useEffect(()=>{
        getOrders();
    },[])

    return ( 
        <>

        <PageContainer description="Market Place Orders">
        
        <DashboardCard title="Market Place Orders">

        <Table>
        
        <TableHead>

        <TableRow>

        <TableCell>Order Id</TableCell>
        <TableCell>Customer Name</TableCell>
        <TableCell>Status</TableCell>
        <TableCell>Date Ordered</TableCell>
        <TableCell></TableCell>
        
        </TableRow>
        </TableHead>


        <TableBody>

        {
            orders.length !== 0 ?(

                orders.map((order)=>(
                    <TableRow key={order.idmarket_place_order}>
                    <TableCell>{order.order_id}</TableCell>
                    <TableCell>{order.user_id}</TableCell>
                    <TableCell>{order.status}</TableCell>
                    <TableCell>{order.date_ordered}</TableCell>
                    <TableCell><Button style={{background:theme.palette.mf.main}} variant="contained" onClick={()=>processOrder(order)}>Process</Button></TableCell>
                    </TableRow>
                ))

            ):(

                <TableRow>
                <TableCell><TableCell>No Market Place Orders Found</TableCell></TableCell>
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
 
export default MarketPlaceOrders;
