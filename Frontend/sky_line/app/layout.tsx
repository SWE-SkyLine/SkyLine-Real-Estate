/* eslint-disable @next/next/no-sync-scripts */
import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import './globals.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import st from "./page.module.css"
const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Skyline Real Estate',
  description: 'Generated by create next app',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    
    <html lang="en" >

    <script
      src="https://kit.fontawesome.com/6d56012fea.js"
      crossOrigin="anonymous"
    ></script>
    
    <script
      src="https://kit.fontawesome.com/6d56012fea.js"
      crossOrigin="anonymous"
    ></script>
     
      <body className={inter.className}>{children}</body>
    </html>
  )
}
