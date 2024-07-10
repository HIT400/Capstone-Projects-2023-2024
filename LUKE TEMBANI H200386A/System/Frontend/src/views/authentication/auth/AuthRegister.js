import React, { useState } from 'react';
import { Box, Typography, Button } from '@mui/material';

import CustomTextField from '../../../components/forms/theme-elements/CustomTextField';
import { Stack } from '@mui/system';
import RegisterUser from 'src/api/postRegister';
import {ToastContainer, toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';



const AuthRegister = ({ title, subtitle, subtext }) => {
    const [company_name, setCompany_name] = useState("");
    const [email,setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [address,setAddress] = useState("");
    const [contact_number, setContact_number] = useState("");
    const [password,setPassword] = useState("");


    const handleSubmit = async(e) => {
        e.preventDefault();

        toast.success("Good",{
            position:"top-center",
            hideProgressBar:true,
            autoClose:700
        });

        const req_body = {
            company_name,
            email,
            username,
            address,
            contact_number,
            password
        }

        console.log(req_body);

        let status = await RegisterUser(req_body);

        console.log(status);

    }


    return ( 
        <>
        {title ? (
            <Typography fontWeight="700" variant="h2" mb={1}>
                {title}
            </Typography>
        ) : null}

        {subtext}

        <Box>

        <form onSubmit={(e)=>handleSubmit(e)}>

        <ToastContainer/>

        <Stack mb={3}>

        <Typography variant="subtitle1"
        fontWeight={600} component="label" htmlFor='name' mb="5px">Company Name</Typography>
        <CustomTextField id="name" variant="outlined" fullWidth required onChange={(e)=>setCompany_name(e.target.value)} />

        <Typography variant="subtitle1"
        fontWeight={600} component="label" htmlFor='email' mb="5px" mt="25px">Email Address</Typography>
        <CustomTextField id="email" variant="outlined" fullWidth required onChange={(e)=>setEmail(e.target.value)} />


        <Typography variant="subtitle1"
        fontWeight={600} component="label" htmlFor='username' mb="5px">Username</Typography>
        <CustomTextField id="username" variant="outlined" fullWidth required onChange={(e)=>setUsername(e.target.value)} />

        <Typography variant="subtitle1"
        fontWeight={600} component="label" htmlFor='address' mb="5px">Address</Typography>
        <CustomTextField id="address" variant="outlined" fullWidth required onChange={(e)=>setAddress(e.target.value)} />

        <Typography variant="subtitle1"
        fontWeight={600} component="label" htmlFor='contact' mb="5px">Contact Number</Typography>
        <CustomTextField id="contact" variant="outlined" fullWidth required onChange={(e)=>setContact_number(e.target.value)} />

        <Typography variant="subtitle1"
        fontWeight={600} component="label" htmlFor='password' mb="5px" mt="25px">Password</Typography>
        <CustomTextField id="password" variant="outlined" type="password" fullWidth required onChange={(e)=>setPassword(e.target.value)} />
        </Stack>
        <Button sx={{
            background:"#ffa500",
            ":hover":{
                background:"#ffa500"
            }
        }} variant="contained" size="large" fullWidth type='submit'>
        Sign Up
        </Button>

        </form>

        </Box>
        {subtitle}
    </>
     );
}
 
export default AuthRegister;
