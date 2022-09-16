import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/common/header.module.css';
import Navigation from '../common/Navigation';
import ProfileTap from '../common/ProfileTap';

const Header = () => {
    const [isActive, setIsActive] = useState(false);
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

    if (loading) {
        return null;
    }
    
    return (
        <header className={isActive ? style.openmenu : ''}>
            <div className="wrap">
                <div className={style.header_container}>
                    <div className={style.logo_area}>
                        <Link className={style.logo} to="/">BOXFOLIO</Link>
                    </div>
                    <Navigation setIsActive={setIsActive} />
                    {!login && <div className={style.member_area}>
                        <Link to="/login" className={style.start_btn}>
                            시작하기
                        </Link>
                    </div>}
                    {login && <ProfileTap />}
                </div>
            </div>
        </header>
    );
};

export default Header;