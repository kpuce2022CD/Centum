import React, { useEffect, useState } from 'react';
import PortfolioDetailAccess from './PortfolioDetailAccess';
import style from '../../css/portfolio/portfolio_detail.module.css';
import axios from 'axios';
import instance from '../security/Interceptor';
import { useParams } from 'react-router-dom';

const PortfolioDetail = (props) => {
    const { id } = props;
    const [portfolio, setPortfolio] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchPortfolio = async () => {
            setLoading(true);
            try {
                await instance.get('/api/portfolio/' + id).then(response => {
                    setPortfolio(response.data.data.portfolio);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchPortfolio();
    }, []);

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
                    </div>
                    <PortfolioDetailAccess portfolio={portfolio} />
                </div>
            </div>
        </section>
    );
};

export default PortfolioDetail;