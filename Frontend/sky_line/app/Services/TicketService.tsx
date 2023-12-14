import axios, { Axios } from "axios";
import { Ticket } from "../objects/Ticket";

const apiUrl = 'http://localhost:8080';

const sendTicketRequest = async (ticket : Ticket) => {

    const url = `${apiUrl}/api/ticket`

    const makeRequest = async(url: string, ticket : Ticket) =>{
        try {
            const response = await axios.post(url, ticket);
            console.log("respose is");
            console.log(response);
            return response.data;
        } catch (error) {
            console.log("error is");
            console.error(error);
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
