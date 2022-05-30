import React, { useEffect, useState } from 'react';
import CheckImage from '../../image/check.png';
import style from '../../css/member/signup_result.module.css';
import { useLocation, useNavigate } from 'react-router-dom';
import qs from 'qs';
import axios from 'axios';

const SignUpResult = (props) => {
    const location = useLocation()
    const navigate = useNavigate();
    const [member, setMember] = useState({});
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchPortfolio = async () => {
            setLoading(true);
            try {
                await axios.get('/api/token' + location.search).then(response => {
                    if (response.data.resultCode === 0) {
                        setMember(response.data.data.member);
                    } else {
                        navigate('/expire');
                    }
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchPortfolio();
    }, [])

    if (loading) {
        return null;
    }

    if (!member) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <div className={style.complete_view_area}>
                        <div className={style.complete_img}>
                            <img src={CheckImage} alt="check_img"/>
                        </div>
                        <div className={style.descript}>
                            <h1>BOXFOLIO 가입을 환영합니다!</h1>
                            <div className={style.detail_descript}>
                                <span>회원가입이 완료되었습니다.<span className={style.member_name}>{member.realName}</span>님의 아이디는 <span className={style.member_id}>{member.loginId}</span>입니다.</span>
                            </div>
                        </div>
                        <div className={style.access_area}>
                            <button className={style.home_button} onClick={() => navigate('/')}>홈으로 가기</button>
                            <button className={style.login_button} onClick={() => navigate('/login')}>로그인</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default SignUpResult;