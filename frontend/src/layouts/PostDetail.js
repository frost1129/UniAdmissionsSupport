import React, { useContext, useEffect, useState } from "react";
import { Alert, Button, Card, Container, Form, Image } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import Comment from "../components/Comment";
import Api, { authApi, endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import { formatTimestamp } from "../config/Timestamp";
import QuillHtmlRender from "../components/QuillHtmlRender";
import { MyUserContext } from "../App";

const PostDetail = () => {
    const [user, ] = useContext(MyUserContext);
    const [post, setPost] = useState(null);
    const [comments, setComments] = useState(null);
    const { postId } = useParams();
    const [cmt, setCmt] = useState({
        "content": ""
    });

    const [loading, setLoading] = useState(false);
    const [notify, setNotify] = useState({
        "variant": "", 
        "content": ""
    });

    const addComment = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();
            for (let field in cmt)
                form.append(field, cmt[field]);

            setLoading(true);
            let res = await authApi().post(endpoints["post-add-comment"](postId), form);

            if (res.status === 201) {
                setLoading(false);
                loadNotify("success", "Gửi bình luận thành công");
                setCmt({
                    "content": "", 
                });
            } else {
                loadNotify("danger", "Hệ thống đang gặp trục trặc, vui lòng thử lại sau.");
            }
        }
        if (cmt.content === "")
            loadNotify("warning", "Vui lòng nhập nội dung comment");
        else 
            process();
    }

    const addQuestion = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();
            for (let field in cmt)
                form.append(field, cmt[field]);

            setLoading(true);
            let res = await authApi().post(endpoints["post-add-question"](postId), form);

            if (res.status === 201) {
                setLoading(false);
                loadNotify("success", "Gửi câu hỏi thành công, hãy đón chờ buổi livestream để nhận câu trả lời nhé");
                setCmt({
                    "content": "", 
                });
            } else {
                loadNotify("danger", "Hệ thống đang gặp trục trặc, vui lòng thử lại sau.");
            }
        }
        if (cmt.content === "")
            loadNotify("warning", "Vui lòng nhập nội dung câu hỏi");
        else 
            process();
    }

    const loadNotify = (variant, content) => {
        setNotify({
            "variant": variant, 
            "content": content,
        });
    };

    const change = (evt, field) => {
        setCmt(current => {
            return {...current, [field]: evt.target.value}
        })
    }

    useEffect(() => {
        const loadPostDetai = async () => {
            let {data} = await Api.get(endpoints["post-details"](postId));
            const formattedDate = formatTimestamp(data.updatedDate);
            setPost({
                ...data, 
                updatedDate: formattedDate,
            });
        }

        
        const loadComment = async () => {
            let {data} = await Api.get(endpoints["post-comments"](postId));
            setComments(data);
        }

        loadPostDetai();
        loadComment();
    }, [postId, loading]);

    if (post === null || comments === null) return <MySpinner />;

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
                    {notify.variant === "" ? "" : <Alert variant={notify.variant}>{notify.content}</Alert>}
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
                                        <Link to="/login" className="btn btn-outline-success">
                                            Đăng nhập
                                        </Link>
                                    </div>
                                    <hr />
                                    <div className="d-lg-flex justify-content-between">
                                        <p className="mb-0">
                                            Nếu chưa có tài khoản, bạn có thể bắt đầu bằng việc đăng ký tài khoản.
                                        </p>
                                        <Link to="/signup" className="btn btn-outline-warning">
                                            Đăng ký
                                        </Link>
                                    </div>
                                </Alert>
                            :
                                <Form className="mb-3" onSubmit={addComment}>
                                    <textarea
                                        type="text" 
                                        as="textarea" 
                                        rows={3}
                                        value={cmt.content}
                                        onChange={(e) => change(e, "content")}
                                        className="form-control"
                                        placeholder="Hãy để lại bình luận nào..." />
                                    {loading === false ? 
                                    <Button className="mt-2" type="submit">Gửi bình luận</Button> : 
                                    <MySpinner />}
                                </Form>
                            }
                            {comments.length === 0 ? 
                                <h5 className="text-secondary">Bài viết chưa có bình luận nào!</h5>
                            : 
                                comments.map(cmt => <Comment key={cmt.id} comment={cmt} />)
                            }
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
                                        <Link to="/login" className="btn btn-outline-success">
                                            Đăng nhập
                                        </Link>
                                    </div>
                                    <hr />
                                    <div className="d-lg-flex justify-content-between">
                                        <p className="mb-0">
                                            Nếu chưa có tài khoản, bạn có thể bắt đầu bằng việc đăng ký tài khoản.
                                        </p>
                                        <Link to="/signup" className="btn btn-outline-warning">
                                            Đăng ký
                                        </Link>
                                    </div>
                                </Alert>
                            :
                                <Form className="mb-3" onSubmit={addQuestion}>
                                    <textarea 
                                        type="text" 
                                        as="textarea" 
                                        rows={3}
                                        value={cmt.content}
                                        onChange={(e) => change(e, "content")}
                                        className="form-control"
                                        placeholder="Bạn muốn đặt câu hỏi gì cho buổi livestream?" />
                                    {loading === false ? 
                                    <Button className="mt-2" type="submit">Gửi câu hỏi</Button> : 
                                    <MySpinner />}
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
