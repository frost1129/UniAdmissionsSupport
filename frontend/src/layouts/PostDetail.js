import React, { useContext, useEffect, useState } from "react";
import { Alert, Button, Card, Container, Form, Image } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import Comment from "../components/Comment";
import Api, { endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import { formatTimestamp } from "../config/Timestamp";
import QuillHtmlRender from "../components/QuillHtmlRender";
import { MyUserContext } from "../App";

const PostDetail = () => {
    const [user, ] = useContext(MyUserContext);
    const [post, setPost] = useState(null);
    const [comments, setComments] = useState([]);
    const { postId } = useParams();

    useEffect(() => {
        const loadPostDetai = async () => {
            let {data} = await Api.get(endpoints["post-details"](postId));
            const formattedDate = formatTimestamp(data.updatedDate);
            setPost({
                ...data, 
                updatedDate: formattedDate,
            });
        }

        loadPostDetai();
    }, [postId]);

    if (post === null) return <MySpinner />;

    return (
        <Container>
            <Container className="col-md-10 mx-auto bg-white">
                <article>
                    <Image className="img-fit mt-3" src={post.image} fluid rounded />

                    <header className="my-4">
                        <h3 className="fw-bold text-uppercase mt-3 text-primary">
                            {post.title}
                        </h3>
                        <p className="text-muted fst-italic">
                            Được đăng vào {post.updatedDate}
                        </p>
                        <Link className="badge bg-secondary text-decoration-none link-light">
                            {post.admissionType.name}
                        </Link>
                    </header>

                    <section className="my-4">
                        <QuillHtmlRender content={post.content} />
                    </section>
                </article>

                <section className="my-5">
                    {post.postType === "post" ? 
                    <Card className="bg-white">
                        <Card.Body>
                            {user === null ? 
                                <Alert variant="success">
                                    <Alert.Heading>Hãy đăng nhập để có thể bình luận</Alert.Heading>
                                    <div className="d-lg-flex justify-content-between align-items-end">
                                        <p className="mb-0">
                                            Hãy đăng nhập để có thể bắt đầu để lại bình luận.
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
                            :
                                <Form className="mb-3">
                                    <textarea className="form-control" rows="3" placeholder="Hãy để lại bình luận nào..." />
                                    <Button className="mt-2" type="submit">Gửi bình luận</Button>
                                </Form>
                            }
                            {comments.length === 0 ? 
                                <h5 className="text-secondary">Bài viết chưa có bình luận nào!</h5>
                            : <Comment/> }
                        </Card.Body>
                    </Card>
                    : 
                    <Card className="bg-white">
                        <Card.Body>
                            {user === null ? 
                                <Alert variant="success">
                                    <Alert.Heading>Hãy đăng nhập để có thể gửi câu hỏi cho buổi livestream</Alert.Heading>
                                    <div className="d-lg-flex justify-content-between align-items-end">
                                        <p className="mb-0">
                                            Hãy đăng nhập để có thể bắt đầu gửi câu hỏi cho buổi livestream.
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
                            :
                                <Form className="mb-3">
                                    <textarea className="form-control" rows="3" placeholder="Bạn muốn đặt câu hỏi gì cho buổi livestream?" />
                                    <Button className="mt-2" type="submit">Gửi câu hỏi</Button>
                                </Form>
                            }
                        </Card.Body>
                    </Card>
                    }
                </section>
            </Container>
        </Container>
    );
};

export default PostDetail;
