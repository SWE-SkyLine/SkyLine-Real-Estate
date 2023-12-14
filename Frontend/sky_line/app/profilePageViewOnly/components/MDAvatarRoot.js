
// @mui material components
import Avatar from "@mui/material/Avatar";
import { styled } from "@mui/material/styles";
import colors from "../theme/base/colors";
import boxShadow from "../theme/functions/boxShadow";
export default styled(Avatar)(({ theme, ownerState }) => {
  const { palette, functions, typography, boxShadows1 } = theme;
  const { shadow, bgColor, size } = ownerState;

  const { gradients, transparent, red } = palette;
  const { pxToRem, linearGradient } = functions;
  const { size: fontSize, fontWeightRegular } = typography;
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
  // backgroundImage value
  const backgroundValue =
    bgColor === "transparent"
      ? transparent.main
      : linearGradient(gradients[bgColor].main, gradients[bgColor].state);

  // size value
  let sizeValue;

  switch (size) {
    case "xs":
      sizeValue = {
        width: pxToRem(24),
        height: pxToRem(24),
        fontSize: fontSize.xs,
      };
      break;
    case "sm":
      sizeValue = {
        width: pxToRem(36),
        height: pxToRem(36),
        fontSize: fontSize.sm,
      };
      break;
    case "lg":
      sizeValue = {
        width: pxToRem(58),
        height: pxToRem(58),
        fontSize: fontSize.sm,
      };
      break;
    case "xl":
      sizeValue = {
        width: pxToRem(300),
        height: pxToRem(300),
        fontSize: fontSize.md,
      };
      break;
    case "xxl":
      sizeValue = {
        width: pxToRem(220),
        height: pxToRem(220),
        fontSize: fontSize.md,
      };
      break;
    default: {
      sizeValue = {
        width: pxToRem(48),
        height: pxToRem(48),
        fontSize: fontSize.md,
      };
    }
  }

  return {
    background: backgroundValue,
    color: white.main,
    fontWeight: fontWeightRegular,
    boxShadow: boxShadows[shadow],
    ...sizeValue,
  };
});
