// Import necessary modules from React and Bootstrap
"use client"
import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

// Functional component definition
const SortFilter: React.FC = () => {
  // State variables using the useState hook
  const [showFormModal, setShowFormModal] = useState(false);
  const [validated, setValidated] = useState(false);
  const [rentBuyValue, setRentBuyValue] = useState<string | null>(null);
  const [area, setArea] = useState<string | null>(null);
  const [estateType, setEstateType] = useState<string>("APARTMENT");
  const [priceFrom, setPriceFrom] = useState<string>("");
  const [priceTo, setPriceTo] = useState<string>("");
  const [priceSortOrder, setPriceSortOrder] = useState<boolean>(false);
  const [areaSortOrder, setAreaSortOrder] = useState<boolean>(false);

  // Handle form submission
  const handleFormSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const form = event.currentTarget;

    if (form.checkValidity() === false) {
      event.stopPropagation();
      setValidated(true);
      return;
    }

    // Correctly extract the search query
    const searchInput = form.elements.namedItem(
      "search"
    ) as HTMLInputElement | null;
    const searchQuery = searchInput ? searchInput.value : "";

    // Send search query to the backend only if it's not null or undefined
    if (searchQuery) {
      console.log("alo");
      try {
        const searchResponse = await fetch(
          `http://localhost:8080/api/get_posts_with_photos?searchQuery=${searchQuery}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
            },
          }
        );

        if (searchResponse.ok) {
          console.log("Search request sent successfully");
        } else {
          console.error("Error sending search request");
        }
      } catch (error) {
        console.error("Error sending search request", error);
      }
    }

    // Build the filter query parameters
    const filterQueryParams = new URLSearchParams();
    filterQueryParams.append("priceFrom", priceFrom || "");
    filterQueryParams.append("priceTo", priceTo || "");
    filterQueryParams.append("estateType", estateType);
    filterQueryParams.append(
      "rent",
      rentBuyValue === "rent" ? "true" : "false"
    );

    // Send filter data to the backend with a GET request
    try {
      const filterResponse = await fetch(
        `http://localhost:8080/api/get_posts_with_photos?${filterQueryParams.toString()}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (filterResponse.ok) {
        console.log("Filter request sent successfully");
        // Handle the response as needed
      } else {
        console.error("Error sending filter request");
      }
    } catch (error) {
      console.error("Error sending filter request", error);
    }

    setValidated(false);
  };

  // Open the form modal
  const openFormModal = () => {
    setShowFormModal(true);
  };

  // Close the form modal
  const closeFormModal = () => {
    setShowFormModal(false);
    setValidated(false);
  };

  // Toggle the price sort order and send sort request to the backend
  const togglePriceSortOrder = () => {
    setPriceSortOrder(!priceSortOrder);
    sendSortRequest("price", priceSortOrder);
  };

  // Toggle the area sort order and send sort request to the backend
  const toggleAreaSortOrder = () => {
    setAreaSortOrder(!areaSortOrder);
    sendSortRequest("area", areaSortOrder);
  };

  // Send sort request to the backend
  const sendSortRequest = async (sortType: string, sortOrder: boolean) => {
    try {
      const sortResponse = await fetch(
        `http://localhost:8080/api/get_posts_with_photos?sortBy=${sortType}&sortOrder=${
          sortOrder ? "asc" : "desc"
        }`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (sortResponse.ok) {
        console.log(`Sort request for ${sortType} sent successfully`);
        // Handle the response as needed
      } else {
        console.error(`Error sending sort request for ${sortType}`);
      }
    } catch (error) {
      console.error(`Error sending sort request for ${sortType}`, error);
    }

    // Handle search button click
  };
  const handleSearchButtonClick = async () => {
    const searchInput = document.getElementsByName(
      "search"
    )[0] as HTMLInputElement | null;

    if (searchInput) {
      const searchQuery = searchInput.value.trim();

      if (searchQuery) {
        try {
          const searchResponse = await fetch(
            `http://localhost:8080/api/get_posts_with_photos?searchQuery=${searchQuery}`,
            {
              method: "GET",
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          console.log("Response Status:", searchResponse.status); // Log response status

          const data = await searchResponse.json();
          if (searchResponse.ok) {
            console.log("Search request sent successfully");
          } else {
            console.error("Error sending search request", data);
          }
        } catch (error) {
          console.error("Error sending search request", error);
        }
      }
    }
  };

  return (
    <>
      <div className="container mt-3">
        <div className="row" style={{ marginTop: "70px" }}>
          {/* Search Bar */}
          <div className="col-6">
            <div className="input-group">
              <span className="input-group-text">
                <i className="bi bi-search"></i>
              </span>
              <input
                type="text"
                className="form-control"
                placeholder="Search"
                name="search" // Assign a name to the search input
              />
              {/* Correct usage of handleFormSubmit */}
              <button
                className="btn btn-primary"
                type="button" // Change type to "button"
                onClick={handleSearchButtonClick} // Attach click event
              >
                <i className="bi bi-search"></i> Search
              </button>
            </div>
          </div>

          {/* Sort Buttons */}
          <div className="col">
            <Button variant="outline-primary" onClick={togglePriceSortOrder}>
              {priceSortOrder ? (
                <i className="bi bi-arrow-down"></i>
              ) : (
                <i className="bi bi-arrow-up"></i>
              )}{" "}
              Price
            </Button>
            <span style={{ padding: "10px" }}></span>
            <Button variant="outline-primary" onClick={toggleAreaSortOrder}>
              {areaSortOrder ? (
                <i className="bi bi-arrow-down"></i>
              ) : (
                <i className="bi bi-arrow-up"></i>
              )}{" "}
              Area
            </Button>
          </div>

          {/* Open Form Button */}
          <div className="col">
            <Button variant="primary" onClick={openFormModal}>
              Filter
            </Button>
          </div>
        </div>
      </div>

      {/* Form Modal */}
      <Modal show={showFormModal} onHide={closeFormModal}>
        <Modal.Body>
          <Form noValidate validated={validated} onSubmit={handleFormSubmit}>
            <Form.Group className="mb-3">
              {/* <Form.Label>Rent/Buy</Form.Label> */}
              <ToggleButtonGroup
                type="radio"
                name="rentBuyOptions"
                value={rentBuyValue}
                onChange={(val) => setRentBuyValue(val)}
              >
                <ToggleButton id="1" value="rent" className="custom-toggle">
                  Rent
                </ToggleButton>
                <ToggleButton id="2" value="buy" className="custom-toggle">
                  Buy
                </ToggleButton>
              </ToggleButtonGroup>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Estate Type</Form.Label>
              <Form.Control
                as="select"
                value={estateType}
                onChange={(e) => setEstateType(e.target.value)}
              >
                <option value="APARTMENT">Apartment</option>
                <option value="HOUSE">House</option>
                <option value="VILLA">Villa</option>
                <option value="LAND">Land</option>
              </Form.Control>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Price Range</Form.Label>
              <Row>
                <Col>
                  <Form.Control
                    type="number"
                    placeholder="From"
                    value={priceFrom}
                    onChange={(e) => setPriceFrom(e.target.value)}
                  />
                </Col>
                <Col>
                  <Form.Control
                    type="number"
                    placeholder="To"
                    value={priceTo}
                    onChange={(e) => setPriceTo(e.target.value)}
                  />
                </Col>
              </Row>
            </Form.Group>
            <div className="d-flex justify-content-end">
              <Button
                variant="secondary"
                className="me-2"
                onClick={closeFormModal}
              >
                Cancel
              </Button>
              <Button type="submit">Apply Filters</Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
};

// Export the component as the default export
export default SortFilter;
