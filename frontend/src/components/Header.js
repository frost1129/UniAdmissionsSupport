import React from "react";
import { Container, Nav, NavDropdown, Navbar } from "react-bootstrap";

import logo from "../img/ou_logo_long.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faRightToBracket } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

const Header = () => {
    return (
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
                    <Navbar.Collapse id="basic-navbar-nav" className="float-end" >
                        <Nav className="me-auto">
                            <NavDropdown title="Hệ tuyển sinh" id="basic-nav-dropdown">
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
                            <Nav.Link href="#faculties">Thông tin Khoa-Ngành</Nav.Link>
                            <NavDropdown title="Có thể bạn quan tâm" id="basic-nav-dropdown">
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
                            <Nav.Link href="#login">
                                <FontAwesomeIcon className="mx-2" icon={faRightToBracket} />
                                Đăng nhập
                            </Nav.Link>
                            {/* <NavDropdown title="Xin chào, user" id="basic-nav-dropdown">
                                <NavDropdown.Item href="#xemthongtin">
                                    Thông tin cá nhân
                                </NavDropdown.Item>
                                <NavDropdown.Item>
                                    <Link className="text-decoration-none text-black">Đăng xuất</Link>
                                </NavDropdown.Item>
                            </NavDropdown> */}
                        </Nav>
                    </Navbar.Collapse>
                </span>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            </Container>
        </Navbar>
    );
};

export default Header;
