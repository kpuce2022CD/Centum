import React from 'react';
import style from '../../css/portfolio/portfolio_git.module.css';

const PortfolioGit = (props) => {
    const { portfolioRow } = props;
    return (
        <div className={style.git_box}>
            <div className={style.git}>{portfolioRow.contents}</div>
        </div>
    );
};

export default PortfolioGit;