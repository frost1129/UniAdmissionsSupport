import React, { useState } from "react";
import { Button, Container, Form, InputGroup } from "react-bootstrap";
import Paging from "../components/Paging";
import SearchPostItem from "../components/SearchPostItem";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";

const Posts = () => {
    const postData = {
        title: "Tiêu đề bài viết 123",
        type: "Loại hình tuyển sinh",
        date: "Ngày đăng",
    };

    const [kw, setKw] = useState("");
    const nav = useNavigate();

    const search = (evt) => {
        evt.preventDefault();
        nav(`/posts/?kw=${kw}`)
    }

    return (
        <Container className="bg-white">
            <Container className="col-md-10 mx-auto bg-white">
                <h3 className="text-uppercase text-center fw-bold p-3">
                    Tiêu đề hệ tuyển sinh
                </h3>
                <Form onSubmit={search}>
                    <InputGroup className="mb-3 shadow-sm">
                        <Form.Control
                            type="text"
                            value={kw}
                            name="kw"
                            onChange={e => setKw(e.target.value)}
                            placeholder="Nội dung cần tìm..."
                        />
                        <Button type="submit" variant="outline-secondary">
                            Tìm kiếm
                            <FontAwesomeIcon className="mx-1" icon={faSearch} />
                        </Button>
                    </InputGroup>
                </Form>

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
