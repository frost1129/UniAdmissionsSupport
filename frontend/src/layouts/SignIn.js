import React, { useState } from "react";
import {
    Button,
    Container,
    Form,
    Image,
    InputGroup,
    Row,
} from "react-bootstrap";
import ImgLogin from "../img/login_img.png";
import "./signin.css"
import { Link, Navigate } from "react-router-dom";
import Api, { authApi, endpoints } from "../config/Api";
import cookie from "react-cookies";
import { useContext } from "react";
import { MyUserContext } from "../App";

const SignIn = () => {
    const [user, dispatch] = useContext(MyUserContext);
    const [u, setU] = useState({
        "email": "",
        "password": ""
    });

    const login = (evt) => {
        evt.preventDefault();

        const process = async () => {
            try {
                let res = await Api.post(endpoints["login"], {
                    "email": u.email,
                    "password": u.password
                });
                cookie.save("token", res.data);
                
                let { data } = await authApi().get(endpoints["current-user"]);
                cookie.save("user", data);
                console.log(data);

                dispatch({
                    "type": "login", 
                    "payload": data
                })

            } catch (ex) {
                console.error(ex);
            }
        }

        process();
    }

    const change = (evt, field) => {
        setU(current => {
            return {...current, [field]: evt.target.value}
        })
    }

    if (user !== null)
        return <Navigate to="/" />

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
                    <Form onSubmit={login}>
                        <div className="mb-4">
                            <h3 className="fw-bold">ĐĂNG NHẬP</h3>
                        </div>
                        <InputGroup className="mb-3 text-primary-emphasis">
                            <input
                                type="text"
                                onChange={(e) => change(e, "email")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Địa chỉ Email"
                                name="email"
                            />
                        </InputGroup>
                        <InputGroup className="mb-3">
                            <input
                                type="password"
                                onChange={(e) => change(e, "password")}
                                className="form-control form-control-lg bg-light fs-6"
                                placeholder="Mật khẩu"
                                name="password"
                            />
                        </InputGroup>
                        
                        <InputGroup className="mb-3">
                            <Button type="submit" className="btn-lg btn-primary w-100 fs-6">
                                Đăng nhập
                            </Button>
                        </InputGroup>
                        {/* <InputGroup className="mb-3">
                            <Button className="btn-lg btn-light w-100 fs-6">
                                <small>Đăng nhập bằng Facebook</small>
                            </Button>
                        </InputGroup> */}
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
