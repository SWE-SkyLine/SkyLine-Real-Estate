'use client'
import { useState } from "react";
import { useRef } from "react";
import style from "./page_verify/page.module.css"
import Link from "next/link";
import styles from './page.module.css';
import Head from 'next/head';
 
export default function Home() {
  return (
    <div className={styles.main}>
    <button className={style.btn_verify}><Link href="/login">go to Login</Link></button>
    <br />
    {/* <button className={style.btn_verify}><Link href="/page_verify">go to verify</Link></button> */}
    <br />
    <button className={style.btn_verify}><Link href="/signup">go to signup</Link></button>
    <br />
    </div>
    )
}

