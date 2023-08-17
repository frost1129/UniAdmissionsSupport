import axios from "axios";

const SERVER_CONTEXT = "/notbackend";

export const enpoints = {
    "info": `${SERVER_CONTEXT}/api/info/`,
    "branches": `${SERVER_CONTEXT}/api/branches/`,
    "banners": `${SERVER_CONTEXT}/api/banners/`
}

export default axios.create({
    baseURL: "http://localhost:8080"
});