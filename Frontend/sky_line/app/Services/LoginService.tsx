// api.ts

const apiUrl = 'https://your-backend-api.com';

interface LoginResponse {
  success: boolean;
  // Add other fields based on your backend response
}

const login = async (email: string, password: string): Promise<LoginResponse> => {
  const loginUrl = `${apiUrl}/login`;

  const requestBody = {
    email: email,
    password: password,
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
    return data;
  } catch (error) {
    console.error('Error during login:', error);
    throw error;
  }
};

export { login };
