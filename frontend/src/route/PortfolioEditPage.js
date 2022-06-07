import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../component/common/Header';
import PortfolioEdit from '../component/portfolio/PortfolioEdit';
import AuthenticationService from '../component/security/AuthenticationService';

const PortfolioEditPage = (props) => {
    const { process } = props;
    const navigate = useNavigate();

    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login');
        }
    }, []);
    
    return (
        <>
            <Header />
            <PortfolioEdit process={process}/>
        </>
    );
};

export default PortfolioEditPage;