import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/board_title.module.css';

const BoardTitle = (props) => {
    const { category } = props;

    return (
         <div className={style.board_title_container}>
            <h2 className={style.board_title}>{
                {
                    "free": "자유게시판",
                    "info": "정보게시판",
                    "recruit": "구인게시판"
                }[category]
            }</h2>
            <ul className={style.board_category}>
                <li className={style.free_board}>
                    <Link to="/board/free" className={category === "free" ? style.up : null}>자유</Link>
                </li>
                <li className={style.info_board}>
                    <Link to="/board/info" className={category === "info" ? style.up : null}>정보</Link>
                </li>
                <li className={style.recruit_board}>
                    <Link to="/board/recruit" className={category === "recruit" ? style.up : null}>구인</Link>
                </li>
            </ul>
        </div>
    );
};

export default BoardTitle;