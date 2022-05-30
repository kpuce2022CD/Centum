import React from 'react';
import style from '../../css/board/recruit_info.module.css';

const RecruitInfo = (props) => {
    const { post } = props;
    return (
        <div className={style.recruit_info}>
            <div className={style.auto_matching_area}>
                <span className={post.autoMatchingStatus ? style.auto_matching_opened : style.auto_matching_closed}>{ post.autoMatchingStatus ? "자동 매칭 활성화 됨 " : "자동 매칭 비활성화 됨 "}<i className="fas fa-circle status_circle"></i></span>
            </div>
            <div className={style.recruit_total_area}>
                <span>인원</span>
                <div className={style.recruit_total_status}>
                    <i className={post.memberTally >= 1 ? ["fas fa-user", style.entered_member].join(' ') : "fas fa-user" }></i>
                    <i className={post.memberTally >= 2 ? ["fas fa-user", style.entered_member].join(' ') : "fas fa-user" }></i>
                    <i className={post.memberTally >= 3 ? ["fas fa-user", style.entered_member].join(' ') : "fas fa-user" }></i>
                    <i className={post.memberTally >= 4 ? ["fas fa-user", style.entered_member].join(' ') : "fas fa-user" }></i>
                    <i className={post.memberTally >= 5 ? ["fas fa-user", style.entered_member].join(' ') : "fas fa-user" }></i>
                    <i className={post.memberTally >= 6 ? ["fas fa-user", style.entered_member].join(' ') : "fas fa-user" }></i>
                </div>
            </div>
            <div className={style.recruit_deadline_area}>
                <span>기간</span>
                <span className={style.start_date}>{post.createdDate.split(' ')[0].substr(2)}</span>
                <span>~</span>
                <span className={style.end_date}>{post.deadlineDate.split(' ')[0].substr(2)}</span>
            </div>
        </div>
    );
};

export default RecruitInfo;