import React, { useState } from 'react';
import style from '../../css/board/board_detail_access.module.css';
import instance from '../security/Interceptor';

const PostAccess = (props) => {
    const { post } = props;
    const [loading, setLoading] = useState(false);

    const countStar = () => {
        setLoading(true);
        try {
            instance.get('/api/board/' + post.id + '/star').then(response => {
                console.log(response.data);
                if (response.data.data.postStar === null) {
                    alert("추천을 취소했습니다.");
                } else {
                    alert("게시글을 추천했습니다.");
                }
                window.location.reload();
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    const countScrap = () => {
        setLoading(true);
        try {
            instance.get('/api/board/' + post.id + '/scrap').then(response => {
                console.log(response.data);
                if (response.data.data.postScrap === null) {
                    alert("스크랩이 취소되었습니다.");
                } else {
                    alert("스크랩 목록에 추가되었습니다.");
                }
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
        <div className={style.access_container}>
            <div className={style.comment_count_area}>
                <i className="far fa-comments"></i>
                <span className={style.comment_count}>댓글 {post.commentTally}</span>
            </div>
            <div className={style.recommend_area}>
                <button className={style.star_btn} onClick={() => countStar()}>
                    <i className="far fa-star"></i>
                    <span>추천</span>
                </button>
                <button className={style.scrap_btn} onClick={() => countScrap()}>
                    <i className="far fa-bookmark"></i>
                    <span>스크랩</span>
                </button>
            </div>
        </div>
    );
};

export default PostAccess;