import './index.css';
import './header.css';
import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import hatLogo from '../../assets/img/hat-logo.png';
import imageMe from '../../assets/img/me.jpg';

const Index = () => {
    // Svg animation
    useEffect(() => {
        const svg = document.querySelector('svg.squiggle-index');
        const path = svg.querySelector('path');

        const scroll = () => {
            const distance = window.scrollY
            const totalDistance = svg.clientHeight - window.innerHeight

            const percentage = distance / totalDistance

            const pathLength = path.getTotalLength()

            //svg.style.height = document.body.clientHeight;
            path.style.strokeDasharray = `${pathLength}`
            path.style.strokeDashoffset = `${pathLength * (1 - percentage)}`
        }
        scroll()
        window.addEventListener('scroll', scroll)
    }, []);

    // Scroll animation 
    const observer = new IntersectionObserver((entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                entry.target.classList.add('show');
            }
            else {
                entry.target.classList.remove('show');
            }
        });
    });

    const revealElements = document.querySelectorAll('.reveal');
    revealElements.forEach((el) => observer.observe(el));

    // Get bundels
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

    function navigateBundel(id) {
        navigate('/les/' + id)
    }

    function navigateBundels() {
        navigate('/lessen')
    }

    return (
        <>
            <header>
                <div id="intro" className="bg-image">
                    <div className="mask text-white">
                        <div className="container img-box d-flex align-items-center justify-content-center text-center">
                            <div className="d-flex align-items-center justify-content-center">
                                <div className="circle2 d-flex justify-content-start"></div>
                                <div className='circle d-flex justify-content-center align-items-center'>
                                    <div>
                                        <img src={hatLogo} alt='Hat in logo'></img>
                                        <h1>Kooperdraat</h1>
                                        <p>Experience technology</p>
                                    </div>
                                </div>
                                <div className="circle2 circle3 d-flex justify-content-end"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <div className="yellow-line"></div>

            <div className="container content over-mij reveal">
                <div className="row g-4 d-flex justify-content-end">
                    <div className="col-md-6">
                        <img className="img-fluid" src={imageMe} alt="een foto van mij" />
                    </div>
                    <div className="col-lg-4 col-md-6 d-flex item-align-center">
                        <div className="text-box">
                            <h1>Over mij</h1>
                            <p>Ik ben een technisch creatieve duizendpoot die goed met leerlingen om kan gaan. Werken in een schoolomgeving geeft mij energie. Hier ben ik op mijn best. Met mijn spontane positieve instelling en mijn “drive” weet ik leerlingen te boeien en mee te nemen in mijn lessen. Mijn creativiteit helpt mij om gevarieerde lessen te geven en lesmaterialen te ontwikkelen. Techniek is voor mij naast een vak ook een hobby. Ik ben breed geïntresseerd en snel geboeid. Nieuwe (technische) ontwikkelingen zie ik als kansen om me verder te kunnen ontplooien.</p>
                        </div>
                    </div>
                </div>
            </div>

            <div className='container content projecten'>
                {lesBundels.filter((bundel, i) => {
                    return i < 3;
                })
                    .map((bundel, i) => {
                        return i % 2 === 0 ? (
                            <div className="row reveal d-flex justify-content-end mt-5 mb-5" key={bundel.id}>
                                <div className='bundel col-lg-8 col-md-10 col-12' id={"bundel" + bundel.id}>
                                    <div className='img-box'>
                                        <img
                                            src={bundel.thumbnail}
                                            className="img-fluid"
                                            alt="Les thumbnail"
                                        />
                                    </div>
                                    <div className='text-box'>
                                        <h1>{bundel.naam}</h1>
                                        <p>{bundel.korteBeschrijving}</p>
                                        <button className='mt-2' onClick={() => navigateBundel(bundel.id)}>Kijk verder <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="#ffffff" d="M438.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L338.8 224 32 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l306.7 0L233.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l160-160z" /></svg></button>
                                    </div>
                                </div>
                            </div>
                        ) : (
                            <div className="row reveal d-flex justify-content-start mt-5 mb-5" key={bundel.id}>
                                <div className='bundel col-lg-8 col-md-10 col-12' id={"bundel" + bundel.id}>
                                    <div className='img-box'>
                                        <img
                                            src={bundel.thumbnail}
                                            className="img-fluid"
                                            alt="Les thumbnail"
                                        />
                                    </div>
                                    <div className='text-box'>
                                        <h1>{bundel.naam}</h1>
                                        <p>{bundel.korteBeschrijving}</p>
                                        <button className='mt-2' onClick={() => navigateBundel(bundel.id)}>Kijk verder <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="#ffffff" d="M438.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L338.8 224 32 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l306.7 0L233.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l160-160z" /></svg></button>
                                    </div>
                                </div>
                            </div>
                        )
                    })}
                    
                <div className='view-more row mt-5'>
                    <div className='d-flex justify-content-center align-items-center'>
                        <p>Bekijk meer van mijn projecten</p>
                        <button onClick={() => navigateBundels()}>Kijk verder <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path fill="#ffffff" d="M438.6 278.6c12.5-12.5 12.5-32.8 0-45.3l-160-160c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L338.8 224 32 224c-17.7 0-32 14.3-32 32s14.3 32 32 32l306.7 0L233.4 393.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0l160-160z" /></svg></button>
                    </div>
                </div>
            </div>

            {/* <svg
                width="1000"
                height="5000"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 1000 5000"
                fill="none"
                className="squiggle"
            >
                <path
                    d="M954.733963,-1871.541242c-309.5,214-30.36278,347.291-168.723,530.238-95.181,125.853-415.292,151.608-464.585-13.782-49.293-165.389,111.1807-250.382542,202.51-190.659c91.33,59.724,142.952585,186.061374,83.389,438.74-61.177,259.523,243.509339,142.402963,222.909,396.453-44.222,545.36-719,63.01-413-119.49c360.283-214.875,558,774-678.5,709.5-92.336004,56.751928-71.811493,136.329493,51.403063,157.520282C183.058555,59.109524,38.773971,372.59526,144.223,631.238c59.571382,146.115196,415.292,151.608,464.585-13.782c49.293-165.389-111.181-250.383-202.51-190.659-91.33,59.724-142.951,186.061-83.389,438.74C384.086,1125.06,79.3992,1007.94,100,1261.99c44.222,545.36,719,63.01,413-119.49-360.283-214.875-558,774,678.5,709.5"
                    fill="none"
                    stroke="#004a7c"
                    strokeWidth="30"
                    strokeLinejoin="round"
                    strokeLinecap="round"
                />
            </svg> */}

            <svg
                width="1000"
                height="1800"
                viewBox="0 0 1000 1800"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
                className="squiggle-index"
            >
                <path
                    d="M-24.5,101C285,315,5.86278,448.291,144.223,631.238c95.181,125.853,285.945687,150.509927,290.858149-21.999548c3.75417-131.834074-73.63244-119.846327-112.172149-104.868914-87.994597,34.196714-57.052807,103.470076,0,361.167462C380.545154,1125.869262,79.3992,1007.94,100,1261.99c44.222,545.36,594.848672,320.109324,304.0381,114.266035C117.934141,1173.744209,-216.174894,1928.767359,1000,1860.879841"
                    fill="none"
                    stroke="#004a7c"
                    strokeWidth="30"
                    strokeLinejoin="round"
                    strokeLinecap="round"
                />
            </svg>
        </>
    );

}
export default Index;