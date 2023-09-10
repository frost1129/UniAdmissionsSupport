import { faInfoCircle, faPencilAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useState } from "react";
import { useContext } from "react";
import { Alert, Button, Container, Form, Modal, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { MyUserContext } from "../App";
import { useEffect } from "react";
import Api, { authApi, endpoints } from "../config/Api";
import MySpinner from "../components/MySpinner";
import { formatTimestamp } from "../config/Timestamp";

const QuestionManage = () => {
    const [user, ] = useContext(MyUserContext);
    const [unQues, setUnQues] = useState([]);
    const [emailQues, setEmailQues] = useState([]);
    const [advisorQues, setAdvisorQues] = useState([]);

    const [loading, setLoading] = useState(false);

    const [showEdit, setShowEdit] = useState(false);
    const [showInfo, setShowInfo] = useState(false);
    const [currentQues, setCurrentQues] = useState(null);

    const handleClose = () => {
        setShowInfo(false);
        setCurrentQues(null);
    }
    const handleEditClose = () => {
        setShowEdit(false);
        setCurrentQues(null);
    }

    const handleShow = (quesId) => {
        loadQuestion(quesId);
        setShowInfo(true);
    }
    const handleEditShow = (quesId) => {
        loadQuestion(quesId);
        setShowEdit(true);
    }

    const loadQuestion = async (quesId) => {
        let {data} = await Api.get(endpoints["question-detail"](quesId));
        setCurrentQues(data);
    }

    const change = (evt, field) => {
        setCurrentQues(current => {
            return {...current, [field]: evt.target.value}
        })
    }

    const updateQuestion = (evt) => {
        evt.preventDefault();

        const process = async () => {
            setLoading(true);
            let res = await authApi().put(endpoints["question-update"], {
                "id": currentQues.id,
                "answer": currentQues.answer
            });
            
            if (res.status === 200) {
                setLoading(false);
                handleEditClose();
            }
        }

        process();
    }

    useEffect(() => {
        const loadUnQues = async () => {
            let {data} = await Api.get(endpoints["question-unanswer"]);
            const formattedData = data.map(d => ({
                ...d,
                submitTime: formatTimestamp(d.submitTime),
            }));
            setUnQues(formattedData);
        }

        const loadEmailQues = async () => {
            let {data} = await authApi().get(endpoints["question-email"]);
            const formattedData = data.map(d => ({
                ...d,
                submitTime: formatTimestamp(d.submitTime),
            }));
            setEmailQues(formattedData);
        }

        const loadAdvisorQues = async () => {
            let {data} = await authApi().get(endpoints["question-admission"]);
            const formattedData = data.map(d => ({
                ...d,
                submitTime: formatTimestamp(d.submitTime),
            }));
            setAdvisorQues(formattedData);
        }

        if (user.userRole === "ROLE_USER") {
            loadEmailQues();
        } else {
            loadAdvisorQues();
            loadUnQues();
        }
    }, [user.userRole, unQues, emailQues]);

    if (emailQues === null || (advisorQues === null && unQues === null)) return <MySpinner />

    return (
        <Container className="bg-white">
            <Container className="col-md-10 mx-auto bg-white">
                {user.userRole === "ROLE_USER" ?
                <>
                    <h3 className="text-uppercase text-center fw-bold p-3">Danh sách câu hỏi của bạn</h3>
                    {emailQues.length === 0 ?
                        <Alert variant="info">Bạn chưa đặt câu hỏi nào cho chúng tôi.</Alert>
                    : 
                    <>
                    <Table bordered hover>
                        <thead className="text-center table-info">
                            <tr>
                                <th className="col-3">Nội dung câu hỏi</th>
                                <th>Thời gian</th>
                                <th className="col-2">Status</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody className="text-center">
                            {emailQues.map(ques => 
                            <tr key={ques.id}>
                                <td className="text-start">{ques.content}</td>
                                <td>1</td>
                                <td>
                                    {ques.answer === null ?
                                        <Button variant="warning" className="rounded-pill">Chưa trả lời</Button>
                                    : 
                                        <Button variant="success" className="rounded-pill">Đã trả lời</Button>
                                    }
                                </td>
                                <td>
                                    <Link className="btn btn-sm" onClick={() => handleShow(ques.id)}>
                                        <FontAwesomeIcon icon={faInfoCircle} style={{color: "#2e2eff"}}/>
                                    </Link>
                                </td>
                            </tr>
                            )}
                        </tbody>
                    </Table>
                    </>}
                </>
                : 
                <>
                {unQues.length === 0 ? 
                    <Alert variant="success">Toàn bộ câu hỏi đã được trả lời!</Alert>
                    : 
                    <>
                        <h3 className="text-uppercase text-center fw-bold p-3">Câu hỏi chưa trả lời</h3>
                        <Table bordered hover>
                            <thead className="text-center table-info">
                                <tr>
                                    <th></th>
                                    <th className="col-3">Nội dung câu hỏi</th>
                                    <th>Người gửi</th>
                                    <th>Thời gian</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody className="text-center">
                                {unQues.map(ques => 
                                <tr>
                                    <td>{ques.id}</td>
                                    <td className="text-start">{ques.content}</td>
                                    <td>{ques.askUserEmail}</td>
                                    <td>{ques.submitTime}</td>
                                    <td>
                                        <Link className="btn btn-sm" onClick={() => handleEditShow(ques.id)}>
                                            <FontAwesomeIcon icon={faPencilAlt}/>
                                        </Link>
                                    </td>
                                </tr>
                                )}
                            </tbody>
                        </Table>
                    </>
                }
                {advisorQues.length === 0 ?
                    <Alert variant="info">Bạn chưa trả lời câu hỏi nào.</Alert>
                : 
                    <>
                        <h3 className="text-uppercase text-center fw-bold mt-3 p-3">Câu hỏi đã trả lời</h3>
                        <Table bordered hover>
                            <thead className="text-center table-info">
                                <tr>
                                    <th></th>
                                    <th className="col-3">Nội dung câu hỏi</th>
                                    <th>Người gửi</th>
                                    <th>Thời gian</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody className="text-center">
                                {advisorQues.map(ques => 
                                <tr>
                                    <td>{ques.id}</td>
                                    <td className="text-start">{ques.content}</td>
                                    <td>{ques.askUserEmail}</td>
                                    <td>{ques.submitTime}</td>
                                    <td>
                                        <Link className="btn btn-sm" onClick={() => handleEditShow(ques.id)}>
                                            <FontAwesomeIcon icon={faPencilAlt}/>
                                        </Link>
                                    </td>
                                </tr>
                                )}
                            </tbody>
                        </Table>
                    </>
                }
                </>
                }

{/* MODAL XEM CHI TIẾT CÂU HỎI */}
                <Modal
                    show={showInfo}
                    size="md"
                    aria-labelledby="contained-modal-title-vcenter"
                    centered
                    onHide={handleClose}
                >
                    {currentQues === null ? 
                        <MySpinner />
                    : <>
                        <Modal.Header closeButton>
                            <Modal.Title id="contained-modal-title-vcenter">
                                Chi tiết câu hỏi
                            </Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form>
                                <Form.Group className="mb-3">
                                    <Form.Label className="fw-bold">Nội dung câu hỏi</Form.Label>
                                    <p>{currentQues.content}</p>
                                </Form.Group>
                                <Form.Group >
                                    <Form.Label className="fw-bold">Câu trả lời</Form.Label>
                                    <p>{currentQues.answer === null ? "Chưa có câu trả lời" : currentQues.answer}</p>
                                </Form.Group>
                            </Form>
                        </Modal.Body>
                    </>
                    }
                    
                </Modal>

{/* MODAL CHỈNH SỬA CÂU HỎI */}
                <Modal
                    show={showEdit}
                    size="md"
                    aria-labelledby="contained-modal-title-vcenter"
                    backdrop="static"
                    centered
                >   
                    {currentQues === null ? 
                        <MySpinner />
                    : <>
                        <Modal.Header>
                            <Modal.Title id="contained-modal-title-vcenter">
                                Chi tiết câu hỏi
                            </Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form onSubmit={updateQuestion}>
                                <Form.Group className="mb-3">
                                    <Form.Label>Nội dung câu hỏi</Form.Label>
                                    <Form.Control type="text" value={currentQues.content} readOnly/>
                                </Form.Group>
                                <Form.Group className="mb-3">
                                    <Form.Label>Câu trả lời</Form.Label>
                                    <Form.Control 
                                        as="textarea" 
                                        rows={5} 
                                        placeholder="Nhập câu trả lời ở đây" 
                                        onChange={(e) => change(e, "answer")}
                                        value={currentQues.answer === null ? undefined : currentQues.answer} />
                                </Form.Group>
                                {loading === true ? <MySpinner /> : 
                                <Form.Group className="mb-3">
                                    <Button type="button" variant="secondary" className="me-2" onClick={handleEditClose}>Cancle</Button>
                                    <Button type="submit" variant="primary">Lưu câu trả lời</Button>
                                </Form.Group>
                                }
                            </Form>
                        </Modal.Body>
                    </>}
                </Modal>
            </Container>
        </Container>
    );
};

export default QuestionManage;
