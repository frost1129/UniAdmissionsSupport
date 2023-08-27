import React, { useState } from "react";
import { Button, Col, Form, InputGroup, Row } from "react-bootstrap";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import { useParams } from "react-router-dom";

const PostConfig = () => {
    const { slug } = useParams();
    const [post, setPost] = useState({
        "title": "", 
        "postType": "", 
        "admissionType": "", 
        "content": "",  
    });
    const modules = {
        toolbar: [
            [{ header: [1, 2, 3, 4, 5, 6, false] }],
            ["bold", "italic", "underline", "strike", "clean"],
            [{ color: [] }, { background: [] }],
            [{ script: "sub" }, { script: "super" }],
            ["blockquote", "code-block", "image"],
            [{ list: "ordered" }, { list: "bullet" }],
            [{ indent: "-1" }, { indent: "+1" }, { align: [] }],
        ],
    };

    const sendPost = (evt) => {
        evt.preventDefault();
        console.log({post});
    };

    const change = (evt, field) => {
        setPost({...post, [field]: evt.target.value});
    };

    const handleContentChange = (content) => {
        setPost(prevPost => ({
          ...prevPost,
          content: content
        }));
      };

    return (
        <div className="d-flex justify-content-end m-4">
            <Form className="col-xl-10 col-9 p-2" onSubmit={sendPost}>
                <div className="mb-4 text-primary-emphasis">
                    <h4>Chi tiết bài đăng</h4>
                </div>
                <Form.Group className="mb-2">
                    <Form.Label>Tiêu đề bài đăng</Form.Label>
                    <Form.Control onChange={(e) => change(e, "title")} type="text" placeholder="Nhập tiêu đề bài viết ở đây" required/>
                </Form.Group>
                <Row className="mb-3">
                    <Form.Group as={Col}>
                        <Form.Label>Loại bài đăng</Form.Label>
                        <Form.Select required value={post.postType} onChangeCapture={(e) => change(e, "postType")}>
                            <option value="">Chọn loại bài đăng</option>
                            <option value="post">Tin tuyển sinh</option>
                            <option value="livestream">Livestream tuyển sinh</option>
                        </Form.Select>
                    </Form.Group>
                    <Form.Group as={Col}>
                        <Form.Label>Hệ tuyển sinh</Form.Label>
                        <Form.Select required value={post.admissionType} onChangeCapture={(e) => change(e, "admissionType")}>
                            <option value="">Chọn hệ tuyển sinh</option>
                            <option value="1">Tin tuyển sinh</option>
                        </Form.Select>
                    </Form.Group>
                </Row>

                <Form.Group className="mb-3">
                    <Form.Label>Nội dung bài đăng</Form.Label>
                    
                    <ReactQuill
                        modules={modules}
                        theme="snow"
                        placeholder="Nội dung bài đăng"
                        value={ post.content }
                        onChange={handleContentChange}
                    />
                </Form.Group>

                <InputGroup className="mb-3">
                    <Button className="btn-lg btn-primary mx-auto fs-6" type="submit">
                        Lưu bài đăng
                    </Button>
                </InputGroup>
            </Form>
        </div>
    );
};

export default PostConfig;
