import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/member/login.module.css';

const Login = () => {
    return (
        <section className={style.main}>
            <div className={style.wrap}>
                <div className={style.main_container}>
                    <div className={style.login_wrap}>
                        <div className={style.login_header}>
                            <h2>로그인</h2>
                        </div>
                        <div className={[style.input_row, style.id_area].join(' ')}>
                            <div className={style.id_icon}>
                                <i className="fas fa-user"></i>
                            </div>
                            <input type="text" className={style.input_id} placeholder="아이디" title="ID" />
                        </div>
                        <div className={[style.input_row, style.pw_area].join(' ')}>
                            <div className={style.pw_icon}>
                                <i className="fas fa-unlock-alt"></i>
                            </div>
                            <input type="password" className={style.input_pw} placeholder="비밀번호" title="PASSWORD" />
                        </div>
                        <div className={style.auto_login_area}>
                            <label><input type="checkbox" /> 로그인 상태 유지</label>
                        </div>
                        <div className={style.error_msg_area} >
                            에러메세지
                        </div>
                        <div className={style.login_area}>
                            <input type="submit" className={style.login_btn} value="로그인" />
                        </div>
                        <ul className={style.find_area}>
                            <li>
                                <Link to="#">아이디 찾기</Link>
                            </li>
                            <li>
                                <Link to="#">비밀번호 찾기</Link>
                            </li>
                            <li>
                                <Link to="../member/signup.html">회원가입</Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default Login;