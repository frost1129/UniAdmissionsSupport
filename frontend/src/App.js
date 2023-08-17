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
            {/* <Header /> */}

            <Routes>
                {/* <Route path="/" element={<Home/>} /> */}
                <Route path="/" element={<SignIn />} />
            </Routes>
            <SignUp />
            {/* <Footer /> */}
        </BrowserRouter>
    );
};

export default App;
