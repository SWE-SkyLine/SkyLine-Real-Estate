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
                const posts = response.data;

                // Convert each array of bytes to a data URL for each post
                const postsWithPhotoUrls = posts.map((post) => {
                    const photoUrls = post.photosByteArray.map((photoBytes) => {
                        `data:image/jpeg;base64,${photoBytes}`
                        return `data:image/jpeg;base64,${photoBytes}`;
                    });

                    // Update the post data with the photo URLs
                    return { ...post, photosByteArray: photoUrls };
                });

                // Update the state or perform other actions with the converted data
                setPostsListData(postsWithPhotoUrls);
                console.log(postsWithPhotoUrls);
                console.log(postsListData);
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
        
      />
    </DashboardLayout> 
  );
}

export default Overview;
