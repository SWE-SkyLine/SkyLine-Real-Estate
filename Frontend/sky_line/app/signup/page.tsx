'use client'
import { useState } from "react";
import style_signup from "./page.module.css"
import Link from "next/link";
import { useForm } from "react-hook-form";
import 'bootstrap/dist/css/bootstrap.min.css';
import { User } from "../objects/User";
import {SignupRequest} from "../Services/UserSignupService"
import { Router, useNavigate } from 'react-router-dom';
import { AxiosResponse } from "axios";
import { useRouter } from "next/navigation";
import { Popup_respone } from "../Utility/Popup/Popup";
import { text } from "stream/consumers";

export default  function Signup(){

return(
    
  <div className={style_signup.container}>

    <div className={style_signup.left_img}></div>

    <div className={style_signup.right}>
        <div className={style_signup.logo}></div>
        <div className={style_signup.verify_form}> 
            <span className={style_signup.header_text}>Sign up Page</span>
            <From_signup></From_signup>
            <label className={style_signup.lable} >Already a member?<Link href="/login" className={style_signup.link}> Sign in now</Link></label>
        </div>
         
  </div>
</div>
);
};


function From_signup() {
  let router=useRouter();
  const [kind, setKind] = useState("Company");

  const {register,handleSubmit,formState:{ errors },watch} =useForm()
  enum UserTypeEnum {
    SUPERADMIN = 'SUPERADMIN',
    ADMIN = 'ADMIN',
    CLIENT = 'CLIENT',
    COMPANY = 'COMPANY',
    AGENT = 'AGENT',
  }
  const [showModal, setShowModal] = useState(false);
  const handleShow = () => setShowModal(true);
  
  let title="Sign-up Failed"
  let body="An error occurred while creating your account. Please try again."
  let btn_text="Close"
   function btn_action() {
    setShowModal(false);
  }
  

  const checkPasswordStrength=(password: string)=>{
    // Check if password has at least one uppercase letter
    const hasUppercase = /[A-Z]/.test(password);
    // Check if password has at least one lowercase letter
    const hasLowercase = /[a-z]/.test(password);
    // Check if password has at least one digit
    const hasDigit = /\d/.test(password);
    // Check if password has at least one special character
    const hasSpecialCharacter = /[!@#$%^&*(),.?":{}|<>]/.test(password);
   

    if(password.length >=8){
    if (!hasUppercase && !hasLowercase && hasDigit && !hasSpecialCharacter) {
      return "Weak Password"; // Password doesn't have uppercase letters, lowercase letters, digits, or special characters
    } else if ((hasUppercase ||hasLowercase&& hasDigit )|| hasSpecialCharacter) {
      return "Strong password"; // Password has uppercase letters, lowercase letters, digits, and special characters
    } else if(hasUppercase && hasDigit && hasSpecialCharacter) {
      return "Very Strong"; // Password is neither very weak, weak, nor strong, so it's considered good
    }
    else {
      return "weak Password"; // Password
    }
   }
   
  }
  const handleKindChange = (event:any) => {
    setKind(event.target.value);
  };
  const Submit = async (data:any) => {
    //  console.log(data

    if(data.password==data.confirmPassword){
    let user = new User();
        user.email = data.email;
        user.password = data.password;
        user.phone_number = data.Phone;
         if(kind =="User"){
         user.userType= UserTypeEnum.CLIENT; 
         user.firstName = data.firstName;
         user.lastName = data.lastName;
         }
         else{
          user.userType= UserTypeEnum.COMPANY;
          user.firstName = data.companyName;
          user.lastName = "";
         }

          console.log(user);
          const res = await SignupRequest(user);
          if ((res as AxiosResponse).status === 200) {
            router.push(`/page_verify?Email=${user.email}`);

          } else { 
              // we popup with error message 
              handleShow();
            }

        //requet to back sign in
    }
    
      
    
    //send data then route

  };
  return (
    <div>
      <form onSubmit={handleSubmit(Submit)}>
      <table className={style_signup.table}>
        <tbody>
          <tr>
            <td colSpan={2}>
              <label>User Or Company</label>
              <select
                className={style_signup.table_input}
                value={kind}
                onChange={handleKindChange}
              >
                <option value="User">User</option>
                <option value="Company">Company</option>
              </select>
            </td>
          </tr>

          {kind === "User" && (
            <tr>
              <td>
                <label>First Name</label>
                <input
                   className={style_signup.table_input}
                   placeholder="Enter First Name"
                  // onChange={(e) => setFirstName(e.target.value)}
                  {...register("firstName",{required:"this is required"})}
                />
                <span>{errors.firstName?.message && String(errors.firstName.message)}</span>

              </td>
              <td>
                <label>Last Name</label>
                <input
                  placeholder="Enter Last Name"
                  {...register("lastName",{required:"this is required"})}
                  className={style_signup.table_input}
                />
                <span>{errors.lastname?.message && String(errors.lastname.message)}</span>
              </td>
            </tr>
          )}
          {kind === "Company" && (
            <tr>
              <td colSpan={2}>
                <label>Company Name</label>
                <input
                  placeholder="Enter Company Name"
                  className={style_signup.table_input}
                  {...register("companyName",{required:"this is required"})}
                />
              <span>{errors.companyName?.message && String(errors.companyName.message)}</span>

              </td>
            </tr>
          )}

          <tr>
            <td>
              <label>Phone Number</label>
              <input
                type="Phone"
                placeholder="Enter Phone"
                className={style_signup.table_input}
                {...register("Phone",{required:"this is required"})}
              />
              <span>{errors.Phone?.message && String(errors.Phone.message)}</span>
            </td>
          </tr>

          <tr>
            <td>
              <label>Address</label>
              <input
                type="text"
                placeholder="Enter Address"
                className={style_signup.table_input}
                {...register("address",{required:"this is required"})}
              />
              <span>{errors.address?.message && String(errors.address.message)}</span>

            </td>
          </tr>

          <tr>
            <td>
              <label>Email</label>
              <input
                type="text"
                placeholder="Enter Email Address"
                className={style_signup.table_input}
                {...register("email",{required:"this is required"})}
              />
              <span>{errors.email?.message && String(errors.email.message)}</span>
              {watch().email && !watch().email.includes("@") && <span>Enter a Valid Email</span>}

            </td>
          </tr>
          <tr>
            <td>
              <label>Password</label>
              <input
                type="text"
                placeholder="Enter Password"
                className={style_signup.table_input}
                {...register("password",{required:"this is required",minLength:{value:8,message:"Min length required is 8"}})}
              />
                <span>{errors.password?.message && String(errors.password.message)}</span>
                {watch().password&& <span className={style_signup.pass}>{checkPasswordStrength(watch().password)}</span>}

            </td>
          </tr>
          <tr>
            <td>
              <label>Confirm Password</label>
              <input
                type="text"
                placeholder="Enter Confirm Your Password "
                className={style_signup.table_input}
                {...register("confirmPassword",{required:"this is required",minLength:{value:8,message:"Min length required is 8"}})}
              />
              <span>{errors.confirmPassword?.message && String(errors.confirmPassword.message)}</span>
             {watch().password!=watch().confirmPassword&& <span>Password must be the same as Confirm Password.</span>}

            </td>
          </tr>
        </tbody>
      </table>
      <button className={style_signup.btn_verify}>Sign up</button>
      
      </form>

      <Popup_respone showModal={showModal} setShowModal={setShowModal}
  title={title} body={body} btn_text={btn_text} btn_action={btn_action}
 />
    </div>
  );
}

