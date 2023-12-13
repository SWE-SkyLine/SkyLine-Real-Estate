
// react-router-dom components
import { Link } from "react-router-dom";

// prop-types is a library for typechecking of props
import PropTypes from "prop-types";
import "./editStyle.css"
// @mui material components
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";

// Material Dashboard 2 React components
import MDBox from "../components/MDBoxindex";
import MDTypography from "../components/MDtypoindex";

import Grid from "@mui/material/Grid";

import ViewPost from "../components/EditButton/ViewPost";



// { image, label, title, description, action }
function DefaultProjectCard({ posts }) {


  return posts.map(({images, label, title, auction, description, price, area, status, rooms, bathrooms, floors, link, phone, date,address}) => (
    <Grid key={label} item xs={12}  xl={3}>
      <Card
        sx={{
          display: "flex",
          flexDirection: "column",
          backgroundColor: "transparent",
          boxShadow: "none",
          overflow: "visible",
        }}
      >
        <MDBox position="relative" width="100.25%" shadow="xl" borderRadius="xl">
          <CardMedia
            src={images[0]}
            component="img"
            title={title}
            sx={{
              maxWidth: "100%",
              margin: 0,
              objectFit: "cover",
              objectPosition: "center",
            }}
          />
        </MDBox>
        <MDBox mt={1} mx={0.5}>
          <MDTypography
            variant="button"
            fontWeight="regular"
            color="text"
            textTransform="capitalize"
          >
            {label}
          </MDTypography>
          <MDBox mb={1}>
              <MDTypography
                textTransform="capitalize"
              >
                {title + " "}
                <MDTypography variant="button" fontWeight={"light"}>
                  {auction}
                </MDTypography>
              </MDTypography>
          </MDBox>
          <MDBox mb={3} lineHeight={0}>
            <MDTypography variant="button" fontWeight="light" color="text">
              {description}
            </MDTypography>
          </MDBox>
          <MDBox display="flex" justifyContent="space-between" alignItems="center">
              <ViewPost postInfo={{images, label, title, auction, description, price, area, status, rooms, bathrooms, floors, link, phone, date,address}}/>
          </MDBox>
        </MDBox>
      </Card>
    </Grid>
  ));
}


// Typechecking props for the DefaultProjectCard
DefaultProjectCard.propTypes = {
    posts: PropTypes.arrayOf(
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
        })
    ).isRequired,

};

export default DefaultProjectCard;
