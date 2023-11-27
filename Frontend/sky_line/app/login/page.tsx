'use client'
import { useState } from "react";
import style from "../page.module.css"
// import { Link, useHistory } from 'react-router-dom';

const LoginForm = () => {
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

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  // const history = useHistory();

  const handleLogin = () => {
    // Example URL, replace it with your backend authentication endpoint
    const apiUrl = 'https://your-backend-api.com/login';

    // Example request body, adjust it based on your backend requirements
    const requestBody = {
      email: email,
      password: password,
    };

    fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestBody),
    })
      .then(response => response.json())
      .then(data => {
        if (data.success) {
          // Successful login, redirect to home
          // history.push('/');
          console.log("Successfully login")
        } else {
          // Failed login, display error message
          setErrorMessage('Wrong email or password. Please try again.');
        }
      })
      .catch(error => {
        // Handle network or other errors
        console.error('Error during login:', error);
      });
  };

  return (
    <div className={style.container}>
      <div className={style.left_img}></div>
      <div className={style.right}>
        <div className={style.logo}></div>
        <h2 className={style.header_text}>Login</h2>
        <form>
          <label className={style.lable}>Email:</label>
          <div>
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          </div>
          <label className={style.lable}>Password:</label>
          <div>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>

          <button className={style.btn_verify} type="button" onClick={handleLogin}>
            Login
          </button>
          <a className={style.lable} href="#" onClick={openPopup}>
            Forgot Password
          </a>
          {showPopup && (
            <div className="popup">
              <div className="popup-content">
                <span className="close" onClick={closePopup}>
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