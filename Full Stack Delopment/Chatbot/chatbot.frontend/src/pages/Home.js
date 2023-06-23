import "../css/home.css"
function Home() {


    return (
      <div className="home">
        <div className="search-vehicle">
          <div className="container">
            <div className="row">
              <h1>Buy your vehicle online. <span>Easy and safe</span></h1>
              <div className="col-md-4">
                <input type="text" placeholder="Search a vehicle..."></input>
              </div>
              <div className="col-md-4">
                <input type="text" placeholder="Brand"></input>
              </div>
              <div className="col-md-4">
                <input type="text" placeholder="Model"></input>
              </div>
              <div className="col-md-4">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <div className="input-group-text">Year</div>
                  </div>
                  <input type="text" className="form-control" placeholder="Year"></input>
                </div>
              </div>
              <div className="col-md-4">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <div className="input-group-text">Budget</div>
                  </div>
                  <input type="text" className="form-control" placeholder="Euro"></input>
                </div>
              </div>
              <div className="col-md-4">
                <input type="button" className="btn btn-primary show-results" value="Show results"></input>
              </div>
            </div>
          </div>
        </div>

        <div className="container">
          <div className="search-category">
            <div className="row">
              <h1>Search by vehicle category</h1>
              <div className="col-md-2">
                <div className="category">
                  <i className="fa-solid fa-tractor"></i>
                  <div className="icon-text">
                    <label>Tractors</label>
                    <label>(438)</label>
                  </div>
                </div>
              </div>
              <div className="col-md-2">
                <div className="category">
                  <i className="fa-solid fa-truck"></i>
                    <div className="icon-text">
                    <label>Trucks</label>
                    <label>(257)</label>
                  </div>
                </div>
              </div>
              <div className="col-md-2">
                <div className="category">
                  <i className="fa-solid fa-trailer"></i>
                  <div className="icon-text">
                    <label>Trailers</label>
                    <label>(257)</label>
                  </div>
                </div>
              </div>
              <div className="col-md-2">
                <div className="category">
                  <i className="fa-solid fa-car"></i>
                  <div className="icon-text">
                    <label>Cars</label>
                    <label>(531)</label>
                  </div>
                </div>
              </div>
              <div className="col-md-2">
                <div className="category">
                  <i className="fa-solid fa-truck-pickup"></i>
                  <div className="icon-text">
                    <label>Pick-up trucks</label>
                    <label>(267)</label>
                  </div>
                </div>
              </div>
              <div className="col-md-2">
                <div className="category">
                  <i className="fa-solid fa-caravan"></i>
                  <div className="icon-text">
                    <label>Caravans</label>
                    <label>(24)</label>
                  </div>
                </div>
              </div>
            </div>
            <div className="ad">
              <div className="row">
                <div className="col-md-9">
                  <h1>Close yout next deal. Safe and secure.</h1>
                </div>
                <div className="col-md-3">
                  <input type="button" className="btn btn-primary" value="See our stock"></input>
                  <input type="button" className="btn btn-primary" value="See our selling solutions"></input>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="container">
          <div className="advantages">
            <div className="row">
              <div className="col-md-4">
                <div className="icon">
                  <i className="fa-regular fa-face-smile"></i>
                </div>
                <div className="icon-text">
                  <span>Hassle-free vehicle trading at its best</span>
                </div>
              </div>
              <div className="col-md-4">
                <div className="icon">
                  <i className="fa-solid fa-clipboard-list"></i>
                </div>
                <div className="icon-text">
                  <span>A virtually infinite stock. Each vehicle is carefully inspected</span>
                </div>
              </div>
              <div className="col-md-4">
                <div className="icon">
                  <i className="fa-solid fa-shield-halved"></i>
                </div>
                <div className="icon-text">
                <span>100% safe transaction and worldwide transport</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      );
    }
    
    export default Home;