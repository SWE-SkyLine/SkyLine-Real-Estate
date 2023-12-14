import React, {useState} from 'react';
import MDBox from '../components/MDBoxindex';
import DashboardLayout from '../cards/Dashboard.js';
import Header from '../layout/header.js';
import profilesListData from '../data/AuctionsBidIn';
import postsListData from '../data/Posts';
import profileInfo from '../data/ProfileInfo.js';


function Overview() {
    const [{ image, role, firstName, lastName, mobile, email, location }, setUserInfo] = useState(profileInfo[0]);
   console.log("profile img  "+image);

    function handleUpdate(newUserInfo) {
        // Update the state with the new information
        setUserInfo(newUserInfo);
    }
    function handleUpdatePhoto(newUserInfo) {
        // Update the state with the new information
        setUserInfo(newUserInfo);
    }
    return (

        <DashboardLayout>
            <MDBox mt={1} mb={3} />
            <Header
                profileInfo={{ image, role, firstName, lastName, mobile, email, location }}
                profilesListData={profilesListData}
                postsListData={postsListData}
                onUpdate={handleUpdate}
                onUpdatePhoto={handleUpdatePhoto}
             />
        </DashboardLayout>

    );
}

export default Overview;