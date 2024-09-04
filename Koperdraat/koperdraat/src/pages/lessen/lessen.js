import './lessen.css'
import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';

const Lessen = () => {
    const [lesBundels, setLesBundels] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('data/lesBundels.json')
            .then(response => response.json())
            .then(lesBundels => {
                setLesBundels(lesBundels);
            })
            .catch(error => console.error(error));
    }, []);

    function navigateLes(id) {
        navigate('/les/' + id)
    }


    return (
        <div className="container content">
            <div className="row g-5 d-flex justify-content-center">
                <h1>Les Bundels</h1>

                {lesBundels.map((bundel, i) => {
                    return (
                        <div className='col-lg-4 col-md-6 col-12' id={"bundel" + bundel.id}>
                            <div className='bundel'>
                                <div className='img-box mb-3'>
                                    <img
                                        src={bundel.thumbnail}
                                        className="img-fluid"
                                        alt="Les thumbnail"
                                    />
                                </div>
                                <div className='text-box'>
                                    <h1>{bundel.naam}</h1>
                                    <p>{bundel.korteBeschrijving}</p>
                                    <button className='mt-2' onClick={() => navigateLes(bundel.id)}>Kijk verder <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="#ffffff" d="M438.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L338.8 224 32 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l306.7 0L233.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l160-160z" /></svg></button>
                                </div>
                            </div>
                        </div>

                    )
                })}
            </div>
        </div>
    );

}
export default Lessen;