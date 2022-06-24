import React, { useEffect, useState } from 'react';
import style from '../../css/board/board_comment_edit.module.css';
import instance from '../security/Interceptor';

const PostCommentEdit = (props) => {
    const { post } = props;
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
            instance.post('/api/board/' + post.id + '/comments', postComment).then(response => {
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
        <div className={style.comment_write_area}>
            <textarea name="comment" className={style.comment_textarea} rows="1" placeholder="댓글을 입력하세요" onInput={commentTextareaChangeHeight} onChange={contentsOnChange}></textarea>
            <div className={style.comment_write_access_area}>
                <div className={style.file_box}>
                    <button className={style.image_upload_btn}>
                        <i className="far fa-image"></i>
                    </button>
                </div>
                <button className={style.comment_post_button} onClick={() => postPostComment()}>등록</button>
            </div>
        </div>
    );
};

export default PostCommentEdit;