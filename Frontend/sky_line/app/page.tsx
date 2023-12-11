/* eslint-disable react/no-unescaped-entities */
'use client'
import { useState } from "react";
import { useRef } from "react";
import style from "./page.module.css"
import 'bootstrap/dist/css/bootstrap.css'
import Link from "next/link";
import styles from './page.module.css';
import Head from 'next/head';
import Navbar from './navbar/page'
import 'bootstrap-icons/font/bootstrap-icons.css';
import router from "next/navigation";
import { useRouter } from "next/navigation";
 
export default function Home() {
  let router =useRouter();
  const handleClick = (path: string) => {
    router.push(`${path}`);
  };


  let first_name="KARIM",last_name="TAREK";
  let photo="../assets/user3.jpeg"
  const x = '/assets/user.jpg';
  const home= '/assets/home1.jpeg';
  const inside1= '/assets/inside1.jpeg';
  const inside2= '/assets/inside2.jpeg';
  const inside3= '/assets/inside3.jpg';

  return (


    <>
    <Navbar/>
  <div className={style.container}>

    <div className={style.left}>
      <div className={style.upperSlice}>
        <div className={style.photo} style={{ backgroundImage: `url(${photo})` }}></div>
        <label className={style.name}>{first_name} {last_name}</label>
        <div className={style.phone}><i className="fa-solid fa-phone"></i> <span>01202743255</span></div>
       {/* <div className={style.follow}><i className="fa-solid fa-user-group"></i> <span> Followers: 50</span></div>
       <div className={style.follow}> <i className="fa-solid fa-user-check"></i> <span>Following: 100</span></div> */}
        <button className={style.btn_edit}>Edit Profile</button>
      </div>
      <div className={style.lowerSlice}></div>
    </div>
    <div className={style.right}>
      <div className={style.contain_post}>
        <div>
          <div className={`${style.photo}`} style={{ backgroundImage: `url(${x})` }}></div>
          <label className={style.post_owner}>{first_name} {last_name}</label>
        </div>
      <div className={style.post_body}>
        <div className={style.post_head}> <label>Title:</label> </div>
        <label className={style.under_head}>Charming Family Home with Modern Elegance and Picturesque Views (<span className={style.post_type}>Rent</span>)</label>
        <div className={style.post_head}> <label>Description:</label> </div>
        <label className={style.under_head}>Welcome to this beautifully designed family home that seamlessly combines modern elegance with the warmth of a welcoming retreat. Nestled in a serene neighborhood, this residence offers a perfect blend of comfort and style. The thoughtful layout ensures ample space for family gatherings, entertaining guests, and creating lasting memories.
        </label>   
        <div className={style.post_head}> <label>Price</label> : <span className={style.under_head}>400.000 <i className="fa-solid fa-dollar-sign"> </i></span> </div>
        <div className={style.post_head}> <label>Area</label>: <span className={style.under_head}>150 m<sup>2</sup></span> </div>
        <div className={style.post_head}> <label>Map Link:</label> <a href="https://www.google.com/maps" target="_blank">Https:Link</a> </div>
      </div>
      <div className={style.post_imgs}>
        <div className={style.post_img} style={{ backgroundImage: `url(${home})` }}></div>
        <div className={style.post_img} style={{ backgroundImage: `url(${inside1})` }}></div>
        <div className={style.post_img} style={{ backgroundImage: `url(${inside2})` }}></div>
        <div className={style.post_img} style={{ backgroundImage: `url(${inside3})` }}></div>
      </div>
    </div>


    {/* will remove it */}

    <div className={style.contain_post}>
        <div>
          <div className={`${style.photo}`} style={{ backgroundImage: `url(${x})` }}></div>
          <label className={style.post_owner}>Ahmed Hesham</label>
        </div>
      <div className={style.post_body}>
        <div className={style.post_head}> <label>Title:</label> </div>
        <label className={style.under_head}>Charming Family Home with Modern Elegance and Picturesque Views (<span className={style.post_type}>Buy</span>)</label>
        <div className={style.post_head}> <label>Description:</label> </div>
        <label className={style.under_head}>Welcome to this beautifully designed family home that seamlessly combines modern elegance with the warmth of a welcoming retreat. Nestled in a serene neighborhood, this residence offers a perfect blend of comfort and style. The thoughtful layout ensures ample space for family gatherings, entertaining guests, and creating lasting memories.
        </label>   
        <div className={style.post_head}> <label>Price</label> : <span className={style.under_head}>400.000 <i className="fa-solid fa-dollar-sign"> </i></span> </div>
        <div className={style.post_head}> <label>Area</label>: <span className={style.under_head}>150 m<sup>2</sup></span> </div>
        <div className={style.post_head}> <label>Map Link:</label> <a href="https://www.google.com/maps" target="_blank">Https:Link</a> </div>
      </div>
      <div className={style.post_imgs}>
        <div className={style.post_img} style={{ backgroundImage: `url(${home})` }}></div>
        <div className={style.post_img} style={{ backgroundImage: `url(${inside1})` }}></div>
        <div className={style.post_img} style={{ backgroundImage: `url(${inside2})` }}></div>
        <div className={style.post_img} style={{ backgroundImage: `url(${inside3})` }}></div>
      </div>
    </div>
  </div>     
</div>
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

