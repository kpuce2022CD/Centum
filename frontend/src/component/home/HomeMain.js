import React from 'react';
import style from '../../css/home/home_main.module.css';
import AnalysisIcon from '../../image/connection.png';
import PortfolioIcon from '../../image/briefcase.png';
import CommunityIcon from '../../image/community.png';
import QuestionIcon from '../../image/question-and-answer.png';
import { Link } from 'react-router-dom';

const home = () => {
    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <h2 className={style.main_head}>
                        서비스 소개
                    </h2>
                    <div className={style.icon_area}>
                        <Link to="#" className={style.analysis_icon}>
                            <img src={AnalysisIcon} alt="analysis-icon" />
                            <p>프로젝트 분석</p>
                        </Link>
                        <Link to="#" className={style.portfolio_icon}>
                            <img src={PortfolioIcon} alt="portfolio-icon" />
                            <p>포트폴리오 관리</p>
                        </Link>
                        <Link to="#" className={style.community_icon}>
                            <img src={CommunityIcon} alt="community-icon" />
                            <p>팀원 매칭</p>
                        </Link>
                        <Link to="#" className={style.question_icon}>
                            <img src={QuestionIcon} alt="question-icon" />
                            <p>1:1 문의</p>
                        </Link>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default home;