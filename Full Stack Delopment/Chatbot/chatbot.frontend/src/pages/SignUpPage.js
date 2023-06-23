import {useEffect} from "react";
import {useCookies} from 'react-cookie';
import {useNavigate} from 'react-router-dom';
import axios, { Axios } from "axios";
import SignUp from "../components/SignUp";

function SignUpPage(props){
    const navigate = useNavigate();
    /*
    useEffect(() => {
        if(cookies.token === '1'){
            navigate('/FAQ');
        }
    });
    */
    const accountNew = (email, pass, firstName, lastName) => {
        axios.post('http://localhost:8080/users/', {
            "email": email,
            "password": pass,
            "firstName": firstName,
            "lastName": lastName
        })
        .then((response) => {
            props.doLogin(email, pass);
            navigate("/");
        })
        .catch(() => {});
    };
    
    return <SignUp accountNew={accountNew} />
}

export default SignUpPage;