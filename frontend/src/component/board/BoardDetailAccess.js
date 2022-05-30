import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/board_detail_access.module.css';

const BoardDetailAccess = (props) => {
    const { post } = props;

    return (
        <div className={style.access_container}>
            <div className={style.comment_count_area}>
                <i className="far fa-comments"></i>
                <span className={style.comment_count}>댓글 {post.commentTally}</span>
            </div>
            <div className={style.recommend_area}>
                <Link className={style.star_btn} to="">
                    <i className="far fa-star"></i>
                    <span>추천</span>
                </Link>
                <Link className={style.scrap_btn} to="">
                    <i className="far fa-bookmark"></i>
                    <span>스크랩</span>
                </Link>
            </div>
        </div>
    );
};

export default BoardDetailAccess;