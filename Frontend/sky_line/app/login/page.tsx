'use client'
import { useState } from "react";
import myStyle from "./page.module.css"
import commonStyle from "../Utility/CommonCode/common.module.css"
import { loginRequest, sendEmail, updatePassword, verifyCode } from "../Services/LoginService"
import { GoogleOAuthProvider } from '@react-oauth/google';
import Gmail from "./Gmail";
import Navbar from "../navbar/page";
import Link from "next/link";
import { useRouter } from "next/navigation";
import axios from "axios";

const LoginForm = () => {


 // Functions for popup window
 const submitEmail = async () => {
  try {
    const enteredEmail = window.prompt('Enter your email:');

    if (enteredEmail !== null) {
      await sendEmail(enteredEmail);
      const enteredCode = window.prompt('Enter the verification code sent to your email:');

      if (enteredCode !== null) {
        console.log(parseInt(enteredCode))
        const verificationResult = await verifyCode(enteredEmail, parseInt(enteredCode));
        console.log(verificationResult)
        if (verificationResult) {
          const newPassword = window.prompt('Enter your new password:');
           
          
          if (newPassword !== null) {
            const passwordChangeStatus = await updatePassword(enteredEmail, newPassword)
            if(passwordChangeStatus){
              alert('Password change success');
            }
            
            // Handle setting the new password
          } else {
            alert('Password change cancelled')
          }
        } else {
          console.log('Incorrect verification code.');
          // Handle the case where the verification code is incorrect
        }
      } else {
        console.log('Verification code input canceled.');
      }
    } else {
      console.log('Email input canceled.');
    }
  } catch (error) {
    console.error('Error sending email:');
  }
};

  // login form varaiables
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [selectedOption, setSelectedOption] = useState('');
  const [loginResult, setLoginResult] = useState(false);
  let router =useRouter();
  // on submit handler
  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // console.log("login buton")
    // console.log(email)
    // console.log(password)
    // console.log(selectedOption)
    const res = await loginRequest(email, password, selectedOption);
    // setLoginResult(res);
    console.log(res)
    const {jwtToken, ...userDetails} = res.data;
    localStorage.setItem('userDetails', JSON.stringify(userDetails));
    //console.log("userDetaels before saving : " );
    //console.log(userDetails)
    //const detailsSaved = JSON.parse(localStorage.getItem('userDetails') as string);
    //console.log("userDetaels after saving : " );
    //console.log(detailsSaved)

    if((res as Response).status ==200){
        // id first, last name
        router.push(`/Home?id=${res.data}`)
    }
    else{
      alert("User is not registered")
    }
  };

  return (
    <div className={commonStyle.allComponents}>  
        {/*  all page */}
        <div className={commonStyle.container}>
            {/* left and right parts */}
            <div className={myStyle.left_img}></div>

            <div className={commonStyle.right}>
                <div className={commonStyle.logo}></div>
                <h2 className={commonStyle.header_text}>Login</h2>
                {/* login form  */}
                <form className={commonStyle.Form} onSubmit={handleLogin}>
                    {/* add gmail button */}
                    <label className={commonStyle.head}>Email</label>
                    <div>
                        <input className={commonStyle.emailTextBox} placeholder="E-mail" type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>

                    <label className={commonStyle.head}>Password</label>
                    <div>
                        <input className={commonStyle.emailTextBox} placeholder="Password" type="password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
                    </div>

                    <button className={commonStyle.sendBtn} type="submit">
                        Login
                    </button>

                    {/* forget password link and popup window  */}
                    <span className={commonStyle.lable}>Not a member? <Link className={commonStyle.link} href="/signup">Sign up now</Link></span>
                    <a className={commonStyle.link} href="#" onClick={submitEmail}>
                        Forgot Password
                    </a>

                    <div className={myStyle.gmaildiv}>
                    <GoogleOAuthProvider clientId="286653287539-cfsq1r439hetsrluac5hdorpjoajbd3h.apps.googleusercontent.com">
                        <Gmail/>
                    </GoogleOAuthProvider>
                    </div>
                </form>
                
            </div>
        </div>
    </div>
    
  );
};

export default LoginForm;
