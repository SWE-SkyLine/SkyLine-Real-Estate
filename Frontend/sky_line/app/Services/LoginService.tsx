// api.ts

const apiUrl = 'http://localhost:8084';

// interface LoginResponse {
//   success: boolean;
//   // Add other fields based on your backend response
// }

const loginRequest = async (email: string, password: string, type: string) => {
  const loginUrl = `${apiUrl}/register/user/login`
  // if(type === "company")
  //   loginUrl = `${apiUrl}/register/company`;
  // else
  //   loginUrl = `${apiUrl}/register/user/login`;

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

    return response;
  } catch (error) {

    console.error('Error during login:', error);
    return 403;   
  }
};

const sendEmail = async (email: string): Promise<void> => {
  try {  
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

  } catch (error: any) {
    console.error('Error sending email:', error);
    throw new Error(`Error sending email: ${error.message}`);
  }
};


const verifyCode = async (email: string, code: number): Promise<boolean> => {
  try {
    const verifyURL = `${apiUrl}/api/users/verify-code?email=${email}&code=${code}`;
    const response = await fetch(verifyURL, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log(response.ok)
    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`Error verifying code: ${response.status} - ${errorText}`);
    }

    return true; // Verification successful
  } catch (error) {
    console.error('Error verifying code:', error);
    return false; // Verification failed
  }
};

const updatePassword = async (email: string, newPassword: string): Promise<boolean> => {
  try {
    //const updatePasswordURL = `${apiUrl}/api/users/update-password`;
    const response = await fetch("http://localhost:8080/api/users/update-password", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, newPassword }),
    });

    if (!response.ok) {
      const errorText = await response.text();
      throw new Error(`Error updating password: ${response.status} - ${errorText}`);
    }

    return true; // Password update successful
  } catch (error) {
    console.error('Error updating password:', error);
    return false; // Password update failed
  }
};





export {sendEmail, loginRequest, verifyCode, updatePassword };