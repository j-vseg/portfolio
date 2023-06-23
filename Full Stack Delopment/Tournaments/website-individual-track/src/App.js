import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import 'https://kit.fontawesome.com/ecef4a84e2.js'
import "./css/templates/index.css"
import axios from 'axios'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import jwt_decode from "jwt-decode";
import {useState} from 'react';
import{ Cookies, useCookies} from 'react-cookie';
import Layout from './pages/Layout'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import Bundles from './pages/Bundles'
import Bundle from './pages/bundle/Bundle';
import Package from './pages/Package';
import Users from './pages/Users';
import Orders from './pages/Orders'
import SignUp from './pages/SignUp'
import ShoppingCart from './pages/shopping-cart/ShoppingCart'
import Error from './components/errorpage/Error'
import User from './pages/User'
import Statistics from './pages/Statistics'

function App() {
  const [token, setToken] = useState(0);
  const [cookies] = useCookies(['accountCookie']);  
  let errorMessage = "";


  (function() {
    var token = localStorage.getItem('accessToken');
    if (token) {
        axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("accessToken")}`;
    } else {
        delete axios.defaults.headers.common["Authorization"];
        //axios.defaults.headers.common['Authorization'] = null;
        /*if setting null does not remove `Authorization` header then try     
          delete axios.defaults.headers.common['Authorization'];*/      
    }

    axios.interceptors.response.use(response => {
      return response;
   }, error => {
     if (error.response.status === 401) {
      errorMessage = error.response.data.error;
      document.location.href = "/error-401";
     }
     return error;
   });

   axios.interceptors.response.use(response => {
    return response;
 }, error => {
   if (error.response.status === 403) {
    errorMessage = error.response.data.error;
    document.location.href = "/error-403";
   }
   return error;
 });
})();

  return (
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<Dashboard />} />
        <Route path="login" element={<Login />} />
        <Route path="dashboard" element={<Dashboard />} />
        <Route path="bundles" element={<Bundles />} />
        <Route path="bundles/:id" element={<Bundle />} />
        <Route path="bundles/:bundleId/packages/:id" element={<Package />} />
        <Route path="orders" element={<Orders />} />
        <Route path="users" element={<Users />} />    
        <Route path="signup" element={<SignUp />} />
        <Route path="shopping-cart" element={<ShoppingCart />} />
        <Route path="user" element={<User />} />
        <Route path="statistics" element={<Statistics />} />
        <Route path="error-401" element={<Error code="401" message={errorMessage} subText="Looks like you are not authorized for this page."/>} />
        <Route path="error-403" element={<Error code="403" message={errorMessage} subText="Looks like you are not authorized for this page."/>} />
        <Route path="*" element={<Error code="404" message="Page not found" subText="Looks like you came to the wrong pape on our server" />} />
      </Route>
    </Routes>
  </BrowserRouter>
  );
}

export default App;
