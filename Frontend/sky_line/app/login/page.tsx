'use client'
import { useState } from "react";
import myStyle from "./page.module.css"
import style from "../page.module.css"
// import { Link, useHistory } from 'react-router-dom';

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

  // on submit handler
  const handleLogin = () => {
    console.log("login submit buton")
  };

  // user type radio button handler
  const handleOptionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedOption(event.target.value);
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
        <form className={myStyle.loginForm}>

          <label className={myStyle.lable}>Email</label>
          <div>
            <input className={myStyle.textBox} type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>

          <label className={myStyle.lable}>Password</label>
          <div>
            <input className={myStyle.textBox} type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>

          {/* user type */}
          <div>
            <label className={myStyle.lable}>
              <input
                className={myStyle.radio}
                type="radio"
                value="Personal"
                checked={selectedOption === 'Personal'}
                onChange={handleOptionChange}
              />
              Personal
            </label>

            <label className={myStyle.lable}>
              <input
                className={myStyle.radio}
                type="radio"
                value="company"
                checked={selectedOption === 'company'}
                onChange={handleOptionChange}
              />
              company
            </label>
          </div>

          <button className={myStyle.loginBtn} type="button" onClick={handleLogin}>
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