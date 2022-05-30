import React from 'react';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_descript.module.css';
import PortfolioDeleteButton from './PortfolioDeleteButton';


const PortfolioDescript = (props) => {
    const { row, deleteRow } = props;
    const { setDescript } = props;

    return (
        <div className={style.descript_box}>
            <PortfolioOrder order={row.index} />
            <label className={style.descript}>
                <input type="text" placeholder="설명을 입력하세요" onChange={e => setDescript(e, row)}/>
            </label>
            <PortfolioDeleteButton order={row.index} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioDescript;