import React from 'react';
import MemberImage from '../../image/member.png';
import style from '../../css/common/profile_tap.module.css';
import { Link } from 'react-router-dom';

const ProfileTap = () => {
    return (
        <details className={style.member_area}>
            <summary><img src={MemberImage} alt="member-icon" className={style.profile_btn} /></summary>
            <div className={style.member_access}>
                <ul className={style.access_list}>
                    <li>
                        <p className={style.member_nickname}>SeoArc</p>
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
                        <Link to="/logout">로그아웃 <i className="fa-solid fa-arrow-right-from-bracket"></i></Link>
                    </li>
                </ul>
            </div>
        </details>
    );
};

export default ProfileTap;