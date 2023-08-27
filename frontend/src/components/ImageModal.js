import React from 'react';
import { Modal } from 'react-bootstrap';

const ImageModal = ({ imageUrl, show, onClose }) => {
    return (
        <Modal show={show} onHide={onClose}>
            <Modal.Body>
                <img src={imageUrl} style={{ width: '100%' }} alt='zoomed photo' />
            </Modal.Body>
        </Modal>
    );
};

export default ImageModal;