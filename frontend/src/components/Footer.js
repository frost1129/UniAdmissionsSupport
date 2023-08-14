import React from "react";
import { Row } from "react-bootstrap";
import { Link } from "react-router-dom";

import logo from "../img/ou_logo_short.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEnvelope,
    faMapMarkerAlt,
    faPhone,
} from "@fortawesome/free-solid-svg-icons";

const Footer = () => {
    return (
        <footer className="page-footer font-small blue pt-4">
            <div className="container-fluid text-center text-md-left">
                <Row>
                    <div className="col-md-4 mt-md-0 mt-3 center img-fluid">
                    <img
                        src={logo}
                        height="180"
                        className="d-inline-block align-top"
                        alt="OU footer logo"
                    />
                    </div>

                    <div className="col-md-3 mb-md-0 mb-3 text-start">
                        <h5 className="text-uppercase">Liên hệ</h5>
                        <ul className="list-unstyled">
                            <li>
                                <FontAwesomeIcon
                                    className="mx-2"
                                    icon={faMapMarkerAlt}
                                />
                                sđt: ...
                            </li>
                            <li>
                                <FontAwesomeIcon
                                    className="mx-2"
                                    icon={faPhone}
                                />
                                sđt: ...
                            </li>
                            <li>
                                <FontAwesomeIcon
                                    className="mx-2"
                                    icon={faEnvelope}
                                />
                                sđt: ...
                            </li>
                        </ul>
                    </div>

                    <div className="col-md-5 mb-md-0 mb-3 text-start">
                        <h5 className="text-uppercase">Cơ sở trực thuộc</h5>
                        <ul className="list-unstyled">
                            <li>
                                <Link
                                    to="https://goo.gl/maps/D9yyhVUe2htHkjNB7/"
                                    target="_blank"
                                    className="text-decoration-none link-dark link-opacity-75-hover"
                                > <b>Cơ sở 1:</b> 97 Võ Văn Tần, Phường 6, Quận 3, Tp.HCM</Link>
                            </li>
                        </ul>
                    </div>
                </Row>
            </div>

            <hr/>

            <div className="text-center my-3">
                © 2023 Trường Đại học Mở Thành phố Hồ Chí Minh
            </div>
        </footer>
    );
};

export default Footer;
