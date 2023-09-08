import { faSearch } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import {
    Accordion,
    Button,
    Container,
    Form,
    InputGroup,
} from "react-bootstrap";
import Api, { endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import QuillHtmlRender from "../components/QuillHtmlRender";
import { useNavigate } from "react-router-dom";

const FAQs = () => {
    const [faqs, setFaqs] = useState([]);
    const [kw, setKw] = useState("");
    const nav = useNavigate();

    const search = (evt) => {
        evt.preventDefault();
        nav(`/faqs/?kw=${kw}`);
    }

    useEffect(() => {
        const loadFaqs = async () => {
            let {data} = await Api.get(endpoints["faqs"]);
            setFaqs(data);
        }

        loadFaqs();
    }, []);

    if (faqs === null) return <MySpinner />;

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
            </Container>
        </Container>
    );
};

export default FAQs;
