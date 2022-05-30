import React from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/member/token_expired.module.css';
import ExpiredImage from '../../image/expired.png';

const TokenExpired = (props) => {
    const navigate = useNavigate();

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <div className={style.auth_view_area}>
                        <div className={style.descript}>
                            <img src={ExpiredImage} alt="expired.png" className={style.email_icon} />
                            <h2>
                                인증 시간이 만료되었습니다.
                            </h2>
                            <p>재 인증 요청이 필요합니다.</p>
                        </div>
                        <div className={style.button_area}>
                            <button className={style.next_button} onClick={() => navigate('/')}>홈으로</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default TokenExpired;