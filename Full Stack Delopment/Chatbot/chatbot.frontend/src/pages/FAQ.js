import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import '../css/faq.css'
import ChatBot from '../components/chatbot/ChatBot';

function FAQ(props) {

return (
    <div>
        <div className='faq'>
            <div className='container overflow-hidden'>
                <h1>FAQ</h1>
                <div className='row gx-5'>
                    <div className='col-md-6'>
                        <div className='category'>

                            <h2>New to BAS World</h2>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>Why can't I contact some of the external sellers?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question1-catory1" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question1-catory1">
                                    <div className="card card-body">
                                    An external seller might not be equipped for selling directly to customers. That’s why they choose to sell their vehicle completely online through BAS World. Don’t worry. All external sellers are checked on important quality requirements.
                                    </div>
                                </div>
                            </div>
                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>What is Safe Deal?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question2-catory1" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question2-catory1">
                                    <div className="card card-body">
                                    If you are buying from an international client, you want to avoid any risk of disappointment or financial loss. That’s why BAS World offers a Safe Deal solution that also includes a delivery guarantee. How does that work? That's simple: The buyer transfers his money to the BAS World escrow account and BAS World holds onto the money until the vehicle has been picked up by an authorized shipping company.
                                    </div>
                                </div>
                            </div>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>Are all BAS World vehicles checked thoroughly?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question3-catory1" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question3-catory1">
                                    <div className="card card-body">
                                    We check specifications, the registration documents and we use big data to verify whether a vehicle is listed correctly.
                                    </div>
                                </div>
                            </div>

                        </div>  
                    </div>
                    <div className='col-md-6'>
                        <div className='category'>

                            <h2>Using the platform</h2>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>What can I do when I have issues with my account?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question1-catory2" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question1-catory2">
                                    <div className="card card-body">
                                        Please contact our BAS World service desk. Our experts of the service desk can help you with all your questions regarding account issues. You can find our contact details here: Contact us
                                    </div>
                                </div>
                            </div>
                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>Will you send me an update if new vehicles become available that match my Saved Search criteria?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question2-catory2" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question2-catory2">
                                    <div className="card card-body">
                                    Yes, we will! We send BAS World Saved Search email update and show you newly available vehicles.
                                    </div>
                                </div>
                            </div>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>Why should I mark a vehicle as a favourite?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question3-catory2" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question3-catory2">
                                    <div className="card card-body">
                                    You can see all your favourite vehicles quickly and easily in your BAS World dashboard – and you don’t have to search for these again. You will also get updates about any price reductions and see when a vehicle is sold.
                                    </div>
                                </div>
                            </div>

                        </div>  
                    </div>

                    <div className='col-md-6'>
                        <div className='category'>

                            <h2>Vehicle condition</h2>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>The vehicle delivered does not match the specifications of the vehicle as listed on BAS World. What should I do now?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question1-catory3" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question1-catory3">
                                    <div className="card card-body">
                                    Get in touch with our service desk as soon as possible and provide proof of the mismatch. We’ll immediately contact the seller and start a procedure to resolve the issue.
                                    </div>
                                </div>
                            </div>
                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>Is it possible to add a maintenance service on the vehicle?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question2-catory3" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question2-catory3">
                                    <div className="card card-body">
                                    You can discuss what is possible with the seller. He or she might be able to deliver several extra services such as new tyres or oil service.
                                    </div>
                                </div>
                            </div>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>Can I trust the mileage on the vehicle and the advertisement?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question3-catory3" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question3-catory3">
                                    <div className="card card-body">
                                    BAS World does a check on the mileage to verify that the mileage mentioned in the specifications matches the actual mileage displayed on the vehicle dashboard. Please bear in mind that this is not proof that the mileage has not been changed at some point. You should always check for yourself if you can logically explain the mileage on a vehicle.
                                    </div>
                                </div>
                            </div>

                        </div>  
                    </div>
                    <div className='col-md-6'>
                        <div className='category'>

                            <h2>Documents</h2>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>What documents do I need to bring with me when I pick up my vehicle?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question1-catory4" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question1-catory4">
                                    <div className="card card-body">
                                    The selling party will ask you to bring a power of attorney with you. You will also be asked for your identification papers.
                                    </div>
                                </div>
                            </div>
                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>When can I expect to receive the corresponding documents?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question2-catory4" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question2-catory4">
                                    <div className="card card-body">
                                    After the vehicle is paid and delivery is confirmed, we will send the documents to you when using Safe Deal. If you're not making use of our Safe Deal service you'll have to organize this with the seller.
                                    </div>
                                </div>
                            </div>

                            <div className='question'>
                                <div className='card-header'>
                                    <p>
                                        <div className='question-text'>
                                            <a>How do I receive my vehicle documents?</a>
                                        </div>
                                        <i className="fa-solid fa-angle-down" data-bs-toggle="collapse" href="#question3-catory4" role="button" aria-expanded="false" aria-controls="collapseExample"></i>
                                    </p>
                                </div>
                                <div className="answer collapse" id="question3-catory4">
                                    <div className="card card-body">
                                    The documents will always be sent to you by DHL when using Safe Deal. You can find the track and trace in your BAS World account. If you do not use Safe Deal you have to organize this with the seller.
                                    </div>
                                </div>
                            </div>

                        </div>  
                    </div>
                </div>
            </div>
        </div>
        <ChatBot userInfo={props.userInfo} />
    </div>
);
}

export default FAQ;