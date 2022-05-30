import React, { useEffect, useRef, useState } from 'react';
import { Link } from 'react-router-dom';
import MemberDefaultImage from '../../image/member.png';
import style from '../../css/member/profile.module.css';

const Profile = () => {

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.profile_container}>
                    <h2 className={style.profile_title}>
                        프로필 정보
                    </h2>
                    <div className={style.profile_area}>
                        <div className={style.profile_img_area}>
                            <Link to="#" className={style.profile_img}>
                                <img src={MemberDefaultImage} alt="" />
                            </Link>
                        </div>
                        <ul className={style.member_info_area}>
                            <li className={style.member_status}>
                                <p className={style.member_nickname}>SeoArc</p>
                                <p className={style.member_level}>입문개발자</p>
                                <p className={style.member_level}>초급개발자</p>
                                <p className={style.member_level}>중급개발자</p>
                                <p className={style.member_level}>고급개발자</p>
                                <p className={style.member_level}>관리자</p>
                            </li>
                            <li className={style.member_name}>서원호</li>
                            <li className={style.member_email}>rt3310@naver.com</li>
                            <li className={style.member_field}>
                                <div className={style.interest_field}>
                                    <h6>관심</h6>
                                    <p>웹/프론트엔드</p>
                                    {/* <p>웹/백엔드</p>
                                    <p>앱/안드로이드</p>
                                    <p>앱/IOS</p>
                                    <p>AI</p>
                                    <p>하드/임베디드</p>
                                    <p>하드/IoT</p> */}
                                </div>
                                <i className="fa-solid fa-caret-right"></i>
                                <div className={style.progress_field}>
                                    <h6>진행</h6>
                                    {/* <p>분석중</p>
                                    <p>웹/프론트엔드</p>
                                    <p>웹/백엔드</p>
                                    <p>앱/안드로이드</p>
                                    <p>앱/IOS</p>
                                    <p>AI</p>
                                    <p>하드/임베디드</p> */}
                                    <p>하드/IoT</p>
                                </div>
                            </li>
                        </ul>
                        <div className={style.profile_access_area}>
                            <Link to="#" className={style.modify_profile_btn}>
                                <i className="fa-solid fa-pen"></i>
                            </Link>
                            <Link to="#" className={style.github_link_btn}>
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" className="github-icon">
                                    <path
                                        d="M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z" />
                                </svg>
                                <p className={style.github_id}>연동된 ID가 없습니다</p>
                                <p className={style.github_id}></p>
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default Profile;