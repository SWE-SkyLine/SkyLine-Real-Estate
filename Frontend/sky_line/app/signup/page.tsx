'use client'
import { useState } from "react";
import { useRef } from "react";
import style from "./page.module.css"

export default  function Verification(){

 


return(
    
  <div className={style.container}>

    <div className={style.left_img}></div>

    <div className={style.right}>
        <div className={style.logo}></div>
        <div className={style.verify_form}> 
            <span className={style.header_text}>Sign up Page</span>
            <button className={style.btn_verify}>Sign up</button>
        </div>
         
  </div>
</div>
);
};



