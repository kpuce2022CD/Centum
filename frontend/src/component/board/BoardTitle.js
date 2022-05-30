import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/board_title.module.css';

const BoardTitle = () => {
    return (
         <div className={style.board_title_container}>
            <h2 className={style.board_title}>자유게시판</h2>
            <ul className={style.board_category}>
                <li className={style.free_board}>
                    <Link to="/board/free">자유</Link>
                </li>
                <li className={style.info_board}>
                    <Link to="/board/info">정보</Link>
                </li>
                <li className={style.recruit_board}>
                    <Link to="/board/recruit">구인</Link>
                </li>
            </ul>
        </div>
    );
};

export default BoardTitle;