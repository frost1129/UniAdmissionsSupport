import React from "react";
import {
    Button,
    Container,
    Form,
    FormCheck,
    Image,
    InputGroup,
    Row,
} from "react-bootstrap";
import FormCheckInput from "react-bootstrap/esm/FormCheckInput";
import FormCheckLabel from "react-bootstrap/esm/FormCheckLabel";
import ImgLogin from "../img/login_img.png";
import "./signin.css"
import { Link } from "react-router-dom";

const SignIn = () => {
    return (
        <Container className="d-flex justify-content-center align-items-center min-vh-100">
            <Row className="rounded-4 p-3 bg-white shadow box-area">
                
                <div className="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
                    <div className="featured-image mb-3">
                        <Image src={ImgLogin} fluid/>
                    </div>
                </div>

                {/* SIGN IN */}
                <Container className="col-md-6 rounded-4 bg-info-subtle right-box">
                    <Form>
                        <div className="mb-4">
                            <h3 className="fw-bold">ĐĂNG NHẬP</h3>
                        </div>
                        <InputGroup className="mb-3 text-primary-emphasis">
                            <input
                                type="text"
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Địa chỉ Email"
                                name="email"
                            />
                        </InputGroup>
                        <InputGroup className="mb-1">
                            <input
                                type="password"
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Mật khẩu"
                                name="password"
                            />
                        </InputGroup>
                        <InputGroup className="mb-5 d-flex justify-content-between">
                            <FormCheck className="form-check">
                                <FormCheckInput
                                    type="checkbox"
                                    id="formCheck"
                                />
                                <FormCheckLabel
                                    for="formCheck"
                                    className="text-secondary"
                                >
                                    <small>Ghi nhớ đăng nhập</small>
                                </FormCheckLabel>
                            </FormCheck>
                            <div className="forgot">
                                <small>
                                    <a href="#quenmk">Quên mặt khẩu?</a>
                                </small>
                            </div>
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <Button className="btn-lg btn-primary w-100 fs-6">
                                Đăng nhập
                            </Button>
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <Button className="btn-lg btn-light w-100 fs-6">
                                {/* TODO: icon facebook */}
                                <small>Đăng nhập bằng Facebook</small>
                            </Button>
                        </InputGroup>
                        <Row className="text-end">
                            <small>
                                Chưa có tài khoản? 
                                <Link to='/signup' className="mx-1">Đăng ký</Link>
                            </small>
                        </Row>
                    </Form>
                </Container>
            </Row>
        </Container>
    );
};

export default SignIn;
