import axios from "axios";
import {useLoginStore} from "@/store/login";

const instance = axios.create({
    baseURL: 'http://localhost:8080'
});

instance.interceptors.request.use(function (config) {
    const loginStore = useLoginStore();

    if (loginStore.token !== null) {
        config['headers'] = {
            Authorization: `Bearer ${loginStore.token}`
        }
    }

    return config;
});

export default instance;
