import axios from 'axios'
import { Outlet, Link } from "react-router-dom";
import jwt_decode from "jwt-decode";
import { useCookies } from 'react-cookie';
import 'boxicons/css/boxicons.min.css'
import '../css/templates/sidebar.css'
import { useState, useEffect } from 'react'

const Layout = () => {
  //const [cookies, setCookie, removeCookie] = useCookies(['accountCookie']);
  //const [account, setAccount] = useState();
  var token = undefined;

  let sidebar = null;
  let closeBtn = null;

  if (localStorage.getItem('accessToken') !== null) {
    //console.log((jwt_decode(localStorage.getItem('accessToken')).exp)+" "+jwt_decode(localStorage.getItem('accessToken')).exp);
    //if (jwt_decode(localStorage.getItem('accessToken')).exp <= Date.now) {
      //console.log(jwt_decode(localStorage.getItem('accessToken')));
      token = jwt_decode(localStorage.getItem('accessToken'));
      //console.log(token);
    //}
  }

  useEffect(() => {
    sidebar = document.querySelector(".sidebar");
    closeBtn = document.querySelector("#btn");
    /*if (cookies.accountCookie !== undefined) { 
      console.log("Cookie " + cookies.accountCookie)
      axios.get("http://localhost:8080/accounts/getById/"+cookies.accountCookie)
      .then (function (response) {
          setAccount(response.data);
          console.log(response.data);
      })
      .catch(error => console.log("Error: " + error))}*/
  })

  function logout() {localStorage.removeItem('accessToken'); token = undefined; document.location.reload(); }

  function sideBar() {
    //closeBtn.addEventListener("click", () => {
      sidebar.classList.toggle("open");
      menuBtnChange();//calling the function(optional)
    };
  
    /*function openSideBar() {
    //searchBtn.addEventListener("click", () => { // Sidebar open when you click on the search iocn
      sidebar.classList.toggle("open");
      menuBtnChange(); //calling the function(optional)
    }; */
    
      // following are the code to change sidebar button(optional)
      function menuBtnChange() {
        if(sidebar.classList.contains("open")){
          closeBtn.classList.remove("bx-menu");
          closeBtn.classList.add("bx-menu-alt-right");//replacing the iocns class
        }
        else {
          closeBtn.classList.add("bx-menu");
          closeBtn.classList.remove("bx-menu-alt-right");//replacing the iocns class
          //closeBtn.classList.replace("bx-menu-alt-right","bx-menu");//replacing the iocns class
        }}

    return (
    <>
      <div className="sidebar">
      <Link to="/dashboard">
        <div className="logo-details">
          <i className="fa-solid fa-graduation-cap"></i>
          <div className="logo_name">Kooperdraat</div>
          <i className='bx bx-menu' id="btn" onClick={sideBar}></i>
        </div>
      </Link>
      <ul className="nav-list">
        <li>
            <i className='bx bx-search' onClick={sideBar}></i>
           <input type="text" placeholder="Search..."></input>
           <span className="tooltip">Search</span>
        </li>
        <li>
          <Link to="/dashboard">
            <i className='bx bx-grid-alt'></i>
            <span className="links_name">Dashboard</span>
          </Link>
           <span className="tooltip">Dashboard</span>
        </li>
        <li>
        <Link to="/bundles">
              <i className='bx bx-book-bookmark'></i>
            <span className="links_name">Bundles</span>
        </Link>
          <span className="tooltip">Bundles</span>
        </li>
        {(() => {
          if (token !== undefined && token.role !== "Customer") {
            return <div>
              <li>
              <Link to="/users">
                <i className='bx bx-user' ></i>
                <span className="links_name">Users</span>
              </Link>
              <span className="tooltip">Users</span>
              </li>
              <li>
              <Link to="/orders">
                <i className='bx bx-shopping-bag' ></i>
                <span className="links_name">Orders</span>
              </Link>
              <span className="tooltip">Orders</span>
              </li>
              <li>
              <Link to="/statistics">
                  <i className='bx bx-pie-chart-alt-2' ></i>
                  <span className="links_name">Statistics</span>
                </Link>
                <span className="tooltip">Statistics</span>
              </li>
            </div>
          }
          else {
            return <li>
                <Link to="/shopping-cart">
                  <i className='bx bx-shopping-bag' ></i>
                  <span className="links_name">Shopping Cart</span>
                </Link>
                <span className="tooltip">Shopping Cart</span>
              </li>
          }
        }
        )()}
          <li>
              <Link to="/user">
                <i className='bx bx-user' ></i>
                <span className="links_name">User</span>
              </Link>
              <span className="tooltip">User</span>
              </li>
       <li className="profile">
           <div className="profile-details">
             <div className="name_job">
              {(() => {
                if (token !== undefined) {
                  return <div>
                    <div className="name">{token.sub}</div>
                      <div className="job">{token.role}</div>
                    </div>
                }
              })()}
             </div>
           </div>
           {(() => {
                if (token !== undefined) { 
                  return <i onClick={logout}className='bx bx-log-out' id="log_out" ></i>
                }
                else { return <div>
                  <Link to="/login">
                    <i className='bx bx-log-in' id="log_in" ></i>
                    <span className="links_name">Login</span>
                  </Link>
                  <span className="tooltip">Login</span>
                </div>
                {/*<Link to="/login">
                    <i className='bx bx-log-in' id="log_in" ></i>
                </Link>*/}

              }
              })()}
       </li>
      </ul>
      </div>

      <Outlet />
      </>
    );
  }


export default Layout;