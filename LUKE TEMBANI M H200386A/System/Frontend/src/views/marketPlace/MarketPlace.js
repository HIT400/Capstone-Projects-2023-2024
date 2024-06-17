import { useEffect, useState } from 'react';
import { Button, Table, TableBody, TableCell, TableHead, TableRow, useTheme } from '@mui/material';
import PageContainer from 'src/components/container/PageContainer';
import DashboardCard from 'src/components/shared/DashboardCard';
import getChickensOnSale from 'src/api/getChickensOnSale';
import useWebSocket from 'react-use-websocket';
import claimChickenOrder from 'src/api/postOrderClaim';
import { useNavigate } from 'react-router';
import { ToastContainer, toast } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";
const WS_URL = 'ws://127.0.0.1:8823';

const MarketPlace = () => {
  let navigate = useNavigate();

  const theme = useTheme();

  useWebSocket(WS_URL, {
    onOpen: () => {
      console.log('Webscoket Connected sUCCESSFULLY');
    },

    onMessage: (data) => {
      setUpdateTrigger(data.data);
    },

    onClose: () => {
      console.log('Socket connection closed');
    },
    share: true,
    filter: () => false,
    retryOnError: true,
    shouldReconnect: () => true,
  });

  const placeOrder = async (idchicksforsale, customer_name) => {

    let confirmResult = window.confirm("Are you sure you want to place this order ?");

    if(confirmResult){
      let status = localStorage.getItem('Status');
      let user_id = localStorage.getItem('Username');
      let date_ordered = Date();
  
      if (status) {
        let order_data = {
          status: 'claimed',
          idchicksforsale,
          user_id,
          date_ordered,
          seller_name: customer_name,
        };
        let result = await claimChickenOrder(order_data);
  
        if (result === 'updated') {
          toast.success("Order Placed",{
            position:"top-center",
            hideProgressBar:true,
            autoClose:700
          })
          let time_stamp = Date.now();
          setUpdateTrigger('Update' + time_stamp);
        } else {
          toast.error("Failed to place order",{
            position:"top-center",
            hideProgressBar:true,
            autoClose:800
          })
        }
      } else {
        alert('You are not logged in, Please Login!');
        navigate('/auth/login');
      }
    }

  };

  const [chickens, setChickens] = useState([]);
  const [updateTrigger, setUpdateTrigger] = useState('');

  const getChickens = async () => {
    let stock = await getChickensOnSale();
    setChickens(stock);
  };

  useEffect(() => {
    getChickens();
  }, [updateTrigger]);

  return (
    <div>
      <PageContainer description="Market Place">
      <ToastContainer/>
        <DashboardCard title="Welcome to Market Place!">
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Customer Name</TableCell>
                <TableCell>Location</TableCell>
                <TableCell>Number Of Birds</TableCell>
                <TableCell>Price</TableCell>
                <TableCell>Date</TableCell>
                <TableCell>Action</TableCell>
              </TableRow>
            </TableHead>

            <TableBody>
              {chickens.length !== 0 ? (
                chickens.map((chicken) => (
                  <TableRow key={chicken.idchicksforsale}>
                    <TableCell>{chicken.customer_username}</TableCell>
                    <TableCell>{chicken.location}</TableCell>
                    <TableCell>{chicken.number_of_birds}</TableCell>
                    <TableCell>{chicken.price}</TableCell>
                    <TableCell>{chicken.date}</TableCell>
                    {chicken.status === 'available' ? (
                      <TableCell>
                        <Button
                        style={{background:theme.palette.mf.main}}
                          variant="contained"
                          onClick={() =>
                            placeOrder(chicken.idchicksforsale, chicken.customer_username)
                          }
                        >
                          Buy Chickens
                        </Button>
                      </TableCell>
                    ) : (
                      <TableCell>
                        <Button disabled>Claimed...</Button>
                      </TableCell>
                    )}
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell>No Chickens For Sale Found!</TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </DashboardCard>
      </PageContainer>
    </div>
  );
};

export default MarketPlace;
