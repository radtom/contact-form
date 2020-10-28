import React from 'react';
import { RequestDto, RequestTypeDto } from './types';
import axios from "axios";
import styled from "@emotion/styled"

const FormBody = styled.div`
    margin: auto;
    padding: 0;
    display: flex;
    justify-content: center;
    flex-direction: column;
    width: 25%;
`

const Title = styled.h1`
    text-align:center;
    font-family: Arial;
`

const InputWrapper = styled.div`
    padding-top: 15px;
    display: flex;
    justify-content: center;
    flex-direction: column;
`

const TextAreaWrapper = styled.div`
    width: 100%;
    padding-top: 15px;
    display: flex;
    justify-content: center;
    flex-direction: column;
`

const TextArea = styled.textarea`
    resize: vertical;
`

const Label = styled.label`
    font-family: Arial;
    padding-bottom: 10px;
    font-size: 14px;
`

const CharCounter = styled.p`
    color: grey;
    position: relative;
    top: -30px;
    right: 5%;
    margin-left: auto;
    font-family: Arial;
    margin-bottom: 0;
    font-size: 12px;
`

const Error = styled.p`
    padding-top: 2px;
    margin: 0;
    color: red;
`

const Success = styled.p`
    padding-top: 2px;
    margin: 0;
    color: green;
`

const Button = styled.button`
    background-color: #0099cc;
    color: white;
    width: 50%;
    height: 40px;
    margin-left: auto;
    margin-top: -10px;
    font-size: 14px;
    border: none; 
    border-radius: 9px;
    font-family: Arial;
`

interface State {
    requestType: number;
    policyNumber: string;
    name: string;
    surname: string;
    body: string;
    selectOptions: RequestTypeDto[]
    policyBlankError: boolean;
    policyFormatError: boolean;
    nameBlankError: boolean;
    nameFormatError: boolean;
    surnameBlankError: boolean;
    surnameFormatError: boolean;
    bodyBlankError: boolean;
    successMessage: boolean;
    errorMessage: boolean;

}

export default class ContactForm extends React.Component<{}, State> {

    state = {
        requestType: 1,
        policyNumber: "",
        name: "",
        surname: "",
        body: "",
        selectOptions: [] as RequestTypeDto[],
        policyBlankError: false,
        policyFormatError: false,
        nameBlankError: false,
        nameFormatError: false,
        surnameBlankError: false,
        surnameFormatError: false,
        bodyBlankError: false,
        successMessage: false,
        errorMessage: false,
    }

    render(){

        const { 
            requestType,
            policyNumber,
            name,
            surname,
            body,
            selectOptions,
            policyBlankError,
            policyFormatError,
            nameBlankError,
            nameFormatError,
            surnameBlankError,
            surnameFormatError,
            bodyBlankError,
            successMessage,
            errorMessage,
        } = this.state

        return(
        <FormBody>
            <Title>Contact us</Title>
            {successMessage && 
                <Success>Request submitted successfully</Success>
            }
            {errorMessage &&
                <Error>Something went wrong</Error>
            }
            <InputWrapper>
                <Label>Kind of Request</Label>
                <select onChange={this.handleSelect} value={requestType}>
                    {selectOptions.map(option => 
                        {return <option value={option.id}>{option.name}</option>} )}
                </select>
            </InputWrapper>
            <InputWrapper>
                <Label>Policy Number</Label>
                <input type="text" name="policy" value={policyNumber} onChange={this.handlePolicyChange}/>
                {policyBlankError &&
                    <Error>Policy Number cannot be blank</Error>
                }
                {policyFormatError &&
                    <Error>Policy can only contain letters and numbers</Error>
                }
            </InputWrapper>
            <InputWrapper>
                <Label>Name</Label>
                <input type="text" name="name" value={name} onChange={this.handleNameChange}/>
                {nameBlankError &&
                    <Error>Name cannot be blank</Error>
                }
                {nameFormatError &&
                    <Error>Name can only contain letters</Error>
                }
            </InputWrapper>
            <InputWrapper>
                <Label>Surname</Label>
                <input type="text" name="surname" value={surname} onChange={this.handleSurnameChange}/>
                {surnameBlankError &&
                    <Error>Surname cannot be blank</Error>
                }
                {surnameFormatError &&
                    <Error>Surname can only contain letters</Error>
                }
            </InputWrapper>
            <TextAreaWrapper>
                <Label>Your Request</Label>
                <TextArea value={body} onChange={this.handleBodyChange} maxLength={5000} rows={10}/>
                <CharCounter>{body.length}/5000</CharCounter>
                {bodyBlankError &&
                    <Error>Request body cannot be blank</Error>
                }
            </TextAreaWrapper>
            <Button onClick={this.handleSubmit}>SEND REQUEST</Button>
        </FormBody>)
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
                .then(response => {
                    this.setState({
                        requestType: 1,
                        policyNumber: "",
                        name: "",
                        surname: "",
                        body: "",
                        successMessage: true,
                    })
                })
                .catch(error => this.setState({errorMessage: true}))
        }
    }

    validateState = () => {
        let ret = true;
        if(this.state.policyNumber === ""){
            this.setState({policyBlankError: true})
            ret = false;
        }
        else this.setState({policyBlankError: false})
        var format = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
        if(format.test(this.state.policyNumber)){
            this.setState({policyFormatError: true})
            ret = false;
        }
        else this.setState({policyFormatError: false})
        if(this.state.name === ""){
            this.setState({nameBlankError: true})
            ret = false;
        }
        else this.setState({nameBlankError: false})
        if(this.state.surname === ""){
            this.setState({surnameBlankError: true})
             ret = false;
        }
        else this.setState({surnameBlankError: false})
        if(!/^[a-zA-Z]+$/.test(this.state.name)) {
            this.setState({nameFormatError: true})
            ret = false;
        }
        else this.setState({nameFormatError: false})
        if(!/^[a-zA-Z]+$/.test(this.state.surname)) {
            this.setState({surnameFormatError: true})
            ret = false;
        }
        else this.setState({surnameFormatError: false})
        if(this.state.body === "") {
            this.setState({bodyBlankError: true})
            ret = false;
        }
        else this.setState({bodyBlankError: false})
        return ret;
    }

}