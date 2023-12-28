// api.ts
import { Post_object } from "../objects/Post_object";
import { FilterData } from "../Home/page"

import axios from 'axios';
const apiUrl = 'http://localhost:8080';


const get_all_posts = async () => {
  const url = `${apiUrl}/api/get_posts_with_photos`;

  try {
    const response = await axios.get(url, {withCredentials: true});
     return response;
  }
  catch (error:any) {
    console.log(error.response);
    return error.response
  }
};
 
const get_bid = async (auction_id:any) => {
  const url = `${apiUrl}/api/get_bids/${auction_id}`;
  try {
    const response = await axios.get(url);
     return response;
  }
  catch (error:any) {
    console.log(error.response);
    return error.response
  }
};
const add_bids = async (client_id:any,bid_price:any,auction_id:any) => {

  const url = `${apiUrl}/api/add_bid/${client_id}/${bid_price}/${auction_id}`;
  
  // let data={
  //   client_id:client_id,
  //   bid_price:bid_price,
  //   auction_id:auction_id,
  //   id:"0"
  // }

  try {
    const response = await axios.post(url);
     return response;
  }
  catch (error:any) {
    console.log(error.response);
    return error.response
  }
};





const get_all_Auctions = async () => {
  const url = `${apiUrl}/api/get_auctions_with_photos`;

  try {
    const response = await axios.get(url);
     return response;
  }
  catch (error:any) {
    console.log(error.response);
    return error.response
  }
};


const filter_all_posts = async (filterData: URLSearchParams) => {
  const url = `${apiUrl}/api/get_posts_with_photos`;
  try {
    const response = await axios.get(url, {
      params: filterData,withCredentials: true
    });
    return response; // Return the data property of the response
  } catch (error: any) {
    // Handle the error as needed
    console.error("Error sending filter request:", error);
    return error.response?.data || error.message; // Return the error data or message
  }
};


const sort_all_posts = async (sortType: string, sortOrder: boolean) => {
  const url = `${apiUrl}/api/get_posts_with_photos?sortBy=${sortType}&sortOrder=${sortOrder ? "asc" : "desc"}`;
  try {
    const sortResponse = await axios.get(url);
    return sortResponse; 
  } catch (error: any) {
    // Handle the error as needed
    console.error("Error sending sort request:", error);
    return error.response?.data || error.message; 
  }
};


const search_all_posts = async (searchQuery:string) => {
  const url = `${apiUrl}/api/get_posts_with_photos?searchQuery=${searchQuery}`;
  try {
    const searchResponse = await axios.get(url);
    return searchResponse; 
  } catch (error: any) {
    // Handle the error as needed
    console.error("Error sending search request:", error);
    return error.response?.data || error.message; 
  }
};


export { get_all_posts, filter_all_posts, sort_all_posts, search_all_posts,get_all_Auctions,get_bid,add_bids };