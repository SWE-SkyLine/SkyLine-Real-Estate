import axios from 'axios';
import {User} from "@/app/objects/User";
const getClients = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/users/allUsersToPromote');
        console.log("response is "+response.data);
        return response;
    } catch (error) {
        console.error(error);

        return "Error";
    }

    };

const promoteUser = async (url:string) => {
    try {
        const response = await axios.post(url);
        console.log("response is "+response.data);
        return response;
    } catch (error) {
        console.error(error);
        return "Error";
    }

};




export {getClients , promoteUser};