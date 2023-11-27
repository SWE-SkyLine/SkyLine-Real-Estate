// api.ts

const apiUrl = 'http://localhost:8082';

interface LoginResponse {
  success: boolean;
  // Add other fields based on your backend response
}

const loginRequest = async (email: string, password: string, type: string): Promise<boolean> => {
  const loginUrl = `${apiUrl}/login`;

  const requestBody = {
    email: email,
    password: password,
    type: type,
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

    const data: LoginResponse = await response.json();
    return data.success;
  } catch (error) {
    console.error('Error during login:', error);
    throw error;
  }
};

export { loginRequest };