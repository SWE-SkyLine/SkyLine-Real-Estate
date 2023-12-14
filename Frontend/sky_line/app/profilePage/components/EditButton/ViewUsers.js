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
import { React } from "react";
import TextField from "@mui/material/TextField";
import UsersList from "@/app/profilePage/components/UsersList";
import {getClients} from "@/app/Services/NotificationService";

function Component({ title, profiles, shadow }) {
    const [openModal, setOpenModal] = useState(false);

    function onCloseModal() {
        setOpenModal(false);
    }
    const [inputText, setInputText] = useState("");
    let inputHandler = (e) => {
        //convert input text to lower case
        const lowerCase = e.target.value.toLowerCase();
        setInputText(lowerCase);
    };
    const [userProfiles, setUserProfiles] = useState([]);
    const onPromoteUsersClick = async () => {
        setOpenModal(true);
        // Call getClients to get user profiles
        try {
            const response = await getClients();
            setUserProfiles(response.data); // Assuming the user profiles are in the 'data' property of the response
        } catch (error) {
            console.error(error);
            alert("Error fetching user profiles");
        }
    };

    return (
        <>
            <Button
                className="Button"
                style={{ padding: "8px 21px", marginRight: "0px", marginLeft: "0px" }}
                onClick={onPromoteUsersClick}
            >
                Promote Users
            </Button>
            <Modal show={openModal} size="xl" onClose={onCloseModal} popup className="Modal" style={{ transform: 'translate(0%, -380%)' }}>
                <Modal.Header />
                <Modal.Body className="ModalBody">
                    <div className="space-y-6" style={{backgroundColor:"white", borderRadius:"15px" , border:"1.5px solid #3498db", paddingBottom:"3px"}}>
                    <Card sx={{ height: "100%", boxShadow: !shadow && "black", backgroundColor:"white"}}>
                        <div className={"Auction"} style={{ padding:"5px 15px", borderRadius:"10px" , backgroundColor:"#3498db", border:"none",marginTop:"0px", color:"white"}}>
                            <MDBox pt={1} px={25} >
                                <MDTypography variant="h5" fontWeight="medium" textTransform="capitalize" color={"white"} px={3}>
                                    {title}
                                </MDTypography>
                            </MDBox>
                        </div>

                        <div className="search" style={{backgroundColor:"white", width:"670px", border:"2px solid white", borderRadius:"20px" , marginTop:"0px", marginBottom:"2px"}} >
                            <TextField
                                id="outlined-basic"
                                onChange={inputHandler}
                                variant="outlined"
                                fullWidth
                                label="Search Users &#128269;"
                            />
                        </div>
                        <MDBox>
                            <MDBox  component="ul" display="flex" flexDirection="column">
                                <div style={{height:"290px",width:"auto", overflowY:"scroll", border:"none", borderRadius:"15px",paddingRight:"2px", paddingLeft:"2px", paddingTop:"0px", backgroundColor:"transparent"}}>
                                    <UsersList input={inputText} profiles={userProfiles}/>
                                </div>
                            </MDBox>
                        </MDBox>
                    </Card>
                    </div>
                </Modal.Body>

            </Modal>
        </>
    );
}
Component.propTypes = {
    title: PropTypes.string.isRequired,
    profiles: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.number.isRequired,
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