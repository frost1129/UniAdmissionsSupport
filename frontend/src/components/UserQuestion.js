import React from "react";
import { Button, Container, Form } from "react-bootstrap";
import { Link } from "react-router-dom";

const UserQuestion = () => {
    return (
        <Container>
            <h3 className="text-center my-3">Câu hỏi tuyển sinh</h3>
            <Form className="col-md-10  mx-auto">
                <Form.Group className="mb-3">
                    <Form.Label>Họ và Tên</Form.Label>
                    <Form.Control type="text" placeholder="Nguyễn Văn A" />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Địa chỉ Email</Form.Label>
                    <Form.Control type="email" placeholder="name@example.com" />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Hệ đào tạo</Form.Label>
                    <Form.Select aria-label="Default select example">
                        <option>Chọn hệ đào tạo muốn đặt câu hỏi</option>
                        <option value="1">One</option>
                    </Form.Select>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Nội dung câu hỏi</Form.Label>
                    <Form.Control as="textarea" rows={3} placeholder="Nhập nội dung câu hỏi ở đây" />
                </Form.Group>
                <Form.Group className="mb-1 col-12 text-center">
                    Bạn có thể đăng nhập để gửi và lưu câu hỏi của mình 
                    <Link className="mx-1">
                    ở đây.
                    </Link>
                    <br/>
                    <Form.Text id="submitHelpBlock" muted> or </Form.Text>
                    <br/>
                    <Button variant="info" type="submit">Gửi câu hỏi</Button>
                </Form.Group>
            </Form>
        </Container>
    );
};

export default UserQuestion;
