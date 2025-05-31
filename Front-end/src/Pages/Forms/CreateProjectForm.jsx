import { useState } from 'react';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Swal from 'sweetalert2';


const token = localStorage.getItem("token");


function CreateProjectForm() {
  const [selectedTags, setSelectedTags] = useState([]);
  const tags = [
    { text: 'Java', value: 'java' },
    { text: 'JavaScript', value: 'javascript' },
    { text: 'React', value: 'react' },
    { text: 'NodeJS', value: 'node.js' },
    { text: 'Python', value: 'python' },
    { text: 'Django', value: 'django' },
    { text: 'Flask', value: 'flask' },
    { text: 'Spring', value: 'spring' },
    { text: 'SpringBoot', value: 'springboot' },
    { text: 'Angular', value: 'angular' },
    { text: 'VueJS', value: 'vuejs' },
    { text: 'HTML', value: 'html' },
    { text: 'CSS', value: 'css' },
    { text: 'Bootstrap', value: 'bootstrap' },
  ];

  const handleTagChange = (e) => {
    const selectedValue = e.target.value;
    if (!selectedTags.includes(selectedValue)) {
      setSelectedTags([...selectedTags, selectedValue]);
    }
  };

  const removeTag = (tagToRemove) => {
    setSelectedTags(selectedTags.filter((tag) => tag !== tagToRemove));
  };

  const handleForm =async (e) => {
    e.preventDefault();

    // Gather form data directly from the form elements
    const formData = {
      name: e.target.name.value,
      description: e.target.description.value,
      category: e.target.category.value,
      tags: selectedTags,
      checked: e.target.checked.checked,
    };

    console.log('Form Data:', formData);
    e.target.reset();
    setSelectedTags([]);
    // Add logic to handle form submission, e.g., API call
    
     const res = await fetch('http://localhost:5454/api/projects', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
     	 'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify(formData),
    });
    
    const data = await res.json();
    if (res.ok) {
    		 await Swal.fire({
        title: 'Good job!',
        text: 'Project Created Successfully!',
        icon: 'success',
        confirmButtonText: 'Cool',
      });

      window.location.href = '/projects';
    } else {
      const errorMsg = data.message || 'Failed to create project';
      Swal.fire('Error', errorMsg, 'error');
    
    }

  };

  return (
    <div>
      <form onSubmit={handleForm}>
        <h1 className="text-center">Create New Project</h1>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Project Name
          </label>
          <input
            type="text"
            className="form-control"
            id="name"
            name="name"
            required
            placeholder="project name ......"
          />
        </div>
        <div className="mb-3">
          <label htmlFor="description" className="form-label">
            Description
          </label>
          <input
            type="text"
            className="form-control"
            id="description"
            name="description"
            placeholder="project description ......"
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="category" className="form-label">
            Category
          </label>
          <div className="mb-3">
            <select
              className="form-select"
              aria-label="Default select example"
              id="category"
              name="category"
              required
            >
              <option value="" disabled selected>
                Select your category
              </option>
              <option value="full stack">Full Stack</option>
              <option value="front end ">Front end </option>
              <option value="backend ">Backend</option>
            </select>
          </div>
        </div>

        <div className="mb-3">
          <label htmlFor="tags" className="form-label">
            Tags
          </label>
          <div className="mb-3">
            <select
              className="form-select"
              id="tags"
              onChange={handleTagChange}
              name="tags"
              required
            >
              <option value="" disabled>
                Choose your tags
              </option>
              {tags.map((tag, key) => (
                <option key={key} value={tag.value}>
                  {tag.text}
                </option>
              ))}
            </select>
          </div>

          <div className="d-flex flex-wrap">
            {selectedTags.map((tag, index) => (
              <div key={index} className="small-display me-2 mb-2">
                <span className="text-sm">{tag}</span>
                <i
                  className="bi bi-x-circle h-3 w-3 ms-1"
                  onClick={() => removeTag(tag)}
                  style={{ cursor: 'pointer' }}
                ></i>
              </div>
            ))}
          </div>
        </div>

        <div className="mb-3 form-check">
          <input
            type="checkbox"
            className="form-check-input"
            id="checked"
            name="checked"
          />
          <label className="form-check-label" htmlFor="checked">
            Check me out
          </label>
        </div>
        <button type="submit" className="btn btn-primary float-end">
          Submit
        </button>
      </form>
    </div>
  );
}

export default CreateProjectForm;
