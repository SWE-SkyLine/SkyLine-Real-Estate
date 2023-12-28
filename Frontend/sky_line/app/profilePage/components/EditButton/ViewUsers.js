"use client";

import {
  Table,
  Button,
  Checkbox,
  Label,
  Modal,
  TextInput,
} from "flowbite-react";
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
import UsersList from "../UsersList";
import axios from "axios";
import { useRouter } from "next/navigation";

function Component({ title, profiles, shadow }) {
  const [openModal, setOpenModal] = useState(false);
  const [searchResults, setSearchResults] = useState([]);
   let admin=title === "Promote To Admin";
  function onCloseModal() {
    setOpenModal(false);
  }
  const [inputText, setInputText] = useState("");
  let inputHandler = (e) => {
    //convert input text to lower case
    const lowerCase = e.target.value.toLowerCase();
    setInputText(lowerCase);
  };

  const handleSearch = async () => {
    try {
        const urlParams = new URLSearchParams(window.location.search);
        const userIdFromParams = urlParams.get("id");
        const response = await axios.get(`http://localhost:8080/api/notifications/search?query=${inputText}&Id=${userIdFromParams}`);
        const profileData = response.data;
        let photoUrl = `data:image/jpeg;base64,${profileData[0].profilePhoto}`
        profileData[0].profilePhoto = photoUrl;
         console.log(profileData[0]);
        setSearchResults(profileData);
        console.log(searchResults);
    } catch (error) {
        console.error("Error during search:", error);
    }
};


  return (
    <>
      <Button
        className="Button"
        style={{ padding: "8px 21px", marginRight: "0px", marginLeft: "0px" }}
        onClick={() => setOpenModal(true)}
      >
          {admin?"Promote Users": "Hire Agents"}
      </Button>
      <Modal
        show={openModal}
        size="xl"
        onClose={onCloseModal}
        popup
        className="Modal"
        style={{ transform: "translate(0%, -380%)" }}
      >
        <Modal.Header />
        <Modal.Body className="ModalBody">
          <div
            className="space-y-6"
            style={{
              backgroundColor: "white",
              borderRadius: "15px",
              border: "1.5px solid #3498db",
              paddingBottom: "3px",
            }}
          >
            <Card
              sx={{
                height: "100%",
                boxShadow: !shadow && "black",
                backgroundColor: "white",
              }}
            >
              <div
                className={"Auction"}
                style={{
                  padding: "5px 15px",
                  borderRadius: "10px",
                  backgroundColor: "#3498db",
                  border: "none",
                  marginTop: "0px",
                  color: "white",
                }}
              >
                <MDBox pt={1} px={25}>
                  <MDTypography
                    variant="h5"
                    fontWeight="medium"
                    textTransform="capitalize"
                    color={"white"}
                    px={3}
                  >
                    {title}
                  </MDTypography>
                </MDBox>
              </div>

              <div
                className="search"
                style={{
                  backgroundColor: "white",
                  width: "670px",
                  border: "2px solid white",
                  borderRadius: "20px",
                  marginTop: "0px",
                  marginBottom: "2px",
                }}
              >
                <TextField
                  id="outlined-basic"
                  onChange={inputHandler}
                  variant="outlined"
                  fullWidth
                  label="Search Users &#128269;"
                />
                <Button
                  className="Button"
                  style={{ marginLeft: "10px" }}
                  onClick={() => handleSearch()}
                >
                  Search
                </Button>
              </div>

              <MDBox>
                <MDBox component="ul" display="flex" flexDirection="column">
                  <div
                    style={{
                      height: "290px",
                      width: "auto",
                      overflowY: "scroll",
                      border: "none",
                      borderRadius: "15px",
                      paddingRight: "2px",
                      paddingLeft: "2px",
                      paddingTop: "0px",
                      backgroundColor: "transparent",
                    }}
                  >
                    <UsersList input={inputText} profiles={searchResults} admin={admin} />
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
    profiles: PropTypes.arrayOf(
      PropTypes.shape({
        id: PropTypes.string.isRequired,
        firstName: PropTypes.string.isRequired,
        lastName: PropTypes.string.isRequired,
        profilePhoto: PropTypes.string,
      })
    ).isRequired,
    shadow: PropTypes.bool,
  };

export default Component;
