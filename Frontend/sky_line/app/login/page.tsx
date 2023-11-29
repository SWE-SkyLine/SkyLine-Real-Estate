'use client'
import { useState } from "react";
import myStyle from "./page.module.css"
import style from "../page_verify/page.module.css"
import { loginRequest } from "../Services/LoginService"
import { GoogleOAuthProvider } from '@react-oauth/google';
import Gmail from "./Gmail";
import Link from "next/link";

import { sendEmail,verifyCode, updatePassword } from "../Services/LoginService";

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

  // on submit handler
  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("login buton")
    console.log(email)
    console.log(password)
    console.log(selectedOption)
    const res = await loginRequest(email, password, selectedOption);
    setLoginResult(res);
    console.log(loginResult)

  };

  return (
    // all page
    <div className={style.container}>
      {/* left and right parts */}
      <div className={style.left_img}></div>

      <div className={style.right}>
        <div className={style.logo}></div>
        <h2 className={style.header_text}>Login</h2>
        {/* login form  */}
        <form className={myStyle.loginForm} onSubmit={handleLogin}>
          {/* add gmail button */}
          <label className={myStyle.lable}>Email</label>
          <div>
            <input className={myStyle.textBox} type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          </div>

          <label className={myStyle.lable}>Password</label>
          <div>
            <input className={myStyle.textBox} type="password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
          </div>

          {/* user type
          <div>
            <label className={myStyle.lable}>
              <input
                className={myStyle.radio}
                type="radio"
                value="Personal"
                checked={selectedOption === 'Personal'}
                required
                name="userType"
                onChange={(e) => setSelectedOption(e.target.value)}
              />
              Personal
            </label>

            <label className={myStyle.lable}>
              <input
                className={myStyle.radio}
                type="radio"
                value="company"
                checked={selectedOption === 'company'}
                required
                name="userType"
                onChange={(e) => setSelectedOption(e.target.value)}
              />
              company
            </label>
          </div> */}

          <button className={style.btn_verify} type="submit">
            Login
          </button>

<<<<<<< HEAD
          {/* Forget password link */}
          <a className={myStyle.link} href="#" onClick={submitEmail}>
=======
          {/* forget password link and popup window  */}
          <span className={myStyle.lable1}>Not a member? <Link className={myStyle.link1} href="/signup">Sign up now</Link></span>
          <a className={myStyle.link} href="#" onClick={openPopup}>
>>>>>>> 0f17a228c1c13cf737cafa288894d66d8e00152e
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
  );
};

export default LoginForm;
