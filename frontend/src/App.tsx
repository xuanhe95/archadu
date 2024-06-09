import React from 'react';
import { useEffect, useState } from 'react';
import axios from 'axios';
import logo from './logo.svg';
import './App.css';

function App() {

  const [message, setMessage] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/test')
      .then(response => {
        setMessage(response.data);
      })
      .catch(error => console.error('Error:', error));
  }, []);

  if (message) {
    return (
      <div className="App">
        <header className="App-header">
          <h3>Message from Backend</h3>
          <h1>{message}</h1>
        </header>
      </div>
    );
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
