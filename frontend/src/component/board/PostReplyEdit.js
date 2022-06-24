import React, { useState } from 'react';
import style from '../../css/board/reply_edit.module.css';
import instance from '../security/Interceptor';

const PostReplyEdit = (props) => {
    const { comment, post } = props;
    const [postComment, setPostComment] = useState({
        contents: ""
    });
    const [loading, setLoading] = useState(false);
    
    const contentsOnChange = (e) => {
        const nextPostComment = {
            ...postComment,
            contents: e.target.value
        };
        setPostComment(nextPostComment);
    };

    const postPostComment = () => {
        setLoading(true);
        try {
            instance.post('/api/board/' + post.id + '/comments/' + comment.id + '/replies', postComment).then(response => {
                console.log(response);
                window.location.reload();
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };

    const commentTextareaChangeHeight = (e) => {
        e.target.style.height = 'auto';
        const height = e.target.scrollHeight;
        e.target.style.height = `${height}px`;
    };

    if (loading) {
        return null;
    }
    
    return (
         <div className={style.reply_edit}>
            <span className={style.tag_nickname}></span>
            <textarea name="reply" className={style.reply_textarea} rows="1" onInput={commentTextareaChangeHeight} placeholder="답글을 입력하세요" onChange={contentsOnChange}></textarea>
            <div className={style.reply_write_access_area}>
                <div className={style.file_box}>
                    <button className={style.image_upload_btn}>
                        <i className="far fa-image"></i>
                    </button>
                </div>
                <div className={style.btn_box}>
                    <button className={style.reply_cancel_btn}>취소</button>
                    <button className={style.reply_post_btn} onClick={() => postPostComment()}>등록</button>
                </div>
            </div>
        </div>
    );
};

export default PostReplyEdit;