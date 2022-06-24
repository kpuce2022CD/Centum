import React from 'react';
import style from '../../css/portfolio/portfolio_descript.module.css';

const PortfolioDescript = (props) => {
    const { portfolioRow } = props;
    return (
        <div className={style.descript_box}>
            <div className={style.descript}>{portfolioRow.contents}</div>
        </div>
    );
};

export default PortfolioDescript;