// api.ts
import { Post_object } from "../objects/Post_object";

import axios from 'axios';
const apiUrl = 'http://localhost:8080';


const get_all_posts = async () => {
  const url = `${apiUrl}/api/get_posts_with_photos`;

  try {
    const response = await axios.get(url);
     return response;
  }
  catch (error:any) {
    console.log(error.response);
    return error.response
  }
};



export { get_all_posts };