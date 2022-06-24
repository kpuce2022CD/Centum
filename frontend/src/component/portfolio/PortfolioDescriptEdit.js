import React from 'react';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_descript_edit.module.css';
import PortfolioDeleteButton from './PortfolioDeleteButton';


const PortfolioDescriptEdit = (props) => {
    const { row, deleteRow } = props;
    const { setDescript } = props;
    const { process } = props;

    return (
        <div className={style.descript_box}>
            <PortfolioOrder order={row.rowOrder} />
            <label className={style.descript}>
                <input type="text" placeholder="설명을 입력하세요" onChange={e => setDescript(e, row)} defaultValue={row.contents} />
            </label>
            <PortfolioDeleteButton order={row.rowOrder} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioDescriptEdit;