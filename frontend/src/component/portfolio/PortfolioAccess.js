import React from 'react';
import style from '../../css/portfolio/portfolio_access.module.css';

const PortfolioAccess = () => {
    return (
        <div className={style.access_container}>
            <div className={style.portfolio_search}>
                <div className={style.portfolio_search_form}>
                    <select name="what">
                        <option value="제목">제목</option>
                        <option value="작성자">작성자</option>
                    </select>
                    <form action="/portfolios/search/title" method="get" role="search">
                        <input className={style.portfolio_query} type="text" name="title" title="포트폴리오검색" maxLength="255" />
                        <button className={style.portfolio_search_button} type="submit" title="포트폴리오검색">
                            <i className="fas fa-search search-icon"></i>
                        </button>
                    </form>
                </div>
            </div>
            <div className={style.portfolio_access}>
                <div className={style.portfolio_create_button}>
                    <a href="/portfolio/create">포트폴리오 제작</a>
                    <i className="fas fa-arrow-right"></i>
                </div>
                <div className={style.my_portfolio_button}>
                    <a href="/portfolios/search/mine">내 포트폴리오</a>
                    <i className="fas fa-arrow-right"></i>
                </div>
            </div>
        </div>
    );
};

export default PortfolioAccess;