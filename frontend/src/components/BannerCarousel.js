import React, { useEffect, useState } from "react";
import { Carousel, Image } from "react-bootstrap";

import logo from "../img/ou_logo_short.png";
import Api, { enpoints } from "../config/Api";
import MySpinner from "./MySpinner";

const BannerCarousel = () => {
    const [banners, setBanners] = useState(null);

    useEffect(() => {
        const loadBanners = async () => {
            let res = await Api.get(enpoints["banners"]);
            setBanners(res.data);
        };

        loadBanners();
    }, []);

    if (banners === null) return <MySpinner />;

    return (
        <Carousel data-bs-theme="dark">
            {banners.map((banner) => {
                return (
                    <Carousel.Item>
                        <Image src={banner.image} fluid />
                    </Carousel.Item>
                );
            })}
        </Carousel>
    );
};

export default BannerCarousel;
