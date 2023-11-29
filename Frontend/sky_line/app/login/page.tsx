'use client'
import { useState } from "react";
import myStyle from "./page.module.css"
import style from "../page_verify/page.module.css"
import { loginRequest } from "../Services/LoginService"
import { GoogleOAuthProvider } from '@react-oauth/google';
import Gmail from "./Gmail";

const LoginForm = () => {
  // popup window functions
  const [showPopup, setShowPopup] = useState(false);
  const openPopup = () => {
    setShowPopup(true);
  };

  const closePopup = () => {
    setShowPopup(false);
  };

  const submitEmail = () => {
    // Add logic to handle the submitted email, e.g., send a reset email.
    // For now, let's just close the popup
    closePopup();
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

          <button className={myStyle.loginBtn} type="submit">
            Login
          </button>

          {/* forget password link and popup window  */}
          <a className={myStyle.link} href="#" onClick={openPopup}>
            Forgot Password
          </a>
          {showPopup && (
            <div className={myStyle.popup}>
              <div className={myStyle.popupContent}>
                <span className={myStyle.close} onClick={closePopup}>
                  &times;
                </span>
                <label htmlFor="email">Enter your Gmail:</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
                <button onClick={submitEmail}>Submit</button>
              </div>
            </div>
          )}
          <div className={myStyle.gmaildiv}>
          <GoogleOAuthProvider clientId="286653287539-cfsq1r439hetsrluac5hdorpjoajbd3h.apps.googleusercontent.com">
            <Gmail/>
          </GoogleOAuthProvider>
          </div>
        </form>
        <div>
            <a href="/Signup" className={myStyle.signupLink}>Sign Up</a>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
