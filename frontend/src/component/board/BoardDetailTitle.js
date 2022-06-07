import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/board_detail_title.module.css';
import RecruitInfo from './RecruitInfo';
import DefaultMemberImage from '../../image/member.png';
import AuthenticationService from '../security/AuthenticationService';

const BoardDetailTitle = (props) => {
    const { category, post } = props;
    const loginId = AuthenticationService.getLoggedInLoginId();

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
                    { loginId === post.member.loginId && <Link className={style.modify_board} to="">수정</Link>}
                    { loginId === post.member.loginId && <Link className={style.delete_board} to="">삭제</Link>}
                </div>
            </div>
            {category === "recruit" ? <RecruitInfo post={post}/> : null}
        </div>
    );
};

export default BoardDetailTitle;