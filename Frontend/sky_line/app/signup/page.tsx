'use client'
import { useState } from "react";
import style_signup from "./page.module.css"
import commonStyle from "../Utility/CommonCode/common.module.css";
import Link from "next/link";
import { useForm } from "react-hook-form";
import 'bootstrap/dist/css/bootstrap.min.css';
import { User, UserTypeEnum } from "../objects/User";
import {SignupRequest} from "../Services/UserSignupService"
import { AxiosResponse } from "axios";
import { useRouter } from "next/navigation";
import { Popup_respone } from "../Utility/Popup/Popup";
import Navbar from "../navbar/page";

export default  function Signup(){

return(
    <>  
        <Navbar/>
        <div className={commonStyle.container}>
            <div className={style_signup.left_img}></div>
            <div className={commonStyle.right}>
                <div className={commonStyle.logo}></div>
                <div> 
                    <span className={commonStyle.header_text}>Sign up</span>
                    <From_signup></From_signup>
                </div>
            </div>
        </div>
    </>
  
);
};


function From_signup() {
    let router=useRouter();
    const [kind, setKind] = useState("Company");

    const {register,handleSubmit,formState:{ errors },watch} =useForm()
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
            }else {
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
        <form className={commonStyle.Form} onSubmit={handleSubmit(Submit)}>
            <table className={style_signup.table}>
                <tbody>
                    <tr>
                        <td colSpan={2}>
                            <label className={commonStyle.head}>User Or Company</label>
                            <select
                                className={commonStyle.emailTextBox}
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
                            <label className={commonStyle.head}>First Name</label>
                            <input
                                className={commonStyle.emailTextBox}
                                placeholder="Enter First Name"
                                // onChange={(e) => setFirstName(e.target.value)}
                                {...register("firstName",{required:"this is required"})}
                            />
                            <span>{errors.firstName?.message && String(errors.firstName.message)}</span>
                        </td>
                        <td>
                            <label className={commonStyle.head}>Last Name</label>
                            <input
                                placeholder="Enter Last Name"
                                {...register("lastName",{required:"this is required"})}
                                className={commonStyle.emailTextBox}
                            />
                            <span>{errors.lastname?.message && String(errors.lastname.message)}</span>
                        </td>
                    </tr>
                    )}
                    {kind === "Company" && (
                    <tr>
                        <td colSpan={2}>
                            <label className={commonStyle.head}>Company Name</label>
                            <input
                                placeholder="Enter Company Name"
                                className={commonStyle.emailTextBox}
                                {...register("companyName",{required:"this is required"})}
                            />
                        <span>{errors.companyName?.message && String(errors.companyName.message)}</span>
                        </td>
                    </tr>
                    )}

                    <tr>
                        <td>
                            <label className={commonStyle.head}>Phone Number</label>
                            <input
                                type="Phone"
                                placeholder="Enter Phone"
                                className={commonStyle.emailTextBox}
                                {...register("Phone",{required:"this is required"})}
                            />
                            <span>{errors.Phone?.message && String(errors.Phone.message)}</span>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label className={commonStyle.head}>Address</label>
                            <input
                                type="text"
                                placeholder="Enter Address"
                                className={commonStyle.emailTextBox}
                                {...register("address",{required:"this is required"})}
                            />
                            <span>{errors.address?.message && String(errors.address.message)}</span>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label className={commonStyle.head}>Email</label>
                            <input
                                type="text"
                                placeholder="Enter Email Address"
                                className={commonStyle.emailTextBox}
                                {...register("email",{required:"this is required"})}
                            />
                            <span>{errors.email?.message && String(errors.email.message)}</span>
                            {watch().email && !watch().email.includes("@") && <span>Enter a Valid Email</span>}

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={commonStyle.head}>Password</label>
                            <input
                                type="text"
                                placeholder="Enter Password"
                                className={commonStyle.emailTextBox}
                                {...register("password",{required:"this is required",minLength:{value:8,message:"Min length required is 8"}})}
                            />
                            <span>{errors.password?.message && String(errors.password.message)}</span>
                            {watch().password&& <span className={style_signup.pass}>{checkPasswordStrength(watch().password)}</span>}

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label className={commonStyle.head}>Confirm Password</label>
                            <input
                                type="text"
                                placeholder="Enter Confirm Your Password "
                                className={commonStyle.emailTextBox}
                                {...register("confirmPassword",{required:"this is required",minLength:{value:8,message:"Min length required is 8"}})}
                                />
                            <span>{errors.confirmPassword?.message && String(errors.confirmPassword.message)}</span>
                            {watch().password!=watch().confirmPassword&& <span>Password must be the same as Confirm Password.</span>}

                        </td>
                    </tr>
                </tbody>
            </table>
            <button className={commonStyle.sendBtn}>Sign up</button>
            <label className={commonStyle.lable} >Already a member?<Link href="/login" className={commonStyle.link}> Login now</Link></label>

        </form>

        <Popup_respone showModal={showModal} setShowModal={setShowModal}
            title={title} body={body} btn_text={btn_text} btn_action={btn_action}
        />
    </div>
    );
}

