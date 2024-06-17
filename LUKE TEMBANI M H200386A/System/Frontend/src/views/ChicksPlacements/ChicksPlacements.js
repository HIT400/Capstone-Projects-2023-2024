import { Button, Table, TableBody, TableCell, TableHead, TableRow, useTheme } from "@mui/material";
import { useEffect, useState } from "react";
import getPlacements from "src/api/getPlacements";
import pushApproval from "src/api/pushPlacementApproval";
import pushRevoke from "src/api/pushPlacementRevoke";
import updatePlacementStatus from "src/api/updatePlacementStatus";
import PageContainer from "src/components/container/PageContainer";
import DashboardCard from "src/components/shared/DashboardCard";


const ChicksPlacements = () => {

    let company_name = localStorage.getItem("COMPANY_NAME");
    const [placements,setPlacements] = useState([]);
    const [reloader,setReloader] = useState(false);

    const loadPlacements = async(companyName)=>{
        let placements = await getPlacements(companyName);
        setPlacements(placements);
    }


    const updatePlacement = async(placement_id, status,username)=>{
    
        if (status === "Approved"){
           let result =  window.confirm("Approve Placement ?");

           if (result){
            let result = await updatePlacementStatus(status,placement_id);
            if(result === "Success"){
                setReloader(prev => !prev);

                let notification_data = {
                    "username":username,
                    "title":"Modern Farmer",
                    "body":"Your Chicks Placement has been Approved",
                    "placement_id":placement_id,
                    "status":"accepted"
                }
                
                
                pushApproval(notification_data);
            }else{
                alert("Status Failed To Update")
            }
    
           }
           
           
        }else{
            let result =  window.confirm("Revoke Order ?");

            if (result){
             let result = await updatePlacementStatus(status,placement_id);
             if(result === "Success"){
                 setReloader(prev => !prev);

                 let notification_data_revoke = {
                    "username":username,
                    "title":"Modern Farmer",
                    "body":"Your Chicks Placement has been Revoked",
                    "placement_id":placement_id,
                    "status":"revoked"
                }
                 pushRevoke(notification_data_revoke);
             }else{
                 alert("Status Failed To Update")
             }
     
            }
        }

    }


    useEffect(()=>{
        loadPlacements(company_name)
    },[reloader]);


    return ( 
        <div>
        
        <PageContainer description="Chicks Placement">
        
        <DashboardCard title="Chicks Placement">

        <Table>
        
        <TableHead>

        <TableRow>
        <TableCell>Customer Name</TableCell>
        <TableCell>Breed</TableCell>
        <TableCell>Number Of Chicks</TableCell>
        <TableCell>Placement Status</TableCell>
        <TableCell>Date</TableCell>
        <TableCell></TableCell>
        
        </TableRow>
        </TableHead>


        <TableBody>

        {
            placements.length !== 0 ?(

                placements.map((placement)=>(
                    <TableRow key={placement.idchicks_placement}>
                    
                    <TableCell>{placement.customer_username}</TableCell>
                    <TableCell>{placement.chicks_breed}</TableCell>
                    <TableCell>{placement.chicks_quantity}</TableCell>
                    <TableCell>{placement.placement_status}</TableCell>
                    <TableCell>{placement.placement_date}</TableCell>
                    <TableCell><Button style={{backgroundColor:"green"}} variant="contained" onClick={()=>updatePlacement(placement.idchicks_placement,"Approved", placement.customer_username)}>Approve</Button></TableCell>
                    <TableCell><Button style={{backgroundColor:"red"}} variant="contained" onClick={()=>updatePlacement(placement.idchicks_placement,"Revoked",placement.customer_username)}>Decline</Button></TableCell>
                    </TableRow>
                ))

            ):(

                <TableRow>
                <TableCell>No Placements Found</TableCell>
                </TableRow>
                
            )
        }


        
        </TableBody>
        
        </Table>

        
        </DashboardCard>
        
        </PageContainer>
        
        </div>

     );
}
 
export default ChicksPlacements;