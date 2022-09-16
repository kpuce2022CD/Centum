import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import style from '../../css/home/home_banner.module.css';

const HomeBanner = () => {
    const navigate = useNavigate();
    const [login, setLogin] = useState(false);
    const [loading, setLoading] = useState(false);
    
    const fetchMemberData = () => {
        setLoading(true);
        try {
            axios.get('/api/members/my').then(response => {
                if (response.data.success === 0) {
                    setLogin(true);
                }
            }).catch(error => {
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    useEffect(() => {
        fetchMemberData();
    }, []);

    const buttonOnClick = () => {
        if (login) {
            navigate('/portfolios/create');
        } else {
            navigate('/login')
        }
    };

    if (loading) {
        return null;
    }

    return (
        <section className={style.banner}>
            <div className="wrap">
                <div className={style.banner_container}>
                    <h1>
                        BOXFOLIO
                    </h1>
                    <span >자신만의 포트폴리오를 만들고 분석해보세요!</span>
                    <button onClick={() => buttonOnClick()} className={style.analysis_btn}>
                        분석 받아보기
                    </button>
                </div>
            </div>
        </section>
    );
};

export default HomeBanner;