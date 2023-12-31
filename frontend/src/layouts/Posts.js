import React, { useEffect, useState } from "react";
import { Alert, Button, Container, Form, InputGroup, Pagination } from "react-bootstrap";
import Paging from "../components/Paging";
import SearchPostItem from "../components/SearchPostItem";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import { formatTimestamp } from "../config/Timestamp";

const Posts = () => {
    const [posts, setPosts] = useState(null);
    const [type, setType] = useState(null);
 
    const [counter, setCounter] = useState(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [q, setQ] = useSearchParams();
    const [kw, setKw] = useState("");
    const nav = useNavigate();

    let items = [];
    for (let number = 1; number <= counter; number++) {
        items.push(
            <Pagination.Item 
                key={number} 
                active={number === currentPage}
                onClick={() => handlePageClick(number)}
            >
                {number}
            </Pagination.Item>
        );
    }

    const handlePageClick = (pageNum) => {
        let updatedSearchParams = new URLSearchParams(q.toString());
        updatedSearchParams.set('page', pageNum);
        setQ(updatedSearchParams.toString());
        setCurrentPage(pageNum);
    }

    const search = (evt) => {
        evt.preventDefault();
        setCurrentPage(1);
        nav(`/posts/?admissionType=${type.id}&page=${1}&kw=${kw}`);
    }

    useEffect(() => {
        const loadAdmissionType = async () => {
            let admissionId = q.get("admissionType");
            if (admissionId !== null) {
                let {data} = await Api.get(endpoints["admission"](admissionId));
                setType(data);
            }
        }

        const loadCounter = async () => {
            let e = endpoints["posts-count"];
            e = `${e}?`

            let admissionType = q.get("admissionType");
            if (admissionType !== null)
                e = `${e}admissionType=${admissionType}`;

            let searchKw = q.get("kw");
            if (searchKw !== null) {
                e = `${e}&kw=${searchKw}`;
            }

            let resCount = await Api.get(e);
            setCounter(resCount.data);
        }

        const loadPosts = async () => {
            try {
                let e = endpoints["posts"];
                e = `${e}?`

                let admissionType = q.get("admissionType");
                if (admissionType !== null)
                    e = `${e}admissionType=${admissionType}`;

                let searchKw = q.get("kw");
                if (searchKw !== null) {
                    e = `${e}&kw=${searchKw}`;
                }

                let paging = q.get("page");
                if (paging !== null) 
                    e = `${e}&page=${paging}`;
                else 
                    e = `${e}&page=1`;


                let res = await Api.get(e);

                const formattedData = res.data.map(post => ({
                    ...post,
                    updatedDate: formatTimestamp(post.updatedDate),
                }));

                setPosts(formattedData);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadAdmissionType();
        loadPosts();
        loadCounter();
        setKw("");
    }, [q]);


    if (posts === null || counter === null || type === null) return <MySpinner />;

    return (
        <Container className="bg-white">
            <Container className="col-md-10 mx-auto bg-white">
                <h3 className="text-uppercase text-center fw-bold p-3 text-primary">
                    Thông tin tuyển sinh {type === null ? "" : type.name}
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

                {posts.length === 0 ? <Alert variant="warning">Không có bài viết nào với từ khóa này</Alert> : 
                    <Container className="mb-3">
                        {posts.map(post => 
                            <SearchPostItem key={post.id} post={post} />
                        )}
                    </Container>
                }

                {counter === 0 ? "" : 
                    <Pagination className="justify-content-center">{items}</Pagination>
                }
            </Container>
        </Container>
    );
};

export default Posts;
