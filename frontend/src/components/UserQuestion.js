import React, { useContext, useEffect, useState } from "react";
import { Alert, Button, Container, Form } from "react-bootstrap";
import { Link } from "react-router-dom";
import { MyUserContext } from "../App";
import Api, { authApi, endpoints } from "../config/Api";
import MySpinner from "./MySpinner";

const UserQuestion = () => {
    const [user, ] = useContext(MyUserContext);
    const [admissionType, setAdmissionType] = useState([]);
    const [setting, setSetting] = useState(null);
    const [notify, setNotify] = useState({
        "variant": "", 
        "content": ""
    });
    const [err, setErr] = useState(null);
    const [info, setInfo] = useState(null);
    const [loading, setLoading] = useState(false);
    const [question, setQuestion] = useState({
        "content": "", 
        "admissionType": ""
    });

    const loadNotify = (variant, content) => {
        setNotify({
            "variant": variant, 
            "content": content,
        });
    };

    const send = (evt) => {
        evt.preventDefault();
        
        const process = async () => {
            let form = new FormData();
            
            for (let field in question)
                form.append(field, question[field]);

            setLoading(true)
            let res = await authApi().post(endpoints["question-add"], form);

            if (res.status === 201) {
                setLoading(false);
                loadNotify("info", "Đã gửi câu hỏi thành công, đội ngũ tư vấn sẽ trả lời bạn trong thời gian sớm nhất.");
                setQuestion({
                    "content": "", 
                    "admissionType": "",
                });
            } else {
                loadNotify("danger", "Hệ thống đang gặp trục trặc, vui lòng thử lại sau.");
            }
        }

        console.log(question);
        if (question.admissionType === '' || question.content.length === 0) {
            loadNotify("danger", "Vui lòng nhập đầy đủ các trường!");
        } else if (!isInTimeAcceptQuestion()) {
            loadNotify("danger", "Hiện đang ngoài khoảng thời gian cho phép gửi câu hỏi, vui lòng quay lại sau!");
        } else {
            loadNotify("", "");
            process();
        }
    }

    const change = (evt, field) => {
        setQuestion(current => {
            return {...current, [field]: evt.target.value}
        })
    }

    const isInTimeAcceptQuestion = () => {
        const currentTime = new Date();
        const fromTimeParts = setting.fromTime.split(':');
        const toTimeParts = setting.toTime.split(':');

        const fromTime = new Date();
        fromTime.setHours(parseInt(fromTimeParts[0], 10), parseInt(fromTimeParts[1], 10), parseInt(fromTimeParts[2], 10));

        const toTime = new Date();
        toTime.setHours(parseInt(toTimeParts[0], 10), parseInt(toTimeParts[1], 10), parseInt(toTimeParts[2], 10));

        const isCurrentTimeInRange = currentTime >= fromTime && currentTime <= toTime;

        return isCurrentTimeInRange;
    };

    useEffect(() => {
        const loadAdmissionType = async () => {
            let {data} = await Api.get(endpoints["admissions"]);
            setAdmissionType(data);
        }

        const loadSetting = async () => {
            let {data} = await Api.get(endpoints["question-setting"]);
            setSetting(data);
        }

        loadAdmissionType();
        loadSetting();
    }, [user]);

    if (admissionType === null || setting === null) return <MySpinner />;

    return (
        <Container className="bg-white">
            <Form onSubmit={send} className="col-md-10 mx-auto p-3 bg-white rounded shadow">
                <h3 className="text-center fw-bold mb-3">Câu hỏi tuyển sinh</h3>
                <p className="text-center fst-italic text-info-emphasis">
                    Hệ thống sẽ ghi nhận câu hỏi từ {setting.fromTime} đến {setting.toTime} trong ngày.
                </p>

                {notify.variant === "" ? "" : <Alert variant={notify.variant}>{notify.content}</Alert>}

                <Form.Group className="mb-3">
                    <Form.Label>Hệ đào tạo</Form.Label>
                    <Form.Select 
                        name="admissionType" 
                        value={question.admissionType} 
                        onChange={(e) => change(e, "admissionType")} 
                        required
                    >
                        <option selected><i>Chọn hệ đào tạo muốn đặt câu hỏi</i></option>
                        {admissionType.map(type => 
                            <option value={type.id}>{type.name}</option>
                        )}
                    </Form.Select>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Nội dung câu hỏi</Form.Label>
                    <Form.Control 
                        type="text" 
                        as="textarea" 
                        rows={3}
                        value={question.content}
                        onChange={(e) => change(e, "content")} 
                        placeholder="Nhập nội dung câu hỏi ở đây"
                        />
                </Form.Group>
                
                <Form.Group className="mb-1 col-12 text-center">
                    {user === null ? 
                        <Alert variant="warning">
                            Vui lòng đăng nhập 
                            <Link to="/login" className="mx-1">
                            ở đây
                            </Link> để gửi câu hỏi.
                        </Alert>
                    :
                    <>
                        {loading === true ? <MySpinner /> : 
                            <Button variant="info" type="submit">Gửi câu hỏi</Button>
                        }
                    </>
                    }
                </Form.Group>
            </Form>
        </Container>
    );
};

export default UserQuestion;
