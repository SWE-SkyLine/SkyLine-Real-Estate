

import PropTypes from "prop-types";

// @mui material components
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "../components/MDBoxindex";
import MDTypography from "../components/MDtypoindex";
import MDAvatar from "../components";

import ViewAuction from "../components/EditButton/ViewAuction";

function ProfilesList({ title, profiles, shadow }) {

  const renderProfiles = profiles.map(({images, label, title, auction, description, price, area, status, rooms, bathrooms, floors, link, phone, date,address, bid, maxbid}) => (
      <div key={label}  className={"Auction"} style={{ padding:"5px 15px", borderRadius:"15px"}}>
    <MDBox component="li" display="flex" alignItems="center" py={1} mb={1}>
      <MDBox mr={2}>
        <MDAvatar src={images[0]} alt="something here" shadow="md" />
      </MDBox>
      <MDBox display="flex" flexDirection="column" alignItems="flex-start" justifyContent="center">
        <MDTypography variant="h7" fontWeight="medium">
          {title}
        </MDTypography>
        <MDTypography variant="button" color="text">
          {description}
        </MDTypography>
      </MDBox>

      <MDBox ml="auto">
          <ViewAuction
              className="Button"
              style={{ padding: "8px 21px", marginRight: "0px", marginLeft: "0px" }}
           profiles={{images, label, title, auction, description, price, area, status, rooms, bathrooms, floors, link, phone, date,address, bid, maxbid}}>
              View Auction
          </ViewAuction>
      </MDBox>
    </MDBox>
      </div>

  ));

  return (
    <Card sx={{ height: "100%", boxShadow: !shadow && "none" }}>
      <MDBox pt={2} px={2}>
        <MDTypography variant="h5" fontWeight="medium" textTransform="capitalize">
          {title}
        </MDTypography>
      </MDBox>
      <MDBox>
        <MDBox  component="ul" display="flex" flexDirection="column">
          {renderProfiles}
        </MDBox>
      </MDBox>
    </Card>
  );
}


// Typechecking props for the ProfilesList
ProfilesList.propTypes = {
  title: PropTypes.string.isRequired,
    profiles: PropTypes.arrayOf(
        PropTypes.shape({
            images: PropTypes.arrayOf(PropTypes.string).isRequired,
            label: PropTypes.string.isRequired,
            title: PropTypes.string.isRequired,
            auction: PropTypes.string.isRequired,
            description: PropTypes.string.isRequired,
            price: PropTypes.string.isRequired,
            area: PropTypes.string.isRequired,
            status: PropTypes.string.isRequired,
            rooms: PropTypes.string.isRequired,
            bathrooms: PropTypes.string.isRequired,
            floors: PropTypes.string.isRequired,
            link: PropTypes.string.isRequired,
            phone: PropTypes.string.isRequired,
            date: PropTypes.string.isRequired,
            address: PropTypes.string.isRequired,
            bid:PropTypes.string.isRequired,
            maxbid:PropTypes.string.isRequired,
        })
    ).isRequired,
  shadow: PropTypes.bool,
};

export default ProfilesList;
