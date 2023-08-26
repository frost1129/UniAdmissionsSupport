import React, { useState } from 'react'
import { Button, Card, Container, Row, Table } from 'react-bootstrap'
import AdminNav from './AdminNav';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInfoCircle, faPencilAlt } from '@fortawesome/free-solid-svg-icons';
import ImageModal from '../components/ImageModal';

const Admin = () => {
	const [imgShow, setImgShow] = useState(false);
    const [imgUrl, setImgUrl] = useState('');

    const handleImageClick = (url) => {
        setImgUrl(url);
        setImgShow(true);
    };

    const handleCloseImage = () => {
        setImgShow(false);
    };

	return (
		<Container fluid>
			<Row>
				<div className='col-3 col-xl-2 bg-dark'>
					<AdminNav/>
				</div>
				<div className='col-9 col-xl-10 pt-3 overflow-y-auto'>
					<Container className='py-2' fluid>
						<h4 className=''>Banner</h4>
						<Row className='flex-nowrap overflow-x-auto py-2'>
							<Card style={{ width: '12rem' }} className='p-2 me-2'>
							<Link className='text-decoration-none'>
								<div 
									onClick={() => console.log("hello")}
									style={{ height: '11rem'}}
									className='d-flex justify-content-center align-items-center bg-body-secondary rounded fw-bolder'
								>
									+
								</div>
							</Link>
							</Card>
							<Card style={{ width: '12rem' }} className='pt-2 px-2 me-2'>
								<Card.Img 
									alt="Banner"
									src="https://dummyimage.com/600x400/aaa/fff" 
									onClick={() => handleImageClick("https://dummyimage.com/600x400/aaa/fff")}
								/>
								<Card.Body className='text-center'>
									<Button variant="danger">Xóa</Button>
								</Card.Body>
							</Card>
						</Row>
					</Container>

					<Container className='py-2' fluid>
						<h4 className=''>Bài đăng nổi bật</h4>
						<Table hover striped>
							<thead className="text-center">
								<tr>
									<th></th>
									<th className="col-3">Tiêu đề</th>
									<th>Người đăng</th>
									<th>Thời gian</th>
									<th>Bình luận</th>
									<th></th>
								</tr>
							</thead>
							<tbody className="text-center">
								<tr>
									<td>1</td>
									<td className="text-start">1</td>
									<td>1</td>
									<td>1</td>
									<td>
										<Button variant="danger" className="rounded-pill p-0 px-2">Not Allow</Button>
									</td>
									<td>
										<Link className="btn p-0 px-1 m-0">
											<FontAwesomeIcon icon={faInfoCircle} style={{color: "#2e2eff"}}/>
										</Link>
										<Link className="btn p-0 px-1 m-0">
											<FontAwesomeIcon icon={faPencilAlt}/>
										</Link>
									</td>
								</tr>
							</tbody>
						</Table>
					</Container>
					
				</div>
			</Row>

			<ImageModal
                imageUrl={imgUrl}
                show={imgShow}
                onClose={handleCloseImage}
            />
		</Container>
	)
};

export default Admin;
