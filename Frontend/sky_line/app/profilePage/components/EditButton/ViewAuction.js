"use client";

import { Table,Button, Checkbox, Label, Modal, TextInput } from "flowbite-react";
import { useEffect, useState } from "react";
import "./editModelStyle.css";
import PropTypes from "prop-types";
import CardMedia from "@mui/material/CardMedia";
import MDBox from "../MDBoxindex";

function Component({ profiles}) {
    const{ images, label, title, auction, description, price, area, status, rooms, bathrooms, floors, link, phone, date,address, bid, maxbid} = profiles;
    const [openModal, setOpenModal] = useState(false);
    const [currentImageIndex, setCurrentImageIndex] = useState(0);

    // State to track edited values
    const [editedValues, setEditedValues] = useState({
        bid:bid,

        // Add other properties as needed
    });

// State to track which cells are in edit mode
    const [editMode, setEditMode] = useState({
        bid:false,

        // Add other properties as needed
    });


    function onCloseModal() {
        setOpenModal(false);
    }

    function handleNextImage() {
        if (currentImageIndex < images.length - 1) {
            setCurrentImageIndex(currentImageIndex + 1);
        }
    }

    function handlePreviousImage() {
        if (currentImageIndex > 0) {
            setCurrentImageIndex(currentImageIndex - 1);
        }
    }

    // Function to handle the edit button click
    function handleEdit(property) {
        setEditMode((prevEditMode) => ({ ...prevEditMode, [property]: true }));
    }

    // Function to handle the save button click
    function handleSave(property) {
        // Update the postInfo with the edited value
        profiles[property] = editedValues[property];

        // Turn off edit mode
        setEditMode((prevEditMode) => ({ ...prevEditMode, [property]: false }));
    }

    // Function to handle changes in the input field
    function handleInputChange(event, property) {
        const { value } = event.target;
        setEditedValues((prevValues) => ({ ...prevValues, [property]: value }));
    }
    return (
        <>
            <Button
                className="Button"
                style={{ padding: "8px 21px", marginRight: "0px", marginLeft: "0px" }}
                onClick={() => setOpenModal(true)}
            >
                View Auction
            </Button>
            <Modal show={openModal} size="xl" onClose={onCloseModal} popup className="Modal" style={{ transform: 'translate(0%, -380%)' }}>
                <Modal.Header />
                <Modal.Body className="ModalBody">
                    <div className="space-y-6" style={{backgroundColor:"white", borderRadius:"20px"}}>
                        <MDBox
                            position="relative"
                            width="100%"
                            height="100%"
                            shadow="xl"
                            borderRadius="xl"
                        >
                            <CardMedia
                                src={images[currentImageIndex]}
                                component="img"
                                title={title}
                                sx={{
                                    maxWidth: "100%",
                                    maxHeight: "100%",
                                    margin: 0,
                                    objectFit: "cover",
                                    objectPosition: "center",
                                }}
                            />
                        </MDBox>
                        <div style={{ display: "flex", justifyContent: "space-between", marginTop: '2px' }}>
                            <Button
                                style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer" }}
                                onClick={handlePreviousImage}
                                disabled={currentImageIndex === 0}
                            >
                                &#8678; Previous Photo
                            </Button>
                            <Button
                                style={{ borderRadius: "5px", padding: "5px 17px", cursor: "pointer" }}
                                onClick={handleNextImage}
                                disabled={currentImageIndex === images.length - 1}
                            >
                                Next Photo &#8680;
                            </Button>
                        </div>
                        <div style={{height:"275px",width:"auto", overflowY:"scroll", border:"2px solid #ddd", borderRadius:"20px",paddingRight:"12px", paddingLeft:"0px", paddingTop:"0px", backgroundColor:"#ddd"}}>
                            <ul>
                        <div className="overflow-x-auto" style={{ color: "black", marginTop: "2px" }}>
                            <Table hoverable className="custom-table">

                                <Table.Body className="divide-y">
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0",width: "100px" }}>
                                            Your Bid
                                        </Table.Cell>
                                        {editMode.bid ? (
                                            <>
                                                <Table.Cell>
                                                    <div style={{ display: "flex", justifyContent: "space-between", marginTop: '2px' }}>
                                                    <TextInput
                                                        value={editedValues.bid}
                                                        onChange={(e) => handleInputChange(e, 'bid')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"450px"}}
                                                    />
                                                    <Button className="EditPostButton" onClick={() => handleSave('bid')} style={{rotate:"none"}}>
                                                        Place New Bid

                                                    </Button>
                                                    </div>
                                                </Table.Cell>



                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {bid}
                                                    <Button className="EditPostButton" onClick={() => handleEdit('bid')} style={{marginLeft:"400px", rotate:"none"}}>
                                                        New Bid
                                                    </Button>

                                                </Table.Cell>


                                            </>
                                        )}

                                    </Table.Row>
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Highest Bid
                                        </Table.Cell>

                                        <Table.Cell>
                                            {maxbid}
                                        </Table.Cell>


                                    </Table.Row>
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0",width: "100px" }}>
                                            Title
                                        </Table.Cell>
                                                <Table.Cell>
                                                    {title}
                                                </Table.Cell>



                                    </Table.Row>
                                    {/* (Description Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Description
                                        </Table.Cell>


                                                <Table.Cell>
                                                    {description}
                                                </Table.Cell>


                                    </Table.Row>
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Address
                                        </Table.Cell>

                                        <Table.Cell>
                                            {address}
                                        </Table.Cell>


                                    </Table.Row>
                                    {/* (Price Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Price
                                        </Table.Cell>


                                                <Table.Cell>
                                                    {price}
                                                </Table.Cell>


                                    </Table.Row>
                                    {/* (Area Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Area
                                        </Table.Cell>

                                                <Table.Cell>
                                                    {area}
                                                </Table.Cell>


                                    </Table.Row>
                                    {/* (Status Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Status
                                        </Table.Cell>

                                                <Table.Cell>
                                                    {status}
                                                </Table.Cell>


                                    </Table.Row>

                                    {/* (Rooms Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Rooms
                                        </Table.Cell>

                                                <Table.Cell>
                                                    {rooms}
                                                </Table.Cell>


                                    </Table.Row>

                                    {/* (Bathrooms Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Bathrooms
                                        </Table.Cell>

                                                <Table.Cell>
                                                    {bathrooms}
                                                </Table.Cell>


                                    </Table.Row>

                                    {/* Continue with other rows as needed */}

                                    {/* (Floors Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Floors
                                        </Table.Cell>

                                                <Table.Cell>
                                                    {floors}
                                                </Table.Cell>

                                    </Table.Row>

                                    {/* (Contact Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Contact
                                        </Table.Cell>

                                                <Table.Cell>
                                                    {phone}
                                                </Table.Cell>


                                    </Table.Row>

                                    {/* (Maps Link Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Maps Link
                                        </Table.Cell>

                                                <Table.Cell>
                                                    {link}
                                                </Table.Cell>

                                    </Table.Row>
                                </Table.Body>
                            </Table>
                            <div className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{fontWeight:"bolder" ,backgroundColor:" #d0d0d0", borderRadius:"2px"}}>
                                <div style={{marginLeft:"10px"}}>Posted : {date} </div>
                            </div>
                        </div>
                            </ul>
                        </div>


                    </div>
                    <div className="grid grid-cols-2 gap-16" style={{ marginTop: "5px" }}>
                        <Button className="Button" onClick={onCloseModal} style={{ padding: "8px 15px", backgroundColor:"black" , border:"white", color:"white"}}>
                            Close
                        </Button>
                    </div>
                </Modal.Body>
            </Modal>
        </>
    );
}
Component.propTypes = {
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
};

export default Component;
