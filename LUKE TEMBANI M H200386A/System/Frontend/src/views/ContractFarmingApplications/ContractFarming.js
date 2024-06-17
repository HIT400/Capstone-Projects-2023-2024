import { Button, Stack, Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import { IconEye } from "@tabler/icons";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import getApplication from "src/api/getContractFarmingApplications";
import PageContainer from "src/components/container/PageContainer";
import DashboardCard from "src/components/shared/DashboardCard";

const ContractFarming = () => {

    const [applications,setApplications] = useState(null);
    const navigate = useNavigate();
    let supplier_name = localStorage.getItem("COMPANY_NAME");
    

    const viewData = (data)=>{
        navigate("/dashboard/contract-farming-data",{state:data});
    }

    const getApplicationsFunc = async()=>{

        let companyData = {
            supplier_name
        }
        let results = await getApplication(companyData);
        if(results !== "failed"){
            setApplications(results);
        }
    }

    useEffect(()=>{
        getApplicationsFunc();
    },[])


    return ( 
        <PageContainer title="Contract Farming">
        
        <DashboardCard title={"Contract Farming"}>

        <Table>
        
        <TableHead>
        <TableRow>
        <TableCell>Applicant Full Name</TableCell>
        <TableCell>Status</TableCell>
        <TableCell>Date</TableCell>
        <TableCell></TableCell>
        </TableRow>
        </TableHead>


        <TableBody>

        {
            applications !== null ? (
                applications.map((application)=>(
                    <TableRow key={application.idcontract_farming_applications}>
                    <TableCell>{application.full_name}</TableCell>
                    <TableCell>{application.status}</TableCell>
                    <TableCell>{application.date.split("T")[0]}</TableCell>
                    <TableCell><IconEye onClick={()=>viewData(application)}/></TableCell>
                    </TableRow>
                ))
            ):(
                <TableRow>
                <TableCell>No Applications Found !</TableCell>
                </TableRow>
            )
        }

        </TableBody>
        
        </Table>
        
        
        </DashboardCard>
        
        </PageContainer>
     );
}
 
export default ContractFarming;