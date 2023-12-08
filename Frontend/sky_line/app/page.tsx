'use client'
import { useState } from "react";
import { useRef } from "react";
import style from "./page_verify/page.module.css"
import 'bootstrap/dist/css/bootstrap.css'
import Link from "next/link";
import styles from './page.module.css';
import Head from 'next/head';
import Navbar from './navbar/navbar'
import 'bootstrap-icons/font/bootstrap-icons.css';
<!-- import router from "next/navigation";
import { useRouter } from "next/navigation"; -->
 
export default function Home() {
  let router =useRouter();
  const handleClick = (path: string) => {
    router.push(`${path}`);
  };
  
  return (


    <>
    <Navbar />
    <div className={styles.main}>
        <h1>Welcome to Next.js</h1>
<!--       <button className={style.btn_verify} onClick={() => handleClick("/login")}>Login</button>
    <br />
    {/* <button className={style.btn_verify}><Link href="/page_verify">go to verify</Link></button> */}
    <br />
    <button className={style.btn_verify} onClick={() => handleClick("/signup")}>Signup</button>
    <br /> -->
    </div>
    </>
    )
}

