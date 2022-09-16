import React, { useEffect, useState } from 'react';
import MemberImage from '../../image/member.png';
import style from '../../css/common/profile_tap.module.css';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const ProfileTap = () => {
    const navigate = useNavigate();
    const [portfolio, setPortfolio] = useState(null);
    const [member, setMember] = useState({});
    const [loading, setLoading] = useState(false);

    const fetchPortfolioData = () => {
        setLoading(true);
        try {
            axios.get('/api/members/my/portfolio').then(response => {
                if (response.data.success === 0) {
                    setPortfolio(response.data.data.portfolio);
                } else {
                    console.log(response.data);
                }
            }).catch(error => {
                console.log(error.response);
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    const fetchMemberData = () => {
        setLoading(true);
        try {
            axios.get('/api/members/my').then(response => {
                setMember(response.data.data.member);
            }).catch(error => {
                console.log(error.response);
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };
    
    useEffect(() => {
        fetchMemberData();
        fetchPortfolioData();
    }, []);
    
    const logout = () => {
        try {
            axios.get('/api/members/logout').then(response => {
                if (response.data.success === 0) {
                    console.log(response.data);
                }
            })
        } catch (e) {
            console.log(e);
        }
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
                        <button onClick={() => { if (portfolio) { navigate('/portfolios/' + portfolio.id) } else {alert('포트폴리오를 생성해주세요'); navigate('/portfolios/create');}}}>포트폴리오</button>
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