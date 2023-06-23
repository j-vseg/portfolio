import axios from 'axios'
import '../css/login.css'
//import { useState, useEffect } from 'react';
//import { useCookies } from 'react-cookie';
import {useNavigate} from 'react-router-dom';
import image from '../img/background.jpg'

function Login() {
  //const [account, setAccount] = useState();
  //const [cookies, setCookie, removeCookie] = useCookies(['accountCookie']);
  const navigate = useNavigate();

 /* useEffect(() => {
    if (document.getElementById('username') !== undefined) {
      axios.get("http://localhost:8080/accounts/"+document.getElementById('username').value)
      .then (function (response) {
          setAccount(response.data);
      })
      .catch(error => console.log("Error: " + error))
    }
  })*/
  
  function navigateToHome() {    
    if (document.getElementById('username') !== undefined && document.getElementById('password') !== undefined && document.getElementById('remember') !== undefined) { 
      axios.post('http://localhost:8080/login', {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value
      })
      .then(function (response) {
        //console.log(document.getElementById('remember').value); // Todo: checked is always 'on'
        if (response.data !== undefined) {
          console.log("Token "+response.data.accessToken);
          /*var accountDecode = jwt_decode(cookies.accountCookie);
          if (document.getElementById('remember').value !== "on") { setCookie("accountCookie", accountDecode.accountId, {maxAge : 2592000}); }
          else { setCookie("accountCookie", accountDecode.accountId, {maxAge : 18000} ); }*/
          localStorage.setItem("accessToken",  response.data.accessToken)
          navigate("/dashboard"); 
          document.location.reload();
        }
      })
      .catch(function (error) {
        console.log(error);
      });
    }
  }

  function navigateToSignUp() {
    navigate('/signup');
  }

    return (
    <section className="login">
      <div className="imgBx">
        <img src={image} alt=""></img>
      </div>
      <div className="contentBx">
        <div className="formBx">
          <h2>Login</h2>
          <form>
            <div className="inputBx">
              <span>Username</span>
              <input type="text" id="username"></input>
            </div>
            <div className="inputBx">
              <span>Password</span>
              <input type="password" id="password"></input>
            </div>
            <div className="remember">
              <label><input type="checkbox" id="remember"></input> Remember me?</label>
            </div>
            <div className="inputBx">    
              <input type="button" onClick={navigateToHome} value="Login"></input>
            </div>
            <div className="inputBx">
              <label>No account yet? Sign up <span onClick={navigateToSignUp}>here</span></label>
            </div>
          </form>
        </div>
        </div>
      </section>
        );
    }

    export default Login;