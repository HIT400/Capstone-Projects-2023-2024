import React, { useState } from 'react';
import {  useNavigate } from 'react-router-dom';
import {
  Avatar,
  Box,
  Menu,
  Button,
  IconButton
} from '@mui/material';


import ProfileImg from 'src/assets/images/profile/account_avatar.png';

const Profile = () => {

  const navigate = useNavigate();

  const [anchorEl2, setAnchorEl2] = useState(null);
  const handleClick2 = (event) => {
    setAnchorEl2(event.currentTarget);
  };
  const handleClose2 = () => {
    setAnchorEl2(null);
  };


  const handleLogOut = ()=>{
    window.location.href = "/auth/login";
    localStorage.clear();
    
  }

  return (
    <Box>
      <IconButton
        size="large"
        aria-label="show 11 new notifications"
        color="inherit"
        aria-controls="msgs-menu"
        aria-haspopup="true"
        sx={{
          ...(typeof anchorEl2 === 'object' && {
            color: 'primary.main',
          }),
        }}
        onClick={handleClick2}
      >
        <Avatar
          src={ProfileImg}
          alt={ProfileImg}
          sx={{
            width: 35,
            height: 35,
          }}
        />
      </IconButton>
      {/* ------------------------------------------- */}
      {/* Message Dropdown */}
      {/* ------------------------------------------- */}
      <Menu
        id="msgs-menu"
        anchorEl={anchorEl2}
        keepMounted
        open={Boolean(anchorEl2)}
        onClose={handleClose2}
        anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
        transformOrigin={{ horizontal: 'right', vertical: 'top' }}
        sx={{
          '& .MuiMenu-paper': {
            width: '200px',
          },
        }}
      >

      <Box mt={1} py={1} px={2}>
      <p>Username: {
        (localStorage.getItem("Username") ? localStorage.getItem("Username") : "Invalid User" )
      }</p>
      </Box>

      
      <Box mt={1} py={1} px={2}>
      <p>Company Name: {
        (localStorage.getItem("COMPANY_NAME") ? localStorage.getItem("COMPANY_NAME") : "Invalid Company" )
      }</p>
      </Box>

        <Box mt={1} py={1} px={2}>
          <Button onClick={()=>handleLogOut()} variant="outlined" color="primary" fullWidth>
            Logout
          </Button>
        </Box>
      </Menu>
    </Box>
  );
};

export default Profile;
