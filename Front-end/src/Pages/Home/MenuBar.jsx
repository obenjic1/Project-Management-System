import { useState } from 'react';
import React from 'react';
import { Dropdown, Badge } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

import './MenuBar.css';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import CreateProjectForm from '../Forms/CreateProjectForm';

function MenuBar() {
  // const [modalShow, setModalShow] = useState(false);
  const navigate = useNavigate();
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <nav className="menu-bar">
      <div className="menu-logo">Project Management System</div>
      <ul className="menu-links">
        <li className="link">
          <a variant="primary" onClick={handleShow}>
            New Project
          </a>

          <Modal
            show={show}
            onHide={handleClose}
            animation={true}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
          >
            <Modal.Header closeButton className="modal-header">
              <Modal.Title>Create New Project</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <CreateProjectForm />
            </Modal.Body>
          </Modal>
        </li>
        <li>
          <a className="link" onClick={() => navigate('/subscription')}>
            Upgrade Plan
          </a>
        </li>
        <li>
          <a className="link" onClick={() => navigate('/')}>
            Projects
          </a>
        </li>
        <li>
          <div className="d-flex">
            <Dropdown>
              <Dropdown.Toggle
                variant=""
                id="dropdown-basic"
                style={{ color: 'white' }}
              >
                <i
                  class="bi bi-person-circle "
                  style={{
                    fontSize: '1rem',
                    cursor: 'pointer',
                    color: 'white',
                  }}
                ></i>
              </Dropdown.Toggle>

              <Dropdown.Menu>
                <Dropdown.Item
                  href="#/action-1"
                  className="text-primary"
                  style={{ hover: { backgroundColor: 'green' } }}
                >
                  {' '}
                  Log Out
                </Dropdown.Item>
              </Dropdown.Menu>
            </Dropdown>
            <p>hello</p>
          </div>
        </li>
      </ul>
    </nav>
  );
}

export default MenuBar;
