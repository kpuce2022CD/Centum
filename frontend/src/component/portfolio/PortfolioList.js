import React, { useEffect, useState } from 'react';
import PortfolioAccess from './PortfolioAccess';
import PortfolioListItem from './PortfolioListItem';
import style from '../../css/portfolio/portfolio_list.module.css';
import axios from 'axios';

const PortfolioList = () => {
    const [member, setMember] = useState({});
    const [searchParam, setSearchParam] = useState({
        type: "title",
        query: ""
    });
    const [portfolioList, setPortfolioList] = useState([]);
    const [bestPortfolioList, setBestPortfolioList] = useState([]);
    const [loading, setLoading] = useState(false);

    const fetchPortfolioList = () => {
        setLoading(true);
        try {
            axios.get('/api/portfolios', { params: {type: searchParam.type, query: searchParam.query}}).then(response => {
                if (response.data.success === 0) {
                    setPortfolioList(response.data.data.portfolios);
                }
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }
    const fetchBestPortfolioList = () => {
        setLoading(true);
        try {
            axios.get('/api/portfolios/best').then(response => {
                if (response.data.success === 0) {
                    setBestPortfolioList(response.data.data.bestPortfolios);
                }
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
                if (response.data.success === 0) {
                    setMember(response.data.data.member);
                }
            }).catch(error => {
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };
    
    useEffect(() => {
        fetchBestPortfolioList();
        fetchMemberData();
    }, []);

    useEffect(() => {
        fetchPortfolioList();
    }, [searchParam]);

    if (loading) {
        return null;
    }

    if (!portfolioList || !bestPortfolioList || !member) {
        return null;
    }

    return (
        <section>
            <div className="wrap">
                <PortfolioAccess searchParam={searchParam} setSearchParam={setSearchParam} />
                <div className={style.portfolio_container}>
                    {searchParam.query === "" && <p className={style.best_title}>BEST 포트폴리오</p>}
                    {searchParam.query === "" && <ul className={style.best_portfolio_list}>
                        {
                            bestPortfolioList.map(bestPortfolio => {
                                if (bestPortfolio.visibility || (member.id === bestPortfolio.member.id)) {
                                    return <PortfolioListItem key={bestPortfolio.id} portfolio={bestPortfolio} searchParam={searchParam} />;
                                }
                            })
                        }
                    </ul>}
                    <ul className={style.portfolio_list}>
                        {
                            portfolioList.map(portfolio => {
                                if (portfolio.visibility || (member.id === portfolio.member.id)) {
                                    return <PortfolioListItem key={portfolio.id} portfolio={portfolio} searchParam={searchParam} />;
                                }
                            })
                        }
                    </ul>
                </div>
            </div>
        </section>
    );
};

export default PortfolioList;