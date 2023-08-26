import React, { useEffect, useState } from "react";
import { Image, Nav } from "react-bootstrap";
import { Link, useLocation } from "react-router-dom";

import logo from "../img/ou_logo_long_white.png";

const AdminNav = () => {
	const location = useLocation();
    const [activeTab, setActiveTab] = useState("");

	const tabs = [
        { key: 'main', path: '/admin' },
        { key: 'users', path: '/admin/users' },
        { key: 'faculties', path: '/admin/faculties' },
        { key: 'post', path: '/admin/post' },
        { key: 'livestream', path: '/admin/livestream' },
    ];

    useEffect(() => {
        const matchingTab = tabs.find(tab => tab.path === location.pathname);
        if (matchingTab) {
            setActiveTab(matchingTab.key);
        }
    }, [location.pathname, tabs]);

    return (
        <div className="bg-dark min-vh-100 text-white">
			<Link
				to="/admin"
				className="text-decoration-none d-flex align-items-center"
			>
				<Image src={logo} fluid className="py-5" alt="OU logo" />
			</Link>
			<Nav
				variant="pills"
				className="flex-column"
				activeKey={activeTab}
			>
				<small className="fw-bold text-uppercase mb-2">
					Thông tin chung
				</small>
				<Nav.Item>
					<Nav.Link
						as={Link}
						to="/admin"
						eventKey="main"
						className="nav-link text-white"
					>
						Trang chủ
					</Nav.Link>
				</Nav.Item>
				<Nav.Item>
					<Nav.Link
						as={Link}
						to="/admin/users"
						eventKey="users"
						className="nav-link text-white"
					>
						Quản lý người dùng
					</Nav.Link>
				</Nav.Item>
				<Nav.Item>
					<Nav.Link
						as={Link}
						to="/admin/faculties"
						eventKey="faculties"
						className="nav-link text-white"
					>
						Quản lý Khoa
					</Nav.Link>
				</Nav.Item>

				<small className="fw-bold text-uppercase mb-2">
					Thông tin tuyển sinh
				</small>
				<Nav.Item>
					<Nav.Link
						as={Link}
						to="/admin/post"
						eventKey="post"
						className="nav-link text-white"
					>
						Bài đăng tuyển sinh
					</Nav.Link>
				</Nav.Item>
				<Nav.Item>
					<Nav.Link
						as={Link}
						to="/admin/livestream"
						eventKey="livestream"
						className="nav-link text-white"
					>
						Livestream tuyển sinh
					</Nav.Link>
				</Nav.Item>
			</Nav>
        </div>
    );
};

export default AdminNav;
