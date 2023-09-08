import axios from "axios";

const SERVER_CONTEXT = "/notbackend";

export const endpoints = {
    "info": `${SERVER_CONTEXT}/api/info/`,
    "branches": `${SERVER_CONTEXT}/api/branches/`,
    "banners": `${SERVER_CONTEXT}/api/banners/`, 
    "admissions": `${SERVER_CONTEXT}/api/admissions/`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "login": `${SERVER_CONTEXT}/api/login/`, 
    "top-admission-post": (admissionId) => `${SERVER_CONTEXT}/api/posts/top-five/${admissionId}/`
}

export default axios.create({
    baseURL: "http://localhost:8080"
});