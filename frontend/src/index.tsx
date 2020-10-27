import React from 'react';
import ReactDOM from 'react-dom';
import ContactForm from './ContactForm';
import axios from "axios";


function renderContactForm(){
  axios.defaults.headers.post["Content-Type"] = 'application/json'
  axios.defaults.headers.put["Content-Type"] = 'application/json'
  
  ReactDOM.render(
      <ContactForm />,
    document.getElementById('react-root')
  );
}


window.onload = () => {renderContactForm()}