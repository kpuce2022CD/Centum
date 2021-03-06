import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import style from '../../css/member/login.module.css';
import AuthenticationService from '../security/AuthenticationService';

const Login = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [loginInfo, setLoginInfo] = useState({
        loginId: localStorage.getItem("authenticatedUser") || '',
        passwd: "",
        token: localStorage.getItem("token") || '',
        hasLoginFailed: false,
        notAuthenticatedUser: false
    })

    const inputOnChange = (e) => {
        const nextLoginInfo = {
            ...loginInfo,
            [e.target.name]: e.target.value
        }
        setLoginInfo(nextLoginInfo);
    }

    const login = () => {
        setLoading(true);
        AuthenticationService
            .executeJwtAuthentication(loginInfo.loginId, loginInfo.passwd).then(response => {
                if (response.data.data.loginResult.emailVerified === true) {
                    const nextLoginInfo = {
                        ...loginInfo,
                        token: response.data.data.loginResult.token
                    };
                    console.log(response.data.data.loginResult);
                    setLoginInfo(nextLoginInfo);
                    AuthenticationService.registerSuccessfulLoginForJwt(loginInfo.loginId, response.data.data.loginResult.token);
                    location.search === "" ? navigate('/') : navigate(location.search.split('=')[1])
                } else {
                    const nextLoginInfo = {
                        ...loginInfo,
                        hasLoginFailed: true,
                        notAuthenticatedUser: true
                    };
                    setLoginInfo(nextLoginInfo);
                }
            }).catch(() => {
                const nextLoginInfo = {
                    ...loginInfo,
                    hasLoginFailed: true,
                    notAuthenticatedUser: false
                };
                setLoginInfo(nextLoginInfo);
            })
        setLoading(false);
    }

    useEffect(() => {
        console.log(loginInfo);
    }, [loginInfo])

    if (loading) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className={style.wrap}>
                <div className={style.main_container}>
                    <div className={style.login_header}>
                        <h2>?????????</h2>
                    </div>
                    <div className={style.login_wrap}>
                        <div className={[style.input_row, style.id_area].join(' ')}>
                            <div className={style.id_icon}>
                                <i className="fas fa-user"></i>
                            </div>
                            <input type="text" className={style.input_id} name="loginId" placeholder="?????????" title="ID" onChange={inputOnChange}/>
                        </div>
                        <div className={[style.input_row, style.pw_area].join(' ')}>
                            <div className={style.pw_icon}>
                                <i className="fas fa-unlock-alt"></i>
                            </div>
                            <input type="password" className={style.input_pw} name="passwd" placeholder="????????????" title="PASSWORD" onChange={inputOnChange} onKeyDown={e => e.key === 'Enter' ? login() : null}/>
                        </div>
                        <div className={style.auto_login_area}>
                            <label><input type="checkbox" /> ????????? ?????? ??????</label>
                        </div>
                        {loginInfo.hasLoginFailed && !loginInfo.notAuthenticatedUser && <div className={style.error_msg_area} >
                            ????????? ?????? ??????????????? ?????? ????????????.
                        </div>}
                        {loginInfo.hasLoginFailed && loginInfo.notAuthenticatedUser && <div className={style.error_msg_area} >
                            ?????? ?????? ?????? ???????????????.
                        </div>}
                        <div className={style.login_area}>
                            <button className={style.login_btn} onClick={() => login()}>?????????</button>
                        </div>
                        <ul className={style.find_area}>
                            <li>
                                <Link to="#">????????? ??????</Link>
                            </li>
                            <li>
                                <Link to="#">???????????? ??????</Link>
                            </li>
                            <li>
                                <Link to="/signup/terms">????????????</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default Login;