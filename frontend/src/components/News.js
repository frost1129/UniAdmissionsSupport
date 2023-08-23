import React from "react";
import { Container, Image, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import "./news.css";
import Img from "../img/login_img.png";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClock } from "@fortawesome/free-solid-svg-icons";

const News = () => {
    return (
        <Container className="section col-md-10 mx-auto my-3">
            <Row className="my-3 p-2 rounded shadow-sm">
            <h3 className="text-sm-start text-center fw-bold">Section title</h3>
                <div className="col-sm-12 col-md-7 col-xs-12 col-lg-7">
                    <div className="thumb-container">
                        <Link>
                            <Image className="thumb mb-3 border-2" src={Img} />
                        </Link>
                    </div>
                    <div>
                        <small className="text-secondary">
                            <FontAwesomeIcon icon={faClock} className="mx-1" />
                            Timestamp
                        </small>
                    </div>
                    <Link className="text-decoration-none">
                        <h3>Title of the paper is here</h3>
                    </Link>
                </div>

                <div className="col-sm-12 col-md-5 col-xs-12 col-lg-5">
                    <Row className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link>
                                    <Image className="thumb img-md" src={Img} />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <FontAwesomeIcon icon={faClock} className="mx-1" />
                                Timestamp
                            </small>
                            <Link className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    Title of the paper is here and super duper long long long long long long ...
                                </p>
                            </Link>
                        </div>
                    </Row>

                    <Row className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link>
                                    <Image className="thumb img-md" src={Img} />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <FontAwesomeIcon icon={faClock} className="mx-1" />
                                Timestamp
                            </small>
                            <Link className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    Title of the paper is here and super duper long long long long long long ...
                                </p>
                            </Link>
                        </div>
                    </Row>

                    <Row className="my-1">
                        <Link className="text-decoration-none text-center text-uppercase">
                            <small>Xem tất cả</small>
                        </Link>
                    </Row>
                </div>
            </Row>

            <Row className="rounded shadow-sm">
                <div className="col-sm-12 col-md-6 col-xs-12 col-lg-6 my-3">
                    <h3 className="text-center">Section 2 title</h3> 
                    <Row className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link>
                                    <Image className="thumb img-md" src={Img} />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <FontAwesomeIcon icon={faClock} className="mx-1" />
                                Timestamp
                            </small>
                            <Link className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    Title of the paper is here and super duper long long long long long long ...
                                </p>
                            </Link>
                        </div>
                    </Row> 

                    <Row className="my-1">
                        <Link className="text-decoration-none text-center text-uppercase">
                            <small>Xem tất cả</small>
                        </Link>
                    </Row>             
                </div>

                <div className="col-sm-12 col-md-6 col-xs-12 col-lg-6 my-3">
                    <h3 className="text-center">Section 3 title</h3> 
                    <Row className="mt-3 news-md">
                        <div className="col-4">
                            <div className="thumb-container">
                                <Link>
                                    <Image className="thumb img-md" src={Img} />
                                </Link>
                            </div>
                        </div>
                        <div className="col-8">
                            <small className="text-secondary">
                                <FontAwesomeIcon icon={faClock} className="mx-1" />
                                Timestamp
                            </small>
                            <Link className="text-decoration-none">
                                <p className="mb-4 link-dark link-opacity-50-hover">
                                    Title of the paper is here and super duper long long long long long long ...
                                </p>
                            </Link>
                        </div>
                    </Row>  

                    <Row className="my-1">
                        <Link className="text-decoration-none text-center text-uppercase">
                            <small>Xem tất cả</small>
                        </Link>
                    </Row>            
                </div>
                
            </Row>

        </Container>

        
    );
};

export default News;
