import axios from "axios";
import '../shopping-cart/shopping-cart.css'
import DataTable from "../../components/datatable/DataTable";
import { useEffect, useState } from "react";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import jwt_decode from "jwt-decode";
import {useNavigate} from 'react-router-dom';

function ShoppingCart() {
    const [order, setOrder] = useState({});
    const [bundles, setBundles] = useState([]);
    const headers = ["ID", "Product", "Quantity", "Price"];
    const [stompClient, setStompClient] = useState(null);
    const ENDPOINT = "http://localhost:8080/ws";
    var token = undefined;
    const navigate = useNavigate();

    if(localStorage.getItem('accessToken') !== null) {token = jwt_decode(localStorage.getItem('accessToken')); }
  
    //TODO, add a solution for disconnection
    function STOMPDisconnect() {
      stompClient.disconnect(function() {   
      })};

    function onMessageReceived(data) {
        const result=  JSON.parse(data.body);
        alert(result);
    };

    useEffect(() => {
        if(localStorage.getItem('accessToken') === null) { navigate('/login'); }
        // use SockJS as the websocket client
        const socket = SockJS(ENDPOINT);
        // Set stomp to use websockets
        const stompClient = Stomp.over(socket);
        // connect to the backend
        stompClient.connect({}, () => {
            // subscribe to the backend
            stompClient.subscribe('/topic/live-stock', (data) => {
            console.log(data);
            //onMessageReceived(data);
            //STOMPDisconnect();
            });
            // maintain the client for sending and receiving
            setStompClient(stompClient);
        }, function(message) {
            console.log(message);
        });


        if (localStorage.getItem('orderId') !== null) {
            axios.get("http://localhost:8080/orders/"+localStorage.getItem('orderId'))
                .then (function (response) {
                    setOrder(response.data);
                })
                .catch(error => console.log("Error: " + error)) 
        }
    }, [])

    

    function confirmOrder() {
        if (order !== undefined && token !== undefined) {
            axios.put("http://localhost:8080/orders/"+order.id, {
                id: order.id,
                ordered: Date.now(),
                accountId: token.accountId
            })
            .then (function (response) {
                setOrder(response.data);
            })
            .catch(error => console.log("Error: " + error)) 
        
            console.log(order);
            order.orderlines.map(orderline => {
                console.log(orderline);
                console.log(orderline.courseBundle.quantity);
                console.log(orderline.quantity);
                stompClient.send("/app/update-stock", {}, 
                    JSON.stringify({
                        'bundleId': orderline.courseBundle.id, 
                        'quantity': orderline.courseBundle.quantity-orderline.quantity
                    }));
            })
            //localStorage.removeItem('orderId'); 
            //document.location.reload();
        }
    }
 
    return (
        <div className="container">
            <h1>Shopping cart</h1>
            <div className="row">
                <div className="col-md-8">
                    <DataTable list={order.orderlines} headers={headers} name="shopping-cart" />
                </div>
                <div className="col-md-4">
                    <div className="order-overview">
                        <h2>Total: </h2>
                        {(() => {
                            if (localStorage.getItem('orderId') !== null && order.orderlines !== undefined) {
                                    order.orderlines.map(orderline => {
                                        return <div className="order-item">
                                            <h4>{orderline.courseBundle.name}</h4>
                                            <p>{orderline.courseBundle.price * orderline.quantity}</p>
                                            </div>
                                    })
                            }
                        })()}
                        {(() => {
                            if (localStorage.getItem('orderId') !== null) {
                                return <input type="submit" onClick={confirmOrder} value="Order"></input>
                            }
                        })()}
                    </div>
                </div>
            </div>
        </div>
    );
  }
  
  export default ShoppingCart;