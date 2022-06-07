import axios from "axios";


const instance = axios.create({
    baseURL: 'http://localhost:8080'
})

instance.interceptors.request.use( config => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers["Content-Type"] = "application/json";
        config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
}, error => {
    Promise.reject(error);
});

// instance.interceptors.response.use(response => {

// }, error => {
//     Promise.reject(error);
// })

export default instance;