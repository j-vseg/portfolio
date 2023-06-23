import { Outlet, Link, Navigate, useNavigate } from "react-router-dom";
import '../css/navbar.css'
import '../css/templates/footer.css'
import image from '../img/bas-world-logo.png'

function Layout(props) {
  const navigate = useNavigate();

  const logOutOverride = () => {
    props.doLogout();
    navigate("/login");
  };

  function navigateToSignUp() {navigate('/signup');}
    return (
    <>
    <nav>
        <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/faq">FAQ</Link></li>
        {(() => {
            if (props.userInfo.account != null) {
              return <li className="float-right"><button type="button" onClick={logOutOverride}>Logout</button></li>
            }
            else {
              return <li className="float-right"><Link to="/login">Login</Link></li>
            }
            })()}
        </ul>
    </nav>

    <Outlet />

    <footer className="text-center text-lg-start">
      <section className="d-flex justify-content-center justify-content-lg-between p-4 border-bottom social-media">
        <div className="me-5 d-none d-lg-block">
          <span>Get connected with us on social networks:</span>
        </div>

        <div>
          <a href="https://www.facebook.com/basworldplatform/" className="me-4">
            <i className="fab fa-facebook-f"></i>
          </a>
          <a href="https://www.instagram.com/basworldplatform/" className="me-4">
            <i className="fab fa-instagram"></i>
          </a>
          <a href="https://www.youtube.com/channel/UClawvATEUiiIsleoGQeIlWQ" className="me-4">
            <i class="fa-brands fa-youtube"></i>
          </a>
        </div>
      </section>

      <section className="information">
        <div className="container text-center text-md-start mt-5">
          <div className="row mt-3">
            <div className="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
              <h6 className="text-uppercase fw-bold mb-4">
                <i className="fas fa-gem me-3"></i>About us
              </h6>
              <p>
                BAS World has <span>revolutionized the global trade of commercial vehicles</span> and machinery, selling more than 10.000 vehicles every year.
              </p>
            </div>

            <div className="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4">
              <h6 className="text-uppercase fw-bold mb-4">
                Menu
              </h6>
              <p>
                <a href="#!" className="text-reset">About us</a>
              </p>
              <p>
                <a href="#!" className="text-reset">Bas World Trusted Trade</a>
              </p>
              <p>
                <a href="#!" className="text-reset">News</a>
              </p>
            </div>

            <div className="col-md-3 col-lg-2 col-xl-2 mx-auto mb-4 menu">
              <p>
                <a href="#!" className="text-reset">Sell your vehicle</a>
              </p>
              <p>
                <a href="#!" className="text-reset">Contact us</a>
              </p>
              <p>
                <a href="#!" className="text-reset">Careers</a>
              </p>
            </div>

            <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
              <img className="img-fluid" src={image} alt="Bas World Logo"></img>
              <label className="sign-up" onClick={navigateToSignUp}>Sign up to Bas World <i class="fa-solid fa-arrow-right"></i></label>
            </div>
          </div>
        </div>
      </section>

      <div className="text-center p-4">
        © 2022 Copyright
      </div>
    </footer>
    
  </>

    );
  }



export default Layout;