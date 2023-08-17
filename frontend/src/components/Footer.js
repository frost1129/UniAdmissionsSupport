import React, { useEffect, useState } from "react";
import { Row } from "react-bootstrap";
import { Link } from "react-router-dom";

import logo from "../img/ou_logo_short.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEnvelope,
    faMapMarkerAlt,
    faPhone,
} from "@fortawesome/free-solid-svg-icons";
import Api, { enpoints } from "../config/Api";
import MySpinner from "./MySpinner";

const Footer = () => {
    const [info, setInfo] = useState(null);
    const [branches, setBranches] = useState(null);
    const [branchName, setBranchName] = useState(null);

    useEffect(() => {
        const loadInfo = async () => {
            let res = await Api.get(enpoints["info"]);
            setInfo(res.data);
        };

        const loadBranches = async () => {
            let res = await Api.get(enpoints["branches"]);
            setBranches(res.data);
        };

        loadInfo();
        loadBranches();
    }, []);

    useEffect(() => {
        if (info && branches) {
            const branchWithMatchingId = branches.find(b => b.id === info.admissionAddress);
            if (branchWithMatchingId) {
                setBranchName(branchWithMatchingId.address);
            }
        }
    }, [info, branches]);

    if (info === null) return <MySpinner />;

    if (branches === null) return <MySpinner />;

    return (
        <footer className="page-footer font-small blue pt-4">
            <div className="container-fluid text-center text-md-left">
                <Row>
                    <div className="col-md-3 mt-md-0 mt-3 center img-fluid">
                        <img
                            src={logo}
                            height="180"
                            className="d-inline-block align-top"
                            alt="OU footer logo"
                        />
                    </div>

                    <div className="col-md-4 mb-md-0 mb-3 text-start">
                        <h5 className="text-uppercase">Liên hệ</h5>
                        <ul className="list-unstyled">
                            <li>
                                <FontAwesomeIcon
                                    className="mx-2"
                                    icon={faMapMarkerAlt}
                                />
                                {branchName}
                            </li>
                            <li>
                                <FontAwesomeIcon
                                    className="mx-2"
                                    icon={faPhone}
                                />
                                {info.admissionPhone}
                            </li>
                            <li>
                                <FontAwesomeIcon
                                    className="mx-2"
                                    icon={faEnvelope}
                                />
                                {info.admissionEmail}
                            </li>
                        </ul>
                    </div>

                    <div className="col-md-5 mb-md-0 mb-3 text-start">
                        <h5 className="text-uppercase">Cơ sở trực thuộc</h5>
                        <ul className="list-unstyled">
                            <li>
                                {branches.map((b) => {
                                    return (
                                        <Link
                                            to={b.link}
                                            target="_blank"
                                            className="text-decoration-none link-dark link-opacity-75-hover"
                                        >
                                            <b>Cơ sở {b.id}:</b> {b.address} <br/>
                                        </Link>
                                    );
                                })}
                            </li>
                        </ul>
                    </div>
                </Row>
            </div>

            <hr />

            <div className="text-center my-3">
                © 2023 Trường Đại học Mở Thành phố Hồ Chí Minh
            </div>
        </footer>
    );
};

export default Footer;
