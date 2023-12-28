import axios, { Axios } from "axios";
import Post from "../Post/page";

const apiUrl = 'http://localhost:8080';

const retrievePostRequest = async () => {

    const url = `${apiUrl}/api/get_postsr`

    const makeRequest = async(url: string) =>{
        try {
            const response = await axios.get(url, {withCredentials: true});
            return response;
        } catch (error) {
            console.error(error);
            alert("Post is not published")
            return "Post is not published";
        }
    }

    const retrieveRequest = async () =>{
        const response = await makeRequest(url);
        return response; 
    }

  return retrieveRequest()
};


export default retrievePostRequest;