import React from "react";
import { Button, Container, Form, Image, InputGroup, Row } from "react-bootstrap";
import ImgSignup from "../img/signup.png";
import "./signin.css"
import { Link } from "react-router-dom";

const SignUp = () => {
    return (
        <Container className="d-flex justify-content-center align-items-center min-vh-100">
            <Row className="rounded-4 p-3 bg-white shadow box-area">
                <div className="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
                    <div className="featured-image mb-3">
                        <Image src={ImgSignup} fluid />
                    </div>
                </div>

                {/* SIGN UP */}
                <Container className="col-md-6 rounded-4 bg-info-subtle right-box">
                    <Form>
                        <div className="mb-4 text-primary-emphasis">
                            <h3 className="fw-bold">ĐĂNG KÝ TÀI KHOẢN</h3>
                        </div>
                        <InputGroup className="mb-3">
                            <input
                                type="text"
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Địa chỉ Email"
                                name="email"
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="text"
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Họ và tên đệm"
                                name="last_name"
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="text"
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Tên"
                                name="first_name"
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="password"
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Mật khẩu"
                                name="password"
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="password"
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Xác nhận mật khẩu"
                                name="confPassword"
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <Button className="btn-lg btn-primary w-100 fs-6">
                                Đăng Ký
                            </Button>
                        </InputGroup>
                        {/* <InputGroup className="mb-3">
                            <Button className="btn-lg btn-light w-100 fs-6">
                                <small>Đăng ký bằng tài khoản Facebook</small>
                            </Button>
                        </InputGroup> */}
                        <Row>
                            <small>
                                Đã có tài khoản? 
                                <Link to='/login' className="mx-1">Đăng nhập</Link>
                            </small>
                        </Row>
                    </Form>
                </Container>
            </Row>
        </Container>
    );
};

export default SignUp;
