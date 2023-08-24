import React from "react";
import { Card, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

const Faculties = () => {
    return (
        <Container id="faculties" className="col-md-10 mx-auto py-2 my-3 bg-white">
            <Row>
                <div class="col-12 col-md-4 mb-3 text-md-start text-center">
                    <h3 className="text-primary fw-bold">Thông tin các Khoa</h3>
                    <p>Thông tin về các khoa đào tạo của trường Đại học Mở Thành phố Hồ Chí Minh.</p>
                </div>

                <div class="col-6 col-md-4 mb-3 text-center thumbnail">
                    <Link className="text-decoration-none">
                        <Card>
                            <Card.Img src="https://dummyimage.com/600x400/aaa/fff"/>
                            <Card.ImgOverlay>
                                <h5 className="text-white pt-5 mt-5">Ten khoa ne`</h5>
                            </Card.ImgOverlay>
                        </Card>
                    </Link>


                </div>
            </Row>
        </Container>
    );
};

export default Faculties;
