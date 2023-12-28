import React from "react";

const HomeButton = () => {
  const buttonStyle = {
    position: "absolute",
    top: "5px",
    left: "5px",
    zIndex: "999",
    pointerEvents: "none",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
  };

  const arrowStyle = {
    border: "solid black",
    borderWidth: "0 6px 6px 0",
    display: "inline-block",
    padding: "3px",
    transform: "rotate(135deg)",
    WebkitTransform: "rotate(135deg)",
  };

 
  return (
    <a href="/Home"  className="Button Button--primary hover:bg-blue-600">
      <span style={arrowStyle}></span> Home
    </a>
  );
};

export default HomeButton;
