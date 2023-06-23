import '../css/login.css'
import {useState} from 'react';
import Image from '../img/background-image.jpg'
import { useNavigate } from 'react-router-dom';

function Login(props) {

  const [email, setEmail] = useState();
  const [pass, setPass] = useState();
  const [rememberMe, setRememberMe] = useState(); 

  const handleSubmit = () => {
    // magic login stuff XD kekw
    // no
    //props.loginNew(email, pass, rememberMe);
    props.doLogin(email, pass, rememberMe);
  }

  const navigate = useNavigate();
  function navigateToSignUp() { navigate("/signup"); }

  const emailChanged = e => {
    setEmail(e.target.value);
  }
  const passChanged = e => {
    setPass(e.target.value);
  }
  const rememberMeChanged = e => {
    setRememberMe(e.target.value);
  }

    return (
      <section className="login">
        <div className="imgBx">
          <img src={Image} alt="Bas World Main Office"></img>
        </div>
        <div className="contentBx">
          <div className="formBx">
            <h2>Login</h2>
            <form>
              <div className="inputBx">
                <input placeholder="Email.." type="text" name="email" onChange={emailChanged}></input>
              </div>
              <div className="inputBx">
                <input placeholder="Password.." type="password" name="password" onChange={passChanged}></input>
              </div>
              <div className="remember">
                <label><input type="checkbox" name="remember" onChange={rememberMeChanged}></input> Remember me?</label>
              </div>
              <div className="inputBx">
                <button type="button" value="Login" onClick={handleSubmit}>Login</button>
              </div>
              <div className="inputBx">
                <label>Don't have an account? <span onClick={navigateToSignUp}>Sign up here</span></label>
              </div>
            </form>
          </div>
        </div>
      </section>
      );
    }
    
    export default Login;