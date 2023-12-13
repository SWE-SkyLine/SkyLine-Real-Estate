"use client"
import React, { useState } from 'react';
import { InputGroup, Button, Container, Form, Navbar, Nav, NavDropdown, Modal, ListGroup } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import  style from'../navbar/page.module.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import Link from 'next/link';

const NavbarComponent: React.FC = () => {
  const [showNotification, setShowNotification] = useState(false);

  const handleNotificationClick = () => {
    setShowNotification(!showNotification);
  };

  return (
    <>
      <Navbar expand="lg" className={`${style.navbar} bg-body-tertiary fixed-top`}>
        <Container fluid>
          <Navbar.Brand className={style.brand} >SKYLINE</Navbar.Brand>
          
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll" className="justify-content-center">
            <Nav className="me-auto my-2 my-lg-0" navbarScroll>
            <Link href="/Home" className={`nav-link ${style.navLink}`}>Home</Link>
            <Link href="/Home" className={`nav-link ${style.navLink}`}>Rent</Link>
            <Link href="/Home" className={`nav-link ${style.navLink}`}>Buy</Link>
            <Link href="/support" className={`nav-link ${style.navLink}`}>Support</Link>
            <Link href="/Home" className={`nav-link ${style.navLink}`}>About Us</Link>
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
            <ListGroup.Item>New notification 1</ListGroup.Item>
            <ListGroup.Item>New notification 2</ListGroup.Item>
            <ListGroup.Item>New notification 3</ListGroup.Item>
          </ListGroup>
        </Modal.Body>
      </Modal>
    </>
  );
};

export default NavbarComponent;
