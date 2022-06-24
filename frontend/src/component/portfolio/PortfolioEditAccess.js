import React from 'react';
import style from '../../css/portfolio/portfolio_edit_access.module.css';

const PortfolioEditAccess = (props) => {
    const { rows, setRows } = props;
    const { currentOrder, setCurrentOrder } = props;
    const { visibility, setVisibility } = props;
    const { postPortfolio } = props;
    
    const additionRow = (e) => {
        const nextRow = {
            rowOrder: currentOrder + 1,
            rowType: e.target.value,
            saveType: "",
            contents: ""
        };
        setRows([...rows].concat(nextRow));
        setCurrentOrder(currentOrder + 1);
    }

    return (
        <div className={style.access_area}>
            <div className={style.public_box}>
                <span>private</span>
                <input className={[style.public_status, "switch"].join(' ')} type="checkbox" checked={visibility} onClick={() => setVisibility(!visibility)} readOnly/>
                <span>public</span>
            </div>
            <div className={style.addition_box}>
                <button className={style.add_image} onClick={e => additionRow(e)} value="image">사진 추가</button>
                <button className={style.add_youtube} onClick={e => additionRow(e)} value="youtube">유튜브 영상 추가</button>
                <button className={style.add_video} onClick={e => additionRow(e)} value="video">영상 파일 추가</button>
                <button className={style.add_descript} onClick={e => additionRow(e)} value="descript">설명 추가</button>
                <button className={style.add_git} onClick={e => additionRow(e)} value="github">Github 저장소 추가</button>
                <button className={style.complete_button} onClick={() => postPortfolio()}>완료</button>
            </div>
        </div>
    );
};

export default PortfolioEditAccess;