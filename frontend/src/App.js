import React from "react";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./layouts/Home";
import News from "./components/News";

const App = () => {
    return (
        <BrowserRouter>
<<<<<<< HEAD
            <Routes>
                {/* <Route path="/">
                    <Route index element={<Header />} />
                    <Route index element={<Home/>} />
                    <Route index element={<Footer />} />
                </Route> */}
                <Route path="/" element={<SignIn />} />
            </Routes>
=======
            <Header />

            <Routes>
                <Route path="/" element={<News/>} />
            </Routes>
            {/* <Footer /> */}
>>>>>>> implement-ui
        </BrowserRouter>
    );
};

export default App;
