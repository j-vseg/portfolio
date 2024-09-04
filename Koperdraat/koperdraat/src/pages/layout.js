import '../css/navbar.css';
import '../css/footer.css';
import { Outlet, Link } from "react-router-dom";

const Layout = () => {
    return (
        <>
            <nav className="navbar navbar-expand-sm fixed-top navbar-scroll">
                <a className="navbar-brand" href="index.html"><img src="../img/logo2.png" alt="" /></a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"><img src="../icons/bars.svg" alt="Menu icon"></img></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link to="/"><span className="nav-link">Home</span></Link>
                        </li>
                        <li className="nav-item active">
                            <Link to="over-mij"><span className="nav-link">Over mij</span></Link>
                        </li>
                        <li className="nav-item">
                            <Link to="Lessen"><span className="nav-link">Lessen</span></Link>
                        </li>
                    </ul>
                </div>
            </nav>

            <Outlet />

            <footer className="text-center text-lg-start">
                <section className="d-flex justify-content-center justify-content-lg-between p-4 border-bottom">
                    <div className="me-5 d-none d-lg-block">
                        <span>Get connected with us on social networks:</span>
                    </div>

                    <div>
                        <a href="https://www.linkedin.com/in/john-van-seggelen-1b197a243/" className="me-4 social">
                            <i className="fab fa-linkedin"></i>
                        </a>
                    </div>
                </section>

                <section className="">
                    <div className="container text-center text-md-start mt-5">
                        <div className="row mt-3">
                            <div className="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4">
                                <h6 className="text-uppercase fw-bold mb-4">
                                    <i className="fas fa-gem me-3"></i>Kooperdraat
                                </h6>
                                <p>
                                    Kooperdraat is een eenman's bedrijf gedreven door een enthousiaste leraar. Zijn doel is om kinderen uit hun comfortzone te halen door te doen.
                                </p>
                            </div>

                            <div className="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4">
                                <h6 className="text-uppercase fw-bold mb-4">Contact</h6>
                                <p>
                                    <i className="fas fa-envelope me-3"></i>
                                    <a href="mailto:jawvanseggelen@gmail.com">jawvanseggelen@gmail.com</a>
                                </p>
                                <p><i className="fas fa-phone me-3"></i> <a href="tel:+316275789977">+31 6 275 789 977</a></p>
                            </div>
                        </div>
                    </div>
                </section>
            </footer>
        </>
    );
}


export default Layout;