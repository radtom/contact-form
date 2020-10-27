import React from 'react';
import { RequestDto, RequestTypeDto } from './types';
import axios from "axios";


interface State {
    requestType: number;
    policyNumber: string;
    name: string;
    surname: string;
    body: string;
    selectOptions: RequestTypeDto[]
}

export default class ContactForm extends React.Component<{}, State> {

    state = {
        requestType: 0,
        policyNumber: "",
        name: "",
        surname: "",
        body: "",
        selectOptions: [] as RequestTypeDto[],
    }

    render(){

        const { 
            requestType,
            policyNumber,
            name,
            surname,
            body,
            selectOptions
        } = this.state

        return(<div>
            <h1>Contact Us</h1>
            <select onChange={this.handleSelect} value={requestType}>
                {selectOptions.map(option => 
                    {return <option value={option.id}>{option.name}</option>} )}
            </select>
            <input type="text" name="policy" value={policyNumber} onChange={this.handlePolicyChange}/>
            <input type="text" name="name" value={name} onChange={this.handleNameChange}/>
            <input type="text" name="surname" value={surname} onChange={this.handleSurnameChange}/>
            <textarea value={body} onChange={this.handleBodyChange} maxLength={5000}/>
            <button onClick={this.handleSubmit}>Send Request</button>
        </div>)
    }

    componentDidMount(){
        this.loadOptions()
    }

    loadOptions = () => {
        axios.get("/rest/request-type").then(response => {
            this.setState({selectOptions: response.data})
            if(response.data.length > 0){
                this.setState({requestType: response.data[0].id})
            }
        })
        
    }

    handleSelect = (e: any) => {
        this.setState({requestType: e.target.value})
    }

    handlePolicyChange = (e: any) => {
        this.setState({policyNumber: e.target.value})
    }

    handleNameChange = (e: any) => {
        this.setState({name: e.target.value})
    }

    handleSurnameChange = (e: any) => {
        this.setState({surname: e.target.value})
    }

    handleBodyChange = (e: any) => {
        this.setState({body: e.target.value})
    }

    handleSubmit = () => {
        if(this.validateState()){
            const data: RequestDto = {
                requestType: this.state.requestType,
                policyNumber: this.state.policyNumber,
                name: this.state.name,
                surname: this.state.surname,
                body: this.state.body
            }
            axios.post("/rest/request/", data)
                .then(response => console.log(response))
                .catch(error => console.log(error))
        }
    }

    validateState = () => {
        if(this.state.policyNumber === ""){
            console.log("Policy number cannot be empty")
            return false;
        }
        var format = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
        if(format.test(this.state.policyNumber)){
            console.log("Policy number can contain only numbers and letters")
            return false;
        }
        if(this.state.name === ""){
            console.log("Name cannot be empty")
            return false;
        }
        if(this.state.surname === ""){
            console.log("Surname cannot be empty")
             return false;
            }
        if(!/^[a-zA-Z]+$/.test(this.state.name)) {
            console.log("Name can only contain letters")
            return false;
        }
        if(!/^[a-zA-Z]+$/.test(this.state.surname)) {
            console.log("Surname can only contain letters")
            return false;
        }
        if(this.state.body === "") {
            console.log("Body cannot be empty")
            return false;
        }
        return true;
    }

}
