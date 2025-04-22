import React from 'react';
import Project from './Projects';

function ProjectList() {
  const [keyword, setKeyword] = React.useState('');
  const [category, setCategory] = React.useState('all');
  const [tag, setTag] = React.useState('all');

  const handleFilterChange = (filterType, filterValue) => {
    console.log(filterType + ' = ' + filterValue.target.value);
    // Add your filter logic here
  };
  const handleSearch = (e) => {
    setKeyword(e.target.value);
    console.log(keyword);
    // Add your search logic here
  };
  const tags = [
    { text: 'Java', id: 'java', value: 'java' },
    { text: 'JavaScript', id: 'javascript', value: 'javascript' },
    { text: 'React', id: 'react', value: 'react' },
    { text: 'NodeJS', id: 'nodejs', value: 'node.js' },
    { text: 'Python', id: 'python', value: 'python' },
    { text: 'Django', id: 'django', value: 'django' },
    { text: 'Flask', id: 'flask', value: 'flask' },
    { text: 'Spring', id: 'spring', value: 'spring' },
    { text: 'SpringBoot', id: 'springboot', value: 'springboot' },
    { text: 'Angular', id: 'angular', value: 'angular' },
    { text: 'VueJS', id: 'vuejs', value: 'vuejs' },
    { text: 'HTML', id: 'html', value: 'html' },
    { text: 'CSS', id: 'css', value: 'css' },
    { text: 'Bootstrap', id: 'bootstrap', value: 'bootstrap' },
  ];
  return (
    <>
      <div className="d-flex px-5 relative lg:px-0 lg:flex lg:gap-5 lg:justify-content-center py-5 sticky">
        <section className="filterSection w-25 lg:w-1/4 ">
          <div
            style={{ height: '800px', overflow: 'auto' }}
            className="card p-2 sticky top-10 overflow-auto"
          >
            <div className="design d-flex justify-content-between align-items-center">
              <h4 className="mb-5">Filter Options</h4>

              <i
                className="bi bi-filter-square mb-5"
                style={{ fontSize: '1.5rem', cursor: 'pointer' }}
              ></i>
            </div>

            <h5 className="m-2"> Category</h5>
            <hr />
            <div className="form-check">
              <input
                onChange={(value) => handleFilterChange('category', value)}
                className="form-check-input"
                type="radio"
                name="category"
                id="all"
                value="all"
                defaultChecked
              />
              <label className="form-check-label" htmlFor="all">
                All
              </label>
            </div>
            <div className="form-check">
              <input
                onChange={(value) => handleFilterChange('category', value)}
                className="form-check-input"
                type="radio"
                name="category"
                id="FrontEnd"
                value="FrontEnd"
              />
              <label className="form-check-label" htmlFor="FrontEnd">
                FrontEnd
              </label>
            </div>
            <div className="form-check">
              <input
                onChange={(value) => handleFilterChange('category', value)}
                className="form-check-input"
                type="radio"
                name="category"
                id="backend"
                value="backend"
              />
              <label className="form-check-label" htmlFor="option3">
                BackEnd
              </label>
            </div>
            <div className="form-check">
              <input
                onChange={(value) => handleFilterChange('category', value)}
                className="form-check-input"
                type="radio"
                name="category"
                id="fullstack"
                value="fullstack"
              />
              <label className="form-check-label" htmlFor="fullstack">
                FullStack
              </label>
            </div>

            <h5 className="m-2 pt-3"> Tag</h5>
            <hr />
            {tags.map((tag) => (
              <div className="form-check my-1" key={tag.id}>
                <input
                  onChange={(value) => handleFilterChange('tag', value)}
                  className="form-check-input"
                  type="radio"
                  name="tag"
                  id={tag.id}
                  value={tag.value}
                />
                <label className="form-check-label" htmlFor={tag.id}>
                  {tag.text}
                </label>
              </div>
            ))}
          </div>
        </section>
        <section className="ProjectListSection px-4 w-75 lg:w-3/4">
          <div
            style={{ height: '800px', overflow: 'auto' }}
            className="h-800px "
          >
            <div className="search mb-4 d-flex justify-content-between align-items-center">
              <div className="input-group" style={{ width: '700px' }}>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search projects..."
                  onChange={handleSearch}
                />
                <span className="input-group-text">
                  <i className="bi bi-search"></i>
                </span>
              </div>
            </div>
            <div className="d-block">
              {keyword
                ? [1, 1, 1].map((item) => <Project key={item} />)
                : [1, 1, 1, 1].map((item) => <Project key={item} />)}
            </div>
          </div>
        </section>
      </div>
    </>
  );
}

export default ProjectList;
