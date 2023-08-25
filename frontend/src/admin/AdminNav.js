import React, { useState } from 'react'
import { Container, Image, Nav, Row } from 'react-bootstrap'
import { Link } from 'react-router-dom'

import logo from "../img/ou_logo_long_white.png";

const AdminNav = () => {
    const [activeTab, setActiveTab] = useState("banner");

	return (
		<Container fluid>
			<Row>
				<div className="bg-dark col-auto col-md-3 col-lg-2 min-vh-100 text-white">
					<Link to="/admin" className="text-decoration-none d-flex align-items-center">
                        <Image
                            src={logo}
                            fluid
                            className="py-5"
                            alt="OU logo"
                        />
                        </Link>
					<Nav 
						variant="pills" 
						className="flex-column" 
						activeKey={activeTab}
						onSelect={(key) => setActiveTab(key)}
					>
						<small className="fw-bold text-uppercase mb-2">Thông tin chung</small>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="banner" className="nav-link text-white">
								Quản lý Banner
							</Nav.Link>
						</Nav.Item>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="users" className="nav-link text-white">
								Quản lý người dùng
							</Nav.Link>
						</Nav.Item>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="faculties" className="nav-link text-white">
								Quản lý Khoa
							</Nav.Link>
						</Nav.Item>

						<small className="fw-bold text-uppercase mb-2">Thông tin tuyển sinh</small>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="popular" className="nav-link text-white">
								Bài đăng nổi bật
							</Nav.Link>
						</Nav.Item>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="post" className="nav-link text-white">
								Bài đăng tuyển sinh
							</Nav.Link>
						</Nav.Item>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="livestream" className="nav-link text-white">
								Livestream tuyển sinh
							</Nav.Link>
						</Nav.Item>
					</Nav>
				</div>
			</Row>
		</Container>
	)
};

export default AdminNav;