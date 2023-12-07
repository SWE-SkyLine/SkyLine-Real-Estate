import axios, { Axios } from "axios";
import { Ticket } from "../objects/Ticket";

const apiUrl = 'http://localhost:8080';

const sendTicketRequest = async (ticket : Ticket) => {

    const url = `${apiUrl}/api/ticket`

    const makeRequest = async(url: string, ticket : Ticket) =>{
        try {
            const response = await axios.post(url, ticket);
            return response.data;
        } catch (error) {
            console.error(error);
            alert("Ticket not sent")
            return "Ticket not sent";
        }
    }

    const sendRequest = async () =>{
        const response = await makeRequest(url, ticket);
        return response; 
    }

  return sendRequest()
};

export default sendTicketRequest;