import { faInfoCircle, faPencilAlt } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useState } from "react";
import { Button, Container, Form, Modal, Table } from "react-bootstrap";
import { Link } from "react-router-dom";

const QuestionManage = () => {
    const [showEdit, setShowEdit] = useState(false);
    const [showInfo, setShowInfo] = useState(false);

    const handleClose = () => setShowInfo(false);
    const handleShow = () => setShowInfo(true);
    const handleEditClose = () => setShowEdit(false);
    const handleEditShow = () => setShowEdit(true);

    return (
        <Container className="bg-white">
            <Container className="col-md-10 mx-auto bg-white">
                <h3 className="text-uppercase text-center fw-bold p-3">Danh sách câu hỏi</h3>
              	<Table bordered hover>
                    <thead className="text-center table-info">
                        <tr>
                            <th></th>
                            <th className="col-3">Nội dung câu hỏi</th>
                            <th>Người gửi</th>
                            <th>Thời gian</th>
                            <th className="col-2">Status</th>
                            <th>Người trả lời</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody className="text-center">
                        <tr>
                            <td>1</td>
                            <td className="text-start">1</td>
                            <td>1</td>
                            <td>1</td>
                            <td>
                                <Button variant="success" className="rounded-pill">Đã trả lời</Button>
                                <Button variant="warning" className="rounded-pill">Chưa trả lời</Button>
                            </td>
                            <td>1</td>
                            <td>
                                <Link className="btn btn-sm" onClick={handleShow}>
                                    <FontAwesomeIcon icon={faInfoCircle} style={{color: "#2e2eff"}}/>
                                </Link>
                                <Link className="btn btn-sm" onClick={handleEditShow}>
                                    <FontAwesomeIcon icon={faPencilAlt}/>
                                </Link>
                            </td>
                        </tr>
                    </tbody>
                </Table>
                <Modal
                    show={showInfo}
                    size="md"
                    aria-labelledby="contained-modal-title-vcenter"
                    centered
                    onHide={handleClose}
                >
                    <Modal.Header closeButton>
                        <Modal.Title id="contained-modal-title-vcenter">
                            Chi tiết câu hỏi
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label className="fw-bold">Nội dung câu hỏi</Form.Label>
                                <p>Nội dung câu hỏi </p>
                            </Form.Group>
                            <Form.Group >
                                <Form.Label className="fw-bold">Câu trả lời</Form.Label>
                                <p>Nội dung câu trả lời </p>
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                </Modal>
                <Modal
                    show={showEdit}
                    size="md"
                    aria-labelledby="contained-modal-title-vcenter"
                    backdrop="static"
                    centered
                >
                    <Modal.Header>
                        <Modal.Title id="contained-modal-title-vcenter">
                            Chi tiết câu hỏi
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form>
                            <Form.Group className="mb-3">
                                <Form.Label>Nội dung câu hỏi</Form.Label>
                                <Form.Control type="text" placeholder="Nội dung câu hỏi..." readOnly/>
                            </Form.Group>
                            <Form.Group >
                                <Form.Label>Câu trả lời</Form.Label>
                                <Form.Control as="textarea" rows={5} placeholder="Nhập câu trả lời ở đây" />
                            </Form.Group>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={handleEditClose}>Cancle</Button>
                        <Button variant="primary" onClick={handleEditClose}>Lưu câu trả lời</Button>
                    </Modal.Footer>
                </Modal>
            </Container>
        </Container>
    );
};

export default QuestionManage;
