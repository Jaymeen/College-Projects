import React, { useState } from 'react';
import { Button, TextField } from '@material-ui/core';
import axios from 'axios';
import { useNavigate } from 'react-router-dom'; // Import useNavigate instead of useHistory

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); // Use useNavigate hook

  // Handle changes in the username field
  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  // Handle changes in the password field
  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  // Handle the form submission and Axios call
  const handleLogin = async () => {
    if (!username || !password) {
      console.error('Username and password are required');
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/home/authenticate', {
        username: username,
        password: password,
      });

      // Handle the response, e.g., store authentication token, redirect, etc.
      console.log('Authentication successful:', response.data);

      if (response.data !== null) {
        // Redirect to the '/allStudents' page
        navigate(`/courses/${username}`);
      } else {
        console.error('Authentication failed:', 'Invalid credentials');
      }
    } catch (error) {
      // Handle errors, e.g., display an error message
      console.error('Authentication failed:', error.message);
    }
  };

  
    // Return the login form with email and password fields
    return (
      <form>
        <TextField
          id="filled-basic"
          label="Username"
          variant="filled"
          margin="normal"
          value={username}
          onChange={handleUsernameChange}
        />
        <br></br>
        <TextField
          id="standard-password-input"
          label="Password"
          type="password"
          autoComplete="current-password"
          variant="standard"
          margin="normal"
          value={password}
          onChange={handlePasswordChange}
        />
        <br>
        </br>
        <br>
        </br>
        <Button variant="contained" color="primary" onClick={handleLogin}>
          Submit
        </Button>
      </form>
    );
  };
  export default LoginPage;