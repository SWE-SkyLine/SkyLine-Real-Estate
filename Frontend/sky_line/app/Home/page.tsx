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
import {get_all_posts,get_all_Auctions, get_bid} from "../Services/PostService";
import {filter_all_posts} from "../Services/PostService";
import { sort_all_posts } from "../Services/PostService";
import { search_all_posts } from "../Services/PostService";
import { AxiosResponse } from "axios";
import { useRouter } from "next/navigation";
import { Popup_respone,Pop_addbid,Popu_show_score} from "../Utility/Popup/Popup";

import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import ToggleButton from "react-bootstrap/ToggleButton";
import ToggleButtonGroup from "react-bootstrap/ToggleButtonGroup";
import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Post from "../Post/page";

export interface FilterData {
  priceFrom: string;
  priceTo: string;
  estateType: string;
  rent: boolean;
}

 export default function Home_page() {

  
  let first_name="Nagui",last_name="Mostafa";
  let photo="/img.png"
  const x = '/assets/user.jpg';
 

  var post :Post_object=new Post_object();

  const initialIndexArray = Array.from({ length: 100 }, () => 0);
  const [all_posts, setAllPosts] = useState<Post_object[]>([]);
  const [all_posts1, setAllPosts1] = useState<Post_object[]>([]);
  const [indexArray, setIndexArray] = useState<number[]>(initialIndexArray);
  const [userId,setUserId] = useState("");
  let [inputValue, setInputValue] = useState<string>('');

  let router = useRouter();
  // const [images, setImages] = useState<string[]>([]);

 



  useEffect(() => {

    const urlParams = new URLSearchParams(window.location.search);
    let userid=urlParams.get('id');
    if(userid!=null) {
    setUserId(userid);
    }
    console.log(userId)

    const fetchData = async () => {
      try {
        const res = await get_all_posts();
        if (res.status === 200) {
          console.log(res.data)
          // setAllPosts(res.data);
          const res2 =await get_all_Auctions();
          if (res2.status === 200) {
            console.log(res2.data)
            const combinedPosts = res.data.concat(res2.data);
            // Assuming setAllPosts is a state-setting function
            setAllPosts(combinedPosts);          } else {
            // Handle error
          }

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

    const initialIndexArray = Array.from({ length: 100 }, () => 0);
    setIndexArray(initialIndexArray)


  }, [all_posts]);



    function handleNextImage(index:any) {
    if (indexArray[index] < all_posts1[index]?.photosByteArray.length - 1) {
      const newArray = [...indexArray];
      console.log(newArray);
      newArray[index] ++;
      setIndexArray(newArray);}

  }

  function handlePreviousImage(index:any) {
    if (indexArray[index] > 0) {
      const newArray = [...indexArray];
      console.log(newArray);
      newArray[index]--; 
      setIndexArray(newArray);    }
  }



  const [showFormModal, setShowFormModal] = useState(false);
  const [validated, setValidated] = useState(false);
  const [rentBuyValue, setRentBuyValue] = useState<string | null>(null);
  const [area, setArea] = useState<string | null>(null);
  const [estateType, setEstateType] = useState<string>("APARTMENT");
  const [priceFrom, setPriceFrom] = useState<string>("");
  const [priceTo, setPriceTo] = useState<string>("");
  const [priceSortOrder, setPriceSortOrder] = useState<boolean>(false);
  const [areaSortOrder, setAreaSortOrder] = useState<boolean>(false);

  
  const [showModal, setShowModal] = useState(false);
  const [showModalbid, setShowModalbid] = useState(true);

  const handleShow = async (auction_id:any) =>{
    setauction_id(auction_id)
    const res =await get_bid(auction_id);
    if (res.status === 200) {
      set_bids(res.data);
      // Assuming setAllPosts is a state-setting function
       } else {
      // Handle error
    }

    setShowModal(true)
  };
  const handleadd = (auction_id:any) => {
    setauction_id(auction_id)

    setShowModalbid(true);
  };

  let [bids,set_bids]=useState([])
  let [auction_id,setauction_id]=useState()

  let [title,settitle]=useState("Score board")
  let [body,setbody]=useState("Please double-check the code in your email and try again.")
  let btn_text="Close"
  function btn_action_show() {
    
      setShowModal(false);
  }
  function btn_action_add() {

    console.log(auction_id);
    console.log(inputValue)
    setShowModalbid(false);
}

  const handleFormSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const form = event.currentTarget;

    if (form.checkValidity() === false) {
      event.stopPropagation();
      setValidated(true);
      return;
    }

    const filterQueryParams = new URLSearchParams();
    filterQueryParams.append("priceFrom", priceFrom || "");
    filterQueryParams.append("priceTo", priceTo || "");
    filterQueryParams.append("estateType", estateType);
    filterQueryParams.append("rent", rentBuyValue === "rent" ? "true" : "false");
    
    try {
      const filterResponsePromise = filter_all_posts(filterQueryParams);
      const filterResponse = await filterResponsePromise;
      if (filterResponse.status === 200) {
        console.log(filterResponse.data);
        setAllPosts(filterResponse.data)
        console.log("Filter request sent successfully");
      } else {
        if (filterResponse.status) {
          console.error("Response status:", filterResponse.status);
        }
      }
    } catch (error) {
      console.error("Error sending filter request", error);
    }
  setValidated(false);
};

  // Open the form modal
  const openFormModal = () => {
    setShowFormModal(true);
  };

  // Close the form modal
  const closeFormModal = () => {
    setShowFormModal(false);
    setValidated(false);
  };

  // Toggle the price sort order and send sort request to the backend
  const togglePriceSortOrder = () => {
    setPriceSortOrder(!priceSortOrder);
    sendSortRequest("price", priceSortOrder);
  };

  // Toggle the area sort order and send sort request to the backend
  const toggleAreaSortOrder = () => {
    setAreaSortOrder(!areaSortOrder);
    sendSortRequest("area", areaSortOrder);
  };

  // Send sort request to the backend
  const sendSortRequest = async (sortType: string, sortOrder: boolean) => {
    try {
      const sortRequestPromise = sort_all_posts(sortType, sortOrder);
      const sortResponse = await sortRequestPromise;
      if (sortResponse.status === 200) {
        console.log(`Sort request for ${sortType} sent successfully`);
        console.log(sortResponse.data)
        setAllPosts(sortResponse.data)
      } else {
        console.error(`Error sending sort request for ${sortType}`);
      }
    } catch (error) {
      console.error(`Error sending sort request for ${sortType}`, error);
    }
  };
  const handleSearchButtonClick = async () => {
    const searchInput = document.getElementsByName(
      "search"
    )[0] as HTMLInputElement | null;

    if (searchInput) {
      const searchQuery = searchInput.value.trim();

      if (searchQuery) {
        try {
          const searchRequestPromise = search_all_posts(searchQuery);
          const searchResponse = await searchRequestPromise;
          if (searchResponse.status === 200) {
            console.log(`search request for ${searchQuery} sent successfully`);
            console.log(searchResponse.data)
            setAllPosts(searchResponse.data)
          } else {
            console.error(`Error sending sort request for ${searchQuery}`);
          }
        } catch (error) {
          console.error("Error sending search request", error);
        }
      }
    }
  };



  return (
    <>
    <Post userId={userId}/>
    <Navbar/>
    <Popu_show_score showModal={showModal} setShowModal={setShowModal}
            title={"Score Board"} bids={bids}
            />
    <Pop_addbid showModal={showModalbid} setShowModal={setShowModalbid}
            title={"Add Bid"} setinput={setInputValue} btn_text={"Add +"} btn_action={btn_action_add}
            />
  <div className={style.container}>

    <div className={style.left}>

     <div className={style.upperSlice}>
      <div className={style.contain_upper}> 
        <div className={style.user_data}>
         <div className={style.photo} style={{ backgroundImage: `url(${photo})` }}></div>
        <label className={style.name} >{first_name} {last_name}</label>
        </div>
        {/* <div className={style.phone}><i className="fa-solid fa-phone"></i> <span>01202743255</span></div> */}
       {/* <div className={style.follow}><i className="fa-solid fa-user-group"></i> <span> Followers: 50</span></div>
       <div className={style.follow}> <i className="fa-solid fa-user-check"></i> <span>Following: 100</span></div> */}
       
        <button className={style.btn_edit} onClick={()=>{router.push("/profilePage")}}>Edit Profile</button>
      </div>
      </div>
        
    
    <div className={style.lowerSlice}>

    <div className="container mt-3">
        <div className="row" style={{ marginTop: "1rem",marginBottom:"2rem",marginLeft:"7rem" }}>
          {/* Search Bar */}
          <div className="col-6">
            <div className="input-group">
              <span className="input-group-text">
                <i className="bi bi-search"></i>
              </span>
              <input
                type="text"
                className="form-control"
                placeholder="Search"
                name="search" // Assign a name to the search input
              />
              {/* Correct usage of handleFormSubmit */}
              <button
                className="btn btn-primary"
                style={{
                  zIndex : '0'
                }}
                type="button" // Change type to "button"
                onClick={handleSearchButtonClick} // Attach click event
              >
                <i className="bi bi-search"></i> Search
              </button>
            </div>
          </div>

          {/* Sort Buttons */}
          <div className="col">
            <Button variant="outline-primary" onClick={togglePriceSortOrder}>
              {priceSortOrder ? (
                <i className="bi bi-arrow-down"></i>
              ) : (
                <i className="bi bi-arrow-up"></i>
              )}{" "}
              Price
            </Button>
            <span style={{ padding: "10px" }}></span>
            <Button variant="outline-primary" onClick={toggleAreaSortOrder}>
              {areaSortOrder ? (
                <i className="bi bi-arrow-down"></i>
              ) : (
                <i className="bi bi-arrow-up"></i>
              )}{" "}
              Area
            </Button>
          </div>

          {/* Open Form Button */}
          <div className="col">
            <Button variant="primary" onClick={openFormModal}>
              Filter
            </Button>
          </div>
        </div>
      </div>

      {/* Form Modal */}
      <Modal show={showFormModal} onHide={closeFormModal}>
        <Modal.Body>
          <Form noValidate validated={validated} onSubmit={handleFormSubmit}>
            <Form.Group className="mb-3">
              {/* <Form.Label>Rent/Buy</Form.Label> */}
              <ToggleButtonGroup
                type="radio"
                name="rentBuyOptions"
                value={rentBuyValue}
                onChange={(val) => setRentBuyValue(val)}
              >
                <ToggleButton id="1" value="rent" className="custom-toggle">
                  Rent
                </ToggleButton>
                <ToggleButton id="2" value="buy" className="custom-toggle">
                  Buy
                </ToggleButton>
              </ToggleButtonGroup>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Estate Type</Form.Label>
              <Form.Control
                as="select"
                value={estateType}
                onChange={(e) => setEstateType(e.target.value)}
              >
                <option value="APARTMENT">Apartment</option>
                <option value="HOUSE">House</option>
                <option value="VILLA">Villa</option>
                <option value="LAND">Land</option>
              </Form.Control>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Price Range</Form.Label>
              <Row>
                <Col>
                  <Form.Control
                    type="number"
                    placeholder="From"
                    value={priceFrom}
                    onChange={(e) => setPriceFrom(e.target.value)}
                  />
                </Col>
                <Col>
                  <Form.Control
                    type="number"
                    placeholder="To"
                    value={priceTo}
                    onChange={(e) => setPriceTo(e.target.value)}
                  />
                </Col>
              </Row>
            </Form.Group>
            <div className="d-flex justify-content-end">
              <Button
                variant="secondary"
                className="me-2"
                onClick={closeFormModal}
              >
                Cancel
              </Button>
              <Button type="submit">Apply Filters</Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>

       {/* wael search filter */}


      {all_posts1.map((p, index) => (
            <div key={index} className={style.contain_post}>
              <div>
                <div className={`${style.photo}`} style={{ backgroundImage: `url(${x})` }}></div>
                <label className={style.post_owner}>{p.fullName}</label>                
              </div>

            <div className={style.post_body}>
            <div className={style.post_head}> <label>Type:<span className={style.under_head}>{p.post_type}</span></label> </div>

             <div className={style.post_head}> <label>Title:</label> </div>
             <label className={style.under_head}>{p.title} (<span className={style.post_type}>{p.rent && "For rent"}{!p.rent && "For sale"}</span>)</label>   
             <div className={style.post_head}> <label>EstateType</label> : <span className={style.under_head}>{p.estateType}</span> </div>
             <div className={style.post_head}> <label>City</label> : <span className={style.under_head}>{p.city}</span> </div>
             <div className={style.post_head}> <label>Adress</label> : <span className={style.under_head}>{p.address}</span> </div>
             <div className={style.post_head}> <label>Price</label> : <span className={style.under_head}>{p.price} <i className="fa-solid fa-dollar-sign"> </i></span> </div>
             <div className={style.post_head}> <label>Number OF Bathroom</label> : <span className={style.under_head}>{p.bathroom} </span> </div>
             <div className={style.post_head}> <label>Number OF Bedroom</label> : <span className={style.under_head}>{p.bedroom}</span> </div>
             <div className={style.post_head}> <label>Number OF Level</label> : <span className={style.under_head}>{p.level} </span> </div>
             <div className={style.post_head}> <label>Area</label>: <span className={style.under_head}>{p.area}m<sup>2</sup></span> </div>
             <div className={style.post_head}> <label>Map Link:</label> <a href={p.map_link} target="_blank">Location</a> </div>
             <div className={style.post_head}> <label>Description:</label> </div>
             <label className={style.under_head}>{p.description}</label>
             </div>
                 <div className={style.post_imgs}>
                  <div style={{display:"block",width:"100%"}}>  
                    <button
                  className={style.btn}
                  style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer", fontWeight: "500"}}
                  onClick={() => handlePreviousImage(index)}
                  disabled={indexArray[index] === 0}
                >
                  &#8678; Previous Photo
                </button>
                <button
                  className={style.btn}
                  style={{ borderRadius: "5px", padding: "5px 10px", cursor: "pointer", fontWeight: "500", right:"0"  }}
                  onClick={() => handleNextImage(index)}
                  disabled={indexArray[index] === all_posts1[index]?.photosByteArray.length- 1}
                >
                  Next Photo &#8680;
                </button>
                </div>
               
                <img
              src={all_posts1[index]?.photosByteArray[indexArray[index]]}
              style={{
                    maxWidth: "70%",
                    maxHeight: "50%",
                    margin: 0,
                    objectFit: "cover",
                    objectPosition: "center",
                    borderRadius: "5px",
                    border: "3px solid rgb(27, 31, 33)"
                  }}
                />

              </div>

              {p.post_type=="auction"&&
              <div className={style.score_div}>
                <button className={style.score_btn} onClick={handleShow}>Score Board</button>
                <button className={style.score_btn} onClick={() => handleadd(p.id)} style={{marginTop:"1rem"}} >Add Bid</button>

              </div>
              }
            </div>
            


          ))}

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

