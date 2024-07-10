import React from 'react'
import { 
    Box,
    Grid,
    styled,
    Typography,
} from '@mui/material'
import Title from './Title'
// img
import imgDetail from '../../assets/images/chickens.jpg';
import imgDetail2 from '../../assets/images/poultry.jpg';


const GetStarted = () => {

    const CustomGridItem = styled(Grid) ({
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
    })
    
    const CustomTypography = styled(Typography) ({
        fontSize: '1.1rem',
        textAlign: 'start',
        lineHeight: '1.5',
        color: '#515151',
        marginTop: '1.5rem',
    })

    return (
            
        <Grid container spacing={{ xs: 4, sm: 4, md: 0 }}   
        sx={{
            py: 10,
            px: 2,
             
        }}
        >
            <CustomGridItem item xs={12} sm={8} md={6} 
            component = 'section'
           
            >
                <Box component='article'
                sx={{
                    px: 4,
                }}
                >
                    <Title
                    text={
                        'Optimal Growth Conditions'
                    }
                    textAlign={'start'}
                    />
                    <CustomTypography>
                    To maximize broiler growth, farmers provide controlled environments with proper nutrition, lighting, and ventilation.<br/>
                    Modern broiler houses are equipped with advanced technology to monitor and adjust these factors, ensuring optimal conditions for the birds to reach market weight in a relatively short span, typically around 6 to 7 weeks.
                    </CustomTypography> 
                </Box>

            </CustomGridItem>
            
            <Grid item xs={12} sm={4} md={6}>
                <img src={imgDetail} alt="" 
                style={{
                    width: '100%',
                }}
                />
            </Grid>

            <Grid item xs={12} sm={4} md={6}
            sx={{
                order: {xs: 4, sm: 4, md: 3}
            }}
            >
                <img src={imgDetail2} alt="" 
                style={{ 
                    width: "100%",
                }}
                />
            </Grid>

            <CustomGridItem item xs={12} sm={8} md={6}
            sx={{
                order: {xs: 3, sm: 3, md: 4}
            }}
            >
                <Box component='article'
                sx={{
                    px: 4,
                }}
                >
                    <Title
                    text={
                        'Economic Importance'
                        
                    }
                    textAlign={'start'}
                    />
                    <CustomTypography>
                    Broiler production plays a crucial role in the global poultry industry, meeting the high demand for affordable and protein-rich meat. <br/>
                    The efficient growth of broilers has made chicken one of the most consumed meats worldwide, contributing significantly to the economies of many countries and providing a sustainable source of protein for a growing population.
                    </CustomTypography>
                </Box>
            </CustomGridItem>
        </Grid>
    )
}

export default GetStarted;