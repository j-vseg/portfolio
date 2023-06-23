import logo from "../../img/logo.png"

function Error(props) {
    if (props.code === undefined) {props.code = "Error code not found"; }
    if (props.message === undefined) {props.message = "Message not found"; }
    if (props.subText === undefined) {props.subText = ""; }

    return (
        <div className="error-page">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-4 justify-content-center">
                        {<h1>Oops..! {props.code + " " + props.message}</h1>} 
                        {<p>{props.subText}</p>}
                    </div>
                    <div className="col-md-4 d-flex justify-content-center">
                        <img className="img-fluid d-block" src={logo}></img>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default Error;