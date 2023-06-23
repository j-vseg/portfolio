import '../../components/datatable/datatable.css'
import '../bundle/bundle.css'
import axios from 'axios'
import { useState, useEffect } from 'react'
import {useNavigate, useParams} from 'react-router-dom';
import jwt_decode from "jwt-decode";
import DataTable from '../../components/datatable/DataTable';
import AddForm from '../../components/addform/AddForm'
const Bundle = () => {
    let {id} = useParams();
    let [newBundle, setBundle] = useState();
    if (newBundle === undefined) {newBundle = {}}
    //let [packages, setPackages] = useState([]);
    let listHeaders = ["ID", "Name", "Price"];
    let item = "package";

    useEffect(() => {
        axios.get("http://localhost:8080/bundles/" + id )
            .then (function (response) {
                setBundle(response.data);
                //setPackages(response.data.packages);
            })
            .catch(error => console.log("Error: " + error))
    }, [])

    const deletePackage= id => removePackage(id);
    const changePackage =  id => modifyPackage(newBundle.id, id)

    function removePackage(id) {
        axios.delete('http://localhost:8080/bundles/'+newBundle.id+'/packages/'+id)
        //.then(response => id = 'Delete successful')
        .catch(error => {
            console.error("Error: " + error);
        }); 
        document.location.reload();
    }

    const navigate = useNavigate();
    function modifyPackage(bundleId, id) {
        navigate('/bundles/'+bundleId+"/packages/"+id);
    }

    function savePackage() {
        axios.post('http://localhost:8080/bundles/'+newBundle.id+'/packages', {
            name: document.getElementById('name').value,
            description: document.getElementById('description').innerHTML,
            price: document.getElementById('price').value,
            materials: []
          })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
          document.location.reload(); 
    }

    function updateBundle() {
        axios.put('http://localhost:8080/bundles/'+newBundle.id, { 
            id: newBundle.id,
            name: document.getElementById('bundleName').value,
            description: document.getElementById('bundleDescription').value,
            price: document.getElementById('bundlePrice').value,
            quantity: document.getElementById('bundleQuantity').value,
            packages: []
        });
        document.location.reload();
    }

    return (
    <div className="container">
        <h1>{newBundle.name}</h1>
        <div className="row g-2">
            <div className="col-md-6 information">
                <div className='content'>
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon1">Name</span>
                        </div>
                        <input id="bundleName" type="text" className="form-control" placeholder={newBundle.name}></input>
                    </div>
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon1">Price</span>
                        </div>
                        <input id="bundlePrice" type="number" step="10" className="form-control" placeholder={newBundle.price}></input>
                    </div>
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon1">Quantity</span>
                        </div>
                        <input id="bundleQuantity" type="number" step="1" className="form-control" placeholder={newBundle.quantity}></input>
                    </div>
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">Description</span>
                        </div>
                    <textarea id="bundleDescription" className="form-control" rows="4" cols="50">{newBundle.description}</textarea>
                </div>
            </div>
            {(() => {
                if (localStorage.getItem('accessToken') !== null && jwt_decode(localStorage.getItem('accessToken')).role !== "Customer") {
                    return <button type="button" onClick={updateBundle} className="btn btn-success">Save changes</button>
                }          
            })()}
        </div>
            <div className="col-md-6">
                <div className='content'>
                    <h2>Packages</h2>
                    <DataTable 
                        list = {newBundle.packages} 
                        headers = {listHeaders}
                        shownEntities={5}
                        modifyMethod={changePackage}
                        deleteMethod={deletePackage}
                        name={"bundlepackage"}
                        />
                </div>
            </div>
        </div>
            {(() => {
                if (localStorage.getItem('accessToken') !== null && jwt_decode(localStorage.getItem('accessToken')).role !== "Customer") {
                    return <div className='row mt-2 justify-content-end'>
                        <div className='col-md-6'>
                            <AddForm 
                            item={item}
                            saveMethod={savePackage} 
                            />
                        </div>
                    </div>
                }          
            })()}

        </div>
        );
    }

    export default Bundle;