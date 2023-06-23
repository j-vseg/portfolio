import '../chatbot/chatbot.css'
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import {useCookies} from 'react-cookie';
import { useEffect } from 'react';

function ChatBot(props) {
    //var cookies = props.cookies;
    const [cookies, setCookie, removeCookie] = useCookies(['token']);
    const baseUrl = "http://127.0.0.1:5000/bot?question=";

    // Todo: Get value from cookie and get account
    var account = {id: 1, firstName: "Janne", lastName: "van Seggelen", email: "janne@test.nl", password: "password"}; // Hardcoded for now
    var inputSendBtn = null;

    useEffect(() => {
        inputSendBtn = document.getElementById("data");
        if (inputSendBtn !== null) {
            inputSendBtn.addEventListener("keypress", function(event) {
            if (event.key === "Enter") {
                event.preventDefault();
                sendMessage();
            }
            });
        }
    });

    function openOrCloseChat() {
        console.log(document.getElementById('wrapper').style.display);
        if (document.getElementById('wrapper').style.display === "block") {
            /*var alert = window.confirm("Are you sure you want to delete the chat? All data will be lost.")
            if (alert) {
                //document.getElementById("chat-box").style.display = "block";
                //document.getElementById("form").style.display = "none";
                //document.getElementById("typing-field").style.display = "none";
                //document.getElementById("btn-close-chat").style.display = "none";
                window.location.reload();
            }*/
            document.getElementById('wrapper').style.display = 'none';
        }
        else {
            document.getElementById('wrapper').style.display = 'block';
        }
        /*else { document.getElementById('wrapper').style.display === "block"; }*/
    }
    function botSendMessage(response){
        var form = document.getElementById("form");

        // Setting up html
        let divUser = document.createElement('div')
        divUser.className = "bot-inbox inbox";
        
        let divHeader = document.createElement('div');
        divHeader.className = "msg-header";

        let p = document.createElement('p');
        p.innerText = response.data;

        divHeader.appendChild(p);
        divUser.appendChild(divHeader);
            
        //let msg = `<div className="user-inbox inbox"><div className="msg-header"><p> ` + value + ` </p></div></div>`;
        form.append(divUser);

        
    }

    function sendMessage() {
        var form = document.getElementById("form");
        let value = document.getElementById('data').value;
        axios.get(baseUrl + value)
        .then(response => {botSendMessage(response)})
        .catch(ex => {});

        // Setting up html
        let divUser = document.createElement('div')
        divUser.className = "user-inbox inbox";
        
        let divHeader = document.createElement('div');
        divHeader.className = "msg-header";

        let p = document.createElement('p');
        p.innerText = value;

        divHeader.appendChild(p);
        divUser.appendChild(divHeader);
            
        //let msg = `<div className="user-inbox inbox"><div className="msg-header"><p> ` + value + ` </p></div></div>`;
        if (value !== "") { form.append(divUser); }
        document.getElementById('data').value = '';
            
    };

    const navigate = useNavigate();
    function navigateToLogin() {
        navigate('/login')
    }

    function deleteChat() {
        var alert = window.confirm("With this action your chat will be deleted. Are you sure?")
        if (alert) {
            window.location.reload();
        }
    }

    return (
    <div>
        <div className="chatbot">
            <div className="chat-icon">
                <input onClick={openOrCloseChat} type="checkbox" id="click"></input>
                    <label htmlFor="click">
                    <i className="fab fa-facebook-messenger"></i>
                    <i className="fas fa-times"></i>
                    </label>
            </div>
            <div className="wrapper" id="wrapper">
                <div className="title">Live Chat <button id="btn-close-chat" onClick={deleteChat}><i className="fa-solid fa-circle-xmark"></i></button></div>
                
                {(() => {
                    if (props.userInfo.account == null) {
                        return <div id="chat-box" className="chat-box">
                            <div className="desc-text">
                                Please login because starting a chat.
                            </div>
                            <form>
                                <div className="field">
                                    <button type="button" onClick={navigateToLogin}>Login</button>
                                </div>
                            </form>
                        </div>
                    }
                })()}

                {(() => {
                    if(props.userInfo.account){
                        return <div>
                        <div  id="form" className="form">
                                <div className="bot-inbox inbox">
                                    <div className="icon">
                                        <i className="fas fa-user"></i>
                                    </div>
                                    <div className="msg-header">
                                        <p id="welcome-message">Hello {props.userInfo.account.firstName}, how can I help you?</p>
                                    </div>
                                </div>
                                <div className="bot-inbox inbox">
                                    <div className="msg-header rating">
                                        <p id="welcome-message">Was this helpful?
                                            <div className='rate'>
                                                <button type='button'><i className="fa-solid fa-star"></i></button>
                                                <button type='button'><i className="fa-solid fa-star"></i></button>
                                                <button type='button'><i className="fa-solid fa-star"></i></button>
                                                <button type='button'><i className="fa-solid fa-star"></i></button>
                                                <button type='button'><i className="fa-solid fa-star"></i></button>
                                            </div>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div id="typing-field" className="typing-field">
                                <div className="input-data">
                                    <input id="data" type="text" placeholder="Type something here.." required></input>
                                    <button type="submit" onClick={sendMessage} id="send-btn">Send</button>
                                </div>
                            </div> 
                        </div>
                    }
                })()}
            </div>       
        </div>
    </div>
      );
    }
    
    export default ChatBot;
    