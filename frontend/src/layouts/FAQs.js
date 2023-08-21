import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import {
    Accordion,
    Button,
    Container,
    Form,
    InputGroup,
} from "react-bootstrap";

const FAQs = () => {
    return (
        <Container>
            <Container className="col-md-10 mx-auto">
                <h3 className="text-uppercase text-center p-3">
                    Các câu hỏi thường gặp
                </h3>
                <InputGroup className="mb-3 shadow-sm">
                    <Form.Control
                        placeholder="Nội dung cần tìm..."
                        aria-label="Nội dung cần tìm..."
                    />
                    <Button variant="outline-secondary">
                        Tìm kiếm
                        <FontAwesomeIcon className="mx-1" icon={faSearch} />
                    </Button>
                </InputGroup>

                <Container fluid>
                    <Accordion defaultActiveKey="0" flush>
                        <Accordion.Item eventKey="0">
                            <Accordion.Header>
                                Accordion Item #1
                            </Accordion.Header>
                            <Accordion.Body>
                                Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit, sed do eiusmod tempor
                                incididunt ut labore et dolore magna aliqua. Ut
                                enim ad minim veniam, quis nostrud exercitation
                                ullamco laboris nisi ut aliquip ex ea commodo
                                consequat. Duis aute irure dolor in
                                reprehenderit in voluptate velit esse cillum
                                dolore eu fugiat nulla pariatur. Excepteur sint
                                occaecat cupidatat non proident, sunt in culpa
                                qui officia deserunt mollit anim id est laborum.
                            </Accordion.Body>
                        </Accordion.Item>
                        <Accordion.Item eventKey="1">
                            <Accordion.Header>
                                Accordion Item #2
                            </Accordion.Header>
                            <Accordion.Body>
                                Lorem ipsum dolor sit amet, consectetur
                                adipiscing elit, sed do eiusmod tempor
                                incididunt ut labore et dolore magna aliqua.
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>
                </Container>
            </Container>
        </Container>
    );
};

export default FAQs;
