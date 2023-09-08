import { faArrowRight } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import { Accordion, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import MySpinner from "./MySpinner";
import QuillHtmlRender from "./QuillHtmlRender";

const FAQ = () => {
    const [faqs5, setFaqs5] = useState([]);

    useEffect(() => {
        const loadFaqs5 = async () => {
            let {data} = await Api.get(endpoints["top-5-faqs"]);
            setFaqs5(data);
        }

        loadFaqs5();
    }, []);

    if (faqs5 === null) return <MySpinner />;

    return (
        <Container className="mt-4 py-2 bg-white" fluid>
            <h3 className="text-center my-3 fw-bold">Các câu hỏi thường gặp</h3>
            <Accordion className="col-md-10 mx-auto" flush>
                {faqs5.map(faq => 
                <Accordion.Item eventKey={faq.id}>
                    <Accordion.Header><b>{faq.title}</b></Accordion.Header>
                    <Accordion.Body>
                        <QuillHtmlRender content={faq.content} />
                    </Accordion.Body>
                </Accordion.Item>
                )}
            </Accordion>
            
            <Row className="my-1">
                <Link to='/faqs' className="text-decoration-none text-center text-uppercase fw-bold my-3">
                    Xem tất cả câu hỏi thường gặp
                    <FontAwesomeIcon icon={faArrowRight} className="mx-2" />
                </Link>
            </Row>
        </Container>
    );
};

export default FAQ;
