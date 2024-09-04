import { useState, useEffect } from 'react'
import imageMe from '../assets/img/me.jpg';

const OverMij = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        fetch('https://www.linkedin.com/in/john-van-seggelen-1b197a243/recent-activity/all/')
            .then(response => response.json())
            .then(posts => {
                console.log(posts);
                setPosts(posts);
            })
            .catch(error => console.error(error));
    }, []);
    return (
        <>
            <div className="container content">
                <div className="row g-4">
                    <div className="col-lg-4 col-md-6">
                        <img className="img-fluid" src={imageMe} alt="een foto van mij" />
                    </div>
                    <div className="col-lg-8 col-md-6 d-flex item-align-center">
                        <div className="text-box">
                            <h1>Over mij</h1>
                            <p>Ik ben een technisch creatieve duizendpoot die goed met leerlingen om kan gaan. Werken in een schoolomgeving geeft mij energie. Hier ben ik op mijn best. Met mijn spontane positieve instelling en mijn “drive” weet ik leerlingen te boeien en mee te nemen in mijn lessen. Mijn creativiteit helpt mij om gevarieerde lessen te geven en lesmaterialen te ontwikkelen. Techniek is voor mij naast een vak ook een hobby. Ik ben breed geïntresseerd en snel geboeid. Nieuwe (technische) ontwikkelingen zie ik als kansen om me verder te kunnen ontplooien.</p>
                        </div>
                    </div>
                </div>
            </div>

            <div className='container content'>
                <h1>Blog</h1>
            </div>
        </>
    );

}
export default OverMij;