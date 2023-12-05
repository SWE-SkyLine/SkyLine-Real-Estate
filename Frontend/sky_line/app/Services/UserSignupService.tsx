// api.ts
import { User } from "../objects/User";

import axios from 'axios';
const apiUrl = 'http://localhost:8080';



const SignupRequest = async (user:User) => {
  const url = `${apiUrl}/register/user/signup`;

const data = {
  // Data to send in the request body
  name: 'John Doe',
  email: 'johndoe@example.com',
  // ... Other data fields
};


const makeRequest = async (url: string, user: any) => {
  try {
    const response = await axios.post(url, user);
    return response;
  } catch (error) {
    console.error(error);
    alert("user already exists")
    return "Error";
  }
};

const sendPostRequest = async () => {
  const response = await makeRequest(url, user);
  //console.log(response.status);
  return response; // Handle the response data here
};

return sendPostRequest();
}

const verify_code_request = async (Email:string,code:string) => {
  const url = `${apiUrl}/register/user/verify`;

const data = {
  email:Email,
  code:code,
};


const makeRequest = async (url: string) => {
  try {
    const response = await axios.post(url,data);
    return response;
  } catch (error) {
    console.error(error);
    alert("Error")
    return "Error";
  }
};

const sendPostRequest = async () => {
  const response = await makeRequest(url);
  //console.log(response.status);
  return response; // Handle the response data here
};

return sendPostRequest();
}



export { SignupRequest ,verify_code_request };