import React from "react";
import { Carousel, Image } from "react-bootstrap";

import logo from "../img/ou_logo_short.png";

const BannerCarousel = () => {
    return (
        <Carousel data-bs-theme="dark">
            <Carousel.Item>
                <Image src={logo} fluid />
            </Carousel.Item>
            <Carousel.Item>
                <Image className="w-75" src={logo} fluid />
            </Carousel.Item>
        </Carousel>
    );
};

export default BannerCarousel;
