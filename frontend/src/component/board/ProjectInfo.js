import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/project_info.module.css';
import AuthenticationService from '../security/AuthenticationService';
import instance from '../security/Interceptor';

const ProjectInfo = (props) => {
    const { post } = props;
    const loginId = AuthenticationService.getLoggedInLoginId();
    const [loading, setLoading] = useState(false);
    const [isProjectMember, setIsProjectMember] = useState(false);
    
    useEffect(() => {
        const fetchProjectMember = async () => {
            setLoading(true);
            try {
                await instance.get('/api/board/isProjectMember/' + post.id).then(response => {
                    setIsProjectMember(response.data.data.isProjectMember);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchProjectMember();
    }, []);

    useEffect(() => {
        console.log(isProjectMember);
    }, [isProjectMember]);

    if (loading) {
        return null;
    }
    
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
                            <span>{post.projectField}</span>
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
                            <span>{post.expectedPeriod}</span>
                        </td>
                    </tr>
                    </tbody>
            </table>
            <div className={style.recruit_access}>
                {
                    {
                        true: (<div>
                            {loginId !== post.member.loginId && <p className={style.project_end_msg}>마감되었습니다.</p>}
                            {loginId === post.member.loginId && <Link className={style.project_restart_btn} to="">모집하기</Link>}
                        </div>),
                        false: (<div>
                            {(loginId !== post.member.loginId && post.memberTally === post.memberTotal) && <p> 인원이 다 찼습니다.</p> }
                            {loginId !== post.member.loginId && isProjectMember && <p>신청이 완료되었습니다.</p>}
                            {(loginId !== post.member.loginId && post.memberTally !== post.memberTotal) && !isProjectMember && <Link to="">신청하기</Link>}
                            {loginId === post.member.loginId && <Link to="">마감하기</Link>}
                        </div>)
                    }[post.deadlineStatus]
                }
            </div>
        </div>
    );
};

export default ProjectInfo;