import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/board_comment.module.css';
import MemberDefaultImage from '../../image/member.png';
import BoardReplyEdit from './BoardReplyEdit';

const BoardComment = (props) => {
    const { comment } = props;
    const [openReply, setOpenReply] = useState(false);

    return (
        <li className={comment.id !== comment.groupNum ? [style.comment_area, style.replied].join(' ') : style.comment_area} >
            <div className={style.comment_box}>
                <div className={style.comment_info_area}>
                    <Link to="" className={style.commenter_img}>
                        <img src={MemberDefaultImage} alt="member-img"/>
                    </Link>
                    <div className={style.comment_info}>
                        <Link to="" className={style.commenter_nickname}>{comment.member.nickname}</Link>
                        <p className={style.mention_member}></p>
                        <p className={style.comment}>{comment.contents}</p>
                        <div className={style.reply_access}>
                            <span className={style.comment_created}>{comment.createdDate}</span>
                            <button className={style.reply_btn} onClick={() => setOpenReply(!openReply)}>답글</button>
                        </div>
                    </div>
                </div>
                <div className={style.comment_access}>
                    <Link className={style.modify_comment} to="">
                        <span>수정</span>
                    </Link>
                    <Link className={style.delete_comment} to="">
                        <span>삭제</span>
                    </Link>
                </div>
            </div>
            {openReply ? <BoardReplyEdit /> : null}
        </li>
    );
};

export default BoardComment;