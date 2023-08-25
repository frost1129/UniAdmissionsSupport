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
import Admin from "./layouts/Admin";

const App = () => {
    const location = useLocation();
    const pathToHideHF = ['/login', '/signup', '/admin'];
    const hideHeaderFooter = pathToHideHF.includes(location.pathname);

    return (
        <>
            {!hideHeaderFooter && <Header />}

            <Routes>
                <Route path="/" element={<Home/>} /> 
                <Route path="/login" element={<SignIn/>} /> 
                <Route path="/signup" element={<SignUp/>} /> 
                <Route path="/search" element={<SearchResult/>} />
                <Route path="/question-manage" element={<QuestionManage/>} />
                <Route path="/admin" element={<Admin/>} />
                <Route path="/faqs" element={<FAQs/>} />
                <Route path="/posts" element={<Posts/>} />
                <Route path="/post" element={<PostDetail/>} />
            </Routes>

            {/* {!hideHeaderFooter && <Footer />} */}
        </>
    );
};

export default App;
