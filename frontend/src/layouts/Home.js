import React from "react";
import BannerCarousel from "../components/BannerCarousel";
import Search from "../components/Search";
import FAQ from "../components/FAQ";
import UserQuestion from "../components/UserQuestion";

const Home = () => {
    return (
        <>
            <BannerCarousel/>
            <Search/>
            <FAQ/>
            <UserQuestion/>
        </>
    )
};

export default Home;
