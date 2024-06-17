import { Button, Stack, Table, TableBody, TableCell, TableHead, TableRow } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation } from "react-router";
import { ToastContainer, toast } from "react-toastify";
import getReportData from "src/api/getContractFarmingReport";
import updateStatus from "src/api/updateContractApplicationStatus";
import PageContainer from "src/components/container/PageContainer";
import DashboardCard from "src/components/shared/DashboardCard";
import "react-toastify/dist/ReactToastify.css";

const ContractFarmingView = () => {
    const location = useLocation();
    const [report,setReport] = useState(null);
    let full_name = location.state.full_name;
    let supplier = location.state.supplier_name;
    console.log(location.state);

    const getReportDataFunc = async()=>{
        let results = await getReportData(supplier,full_name);
        if(results !== "failed"){
            setReport(results);
        }
    }


    const updateStatusFunc = async(id,status)=>{
        let updateData = {
            id,
            status
        }

        let confirm = window.confirm("Update Application ?");

        if(confirm){
            let result  =  await updateStatus(updateData);

            if(result === "success"){
                toast.info("Application Updated",{
                    position:"top-center",
                    autoClose:700
                })
            }else{
                toast.error("Failed to update application, try again !",{
                    position:"top-center",
                    autoClose:700
                })
            }
        }


    }

    useEffect(()=>{
        getReportDataFunc();
    },[]);
    
    return ( 
        <PageContainer title="Contract Farming Data">

        <ToastContainer/>
        
        <DashboardCard title={"Contract Farming Data"}>

        {
            location.state.status === "pending" || location.state.status === "denied" ? (
                <DashboardCard title={"Applicant Details"}>

                <Stack maxWidth={"300px"} spacing={2}>
                <p>Applicant Full Name:{location.state.full_name}</p>
                <p>Address:2 Harare Drive</p>
                <p>Contact: 0774 975 876</p>
                
                </Stack>

                <Stack direction={"row"} maxWidth={"300px"} spacing={2} marginTop={"24px"}>
                <Button onClick={()=>updateStatusFunc(location.state.idcontract_farming_applications, "approved")} variant="contained">Accept</Button>
                <Button onClick={()=>updateStatusFunc(location.state.idcontract_farming_applications, "denied")} variant="contained">Deny</Button>
                </Stack>

                
                </DashboardCard>
            ):(
                <DashboardCard title={"Report"}>
                <Table>
                <TableHead>
                <TableRow>
                <TableCell>Mortality</TableCell>
                <TableCell>Stage</TableCell>
                <TableCell>Growers</TableCell>
                <TableCell>Finisher</TableCell>
                <TableCell>Starter</TableCell>
                <TableCell>Date</TableCell>
                </TableRow>
                </TableHead>

                <TableBody>

                {
                    report !== null ? (
                        report.map((entry)=>(
                            <TableRow key={entry.idcontract_farming_reporting}>
                            <TableCell>{entry.mortality}</TableCell>
                            <TableCell>{entry.stage}</TableCell>
                            <TableCell>{entry.growers}</TableCell>
                            <TableCell>{entry.finisher}</TableCell>
                            <TableCell>{entry.starter}</TableCell>
                            <TableCell>{entry.date.split("T")[0]}</TableCell>
                            </TableRow>
                        ))
                    ):(
                        <TableRow>
                        <TableCell>No Report Data Found !</TableCell>
                        </TableRow>
                    )
                }
                </TableBody>
                </Table>
                </DashboardCard>
            )
        }
        
        
        </DashboardCard>
        
        </PageContainer>
     );
}
 
export default ContractFarmingView;