import React, { useEffect, useRef } from 'react';
import PortfolioDeleteButton from './PortfolioDeleteButton';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_git_edit.module.css';

const PortfolioGitEdit = (props) => {
    const { row, deleteRow } = props;
    const { setGit } = props;
    const { process } = props;
    const gitInput = useRef();

    useEffect(() => {
        if (process === "modify" && row.contents !== "") {
            gitInput.current.value = row.contents;
        }
    }, []);
    
    return (
        <div className={style.git_box}>
            <PortfolioOrder order={row.rowOrder} />
            <label className={style.git}>
                <input type="text" placeholder="Github 저장소 명을 입력해주세요" onChange={e => setGit(e, row)} ref={gitInput} />
            </label>
            <PortfolioDeleteButton order={row.rowOrder} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioGitEdit;