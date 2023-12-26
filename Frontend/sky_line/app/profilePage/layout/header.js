
import { useState, useEffect } from "react";

// prop-types is a library for typechecking of props.
import PropTypes from "prop-types";

// @mui material components
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";

// Material Dashboard 2 React components
import MDBox from "../components/MDBoxindex";
import MDTypography from "../components/MDtypoindex";
import MDAvatar from "../components";

// Material Dashboard 2 React base styles
import breakpoints from "../theme/base/breakpoints";
import EditPhoto from "../components/EditButton/EditPhoto";
// Images

import ProfileInfoCard from "../cards/userInfo.js";
import ProfilesList from "../cards/userAuctions.js";
import DefaultProjectCard from "../cards/userPosts.js";
import ViewUsers from "@/app/profilePage/components/EditButton/ViewUsers";


function Header({ profileInfo, profilesListData, postsListData, onUpdate, onUpdatePhoto, users}) {
  const [tabsOrientation, setTabsOrientation] = useState("horizontal");
  const [tabValue, setTabValue] = useState(0);
  const { image, account_type, firstName, lastName, mobile, email, location } = profileInfo;
  // console.log("hahaaaaaa");
  // console.log(profileInfo);
  // console.log("first image is "+image);

  useEffect(() => {
    // A function that sets the orientation state of the tabs.
    function handleTabsOrientation() {
      return window.innerWidth < breakpoints.values.sm
          ? setTabsOrientation("vertical")
          : setTabsOrientation("horizontal");
    }

    /**
     The event listener that's calling the handleTabsOrientation function when resizing the window.
     */
    window.addEventListener("resize", handleTabsOrientation);

    // Call the handleTabsOrientation function to set the state with the initial value.
    handleTabsOrientation();

    // Remove event listener on cleanup
    return () => window.removeEventListener("resize", handleTabsOrientation);
  }, [tabsOrientation]);


  return (
      
      <MDBox key={email} position="relative" mb={5}>
        
        <MDBox
            display="flex"
            alignItems="center"
            position="relative"
            minHeight="18.75rem"
            borderRadius="xl"
            sx={{
              backgroundImage: ({ functions: { rgba, linearGradient }, palette: { gradients } }) =>
                  `${linearGradient(
                      rgba(gradients.info.main, 0.0),
                      rgba(gradients.info.state, 0.0)
                  )}, url("/profile_bg.png")`,
              backgroundSize: "cover",
              backgroundPosition: "90%",
              overflow: "hidden",
            }}
        />
        <Card
            sx={{
              position: "relative",
              mt: -8,
              mx: 3,
              py: 2,
              px: 2,
            }}
        >
          <Grid container spacing={3} alignItems="center">
            <Grid item>
              <div style={{marginTop:"4px"}}>
                <EditPhoto info={ {image, account_type, firstName, lastName, mobile, email, location} } onUpdate={onUpdatePhoto} />
              </div>
              <MDAvatar  src={image} alt="profile-image" size="xxl" shadow="sm" />
              {/*<div>{image}</div>*/}
              <Grid item marginLeft={8} marginTop={4}>
                <MDBox height="100%" mt={2} lineHeight={1}>
                  <MDTypography variant="h3" fontWeight="medium">
                    {firstName}
                  </MDTypography>
                  <MDTypography variant="h7" color="text" fontWeight="regular">
                    {account_type}
                  </MDTypography>
                </MDBox>
              </Grid>
            </Grid>
            <Grid item x={10} md={10} xl={4}>
              <ProfileInfoCard
                  title="profile information"
                  info={{ image, account_type, firstName, lastName, mobile, email, location }}
                  shadow={false}
                  onUpdate={onUpdate}
              />
            </Grid>
            <Grid item xs={12} xl={4.5}>
              <ProfilesList title="Auctions You Bid In" profiles={profilesListData} shadow={false} />
            </Grid >
            <Grid item py={55}>
              <ViewUsers profiles={users} title="Promote To Admin"></ViewUsers>
            </Grid>
          </Grid>
          <MDBox pt={0} px={2} lineHeight={1.25}>
            <MDTypography variant="h3" fontWeight="bold">
              Posts
            </MDTypography>
            <MDBox mb={1}>
              <MDTypography variant="h8" color="text">
                Take A Look At Your Posts
              </MDTypography>
            </MDBox>
          </MDBox>
          <MDBox>
            <Grid item container spacing={6}>
              <DefaultProjectCard posts={postsListData} />
            </Grid>
          </MDBox>
        </Card>
      </MDBox>
  );
}



// Typechecking props for the Header
Header.propTypes = {
  profileInfo: PropTypes.shape({
    image: PropTypes.string.isRequired,
    firstName: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    account_type: PropTypes.string.isRequired,
    mobile: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired,
    location: PropTypes.string.isRequired,
  }).isRequired,
  profilesListData: PropTypes.arrayOf(PropTypes.object).isRequired,
  postsListData: PropTypes.arrayOf(PropTypes.object).isRequired,
  children: PropTypes.node,
  onUpdate: PropTypes.func.isRequired,
  onUpdatePhoto: PropTypes.func.isRequired,

};

export default Header;
