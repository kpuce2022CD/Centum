import React from 'react';
import style from '../../css/portfolio/portfolio_order.module.css';

const PortfolioOrder = (props) => {
    const { order } = props;
    return (
        <label className={style.order}>
            <input type="text" size="1" defaultValue={order} readOnly/>
        </label>
    );
};

export default PortfolioOrder;