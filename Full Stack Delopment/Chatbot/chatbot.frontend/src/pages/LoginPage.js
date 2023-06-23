import Login from "../components/Login";
import {useNavigate} from 'react-router-dom';

function LoginPage(props){
    const navigate = useNavigate();
    const [cookies, setCookie, removeCookie] = useCookies(['token']);
    const url = 'http://localhost:8080/users/?uname=';

    const doLoginOverride = (email, pass, rememberMe) => {
        props.doLogin(email, pass, rememberMe)
            .then(response => {
                navigate("/")
            })
            .catch(response => {});
    };
    /*
    useEffect(() => {
        if(cookies.token === '1'){
            navigate('/FAQ');
        }
    });
    */
    return <Login doLogin={doLoginOverride} />
}

export default LoginPage;