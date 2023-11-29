// api.ts
import { User } from "../objects/User";

import axios from 'axios';
const apiUrl = 'http://localhost:8080';



const SignupRequest = async (user:User) => {
  const url = `${apiUrl}/register/user/signup`;

//   const requestBody = {
//     user: user
//   };

//   try {
//     const response = await fetch(SignupUrl, {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json',
//       },
//       body: JSON.stringify(requestBody),
//     });

//     if (!response.ok) {
//       throw new Error('Network response was not ok');
//     }

//     const data: any = await response.json();
//     return data;
//   } catch (error) {
//     console.error('Error during login:', error);
//     throw error;
//   }
// };

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
export { SignupRequest };