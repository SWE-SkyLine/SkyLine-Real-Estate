'use client'
import { useState } from "react";
import myStyle from "../login/page.module.css"
import commonStyle from "../Utility/CommonCode/common.module.css"
import Navbar from "../navbar/page";
import { adminLoginRequest } from "../Services/AdminLoginService";

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
const [loginResult, setLoginResult] = useState(false);

  // on submit handler
const handleLogin = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("login buton")
    console.log(email)
    console.log(password)
    const res = await adminLoginRequest(email, password);
    setLoginResult(res);
    console.log(loginResult)
};

return (

    <div className={commonStyle.allComponents}>
        {/* all page */}
        <div className={commonStyle.container}>
            {/* left and right parts */}
            <div className={myStyle.left_img}></div>

            <div className={commonStyle.right}>
                <div className={commonStyle.logo}></div>
                <h2 className={commonStyle.header_text}>Admin Login</h2>
                {/* login form  */}
                <form className={commonStyle.Form} onSubmit={handleLogin}>
                    {/* add gmail button */}
                    <label className={commonStyle.lable}>Email</label>
                    <div>
                        <input className={commonStyle.emailTextBox} type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                    </div>

                    <label className={commonStyle.lable}>Password</label>
                    <div>
                        <input className={commonStyle.emailTextBox} type="password" value={password} onChange={(e) => setPassword(e.target.value)} required/>
                    </div>

                    <button className={commonStyle.sendBtn} type="submit">Login</button>

                    {/* forget password link and popup window  */}
                    <a className={commonStyle.link} href="#" onClick={openPopup}>
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
    </div>
  );
};

export default LoginForm;
