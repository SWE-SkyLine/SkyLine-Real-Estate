"use client";

import { Table,Button, Checkbox, Label, Modal, TextInput } from "flowbite-react";
import { useEffect, useState } from "react";
import "./editModelStyle.css";
import PropTypes from "prop-types";
import CardMedia from "@mui/material/CardMedia";
import MDBox from "../MDBoxindex";
import MDTypography from "@/app/profilePage/components/MDtypoindex";

function Component({ postInfo}) {
    const{ id, publishDate, expiryDate, title, price, rent, area, description, estateType, bedroom, bathroom, level, mapLink,address, city, fullName, postCreatorUID,photosByteArray} = postInfo;
    const [openModal, setOpenModal] = useState(false);
    const [currentImageIndex, setCurrentImageIndex] = useState(0);

    // State to track edited values
    const [editedValues, setEditedValues] = useState({
        title: title,
        description: description,
        price: price,
        area: area,
        rooms: bedroom,
        bathrooms: bathroom,
        floors: level,
        link: mapLink,
        address:address
        // Add other properties as needed
    });

// State to track which cells are in edit mode
    const [editMode, setEditMode] = useState({
        title: false,
        description: false,
        price: false,
        area: false,

        rooms: false,
        bathrooms: false,
        floors: false,

        link: false,
        address: false,
        // Add other properties as needed
    });


    function onCloseModal() {
        setOpenModal(false);
    }

    function handleNextImage() {
        if (currentImageIndex < photosByteArray.length - 1) {
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
        postInfo[property] = editedValues[property];

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
                View Post
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
                                src={photosByteArray[currentImageIndex]}
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
                                disabled={currentImageIndex === photosByteArray.length - 1}
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
                                            Title
                                        </Table.Cell>
                                            {editMode.title ? (
                                                <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.title}
                                                        onChange={(e) => handleInputChange(e, 'title')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}}
                                                    />
                                                    </Table.Cell>
                                                <Table.Cell >
                                                    <Button className="EditPostButton" onClick={() => handleSave('title')} style={{rotate:"none"}}>
                                                        &#10003;

                                                    </Button>
                                                    </Table.Cell>
                                                </>
                                            ) : (
                                                <>
                                                <Table.Cell>
                                                    {title}
                                                </Table.Cell>
                                                    <Table.Cell style={{width: "50px" }}>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('title')}>
                                                        &#9998;
                                                    </Button>
                                                        </Table.Cell>
                                                </>
                                            )}
                                    </Table.Row>
                                    {/* (Description Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Description
                                        </Table.Cell>
                                        {editMode.description ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.description}
                                                        onChange={(e) => handleInputChange(e, 'description')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}} />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('description')} style={{ rotate: "none"}}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {description}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('description')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Address
                                        </Table.Cell>
                                        {editMode.address ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.address}
                                                        onChange={(e) => handleInputChange(e, 'address')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}}  />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('address')} style={{ rotate: "none"}}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {address}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('address')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>
                                    {/* (Price Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Price
                                        </Table.Cell>
                                        {editMode.price ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.price}
                                                        onChange={(e) => handleInputChange(e, 'price')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}}  />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('price')} style={{ rotate: "none"}}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {price}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('price')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>
                                    {/* (Area Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Area
                                        </Table.Cell>
                                        {editMode.area ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.area}
                                                        onChange={(e) => handleInputChange(e, 'area')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}}  />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('area')} style={{ rotate: "none"}}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {area}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('area')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>
                                    

                                    {/* (Rooms Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Rooms
                                        </Table.Cell>
                                        {editMode.rooms ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.rooms}
                                                        onChange={(e) => handleInputChange(e, 'rooms')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}} />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('rooms')} style={{ rotate: "none" }}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {bedroom}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('rooms')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>

                                    {/* (Bathrooms Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Bathrooms
                                        </Table.Cell>
                                        {editMode.bathrooms ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.bathrooms}
                                                        onChange={(e) => handleInputChange(e, 'bathrooms')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}} />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('bathrooms')} style={{ rotate: "none" }}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {bathroom}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('bathrooms')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>

                                    {/* Continue with other rows as needed */}

                                    {/* (Floors Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Floors
                                        </Table.Cell>
                                        {editMode.floors ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.floors}
                                                        onChange={(e) => handleInputChange(e, 'floors')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}} />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('floors')} style={{ rotate: "none" }}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {level}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('floors')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>

                                   
                                    {/* (Maps Link Row) */}
                                    <Table.Row className="bg-white dark:border-gray-700 dark:bg-gray-800" style={{ borderRadius: "10px" }}>
                                        <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{ fontWeight: "bolder", backgroundColor: " #d0d0d0" }}>
                                            Maps Link
                                        </Table.Cell>
                                        {editMode.link ? (
                                            <>
                                                <Table.Cell>
                                                    <TextInput
                                                        value={editedValues.link}
                                                        onChange={(e) => handleInputChange(e, 'link')}
                                                        style={{backgroundColor:"white" , border:"1px solid black",borderRadius:"8px", color:"black", padding:"8px 30px", fontsize:"50px", width:"540px"}}  />
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleSave('link')} style={{ rotate: "none" }}>
                                                        &#10003;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        ) : (
                                            <>
                                                <Table.Cell>
                                                    {mapLink}
                                                </Table.Cell>
                                                <Table.Cell>
                                                    <Button className="EditPostButton" onClick={() => handleEdit('link')}>
                                                        &#9998;
                                                    </Button>
                                                </Table.Cell>
                                            </>
                                        )}
                                    </Table.Row>
                                </Table.Body>
                            </Table>
                            <div className="whitespace-nowrap font-medium text-gray-900 dark:text-white" style={{fontWeight:"bolder" ,backgroundColor:" #d0d0d0", borderRadius:"2px"}}>
                                <div style={{marginLeft:"10px"}}>Posted : {publishDate} </div>
                            </div>
                        </div>
                            </ul>
                        </div>


                    </div>
                    <div className="grid grid-cols-2 gap-16" style={{ marginTop: "2px" }}>
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
    postInfo: PropTypes.shape({
        images: PropTypes.arrayOf(PropTypes.string).isRequired,
        title: PropTypes.string.isRequired,
        label: PropTypes.string.isRequired,
        description: PropTypes.string.isRequired,
        auction: PropTypes.string.isRequired,
    }).isRequired,
};

export default Component;
