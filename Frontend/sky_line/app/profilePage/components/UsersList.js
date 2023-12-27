import React, { useState } from 'react';
import MDBox from "./MDBoxindex";
import MDAvatar from "./index";
import MDTypography from "./MDtypoindex";
import { Button } from "flowbite-react";
import PropTypes from "prop-types";
import Component from "./EditButton/ViewUsers";
import axios from "axios";
import { useRouter } from "next/navigation";
function List(props) {
    const [promotedItems, setPromotedItems] = useState([]);
    let router = useRouter();
    const handlePromoteClick = async (id) => {
        if (promotedItems.includes(id)) {
            // If already promoted, remove from the list
            // setPromotedItems(promotedItems.filter((item) => item !== id));
        } else {
            // If not promoted, add to the list
            setPromotedItems([...promotedItems, id]);
            const urlParams = new URLSearchParams(window.location.search);
            const userIdFromParams = urlParams.get("id");
            console.log(userIdFromParams);
            console.log(id);
            const response = await axios.put(`http://localhost:8080/api/notifications/update/notify?requesterId=${userIdFromParams}&candidateId=${id}`);
            console.log(response);
        }
    };
    const handleVisitProfile = (userId) => {
        router.push(`/profilePage?id=${userId}`)
        location.reload();
    }


    const isPromoted = (id) => promotedItems.includes(id);

    const filteredData = props.profiles.filter((el) => {
        if (props.input === '') {
            return el;
        } else {
            return el.firstName.toLowerCase().startsWith(props.input.toLowerCase());
        }
    });

    return (
        <ul>
            {filteredData.map(({ id, profilePhoto, firstName, lastName, requested }) => (
                <div key={id} className={"Auction"} style={{ padding: "5px 15px", borderRadius: "15px", backgroundColor: "white", border: "1px solid #3498db", marginTop: "2px", width: "665px" }}>
                    <MDBox component="li" display="flex" alignItems="center" py={1} mb={1}>
                        <MDBox mr={2}>
                            <MDAvatar src={profilePhoto} alt="something here" shadow="md" />
                        </MDBox>
                        <MDBox display="flex" flexDirection="column" alignItems="flex-start" justifyContent="center">
                            <MDTypography variant="h7" fontWeight="medium">
                                {firstName} {lastName}
                            </MDTypography>
                            <Button  onClick={()=> handleVisitProfile(id)} className={"Visit"} style={{ backgroundColor: "transparent", color: "#3498db", border: "none", cursor: "pointer", textDecoration: "underline", fontStyle: "italic" }}  >
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
                                disabled={requested}
                                onClick={() => handlePromoteClick(id)}
                            >
                                {requested ? "Requested" : isPromoted(id)? "requested":"Promote"}
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
