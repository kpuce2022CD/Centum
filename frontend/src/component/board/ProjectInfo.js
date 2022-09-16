import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import style from '../../css/board/project_info.module.css';
import AuthenticationService from '../security/AuthenticationService';

const ProjectInfo = (props) => {
    const { post } = props;
    const navigate = useNavigate();
    const [member, setMember] = useState({});
    const [loading, setLoading] = useState(false);
    const [isRecruitMember, setIsRecruitMember] = useState(false);

    const fetchProjectMember = async () => {
        setLoading(true);
        try {
            await axios.get('/api/board/recruit/' + post.id + '/isRecruitMember').then(response => {
                setIsRecruitMember(response.data.data.isRecruitMember);
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };
    
    const fetchMemberData = () => {
        setLoading(true);
        try {
            axios.get('/api/members/my').then(response => {
                setMember(response.data.data.member);
            }).catch(error => {
                console.log(error.response);
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };

    useEffect(() => {
        fetchMemberData();
        fetchProjectMember();
    }, []);

    useEffect(() => {
        console.log(isRecruitMember);
    }, [isRecruitMember]);

    const endRecruit = () => {
        setLoading(true);
        try {
            axios.get('/api/board/recruit/' + post.id + '/end').then(response => {
                console.log(response.data);
                window.location.reload();
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };

    const restartRecruit = () => {
        setLoading(true);
        try {
            axios.get('/api/board/recruit/' + post.id + '/restart').then(response => {
                console.log(response.data);
                window.location.reload();
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };

    const completeRecruit = () => {
        if (window.confirm("프로젝트를 생성하면 게시글이 삭제됩니다. 생성하시겠습니까?")) {
            setLoading(true);
            try {
                axios.get('/api/board/recruit/' + post.id + '/complete').then(response => {
                    console.log(response.data);
                    navigate('/board/recruit');
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
    };

    const applyRecruit = () => {
        if (post.memberTally === post.memberTotal) {
            alert("인원이 다 찼습니다.");
            return;
        }
        setLoading(true);
        try {
            axios.get('/api/board/recruit/' + post.id + '/apply').then(response => {
                console.log(response.data);
                window.location.reload();
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
                            {member.loginId !== post.member.loginId && <p className={style.project_end_msg}>마감되었습니다.</p>}
                            {member.loginId === post.member.loginId && <button className={style.project_restart_btn} onClick={() => restartRecruit()}>모집하기</button>}
                            {member.loginId === post.member.loginId && <button className={style.project_complete_btn} onClick={() => completeRecruit()}>프로젝트 수행</button>}
                        </div>),
                        false: (<div>
                            {(member.loginId !== post.member.loginId && post.memberTally !== post.memberTotal) && isRecruitMember && <p>신청이 완료되었습니다.</p>}
                            {(member.loginId !== post.member.loginId && post.memberTally === post.memberTotal) && isRecruitMember && <p> 인원이 다 찼습니다.</p> }
                            {(member.loginId !== post.member.loginId && post.memberTally !== post.memberTotal) && !isRecruitMember && <button onClick={() => applyRecruit()}>신청하기</button>}
                            {member.loginId === post.member.loginId && <button onClick={() => endRecruit()}>마감하기</button>}
                        </div>)
                    }[post.deadlineStatus]
                }
            </div>
        </div>
    );
};

export default ProjectInfo;