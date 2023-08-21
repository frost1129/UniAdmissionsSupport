import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./layouts/Home";
import SearchResult from "./layouts/SearchResult";

const App = () => {
    return (
        <BrowserRouter>
            <Header />

            <Routes>
                <Route path="/" element={<Home/>} />
                <Route path="/search" element={<SearchResult/>} />
            </Routes>
            {/* <Footer /> */}
        </BrowserRouter>
    );
};

export default App;
