import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Header from '../component/common/Header';
import PortfolioDetail from '../component/portfolio/PortfolioDetail';
import AuthenticationService from '../component/security/AuthenticationService';

const PortfolioDetailPage = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    
    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login?redirect=/portfolios/' + id);
        }
    }, []);
    
    return (
        <>
            <Header />
            <PortfolioDetail id={id} />
        </>
    );
};

export default PortfolioDetailPage;