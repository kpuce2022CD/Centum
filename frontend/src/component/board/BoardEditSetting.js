import React, { useEffect, useState } from 'react';
import style from '../../css/board/board_edit_setting.module.css';
import RecruitEditSetting from './RecruitEditSetting';

const BoardEditSetting = (props) => {
    const { postStatus } = props;
    const [visibility, setVisibility] = useState("public");
    const [reply, setReply] = useState(true);
    const [scrap, setScrap] = useState(false);
    
    return (
        <div className={style.setting_container}>
            <div className={style.visibility_area}>
                <h4>공개 여부</h4>
                <input className={style.public} onClick={e => setVisibility(e.target.value)} checked={visibility === "public" ? true : false} type="radio" value="public" readOnly/>
                <label onClick={() => setVisibility("public")}>공개</label>
                <input className={style.private} onClick={e => setVisibility(e.target.value)} checked={visibility === "private" ? true : false} type="radio" value="private" readOnly/>
                <label onClick={() => setVisibility("private")}>비공개</label>
            </div>
            <div className={style.allow_container}>
                <div className={style.comment_area}>
                    <h4>댓글 허용</h4>
                    <input className={style.comment_allow + " switch"} type="checkbox" checked={reply} onClick={() => setReply(!reply)} readOnly/>
                </div>
                <div className={style.scrap_area}>
                    <h4>스크랩 허용</h4>
                    <input className={style.scrap_allow + " switch"} type="checkbox" checked={scrap} onClick={() => setScrap(!scrap)} readOnly/>
                </div>
            </div>
            { postStatus === "recruit" ? <RecruitEditSetting /> : null}
        </div>
    );
};

export default BoardEditSetting;