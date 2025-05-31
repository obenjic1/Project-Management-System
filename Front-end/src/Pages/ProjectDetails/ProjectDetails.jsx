import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import { Col, Toast, Card, Button, Badge, Modal } from 'react-bootstrap';
import { useState ,useEffect} from 'react';
import Issues from '../Issues/Issues';


function ProjectDetails() {
const token = localStorage.getItem("token");
const ProjectId = localStorage.getItem("projectId");

const [project,setProject]= useState([]);


     useEffect(() => {
        const fetchProject = async () => {
            try {
                const response = await fetch(`http://localhost:5454/api/projects/${ProjectId}`, {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    },
                });

                if (response.ok) {

                    const data = await response.json();
                    setProject(data);
                    

                } 
            } catch (error) {
                console.error("Error during fetch:", error);
            }
        };

        if (token) {
      
            fetchProject();
        }
    }, [token]);



  const handleCommentSubmit = (e) => {
    e.preventDefault();
    console.log('Comment submitted:', e.target.comment.value);
    e.target.reset();
  };
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const handleInvite = (e) => {
    e.preventDefault();
    console.log(e.target.email.value);
    e.target.email.value = '';
    handleClose();
  };

  const chats = [
    {
      name: 'oben',
      comment: 'create new project',
      image: (
        <i className="bi bi-person-circle rounded me-2" alt="profile pic"></i>
      ),
    },
    {
      name: 'Vincent',
      comment: 'edit profile task',
      image: (
        <i className="bi bi-person-circle rounded me-2" alt="profile pic"></i>
      ),
    },
  ];

  return (
    <>
      <Container>
        <Row>
          <Col sm={8}>
            <Card
              className="p-2 card mt-5"
              style={{ height: '320px', overflow: 'auto' }}
            >
              <Card.Body>
                <Card.Title> {project.name}</Card.Title>
                <Card.Text>
                 {project.description}
                </Card.Text>
                <div className="content">
                  <div className="leader d-inline mt-3 ">
                    {' '}
                    <span className="">
                      <label>Project Leader : </label>
                    </span>
			<span className="mx-2">
			  {project.user?.fullName || 'Unknown'}
			</span>
                  </div>
                  <div className="member mt-4 ">
                    {' '}
                    <span className="" style={{}}>
                      <label>Members : </label>
                    </span>
		    {project.team && project.team.length > 0 ? (
			  project.team.map((team, index) => (
			    <span className="mx-5 ml-4" key={index}>
			      {team.fullName}
			    </span>
			  ))
			) : (
			  <span className="text-muted ml-4">No team members</span>
			)}

                    <span className="ml-4">
                      <button
                        type="button"
                        onClick={handleShow}
                        class="btn btn-outline-primary"
                      >
                        Invite
                      </button>

                      <Modal
                        show={show}
                        onHide={handleClose}
                        animation={true}
                        centered
                      >
                        <Modal.Header closeButton>
                          <Modal.Title>Invite a User</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                          <form
                            action=""
                            onSubmit={handleInvite}
                            className="flex"
                          >
                            <input
                              //       type="search"
                              type="email"
                              name="email"
                              id="email"
                              placeholder="Type member email ...."
                              className="form-control "
                              aria-label="Email"
                              required
                            />
                            <button
                              type="submit"
                              className="btn btn-outline-success w-100%"
                              style={{
                                marginTop: '11px',
                                width: ' -moz-available',
                              }}
                            >
                              {' '}
                              Invite User
                            </button>
                          </form>
                        </Modal.Body>
                      </Modal>
                    </span>
                  </div>
                  <div className="leader  mt-4">
                    {' '}
                    <span className="">
                      <label>category : </label>
                    </span>
                    <span className="mx-5">{project.category}</span>
                  </div>
                  <div className="member mt-4">
                    {' '}
                    <span className="  ">
                      <label>Status : </label>
                    </span>
                    <span className="mx-5 text-lg" style={{}}>
                      <Badge bg="success"> In Progress</Badge>
                    </span>
                  </div>
                </div>
              </Card.Body>
            </Card>
            <Card
              className="p-2 card mt-3"
              style={{ height: '420px', overflow: 'auto' }}
            >
              <Card.Header>Tasks</Card.Header>
              <Card.Body>
                <section className="d-flex">
                  <Issues title={'boss man '} status={'pending validation'} />
                  <Issues title={'boss man '} status={'pending validation'} />
                  <Issues title={'boss man '} status={'pending validation'} />
                </section>
              </Card.Body>
            </Card>
          </Col>
          <Col sm={4}>
            <div className="p-2 card mt-5 border-success border-top-none mb-2">
              {' '}
              Chat Room
            </div>
            <div className=" p-2   card  border-primary ">
              <div
                className="card-body"
                style={{ height: '620px', overflow: 'auto' }}
              >
                {chats.map((chats, key) => (
                  <Toast key={key} className="mb-3">
                    <Toast.Header>
                      {chats.image}
                      <strong className="me-auto">{chats.name}</strong>
                      <small>11 mins ago</small>
                    </Toast.Header>
                    <Toast.Body>{chats.comment}</Toast.Body>
                  </Toast>
                ))}
              </div>
              <div className="card-footer">
                {' '}
                <form className="d-flex" onSubmit={handleCommentSubmit}>
                  <input
                    //       type="search"
                    type="Text"
                    name="comment"
                    id="comment"
                    placeholder="Type Comment ...."
                    className="form-control me-2"
                    aria-label="Search"
                    required
                  />
                  <button type="submit" className="btn btn-outline-success">
                    {' '}
                    Send
                  </button>
                </form>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </>
  );
}

export default ProjectDetails;
