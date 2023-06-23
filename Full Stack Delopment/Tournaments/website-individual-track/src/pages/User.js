import axios from "axios";
import jwt_decode from "jwt-decode";
import { useState, useEffect } from 'react'
import {useNavigate} from 'react-router-dom';

const User = () => {
    var token = null;
    let [user, setUser] = useState({});
    const navigate = useNavigate();
    
    useEffect(() => {
        if (localStorage.getItem('accessToken') === null) { navigate('/login'); }
        else {
            token = jwt_decode(localStorage.getItem('accessToken'));
            axios.get("http://localhost:8080/accounts/getById/"+token.accountId/*, {headers: {
                "Authorization": "bearer " + localStorage.getItem("accessToken")}})*/)
                .then (function (response) {
                    setUser(response.data);
                })
                .catch((error) =>
                    console.log(error)) 
        }
    }, [])

    function modifyUser() {
        if (localStorage.getItem('accessToken') === null) { navigate('/login'); }
        else {
            token = jwt_decode(localStorage.getItem('accessToken'));
            if (document.getElementById("password").value !== undefined) {alert("Please, enter a password."); }
            else {
                if (document.getElementById("password") !== undefined && document.getElementById('email') !== undefined 
                && document.getElementById('tel') !== undefined) {
                    axios.put('http://localhost:8080/accounts/'+token.accountId, { 
                        id: token.accountId,
                        username: token.sub,
                        password: document.getElementById("password").value,
                        role: token.role,
                        email: document.getElementById('email').value,
                        phoneNumber: document.getElementById('tel').value
                    })
                    .catch(error => {
                        console.error(error);
                    });
                }
            }
        }
    }

    return (
    <div>
        <div className="container">
            <div className='content'>
                <h1>Account information</h1>
                <div className="row g-2">
                    <div className="col-md-6">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Username</span>
                            </div>
                            <input id="username" type="text" class="form-control" placeholder="Username" value={user.username} readOnly></input>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Password</span>
                            </div>
                            <input id="password" type="password" class="form-control" placeholder="Password" minLength={8} required></input>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Email</span>
                            </div>
                            <input id="email" type="email" class="form-control" placeholder="Email" value={user.email}></input>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Phone number</span>
                            </div>
                            <input id="tel" type="tel" class="form-control" placeholder="Phone number" value={user.phoneNumber}></input>
                        </div>
                        <button type="submit" onClick={modifyUser} className="btn btn-outline-success float-right">Update information</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
        );
    }

    export default User;