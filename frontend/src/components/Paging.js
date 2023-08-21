import React from "react";
import { Container, Pagination } from "react-bootstrap";

const Paging = () => {
    let active = 1;
    let items = [];
    for (let number = 1; number <= 5; number++) {
        items.push(
            <Pagination.Item key={number} active={number === active}>
                {number}
            </Pagination.Item>
        );
    }

    return (
        <Container>
            <Pagination className="justify-content-center">{items}</Pagination>
        </Container>
    );
};

export default Paging;
