import React from 'react';
import { Dropdown, Badge } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function Projects({Project}) {


  const handleEdit = () => {
    alert('Edit');
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
              justify-content-between
            >
              {' '}
              <h5
                onClick={() => navigate('/projectdetails/3')}
                className="card-title text-primary  "
                style={{ cursor: 'pointer' }}
              >
               {Project.id}
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
                  onClick={handleEdit}
                >
                  Edit
                </Dropdown.Item>
                <Dropdown.Item href="#/action-3" className="text-danger">
                  Delete
                </Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
          </div>

          <p className="card-text">
             {Project.description}
          </p>
          <p className="card-text text-lg flex flex-wrap gap-2 items center">
            <Badge bg="secondary mx-3" style={{ padding: '8px' }}>
              java
            </Badge>
            <Badge bg="secondary mx-3" style={{ padding: '8px' }}>
              Primary
            </Badge>
          </p>
          <p className="card-text">
            <i className="bi bi-person-circle"> </i>Created by: John Doe
            <small className="text-muted mx-3">created Date:  {Project.createdDate}</small>
          </p>
        </div>
      </div>
    </>
  );
}

export default Projects;
