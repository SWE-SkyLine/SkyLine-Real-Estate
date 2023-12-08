'use client'
import { useEffect, useState } from "react";
import { useRef } from "react";
import style from "./page.module.css"
import { verify } from "crypto";
import { SignupRequest, verify_code_request } from "../Services/UserSignupService";
import { AxiosResponse } from "axios";
import { useRouter } from "next/navigation";

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
  const select0 =useRef<HTMLInputElement>(null)
  const select1 =useRef<HTMLInputElement>(null)
  const select2 =useRef<HTMLInputElement>(null)
  const select3 =useRef<HTMLInputElement>(null)
  const select4 =useRef<HTMLInputElement>(null)
  const select_box=[select0,select1,select2,select3,select4]

 const handleInputChange = (e:any) => {
     let id= parseInt(e.target.id)
     inputValue[id]=e.target.value
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
  console.log(user_Email);
  console.log(code);

  const res = await verify_code_request( user_Email, code);
          if ((res as AxiosResponse).status === 409) {
            alert("Signup failed, try again");
          } else {
              router.push(`/login`);
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
            <button className={style.btn_verify} onClick={verify}>Verify</button>
            <label className={style.lable}>Send Again</label>
        </div>
         
  </div>
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