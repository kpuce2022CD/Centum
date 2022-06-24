import React from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/portfolio/portfolio_list_item.module.css';

const PortfolioListItem = (props) => {
    const navigate = useNavigate();
    const { portfolio } = props;
    const { searchParam } = props;

    return (
        <li className={style.portfolio_item} onClick={() => navigate('/portfolios/'+portfolio.id)}>
            <div className={style.portfolio_info}>
                <div className={style.portfolio_title_area}>
                    <h3 className={style.portfolio_title}>
                        {searchParam.type === "title" && searchParam.query !== "" ? (
                            <>
                                {
                                    portfolio.title.slice(
                                        portfolio.title.toLowerCase().indexOf(portfolio.title.toLowerCase().split(searchParam.query.toLowerCase())[0]),
                                        portfolio.title.toLowerCase().indexOf(portfolio.title.toLowerCase().split(searchParam.query.toLowerCase())[0]) + portfolio.title.toLowerCase().split(searchParam.query.toLowerCase())[0].length)
                                }
                                <span className={style.search_query}>{portfolio.title.slice(
                                    portfolio.title.toLowerCase().indexOf(searchParam.query.toLowerCase()),
                                    portfolio.title.toLowerCase().indexOf(searchParam.query.toLowerCase()) + searchParam.query.length)}
                                </span>
                                {
                                    portfolio.title.slice(portfolio.title.toLowerCase().indexOf(portfolio.title.toLowerCase().split(searchParam.query.toLowerCase())[1]), portfolio.title.toLowerCase().split(searchParam.query.toLowerCase())[1] === "" ? 0 : portfolio.title.length)
                                }
                            </>
                        ) : (
                                portfolio.title
                        )}
                    </h3>
                </div>
                <div className={style.portfolio_nickname_area}>
                    <p className={style.member_nickname}>
                        {searchParam.type === "nickname" && searchParam.query !== "" ? (
                            <>
                                {
                                    portfolio.member.nickname.slice(
                                        portfolio.member.nickname.toLowerCase().indexOf(portfolio.member.nickname.toLowerCase().split(searchParam.query.toLowerCase())[0]),
                                        portfolio.member.nickname.toLowerCase().indexOf(portfolio.member.nickname.toLowerCase().split(searchParam.query.toLowerCase())[0]) + portfolio.member.nickname.toLowerCase().split(searchParam.query.toLowerCase())[0].length)
                                }
                                <span className={style.search_query}>{portfolio.member.nickname.slice(
                                    portfolio.member.nickname.toLowerCase().indexOf(searchParam.query.toLowerCase()),
                                    portfolio.member.nickname.toLowerCase().indexOf(searchParam.query.toLowerCase()) + searchParam.query.length)}
                                </span>
                                {
                                    portfolio.member.nickname.slice(portfolio.member.nickname.toLowerCase().indexOf(portfolio.member.nickname.toLowerCase().split(searchParam.query.toLowerCase())[1]), portfolio.member.nickname.toLowerCase().split(searchParam.query.toLowerCase())[1] === "" ? 0 : portfolio.member.nickname.length)
                                }
                                님의 포트폴리오
                            </>
                        ) : (
                            portfolio.member.nickname + "님의 포트폴리오"
                        )}
                    </p>
                </div>
                <div className={style.member_interested_area}>
                    <p className={style.member_interested}>
                        {portfolio.member.interestField}
                    </p>
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