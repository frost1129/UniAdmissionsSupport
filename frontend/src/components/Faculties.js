import React, { useEffect, useState } from "react";
import { Card, Container, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import Api, { endpoints } from "../config/Api";
import MySpinner from "./MySpinner";

const Faculties = () => {
    const [faculties, setFaculties] = useState(null);

    useEffect(() => {
        const loadFaculties = async () => {
            let {data} = await Api.get(endpoints["faculties"]);
            setFaculties(data);
        }

        loadFaculties();
    }, []);

    if (faculties === null) return <MySpinner />;

    return (
        <Container id="faculties" className="col-md-10 mx-auto py-2 my-3 bg-white">
            <Row>
                <div class="col-12 col-md-4 mb-3 text-md-start text-center">
                    <h3 className="text-primary fw-bold">Thông tin các Khoa</h3>
                    <p>Thông tin về các khoa đào tạo của trường Đại học Mở Thành phố Hồ Chí Minh.</p>
                </div>

                {faculties.map(faculty => 
                <div key={faculty.id} class="col-6 col-md-4 mb-3 text-center thumbnail">
                    <Link to={`/faculties/${faculty.id}`} className="text-decoration-none">
                        <Card>
                            <Card.Img src="https://res.cloudinary.com/dbh8vdpi7/image/upload/v1694168330/img_cb2nq9.jpg"/>
                            <Card.ImgOverlay>
                                <h3 className="text-white pt-5 mt-5">{faculty.name}</h3>
                            </Card.ImgOverlay>
                        </Card>
                    </Link>
                </div>
                )}
            </Row>
        </Container>
    );
};

export default Faculties;
