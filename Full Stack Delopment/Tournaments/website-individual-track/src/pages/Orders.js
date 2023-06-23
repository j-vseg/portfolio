import '../components/datatable/datatable.css'
import axios from 'axios'
import { useState, useEffect } from 'react'
import {useNavigate} from 'react-router-dom';
import DataTable from '../components/datatable/DataTable'
import AddForm from '../components/addform/AddForm';
const Orders = () => {
    let [orders, setOrders] = useState([]);
    let listHeaders = ["ID", "Account", "Price", "Ordered at"];

    useEffect(() => {
        axios.get("http://localhost:8080/orders")
            .then (function (response) {
                setOrders(response.data.orders);
            })
            .catch(error => console.log("Error: " + error)) 
    }, [])

    const deleteOrder = id => removeOrder(id);
    const changeOrder = id => modifyOrder(id)

    function removeOrder(id) {
        axios.delete('http://localhost:8080/orders/'+id)
        //.then(response => id = 'Delete successful')
        .catch(error => {
            console.error("Error: " + error);
        }); 
    }

    const navigate = useNavigate();
    function modifyOrder(id) {
        navigate('/orders/'+id)
    }

    return (
        <div>
            <div className="container">
                <div className='content'>
                <h1>Orders</h1>
                <DataTable 
                    list = {orders} 
                    headers = {listHeaders}
                    shownEntities={10}
                    modifyMethod={changeOrder}
                    deleteMethod={deleteOrder}
                    name={"order"}
                />
                </div>
            </div>

            {/*<AddForm 
                item={"order"}
                saveMethod={saveOrder}
    />*/}

            </div>
        );
    }

    function saveOrder() {
        axios.post('http://localhost:8080/orders', {
            name: document.getElementById('name').value,
            description: document.getElementById('desc').innerHTML,
            price: document.getElementById('price').value,
            packages: null
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

    export default Orders;