import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import style from '../../css/board/board_detail_title.module.css';
import RecruitInfo from './RecruitInfo';
import DefaultMemberImage from '../../image/member.png';
import AuthenticationService from '../security/AuthenticationService';
import instance from '../security/Interceptor';

const PostTitle = (props) => {
    const { category, post } = props;
    const loginId = AuthenticationService.getLoggedInLoginId();
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    const deletePost = () => {
        setLoading(true);
        try {
            instance.delete('/api/board/' + post.id).then(response => {
                console.log(response.data);
                navigate('/board/' + category);
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };

    if (loading) {
        return null;
    }

    return (
        <div className={style.title_container}>
            <div className={style.general_info}>
                <div className={style.title_area}>
                    <h1 className={style.title}>{post.title}</h1>
                </div>
                <div className={style.writer_area}>
                    <Link to="" className={style.writer_img}>
                        <img src={DefaultMemberImage} alt="profile-img"/>
                    </Link>
                    <Link to="" className={style.writer_nickname}>{post.member.nickname}</Link>
                </div>
                <div className={style.post_info_area}>
                    <span className={style.created_date}>{post.createdDate}</span>
                    <span className={style.view_count}>조회 {post.viewTally}</span>
                    <span className={style.star_cnt}>추천 {post.starTally}</span>
                    <span className={style.scrap_count}>스크랩 {post.scrapTally}</span>
                    { loginId === post.member.loginId && <button className={style.modify_board} onClick={() => navigate("/board/modify/" + post.id, {state: {category: category}})}>수정</button>}
                    { loginId === post.member.loginId && <button className={style.delete_board} onClick={() => deletePost()}>삭제</button>}
                </div>
            </div>
            {category === "recruit" ? <RecruitInfo post={post}/> : null}
        </div>
    );
};

export default PostTitle;