import React from "react";
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
import AdminNav from "./admin/AdminNav";

const App = () => {
    const location = useLocation();
    const pathToHideHF = ['/login', '/signup', '/admin'];
    const adminPath = ['/admin', '/admin/users']

    const hideHeaderFooter = pathToHideHF.includes(location.pathname);
    const isAdminRoute = adminPath.includes(location.pathname);

    return (
        <>
            {!hideHeaderFooter && !isAdminRoute && <Header />}
            {isAdminRoute && <AdminNav />}

            <Routes>
                <Route path="/" element={<Home/>} /> 
                <Route path="/login" element={<SignIn/>} /> 
                <Route path="/signup" element={<SignUp/>} /> 
                <Route path="/search" element={<SearchResult/>} />
                <Route path="/question-manage" element={<QuestionManage/>} />
                <Route path="/admin" element={<Admin/>} />
                <Route path="/admin/users" element={<FAQs/>} />
                <Route path="/faqs" element={<FAQs/>} />
                <Route path="/posts" element={<Posts/>} />
                <Route path="/post" element={<PostDetail/>} />
            </Routes>

            {/* {!hideHeaderFooter && !isAdminRoute && <Footer />} */}
        </>
    );
};

export default App;
