import axios from 'axios';
import React, { useState, useEffect } from 'react';
import DataTable from '../components/datatable/DataTable';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import LineChart from '../components/charts/LineChart';

const Statistics = () => {
    let listHeaders = ["ID", "Name", "Quantity"];
    let [bundles, setBundles] = useState([]);
    let [orders, setOrders] = useState([]);
    const [stompClient, setStompClient] = useState(null);
    const ENDPOINT = "http://localhost:8080/ws";

  
    // display the received data
    function onMessageReceived(data)
    {
      const result=  JSON.parse(data.body);
      setBundles(result.bundles);
    };

    useEffect(() => {
      //.catch(error => console.log("Error: " + error)) 

        // use SockJS as the websocket client
        const socket = SockJS(ENDPOINT);
        // Set stomp to use websockets
        const stompClient = Stomp.over(socket);
        // connect to the backend
        stompClient.connect({}, () => {
            // subscribe to the backend
            stompClient.subscribe('/topic/live-stock', (data) => {
            console.log(data);
            onMessageReceived(data);
            });
        });
        setStompClient(stompClient);

        axios.get("http://localhost:8080/bundles")
        .then (function (response) {
            setBundles(response.data.courseBundles);
        })

        
        axios.get("http://localhost:8080/orders")
        .then (function (response) {
            setOrders(response.data.orders);
        })

        var select = document.getElementById('selectFilter');
        var date = new Date();
        if (select != null) {
            axios.get(`http://localhost:8080/orders/stats?${select.value}=${date.getDate()}-${date.getMonth()+1}-${date.getFullYear()}`)
                .then (function (response) {
                    console.log(response.data);
                    setOrders(response.data.orderData);
            })
        }
      }, [])

      useEffect(() => {
        var select = document.getElementById('selectFilter');
        var date = new Date();
        if (select != null) {
            select.addEventListener("change", function() {
                axios.get(`http://localhost:8080/orders/stats?${select.value}=${date.getDate()}-${date.getMonth()+1}-${date.getFullYear()}`)
                    .then (function (response) {
                        console.log(response.data);
                        setOrders(response.data.orderData);
                })
            });
        }
        })


    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <div className='content'>
                            <h1>Live stock</h1>
                            <DataTable 
                                list = {bundles} 
                                headers = {listHeaders}
                                shownEntities={10}
                                /*modifyMethod={changeBundle}
                                deleteMethod={deleteBundle}
                                addToOrder={addBundleToOrder}*/
                                name={"stock"}
                            />
                        </div>
                    </div>
                    <div className='col-md-8'>
                        <div className='content'>
                            <h1>Profit</h1>
                            <LineChart 
                                chartData={orders} 
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        );
    }

    export default Statistics;