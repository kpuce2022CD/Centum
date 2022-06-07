import React, { useEffect, useState } from 'react';
import style from '../../css/board/board_edit_setting.module.css';
import RecruitEditSetting from './RecruitEditSetting';

const BoardEditSetting = (props) => {
    const { postStatus, board, setBoard } = props;

    const visibilityOnChange = (value) => {
        const nextBoard = {
            ...board,
            visibility: value
        }
        setBoard(nextBoard);
    }

    const commentAllowOnSwitch = (value) => {
        const nextBoard = {
            ...board,
            commentAllow: value
        }
        setBoard(nextBoard);
    }

    const scrapAllowOnSwitch = (value) => {
        const nextBoard = {
            ...board,
            scrapAllow: value
        }
        setBoard(nextBoard);
    }
    
    return (
        <div className={style.setting_container}>
            <div className={style.visibility_area}>
                <h4>공개 여부</h4>
                <input className={style.public} onClick={e => visibilityOnChange(e.target.value)} checked={board.visibility === "public" ? true : false} type="radio" value="public" readOnly/>
                <label onClick={() => visibilityOnChange("public")}>공개</label>
                <input className={style.private} onClick={e => visibilityOnChange(e.target.value)} checked={board.visibility === "private" ? true : false} type="radio" value="private" readOnly/>
                <label onClick={() => visibilityOnChange("private")}>비공개</label>
            </div>
            <div className={style.allow_container}>
                <div className={style.comment_area}>
                    <h4>댓글 허용</h4>
                    <input className={style.comment_allow + " switch"} type="checkbox" checked={board.commentAllow} onClick={() => commentAllowOnSwitch(!board.commentAllow)} readOnly/>
                </div>
                <div className={style.scrap_area}>
                    <h4>스크랩 허용</h4>
                    <input className={style.scrap_allow + " switch"} type="checkbox" checked={board.scrapAllow} onClick={() => scrapAllowOnSwitch(!board.scrapAllow)} readOnly/>
                </div>
            </div>
            {postStatus === "recruit" ? <RecruitEditSetting board={board} setBoard={setBoard}/> : null}
        </div>
    );
};

export default BoardEditSetting;