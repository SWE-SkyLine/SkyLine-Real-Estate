/* eslint-disable react/no-unescaped-entities */
'use client'
import {useState } from "react";
import style  from "../support/page.module.css";
import generalStyle from "../page_verify/page.module.css";
import sendTicketRequest from "../Services/TicketService";
import Navbar from "../navbar/page";
import { Ticket, TicketCategoryEnum } from "../objects/Ticket";


const Tickets = () =>{

    const [email, setEmail] = useState("");
    const [selectedOption, setSelectedOption] = useState("PUBLISH");
    const [reportEmail, setReportEmail] = useState("");
    const [message, setMessage] = useState("");
    const [validLength, setValidLength] = useState(false)
    
    const handleLength = (mes: string)=>{
        setValidLength(mes.length > 10 && mes.length < 10000)
    }

    const sendTicket = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        if(validLength){
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
            try {
                const res = await sendTicketRequest(ticket);
                alert(res)
            } catch (error) {
                alert("Network connection fail")
            }
            
            
        }
    }

    return(

        <>
          <Navbar/>        
          <div className={style.ticketPage}>
            {/* 3 main items: logo, heading and form */}
            <div className={style.allItems}>
                <div className={generalStyle.logo}></div>

                {/* <div>
                    <h1>Do you have a problem?</h1>
                    <h2>Don't worry our support team members will do their best to help you</h2>
                </div> */}

                {/* 4 blocks and an additional one depending on category*/}
                <form className={style.ticketForm} onSubmit={sendTicket}>
                    <div>
                        <h4  className={style.heads} >E-mail</h4>
                        <input className={style.emailTextBox} placeholder="Enter Your Email ... example@gmail.com" type="email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
                    </div>

                    <div>
                        <h4 className={style.heads}>Problem Type</h4>
                        <select className={style.list} value={selectedOption}  onChange={(e) => setSelectedOption(e.target.value)}>
                            <option className={style.list} value="PUBLISH">problem with publishing posts</option>
                            <option className={style.list} value="REPORT">Report a user</option>
                            <option className={style.list} value="OTHER">Other</option>
                        </select>
                    </div> 
                    {/* only shown with report user option */ }
                    {selectedOption === "REPORT" &&(
                        <div>
                            <h4 className={style.heads} >Reported e-mail</h4>
                            <input className={style.emailTextBox} type="email" value={reportEmail} onChange={(e) => setReportEmail(e.target.value)} required/>
                        </div> )
                    }

                    <div>
                        <h4>Message</h4>
                        <textarea className={style.message} value={message} onChange={(e) => {setMessage(e.target.value), handleLength(e.target.value)}} required/>
                        {!validLength && message.length > 0 &&(
                            <div className={style.errorMessage}>
                            The message must have more than 10 charecters and less than 10000 you are using {message.length}
                            </div>
                        )}
                    </div>
                    <button className={style.sendBtn} type="submit">Send</button>
                </form>
            </div>
        </div>    
        </> 
    )
};
export default Tickets;

