import '../addform/addForm.css'
const AddForm = ({item, saveMethod}) => {
if (item === undefined) {item = "";}

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

    return (        

                <div className='content'>
                    <div className='card-header'>
                        <h2 onClick={openForm}>Add form <i className="fa-solid fa-chevron-down float-right"></i></h2>
                        <form id='addForm'>
                            <button type="submit" onClick={closeForm} className="btn"><i className="fa-solid fa-circle-xmark"></i></button>
                                <div className='float-right d-inline'><button type="button" onClick={() => saveMethod()} className="btn btn-outline-success float-right">Save</button></div>
                                {(() =>  {
                                    if (item === "material") { 
                                        return <input id='material' type="text" placeholder="Material" step="10"></input>
                                    }
                                    else if (item === "bundle") {
                                        return <div>
                                            <input id='name' type="text" placeholder="Name" step="10"></input>
                                            <textarea id="description" cols="30" rows="10">Description</textarea>
                                            <input id='price' type="number" placeholder="Price" step="10"></input>
                                            <input id='quantity' type="number" placeholder="Quantity" step="1"></input>
                                        </div>
                                    }
                                    else if (item === "package") {
                                        return <div>
                                            <input id='name' type="text" placeholder="Name" step="10"></input>
                                            <textarea id="description" cols="30" rows="10">Description</textarea>
                                            <input id='price' type="number" placeholder="Price" step="10"></input>
                                        </div>
                                    }
                                    else {
                                    return <div>
                                        <input id='addUsername' type="text" placeholder="Username" step="10"></input>
                                        <select name="roles" id="addRoles">
                                            <option>Owner</option>
                                            <option>Teacher</option>
                                            <option>Customer</option>
                                        </select>
                                        <input id='addPhone' type="tel" placeholder="Phone number" step="10"></input>
                                        <input id="addEmail" type="email" placeholder="Email" step="10"></input>
                                    </div>
                                    }
                                })()}
                        </form>
                    </div>
                </div>
        );
    }

    export default AddForm;