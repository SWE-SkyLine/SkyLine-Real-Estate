import React, { useState } from 'react';
import { InputGroup, Button, Container, Form, Navbar, Nav, NavDropdown, Modal, ListGroup } from 'react-bootstrap';
import { Link } from 'react-router-dom'; // Import Link from react-router-dom
import '../navbar/navbar.css';

const NavbarComponent: React.FC = () => {
  const [showNotification, setShowNotification] = useState(false);

  const handleNotificationClick = () => {
    setShowNotification(!showNotification);
  };

  return (
    <>
      <Navbar expand="lg" className="bg-body-tertiary">
        <Container fluid>
          <Navbar.Brand href="#">SKYLINE</Navbar.Brand>
          <Navbar.Toggle aria-controls="navbarScroll" />
          <Navbar.Collapse id="navbarScroll" className="justify-content-center">
            <Nav className="me-auto my-2 my-lg-0" navbarScroll>
              <Nav.Link href="/login">Home</Nav.Link>
              <Nav.Link href="/support">Support</Nav.Link>
              <Nav.Link href="/AboutUs">About Us</Nav.Link>
              <Form className="d-flex">
                <InputGroup>
                  <Form.Control
                    type="search"
                    placeholder="Search"
                    className="me-2"
                    aria-label="Search"
                  />
                  <Button variant="outline-success" className="bi bi-search bi-sm"></Button>
                </InputGroup>
              </Form>
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
                variant="outline-dark"
                className="bi bi-toggle2 position-relative"
              >
                Login
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
