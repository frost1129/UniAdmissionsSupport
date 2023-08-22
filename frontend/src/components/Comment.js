import React from "react";
import { Image } from "react-bootstrap";

const Comment = () => {
    return (
        <div className="d-flex mb-4">
            <div className="flex-shrink-0">
                <Image
                    src="https://dummyimage.com/50x50/ced4da/6c757d.jpg"
                    roundedCircle
                />
            </div>
            <div className="ms-3">
                <div className="fw-bold">User name</div>
                <p>Content....</p>

                <div>Chứa các cmt con.....</div>

            </div>
        </div>
    );
};

export default Comment;
