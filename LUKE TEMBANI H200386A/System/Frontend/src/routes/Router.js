import React, { lazy } from 'react';
import { Navigate } from 'react-router-dom';
import Loadable from '../layouts/full/shared/loadable/Loadable';


/* ***Layouts**** */
const FullLayout = Loadable(lazy(() => import('../layouts/full/FullLayout')));
const BlankLayout = Loadable(lazy(() => import('../layouts/blank/BlankLayout')));

/* ****Pages***** */
const Dashboard = Loadable(lazy(() => import('../views/dashboard/Dashboard')));
const ErrorPage = Loadable(lazy(() => import('../views/authentication/Error')));
const Register = Loadable(lazy(() => import('../views/authentication/Register')));
const Login = Loadable(lazy(() => import('../views/authentication/Login')));
const Home = Loadable(lazy(()=>import("../views/home/home")));
const Payments = Loadable(lazy(()=> import('../views/Payments/Payments')));
const FeedOrders = Loadable(lazy(()=> import('../views/FeedOrders/FeedOrders')));
const FeedOrderLines = Loadable(lazy(()=> import('../views/FeedOrders/FeedOrderLines')));
const ChicksPlacements = Loadable(lazy(()=> import('../views/ChicksPlacements/ChicksPlacements')));
const MarketPlaceOrders = Loadable(lazy(()=> import('../views/MarketPlaceOrders/MarketPlaceOrders')));
const StockManagement = Loadable(lazy(()=> import('../views/StockManagement/StockManagement')));
const FeedManagement = Loadable(lazy(()=> import('../views/FeedManagement/feedManagement')));
const ChicksManagement = Loadable(lazy(()=> import('../views/ChicksManagement/chicksManagement')));
const MarketPlace = Loadable(lazy(()=> import('../views/marketPlace/MarketPlace')));
const About = Loadable(lazy(()=> import('../views/about/About')));
const MarketPlaceOrderManagement = Loadable(lazy(()=> import("../views/MarketPlaceOrderManagement/MarketPlaceOrderManagement")));
const ContractFarmingApplications = Loadable(lazy(()=>import("../views/ContractFarmingApplications/ContractFarming")));
const ContractFarmingView = Loadable(lazy(()=>import("../views/ContractFarmingView/ContractFarmingView")));

const Router = [

  {
    path:"/",
    element: <BlankLayout/>,
    children:[
      {
        path: "/",
        element:<Home/>
      },

      {
        path:"/about",
        element:<About/>
      }
    ]

  }
  ,

  {
    path: '/dashboard',
    element: <FullLayout />,
    children: [
      { path: '/dashboard', exact: true, element: <Dashboard /> },
      { path: '/dashboard/feed-orders', exact: true, element: <FeedOrders/> },
      { path: '/dashboard/feed-order-lines', exact: true, element: <FeedOrderLines/> },
      { path: '/dashboard/chicks-placements', exact: true, element: <ChicksPlacements/> },
      { path: '/dashboard/stock-management', exact: true, element: <StockManagement/> },
      { path: '/dashboard/payments', exact: true, element: <Payments/> },
      { path: '/dashboard/market-place-orders', exact: true, element: <MarketPlaceOrders/> },
      { path: '/dashboard/feed-management', exact: true, element:<FeedManagement/> },
      { path: '/dashboard/chicks-management', exact: true, element:<ChicksManagement/> },
      { path: '/dashboard/market-place', exact: true, element:<MarketPlace/> },
      { path: '/dashboard/market-place-order-management', exact: true, element:<MarketPlaceOrderManagement/> },
      { path: '/dashboard/contract-farming', exact: true, element:<ContractFarmingApplications/> },
      { path: '/dashboard/contract-farming-data', exact: true, element:<ContractFarmingView/> },
      { path: '*', element: <Navigate to="/dashboard/auth/404" /> },
    ],
  },
  {
    path: '/auth',
    element: <BlankLayout />,
    children: [
      { path: '404', element: <ErrorPage /> },
      { path: '/auth/register', element: <Register /> },
      { path: '/auth/login', element: <Login /> },
      { path: '*', element: <Navigate to="/auth/404" /> },
    ],
  },
];

export default Router;
