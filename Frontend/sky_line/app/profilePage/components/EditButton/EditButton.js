"use client";

import { Button, Checkbox, Label, Modal, TextInput } from "flowbite-react";
import { useEffect, useState } from "react";
import "./editModelStyle.css";
import PropTypes from "prop-types";

function Component({ info, onUpdate }) {
  const { image, firstName, lastName, role, mobile, email, location } = info;
  const [openModal, setOpenModal] = useState(false);
  const [image1, setImage] = useState(image);
  const [email1, setEmail] = useState(email);
  const [firstName1, setFirstName] = useState(firstName);
  const [mobile1, setMobile] = useState(mobile);
  const [lastName1, setLastName] = useState(lastName);
  const [location1, setLocation] = useState(location);
  const [role1, setRole] = useState(role);

  function onCloseModal() {
    setOpenModal(false);
  }
  function onSave() {

    const urlParams = new URLSearchParams(window.location.search);
    const userIdFromParams = urlParams.get("id");
    const data = {
      
      firstName: firstName1,
      lastName: lastName1,
      role: role1,
      email: email1,
      mobile: mobile1
    };
    console.log(userIdFromParams)
    fetch(`http://localhost:8080/api/profile/${userIdFromParams}/updateProfileInfo`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
     
    })
      .then(response => response.text())
      .then(result => {
        console.log(result);
        // Handle success response
      })
      .catch(error => {
        console.error('Error updating profile information:', error);
        // Handle error
      });
    // Close the modal
    setOpenModal(false)
  }

  Component.defaultProps = {
    onUpdate: () => {},
  };


  return (
    <>
      <Button onClick={() => setOpenModal(true)} className="EditButton">
        &#9998;
      </Button>
      <Modal show={openModal} size="md" onClose={onCloseModal} popup className="Modal">
        <Modal.Header />
        <Modal.Body className="Body">
          <div className="space-y-6">
            <h3 className="Label" style={{fontSize:"30px"}}>
              Profile Information
            </h3>
            <div>
              <div className="mb-2 block">
                <Label htmlFor="Name" value="First Name" className="Label" />
              </div>
              <TextInput
                id="First Name"
                value={firstName1}
                onChange={(event) => setFirstName(event.target.value)}
                required
                style={{
                  padding: "10px",
                  border: "none",
                  outline: "none",
                  borderRadius: "5px",
                  boxShadow: "none",
                  width: "100%",
                }}
                className="TextInput"
              />
            </div>
            <div>
              <div className="mb-2 block">
                <Label htmlFor="Name" value="Last Name" className="Label" />
              </div>
              <TextInput
                id="Last Name"
                value={lastName1}
                onChange={(event) => setLastName(event.target.value)}
                required
                style={{
                  padding: "10px",
                  border: "none",
                  outline: "none",
                  borderRadius: "5px",
                  boxShadow: "none",
                  width: "100%",
                }}
                className="TextInput"
              />
            </div>
            <div>
              <div className="mb-2 block">
                <Label htmlFor="Name" value="Email" className="Label" />
              </div>
              <TextInput
                id="Email"
                value={email1}
                onChange={(event) => setEmail(event.target.value)}
                required
                style={{
                  padding: "10px",
                  border: "none",
                  outline: "none",
                  borderRadius: "5px",
                  boxShadow: "none",
                  width: "100%",
                }}
                className="TextInput"
              />
            </div>
            <div>
              <div className="mb-2 block">
                <Label htmlFor="Name" value="Mobile" className="Label" />
              </div>
              <TextInput
                id="Mobile"
                value={mobile1}
                onChange={(event) => setMobile(event.target.value)}
                required
                style={{
                  padding: "10px",
                  border: "none",
                  outline: "none",
                  borderRadius: "5px",
                  boxShadow: "none",
                  width: "100%",
                }}
                className="TextInput"
              />
            </div>
            <div>
              <div className="mb-2 block">
                <Label htmlFor="Name" value="Location" className="Label" />
              </div>
              <TextInput
                id="Location"
                value={location1}
                onChange={(event) => setLocation(event.target.value)}
                required
                style={{
                  padding: "10px",
                  border: "none",
                  outline: "none",
                  borderRadius: "5px",
                  boxShadow: "none",
                  width: "100%",
                }}
                className="TextInput"
              />
            </div>
            <div>
              <div className="mb-2 block">
                <Label htmlFor="Password" value="Old Password" className="Label" />
              </div>
              <TextInput
                id="Old Password"
                placeholder=""
                value={null}
                onChange={(event) => setEmail(event.target.value)}
                required
                style={{
                  padding: "10px",
                  border: "none",
                  outline: "none",
                  borderRadius: "5px",
                  boxShadow: "none",
                  width: "100%",
                }}
                className="TextInput"
              />
            </div>
            <div>
              <div className="mb-2 block">
                <Label htmlFor="Name" value="New Password" className="Label" />
              </div>
              <TextInput
                id="New Password"
                placeholder=""
                value={null}
                onChange={(event) => setEmail(event.target.value)}
                required
                style={{
                  padding: "10px",
                  border: "none",
                  outline: "none",
                  borderRadius: "5px",
                  boxShadow: "none",
                  width: "100%",
                }}
                className="TextInput"
              />
            </div>
          </div>
          <div className="grid grid-cols-2 gap-16" style={{ marginTop: "20px" }}>
            <Button
              className="Button"
              onClick={onSave}
              style={{ padding: "8px 21px", marginRight: "8px", marginLeft: "55px" }}
            >
              Save
            </Button>
            <Button className="Button" onClick={onCloseModal} style={{ padding: "8px 15px" }}>
              Cancel
            </Button>
          </div>
        </Modal.Body>
      </Modal>
    </>
  );
}
Component.propTypes = {
  info: PropTypes.shape({
    firstName: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    email: PropTypes.string.isRequired,
    mobile: PropTypes.string.isRequired
  }).isRequired,
  onUpdate: PropTypes.func.isRequired,
};

export default Component;
