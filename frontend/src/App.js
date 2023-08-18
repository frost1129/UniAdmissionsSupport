import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./layouts/Home";
import SignIn from "./layouts/SignIn";
import SignUp from "./layouts/SignUp";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                {/* <Route path="/">
                    <Route index element={<Header />} />
                    <Route index element={<Home/>} />
                    <Route index element={<Footer />} />
                </Route> */}
                <Route path="/" element={<SignIn />} />
            </Routes>
        </BrowserRouter>
    );
};

export default App;
