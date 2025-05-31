import React from 'react';
import { Dropdown, Badge } from 'react-bootstrap';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';
import EditProjectForm from '../Forms/EditProjectForm';
import Modal from 'react-bootstrap/Modal';
import { useState } from 'react';


function Projects({Project}) {

  const [show, setShow] = useState(false);
  const handleClose2 = () => setShow(false);
 // const handleShow = () => setShow(true);
  const token = localStorage.getItem("token");


  const handleEdit = (Project) => {

    const editProject = localStorage.setItem("projectId",Project);
	setShow(true);
    
  };
  const handleDelete = (id) => {
  Swal.fire({
    title: 'Are you sure?',
    text: 'This action cannot be undone!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'Cancel',
  }).then((result) => {
    if (result.isConfirmed) {
      fetch(`http://localhost:5454/api/projects/${id}`, {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${token}`,
        },
      })
        .then(async (res) => {
          if (res.ok) {
            await Swal.fire('Deleted!', 'The project has been deleted.', 'success');
            window.location.href = '/projects';
          } else {
            const errorData = await res.json();
            const errorMsg = errorData.message || 'Something went wrong';
            Swal.fire('Error', errorMsg, 'error');
          }
        })
        .catch((err) => {
          console.error(err);
          Swal.fire('Error', 'Failed to delete project.', 'error');
        });
    }
  });
};
  const handleMoreOptions = () => {
    alert('More Options');
  };

  const navigate = useNavigate();

  return (
    <>
      <div className="card mb-3">
        <div className="card-body">
          <div className="header d-flex justify-content-between mb-3">
            <div
              className="something d-flex align-items-center"

            >
              {' '}
              <h5
                onClick={() => navigate(`/projectdetails/${Project.id}`)}
                className="card-title text-primary  "
                style={{ cursor: 'pointer' }}
              >
               {Project.name}
              </h5>
              <i className="bi bi-dot mx-4"></i>
              <h6 className="card-subtitle text-muted mx-3">
                Category: {Project.category}
              </h6>
            </div>

            <Dropdown>
              <Dropdown.Toggle variant="" id="dropdown-basic">
                <i
                  className="bi bi-three-dots-vertical"
                  style={{ fontSize: '1rem', cursor: 'pointer' }}
                ></i>
              </Dropdown.Toggle>
              <Dropdown.Menu>
                <Dropdown.Item href="#/action-1">Actions</Dropdown.Item>
                <hr />
                <Dropdown.Item
                  href="#/action-2"
                  className="text-primary"
                  onClick={()=>handleEdit(Project.id)}
                >
            <Modal
            show={show}
            onHide={handleClose2}
            animation={true}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
          >
            <Modal.Header closeButton className="modal-header">

            </Modal.Header>
            <Modal.Body>
              <EditProjectForm />
            </Modal.Body>
          </Modal>
                  Edit
                </Dropdown.Item>
               <Dropdown.Item
  onClick={() => handleDelete(Project.id)}
  className="text-danger"
>
                  Delete
                </Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </div>

          <p className="card-text">
             {Project.description}
          </p>
          <p className="card-text text-lg flex flex-wrap gap-2 items center">
          {
          
          Project.tags.map((type,index)=> 
           <Badge bg="secondary mx-3" style={{ padding: '8px' }} key={index}>
              {type}
            </Badge>
          
          )}
            
          </p>
          <p className="card-text">
            <i className="bi bi-person-circle"> </i>Created by: {Project.user.fullName}
            <small className="text-muted mx-3">created Date:  {Project.createdDate}</small>
          </p>
        </div>
      </div>
    </>
  );
}

export default Projects;
