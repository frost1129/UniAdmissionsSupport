import { faCalendarDay } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Card, Image, Row } from "react-bootstrap";
import { Link } from "react-router-dom";

const SearchPostItem = ({ post }) => {

    return (
        <>
        <Link className="text-decoration-none" to={`/posts/${post.id}`}>
            <Card className="shadow-sm p-1 my-3 bg-white rounded">
                <Row>
                    <div className="col-3">
                        <Image src={post.image} fluid rounded />
                    </div>
                    <Card.Body className="col-9">
                        <Card.Title className="text-primary link-opacity-75-hover">
                            {post.title}
                        </Card.Title>
                        <Card.Subtitle className="mb-2">
                            {post.admissionType.name}
                        </Card.Subtitle>
                        <Card.Text className="text-black">
                            <FontAwesomeIcon
                                className="mx-1"
                                icon={faCalendarDay}
                            />
                            {post.updatedDate}
                        </Card.Text>
                    </Card.Body>
                </Row>
            </Card>
        </Link>
        </>
    );
};

export default SearchPostItem;
