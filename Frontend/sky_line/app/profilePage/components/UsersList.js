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
            {filteredData.map( user => (
                <div key={user.id} className={"Auction"} style={{ padding: "5px 15px", borderRadius: "15px", backgroundColor: "white", border: "1px solid #3498db", marginTop: "2px", width: "665px" }}>
                    <MDBox component="li" display="flex" alignItems="center" py={1} mb={1}>
                        <MDBox mr={2}>
                            <MDAvatar src={"/userPhoto.webp"} alt="something here" shadow="md" />
                        </MDBox>
                        <MDBox display="flex" flexDirection="column" alignItems="flex-start" justifyContent="center">
                            <MDTypography variant="h7" fontWeight="medium">
                                {user.firstName+" "+user.lastName}
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
                                    backgroundColor: isPromoted(user.id) ? "grey" : "transparent",
                                    color: isPromoted(user.id) ? "white" : "#3498db",
                                }}
                                onClick={() => handlePromoteClick(user.id)}
                            >
                                {isPromoted(user.id) ? "Requested" : "Promote"}
                            </Button>
                        </MDBox>
                    </MDBox>
                </div>
            ))}
        </ul>
    );
}

List.propTypes = {
    input: PropTypes.string.isRequired,
};

export default List;
