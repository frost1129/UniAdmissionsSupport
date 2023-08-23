import { faCalendarDay } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Card, Image, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

const SearchPostItem = (props) => {
    const { title, type, date } = props.post;

    return (
        <>
        <Link className="text-decoration-none">
            <Card className="shadow-sm p-1 my-3 bg-white rounded">
                <Row>
                    <div className="col-3">
                        <Image src="https://dummyimage.com/600x400/aaa/fff" fluid rounded />
                    </div>
                    <Card.Body className="col-9">
                        <Card.Title className="text-primary link-opacity-75-hover">
                            {title}
                        </Card.Title>
                        <Card.Subtitle className="mb-2">
                            {type}
                        </Card.Subtitle>
                        <Card.Text className="text-black">
                            <FontAwesomeIcon
                                className="mx-1"
                                icon={faCalendarDay}
                            />
                            {date}
                        </Card.Text>
                    </Card.Body>
                </Row>
            </Card>
        </Link>
        </>
    );
};

export default SearchPostItem;
