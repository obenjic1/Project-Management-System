import { Dropdown, Badge } from 'react-bootstrap';

function Issues(props) {
  const handleOptions = () => {
    alert(' test csll ');
  };
  return (
    <div>
      <div className="card me-2 " style={{ width: '16em' }}>
        <div className="card-body">
          <h5 className="card-title">{props.title} </h5>
          <hr />
          <div className="accord d-flex justify-content-between">
            <p className="card-text">{props.status}</p>
            <div>
              <Dropdown className="">
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
                    onClick={handleOptions}
                  >
                    Edit
                  </Dropdown.Item>
                  <Dropdown.Item href="#/action-3" className="text-danger">
                    Delete
                  </Dropdown.Item>
                </Dropdown.Menu>
              </Dropdown>
            </div>
          </div>

          <a href="#" className="btn btn-primary">
            Go somewhere
          </a>
        </div>
      </div>
    </div>
  );
}

export default Issues;
