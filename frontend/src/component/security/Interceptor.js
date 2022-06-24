import axios from "axios";

const instance = axios.create()

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

instance.interceptors.response.use(response => {
    return response;
}, error => {
    Promise.reject(error);
})

// axios.interceptors.request.eject(instance);

export default instance;