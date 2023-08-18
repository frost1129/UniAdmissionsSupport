import { Container, Spinner } from "react-bootstrap";

export default function MySpinner(params) {
    return (
        <Container className="text-center my-2">
            <Spinner animation="border" variant="info" />
        </Container>
    );
};