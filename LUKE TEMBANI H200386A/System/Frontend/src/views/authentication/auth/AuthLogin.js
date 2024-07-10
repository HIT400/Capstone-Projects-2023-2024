import React, { useState } from 'react';
import {
    Box,
    Typography,
    FormGroup,
    FormControlLabel,
    Button,
    Stack,
    Checkbox,
   
} from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';

import CustomTextField from '../../../components/forms/theme-elements/CustomTextField';
import loginUser from 'src/api/postLogin';

const AuthLogin = ({ title, subtitle, subtext }) => {

    const [username,SetUsername] = useState("");
    const [password,setPassword] = useState("");
    let nav = useNavigate();


    const handleLogin = async(e) =>{
        e.preventDefault();
        let status = await loginUser(username,password);
        console.log("Status",status);

        if(status === "Authenticated"){
            localStorage.setItem("Status","Logged");
            localStorage.setItem("Username",username);
            nav("/dashboard");
        }else{
            alert("Login Failed!");
        }
    }


    return ( 
        <>
        {title ? (
            <Typography fontWeight="700" variant="h2" mb={1}>
                {title}
            </Typography>
        ) : null}

        {subtext}

        <form onSubmit={(e)=>handleLogin(e)}>
        
        <Stack>
        
        <Box>
            <Typography variant="subtitle1"
                fontWeight={600} component="label" htmlFor='username' mb="5px">Username</Typography>
            <CustomTextField id="username" variant="outlined" fullWidth required onChange={(e)=>SetUsername(e.target.value)}/>
        </Box>
        <Box mt="25px">
            <Typography variant="subtitle1"
                fontWeight={600} component="label" htmlFor='password' mb="5px" >Password</Typography>
            <CustomTextField id="password" type="password" variant="outlined" fullWidth required onChange={(e)=>setPassword(e.target.value)}/>
        </Box>
        <Stack justifyContent="space-between" direction="row" alignItems="center" my={2}>
        </Stack>
    </Stack>
    <Box>
        <Button
            variant="contained"
            size="large"
            fullWidth
            type="submit"
            
            sx={{
                background:"#ffa500",
                ":hover":{
                    background:"#ffa500"
                }
            }}
        >
            Sign In
        </Button>
    </Box>
        
        </form>


        {subtitle}
    </>
     );
}
 
export default AuthLogin;
