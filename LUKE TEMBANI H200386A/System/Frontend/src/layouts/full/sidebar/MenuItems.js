import { Inventory, ListAlt, Receipt, ShoppingCart, SpaceDashboard, Store,FileOpen } from '@mui/icons-material';

import { uniqueId } from 'lodash';

const Menuitems = [
  

  {
    id: uniqueId(),
    title: 'Dashboard',
    icon: SpaceDashboard,
    href: '/dashboard',
  },

  {

    id:uniqueId(),
    title:"Feed Orders",
    icon: ListAlt,
    href:"/dashboard/feed-orders"

  },

  {
    id:uniqueId(),
    title:"Chick Placements",
    icon: Receipt,
    href:"/dashboard/chicks-placements"

  },

{
  id:uniqueId(),
  title:"Stock Management",
  icon:Inventory,
  href:"/dashboard/stock-management"
},

{
  id:uniqueId(),
  title:"MarketPlace Orders",
  icon:ShoppingCart,
  href:"/dashboard/market-place-orders"
}
,
{
  id:uniqueId(),
  title:"Market Place",
  icon: Store,
  href:"/dashboard/market-place"
},
{
  id:uniqueId(),
  title:"Contract Farming",
  icon: FileOpen,
  href:"/dashboard/contract-farming"

}


];

export default Menuitems;
