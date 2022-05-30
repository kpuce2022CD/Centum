import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/project_info.module.css';

const ProjectInfo = (props) => {
    const { post } = props;
    
    return (
        <div className={style.project_area}>
            <table className={style.project_info}>
                <colgroup>
                    <col style={{width: "70px"}} />
                    <col />
                </colgroup>
                <tbody>
                    <tr>
                        <th className={style.project_subject}>주제</th>
                        <td>
                            <span>{post.projectSubject}</span>
                        </td>
                    </tr>
                    <tr className={style.project_field}>
                        <th>분야</th>
                        <td>
                            <span>
                                {
                                    {
                                        "android": "앱/안드로이드",
                                        "ios": "앱/IOS",
                                        "backend": "웹/백엔드",
                                        "frontend": "웹/프론트엔드",
                                        "ai": "AI",
                                        "embedded": "임베디드",
                                        "iot": "IoT"
                                    }[post.projectField]
                                }
                            </span>
                        </td>
                    </tr>
                    <tr className={style.project_level}>
                        <th>난이도</th>
                        <td>
                            <span>
                                {
                                    {
                                        1: "하",
                                        2: "중",
                                        3: "상"
                                    }[post.projectLevel]
                                }
                            </span>
                            {/* <span>중</span>
                            <span>상</span> */}
                        </td>
                    </tr>
                    <tr className={style.required_member_level}>
                        <th>최소등급</th>
                        <td>
                            <span>
                                {
                                    {
                                        0: "입문개발자",
                                        1: "초급개발자",
                                        2: "중급개발자",
                                        3: "고급개발자",
                                        4: "관리자"
                                    }[post.requiredMemberLevel]
                                }
                            </span>
                        </td>
                    </tr>
                    <tr className={style.expected_period}>
                        <th>예상기간</th>
                        <td>
                            <span>
                                {
                                    {
                                        "two-w": "2주 이하",
                                        "one-m": "3개월 이하",
                                        "six-m": "6개월 이하",
                                        "lower-one-y": "1년 이하",
                                        "higher-one-y": "1년 이상"
                                    }[post.expectedPeriod]
                                }
                            </span>
                        </td>
                    </tr>
                    </tbody>
            </table>
            <div className={style.recruit_access}>
                {
                    {
                        true: (<div>
                                    <p className={style.project_end_msg}>마감되었습니다.</p>
                                    <Link className={style.project_restart_btn} to="">모집하기</Link>
                                </div>),
                        false: (<div>
                                    <p>인원이 다 찼습니다.</p>
                                    <p>신청이 완료되었습니다.</p>
                                    <Link to="">신청하기</Link>
                                    <Link to="">마감하기</Link>
                                </div>)
                    }[post.deadlineStatus]
                }
            </div>
        </div>
    );
};

export default ProjectInfo;