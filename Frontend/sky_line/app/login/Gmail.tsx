import { CredentialResponse, GoogleLogin } from '@react-oauth/google';
import jwt, {JwtPayload, jwtDecode } from 'jwt-decode';
import { useState } from 'react';
import { gmailLogin } from '../Services/GmailLoginService';

interface CustomJwtPayload extends JwtPayload {
  userInfo: string;
  email: string;
}

function Gmail() {

  const [email, setEmail] = useState('');
  const [loginResult, setLoginResult] = useState(false);
  const handleSuccess = async (credentialResponse: any) => {
    const obj = jwtDecode<CustomJwtPayload>(credentialResponse.credential);
    const email = obj.email
    console.log(obj)
    console.log(email);
    // const res = await gmailLogin(email);
    // setLoginResult(res);
    console.log(loginResult)
  };

  const handleError = () => {
    console.log('Login Failed');
  };

  return (
    <GoogleLogin
      onSuccess={handleSuccess}
      onError={handleError}
    />
  );
}
export default Gmail;