import React from 'react';
import style from '../../css/member/profile_record.module.css';

const ProfileRecord = () => {
    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.record_container}>
                    <h2 className={style.record_title}>
                        내 기록
                    </h2>
                    <div className={style.record_area}>
                        <div className={style.post_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 내 게시글</h3>
                            <ul className={style.posts}>
                                <li></li>
                                <p>게시글이 없습니다.</p>
                            </ul>
                        </div>
                        <div className={style.comment_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 내 댓글</h3>
                            <ul className={style.comments}>
                                <li></li>
                                <p>댓글이 없습니다.</p>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProfileRecord;