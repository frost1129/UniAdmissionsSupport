import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useState } from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Search = () => {
    const [kw, setKw] = useState("");
    const nav = useNavigate();

    const search = (evt) => {
        evt.preventDefault();
        nav(`/search/?kw=${kw}`)
    }

    return (
        <Container className="bg-primary bg-gradient p-3 text-center" fluid>
            <h3 className="text-center my-3 fw-bold text-white">Tìm kiếm thông tin</h3>
            <Container className="w-75 mx-auto">
            <Form onSubmit={search}>
                <InputGroup className="mb-3 shadow">
                    <Form.Control
                        type="text"
                        value={kw}
                        name="kw"
                        onChange={e => setKw(e.target.value)}
                        placeholder="Nội dung cần tìm..."
                    />
                    <Button type="submit" variant="outline-light">
                        Tìm kiếm
                        <FontAwesomeIcon className="mx-1" icon={faSearch} />
                    </Button>
                </InputGroup>
            </Form>
            </Container>
        </Container>
    );
};

export default Search;
