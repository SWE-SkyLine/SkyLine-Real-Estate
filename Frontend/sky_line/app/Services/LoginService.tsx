// api.ts

const apiUrl = 'http://localhost:8080';

interface LoginResponse {
  success: boolean;
  // Add other fields based on your backend response
}

const loginRequest = async (email: string, password: string, type: string): Promise<boolean> => {
  var loginUrl = ''
  if(type === "company")
    loginUrl = `${apiUrl}/login/company`;
  else
    loginUrl = `${apiUrl}/login/user`;

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

const checkEmail = async (email: string): Promise<boolean> => {
  const checkEmailUrl = `${apiUrl}/api/users/check-email`;
  console.log(email);
  await new Promise((resolve) => setTimeout(resolve, 0));

  try {
    const response = await fetch(checkEmailUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email }),
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`Error checking email: ${response.status} - ${errorText}`);
    } else {
      console.log('Email is valid');
      return true;
    }
  } catch (error: any) {
    console.error('Error checking email:', error);
    return false;
  }
};

const sendEmail = async (email: string): Promise<void> => {
  try {
    const isEmailValid = await checkEmail(email);

    if (isEmailValid) {
      const sendEmailUrl = `${apiUrl}/api/users/send-email`;
      console.log(email);
      await new Promise((resolve) => setTimeout(resolve, 0));

      const response = await fetch(sendEmailUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email }),
      });

      if (!response.ok) {
        const errorText = await response.text();
        throw new Error(`Error sending email: ${response.status} - ${errorText}`);
      } else {
        console.log('Email sent successfully');
      }
    } else {
      console.log('Email is not valid, so not sending.');
    }
  } catch (error: any) {
    console.error('Error sending email:', error);
    throw new Error(`Error sending email: ${error.message}`);
  }
};


export {sendEmail, loginRequest };