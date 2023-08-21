import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Accordion, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

const FAQ = () => {
    return (
        <Container fluid>
            <h3 className="text-center my-3">Các câu hỏi thường gặp</h3>
            <Accordion defaultActiveKey="0" className="col-md-10 mx-auto" flush>
                <Accordion.Item eventKey="0">
                    <Accordion.Header>Accordion Item #1</Accordion.Header>
                    <Accordion.Body>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                        sed do eiusmod tempor incididunt ut labore et dolore
                        magna aliqua. Ut enim ad minim veniam, quis nostrud
                        exercitation ullamco laboris nisi ut aliquip ex ea
                        commodo consequat. Duis aute irure dolor in
                        reprehenderit in voluptate velit esse cillum dolore eu
                        fugiat nulla pariatur. Excepteur sint occaecat cupidatat
                        non proident, sunt in culpa qui officia deserunt mollit
                        anim id est laborum.
                    </Accordion.Body>
                </Accordion.Item>
                <Accordion.Item eventKey="1">
                    <Accordion.Header>Accordion Item #2</Accordion.Header>
                    <Accordion.Body>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                        sed do eiusmod tempor incididunt ut labore et dolore
                        magna aliqua.
                    </Accordion.Body>
                </Accordion.Item>
            </Accordion>
            <Row className="my-1">
                <Link className="text-decoration-none text-center text-uppercase fw-bold my-3">
                    Xem tất cả câu hỏi thường gặp
                    <FontAwesomeIcon icon={faArrowRight} className="mx-2" />
                </Link>
            </Row>
        </Container>
    );
};

export default FAQ;
