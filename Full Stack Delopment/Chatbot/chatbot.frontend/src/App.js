import './App.css';
import './css/templates/layout.css'
import 'https://kit.fontawesome.com/ecef4a84e2.js'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from './pages/Layout';
import FAQ from './pages/FAQ'
import {useEffect, useState} from 'react';
import {useCookies} from 'react-cookie';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import ChatManagement from './pages/chat-management/ChatManagement';
import Home from './pages/Home';
import axios from "axios";

function App() {
  const [cookies, setCookie, remCookie] = useCookies([]);
  const [account, setAccount] = useState(null);

  useEffect(() => {
    axios.defaults.headers["Authorization"] = cookies["authToken"];

    if(cookies["authToken"]) {
      axios.get("http://localhost:8080/users?self=true")
          .then(response => {
            setAccount(response.data.user)
          })
          .catch(() => {
          });
    }
  }, [])

  const userInfo = {
    account: account,
    setAccount: setAccount
  };

  const login = (email, password, rememberMe) => {
    let promise = axios.post("http://localhost:8080/login", {
      email: email,
      password: password
    });

    promise
        .then(response => {
          userInfo.setAccount(response.data.user);
          axios.defaults.headers["Authorization"] = response.data.authToken;

          if(rememberMe) {
            setCookie("authToken", response.data.authToken, {maxAge: 30 * 60}); //TODO: get JWT expiration date
          }
        })
        .catch(response => {});

    return promise;
  };

  const logOut = () => {
    userInfo.account = null;
    remCookie("authToken");
  };

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout doLogout={logOut} userInfo={userInfo} />}>
            <Route index element={<Home />} />
            <Route path="/" element={<Home />} />
            <Route path="login" element={<LoginPage doLogin={login}/>} />
            <Route path="faq" element={<FAQ userInfo={userInfo} />} />
            <Route path="signup" element={<SignUpPage doLogin={login} />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
