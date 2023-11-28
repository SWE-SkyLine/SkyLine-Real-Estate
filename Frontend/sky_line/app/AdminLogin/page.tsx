'use client'
import { useState } from "react";
import myStyle from "./page.module.css"
import style from "../page_verify/page.module.css"
import { loginRequest } from "../Services/LoginService"

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

  // on submit handler
  const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("login buton")
    console.log(email)
    console.log(password)
  };

  return (
    // all page
    <div className={style.container}>
      {/* left and right parts */}
      <div className={style.left_img}></div>

      <div className={style.right}>
        <div className={style.logo}></div>
        <h2 className={style.header_text}>Admin Login</h2>
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

        </form>
      </div>
    </div>
  );
};

export default LoginForm;
