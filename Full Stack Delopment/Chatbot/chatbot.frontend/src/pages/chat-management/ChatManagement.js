import './chat-management.css'
import image from '../../img/background-image.jpg'

function ChatManagement(props){

    function openChat(userId, chatId) {
        if (document.getElementById('name'+userId) !== null && document.getElementById('chat-title') !== null) {
            var name = document.getElementById('name'+userId).innerHTML;
            document.getElementById('chat-title').innerHTML = name;
            document.getElementById('open-chat').style.display = 'block';
            document.getElementById('typing-field').style.display = 'block';
            document.getElementById('closed-chat').style.display = 'none';
            let chats = document.getElementsByClassName('chat');
            for (var i = 0; i<chats.length; i++) {
                document.getElementsByClassName('chat')[i].style.display = 'none';
            }
            
            if (chatId === null){
                var chatBox = document.getElementsByClassName('chat-box')
                let div = document.createElement('div');
                div.className = 'chat';
                div.id = "chat"+2; // Hardcoded for now
                chatBox[0].appendChild(div);
                document.getElementById('chat'+2).style.display = 'block';
                div.scrollTop = div.scrollHeight;
            }
            else {
                var chat = document.getElementById('chat'+chatId);
                chat.style.display = 'block'
                chat.scrollTop = chat.scrollHeight;
            }
        }
    }
    function closeChat() {
        document.getElementById('open-chat').style.display = 'none';
        document.getElementById('typing-field').style.display = 'none';
        document.getElementById('closed-chat').style.display = 'block';
    }
    function sendMessage() {
        if (document.getElementById('text-field') !== null) {
            var message = document.getElementById('text-field').value;
            var chat = document.getElementById('chat');

            let span = document.createElement('span');
            span.innerText = message;
            span.className = "bot-box";
            chat.append(span);
        }
        message = "";
    }
    
    return (
       <div className='container-fluid'>
            <div className='row'>
                <div className='col-md-3'>
                    <div className='chat-overview'>
                        <div id='title' className='title'>
                            <h1>Active chats</h1>
                        </div>
                        <div id="search-bar" className='search-bar'>
                            <input type="text" placeholder="Search for a chat"></input>
                            <input type='button' value="Search"></input>
                        </div>
                        <div className='chats'>
                            <div id="[chatId]" onClick={() => openChat(1, 1)} className='chat-item'>
                                <div className='information'>
                                    <h2 id={"name1"/*name[userID]*/} >van Seggelen, Janne</h2>
                                    <p>[Last Message]</p>
                                </div>
                                <div className='notification'>
                                    <i className="fa-solid fa-circle"></i>
                                </div>
                            </div>
                            <div onClick={() => openChat(2, 2)} className='chat-item'>
                                <div className='information'>
                                    <h2 id="name2">Hendrikson, David</h2>
                                    <p>[Last Message]</p>
                                </div>
                                <div className='notification'>
                                    <i className="fa-solid fa-circle"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className='col-md-9'>
                    <div id='open-chat' className='open-chat'>
                        <div className='chat-title'>
                            <i onClick={closeChat} className="fa-solid fa-circle-xmark"></i>
                            <h2 id="chat-title">[Last Name], [Fist Name]</h2>
                        </div>
                        <div className='chat-box'>
                            <div className='imgBx'>
                                {/*<img src={image}></img>*/}
                            </div>
                            <div id="chat1" className='chat'>
                                <span className='bot-box'>Hello Janne, how can I help?</span>
                                <span className='user-box'>I want to view my orders.</span>
                                <span className='bot-box'><i className="fa-regular fa-comment-dots"></i> The bot is typing</span>
                            </div>
                            <div id="chat2" className='chat'>
                                <span className='bot-box'>Hello David, how can I help?</span>
                                <span className='user-box'>I have trouble ordering a truck.</span>
                                <span className='bot-box'><i className="fa-regular fa-comment-dots"></i> The bot is typing</span>
                            </div>
                        </div>
                    </div>
                    <div id='typing-field' className='typing-field'>
                        <input id="text-field" type="text" placeholder="Send a message"></input>
                        <input onClick={sendMessage} type='button' value="Send"></input>
                    </div>
                    <div id='closed-chat' className='closed-chat'>
                        <h1>Open a chat to start chatting.</h1>
                    </div>
                </div>
            </div>
       </div> 
    );

}

export default ChatManagement;