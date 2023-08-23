import React from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";
import Paging from "../components/Paging";
import SearchPostItem from "../components/SearchPostItem";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";

const Posts = () => {
    const postData = {
        title: "Tiêu đề bài viết 123",
        type: "Loại hình tuyển sinh",
        date: "Ngày đăng",
    };

    return (
        <Container>
            <Container className="col-md-10 mx-auto">
                <h3 className="text-uppercase text-center fw-bold p-3">
                    Tiêu đề hệ tuyển sinh
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

                <Container>
                    <SearchPostItem post={postData} />
                    <SearchPostItem post={postData} />
                    <SearchPostItem post={postData} />
                    <SearchPostItem post={postData} />
                    <SearchPostItem post={postData} />
                    <SearchPostItem post={postData} />
                    <SearchPostItem post={postData} />
                    <SearchPostItem post={postData} />
                </Container>

                <Paging />
            </Container>
        </Container>
    );
};

export default Posts;
