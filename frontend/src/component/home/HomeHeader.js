import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/home/home_header.module.css';
import Navigation from '../common/Navigation';
import ProfileTap from '../common/ProfileTap';
import AuthenticationService from '../security/AuthenticationService';

const HomeHeader = () => {
    const [member, setMember] = useState({});
    const [loading, setLoading] = useState(false);
    const [isActive, setIsActive] = useState(false);
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();


    return (
        <header className={isActive ? [style.openmenu, style.home_header].join(' ') : style.home_header}>
            <div className="wrap">
                <div className={style.header_container}>
                    <div className={style.logo_area}>
                        <Link className={style.logo} to="/">BOXFOLIO</Link>
                    </div>
                    <Navigation setIsActive={setIsActive} />
                    {!isUserLoggedIn && <div className={style.member_area}>
                        <Link to="/login" className={style.start_btn}>
                            시작하기
                        </Link>
                    </div>}
                    {isUserLoggedIn && <ProfileTap />}
                </div>
            </div>
        </header>
    );
};

export default HomeHeader;