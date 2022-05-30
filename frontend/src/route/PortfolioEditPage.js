import React from 'react';
import Header from '../component/common/Header';
import PortfolioEdit from '../component/portfolio/PortfolioEdit';

const PortfolioEditPage = (props) => {
    const { process } = props;
    return (
        <>
            <Header />
            <PortfolioEdit process={process}/>
        </>
    );
};

export default PortfolioEditPage;