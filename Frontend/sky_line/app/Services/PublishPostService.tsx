import axios, { Axios } from "axios";
import Post from "../Post/page";

const apiUrl = 'http://localhost:8080';

const publishPostRequest = async (post : any) => {

    const url = `${apiUrl}/api/publish_post`

    const makeRequest = async(url: string, post : FormData) =>{
        try {
            const response = await axios.post(url, post);
            return response;
        } catch (error) {
            console.error(error);
            alert("Post is not published")
            return "Post is not published";
        }
    }

    const sendRequest = async () =>{
        const response = await makeRequest(url, post);
        return response; 
    }

  return sendRequest()
};

export default publishPostRequest;