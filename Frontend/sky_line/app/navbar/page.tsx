"use client"
import React, { useState } from 'react';
import { InputGroup, Button, Container, Form, Navbar, Nav, NavDropdown, Modal, ListGroup } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import  style from'../navbar/page.module.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import Link from 'next/link';
import axios from "axios";

const NavbarComponent: React.FC = () => {
  const [showNotification, setShowNotification] = useState(false);
  const [notifications, setNotifications] = useState([]);
  const handleNotificationClick = async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const userIdFromParams = urlParams.get("id");
    const response = await axios.get(`http://localhost:8080/api/notifications?userId=${userIdFromParams}`);
    const notificationData=response.data;
    console.log(notificationData);
    console.log(response);
    setNotifications(notificationData);
    setShowNotification(!showNotification);
  };

  const handleAcceptClick = async (id : number) => {
    console.log(id);
    const response = await axios.put(`http://localhost:8080/api/notifications/update/approve?NotificationId=${id}`);

  }
  const handleRejectClick = async (id : number) => {
    const response = await axios.put(`http://localhost:8080/api/notifications/update/reject?NotificationId=${id}`);
  }


  return (
    <>
      <Navbar expand="lg" className={`${style.navbar} bg-body-tertiary fixed-top`}>
        <Container fluid>
          <Navbar.Brand className={style.brand} >SKYLINE</Navbar.Brand>
          
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll" className="justify-content-center">
            <Nav className="me-auto my-2 my-lg-0" navbarScroll>
            <Link href="/support" className={`nav-link ${style.navLink}`}>Home</Link>
            <Link href="/login" className={`nav-link ${style.navLink}`}>Rent</Link>
            <Link href="/login" className={`nav-link ${style.navLink}`}>Buy</Link>
            <Link href="/support" className={`nav-link ${style.navLink}`}>Support</Link>
            <Link href="/login" className={`nav-link ${style.navLink}`}>About Us</Link>
              {/* <Form className="d-flex">
                <InputGroup>
                  <Form.Control
                    type="search"
                    placeholder="Search"
                    className="me-2"
                    aria-label="Search"
                  />
                  <Button variant="outline-success" className="bi bi-search bi-sm"></Button>
                </InputGroup>
              </Form> */}
            </Nav>
            <Button
              variant="link"
              onClick={handleNotificationClick}
              className="position-relative"
            >
              <i className={`bi bi-bell${showNotification ? '-fill' : ''} bell-icon`} />
            </Button>
             {/* Use Link component to navigate to '/login' */}
              <Button href="/login" 
                className={`${style.btn} bi-toggle2 position-relative`}
              >
                Login
              </Button>
              <Button href="/signup" 
                className={`${style.btn} bi-toggle2 position-relative`}
              >
                Signup
              </Button>
            
          </Navbar.Collapse>
        </Container>
      </Navbar>

      <Modal show={showNotification} onHide={handleNotificationClick}>
        <Modal.Header closeButton>
          <Modal.Title>Notifications</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ListGroup>
            {notifications.map(({notificationId,responderId,date_requested,date_answered,requesterId,candidateId,message, decide})=> (

                <ListGroup.Item key={notificationId}>
                  <div>{message}</div>
                  <div style={{ display: "flex", justifyContent: "space-between", marginTop: '2px', fontSize:"10px", marginBottom:"5px" }}>
                    <div>Date Requested : {date_requested}</div>
                    <div>Date Answered : {date_answered} </div>
                      </div>
                    {!decide?(
                        <>
                        <Button style={{marginRight:"3px", fontSize:"12px", marginLeft:"301px"}} onClick={() => handleAcceptClick(notificationId)}>
                         Accept
                        </Button>
                        <Button style={{ fontSize:"12px"}} onClick={() => handleRejectClick(notificationId)}>
                          Refuse
                        </Button>
                        </>
                        ):(<></>)}

                </ListGroup.Item>
            ))}
          </ListGroup>
        </Modal.Body>
      </Modal>
    </>
  );
};

export default NavbarComponent;
