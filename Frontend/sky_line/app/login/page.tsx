'use client'
import React, { useState } from 'react';
import myStyle from "./page.module.css"
import style from "../page_verify/page.module.css"
import { sendEmail,loginRequest} from "../Services/LoginService"

const LoginForm = () => {
  // popup window functions
  const [showPopup, setShowPopup] = useState(false);
  const [popupEmail, setPopupEmail] = useState('');
  const [emailSentSuccessfully, setEmailSentSuccessfully] = useState<boolean | null>(null);

  const openPopup = () => {
    setShowPopup(true);
  };

  const closePopup = () => {
    setShowPopup(false);
    setPopupEmail(''); // Reset the email state when closing the popup
  };

  const generateRandomNumber = () => {
    return Math.floor(100000 + Math.random() * 900000);
  };

  const submitEmail = async () => {
    const randomCode = generateRandomNumber();

    try {
      await sendEmail(popupEmail);
      setEmailSentSuccessfully(true);

      const enteredCode = window.prompt('Enter the verification code sent to your email:');

      if (enteredCode !== null) {
        if (enteredCode === '12345') {
          // Open another window prompt for a new password
          const newPassword = window.prompt('Enter your new password:');
          if (newPassword !== null) {
            // Handle setting the new password
          } else {
            // Handle the case where the user canceled entering the new password
          }
        } else {
          console.log('Incorrect verification code. Closing prompt.');
          // Handle the case where the verification code is incorrect
        }
      } else {
        console.log('Verification code input canceled.');
      }
    } catch (error) {
      console.error('Error sending email:', (error as Error).message);
      setEmailSentSuccessfully(false);
    }
  };

  // login form variables
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [selectedOption, setSelectedOption] = useState('');
  const [loginResult, setLoginResult] = useState(false);

  // on submit handler
  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("login button");
    console.log(email);
    console.log(password);
    console.log(selectedOption);
    const res = await loginRequest(email, password, selectedOption);
    setLoginResult(res);
    console.log(loginResult);
  };

  return (
    // all page
    <div className={style.container}>
      {/* left and right parts */}
      <div className={style.left_img}></div>

      <div className={style.right}>
        <div className={style.logo}></div>
        <h2 className={style.header_text}>Login</h2>
        {/* login form */}
        <form className={myStyle.loginForm} onSubmit={handleLogin}>
          {/* add email input */}
          <label className={myStyle.lable}>Email</label>
          <div>
            <input className={myStyle.textBox} type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          </div>

          {/* add password input */}
          <label className={myStyle.lable}>Password</label>
          <div>
            <input className={myStyle.textBox} type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          </div>

          {/* user type radio buttons */}
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
              Company
            </label>
          </div>

          {/* login button */}
          <button className={myStyle.loginBtn} type="submit">
            Login
          </button>

          {/* forget password link and popup window */}
          <a className={myStyle.link} href="#" onClick={openPopup}>
            Forgot Password
          </a>
          {showPopup && (
            <div className={myStyle.popup}>
              <div className={myStyle.popupContent}>
                <span className={myStyle.close} onClick={closePopup}>
                  &times;
                </span>
                <label htmlFor="popup-email">Enter your Gmail:</label>
                <input
                  type="email"
                  id="popup-email"
                  name="popup-email"
                  value={popupEmail}
                  onChange={(e) => setPopupEmail(e.target.value)}
                  required
                />
                <button onClick={submitEmail}>Submit</button>
              </div>
            </div>
          )}

        </form>
      </div>
    </div>
  );
};

export default LoginForm;
