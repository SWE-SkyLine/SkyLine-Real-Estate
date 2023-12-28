import axios from "axios";
import { Rate } from "../objects/Rate";

const apiUrl = 'http://localhost:8080';

const sendRate = async (rate :Rate) =>{
    const url = `${apiUrl}/api/rate`
    try {
        const response = await axios.post(url, rate);
        return response;
    } catch (error) {
        return "Rate not sent";
    }
};

const getRate = async (userId: Number, targetId: Number) =>{
    const url = `${apiUrl}/api/getRate`
    try {
        const requestParam = `${url}?userId=${userId}&targetId=${targetId}`
        const response = await axios.get(requestParam);
        return response.data;
    } catch (error) {
        return 0;
    }
};

const getAvgRate = async (targetId : Number) =>{
    const url = `${apiUrl}/api/avgRate`
    try{
        const requestParam = `${url}?targetId=${targetId}`
        const response = await axios.get(requestParam);
        return response.data;
    }catch (error){
        return 0;
    }
}

export {sendRate, getRate, getAvgRate};