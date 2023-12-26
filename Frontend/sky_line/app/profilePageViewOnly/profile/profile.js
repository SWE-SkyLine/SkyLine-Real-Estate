import React, { useEffect, useState } from "react";
import MDBox from "../components/MDBoxindex";
import DashboardLayout from "../cards/Dashboard.js";
import Header from "../layout/header.js";
import profilesListData from "../data/AuctionsBidIn";
import postsListData from "../data/Posts";

import axios from "axios";

function Overview() {
  const [profile, setProfile] = useState({
    profile_photo: "",
    firstName: "",
    lastName: "",
    account_type: "",
    mobile: "",
    email: "",
  });
  const [pfp, setPfp] = useState("");
  const [userId, setUserId] = useState("");

  useEffect(() => {
    const fetchProfileInfoData = async (id) => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/profile/getProfile/${id}`
        );
        const profileData = response.data;

        // Update both profile and pfp in one go

        let photoUrl = `data:image/jpeg;base64,${profileData.profile_photo}`;
        // Create a data URL from the blob
        //console.log(photoUrl);
        profileData.profile_photo = photoUrl;
        //setProfile(profile);
        setProfile(profileData);
      } catch (error) {
        console.error("Error fetching profile:", error);
      }
    };

    const urlParams = new URLSearchParams(window.location.search);
    const userIdFromParams = urlParams.get("id");

    if (userIdFromParams) {
      setUserId(userIdFromParams);
      fetchProfileInfoData(userIdFromParams);
    }
  }, []);

  return (
    
    <DashboardLayout>
        
      <MDBox mt={1} mb={3} />
      <Header
     
        profileInfo={{
          image: profile.profile_photo.toString(),
          account_type: profile.account_type,
          firstName: profile.firstName,
          lastName: profile.lastName,
          mobile: profile.mobile,
          email: profile.email,
        }}
        profilesListData={profilesListData}
        postsListData={postsListData}
        // onUpdatePhoto={updateProfile}
        
      />
    </DashboardLayout> 
  );
}

export default Overview;
