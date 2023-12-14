/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable @next/next/no-img-element */
/* eslint-disable react/no-unescaped-entities */
'use client'
 import { useState } from "react";
import { useRef } from "react";
import style from "./page_verify/page.module.css"
import 'bootstrap/dist/css/bootstrap.css'
import Link from "next/link";
import styles from './page.module.css';
import Head from 'next/head';
import Navbar from './navbar/page'
import 'bootstrap-icons/font/bootstrap-icons.css';
import router from "next/navigation";
import { useRouter } from "next/navigation";  
import Home_page from './Home/page'


import PostCreate from './PostCreate/page'
import SortFilter from './sortFilter/page'
import Post from "./Post/page";
 


export default function Home() {
  let router =useRouter();
  const handleClick = (path: string) => {
    router.push(`${path}`);
  };
  
  return (



     <>
    <Navbar/>
    {/* <SortFilter/> */}
    <Post/>
    <Home_page/>
  
    </>

// {/* <>
// <button className={style.btn_verify} onClick={() => handleClick("/login")}>Login</button>
// <br />
// <br />
// <button className={style.btn_verify} onClick={() => handleClick("/signup")}>Signup</button>
// <br /> 
// </> */}

    )
}

