

import PropTypes from "prop-types";

// @mui material components
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "../components/MDBoxindex";
import MDTypography from "../components/MDtypoindex";

import EditButton from "../components/EditButton/EditButton";
import Grid from "@mui/material/Grid";
import Rating  from "../../rating/page";
import { use, useEffect, useState } from "react";

function ProfileInfoCard({ title, info, shadow, onUpdate }) {


  const { firstName, lastName, image, role, ...restInfo } = info;

  const labels = ["fullName", ...Object.keys(restInfo)];
  const values = [`${firstName} ${lastName}`, ...Object.values(restInfo)];
  const [userId, setUserId] = useState();
 
  useEffect(
    ()=>{
      const urlParams = new URLSearchParams(window.location.search);
      const userIdFromParams = urlParams.get("id");
      setUserId(userIdFromParams)}
    ,[]);

  // Render the card info items
  const renderItems = labels.map((label, key) => (
    <MDBox key={label} display="flex" py={2} pr={3}>
      <MDTypography variant="h6" fontWeight="bold" textTransform="capitalize">
        {label}: &nbsp;
      </MDTypography>
      <MDTypography variant="h6" fontWeight="regular" color="text">
        &nbsp;{values[key]}
      </MDTypography>
    </MDBox>
  ));
  return (
    <Card marginLeft={8} sx={{ display: "flex", height: "100%", boxShadow: !shadow && "none" }}>
      <MDBox display="flex" justifyContent="space-between" alignItems="center" pt={0} px={4}>
        <MDTypography variant="h7" fontWeight="medium" textTransform="capitalize">
          {title}
        </MDTypography>
        <Grid item marginLeft={10} marginBottom={7.5}>
          <EditButton info={info} onUpdate={onUpdate} />
        </Grid>
      </MDBox>
      <MDBox p={0}>
        <MDBox opacity={10}></MDBox>
        <MDBox pt={0} px={4}>
          {renderItems}
          <Rating targetId={userId}/>
        </MDBox>
      </MDBox>
    </Card>
  );
}

// Setting default props for the ProfileInfoCard
// Typechecking props for the ProfileInfoCard
ProfileInfoCard.propTypes = {
  title: PropTypes.string.isRequired,
  description: PropTypes.string,
  info: PropTypes.shape({
    role: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    firstName: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired,
    mobile: PropTypes.string.isRequired,
    location: PropTypes.string.isRequired,
  }).isRequired,
  social: PropTypes.arrayOf(PropTypes.object),
  action: PropTypes.shape({
    route: PropTypes.string.isRequired,
    tooltip: PropTypes.string.isRequired,
  }),
  shadow: PropTypes.bool,
  onUpdate: PropTypes.func.isRequired,
};

export default ProfileInfoCard;
