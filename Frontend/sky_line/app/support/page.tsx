'use client'
import {useState } from "react";
import style  from "../support/page.module.css";
import generalStyle from "../page_verify/page.module.css"
import sendTicketRequest from "../Services/TicketService";
import { Ticket, TicketCategoryEnum } from "../objects/Ticket";


const ticket = () =>{

    const [email, setEmail] = useState("");
    const [selectedOption, setSelectedOption] = useState("PUBLISH");
    const [reportEmail, setReportEmail] = useState("");
    const [message, setMessage] = useState("");

    const sendTicket = async (event: React.FormEvent<HTMLFormElement>) => {
        // event.preventDefault();
        let ticket = new Ticket();
        ticket.email = email;
        if(selectedOption === "PUBLISH")
            ticket.category = TicketCategoryEnum.PUBLISH;
        else if(selectedOption === "REPORT")
            ticket.category = TicketCategoryEnum.REPORT;
        else
            ticket.category = TicketCategoryEnum.OTHER;
        ticket.reported = reportEmail;
        ticket.message = message

        const res = await sendTicketRequest(ticket);
        alert(res)
    }

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
                <form className={style.ticketForm} onSubmit={sendTicket}>
                    <div>
                        <h4>Your e-mail</h4>
                        <input className={style.emailTextBox} type="email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                    </div>

                    <div>
                        <h4>problem type</h4>
                        <select className={style.list} value={selectedOption}  onChange={(e) => setSelectedOption(e.target.value)}>
                            <option className={style.list} value="PUBLISH">problem with publishing posts</option>
                            <option className={style.list} value="REPORT">Report a user</option>
                            <option className={style.list} value="OTHER">Other</option>
                        </select>
                    </div>
                    {/* only shown with report user option */ }
                    {selectedOption === "REPORT" &&(
                        <div>
                            <h4>Reported e-mail</h4>
                            <input className={style.emailTextBox} type="email" value={reportEmail} onChange={(e) => setReportEmail(e.target.value)} required/>
                        </div> )
                    }

                    <div>
                        <h4>Message</h4>
                        <textarea className={style.message} value={message} onChange={(e) => setMessage(e.target.value)} required minLength={10}/>
                    </div>
                    <button className={style.sendBtn} type="submit">Send</button>
                </form>
            </div>
        </div>     
    )
};
export default ticket;