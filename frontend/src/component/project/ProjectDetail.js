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
        if (window.confirm('?????? ?????????????????????????')) {
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
            alert("personalToken??? ???????????? ????????????. ??????????????? ??????????????????.");
            return;
        }
        if (window.confirm('??????????????? ?????????????????????????')) {
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
                alert('?????? ??? ????????? ??????????????????.');
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
                                <h4 className={style.project_subject_title}>??????</h4>
                                <div className={style.project_subject}>{project.projectField}</div>
                                {project.isCompleted && <h4 className={style.project_repository_title}>?????????</h4>}
                                {project.isCompleted && <div className={style.project_repository}>{project.repositoryName}</div>}
                                <h4 className={style.project_preview_title}>??????</h4>
                                <div className={style.project_preview}>{project.projectPreview}</div>
                                {!project.isCompleted && <h4 className={style.project_plan_title}>??????</h4>}
                                {!project.isCompleted && <ul className={style.project_plan}>
                                    {
                                        projectPlans.map(projectPlan => (
                                            <ProjectPlanItem key={projectPlan.id} projectPlan={projectPlan} process="view" />
                                        ))
                                    }
                                    {!projectPlans.length && <li>????????? ????????????. ????????? ??????????????????</li>}
                                </ul>}
                                {!project.isCompleted && <h4 className={style.project_rule_title}>??????</h4>}
                                {!project.isCompleted && <ul className={style.project_rule}>
                                    {
                                        projectRules.map(projectRule => (
                                            <ProjectRuleItem key={projectRule.id} projectRule={projectRule} process="view" />
                                        ))
                                    }
                                    {!projectRules.length && <li>????????? ????????????. ????????? ??????????????????</li>}
                                </ul>}
                            </div>
                            <div className={style.project_info}>
                                <ProjectMember project={project} projectMembers={projectMembers} />
                                <div className={style.project_access}>
                                    {project.memberId === member.id && <button className={style.modify_btn} onClick={() => navigate('/projects/' + category + '/modify/' + id)}>???????????? ??????</button>}
                                    {project.memberId === member.id && project.isCompleted && <button className={style.analysis_btn} onClick={() => analysisProject()}>???????????? ??????</button>}
                                    {project.memberId === member.id && !project.isCompleted && <button className={style.complete_btn} onClick={() => setPopup(!popup)}>???????????? ??????</button>}
                                    {project.memberId === member.id && <button className={style.destroy_btn} onClick={() => deleteProject()}>{project.isCompleted ? "???????????? ??????" : "???????????? ??????" }</button>}
                                    {projectMembers.find(projectMember => projectMember.id === member.id ? true : false) && project.memberId !== member.id && <button className={style.destroy_btn}>???????????? ??????</button>}
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