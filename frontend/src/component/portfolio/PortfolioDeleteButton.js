import React from 'react';
import style from '../../css/portfolio/portfolio_delete_button.module.css';

const PortfolioDeleteButton = (props) => {
    const { order, deleteRow } = props;
    return (
        <button className={style.delete_btn} onClick={() => deleteRow(order)}>삭제</button>
    );
};

export default PortfolioDeleteButton;