import React from 'react';
import { useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';


function Login() {
  const [form, setForm] = useState({ email: '', password: '' });
  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });

  };
  const token = localStorage.getItem('token');
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(form);
    const res = await fetch('http://localhost:5454/auth/signIn', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',

      },
      body: JSON.stringify(form),
    });
    
    const data = await res.json();
   	
    if (res.ok) {
      localStorage.setItem('token', data.jwt);
      localStorage.setItem('userId', data.userId);
      setMessage('Login successful!');
     window.location.href = '/projects';
    } else {
      setMessage(data.error || 'Login failed');
    }
  };

  return (
    <>
    
      <form className = "form"  onSubmit={handleSubmit}>
      <h1 className="welcome"> Application Sign In </h1>
      <label  htmlFor="email"> Email :</label>
        <input name="email" onChange={handleChange} placeholder="Obe....@...com"  id="email"className="mb2" />
        <br />
        <label  htmlFor="password"> Password :</label>
        <input
          name="password"
          type="password"
          onChange={handleChange}
          placeholder="Password"
        />
        <br />
        <button className="log-in">Sign in</button>
        
      <p>{message} </p>
            <div className = "sign-up"> dont have an Account ... SignUp</div>
      </form>

    </>
  );
}

export default Login;
