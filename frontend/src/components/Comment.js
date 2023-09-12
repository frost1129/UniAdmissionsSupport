import React from "react";
import { Image } from "react-bootstrap";
import Moment from "react-moment";

const Comment = ({ comment }) => {
    return (
        <div className="d-flex mb-4 bg-white">
            <div className="flex-shrink-0">
                <Image
                    src={comment.userId.image}
                    roundedCircle
                    style={{height: "50px", width: "50px", objectFit: "cover"}}
                />
            </div>
            <div className="ms-3">
                <div>
                    <b>{comment.userId.lastName} {comment.userId.firstName}</b> - <Moment locale="vi" fromNow>{comment.updatedDate}</Moment>
                </div>
                <p>{comment.content}</p>
                {/* <div>Chứa các cmt con.....</div> */}
            </div>
        </div>
    );
};

export default Comment;
