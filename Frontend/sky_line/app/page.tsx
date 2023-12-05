'use client'
import { useState } from "react";
import { useRef } from "react";
import style from "./page_verify/page.module.css"
import Link from "next/link";
import styles from './page.module.css';
import Head from 'next/head';
import router from "next/navigation";
import { useRouter } from "next/navigation";
 
export default function Home() {
  let router =useRouter();
  const handleClick = (path: string) => {
    router.push(`${path}`);
  };
  
  return (
    <div className={styles.main}>
      <button className={style.btn_verify} onClick={() => handleClick("/login")}>Login</button>
    <br />
    {/* <button className={style.btn_verify}><Link href="/page_verify">go to verify</Link></button> */}
    <br />
    <button className={style.btn_verify} onClick={() => handleClick("/signup")}>Signup</button>
    <br />
    </div>
    )
}

