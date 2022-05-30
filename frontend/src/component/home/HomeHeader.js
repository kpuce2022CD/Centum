import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/home/home_header.module.css';
import Navigation from '../common/Navigation';
import ProfileTap from '../common/ProfileTap';

const HomeHeader = () => {
    const [isActive, setIsActive] = useState(false);
    return (
        <header className={isActive ? style.openmenu : ''}>
            <div className="wrap">
                <div className={style.header_container}>
                    <div className={style.logo_area}>
                        <Link className={style.logo} to="/">BOXFOLIO</Link>
                    </div>
                    <Navigation setIsActive={setIsActive} />
                    {/* <div className="member-area" th:if="${session.isEmpty()}">
                        <Link to="/login" className="start-btn" th:to="@{/login}" th:text="#{button.start}">
                            시작하기
                        </Link>
                    </div> */}
                    <ProfileTap />
                </div>
            </div>
        </header>
    );
};

export default HomeHeader;