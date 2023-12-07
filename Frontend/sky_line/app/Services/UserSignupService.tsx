// api.ts
import { User } from "../objects/User";

import axios, { Axios, AxiosResponse, responseEncoding } from 'axios';
const apiUrl = 'http://localhost:8080';


const SignupRequest = async (user:User) => {
  const url = `${apiUrl}/register/user/signup`;

  try {
    const response = await axios.post(url, user);
    return response;
  }
  catch (error:any) {
    console.log(error.response);
    return error.response
  }
};
const verify_code_request = async (email:string, code:string) => {
  const url = `${apiUrl}/register/user/verify`;
  const data = { email, code };

  try {
    const response = await axios.post(url, data);
    return response;
  } catch (error:any) {
    console.log(error.response);
    return error.response
  }
};


const send_code_again = async (email:string) => {
  const url = `${apiUrl}/register/user/sendVerifyCodeAgain`;
  const data = { email };

  try {
    const response = await axios.post(url, data);
    console.log(response)
    return response;
  } catch (error:any) {
    console.log(error.response);
    return error.response
  }
};



export { SignupRequest ,verify_code_request,send_code_again };