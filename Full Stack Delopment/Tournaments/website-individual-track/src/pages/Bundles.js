import '../components/datatable/datatable.css'
import axios from 'axios'
import { useState, useEffect } from 'react'
import jwt_decode from "jwt-decode";
import {useNavigate} from 'react-router-dom';
import DataTable from '../components/datatable/DataTable'
import AddForm from '../components/addform/AddForm';
const Bundles = () => {
    let [bundles, setBundles] = useState([]);
    let [order, setOrder] = useState(null);
    let listHeaders = ["ID", "Name", "Price", "Quantity"];
    const navigate = useNavigate();

    useEffect(() => {
        var filterInput = document.getElementById('filter');
        var filterSelect = document.getElementById('selectFilter');
            if (filterInput !== null) {
                if (filterInput.value === "") {
                    axios.get("http://localhost:8080/bundles")
                        .then (function (response) {
                            setBundles(response.data.courseBundles);
                            console.log(response.data.courseBundles);
                        })
                        .catch(error => console.log("Error: " + error)) 
                }
                else {
                    filterInput.addEventListener("keypress", function(event) {
                        // If the user presses the "Enter" key on the keyboard
                        if (event.key === "Enter") {
                            event.preventDefault();
                            var url = "http://localhost:8080/bundles"
                            if (filterSelect.value !== null) { url += "?" + filterSelect.value + "=" + filterInput.value; }
                            axios.get(url)
                            .then (function (response) {
                                setBundles(response.data.courseBundles);
                            })
                            .catch(error => console.log("Error: " + error)) 
                        }
                    });
                }
            }
        })
        
        useEffect(() => {
            axios.get("http://localhost:8080/bundles")
                .then (function (response) {
                    setBundles(response.data.courseBundles);
                })
                .catch(error => console.log("Error: " + error)) 
                if (localStorage.getItem('orderId') === null) {
                    axios.get("http://localhost:8080/orders/"+localStorage.getItem('orderId'))
                    .then (function (response) {
                        setOrder(response.data);
                    })
                    .catch(error => console.log(error)) 
            }
        }, [])

    const deleteBundle = id => removeBundle(id);
    const changeBundle = id => modifyBundle(id);
    const addBundleToOrder = (id, olName) => addBundle(id, olName);

    function removeBundle(id) {
        axios.delete('http://localhost:8080/bundles/'+id)
        //.then(response => id = 'Delete successful')
        .catch(error => {
            console.error("Error: " + error);
        }); 
    }

    
    function addBundle(id, olName) {
        if (order === null) { navigate('/login'); }
        else {
            if (localStorage.getItem("orderId") === null) {
                axios.post('http://localhost:8080/orders', {
                    accountId: jwt_decode(localStorage.getItem('accessToken')).accountId,
                })
                .then( function (response) {
                    localStorage.setItem("orderId", response.data.orderId);
                })
                .catch(error => {
                    console.error("Error: " + error);
                }); 
            }
            else {
                    let exists = order.orderlines.filter(function (el) {
                        console.log(el);
                        if (el.courseBundle.id === id) return el;
                        return null;
                    })
                    console.log(exists); 
                    if (exists.length === 0) {
                        axios.post('http://localhost:8080/orders/'+localStorage.getItem("orderId")+'/orderlines', {
                            quantity: 1,
                            price: document.getElementById("price"+id).innerHTML,
                            courseBundle: id
                        })
                        .catch(error => {
                            console.error("Error: " + error);
                        }); 
                    }
                    else {                     
                        axios.put('http://localhost:8080/orders/'+localStorage.getItem("orderId")+'/orderlines/'+exists[0].id, {
                            quantity: exists[0].quantity +1,
                            price: document.getElementById("price"+id).innerHTML,
                            courseBundleId: id
                        })
                        .catch(error => {
                            console.error("Error: " + error);
                        }); 
                    }
                }
                document.location.reload();
        }
    }
    function modifyBundle(id) {
        navigate('/bundles/'+id)
    }

    return (
        <div>
            <div className="container">
                <div className='content'>
                <h1>Bundles</h1>
                <DataTable 
                    list = {bundles} 
                    headers = {listHeaders}
                    shownEntities={10}
                    modifyMethod={changeBundle}
                    deleteMethod={deleteBundle}
                    addToOrder={addBundleToOrder}
                    name={"coursebundle"}
                    search={true}
                />
                </div>
            {(() => {
                if (localStorage.getItem('accessToken') !== null && jwt_decode(localStorage.getItem('accessToken')).role !== "Customer") {
                    return  <div className='row mt-2 justify-content-end'>
                        <div className='col-md-6'>
                            <AddForm 
                            item={"bundle"}
                            saveMethod={saveBundle}
                            />
                        </div>
                    </div>
                }          
            })()}
            </div>
        </div>
        );
    }

    function saveBundle() {
        axios.post('http://localhost:8080/bundles', {
            name: document.getElementById('name').value,
            description: document.getElementById('description').innerHTML,
            price: document.getElementById('price').value,
            quantity: document.getElementById('quantity').value,
            packages: []
          })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
    }
    
    function openForm(){
        if (document.getElementById('addForm')) {
            document.getElementById('addForm').style.display = 'block';
        }
    }

    function closeForm(){
        if (document.getElementById('addButton') && document.getElementById('addForm')) {
            document.getElementById('addButton').style.display = 'block';
            document.getElementById('addForm').style.display = 'none';

        }
    }

    export default Bundles;