import PageContainer from "src/components/container/PageContainer";
import Navbar from "../home/Navbar";
import DashboardCard from "src/components/shared/DashboardCard";

const About = () => {
    return ( 
        <>
        
        <Navbar/>

        <PageContainer title="About Modern Farmer">

        <DashboardCard title="About Modern Farmer"/>

        <DashboardCard title="Modern Farmer was founded in 2023 with the intention of assisting Poultry Suppliers better manage their inventory as well as help poultry farmers manage invironment variables efficiently"/>
        
        
        
        </PageContainer>
        
        </>
     );
}
 
export default About;