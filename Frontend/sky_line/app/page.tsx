'use client'
import { useState } from "react";
import { useRef } from "react";
import style from "./page.module.css"
import Link from "next/link";
import styles from './page.module.css';
import Head from 'next/head';
 
export default function Home() {
  return (
    <>
    <Link href="/login">go to Login</Link>
    <br />
    <Link href="/page_verify">go to verify</Link>
    <br />
    <Link href="/signup">go to signup</Link>
    <br />

</>
    )
}

