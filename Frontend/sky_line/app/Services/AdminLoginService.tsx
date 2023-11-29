// api.ts

const apiUrl = 'http://localhost:8082';

interface LoginResponse {
  success: boolean;
  // Add other fields based on your backend response
}

const adminLoginRequest = async (email: string, password: string): Promise<boolean> => {
  const loginUrl = `${apiUrl}/register/user/login`

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
    return data.success;
  } catch (error) {
    console.error('Error during login:', error);
    throw error;
  }
};

export { adminLoginRequest };