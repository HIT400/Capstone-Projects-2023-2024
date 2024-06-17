import { CheckBox } from "@mui/icons-material";
import { Dialog, DialogActions, DialogContent, TextField, Button, Table, TableBody, TableCell, TableHead, TableRow, DialogTitle, Stack, useTheme } from "@mui/material";
import { useEffect, useState } from "react";
import getStock from "src/api/getFeedStock";
import postStock from "src/api/postNewStock";




const ChicksManagement = () => {
    const theme = useTheme();
    let stock_type="chicks";
    const [chicksStock,setChicksStock] = useState([]);
    const [open, setOpen] = useState(false);
    const [productName, setProductName] = useState("");
    const [quantity, setQuantity] = useState("");
    const [price,setPrice] = useState("");
    const company_name = localStorage.getItem("COMPANY_NAME");

    useEffect(()=>{
        getFeedStock(company_name,stock_type);
    },[]);

    const addNew = ()=>{
        setOpen(true);
    }

    const handleClose = () => {
        setOpen(false);
      };

      const saveStock = async()=>{

        let product = {
            company_name,
            product_name:productName,
            quantity,
            usd_price:price,
            type:stock_type
            
        }


        let status = await postStock(product);

        if(status === "Success"){
        alert("Product Saved");
        handleClose();
        }else{
            alert("Add New Product Failed!");
        }

    }
  


    const getFeedStock = async(company_name,stock_type)=>{
        let chicks_stock = await getStock(company_name,stock_type);
        setChicksStock(chicks_stock);
    }


    return ( 
        <>
        <div style={{display:"flex", flexDirection:"row"}}>

        <div>

        <Dialog open={open} onClose={()=>handleClose()}>
        
        <DialogTitle>Add New Chicks</DialogTitle>

        <DialogContent>
    
        <TextField
        autoFocus
        margin="dense"
        label="Chicks Breed"
        type="text"
        onChange={(e)=>setProductName(e.target.value)}
        fullWidth
        variant="standard"
        required
        />

        <TextField
        margin="dense"
        label="Quantity"
        type="text"
        onChange={(e)=>setQuantity(e.target.value)}
        fullWidth
        variant="standard"
        required
        />

        <TextField
        margin="dense"
        label="Price (USD)"
        type="text"
        onChange={(e)=>setPrice(e.target.value)}
        fullWidth
        variant="standard"
        required
        />

        </DialogContent>
       
        <DialogActions>

        <Button onClick={()=>handleClose()}>Cancel</Button>
        <Button onClick={()=>saveStock()}>Save</Button>
        
        </DialogActions>
        </Dialog>
    
        </div>

        <Stack direction="row" spacing={2}>
        
        <Button variant="contained" onClick={()=>addNew()}>Add New</Button>

        <Button style={{backgroundColor:theme.palette.mf.main}} variant="contained">Update</Button>

        <Button  variant="contained" style={{backgroundColor:"red"}}>Delete All</Button>
        
        </Stack>



        </div>
        <h2>Chicks Management</h2>

        <div>

        <Table>

        <TableHead>
        <TableRow>
        <TableCell></TableCell>
        <TableCell>Product Name</TableCell>
        <TableCell>Quantity</TableCell>
        <TableCell>Price - (USD)</TableCell>
        </TableRow>
        </TableHead>

        <TableBody>

        {
            chicksStock.length !== 0 ?
            (
                chicksStock.map((chicks_stock_line)=>(
                    <TableRow key={chicks_stock_line.idcompany_stock}>
                    <TableCell><CheckBox/></TableCell>
                    <TableCell>{chicks_stock_line.product_name}</TableCell>
                    <TableCell>{chicks_stock_line.quantity}</TableCell>
                    <TableCell>{chicks_stock_line.usd_price}</TableCell>
                    </TableRow> 
                ))

            ):(
                <TableRow><TableCell>No Chicks Stock Found</TableCell></TableRow>
            )
        }


        </TableBody>
        
        
        </Table>


        
        </div>
        
        </>
     );
}
 
export default ChicksManagement;