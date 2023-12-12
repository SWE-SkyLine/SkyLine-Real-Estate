/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable @next/next/no-img-element */
/* eslint-disable react/no-unescaped-entities */
'use client'
import { useState } from "react";
import { useRef } from "react";
import style from "./page.module.css"
import 'bootstrap/dist/css/bootstrap.css'
import Navbar from '../navbar/page'
import 'bootstrap-icons/font/bootstrap-icons.css';
import SortFilter from "../sortFilter/page";
 export default function Home_page() {

  let first_name="KARIM",last_name="TAREK";
  let photo="../assets/user3.jpeg"
  const x = '/assets/user.jpg';
  const home= '/assets/home1.jpeg';
  const inside1= '/assets/inside1.jpeg';
  const inside2= '/assets/inside2.jpeg';
  const inside3= '/assets/inside3.jpg';
  const images=[inside2,inside1,inside3];
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  
  function handleNextImage() {
    if (currentImageIndex < images.length - 1) {
      setCurrentImageIndex(currentImageIndex + 1);
    }
  }

  function handlePreviousImage() {
    if (currentImageIndex > 0) {
      setCurrentImageIndex(currentImageIndex - 1);
    }
  }
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

      <button 

      className={style.btn}
      style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer",fontWeight:"500"}}
      onClick={handlePreviousImage}
      disabled={currentImageIndex === 0}
      >
          &#8678; Previous Photo
      </button>

      <img
        src={images[currentImageIndex]}
        style={{
        maxWidth: "70%",
        maxHeight: "50%",
        margin: 0,
        objectFit: "cover",
        objectPosition: "center",
        borderRadius: "5px",
        border:"3px solid rgb(27, 31, 33)"
        
        }}
      />

      <button
      className={style.btn}
      style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer",fontWeight:"500"}}
      onClick={handleNextImage}
      disabled={currentImageIndex === images.length - 1}
      >
          Next Photo &#8680;
      </button>
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

      <button 

            className={style.btn}
            style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer",fontWeight:"500"}}
            onClick={handlePreviousImage}
            disabled={currentImageIndex === 0}
        >
          &#8678; Previous Photo
        </button>

        <img
            src={images[currentImageIndex]}
            style={{
              maxWidth: "70%",
              maxHeight: "50%",
              margin: 0,
              objectFit: "cover",
              objectPosition: "center",
              borderRadius: "5px",
              border:"3px solid rgb(27, 31, 33)"
              
            }}
        />

         <button
            className={style.btn}
            style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer",fontWeight:"500"}}
            onClick={handleNextImage}
            disabled={currentImageIndex === images.length - 1}
        >
          Next Photo &#8680;
        </button>
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

