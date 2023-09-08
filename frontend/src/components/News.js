import React, { useEffect, useState } from "react";
import { Container, Image, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import "./news.css";
import Img from "../img/login_img.png";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClock } from "@fortawesome/free-solid-svg-icons";
import Api, { endpoints } from "../config/Api";
import MySpinner from "./MySpinner";

const News = () => {

    const [admissionTypes, setAdmissionTypes] = useState([]);
    const [post1, setPost1] = useState([]);
    const [post1Remain, setPost1Remain] = useState([]);
    const [post2, setPost2] = useState([]);
    const [post3, setPost3] = useState([]);

    const formatTimestamp = (timestamp) => {
        const date = new Date(timestamp);
        const hours = date.getHours().toString().padStart(2, '0');
        const minutes = date.getMinutes().toString().padStart(2, '0');
        const seconds = date.getSeconds().toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();

        return `${hours}:${minutes}:${seconds} ${day}/${month}/${year}`;
    }

    useEffect(() => {
        const loadAdmissionType = async () => {
            let {data} = await Api.get(endpoints["admissions"]);
            setAdmissionTypes(data);
        }

        const loadPost1 = async () => {
            let {data} = await Api.get(endpoints["top-admission-post"](1));
            const formattedData = data.map(post => ({
                ...post,
                updatedDate: formatTimestamp(post.updatedDate),
            }));
            setPost1(formattedData[0]);
            setPost1Remain(formattedData.slice(1));
        }

        const loadPost2 = async () => {
            let {data} = await Api.get(endpoints["top-admission-post"](2));
            const formattedData = data.map(post => ({
                ...post,
                updatedDate: formatTimestamp(post.updatedDate),
            }));
            setPost2(formattedData);
        }

        const loadPost3 = async () => {
            let {data} = await Api.get(endpoints["top-admission-post"](3));
            const formattedData = data.map(post => ({
                ...post,
                updatedDate: formatTimestamp(post.updatedDate),
            }));
            setPost3(formattedData);
        }
        
        loadAdmissionType();
        loadPost1();
        loadPost2();
        loadPost3();
    }, []);

    if (admissionTypes === null || post1 === null || post2 === null || post3 === null) 
        return <MySpinner />;

    return (
        <Container className="section col-md-10 mx-auto my-3 bg-white">

            <Row className="my-3 p-2 rounded shadow-sm">
{/* SECTION 1 */}
                <h3 className="text-sm-start text-center fw-bold">
                    <Link className="text-decoration-none link-dark">
                        Đại học chính quy
                    </Link>
                </h3>

                <div key={post1.id} className="col-sm-12 col-md-7 col-xs-12 col-lg-7">
                    <div className="thumb-container">
                        <Link>
                            <Image className="thumb mb-3 border-2" alt={post1.title} src={post1.image} rounded />
                        </Link>
                    </div>
                    <div>
                        <small className="text-secondary">
                            <FontAwesomeIcon icon={faClock} className="mx-1" />
                            {post1.updatedDate}
                        </small>
                    </div>
                    <Link className="text-decoration-none">
                        <h3>{post1.title}</h3>
                    </Link>
                </div>

                <div className="col-sm-12 col-md-5 col-xs-12 col-lg-5">
                    {post1Remain.map(post => 
                    <Row key={post.id} className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link>
                                    <Image className="thumb img-md" alt={post.title} src={post.image} rounded />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <FontAwesomeIcon
                                    icon={faClock}
                                    className="mx-1"
                                />
                                {post.updatedDate}
                            </small>
                            <Link className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    {post.title}
                                </p>
                            </Link>
                        </div>
                    </Row>
                    )}


                    <Row className="my-1">
                        <Link to='/posts?admissionType=1' className="text-decoration-none text-center text-uppercase">
                            <small>Xem tất cả</small>
                        </Link>
                    </Row>
                </div>
            </Row>

{/* SECTION 2 */}
            <Row className="rounded shadow-sm">
                <div className="col-sm-12 col-md-6 col-xs-12 col-lg-6 my-3">
                    <h4 className="text-center fw-bold">
                        <Link className="text-decoration-none link-dark">
                            Văn bằng 2 và Liên thông Cao đẳng - Đại học
                        </Link>
                    </h4>

                    {post2.map(post => 
                    <Row key={post.id} className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link>
                                    <Image className="thumb img-md" alt={post.title} src={post.image} rounded />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <FontAwesomeIcon
                                    icon={faClock}
                                    className="mx-1"
                                />
                                {post.updatedDate}
                            </small>
                            <Link className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    {post.title}
                                </p>
                            </Link>
                        </div>
                    </Row>
                    )}

                    <Row className="my-1">
                        <Link to='/posts?admissionType=2' className="text-decoration-none text-center text-uppercase">
                            <small>Xem tất cả</small>
                        </Link>
                    </Row>
                </div>

{/* SECTION 3 */}
                <div className="col-sm-12 col-md-6 col-xs-12 col-lg-6 my-3">
                    <h4 className="text-center fw-bold">
                        <Link className="text-decoration-none link-dark">
                            Sau đại học
                        </Link>
                    </h4>

                    {post3.map(post => 
                    <Row key={post.id} className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link>
                                    <Image className="thumb img-md" alt={post.title} src={post.image} rounded />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <FontAwesomeIcon
                                    icon={faClock}
                                    className="mx-1"
                                />
                                {post.updatedDate}
                            </small>
                            <Link className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    {post.title}
                                </p>
                            </Link>
                        </div>
                    </Row>
                    )}

                    <Row className="my-1">
                        <Link to='/posts?admissionType=3' className="text-decoration-none text-center text-uppercase">
                            <small>Xem tất cả</small>
                        </Link>
                    </Row>
                </div>
            </Row>


        </Container>
    );
};

export default News;
