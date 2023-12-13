"use client";

import { Table,Button, Checkbox, Label, Modal, TextInput } from "flowbite-react";
import { useEffect, useState } from "react";
import "./editModelStyle.css";
import PropTypes from "prop-types";
import CardMedia from "@mui/material/CardMedia";
import MDBox from "../MDBoxindex";
import MDAvatar from "../index";
import MDTypography from "../MDtypoindex";
import ViewAuction from "./ViewAuction";
import Card from "@mui/material/Card";

function Component({ title, profiles, shadow }) {
    const [openModal, setOpenModal] = useState(false);
    const renderProfiles = profiles.map(({images, label, title, auction, description, price, area, status, rooms, bathrooms, floors, link, phone, date,address, bid, maxbid}) => (
        <div key={label}  className={"Auction"} style={{ padding:"5px 15px", borderRadius:"15px" , backgroundColor:"white", border:"1px solid #3498db",marginTop:"1px"}}>
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
                    <Button
                        className="Button"
                        style={{ padding: "8px 21px", marginRight: "0px", marginLeft: "0px" }}
                       >
                        Promote
                    </Button>
                </MDBox>
            </MDBox>
        </div>

    ));


    function onCloseModal() {
        setOpenModal(false);
    }

    return (
        <>
            <Button
                className="Button"
                style={{ padding: "8px 21px", marginRight: "0px", marginLeft: "0px" }}
                onClick={() => setOpenModal(true)}
            >
                Promote Users
            </Button>
            <Modal show={openModal} size="xl" onClose={onCloseModal} popup className="Modal" style={{ transform: 'translate(0%, -380%)' }}>
                <Modal.Header />
                <Modal.Body className="ModalBody">
                    <Card sx={{ height: "100%", boxShadow: !shadow && "black", backgroundColor:"white"}}>
                        <div className={"Auction"} style={{ padding:"5px 15px", borderRadius:"15px" , backgroundColor:"#3498db", border:"1px solid #3498db",marginTop:"1px", color:"white"}}>
                        <MDBox pt={1} px={30}>
                            <MDTypography variant="h5" fontWeight="medium" textTransform="capitalize" color={"white"}>
                                {title}
                            </MDTypography>
                        </MDBox>
                            </div>
                        <MDBox>
                            <MDBox  component="ul" display="flex" flexDirection="column">
                                {renderProfiles}
                            </MDBox>
                        </MDBox>
                    </Card>

                </Modal.Body>

            </Modal>
        </>
    );
}
Component.propTypes = {
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

export default Component;
