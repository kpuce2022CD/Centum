import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../component/common/Header';
import PortfolioEdit from '../component/portfolio/PortfolioEdit';

const PortfolioEditPage = (props) => {
    const { process } = props;
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    const fetchPortfolioData = () => {
        setLoading(true);
        try {
            axios.get("/api/members/my/portfolio").then(response => {
                console.log(response.data.success);
                if (response.data.success === 0) {
                    alert('포트폴리오는 계정 당 1개만 생성할 수 있습니다.');
                    navigate('/portfolios/' + response.data.data.portfolio.id);
                }
            }).catch(error => {
                console.log(error);
                navigate('/login?redirect=/portfolios/create');
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
        <>
            <Header />
            <PortfolioEdit process={process}/>
        </>
    );
};

export default PortfolioEditPage;