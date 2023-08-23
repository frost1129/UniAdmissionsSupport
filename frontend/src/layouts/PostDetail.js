import React from "react";
import { Alert, Button, Card, Container, Form, Image } from "react-bootstrap";
import { Link } from "react-router-dom";
import Comment from "../components/Comment";

const PostDetail = () => {
    return (
        <Container>
            <Container className="col-md-10 mx-auto">
                <article>
                    <header className="my-4">
                        <h3 className="fw-bold text-uppercase mt-3 text-primary">
                            Tiêu đề bài viết...
                        </h3>
                        <p className="text-muted fst-italic">
                            Được đăng vào ngày ....
                        </p>
                        <Link className="badge bg-secondary text-decoration-none link-light">
                            Loại hình tuyển sinh
                        </Link>
                    </header>

                    <Image className="img-fit" src="https://dummyimage.com/900x400/ced4da/6c757d.jpg" fluid rounded />

                    <section className="my-4">
                        some content here
                    </section>
                </article>

                <section className="my-5">
                    <Card className="bg-light">
                        <Card.Body>
                            <Alert variant="success">
                                <Alert.Heading>Hãy đăng nhập để có thể bình luận</Alert.Heading>
                                <div className="d-lg-flex justify-content-between align-items-end">
                                    <p className="mb-0">
                                        Hãy đăng nhập để có thể bắt đầu để lại bình luận dưới bài viết.
                                    </p>
                                    <Link className="btn btn-outline-success">
                                        Đăng nhập
                                    </Link>
                                </div>
                                <hr />
                                <div className="d-lg-flex justify-content-between">
                                    <p className="mb-0">
                                        Nếu chưa có tài khoản, bạn có thể bắt đầu bằng việc đăng ký tài khoản.
                                    </p>
                                    <Link className="btn btn-outline-warning">
                                        Đăng ký
                                    </Link>
                                </div>
                            </Alert>

                            <Form className="mb-3">
                                <textarea className="form-control" rows="3" placeholder="Hãy để lại bình luận nào..." />
                                <Button className="mt-2" type="submit">Gửi bình luận</Button>
                            </Form>

                            <h5 className="text-secondary">Bài viết chưa có bình luận nào!</h5>
                            <Comment/>
                            
                        </Card.Body>
                    </Card>
                </section>
            </Container>
        </Container>
    );
};

export default PostDetail;
