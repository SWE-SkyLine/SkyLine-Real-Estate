'use client'
import { useState } from "react";
import myStyle from "./page.module.css"
import style from "../page_verify/page.module.css"
import { loginRequest, sendEmail, updatePassword, verifyCode } from "../Services/LoginService"
import { GoogleOAuthProvider } from '@react-oauth/google';
import Gmail from "./Gmail";
import Link from "next/link";
import ProfilePage from "../profilePage/page";

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
    // setLoginResult(res);
    //console.log(res)
    if((res as Response).status ==200){
      window.location.assign('/')
    }
    else{
      alert("User is not registered")
    }
  };

  return (
      <div>
      <div>
        Akeny navbar
</div>
    <ProfilePage></ProfilePage>
      </div>
  );
};

export default LoginForm;
