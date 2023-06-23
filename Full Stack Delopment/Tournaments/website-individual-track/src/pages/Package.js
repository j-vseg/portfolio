import '../components/datatable/datatable.css'
import axios from 'axios'
import { useState, useEffect } from 'react'
import {useNavigate, useParams} from 'react-router-dom';
import jwt_decode from "jwt-decode";
import DataTable from '../components/datatable/DataTable'
import AddForm from '../components/addform/AddForm';

const Package = () => {
    let [bundlePackage, setPackage] = useState({});
    let [materials, setMaterials] = useState([]);
    let listHeaders = ["ID", "Material"];
    let {bundleId, id}= useParams();

    useEffect(() => {
        axios.get("http://localhost:8080/bundles/"+bundleId+"/packages/"+id)
            .then (function (response) {
                setPackage(response.data);
                setMaterials(response.data.materials);
            })
            .catch(error => console.log("Error: " + error)) 
    }, [])

    const deleteMaterial = id => removeMaterial(id);
    const changeMaterial = (bundleId, id) => modifyMaterial(bundleId, id)

    function removeMaterial(id) {
        axios.delete("http://localhost:8080/bundles/"+bundleId+"/packages/"+bundlePackage.id+'/materials/'+id)
        //.then(response => id = 'Delete successful')
        .catch(error => {
            console.error(error);
        }); 
    }

    const navigate = useNavigate();
    var materialId = undefined;
    function modifyMaterial(id) {
        if(id === null) {/*alert("Select an user using the modify button");*/}
        else {
            materialId = id;
            if ( document.getElementById('modifyForm')) {
                document.getElementById('modifyForm').style.display = 'block';
                let material = materials.filter(function (el) {
                    return el.id === id
                })
                document.getElementById('materialName').value = material[0].material;
    
                document.getElementById('modifyForm').scrollIntoView();
            }
        }
    }
    
    function saveMaterial() {
        axios.post('http://localhost:8080/bundles/'+bundleId+'/packages/'+bundlePackage.id+'/materials', {
            material: document.getElementById('material').value
          })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          }); 
    }

    function updatePackage() {
        axios.put('http://localhost:8080/bundles/'+bundleId+'/packages/'+bundlePackage.id, { 
            id: bundlePackage.id,
            name: document.getElementById('packageName').value,
            description: document.getElementById('packageDescription').value,
            price: document.getElementById('packagePrice').value,
            materials: []
        })
        .catch(error => {
            console.error(error);
        });
    }
    
    function updateMaterial() {
        console.log(document.getElementById('materialName').value);
        axios.put('http://localhost:8080/bundles/'+bundleId+'/packages/'+bundlePackage.id+'/materials/'+materialId, { 
            id: materialId,
            material: document.getElementById('materialName').value
        })
        .catch(error => {
            console.error(error);
        });
    }

    return (
        <div className="container">
        <h1>{bundlePackage.name}</h1>
    <div className="row">
                    <div className="col-md-6 information">
                <div className='content'>
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon1">Name</span>
                        </div>
                        <input id="packageName" type="text" className="form-control" placeholder={bundlePackage.name}></input>
                    </div>
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span className="input-group-text" id="basic-addon1">Price</span>
                        </div>
                        <input id="packagePrice" type="number" step="10" className="form-control" placeholder={bundlePackage.price}></input>
                    </div>
                    <div className="input-group">
                        <div className="input-group-prepend">
                            <span className="input-group-text">Description</span>
                        </div>
                    <textarea id="packageDescription" className="form-control" rows="4" cols="50">{bundlePackage.description}</textarea>
                </div>
            </div>
            {(() => {
                if (localStorage.getItem('accessToken') !== null && jwt_decode(localStorage.getItem('accessToken')).role !== "Customer") {
                    return <button type="button" onClick={updatePackage} className="btn btn-success">Save changes</button>
                }          
            })()}
        </div>
        <div className="col-md-6">
            <div className='content'>
                <h2>Materials</h2>
                <DataTable 
                    list = {materials} 
                    headers = {listHeaders}
                    shownEntities={6}
                    modifyMethod={changeMaterial}
                    deleteMethod={deleteMaterial}
                    name="material"
                    />
            </div>
        </div>
        </div>
        {(() => {
                if (localStorage.getItem('accessToken') !== null && jwt_decode(localStorage.getItem('accessToken')).role !== "Customer") {
                    return <div className='row mt-2 justify-content-end'>
                        <div className='col-md-6'>
                            <div className='content'>
                                <div className='card-header'>
                                    <h2 onClick={modifyMaterial(null, null)}>Modify form </h2>
                                    <form id='modifyForm'>
                                        <button type="submit" onClick={closeForm} className="btn"><i className="fa-solid fa-circle-xmark"></i></button>
                                        <div className='float-right d-inline'><button type="button" onClick={updateMaterial} className="btn btn-outline-success float-right">Save</button></div>
                                        <input id='materialName' type="text" placeholder="Material" step="50"></input>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div className='col-md-6'>
                            <AddForm 
                                item={"material"}
                                saveMethod={saveMaterial}
                            />
                        </div>
                </div>          
                }          
            })()}

        </div>
        );
    }
    
    /*function openForm(){
        if (document.getElementById('addForm')) {
            document.getElementById('addForm').style.display = 'block';
        }
    }*/

    function closeForm(){
        if (document.getElementById('addButton') && document.getElementById('addForm')) {
            document.getElementById('addButton').style.display = 'block';
            document.getElementById('addForm').style.display = 'none';

        }
    }

    export default Package;