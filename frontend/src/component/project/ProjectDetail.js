import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import style from '../../css/project/project_detail.module.css';
import instance from '../security/Interceptor';
import ProjectAnalysisPopup from './ProjectAnalysisPopup';
import ProjectAnalysisResult from './ProjectAnalysisResult';
import ProjectCompletePopUp from './ProjectCompletePopUp';
import ProjectMember from './ProjectMember';
import ProjectPlanItem from './ProjectPlanItem';
import ProjectRuleItem from './ProjectRuleItem';

const ProjectDetail = (props) => {
    const { category, id } = props;
    const navigate = useNavigate();
    const [project, setProject] = useState({});
    const [projectRules, setProjectRules] = useState([]);
    const [projectPlans, setProjectPlans] = useState([]);
    const [projectMembers, setProjectMembers] = useState([]);
    const [projectSkills, setProjectSkills] = useState([]);
    const [github, setGithub] = useState({
        repositoryName: "",
        personalToken: "",
    })
    const [member, setMember] = useState({});
    const [popup, setPopup] = useState(false);
    const [analysisPopup, setAnalysisPopup] = useState(false);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchProjectData = () => {
            setLoading(true);
            try {
                instance.get('/api/projects/' + id).then(response => {
                    setProject(response.data.data.project);
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchProjectRulesData = () => {
            setLoading(true);
            try {
                instance.get('/api/projects/' + id + '/rules').then(response => {
                    setProjectRules(response.data.data.projectRules);
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchProjectPlansData = () => {
            setLoading(true);
            try {
                instance.get('/api/projects/' + id + '/plans').then(response => {
                    setProjectPlans(response.data.data.projectPlans);
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchProjectMemberData = () => {
            setLoading(true);
            try {
                instance.get('/api/projects/' + id + '/projectMembers').then(response => {
                    setProjectMembers(response.data.data.projectMembers);
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchMemberData = () => {
            setLoading(true);
            try {
                instance.get('/api/members/my').then(response => {
                    setMember(response.data.data.member);
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchProjectSkillData = () => {
            setLoading(true);
            instance.get('/api/projects/'+ id +'/skills').then(response => {
                setProjectSkills(response.data.data.projectSkills);
            });
            setLoading(false);
        };
        fetchProjectData();
        fetchMemberData();
        fetchProjectMemberData();
        if (category === "progress") {
            fetchProjectRulesData();
            fetchProjectPlansData();
        }
        if (category === "complete") {
            fetchProjectSkillData();
        }
    }, []);

    useEffect(() => {
        setGithub({ ...github, repositoryName: project.repositoryName, personalToken: member.personalToken });
    }, [project, member]);

    const deleteProject = () => {
        if (window.confirm('정말 파기하시겠습니까?')) {
            setLoading(true);
            try {
                instance.delete('/api/projects/' + id).then(response => {
                    console.log(response.data);
                    navigate('/projects');
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
    }

    const analysisProject = () => {
        if (member.personalToken === "") {
            alert("personalToken이 존재하지 않습니다. 프로필에서 입력해주세요.");
            return;
        }
        if (window.confirm('프로젝트를 분석하시겠습니까?')) {
            setAnalysisPopup(true);
            setLoading(true);
            try {
                instance.post('/api/projects/' + id + '/analysis', github).then(response => {
                    setProjectSkills(response.data.data.projectSkills);
                    setAnalysisPopup(false);
                })
            } catch (e) {
                console.log(e);
                setAnalysisPopup(false);
                alert('분석 중 오류가 발생했습니다.');
            }
            setLoading(false);
        }
    }

    const apiTest = () => {
        try {
            axios.get('http://127.0.0.1:5000/api').then(response => {
                console.log(response.data);
            })
        } catch (e) {
            console.log(e);
        }
    }

    if (loading) {
        return null;
    }

    if (!project || !projectPlans || !projectRules || !projectMembers || !member) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                {popup && <ProjectCompletePopUp setPopup={setPopup} project={project} setProject={setProject} />}
                {analysisPopup && <ProjectAnalysisPopup />}
                <div className={style.project_container}>
                    <div className={style.project_area}>
                        <h3 className={style.project_title} onClick={() => apiTest()}>{project.title}</h3>
                        <div className={style.project}>
                            <div className={style.project_contents}>
                                <h4 className={style.project_subject_title}>주제</h4>
                                <div className={style.project_subject}>{project.projectField}</div>
                                {project.isCompleted && <h4 className={style.project_repository_title}>저장소</h4>}
                                {project.isCompleted && <div className={style.project_repository}>{project.repositoryName}</div>}
                                <h4 className={style.project_preview_title}>내용</h4>
                                <div className={style.project_preview}>{project.projectPreview}</div>
                                {!project.isCompleted && <h4 className={style.project_plan_title}>계획</h4>}
                                {!project.isCompleted && <ul className={style.project_plan}>
                                    {
                                        projectPlans.map(projectPlan => (
                                            <ProjectPlanItem key={projectPlan.id} projectPlan={projectPlan} process="view" />
                                        ))
                                    }
                                    {!projectPlans.length && <li>계획이 없습니다. 계획을 추가해주세요</li>}
                                </ul>}
                                {!project.isCompleted && <h4 className={style.project_rule_title}>규칙</h4>}
                                {!project.isCompleted && <ul className={style.project_rule}>
                                    {
                                        projectRules.map(projectRule => (
                                            <ProjectRuleItem key={projectRule.id} projectRule={projectRule} process="view" />
                                        ))
                                    }
                                    {!projectRules.length && <li>규칙이 없습니다. 규칙을 추가해주세요</li>}
                                </ul>}
                            </div>
                            <div className={style.project_info}>
                                <ProjectMember project={project} projectMembers={projectMembers} />
                                <div className={style.project_access}>
                                    {project.memberId === member.id && <button className={style.modify_btn} onClick={() => navigate('/projects/' + category + '/modify/' + id)}>프로젝트 수정</button>}
                                    {project.memberId === member.id && project.isCompleted && <button className={style.analysis_btn} onClick={() => analysisProject()}>프로젝트 분석</button>}
                                    {project.memberId === member.id && !project.isCompleted && <button className={style.complete_btn} onClick={() => setPopup(!popup)}>프로젝트 완료</button>}
                                    {project.memberId === member.id && <button className={style.destroy_btn} onClick={() => deleteProject()}>{project.isCompleted ? "프로젝트 삭제" : "프로젝트 파기" }</button>}
                                    {projectMembers.find(projectMember => projectMember.id === member.id ? true : false) && project.memberId !== member.id && <button className={style.destroy_btn}>프로젝트 탈퇴</button>}
                                </div>
                            </div>
                        </div>
                        <ProjectAnalysisResult id={id} projectSkills={projectSkills} />
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProjectDetail;