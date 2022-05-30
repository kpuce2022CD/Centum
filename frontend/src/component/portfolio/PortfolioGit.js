import React from 'react';
import PortfolioDeleteButton from './PortfolioDeleteButton';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_git.module.css';

const PortfolioGit = (props) => {
    const { row, deleteRow } = props;
    const { setGit } = props;
    return (
        <div className={style.git_box}>
            <PortfolioOrder order={row.index} />
            <label className={style.git}>
                <input type="text" placeholder="Github Repository 주소를 입력해주세요" onChange={e => setGit(e, row)}/>
            </label>
            <PortfolioDeleteButton order={row.index} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioGit;