import React, { useEffect, useState } from "react";
import MDBox from "../components/MDBoxindex";
import DashboardLayout from "../cards/Dashboard.js";
import Header from "../layout/header.js";
import profilesListData from "../data/AuctionsBidIn";
import postsListData from "../data/Posts";
import users from "../data/Users";
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
  const [postsListData, setPostsListData] = useState([]);

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


  useEffect(() => {
    const fetchProfilePosts = async (id) => {
      try {
        const response = await axios.post(
          "http://localhost:8080/api/get_posts_for_profile_with_photos",
          {
            userId: id,
          }
        );

        const profileData = response.data;
          console.log(response)
          console.log(profileData)
        // Convert each array of bytes to a data URL
        // const photoUrls = profileData.photosByteArray.map((photoBytes) => {
        //   const blob = new Blob([new Uint8Array(photoBytes)], {
        //     type: "image/jpeg",
        //   });
        //   return URL.createObjectURL(blob);
        // });

        // // Update the profile data with the photo URLs
        // profileData.photosByteArray = photoUrls;

        // Update the state or perform other actions with the converted data
        setPostsListData(profileData);
        console.log(profileData)
        console.log(PostListData)
      } catch (error) {
        console.error("Error fetching profile:", error);
      }
    };
    const urlParams = new URLSearchParams(window.location.search);
    const userIdFromParams = urlParams.get("id");

    if (userIdFromParams) {
      setUserId(userIdFromParams);
      fetchProfilePosts(userIdFromParams);
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
        users={users}
      />
    </DashboardLayout> 
  );
}

export default Overview;
