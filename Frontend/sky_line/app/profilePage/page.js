"use client"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import Icon from "@mui/material/Icon";
import theme from "./theme";
import Profile from "./profile/profile";
import {MaterialUIControllerProvider} from "./context";

export default function Page() {
    const profile = {
        type: "collapse",
        name: "Profile",
        key: "profile",
        icon: <Icon fontSize="small">person</Icon>,
        route: "/profile",
        component: <Profile />,
    };

    return (
        <MaterialUIControllerProvider>
        <ThemeProvider theme={theme}>
            <CssBaseline />
           <Profile></Profile>
        </ThemeProvider>
            </MaterialUIControllerProvider>
    );
}
