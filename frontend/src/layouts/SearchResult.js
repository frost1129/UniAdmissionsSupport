import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import { Alert, Button, Container, Form, InputGroup, Pagination } from "react-bootstrap";
import SearchPostItem from "../components/SearchPostItem";
import Paging from "../components/Paging";
import { useNavigate, useSearchParams } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import { formatTimestamp } from "../config/Timestamp";
import MySpinner from "../components/MySpinner";

const SearchResult = () => {
    const [posts, setPosts] = useState(null);

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
        nav(`/search/?kw=${kw}`);
    }

    useEffect(() => {
        const loadCounter = async () => {
            let e = endpoints["posts-count"];
            e = `${e}?`

            let searchKw = q.get("kw");
            if (searchKw !== null) {
                e = `${e}kw=${searchKw}`;
            }

            let resCount = await Api.get(e);
            setCounter(resCount.data);
        }

        const loadPosts = async () => {
            try {
                let e = endpoints["posts"];
                e = `${e}?`

                let searchKw = q.get("kw");
                if (searchKw !== null) {
                    e = `${e}kw=${searchKw}`;
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

        loadPosts();
        loadCounter();
        setKw("");
    }, [q]);

    if (posts === null || counter === null) return <MySpinner />;

    console.log(posts);

    return (
        <Container className="bg-white">
            <Container className="col-md-10 mx-auto bg-white">
                <h3 className="text-uppercase text-center p-3">
                    Kết quả tìm kiếm
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

export default SearchResult;
