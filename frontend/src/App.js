import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./layouts/Home";
import News from "./components/News";

const App = () => {
    return (
        <BrowserRouter>
            <Header />

            <Routes>
                <Route path="/" element={<News/>} />
            </Routes>
            {/* <Footer /> */}
        </BrowserRouter>
    );
};

export default App;
