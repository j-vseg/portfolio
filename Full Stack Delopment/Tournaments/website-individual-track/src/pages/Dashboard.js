import DataTable from "../components/datatable/DataTable";
import axios from 'axios'
import jwt_decode from "jwt-decode";
import { useState, useEffect } from 'react'
import {useNavigate} from 'react-router-dom';

const Dashboard = () => {
    let [bundles, setBundles] = useState([]);
    let listHeaders = ["ID", "Name", "Price"];

    useEffect(() => {
        axios.get("http://localhost:8080/bundles")
            .then (function (response) {
                setBundles(response.data.courseBundles);
            })
            .catch(error => console.log("Error: " + error)) 
    }, [])

    const deleteBundle = id => removeBundle(id);
    const changeBundle = id => modifyBundle(id);
    const addBundleToOrder = id => addBundle(id);
    const navigate = useNavigate();


    function removeBundle(id) {
      axios.delete('http://localhost:8080/bundles/'+id)
      //.then(response => id = 'Delete successful')
      .catch(error => {
          console.error("Error: " + error);
      }); 
  }

  
  function addBundle(id) {
      if (localStorage.getItem("accessToken") === null) { navigate('/login'); }
      else {
          if (localStorage.getItem("orderId") === null) {
              axios.post('http://localhost:8080/orders', {
                  account: jwt_decode(localStorage.getItem('accessToken')).accountId,
              })
              .then( function (response) {
                  localStorage.setItem("orderId", response.data.orderId);
              })
              .catch(error => {
                  console.error("Error: " + error);
              }); 
          }
          axios.post('http://localhost:8080/orders/'+localStorage.getItem("orderId")+'/orderlines', {
              quantity: 1,
              price: document.getElementById("price"+id).innerHTML,
              courseBundle: id
          })
          /*.then( function (response) {
              localStorage.setItem("orderId", response);
          })*/
          .catch(error => {
              console.error("Error: " + error);
          }); 
      }
  }
  function modifyBundle(id) {
      navigate('/bundles/'+id)
  }


    return (
        <div className="container">
        <h1>Dashboard</h1>
        <div className="row g-3">
            <div className="col-md-6">
                <div className="content">
                <h2>Bundles</h2>
                <DataTable 
                list = {bundles} 
                headers = {listHeaders}
                shownEntities={2}
                modifyMethod={changeBundle}
                deleteMethod={deleteBundle}
                addToOrder={addBundleToOrder}
                name="coursebundle"
                />
              </div>
            </div>
            <div className="col-md-6">
                <div className="content">
                    <h2>Live updates</h2>
                    <ul>
                      <li>You can now order bundles!</li>
                      <li>Our privicy policy has been updated.</li>
                    </ul>
                </div>
            </div>
        </div>
            {(() => {
              if (localStorage.getItem('accessToken') !== null && jwt_decode(localStorage.getItem('accessToken')).role !== "Customer") {
                return  <div className="row g-3 mt-2">         
                  <div className="col-md-4">
                      <div className="content">
                        <h2>Chart 1</h2>
                      </div>
                    </div>
                    <div className="col-md-4">
                      <div className="content">
                          <h2>Chart 2</h2>
                      </div>
                    </div>
                    <div className="col-md-4">
                      <div className="content">
                          <h2>Chart 3</h2>
                      </div>
                    </div>
                  </div>
              }
            })()}
    </div>
        );

    }
    export default Dashboard;