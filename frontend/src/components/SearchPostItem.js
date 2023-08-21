import { faCalendarDay } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Card } from "react-bootstrap";
import { Link } from "react-router-dom";

const SearchPostItem = (props) => {
    const { title, type, date } = props.post;

    return (
        <Card className="shadow-sm p-1 my-3 bg-white rounded">
            <Card.Body>
                <Card.Title className="text-info link-opacity-75-hover">
                    <Link className="text-decoration-none">{title}</Link>
                </Card.Title>
                <Card.Subtitle className="mb-2">
                    <Link className="text-decoration-none text-black link-opacity-75-hover">
                        {type}
                    </Link>
                </Card.Subtitle>
                <Card.Text className="text-black">
                    <FontAwesomeIcon className="mx-1" icon={faCalendarDay}/>
                    {date}
                </Card.Text>
            </Card.Body>
        </Card>
    );
};

export default SearchPostItem;
