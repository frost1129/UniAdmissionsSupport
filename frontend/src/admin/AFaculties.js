import React from "react";
import { Container, Row } from "react-bootstrap";
import AdminNav from "./AdminNav";

const AFaculties = () => {
    return (
        <Container className="d-flex" fluid>
            <Row>
                <div className="col-3 col-xl-2 bg-dark">
                    <AdminNav />
                </div>
                <div className="col-9 col-xl-10 overflow-y-scroll">Faculties</div>
            </Row>
        </Container>
    );
};

export default AFaculties;
