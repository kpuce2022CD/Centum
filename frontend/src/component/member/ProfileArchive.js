import React from 'react';
import style from '../../css/member/profile_archive.module.css';

const ProfileArchive = () => {
    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.archive_container}>
                    <h2 className={style.archive_title}>
                        내 보관함
                    </h2>
                    <div className={style.archive_area}>
                        <div className={style.scrap_portfolio_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 스크랩 한 포트폴리오</h3>
                            <ul className={style.scrap_portfolios}>
                                <li></li>
                                <p>스크랩 한 포트폴리오가 없습니다.</p>
                            </ul>
                        </div>
                        <div className={style.scrap_post_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 스크랩 한 게시글</h3>
                            <ul className={style.scrap_posts}>
                                <li></li>
                                <p>스크랩 한 게시글이 없습니다.</p>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProfileArchive;