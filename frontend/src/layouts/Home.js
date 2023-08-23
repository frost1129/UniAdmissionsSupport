import React from "react";
import BannerCarousel from "../components/BannerCarousel";
import Search from "../components/Search";
import FAQ from "../components/FAQ";
import UserQuestion from "../components/UserQuestion";
import News from "../components/News";
import Faculties from "../components/Faculties";

const Home = () => {
    return (
        <>
            <BannerCarousel/>
            <Search/>
            <News/>
            <Faculties/>
            <UserQuestion/>
            <FAQ/>
        </>
    )
};

export default Home;
