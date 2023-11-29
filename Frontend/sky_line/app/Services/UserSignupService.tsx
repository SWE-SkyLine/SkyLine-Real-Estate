// api.ts
import { User } from "../objects/User";
const apiUrl = 'http://localhost:8084';



const SignupRequest = async (user:User) => {
  const SignupUrl = `${apiUrl}/register/user/signup`;

  const requestBody = {
    user: user
  };

  try {
    const response = await fetch(SignupUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestBody),
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const data: any = await response.json();
    return data;
  } catch (error) {
    console.error('Error during login:', error);
    throw error;
  }
};

export { SignupRequest };