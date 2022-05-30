import React from 'react';
import style from '../../css/board/reply_edit.module.css';

const BoardReplyEdit = () => {
    return (
         <div className={style.reply_edit}>
            <span className={style.tag_nickname}></span>
            <textarea name="reply" className={style.reply_textarea} rows="1" placeholder="답글을 입력하세요"></textarea>
            <div className={style.reply_write_access_area}>
                <div className={style.file_box}>
                    <button className={style.image_upload_btn}>
                        <i className="far fa-image"></i>
                    </button>
                </div>
                <div className={style.btn_box}>
                    <button className={style.reply_cancel_btn}>취소</button>
                    <input type="submit" className={style.reply_post_btn} value="등록"/>
                </div>
            </div>
        </div>
    );
};

export default BoardReplyEdit;