
// @mui material components
import Box from "@mui/material/Box";
import { styled } from "@mui/material/styles";
import colors from "../theme/base/colors";
import boxShadow from "../theme/functions/boxShadow";
export default styled(Box)(({ theme, ownerState }) => {
  const { palette, functions, borders, boxShadows1 } = theme;
  const { variant, bgColor, color, opacity, borderRadius, shadow, coloredShadow } = ownerState;

  const { gradients, grey, red } = palette;
  const { linearGradient } = functions;
  const { borderRadius: radius } = borders;
  const { black, white, tabs, coloredShadows } = colors;

  const boxShadows = {
    xs: boxShadow([0, 2], [9, -5], black.main, 0.15),
    sm: boxShadow([0, 5], [10, 0], black.main, 0.12),
    md: `${boxShadow([0, 4], [6, -1], black.main, 0.1)}, ${boxShadow(
        [0, 2],
        [4, -1],
        black.main,
        0.06
    )}`,
    lg: `${boxShadow([0, 10], [15, -3], black.main, 0.1)}, ${boxShadow(
        [0, 4],
        [6, -2],
        black.main,
        0.05
    )}`,
    xl: `${boxShadow([0, 20], [25, -5], black.main, 0.1)}, ${boxShadow(
        [0, 10],
        [10, -5],
        black.main,
        0.04
    )}`,
    xxl: boxShadow([0, 20], [27, 0], black.main, 0.05),
    inset: boxShadow([0, 1], [2, 0], black.main, 0.075, "inset"),
    colored: {
      primary: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.primary,
          0.4
      )}`,
      secondary: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.secondary,
          0.4
      )}`,
      info: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.info,
          0.4
      )}`,
      success: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.success,
          0.4
      )}`,
      warning: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.warning,
          0.4
      )}`,
      error: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.error,
          0.4
      )}`,
      light: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.light,
          0.4
      )}`,
      dark: `${boxShadow([0, 4], [20, 0], black.main, 0.14)}, ${boxShadow(
          [0, 7],
          [10, -5],
          coloredShadows.dark,
          0.4
      )}`,
    },

    navbarBoxShadow: `${boxShadow([0, 0], [1, 1], white.main, 0.9, "inset")}, ${boxShadow(
        [0, 20],
        [27, 0],
        black.main,
        0.05
    )}`,
    sliderBoxShadow: {
      thumb: boxShadow([0, 1], [13, 0], black.main, 0.2),
    },
    tabsBoxShadow: {
      indicator: boxShadow([0, 1], [5, 1], tabs.indicator.boxShadow, 1),
    },
  };
  const { colored } = boxShadows ;

  const greyColors = {
    "grey-100": grey[100],
    "grey-200": grey[200],
    "grey-300": grey[300],
    "grey-400": grey[400],
    "grey-500": grey[500],
    "grey-600": grey[600],
    "grey-700": grey[700],
    "grey-800": grey[800],
    "grey-900": grey[900],
  };

  const validGradients = [
    "primary",
    "secondary",
    "info",
    "success",
    "warning",
    "error",
    "dark",
    "light",
  ];

  const validColors = [
    "transparent",
    "white",
    "black",
    "primary",
    "secondary",
    "info",
    "success",
    "warning",
    "error",
    "light",
    "dark",
    "text",
    "grey-100",
    "grey-200",
    "grey-300",
    "grey-400",
    "grey-500",
    "grey-600",
    "grey-700",
    "grey-800",
    "grey-900",
  ];

  const validBorderRadius = ["xs", "sm", "md", "lg", "xl", "xxl", "section"];
  const validBoxShadows = ["xs", "sm", "md", "lg", "xl", "xxl", "inset"];

  // background value
  let backgroundValue = bgColor;

  if (variant === "gradient") {
    backgroundValue = validGradients.find((el) => el === bgColor)
      ? linearGradient(gradients[bgColor].main, gradients[bgColor].state)
      : white.main;
  } else if (validColors.find((el) => el === bgColor)) {
    backgroundValue = palette[bgColor] ? palette[bgColor].main : greyColors[bgColor];
  } else {
    backgroundValue = bgColor;
  }

  // color value
  let colorValue = color;

  if (validColors.find((el) => el === color)) {
    colorValue = palette[color] ? palette[color].main : greyColors[color];
  }

  // borderRadius value
  let borderRadiusValue = borderRadius;

  if (validBorderRadius.find((el) => el === borderRadius)) {
    borderRadiusValue = radius[borderRadius];
  }

  // boxShadow value
  let boxShadowValue = "none";

  if (validBoxShadows.find((el) => el === shadow)) {
    boxShadowValue = boxShadows[shadow];
  } else if (coloredShadow) {
    boxShadowValue = colored[coloredShadow] ? colored[coloredShadow] : "none";
  }

  return {
    opacity,
    background: backgroundValue,
    color: colorValue,
    borderRadius: borderRadiusValue,
    boxShadow: boxShadowValue,
  };
});
