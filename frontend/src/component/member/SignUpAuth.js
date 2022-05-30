import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import style from '../../css/member/signup_auth.module.css';
import EmailImage from '../../image/email.png';

const SignUpAuth = (props) => {
    const location = useLocation();
    const { email } = location.state;
    const navigate = useNavigate();

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <div className={style.auth_view_area}>
                        <div className={style.descript}>
                            <img src={EmailImage} alt="email_image" className={style.email_icon} />
                            <h2>
                                <span className={style.member_email}>{email}</span>로 링크가 전송되었습니다.
                            </h2>
                            <p>메일을 확인하여 가입을 완료해주세요</p>
                        </div>
                        <div className={style.button_area}>
                            <button className={style.prev_button} onClick={() => navigate(-1)}>뒤로</button>
                            <button className={style.next_button} onClick={() => navigate('/')}>나중에 인증</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default SignUpAuth;