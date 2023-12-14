import React, { useState } from 'react';
import MDBox from "./MDBoxindex";
import MDAvatar from "./index";
import MDTypography from "./MDtypoindex";
import { Button } from "flowbite-react";
import PropTypes from "prop-types";
import Component from "./EditButton/ViewUsers";

function List(props) {
    const [promotedItems, setPromotedItems] = useState([]);

    const handlePromoteClick = (id) => {
        if (promotedItems.includes(id)) {
            // If already promoted, remove from the list
            setPromotedItems(promotedItems.filter((item) => item !== id));
        } else {
            // If not promoted, add to the list
            setPromotedItems([...promotedItems, id]);
        }
    };

    const isPromoted = (id) => promotedItems.includes(id);

    const filteredData = props.profiles.filter((el) => {
        if (props.input === '') {
            return el;
        } else {
            return el.title.toLowerCase().startsWith(props.input.toLowerCase());
        }
    });

    return (
        <ul>
            {filteredData.map(({ id, images, label, title, auction, description, price, area, status, rooms, bathrooms, floors, link, phone, date, address, bid, maxbid }) => (
                <div key={id} className={"Auction"} style={{ padding: "5px 15px", borderRadius: "15px", backgroundColor: "white", border: "1px solid #3498db", marginTop: "2px", width: "665px" }}>
                    <MDBox component="li" display="flex" alignItems="center" py={1} mb={1}>
                        <MDBox mr={2}>
                            <MDAvatar src={images[0]} alt="something here" shadow="md" />
                        </MDBox>
                        <MDBox display="flex" flexDirection="column" alignItems="flex-start" justifyContent="center">
                            <MDTypography variant="h7" fontWeight="medium">
                                {title}
                            </MDTypography>
                            <Button className={"Visit"} style={{ backgroundColor: "transparent", color: "#3498db", border: "none", cursor: "pointer", textDecoration: "underline", fontStyle: "italic" }}>
                           Visit Profile
                            </Button>
                        </MDBox>
                        <MDBox ml="auto">
                            <Button
                                className="Button"
                                style={{
                                    padding: "8px 21px",
                                    marginRight: "0px",
                                    marginLeft: "0px",
                                    backgroundColor: isPromoted(id) ? "grey" : "transparent",
                                    color: isPromoted(id) ? "white" : "#3498db",
                                }}
                                onClick={() => handlePromoteClick(id)}
                            >
                                {isPromoted(id) ? "Requested" : "Promote"}
                            </Button>
                        </MDBox>
                    </MDBox>
                </div>
            ))}
        </ul>
    );
}

List.propTypes = {
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
            bid: PropTypes.string.isRequired,
            maxbid: PropTypes.string.isRequired,
        })
    ).isRequired,
    input: PropTypes.string.isRequired,
};

export default List;
