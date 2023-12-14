"use client";

import { FileInput, Label } from "flowbite-react";
import { Button, Modal } from "flowbite-react";
import { useState } from "react";
import PropTypes from "prop-types";
import "./editModelStyle.css";
import MDAvatar from "../index";
function Component({ info, onUpdate }) {
  const { image, firstName, lastName, role, mobile, email, location } = info;
  const [selectedFile, setSelectedFile] = useState(null);
  const [openModal, setOpenModal] = useState(false);
  const [email1, setEmail] = useState(email);
  const [firstName1, setFirstName] = useState(firstName);
  const [mobile1, setMobile] = useState(mobile);
  const [lastName1, setLastName] = useState(lastName);
  const [location1, setLocation] = useState(location);
  const [role1, setRole] = useState(role);
  const handleFileChange = (event) => {
    // Handle file change and update selectedFile state
    const file = event.target.files[0];
    setSelectedFile(file);
  };
  const handleSetProfilePhoto = () => {
    if (selectedFile) {
      const reader = new FileReader();
      reader.onloadend = () => {

        const dataUrl = reader.result;
        onUpdate({
          image: dataUrl,
          firstName: firstName1,
          lastName: lastName1,
          role: role1,
          email: email1,
          mobile: mobile1,
          location: location1,
        });
        setOpenModal(false);
      };
      reader.readAsDataURL(selectedFile);
    } else {
      alert("Please choose a file before setting the profile photo.");

    }
  };

  return (
    <>
      <Button
        onClick={() => setOpenModal(true)}
        className="EditButton"
        style={{ marginLeft: "200px", marginTop: "20px" }}
      >
        &#9998;
      </Button>
      <Modal show={openModal} onClose={() => setOpenModal(false)} className="Modal">
        <Modal.Header></Modal.Header>
        <Modal.Body className="Body" style={{backgroundColor:"white"}}>
          <div className="flex w-full items-center justify-center ">
            {/* Hidden file input */}
            <FileInput
                id="dropzone-file"
                className="hidden"
                onChange={handleFileChange}
                style={{display:"none"}}
            />
            {/* Custom label to replace the default "Choose File" button */}
            <label
                htmlFor="dropzone-file"
                className="custom-file-label dark:hover:bg-bray-800 flex h-64 w-full cursor-pointer flex-col items-center justify-center rounded-lg border-2 border-dashed border-gray-300 bg-gray-50 hover:bg-gray-100 dark:border-gray-600 dark:bg-gray-700 dark:hover:border-gray-500 dark:hover:bg-gray-600"
            >
              <MDAvatar src="/upload1.png" alt="profile-image" size="xl" shadow="sm" style={{marginLeft: "11px"}}></MDAvatar>
              <div style={{ color: "#1085ce", marginTop: "10px", fontWeight:"bolder" , fontSize:"16px"}}>
                CLICK AND UPLOAD OR DRAG AND DROP
              </div>
              {/* Display selected file name */}
              {selectedFile && (
                  <div style={{ marginTop: "5px", color: "#1085ce" ,marginLeft: "0px"}}>
                    SELECTED FILE:
                    <div style={{ marginTop: "0px", color: "#1085ce" ,marginLeft: "0px", fontStyle:"italic"}}>
                      {selectedFile.name}
                    </div>
                  </div>

              )}
            </label>
          </div>
          <div
            className="grid grid-cols-2 gap-16"
            style={{ marginTop: "20px", marginLeft: "80px" }}
          >
            <div>
              <Button onClick={handleSetProfilePhoto} className="Button"
                      style={{ marginTop: "0px", marginLeft: "0px" }}>
                Set As Profile Photo
              </Button>
            </div>
            <div>
              <Button
                color="gray"
                onClick={() => setOpenModal(false)}
                style={{ marginTop: "10px", marginLeft: "40px" }}
                className="Button"
              >
                Cancel
              </Button>
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer></Modal.Footer>
      </Modal>
    </>
  );
}

Component.propTypes = {
  info: PropTypes.shape({
    role: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    firstName: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired,
    mobile: PropTypes.string.isRequired,
    location: PropTypes.string.isRequired,
  }).isRequired,
  onUpdate: PropTypes.func.isRequired,
};

export default Component;
