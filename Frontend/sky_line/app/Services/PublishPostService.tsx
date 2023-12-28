import axios, { Axios } from "axios";
import Post from "../Post/page";

const apiUrl = 'http://localhost:8080';

// const publishPostRequest = async (post : any) => {

//     const url = `${apiUrl}/api/publish_post`

//     const makeRequest = async(url: string, post : FormData) =>{
//         try {
//             const response = await axios.post(url, post);
//             return response;
//         } catch (error) {
//             console.error(error);
//             alert("Post is not published")
//             return "Post is not published";
//         }
//     }

//     const sendRequest = async () =>{
//         const response = await makeRequest(url, post);
//         return response; 
//     }

//   return sendRequest()
// };
const publishPostRequest = async (post : FormData) => {

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

const publishAuctionRequest = async (post : FormData) => {

    const url = `${apiUrl}/api/publish_auction`

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




const testPhotoApi = async (files: File[]) => {
    const url = `${apiUrl}/api/upload`;

    // Create new FormData instance and append the files
    const formData = new FormData();
    files.forEach((file) => {
        formData.append('file', file);
    });

    try {
        const response = await axios.post(url, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        return response;
    } catch (error) {
        console.error(error);
        alert("File upload failed");
        return "File upload failed";
    }
};

// export default publishPostRequest;
export { publishPostRequest, testPhotoApi,publishAuctionRequest };