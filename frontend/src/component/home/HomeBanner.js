import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/home/home_banner.module.css';

const HomeBanner = () => {
    return (
        <section className={style.banner}>
            <div className="wrap">
                <div className={style.banner_container}>
                    <h1>
                        BOXFOLIO
                    </h1>
                    <span >자신만의 포트폴리오를 만들고 분석해보세요!</span>
                    <Link to="/login" className={style.analysis_btn}>
                        분석 받아보기
                    </Link>
                </div>
            </div>
        </section>
    );
};

export default HomeBanner;