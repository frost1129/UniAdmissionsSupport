import React, { useEffect, useState } from "react";
import { Alert, Card, Container, Row } from "react-bootstrap";
import { Link, useParams } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import { formatTimestamp } from "../config/Timestamp";
import QuillHtmlRender from "../components/QuillHtmlRender";

const FacultyPost = () => {
    const [post, setPost] = useState(null);
    const [score, setScore] = useState([]);
    const [faculty, setFaculty] = useState(null);
    const { facultyId } = useParams();

    useEffect(() => {
        const loadPostDetail = async () => {
            let {data} = await Api.get(endpoints["faculty-post"](facultyId));
            const formattedDate = formatTimestamp(data.updatedDate);
            setPost({
                ...data, 
                updatedDate: formattedDate,
            });
        }

        const loadScore = async () => {
            let {data} = await Api.get(endpoints["faculty-score"](facultyId));
            setScore(data);
        }

        const loadFaculty = async () => {
            let {data} = await Api.get(endpoints["faculty-detail"](facultyId));
            setFaculty(data);
        }

        loadPostDetail();
        loadScore();
        loadFaculty();
    }, [facultyId]);

    if (faculty === null || post === null || score === null) return <MySpinner />;

    return (
        <Container>
            <Container className="col-md-10 mx-auto bg-white">
                <article>
                    <header className="my-4">
                        <h3 className="fw-bold text-uppercase mt-3 text-primary">
                            GIỚI THIỆU KHOA {faculty.name}
                        </h3>
                        <p className="text-muted fst-italic">
                            Được đăng vào {post.updatedDate}
                        </p>
                    </header>

                    <section className="my-4">
                        <QuillHtmlRender content={post.content} />
                    </section>
                </article>

                {score.length === 0 ?
                    <Alert variant="info">Hiện khoa này chưa có thông tin điểm tuyển sinh</Alert>
                : 
                    <Row>
                        <div class="col-12 col-md-4 mb-3 text-md-start text-center">
                            <h4 className="text-primary fw-bold">Điểm chuẩn của khoa</h4>
                            <p>Điểm chuẩn 5 năm gần đây của khoa</p>
                        </div>
                    
                        {score.map(s => 
                            <div key={s.id} className="col-6 col-md-3 mb-3 mx-3 bg-white d-flex rounded shadow">
                                <p>
                                    <b>Năm {s.year}: <br/></b>
                                    <QuillHtmlRender content={s.content} />
                                </p>
                            </div>
                        )}
                    </Row>
                }
            </Container>
        </Container>
    );
};

export default FacultyPost;
