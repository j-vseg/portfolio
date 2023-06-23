import './datatable.css'
import axios from 'axios'
import { useState } from 'react'
import Pagination from './Pagination';
import jwt_decode from "jwt-decode";

function DataTable({ list, headers, shownEntities, modifyMethod, deleteMethod, addToOrder, name, search}) {
    if (list === undefined) {list = []; }
    if (headers === undefined) {headers = [];}
    if (shownEntities === undefined) {shownEntities = 5;}
    if (modifyMethod === undefined) {modifyMethod = null;}
    if (deleteMethod === undefined) {deleteMethod = null;}
    if (addToOrder === undefined) {addToOrder = null;}
    if (search === undefined) {search = false;}
    let newHeaders = headers;
    var token = undefined;
    if(localStorage.getItem('accessToken') !== null) {token = jwt_decode(localStorage.getItem('accessToken')); }

    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage] = useState(shownEntities);

    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = Array.from(list).slice(indexOfFirstItem, indexOfLastItem);

    const paginate = pageNumber => setCurrentPage(pageNumber);
    //console.log(newHeaders.length);

    var totalprice = 0;
    if (name === "order") {
        list.map(order => {
            order.orderlines.map(orderline => {
                totalprice += orderline.price;
            })
        })        
    }

    return ( 
        <div className="row">
            <div className="col">
                <div className='search-bar inline-block'>
                        {(() =>{
                            if (search) {
                                return <div>
                                    <div className="input-group mb-3">                 
                                    {/*<button className="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</button>*/}
                                        <input id='filter' type="text" className="form-control" name="snpid" placeholder="Filter.."/>
                                        <select id="selectFilter" className="selectpicker form-control" data-live-search="true">
                                            <option value="highestPrice">Highest price</option>
                                            <option value="lowestPrice">Lowest price</option>
                                            <option value="name">Name</option>
                                        </select> 
                                    </div>
                                    <p>Press enter to apply the filter</p>
                                </div>
                            }
                            else {
                                return <div className="input-group mb-3">
                                    <input type="text" className="form-control" placeholder="Search"></input>
                                    <div className="input-group-append">
                                        <button className="btn" type="button"><i className="fa-solid fa-magnifying-glass"></i></button>
                                    </div>
                                </div>
                            }
                        })()}           
                </div>
            <table id="example" className="table table-striped">
                <thead>
                    <tr>
                        {newHeaders.map(header =>
                            <td key={header}>{header}</td>
                        )}
                        {(() => {
                            if (name !== "stock") {return <td>Action</td>}
                        })}
                    </tr>
                </thead>
                <tbody>
                    {currentItems.map(item => {
                        if (token !== undefined && token.role !== "Customer") {
                            if (name === "coursebundle")  {
                            return <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.name}</td>
                                    <td>{item.price}</td>
                                    <td>{item.quantity}</td>
                                    <td className="action">
                                        <button type="button" onClick={() => deleteMethod(item.id)} className="btn btn-outline-danger">Remove</button>
                                        <button type="button" onClick={() => modifyMethod(item.id)} className="btn btn-outline-success">Modify</button>
                                    </td>
                                </tr>
                            }
                            else if (name === "bundlepackage")  {
                                return <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.name}</td>
                                        <td>{item.price}</td>
                                        <td className="action">
                                            <button type="button" onClick={() => deleteMethod(item.id)} className="btn btn-outline-danger">Remove</button>
                                            <button type="button" onClick={() => modifyMethod(item.id)} className="btn btn-outline-success">Modify</button>
                                        </td>
                                    </tr>
                                }
                            else if (name === "material") {
                                return <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.material}</td>
                                    <td className="action">
                                        <button type="button" onClick={() => deleteMethod(item.id)} className="btn btn-outline-danger">Remove</button>
                                        <button type="button" onClick={() => modifyMethod(item.id)} className="btn btn-outline-success">Modify</button>
                                    </td>
                                </tr>
                            } 
                            else if (name === "account") {
                                return <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.username}</td>
                                    <td>{item.role}</td>
                                    <td>{item.phoneNumber}</td>
                                    <td>{item.email}</td>
                                    <td className="action">
                                        <button type="button" onClick={() => deleteMethod(item.id)} className="btn btn-outline-danger">Remove</button>
                                        <button type="button" onClick={() => modifyMethod(item.id)} className="btn btn-outline-success">Modify</button>
                                    </td>
                                </tr>
                            }
                            else if (name === "order") {
                                return <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.account.username}</td>
                                    <td>{totalprice}</td>
                                    <td>{new Date(item.ordered).getMonth()}-{new Date(item.ordered).getDay()}-{new Date(item.ordered).getFullYear()}</td>
                                    <td className="action">
                                        <button type="button" onClick={() => deleteMethod(item.id)} className="btn btn-outline-danger">Remove</button>
                                        <button type="button" onClick={() => modifyMethod(item.username)} className="btn btn-outline-success">Modify</button>
                                    </td>
                                </tr>
                            }  
                            else if (name === "stock")  {
                                return <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.name}</td>
                                        <td>{item.quantity}</td>
                                    </tr>
                                }    
                        }   
                        else {
                            if (name === "coursebundle")  {
                                return <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.name}</td>
                                        <td id={"price"+item.id}>{item.price}</td>
                                        <td>{item.quantity}</td>
                                        <td className="action">
                                        <button type="button" onClick={() => addToOrder(item.id, item.name)} className="btn btn-primary">Add to cart</button>
                                        <button type="button" onClick={() => modifyMethod(item.id)} className="btn btn-success">View details</button>
                                        </td>
                                    </tr>
                                }
                                else if (name === "bundlepackage")  {
                                    return <tr key={item.id}>
                                            <td>{item.id}</td>
                                            <td>{item.name}</td>
                                            <td>{item.price}</td>
                                            <td className="action">
                                            <button type="button" onClick={() => modifyMethod(item.id)} className="btn btn-success">View details</button>
                                            </td>
                                        </tr>
                                    }
                                    else if (name === "stock")  {
                                        return <tr key={item.id}>
                                                <td>{item.id}</td>
                                                <td>{item.name}</td>
                                                <td>{item.quantity}</td>
                                            </tr>
                                    }
                                else if (name === "material") {
                                    return <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.material}</td>
                                        <td className="action">
                                        </td>
                                    </tr>
                                } 
                                else if (name === "account") {
                                    return <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.username}</td>
                                        <td>{item.role}</td>
                                        <td>{item.phoneNumber}</td>
                                        <td>{item.email}</td>
                                    </tr>
                                }
                                else if (name === "order") {
                                    return <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.account.username}</td>
                                        <td>{totalprice}</td>
                                        <td>{new Date(item.ordered).getMonth()}-{new Date(item.ordered).getDay()}-{new Date(item.ordered).getFullYear()}</td>
                                        <td className="action">
                                            <button type="button" onClick={() => deleteMethod(item.id)} className="btn btn-outline-danger">Remove</button>
                                            <button type="button" onClick={() => modifyMethod(item.username)} className="btn btn-outline-success">Modify</button>
                                        </td>
                                    </tr>
                                }
                                else if (name === 'shopping-cart')  {
                                    return <tr key={item.id}>
                                        <td>{item.id}</td>
                                        <td>{item.courseBundle.name}</td>
                                        <td>{item.quantity}</td>
                                        <td>{item.courseBundle.price}</td>
                                        <td className="action">
                                            <button type="button" onClick={() => deleteMethod(item.id)} className="btn btn-outline-danger">Remove</button>
                                            {/*<button type="button" onClick={() => modifyMethod(item.username)} className="btn btn-outline-success">Modify</button>*/}
                                        </td>
                                    </tr>
                                }
                        }              
                    })}
                </tbody>
                <tfoot>
                    <tr>
                        {newHeaders.map(header =>
                            <td key={header}>{header}</td>
                        )}
                        {(() => {
                            if (name !== "stock") {return <td>Action</td>}
                        })}
                    </tr>
                </tfoot>
            </table>
            <Pagination 
                    currentPage={currentPage}
                    itemsPerPage={shownEntities}
                    totalItems={list.length}
                    paginate={paginate}
                />
            </div>
        </div> 
        );
    }

    export default DataTable;