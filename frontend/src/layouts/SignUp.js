import React, { useRef, useState } from "react";
import { Alert, Button, Container, Form, Image, InputGroup, Row } from "react-bootstrap";
import ImgSignup from "../img/signup.png";
import "./signin.css"
import { Link, useNavigate } from "react-router-dom";
import MySpinner from "../components/MySpinner";
import Api, { endpoints } from "../config/Api";

const SignUp = () => {

    const [user, setUser] = useState({
        "email": "", 
        "firstName": "", 
        "lastName": "",
        "password": "", 
        "confirmPass": ""
    });
    const [err, setErr] = useState(null);
    const [loading, setLoading] = useState(false);
    const avatar = useRef();
    const nav = useNavigate();

    const register = (evt) => {
        evt.preventDefault();

        const process = async () => {
            let form = new FormData();

            for (let field in user)
                if (field !== "confirmPass")
                    form.append(field, user[field]);

            form.append("avatar", avatar.current.files[0]);

            setLoading(true)
            let res = await Api.post(endpoints['register'], form);
            if (res.status === 201) {
                nav("/login");
            } else
            setErr("Hiện tại hệ thống đang trục trặc, vui lòng thử lại sau.");
        }

        if (user.password !== user.confirmPass)
            setErr("Mật khẩu xác nhận không khớp!");
        else {
            process();
        }
    }

    const change = (evt, field) => {
        // setUser({...user, [field]: evt.target.value})
        setUser(current => {
            return {...current, [field]: evt.target.value}
        })
    }

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
                    <Form onSubmit={register}>
                        <div className="mb-4 text-primary-emphasis">
                            <h3 className="fw-bold">ĐĂNG KÝ TÀI KHOẢN</h3>
                        </div>

                        {err === null?"":<Alert variant="warning">{err}</Alert>}

                        <InputGroup className="mb-3">
                            <input
                                type="text"
                                onChange={(e) => change(e, "email")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Địa chỉ Email"
                                required
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="text"
                                onChange={(e) => change(e, "lastName")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Họ và tên đệm"
                                required
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="text"
                                onChange={(e) => change(e, "firstName")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Tên"
                                required
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="password"
                                onChange={(e) => change(e, "password")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Mật khẩu"
                                required
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="password"
                                onChange={(e) => change(e, "confirmPass")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Xác nhận mật khẩu"
                                required
                            />
                        </InputGroup>
                        <Form.Group className="mb-3">
                            <Form.Label>Ảnh đại diện</Form.Label>
                            <Form.Control type="file" ref={avatar}  />
                        </Form.Group>
                        <InputGroup className="mb-3">
                            {loading === true ? <MySpinner /> : 
                                <Button type="submit" className="btn-lg btn-primary w-100 fs-6">
                                    Đăng Ký
                                </Button> 
                            }
                        </InputGroup>
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
