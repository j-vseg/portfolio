import './les.css';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { gsap } from "gsap";
import { ScrollTrigger } from "gsap/ScrollTrigger";

gsap.registerPlugin(ScrollTrigger);

const Les = () => {
    const { id } = useParams();
    const [lesBundel, setLesBundel] = useState({});

    // Get the bundel
    useEffect(() => {
        fetch('../data/lesBundels.json')
            .then(response => response.json())
            .then(lesBundels => {
                const bundel = lesBundels.find(bundel => bundel.id == id);
                if (bundel) {
                    setLesBundel(bundel);
                }
            })
            .catch(error => console.error(error));
    }, [id]);

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

    return (
        <>
            <div className="container content les">
                <div className="row">
                    <div className="col-md-6 mb-4 d-flex justify-content-center align-items-center les">
                        <img
                            src={lesBundel.thumbnail}
                            className="img-fluid"
                            alt="Bundel thumbnail"
                        />
                    </div>
                    <div className="col-md-6">
                        <h1>{lesBundel.naam}</h1>
                        <p>{lesBundel.beschrijving}</p>
                    </div>
                </div>
                <div className="row mb-5">
                    <div className='col-12'>
                        <div className="accordion mt-3" id={"accordionLes" + lesBundel.id}>
                            <div className="accordion-item">
                                <h2 className="accordion-header" id="accordionBundelheadingOne">
                                    <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#accordionBundelcollapseOne" aria-expanded="true" aria-controls="accordionBundelcollapseOne">
                                        Leerdoelen
                                    </button>
                                </h2>
                                <div id="accordionBundelcollapseOne" className="accordion-collapse collapse" aria-labelledby="#accordionBundelheadingOne" data-bs-parent={"accordionLes" + lesBundel.id}>
                                    <div className="accordion-body">
                                        {lesBundel.leerDoelen && lesBundel.leerDoelen.map((leerdoel, i) => (
                                            <li>{leerdoel}</li>
                                        ))}
                                    </div>
                                </div>
                            </div>
                            {/* <h2>Leerdoelen</h2>
                            <ul>
                                {lesBundel.leerDoelen && lesBundel.leerDoelen.map((leerdoel, i) => (
                                    <li>{leerdoel}</li>
                                ))}
                            </ul> */}
                        </div>
                    </div>
                </div>
                {lesBundel.lessen && lesBundel.lessen.map((les, i) => {
                    return i % 2 === 0 ? (
                        <div className="row reveal d-flex justify-content-start mt-5 mb-5" key={les.id}>
                            <div className='bundel col-lg-8 col-md-10 col-12' id={"bundel" + les.id}>
                                <div className='d-flex justify-content-space-evenly'>
                                    <div className='img-box'>
                                        <img
                                            src={les.thumbnail}
                                            className="img-fluid"
                                            alt="Les thumbnail"
                                        />
                                    </div>

                                    <div className='text-box'>
                                        <h1>{les.naam}</h1>
                                        <p>{les.beschrijving}</p>
                                    </div>
                                </div>

                                <div className="accordion mt-3" id={"accordionLes" + les.id}>
                                    <div className="accordion-item">
                                        <h2 className="accordion-header" id={"accordionLes" + les.id + "headingOne"}>
                                            <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target={"#accordionLes" + les.id + "collapseOne"} aria-expanded="true" aria-controls={"accordionLes" + les.id + "collapseOne"}>
                                                Leerdoelen
                                            </button>
                                        </h2>
                                        <div id={"accordionLes" + les.id + "collapseOne"} className="accordion-collapse collapse" aria-labelledby={"#accordionLes" + les.id + "headingOne"} data-bs-parent={"#accordionLes" + les.id}>
                                            <div className="accordion-body">
                                                {les.leerDoelen && les.leerDoelen.map((leerdoel, i) => (
                                                    <li>{leerdoel}</li>
                                                ))}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ) : (
                        <div className="row reveal d-flex justify-content-end mt-5 mb-5" key={les.id}>
                            <div className='bundel col-lg-8 col-md-10 col-12' id={"bundel" + les.id}>
                                <div className='d-flex justify-content-space-evenly'>
                                    <div className='img-box'>
                                        <img
                                            src={les.thumbnail}
                                            className="img-fluid"
                                            alt="Les thumbnail"
                                        />
                                    </div>

                                    <div className='text-box'>
                                        <h1>{les.naam}</h1>
                                        <p>{les.beschrijving}</p>
                                    </div>
                                </div>

                                <div className="accordion mt-3" id={"accordionLes" + les.id}>
                                    <div className="accordion-item">
                                        <h2 className="accordion-header" id={"accordionLes" + les.id + "headingOne"}>
                                            <button className="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target={"#accordionLes" + les.id + "collapseOne"} aria-expanded="true" aria-controls={"accordionLes" + les.id + "collapseOne"}>
                                                Leerdoelen
                                            </button>
                                        </h2>
                                        <div id={"accordionLes" + les.id + "collapseOne"} className="accordion-collapse collapse" aria-labelledby={"#accordionLes" + les.id + "headingOne"} data-bs-parent={"#accordionLes" + les.id}>
                                            <div className="accordion-body">
                                                {les.leerDoelen && les.leerDoelen.map((leerdoel, i) => (
                                                    <li>{leerdoel}</li>
                                                ))}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            {/* <div className='bundel col-lg-8 col-md-10 col-12' id={"bundel" + les.id}>
                                <div className='img-box'>
                                    <img
                                        src={les.thumbnail}
                                        className="img-fluid"
                                        alt="Les thumbnail"
                                    />
                                </div>
                                <div className='text-box'>
                                    <h1>{les.naam}</h1>
                                    <p>{les.beschrijving}</p>
                                </div>
                            </div> */}
                        </div>
                    )
                })}
            </div>
        </>
    );
};

export default Les;
