import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/portfolio/portfolio_access.module.css';
import instance from '../security/Interceptor';

const PortfolioAccess = (props) => {
    const { searchParam, setSearchParam } = props;
    const [myPortfolio, setMyPortfolio] = useState(null);
    const [loading, setLoading] = useState(false);

    const fetchPortfolioData = () => {
        setLoading(true);
        try {
            instance.get("/api/members/my/portfolio").then(response => {
                setMyPortfolio(response.data.data.portfolio);
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    useEffect(() => {
        fetchPortfolioData();
    }, []);

    if (loading) {
        return null;
    }

    return (
        <div className={style.access_container}>
            <div className={style.portfolio_search}>
                <div className={style.portfolio_search_area}>
                    <select className={style.search_type} onChange={e => setSearchParam({...searchParam, type:e.target.value})}>
                        <option value="title">제목</option>
                        <option value="nickname">작성자</option>
                    </select>
                    <div className={style.search_box}>
                        <input className={style.portfolio_query} type="text" maxLength="255" onChange={e => setSearchParam({...searchParam, query: e.target.value})}/>
                        <button className={style.portfolio_search_button} type="submit" title="포트폴리오검색">
                            <i className="fas fa-search search-icon"></i>
                        </button>
                    </div>
                </div>
            </div>
            <div className={style.portfolio_access}>
                <div className={style.portfolio_create_button}>
                    { !myPortfolio && <Link to="/portfolios/create">포트폴리오 제작 <i className="fas fa-arrow-right"></i></Link>}
                </div>
                <div className={style.my_portfolio_button}>
                    {myPortfolio && <Link to={"/portfolios/" + myPortfolio.id}>내 포트폴리오 <i className="fas fa-arrow-right"></i></Link>}
                </div>
            </div>
        </div>
    );
};

export default PortfolioAccess;