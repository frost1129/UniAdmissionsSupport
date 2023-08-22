import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./layouts/Home";
import SearchResult from "./layouts/SearchResult";
import FAQs from "./layouts/FAQs";
import PostDetail from "./layouts/PostDetail";

const App = () => {
    return (
        <BrowserRouter>
            <Header />

            <Routes>
                <Route path="/" element={<Home/>} /> 
                <Route path="/search" element={<SearchResult/>} />
                <Route path="/faqs" element={<FAQs/>} />
                <Route path="/post" element={<PostDetail/>} />
            </Routes>
            {/* <Footer /> */}
        </BrowserRouter>
    );
};

export default App;
