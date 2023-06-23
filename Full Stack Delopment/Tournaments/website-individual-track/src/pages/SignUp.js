import axios from 'axios';
import '../css/login.css'
import image from '../img/background.jpg'
import {useNavigate} from 'react-router-dom'

function SignUp() {
  const navigate = useNavigate();

  function navigateToHome() {
    axios.post('http://localhost:8080/accounts', {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        role: "Customer",
        email: document.getElementById('email').value,
        phoneNumber: document.getElementById('phone').value
      })
      .then(function (response) {
        navigate('/');
        alert("Account was successfully created");
      })
      .catch(function (error) {
        console.log(error);
      }); 
}

    return (
        <section className="login">
        <div className="imgBx">
          <img src={image} alt=""></img>
        </div>
        <div className="contentBx">
          <div className="formBx">
            <h2>Sign up</h2>
            <form>
              <div className="inputBx">
                <label>Username</label>
                <input type="text" id="username" required minLength={3} maxLength="25"></input>
              </div>
              <div className="inputBx">
                <label>Password</label>
                <input type="password" id="password" rerequired minLength={3} maxLength="25"quired></input>
              </div>
              <div className="inputBx">
                <label>Email</label>
                <input type="email" id="email" required></input>
              </div>
              <div className="inputBx">
                <label>Phonenumber</label>
                <input type="tel" id="phone"></input>
              </div>
              <div className="inputBx">
                <input type="submit" onClick={navigateToHome} value="Sign up"></input>
              </div>
            </form>
          </div>
        </div>
      </section>
        );
    }
    
    export default SignUp;