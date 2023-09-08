import React, { createContext, useReducer } from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { Route, Routes, useLocation } from "react-router-dom";
import Home from "./layouts/Home";
import SearchResult from "./layouts/SearchResult";
import FAQs from "./layouts/FAQs";
import PostDetail from "./layouts/PostDetail";
import Posts from "./layouts/Posts";
import SignIn from "./layouts/SignIn";
import SignUp from "./layouts/SignUp";
import QuestionManage from "./layouts/QuestionManage";
import Admin from "./admin/Admin";
import AdminUser from "./admin/AUsers";
import AdminFaculties from "./admin/AFaculties";
import AdminPost from "./admin/APost";
import AdminLivestream from "./admin/ALivestream";
import PostConfig from "./admin/PostConfig";
import AdminNav from "./admin/AdminNav";
import MyUserReducer from "./reducer/MyUserReducer";
import cookie from "react-cookies";

export const MyUserContext = createContext();

const App = () => {
    const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);

    const location = useLocation();
    const pathToHideHF = ['/login', '/signup', '/admin'];

    const hideHeaderFooter = pathToHideHF.includes(location.pathname);
    const isAdminRoute = location.pathname.includes('/admin')

    return (
        <MyUserContext.Provider value={[user, dispatch]} >
            <>
                {!hideHeaderFooter && !isAdminRoute && <Header />}
                {isAdminRoute && <AdminNav />}

                <Routes>
                    <Route path="/" element={<Home/>} /> 
                    <Route path="/login" element={<SignIn/>} /> 
                    <Route path="/signup" element={<SignUp/>} /> 
                    <Route path="/search" element={<SearchResult/>} />
                    <Route path="/question-manage" element={<QuestionManage/>} />
                    <Route path="/faqs" element={<FAQs/>} />
                    <Route path="/posts" element={<Posts/>} />
                    <Route path="/posts/:postId" element={<PostDetail/>} />
                    <Route path="/faculties/:fId" element={<PostDetail />} />

                    <Route path="/admin/addpost" element={<PostConfig/>} />
                
                    <Route path="/admin" element={<Admin/>} />
                    <Route path="/admin/users" element={<AdminUser/>} />
                    <Route path="/admin/questions" element={<AdminUser/>} />
                    <Route path="/admin/faculties" element={<AdminFaculties/>} />
                    <Route path="/admin/posts" element={<AdminPost/>} />
                    <Route path="/admin/livestream" element={<AdminLivestream/>} />
                </Routes>

                {!hideHeaderFooter && !isAdminRoute && <Footer />}
            </>
        </MyUserContext.Provider>
    );
};

export default App;
