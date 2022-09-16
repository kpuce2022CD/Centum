import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/common/navigation.module.css';

const Navigation = (props) => {
    const { setIsActive } = props;
    const [isNavActive, setIsNavActive] = useState(false);

    const navHover = () => {
        setIsActive(true);
        setIsNavActive(true);
    };

    const navLeave = () => {
        setIsActive(false);
        setIsNavActive(false);
    };

    return (
        <ul className={isNavActive ? [style.nav, style.openmenu].join(' ') : style.nav} onMouseEnter={navHover} onMouseLeave={navLeave}>
            <li>
                <Link to="/portfolios">포트폴리오</Link>
                <ul className={style.depth}>
                    <li>
                        <Link to="/portfolios">포트폴리오 목록</Link>
                    </li>
                </ul>
            </li>
            <li>
                <Link to="/projects">분석&관리</Link>
                <ul className={style.depth}>
                    <li>
                        <Link to="/projects">프로젝트 목록</Link>
                    </li>
                </ul>
            </li>
            <li>
                <Link to="/board/free">커뮤니티</Link>
                <ul className={style.depth}>
                    <li>
                        <Link to="/board/free">자유게시판</Link>
                    </li>
                    <li>
                        <Link to="/board/info">정보게시판</Link>
                    </li>
                    <li>
                        <Link to="/board/recruit">구인게시판</Link>
                    </li>
                </ul>
            </li>
            <li>
                <Link to="#">고객지원</Link>
                <ul className={style.depth}>
                    <li>
                        <Link to="">FAQ</Link>
                    </li>
                    <li>
                        <Link to="">1:1문의</Link>
                    </li>
                </ul>
            </li>
        </ul>
    );
};

export default Navigation;