import { Button, Stack, useTheme } from "@mui/material";
import { useNavigate } from "react-router";
import PageContainer from "src/components/container/PageContainer";
import DashboardCard from "src/components/shared/DashboardCard";

const StockManagement = () => {

    const theme = useTheme();

    let navigate = useNavigate();

    const gotoChicksMgt = ()=>{
        navigate("/dashboard/chicks-management")
    }


    const gotoFeedMgt = ()=>{
        navigate("/dashboard/feed-management")

    }


    return ( 
        <>
        <PageContainer title="Stock Management">

        <DashboardCard title="Stock Management">

        <Stack spacing={2}>
        
        <Button style={{background:theme.palette.mf.main}} variant="contained" onClick={()=>{gotoFeedMgt()}}>Feed Management</Button>

        <Button style={{background:theme.palette.mf.main}} variant="contained" onClick={()=>{gotoChicksMgt()}}>Chicks Management</Button>
        
        </Stack>
        
        
        </DashboardCard>


        </PageContainer>
        </>
     );
}
 
export default StockManagement;