import React, { useEffect, useState } from 'react';
import PortfolioAccess from './PortfolioAccess';
import PortfolioListItem from './PortfolioListItem';
import style from '../../css/portfolio/portfolio_list.module.css';
import axios from 'axios';
import instance from '../security/Interceptor';

const PortfolioList = () => {
    const [portfolioList, setPortfolioList] = useState([]);
    const [bestPortfolioList, setBestPortfolioList] = useState([]);
    const [loading, setLoading] = useState(false);
    useEffect(() => {
        const fetchPortfolioList = () => {
            setLoading(true);
            try {
                instance.get('/api/portfolios').then(response => {
                    setPortfolioList(portfolioList.concat(response.data.data.portfolios));
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchBestPortfolioList = () => {
            setLoading(true);
            try {
                instance.get('/api/portfolios/best').then(response => {
                    setBestPortfolioList(bestPortfolioList.concat(response.data.data.bestPortfolios))
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchPortfolioList();
        fetchBestPortfolioList();
    }, [])

    if (loading) {
        return null;
    }

    if (!portfolioList) {
        return null;
    }

    return (
        <section>
            <div className="wrap">
                <PortfolioAccess />
                <div className={style.portfolio_container}>
                    <p className={style.best_title}>BEST 포트폴리오</p>
                    <ul className={style.best_portfolio_list}>
                        {
                            bestPortfolioList.map(bestPortfolio => (
                                <PortfolioListItem key={bestPortfolio.id} portfolio={bestPortfolio} />
                            ))
                        }
                    </ul>
                    <ul className={style.portfolio_list}>
                        {
                            portfolioList.map(portfolio => (
                                <PortfolioListItem key={portfolio.id} portfolio={portfolio} />
                            ))
                        }
                    </ul>
                </div>
            </div>
        </section>
    );
};

export default PortfolioList;