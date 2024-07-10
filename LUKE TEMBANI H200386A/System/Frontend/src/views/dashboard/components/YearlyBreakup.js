import React from 'react';
import { Grid, Typography } from '@mui/material';

import DashboardCard from '../../../components/shared/DashboardCard';

const YearlyBreakup = (props) => {
  // chart color

  return (
    <DashboardCard title="Chick Placements">
      <Grid container>
        {/* column */}
        <Grid item xs={7} sm={7}>
          <Typography variant="h3" fontWeight="700">
            {props.total}
          </Typography>
        </Grid>
      </Grid>
    </DashboardCard>
  );
};

export default YearlyBreakup;
