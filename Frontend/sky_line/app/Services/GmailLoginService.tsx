// api.ts

const apiUrl = 'http://localhost:8084';

// interface LoginResponse {
//   success: boolean;
//   // Add other fields based on your backend response
// }

const gmailLogin = async (email: string) => {
  const loginUrl = `${apiUrl}/register/user/login`

  const requestBody = {
    email: email,
  };

  try {
    const response = await fetch(loginUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestBody),
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    var data: any;
    return data;
  } catch (error) {
    console.error('Error during login:', error);
    throw error;
  }
};

export { gmailLogin };