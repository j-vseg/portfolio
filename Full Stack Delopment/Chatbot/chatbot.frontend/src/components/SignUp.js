   import '../css/login.css'
import {useState} from 'react';
import Image from '../img/background-image.jpg'

function SignUp(props) {

  const [firstName, setFirstName] = useState();
  const [lastName, setLastName] = useState();
  const [email, setEmail] = useState();
  const [pass, setPass] = useState();

  const handleSubmit = () => {
    // magic login stuff XD kekw
    props.accountNew(email, pass, firstName, lastName);
  }

  const emailChanged = e => {
    setEmail(e.target.value);
  }
  const passChanged = e => {
    setPass(e.target.value);
  }
  const firstNameChanged = e => {
    setFirstName(e.target.value);
  }
  const lastNameChanged = e => {
    setLastName(e.target.value);
  }

    return (
      <section className="login">
        <div className="imgBx">
          <img src={Image} alt="Bas World Main Office"></img>
        </div>
        <div className="contentBx">
          <div className="formBx">
            <h2>Sign up</h2>
            <form 
            onSubmit = {handleSubmit}>
                <div className="inputBx">
                <input placeholder="First name.." type="text" name="firstName" onChange={firstNameChanged}></input>
              </div>
              <div className="inputBx">
                <input placeholder="Last name.." type="text" name="lastName" onChange={lastNameChanged}></input>
              </div>
              <div className="inputBx">
                <input placeholder="Email.." type="text" name="email" onChange={emailChanged}></input>
              </div>
              <div className="inputBx">
                <input placeholder="Password.." type="password" name="password" onChange={passChanged}></input>
              </div>
              <div className="inputBx">
                <button type="button" value="Login" onClick={handleSubmit}>Sign up</button>
              </div>
            </form>
          </div>
        </div>
      </section>
      );
    }
    
    export default SignUp;