import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/board_comment.module.css';
import MemberDefaultImage from '../../image/member.png';
import AuthenticationService from '../security/AuthenticationService';
import instance from '../security/Interceptor';
import PostReplyEdit from './PostReplyEdit';

const PostComment = (props) => {
    const { comment, post } = props;
    const loginId = AuthenticationService.getLoggedInLoginId();
    const [openReply, setOpenReply] = useState(false);
    const [loading, setLoading] = useState(false);


    const deleteComment = () => {
        setLoading(true);
        try {
            instance.delete('/api/board/comments/' + comment.id).then(response => {
                console.log(response.data);
                window.location.reload();
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    if (loading) {
        return null;
    }

    return (
        <li className={comment.id !== comment.groupNum ? [style.comment_area, style.replied].join(' ') : style.comment_area} >
            <div className={style.comment_box}>
                <div className={style.comment_info_area}>
                    <Link to="" className={style.commenter_img}>
                        <img src={MemberDefaultImage} alt="member-img"/>
                    </Link>
                    <div className={style.comment_info}>
                        <Link to="" className={style.commenter_nickname}>{comment.memberNickname}</Link>
                        <p className={style.mention_member}></p>
                        <p className={style.comment}>{comment.contents}</p>
                        <div className={style.reply_access}>
                            <span className={style.comment_created}>{comment.createdDate}</span>
                            <button className={style.reply_btn} onClick={() => setOpenReply(!openReply)}>답글</button>
                        </div>
                    </div>
                </div>
                <div className={style.comment_access}>
                    { loginId === comment.memberLoginId && <button className={style.modify_comment}>수정</button>}
                    { loginId === comment.memberLoginId && <button className={style.delete_comment} onClick={() => deleteComment()}>삭제</button>}
                </div>
            </div>
            {openReply ? <PostReplyEdit comment={comment} post={post} /> : null}
        </li>
    );
};

export default PostComment;