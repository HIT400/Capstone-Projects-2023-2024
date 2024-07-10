import React, { useEffect, useState } from 'react';
import { Grid, Box } from '@mui/material';
import PageContainer from 'src/components/container/PageContainer';

// components
import SalesOverview from './components/SalesOverview';
import YearlyBreakup from './components/YearlyBreakup';
import MonthlyEarnings from './components/MonthlyEarnings';
import { useNavigate } from 'react-router';
import getTotalPlacements from 'src/api/getTotalPlacements';
import getTotalFeedOrders from 'src/api/getTotalFeedOrders';


const Dashboard = () => {
  const logged_status = localStorage.getItem("Status");
  const company_name = localStorage.getItem("COMPANY_NAME");
  const navigate = useNavigate();
  const [placements,setPlacements] = useState("");
  const [orders,setOrders] = useState("");

  // if(!logged_status){
  //   alert("You are not logged in");    
  //   navigate("/auth/login");
  // }

  useEffect(()=>{
    getPlacements(company_name);
    getFeedOrders(company_name);
  },[])


  const getPlacements = async(company_name)=>{
    let no_placements = await getTotalPlacements(company_name);
     setPlacements(no_placements);
     console.log(no_placements);
  }

  const getFeedOrders = async(company_name)=>{
    let no_feed_orders = await getTotalFeedOrders(company_name);
    setOrders(no_feed_orders);
    console.log(no_feed_orders);

  }


  return (
    <PageContainer title="Dashboard" description="this is Dashboard">
      <Box>
        <Grid container spacing={3}>

          <Grid item xs={12} lg={8}>
            <SalesOverview placements={placements} orders={orders}/>
          </Grid>

          <Grid item xs={12} lg={4}>

            <Grid container spacing={3}>
              <Grid item xs={12}>
                <YearlyBreakup total={placements} />
              </Grid>

              <Grid item xs={12}>
                <MonthlyEarnings total={orders} />
              </Grid>

            </Grid>
          </Grid>
        </Grid>
      </Box>
    </PageContainer>
  );
};

export default Dashboard;
