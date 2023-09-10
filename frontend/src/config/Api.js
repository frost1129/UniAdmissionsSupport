import axios from "axios";
import cookie from "react-cookies";

const SERVER_CONTEXT = "/notbackend";
const SERVER = "http://localhost:8080";

export const endpoints = {
    "info": `${SERVER_CONTEXT}/api/info/`,
    "branches": `${SERVER_CONTEXT}/api/branches/`,
    "banners": `${SERVER_CONTEXT}/api/banners/`, 
    "admission": (admissionId) => `${SERVER_CONTEXT}/api/admissions/${admissionId}/`,
    "admissions": `${SERVER_CONTEXT}/api/admissions/`,
    "faculties": `${SERVER_CONTEXT}/api/faculties/`,
    "topics": `${SERVER_CONTEXT}/api/topics/`,
    "register": `${SERVER_CONTEXT}/api/users/`,
    "login": `${SERVER_CONTEXT}/api/login/`, 
    "top-admission-post": (admissionId) => `${SERVER_CONTEXT}/api/posts/top-five/${admissionId}/`, 
    "top-5-faqs": `${SERVER_CONTEXT}/api/faqs/top-5/`,
    "faqs": `${SERVER_CONTEXT}/api/faqs/`, 
    "posts": `${SERVER_CONTEXT}/api/posts/`, 
    "posts-count": `${SERVER_CONTEXT}/api/posts/counter/`, 
    "post-details": (postId) => `${SERVER_CONTEXT}/api/posts/${postId}`, 
    "current-user": `${SERVER_CONTEXT}/api/current-user/`, 
    "question-detail": (id) => `${SERVER_CONTEXT}/api/user-questions/${id}/`,
    "question-unanswer": `${SERVER_CONTEXT}/api/user-questions/unanswer/`,
    "question-admission": `${SERVER_CONTEXT}/api/user-questions/advisor/`,
    "question-email": `${SERVER_CONTEXT}/api/user-questions/by-email/`, 
    "question-setting": `${SERVER_CONTEXT}/api/questions/setting/`, 
    "question-add": `${SERVER_CONTEXT}/api/user-question/add/`, 
    "question-update": `${SERVER_CONTEXT}/api/user-question/update/`, 
    "faculty-post": (id) => `${SERVER_CONTEXT}/api/faculty-post/${id}/`, 
    "faculty-score": (id) => `${SERVER_CONTEXT}/api/faculty-score/${id}/`, 
    "faculty-detail": (id) => `${SERVER_CONTEXT}/api/faculties/${id}/`, 
}

export const authApi = () => {
    return axios.create({
        baseURL: SERVER, 
        headers: {
            "Authorization": cookie.load("token")
        }
    })
}

export default axios.create({
    baseURL: SERVER
});