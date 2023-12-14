/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable @next/next/no-img-element */
/* eslint-disable react/no-unescaped-entities */
'use client'
import { useEffect, useState } from "react";
import { useRef } from "react";
import style from "./page.module.css"
import 'bootstrap/dist/css/bootstrap.css'
import Navbar from '../navbar/page'
import 'bootstrap-icons/font/bootstrap-icons.css';
import SortFilter from "../sortFilter/page";
import { Post_object } from "../objects/Post_object";
import {get_all_posts} from "../Services/PostService";
import { AxiosResponse } from "axios";

 export default function Home_page() {

  let first_name="KARIM",last_name="TAREK";
  let photo="../assets/user3.jpeg"
  const x = '/assets/user.jpg';
  const home= '/assets/home1.jpeg';
  const inside1= '/assets/inside1.jpeg';
  const inside2= '/assets/inside2.jpeg';
  const inside3= '/assets/inside3.jpg';
  // const images: string[]=[];
  const [currentImageIndex, setCurrentImageIndex] = useState(0);

  var post :Post_object=new Post_object();
  post.area=10;
  post.title="Charming Family Home with Modern Elegance and Picturesque Views"
  post.description="Welcome to this beautifully designed family home that seamlessly combines modern elegance with the warmth of a welcoming retreat. Nestled in a serene neighborhood, this residence offers a perfect blend of comfort and style. The thoughtful layout ensures ample space for family gatherings, entertaining guests, and creating lasting memories."
  post.bathroom=4
  post.level=1;
  post.map_link="https://www.bing.com/ck/a?!&&p=23e5b8e8a975aad9JmltdHM9MTcwMjMzOTIwMCZpZ3VpZD0zOTM4ZWFmOS1jYWI4LTY1YTMtMTcxNS1mOTQwY2JjZjY0ZGQmaW5zaWQ9NTc2Mg&ptn=3&ver=2&hsh=3&fclid=3938eaf9-cab8-65a3-1715-f940cbcf64dd&u=a1L21hcHM_Jm1lcGk9MTA3fkxvY2FsflVua25vd25-RW50aXR5X1ZlcnRpY2FsX0xpc3RfQ2FyZCZ0eT0xNyZxPXNlZGliZXNociUyMGxvY2F0aW9uJnNlZ21lbnQ9TG9jYWwmcHBvaXM9MzEuMjU1MjEwODc2NDY0ODQ0XzI5Ljk3NzYyMTA3ODQ5MTIxX0FwYXJ0bWVudCUyMFNlZGklMjBCZXNocl9ZTjgwNTR4MTQ0MjE4NjQ3NDUyODQyMTAxMTF-MzEuMjYzMjgwODY4NTMwMjczXzI5Ljk4NTg0OTM4MDQ5MzE2NF9TZWElMjB2aWV3JTIwYXBhcnRtZW50JTIwU2lkaSUyMGJlc2hyX1lOODA1NHgxMDU1NDEwODcyOTI2NDA2Mjk5fiZzZWk9MCZjcD0zMS4yNTUyMTF-MjkuOTc3NjIxJmNoaWxkPSUyNnR5JTNEMTglMjZxJTNEQXBhcnRtZW50JTI1MjBTZWRpJTI1MjBCZXNociUyNnNzJTNEeXBpZC5ZTjgwNTR4MTQ0MjE4NjQ3NDUyODQyMTAxMTElMjZzZWdtZW50JTNETG9jYWwlMjZwcG9pcyUzRDMxLjI1NTIxMDg3NjQ2NDg0NF8yOS45Nzc2MjEwNzg0OTEyMV9BcGFydG1lbnQlMjUyMFNlZGklMjUyMEJlc2hyX1lOODA1NHgxNDQyMTg2NDc0NTI4NDIxMDExMX4lMjZjcCUzRDMxLjI1NTIxMX4yOS45Nzc2MjElMjZFbmFibGVNYXBWaWV3Q2hhbmdlJTNEdHJ1ZSZGT1JNPU1QU1JQTA&ntb=1"
  post.bedroom="4"
  post.price=400.000
  post.rent=true

  const [all_posts, setAllPosts] = useState<Post_object[]>([]);
  const [all_posts1, setAllPosts1] = useState<Post_object[]>([]);

  // const [images, setImages] = useState<string[]>([]);




  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await get_all_posts();
        if (res.status === 200) {
          setAllPosts(res.data);

        } else {
          // Handle error
        }
      } catch (error) {
        console.error("Error fetching posts:", error);
        // Handle error
      }
    };

    fetchData();
  }, []);



  useEffect(() => {


    console.log("ss");
    for (let i=0; i<all_posts.length; i++) {
      for (let j=0; j<all_posts[i].photosByteArray.length; j++) {
        let photoUrl = `data:image/jpeg;base64,${all_posts[i].photosByteArray[j]}`;
        all_posts[i].photosByteArray[j]=photoUrl;
      } 
    }  setAllPosts1(all_posts)

  }, [all_posts]);



  function handleNextImage() {
    // if (currentImageIndex < images.length - 1) {
    //   setCurrentImageIndex(currentImageIndex + 1);
    // }
  }

  // function handlePreviousImage() {
  //   if (currentImageIndex > 0) {
  //     setCurrentImageIndex(currentImageIndex - 1);
  //   }
  // }



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

       {/* wael search filter */}


      {all_posts1.map((p, index) => (
            <div key={index} className={style.contain_post}>
              <div>
                <div className={`${style.photo}`} style={{ backgroundImage: `url(${x})` }}></div>
                <label className={style.post_owner}>{first_name} {last_name}</label>
              </div>
               <div className={style.post_body}>
             <div className={style.post_head}> <label>Title:</label> </div>
             <label className={style.under_head}>{p.title} (<span className={style.post_type}>{p.rent && "rent"}{!p.rent && "Buy"}</span>)</label>
             <div className={style.post_head}> <label>Description:</label> </div>
             <label className={style.under_head}>{p.description}</label>   
             <div className={style.post_head}> <label>City</label> : <span className={style.under_head}>{p.city}</span> </div>
             <div className={style.post_head}> <label>Adress</label> : <span className={style.under_head}>{p.adress}</span> </div>
             <div className={style.post_head}> <label>Price</label> : <span className={style.under_head}>{p.price} <i className="fa-solid fa-dollar-sign"> </i></span> </div>
             <div className={style.post_head}> <label>Number OF Bathroom</label> : <span className={style.under_head}>{p.bathroom} </span> </div>
             <div className={style.post_head}> <label>Number OF Bedroom</label> : <span className={style.under_head}>{p.bedroom}</span> </div>
             <div className={style.post_head}> <label>Number OF Level</label> : <span className={style.under_head}>{p.level} </span> </div>
             <div className={style.post_head}> <label>Area</label>: <span className={style.under_head}>{p.area}m<sup>2</sup></span> </div>
             <div className={style.post_head}> <label>Map Link:</label> <a href={p.map_link} target="_blank">Location</a> </div>
             </div>
               <div className={style.post_imgs}>
                {/* <button
                  className={style.btn}
                  style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer", fontWeight: "500" }}
                  onClick={handlePreviousImage}
                  disabled={currentImageIndex === 0}
                >
                  &#8678; Previous Photo
                </button> */}
              
            {all_posts1[index]?.photosByteArray.map((photo, photoIndex) => (
              <img
                key={photoIndex}
                src={photo}
                style={{
                  maxWidth: "80%",
                  maxHeight: "50%",
                  margin: 0,
                  objectFit: "cover",
                  objectPosition: "center",
                  borderRadius: "5px",
                  border: "3px solid rgb(27, 31, 33)",
                  marginTop: "1rem",
                }}
                alt={`Displayed Photo ${photoIndex + 1}`}
            />
        ))}
                {/* <img
              src={p?.photosByteArray[0]}
              style={{
                    maxWidth: "70%",
                    maxHeight: "50%",
                    margin: 0,
                    objectFit: "cover",
                    objectPosition: "center",
                    borderRadius: "5px",
                    border: "3px solid rgb(27, 31, 33)"
                  }}
                /> */}
                {/* <button
                  className={style.btn}
                  style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer", fontWeight: "500" }}
                  // onClick={handleNextImage(}
                  // disabled={currentImageIndex === images.length - 1}
                >
                  Next Photo &#8680;
                </button> */}
              </div>
            </div>
          ))}

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

