import axios from 'axios';
const apiUrl = 'http://localhost:8080';

const testHttpOnlyCookie = async () => {
    const url = `${apiUrl}/security/sec`

    try {
        const response = await axios.get(url,  {withCredentials: true});
    console.log("test cookie : ")
    console.log(response)
    } catch (error) {
        console.error('HTTP cookie test failed:', error);
    }
};

export default testHttpOnlyCookie;
