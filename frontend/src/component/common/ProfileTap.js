import React, { useEffect, useState } from 'react';
import MemberImage from '../../image/member.png';
import style from '../../css/common/profile_tap.module.css';
import { Link, useNavigate } from 'react-router-dom';
import AuthenticationService from '../security/AuthenticationService';
import instance from '../security/Interceptor';

const ProfileTap = () => {
    const navigate = useNavigate();
    const [member, setMember] = useState({});
    const [loading, setLoading] = useState(false);
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();

    
    useEffect(() => {
        const fetchMemberData = () => {
            setLoading(true);
            instance.get('/api/member').then(response => {
                setMember(response.data.data.member);
            });
            setLoading(false);
        };
        if (isUserLoggedIn) {
            fetchMemberData();
        }
    }, []);
    
    const logout = () => {
        AuthenticationService.logout();
        navigate('/');
        window.location.reload();
    };

    if (loading) {
        return null;
    }

    return (
        <details className={style.member_area}>
            <summary><img src={MemberImage} alt="member-icon" className={style.profile_btn} /></summary>
            <div className={style.member_access}>
                <ul className={style.access_list}>
                    <li>
                        <p className={style.member_nickname}>{member.nickname}</p>
                    </li>
                    <li>
                        <Link to="/profile">프로필</Link>
                    </li>
                    <hr/>
                    <li>
                        <Link to="#">포트폴리오</Link>
                    </li>
                    <li>
                        <Link to="#">스크랩</Link>
                    </li>
                    <li>
                        <Link to="#">진행 프로젝트</Link>
                    </li>
                    <li>
                        <Link to="#">완료 프로젝트</Link>
                    </li>
                    <li>
                        <Link to="#">환경설정</Link>
                    </li>
                    <hr/>
                    <li>
                        <button onClick={() => logout()} className={style.logout_button}>로그아웃 <i className="fa-solid fa-arrow-right-from-bracket"></i></button>
                    </li>
                </ul>
            </div>
        </details>
    );
};

export default ProfileTap;