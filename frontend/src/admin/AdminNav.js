import React, { useState } from 'react'
import { Container, Image, Nav, Row } from 'react-bootstrap'
import { Link } from 'react-router-dom'

import logo from "../img/ou_logo_long_white.png";

const AdminNav = () => {
    const [activeTab, setActiveTab] = useState("link1");

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
						<small className="fw-bold text-uppercase mb-2">Đây là text nhỏ hơn</small>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="link1" className="nav-link text-white">
								Active???
							</Nav.Link>
						</Nav.Item>
						<Nav.Item>
							<Nav.Link as={Link} eventKey="link2" className="nav-link text-white">
								Active???
							</Nav.Link>
						</Nav.Item>
					</Nav>
				</div>
			</Row>
		</Container>
	)
};

export default AdminNav;