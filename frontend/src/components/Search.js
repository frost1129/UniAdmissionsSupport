import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";

const Search = () => {
    return (
        <Container className="bg-primary bg-gradient p-3 text-center" fluid>
            <h3 className="text-center my-3 fw-bold text-white">Tìm kiếm thông tin</h3>
            <Container className="w-75 mx-auto">
            <InputGroup className="mb-3 shadow">
                <Form.Control
                  placeholder="Nội dung cần tìm..."
                  aria-label="Nội dung cần tìm..."
                />
                <Button variant="outline-light">
                    Tìm kiếm
                    <FontAwesomeIcon className="mx-1" icon={faSearch} />
                </Button>
            </InputGroup>
            </Container>
        </Container>
    );
};

export default Search;
