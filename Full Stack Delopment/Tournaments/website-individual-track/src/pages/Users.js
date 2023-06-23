import '../components/datatable/datatable.css'
import axios from 'axios'
import { useState, useEffect } from 'react'
import DataTable from '../components/datatable/DataTable'
import AddForm from '../components/addform/AddForm'
const Users = () => {
    let [users, setUsers] = useState([]);
    let listHeaders = ["ID", "Username", "Role", "Phonenumber", "Email"];

    useEffect(() => {
        axios.get("http://localhost:8080/accounts"/*, {headers: {
            "Authorization": "bearer " + localStorage.getItem("accessToken")}})*/)
            .then (function (response) {
                setUsers(response.data.accounts);
            })
            .catch((error) =>
                console.log(error)) 
    }, [])

    const deleteUser = id => removeUser(id);
    const changeUser = username => modifyUser(username)

    function removeUser(id) {
        axios.delete('http://localhost:8080/accounts/'+id)
        //.then(response => id = 'Delete successful')
        .catch(error => {
            console.error(error);
        }); 
        document.location.reload();
    }
    let userId = undefined;
    function modifyUser(id) {
        if(id === null) {/*alert("Select an user using the modify button");*/}
        else {
            userId = id;
            if ( document.getElementById('modifyForm')) {
                document.getElementById('modifyForm').style.display = 'block';
                let user = users.filter(function (el) {
                    return el.id === id
                })
                document.getElementById('name').value = user[0].username;
                document.getElementById('roles').options[document.getElementById('roles').selectedIndex].text = user[0].role;
                document.getElementById('phone').value = user[0].phoneNumber;
                document.getElementById('email').value = user[0].email;

                document.getElementById('modifyForm').scrollIntoView();
            }
        }
    }

    function updateUser() {
        var select = document.getElementById('roles');
        //console.log(document.getElementById('email').value+select.options[select.selectedIndex].text+document.getElementById('phone').value);
        let user = users.filter(function (el) {
            return el.id === userId
        })
        axios.put('http://localhost:8080/accounts/'+userId, {
            id: userId,
            username: user[0].username,
            password: user[0].password,
            role: select.options[select.selectedIndex].text,
            email: document.getElementById('email').value,
            phoneNumber: document.getElementById('phone').value
          })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
    }

    return (
    <div>
        <div className="container">
            <div className='content'>
                <h1>Users</h1>
                <DataTable 
                    list = {users} 
                    headers = {listHeaders}
                    shownEntities={10}
                    modifyMethod={changeUser}
                    deleteMethod={deleteUser}
                    name="account"
                />
            </div>
        </div>

        <div className='container forms'>
                <div className='row justify-content-end g-2'>
                        <div className='col-md-6'>
                            <div className='content'>
                                <div className='card-header'>
                                    <h2 onClick={modifyUser(null)}>Modify form </h2>
                                    <form id='modifyForm'>
                                        <button type="submit" onClick={closeForm} className="btn"><i className="fa-solid fa-circle-xmark"></i></button>
                                        <div className='float-right d-inline'><button type="button" onClick={updateUser} className="btn btn-outline-success float-right">Save</button></div>
                                        <input id='name' type="text" placeholder="Username" step="10" readOnly></input>
                                        <select name="roles" id="roles">
                                            <option>Owner</option>
                                            <option>Teacher</option>
                                            <option>Customer</option>
                                        </select>
                                        <input id='phone' type="tel" placeholder="Phone number" step="10"></input>
                                        <input id='email' type="email" placeholder="Email" step="10"></input>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div className='col-md-6'>
                            <AddForm 
                                item={"user"}
                                saveMethod={saveUser}
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    function saveUser() {
        var select = document.getElementById('addRoles');
        axios.post('http://localhost:8080/accounts', {
            username: document.getElementById('addUsername').value,
            password: "password",
            role: select.options[select.selectedIndex].text,
            phoneNumber: document.getElementById('addPhone').value,
            email: document.getElementById('addEmail').value
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
            document.getElementById('modifyForm').style.display = 'none';
        }
    }

    export default Users;