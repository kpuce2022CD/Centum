import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../component/common/Header';
import PortfolioEdit from '../component/portfolio/PortfolioEdit';
import AuthenticationService from '../component/security/AuthenticationService';
import instance from '../component/security/Interceptor';

const PortfolioEditPage = (props) => {
    const { process } = props;
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    const fetchPortfolioData = () => {
        setLoading(true);
        try {
            instance.get("/api/members/my/portfolio").then(response => {
                if (response.data.data.portfolio) {
                    alert('포트폴리오는 계정 당 1개만 생성할 수 있습니다.');
                    navigate('/portfolios/' + response.data.data.portfolio.id);
                }
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login');
        }
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