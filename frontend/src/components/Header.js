import React, { useEffect, useState } from "react";
import { Button, Container, Nav, NavDropdown, Navbar } from "react-bootstrap";

import logo from "../img/ou_logo_long.png";
import UserDetail from "./UserDetail";
import { Link, useLocation, useNavigate } from "react-router-dom";

const Header = () => {
    const [showUserDetail, setShowUserDetail] = useState(false);
    const [toFaculties, setToFaculties] = useState(false);
    const navigate = useNavigate();
    const location = useLocation();

    const handleShowUserDetail = () => {
        setShowUserDetail(true);
    };

    const scrollToFaculties = () => {
        const facultiesComponent = document.getElementById('faculties');
        if (facultiesComponent) {
            facultiesComponent.scrollIntoView({ behavior: 'smooth' });
        }
    };

    const handleToFaculties = () => {
        if (location.pathname === '/') {
            scrollToFaculties();
        } else {
            setToFaculties(true);
            navigate('/');
        }
    };

    useEffect(() => {
        if (toFaculties) {
            scrollToFaculties();
            setToFaculties(false);
        }
    }, [toFaculties]);

    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Navbar.Brand href="#home">
                        <img
                            src={logo}
                            height="60"
                            className="d-inline-block align-top"
                            alt="OU logo"
                        />
                    </Navbar.Brand>

                    <span>
                        <Navbar.Collapse
                            id="basic-navbar-nav"
                            className="float-end"
                        >
                            <Nav className="me-auto">
                                <NavDropdown
                                    title="Hệ tuyển sinh"
                                    id="basic-nav-dropdown"
                                >
                                    <NavDropdown.Item href="#action/3.12">
                                        Đại học chính quy
                                    </NavDropdown.Item>
                                    <NavDropdown.Item href="#action/3.22">
                                        Văn bằng 2, Liên thông Cao đẳng-Đại Học
                                    </NavDropdown.Item>
                                    <NavDropdown.Item href="#action/3.23">
                                        Sau đại học
                                    </NavDropdown.Item>
                                </NavDropdown>
                                <Nav.Link onClick={handleToFaculties}>
                                    Thông tin các Khoa
                                </Nav.Link>
                                <NavDropdown
                                    title="Có thể bạn quan tâm"
                                    id="basic-nav-dropdown"
                                >
                                    <NavDropdown.Item href="#action/3.1">
                                        Điểm chuẩn các năm
                                    </NavDropdown.Item>
                                    <NavDropdown.Item href="#action/3.2">
                                        Tỉ lệ có việc làm
                                    </NavDropdown.Item>
                                    <NavDropdown.Item href="#action/3.3">
                                        Học bổng tuyển sinh
                                    </NavDropdown.Item>
                                    <NavDropdown.Item href="#action/3.4">
                                        Học phí năm học 2023 - 2024
                                    </NavDropdown.Item>
                                    <NavDropdown.Item href="#action/3.5">
                                        Các văn bản pháp lý
                                    </NavDropdown.Item>
                                </NavDropdown>
                                <Nav.Link href="#faqs">FAQs</Nav.Link>
                                <Nav.Link href="#login" className="fw-bold">
                                    Đăng nhập
                                </Nav.Link>
                                <NavDropdown
                                    className="fw-bold"
                                    title="Xin chào, user"
                                    id="basic-nav-dropdown"
                                >
                                    <NavDropdown.Item>
                                        <Link
                                            className="text-decoration-none text-black"
                                            onClick={handleShowUserDetail}
                                        >
                                            Thông tin tài khoản
                                        </Link>
                                    </NavDropdown.Item>
                                    <NavDropdown.Item>
                                        <Link className="text-decoration-none text-black">
                                            Đăng xuất
                                        </Link>
                                    </NavDropdown.Item>
                                </NavDropdown>
                            </Nav>
                        </Navbar.Collapse>
                    </span>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                </Container>
            </Navbar>

            {/* HIỂN THỊ USER DETAILS */}
            {showUserDetail && (
                <UserDetail onClose={() => setShowUserDetail(false)} />
            )}
        </>
    );
};

export default Header;
