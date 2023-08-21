import React from "react";
import BannerCarousel from "../components/BannerCarousel";
import Search from "../components/Search";
import FAQs from "../components/FAQs";
import UserQuestion from "../components/UserQuestion";

const Home = () => {
    return (
        <>
            <BannerCarousel/>
            <Search/>
            <FAQs/>
            <UserQuestion/>
        </>
    )
};

export default Home;
