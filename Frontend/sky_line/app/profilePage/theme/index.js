
// @mui material components
import { createTheme} from "@mui/material/styles";

// Material Dashboard 2 React base styles
import colors from "./base/colors.js";
import typography from "./base/typography";
import borders from "./base/borders";
import globals from "./base/globals";

// Material Dashboard 2 React helper functions
import boxShadow from "./functions/boxShadow";
import hexToRgb from "./functions/hexToRgb";
import linearGradient from "./functions/linearGradient";
import pxToRem from "./functions/pxToRem";
import rgba from "./functions/rgba";

// Material Dashboard 2 React components base styles for @mui material components
import card from "./components/cardindex";
import cardMedia from "./components/cardMedia";
import button from "./components/buttonindex";
import avatar from "./components/avatar";

import container from "./components/container";

export default createTheme({
    palette: { ...colors },
    typography: { ...typography },
    borders: { ...borders },
    functions: {
        boxShadow,
        hexToRgb,
        linearGradient,
        pxToRem,
        rgba,
    },
    components: {
        MuiCssBaseline: {
            styleOverrides: {
                ...globals,
                ...container,
            },
        },
        MuiCard: { ...card },
        MuiCardMedia: { ...cardMedia },
        MuiButton: { ...button },
        MuiAvatar: { ...avatar },
    },
});
