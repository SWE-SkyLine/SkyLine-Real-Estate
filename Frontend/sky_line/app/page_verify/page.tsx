'use client'
import { useEffect, useState } from "react";
import { useRef } from "react";
import style from "./page.module.css"
 import {verify_code_request,send_code_again } from "../Services/UserSignupService";
import { AxiosResponse } from "axios";
import { useRouter } from "next/navigation";
import { SP } from "next/dist/shared/lib/utils";
import { Span } from "next/dist/trace";
import { set } from "react-hook-form";
import { Popup_respone } from "../Utility/Popup/Popup";

export default  function Verification(){
    let [user_Email,set_email] = useState("");
    let router =useRouter();
    useEffect(() => {
      // Access the query parameter from the URL
      const urlParams = new URLSearchParams(window.location.search);
      const email = urlParams.get('Email');
  
      if (email == null || !email.includes('@')) {
        alert('Invalid email format');
      } else {
        set_email(email);
      }
    }, []);

  const [inputValue] = useState(Array(5).fill(''));
  const [flag_boxs,setflag_boxs] = useState(0);
 
  const select0 =useRef<HTMLInputElement>(null)
  const select1 =useRef<HTMLInputElement>(null)
  const select2 =useRef<HTMLInputElement>(null)
  const select3 =useRef<HTMLInputElement>(null)
  const select4 =useRef<HTMLInputElement>(null)
  const select_box=[select0,select1,select2,select3,select4]

  const [showModal, setShowModal] = useState(false);
  const handleShow = () => setShowModal(true);

  let [title,settitle]=useState("Verify Email Failed")
  let [body,setbody]=useState("Please double-check the code in your email and try again.")
  let btn_text="Close"
   function btn_action() {
    setShowModal(false);
  }
 const handleInputChange = (e:any) => {
     let id= parseInt(e.target.id)
     inputValue[id]=e.target.value
     console.log(inputValue)
     setflag_boxs(0);
      if(e.target.value ==""){
         return
     }
     
     if(id<4){
      select_box[id+1].current?.focus();
     }
     
    //  console.log(inputValue.join(''))
 };

 const verify=async ()=>{
  let code = inputValue[0]+""+inputValue[1]+""+inputValue[2]+""+inputValue[3]+""+inputValue[4];
  if(inputValue[0]==""||inputValue[1]==""||inputValue[2]==""||inputValue[3]==""||inputValue[4]==""){
    setflag_boxs(1)
    return
  }
  

  const res = await verify_code_request( user_Email, code);
          if ((res as AxiosResponse).status == 200) {
          
            router.push(`/login`);
          } else {
              handleShow();
            }
 }

 const send_code=async ()=>{
  const res = await send_code_again( user_Email);
          if ((res as AxiosResponse).status == 200) {
            settitle("Send Code Again")
            setbody("code sent successfully")
            handleShow();
          }
          else{
            //popup
            settitle("Send Code Again")
            setbody("Code delivery failed. Please check your connection and try again.");
            handleShow();
          } 
 }


    return(
    
  <div className={style.container}>

    <div className={style.left_img}></div>

    <div className={style.right}>
        <div className={style.logo}></div>
        <div className={style.verify_form}> 
            <span className={style.header_text}>Verify Your Email</span>
            <div className={style.boxs}>
              <Input_box  select_box={select_box} id={0} handleInputChange={handleInputChange}/>
              <Input_box  select_box={select_box} id={1} handleInputChange={handleInputChange}/>
              <Input_box  select_box={select_box} id={2} handleInputChange={handleInputChange}/>
              <Input_box  select_box={select_box} id={3} handleInputChange={handleInputChange}/>
              <Input_box  select_box={select_box} id={4} handleInputChange={handleInputChange}/>
            </div>
            {flag_boxs==1&&<span className={style.error}>All Field are Required</span>}   
            <button className={style.btn_verify} onClick={verify}>Verify</button>
            <label className={style.lable} onClick={send_code}>Send Again</label>
        </div>
         
  </div>
  <Popup_respone showModal={showModal} setShowModal={setShowModal}
  title={title} body={body} btn_text={btn_text} btn_action={btn_action}
 />
</div>
    );
};

function Input_box({select_box,id,handleInputChange}:any){
   
    return( 
    <input
      className={style.box}
      ref={select_box[id]}
      type="text"
      pattern="[A-Za-z]"
      onChange={handleInputChange}
      maxLength={1}
      id={id}
    />
    );

}