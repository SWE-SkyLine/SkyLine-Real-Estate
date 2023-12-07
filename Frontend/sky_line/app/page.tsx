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

 
export default function Home() {
  return (


    <>
    <Navbar />
    <div className={styles.main}>
        <h1>Welcome to Next.js</h1>
    </div>
    </>
    )
}

