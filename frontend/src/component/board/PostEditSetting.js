import React from 'react';
import style from '../../css/board/board_edit_setting.module.css';
import RecruitEditSetting from './RecruitEditSetting';

const PostEditSetting = (props) => {
    const { postStatus, post, setPost } = props;

    const visibilityOnChange = (value) => {
        const nextBoard = {
            ...post,
            visibility: value
        }
        setPost(nextBoard);
    }

    const commentAllowOnSwitch = (value) => {
        const nextBoard = {
            ...post,
            commentAllow: value
        }
        setPost(nextBoard);
    }

    const scrapAllowOnSwitch = (value) => {
        const nextBoard = {
            ...post,
            scrapAllow: value
        }
        setPost(nextBoard);
    }
    
    return (
        <div className={style.setting_container}>
            <div className={style.visibility_area}>
                <h4>공개 여부</h4>
                <input className={style.public} onClick={e => visibilityOnChange(true)} checked={post.visibility} type="radio" value="public" readOnly/>
                <label onClick={() => visibilityOnChange(true)}>공개</label>
                <input className={style.private} onClick={e => visibilityOnChange(false)} checked={!post.visibility} type="radio" value="private" readOnly/>
                <label onClick={() => visibilityOnChange(false)}>비공개</label>
            </div>
            <div className={style.allow_container}>
                <div className={style.comment_area}>
                    <h4>댓글 허용</h4>
                    <input className={style.comment_allow + " switch"} type="checkbox" checked={post.commentAllow} onClick={() => commentAllowOnSwitch(!post.commentAllow)} readOnly/>
                </div>
                <div className={style.scrap_area}>
                    <h4>스크랩 허용</h4>
                    <input className={style.scrap_allow + " switch"} type="checkbox" checked={post.scrapAllow} onClick={() => scrapAllowOnSwitch(!post.scrapAllow)} readOnly/>
                </div>
            </div>
            {postStatus === "recruit" ? <RecruitEditSetting post={post} setPost={setPost}/> : null}
        </div>
    );
};

export default PostEditSetting;