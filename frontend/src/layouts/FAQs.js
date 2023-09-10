import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import {
    Accordion,
    Alert,
    Button,
    Container,
    Form,
    InputGroup,
    Pagination,
} from "react-bootstrap";
import Api, { endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import QuillHtmlRender from "../components/QuillHtmlRender";
import { useNavigate, useSearchParams } from "react-router-dom";

const FAQs = () => {
    const [faqs, setFaqs] = useState([]);

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
        nav(`/faqs/?kw=${kw}`);
    }

    useEffect(() => {
        const loadCounter = async () => {
            let e = endpoints["faqs-count"];
            e = `${e}?`

            let searchKw = q.get("kw");
            if (searchKw !== null) {
                e = `${e}kw=${searchKw}`;
            }

            let resCount = await Api.get(e);
            setCounter(resCount.data);
        }

        const loadFaqs = async () => {
            try {
                let e = endpoints["faqs"];
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

                setFaqs(res.data);
            } catch (ex) {
                console.error(ex);
            }
        }

        loadCounter();
        loadFaqs();
    }, [q]);

    if (faqs === null || counter === null) return <MySpinner />;

    return (
        <Container className="bg-white">
            <Container className="col-md-10 mx-auto bg-white">
                <h3 className="text-uppercase text-center p-3">
                    Các câu hỏi thường gặp
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

                {faqs.length === 0 ? <Alert variant="warning">Không có câu hỏi nào chứa từ khóa trên</Alert> : 
                <Container fluid>
                    <Accordion flush>
                        
                        {faqs.map(faq => 
                        <Accordion.Item eventKey={faq.id}>
                            <Accordion.Header>
                                <b>{faq.title}</b>
                            </Accordion.Header>
                            <Accordion.Body>
                                <QuillHtmlRender content={faq.content} /> 
                            </Accordion.Body>
                        </Accordion.Item>
                        )}

                    </Accordion>
                </Container>
                }

                {counter === 0 ? "" : 
                    <Pagination className="justify-content-center my-3">{items}</Pagination>
                }
            </Container>
        </Container>
    );
};

export default FAQs;
