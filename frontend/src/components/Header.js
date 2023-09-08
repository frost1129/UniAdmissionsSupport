import React, { useContext, useEffect, useState } from "react";
import { Container, Nav, NavDropdown, Navbar } from "react-bootstrap";

import logo from "../img/ou_logo_long.png";
import UserDetail from "./UserDetail";
import { Link, useLocation, useNavigate } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import MySpinner from "./MySpinner";
import { MyUserContext } from "../App";

const Header = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [showUserDetail, setShowUserDetail] = useState(false);
    const [toFaculties, setToFaculties] = useState(false);
    const [admissionTypes, setAdmissionTypes] = useState(null);
    const [topics, setTopics] = useState([]);
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
        const loadAdmissionType = async () => {
            let {data} = await Api.get(endpoints["admissions"]);
            setAdmissionTypes(data);
        }

        const loadTopics = async () => {
            let {data} = await Api.get(endpoints["topics"]);
            setTopics(data);
        }
        
        loadAdmissionType();
        loadTopics();
    }, []);

    useEffect(() => {
        if (toFaculties) {
            scrollToFaculties();
            setToFaculties(false);
        }
    }, [toFaculties]);

    useEffect(() => {
        window.scrollTo(0, 0);
      }, [location]);

    if (admissionTypes === null || topics === null) return <MySpinner />;

    return (
        <>
            <Navbar expand="lg" className="bg-body-tertiary">
                <Container>
                    <Link to='/' className="text-decoration-none">
                        <Navbar.Brand>
                            <img
                                src={logo}
                                height="60"
                                className="d-inline-block align-top"
                                alt="OU logo"
                            />
                        </Navbar.Brand>
                    </Link>

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
                                    {admissionTypes.map(type => {
                                        let h = `/posts?admissionType=${type.id}`;
                                        return <NavDropdown.Item>
                                                <Link to={h} key={type.id} className="text-decoration-none text-dark">
                                                    {type.name}
                                                </Link>
                                            </NavDropdown.Item>
                                    })}
                                    
                                </NavDropdown>

                                <Nav.Link onClick={handleToFaculties}>
                                    Thông tin các Khoa
                                </Nav.Link>

                                <NavDropdown
                                    title="Có thể bạn quan tâm"
                                    id="basic-nav-dropdown"
                                >
                                    {topics.map(topic => 
                                    <NavDropdown.Item key={topic.id}>
                                        <Link to={`/posts/${topic.postId}`} className="text-decoration-none text-dark">
                                            {topic.title}
                                        </Link>
                                    </NavDropdown.Item>
                                    )}
                                </NavDropdown>

                                <Nav.Link>  
                                    <Link to='/faqs' className="text-decoration-none text-dark">
                                        FAQs
                                    </Link>
                                </Nav.Link>
                                {user === null ? 
                                    <Link to='/login' className="btn btn-primary text-decoration-none">
                                        Đăng nhập
                                    </Link> : 
                                    <NavDropdown
                                        className="fw-bold text-dark"
                                        title="Xin chào, user"
                                        id="basic-nav-dropdown"
                                    >
                                        <NavDropdown.Item>
                                            <Link
                                                className="text-decoration-none text-dark"
                                                onClick={handleShowUserDetail}
                                            >
                                                Thông tin tài khoản
                                            </Link>
                                        </NavDropdown.Item>
                                        {/* <NavDropdown.Item>
                                            <Link to='/admin' className="text-decoration-none text-dark">
                                                Bảng điều khiển
                                            </Link>
                                        </NavDropdown.Item> */}
                                        <NavDropdown.Item>
                                            <Link to='/question-manage' className="text-decoration-none text-dark">
                                                Quản lý câu hỏi
                                            </Link>
                                        </NavDropdown.Item>
                                        <NavDropdown.Item>
                                            <Link className="text-decoration-none text-dark">
                                                Đăng xuất
                                            </Link>
                                        </NavDropdown.Item>
                                    </NavDropdown>
                                }       
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
