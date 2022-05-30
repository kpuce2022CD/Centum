import React, { useEffect, useRef } from 'react';
import style from '../../css/member/signup_progress.module.css';

const SignUpProgress = (props) => {
    const { currentPage } = props;

    return (
        <section className={style.signup_progress}>
            <div className="wrap">
                <div className={style.progress_container}>
                    <div className={style.progress_area}>
                        <ul className={style.progress_list}>
                            <li className={currentPage==="terms" ? style.current_page : null}>
                                <span>1. 이용약관 동의</span>
                            </li>
                            <li className={currentPage==="form" ? style.current_page : null}>
                                <span>2. 회원 정보 입력</span>
                            </li>
                            <li className={currentPage==="auth" ? style.current_page : null}>
                                <span>3. 이메일 인증</span>
                            </li>
                            <li className={currentPage==="result" ? style.current_page : null}>
                                <span>4. 가입완료</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div className={style.progress_title_container}>
                    <div className={style.progress_title}>
                        {
                            {
                                "terms": <span>1. 이용약관 동의</span>,
                                "form": <span>2. 회원 정보 입력</span>,
                                "auth": <span>3. 이메일 인증</span>,
                                "result": <span>4. 가입완료</span>
                            }[currentPage]
                        }
                    </div>
                </div>
            </div>
        </section>
    );
};

export default SignUpProgress;