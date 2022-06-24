import React, { useEffect, useState } from 'react';
import PortfolioDetailAccess from './PortfolioDetailAccess';
import style from '../../css/portfolio/portfolio_detail.module.css';
import instance from '../security/Interceptor';
import PortfolioDescript from './PortfolioDescript';
import PortfolioImage from './PortfolioImage';
import PortfolioVideo from './PortfolioVideo';
import PortfolioYoutube from './PortfolioYoutube';
import PortfolioGit from './PortfolioGit';
import { useNavigate } from 'react-router-dom';

const PortfolioDetail = (props) => {
    const { id } = props;
    const [portfolio, setPortfolio] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchPortfolio = async () => {
            setLoading(true);
            try {
                await instance.get('/api/portfolios/' + id).then(response => {
                    setPortfolio(response.data.data.portfolio);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchPortfolio();
    }, []);

    useEffect(() => {
        console.log(portfolio);
    }, [portfolio]);

    if (loading) {
        return null;
    }

    if (!portfolio) {
        return null;
    }

    return (
       <section>
            <div className="wrap">
                <div className={style.portfolio_container}>
                    <div className={style.portfolio_area}>
                        <div className={style.portfolio_title}>{portfolio.title}</div>
                        {
                            portfolio.portfolioRows.map(portfolioRow => {
                                if (portfolioRow.rowType === "descript") {
                                    return (<PortfolioDescript key={portfolioRow.id} portfolioRow={portfolioRow} />);
                                } else if (portfolioRow.rowType === "image") {
                                    return (<PortfolioImage key={portfolioRow.id} portfolioRow={portfolioRow} />);
                                } else if (portfolioRow.rowType === "video") {
                                    return (<PortfolioVideo key={portfolioRow.id} portfolioRow={portfolioRow} />);
                                } else if (portfolioRow.rowType === "youtube") {
                                    return (<PortfolioYoutube key={portfolioRow.id} portfolioRow={portfolioRow} />);
                                } else if (portfolioRow.rowType === "github") {
                                    return (<PortfolioGit key={portfolioRow.id} portfolioRow={portfolioRow} />);
                                } else {
                                    return null;
                                }
                            })
                        }
                    </div>
                    <PortfolioDetailAccess portfolio={portfolio} />
                </div>
            </div>
        </section>
    );
};

export default PortfolioDetail;