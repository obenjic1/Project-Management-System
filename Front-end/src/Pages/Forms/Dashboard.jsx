import React from 'react';
import { useState, useEffect } from 'react';
function Dashboard() {
  const [data, setData] = useState('');
  useEffect(() => {
    const token = localStorage.getItem('token');

    fetch('http://localhost:5454/auth/dashboard', {
      headers: {
        Authorization: 'Bearer ' + token,
      },
    })
      .then((res) => res.json())
      .then((data) => setData(data.data))
      .catch(() => setData('Unauthorized'));
  }, []);

  return (
    <div>
      <h2>Dashboard</h2>
      <p>{data}</p>
    </div>
  );
}

export default Dashboard;
