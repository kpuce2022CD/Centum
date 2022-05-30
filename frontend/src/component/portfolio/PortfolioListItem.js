import React from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/portfolio/portfolio_list_item.module.css';

const PortfolioListItem = (props) => {
    const navigate = useNavigate();
    const { portfolio } = props;

    return (
        <li className={style.portfolio_item} onClick={() => navigate('/portfolio/'+portfolio.id)}>
            <div className={style.portfolio_info}>
                <div className={style.portfolio_item_head}>
                    <h3 className={style.head_title}>
                        {portfolio.member.realName}님의 포트폴리오
                    </h3>
                </div>
                <div className={style.portfolio_title_area}>
                    <p className={style.portfolio_title}>
                        {portfolio.title}
                    </p>
                </div>
                <div className={style.member_interested_area}>
                    <p className={style.member_interested}>{portfolio.member.interestField}</p>
                </div>
                <div className={style.portfolio_created}>
                    <p className={style.created_date}>2022-02-11 12:30</p>
                </div>
            </div>
            <ul className={style.portfolio_icon}>
                <li className={style.portfolio_star}>
                    <i className="far fa-star"></i>
                    <span className={style.star_count}>{portfolio.starTally}</span>
                </li>
                <li className={style.portfolio_scrap}>
                    <i className="far fa-bookmark"></i>
                    <span className={style.scrap_count}>{portfolio.scrapTally}</span>
                </li>
            </ul>
        </li>
    );
};

export default PortfolioListItem;