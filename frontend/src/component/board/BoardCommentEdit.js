import React from 'react';
import style from '../../css/board/board_comment_edit.module.css';

const BoardCommentEdit = () => {
    return (
        <div className={style.comment_write_area}>
            <textarea name="comment" className={style.comment_textarea} rows="1" placeholder="댓글을 입력하세요"></textarea>
            <div className={style.comment_write_access_area}>
                <div className={style.file_box}>
                    <button className={style.image_upload_btn}>
                        <i className="far fa-image"></i>
                    </button>
                </div>
                <button type="submit" className={style.comment_post_button}>등록</button>
            </div>
        </div>
    );
};

export default BoardCommentEdit;