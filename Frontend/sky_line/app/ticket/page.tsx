'use client'
import { ChangeEvent, useState } from "react";
import style  from "../ticket/page.module.css";
import generalStyle from "../page_verify/page.module.css"


const ticket = () =>{

    const [selectedOption, setSelectedOption] = useState("");

    return(
        <div className={style.ticketPage}>
            {/* 3 main items: logo, heading and form */}
            <div className={style.allItems}>
                <div className={generalStyle.logo}></div>

                <div>
                    <h1>Do you have a problem?</h1>
                    <h2>Don't worry our support team members will do their best to help you</h2>
                </div>

                {/* 4 blocks and an additional one depending on category*/}
                <form className={style.ticketForm}>
                    <div>
                        <h4>Your e-mail</h4>
                        <input className={style.emailTextBox} type="email" required/>
                    </div>

                    <div>
                        <h4>problem type</h4>
                        <select className={style.list} value={selectedOption}  onChange={(e) => setSelectedOption(e.target.value)}>
                            <option className={style.list} value="Publish">problem with publishing posts</option>
                            <option className={style.list} value="report">Report a user</option>
                            <option className={style.list} value="other">Other</option>
                        </select>
                    </div>
                    {/* only shown with report user option */ }
                    {selectedOption === "report" &&(
                        <div>
                            <h4>Reported e-mail</h4>
                            <input className={style.emailTextBox} type="email" required/>
                        </div> )
                    }

                    <div>
                        <h4>Message</h4>
                        <textarea className={style.message} required minLength={10}/>
                    </div>
                    <button className={style.sendBtn} type="submit">Send</button>
                </form>
            </div>
        </div>     
    )
};
export default ticket;