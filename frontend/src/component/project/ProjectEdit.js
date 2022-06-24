import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/project/project_edit.module.css';
import instance from '../security/Interceptor';
import ProjectMember from './ProjectMember';
import ProjectPlanItem from './ProjectPlanItem';
import ProjectPlanItemEdit from './ProjectPlanItemEdit';
import ProjectRuleItem from './ProjectRuleItem';
import ProjectRuleItemEdit from './ProjectRuleItemEdit';

const ProjectEdit = (props) => {
    const { category, id } = props;
    const navigate = useNavigate();
    const projectTitleRef = useRef();
    const projectPreviewRef = useRef();
    const [project, setProject] = useState({});
    const [projectRules, setProjectRules] = useState([]);
    const [projectPlans, setProjectPlans] = useState([]);
    const [projectMembers, setProjectMembers] = useState([]);
    const [loading, setLoading] = useState(false);
    const fetchProjectData = () => {
        setLoading(true);
        try {
            instance.get('/api/projects/' + id).then(response => {
                console.log(response.data.data.project);
                setProject(response.data.data.project);
                projectPreviewRef.current.innerHTML = response.data.data.project.projectPreview;
                projectTitleRef.current.innerHTML = response.data.data.project.title;
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
    
    useEffect(() => {
        fetchProjectData();
        if (category === "progress") {
            fetchProjectRulesData();
            fetchProjectPlansData();
            fetchProjectMemberData();
        }
    }, []);

    useEffect(() => {
        console.log(project);
    }, [project]);

    const projectOnChange = (e) => {
        const nextProject = {
            ...project,
            [e.target.name]: e.target.value
        };
        setProject(nextProject);
    }

    const titleOnChange = () => {
        const nextProject = {
            ...project,
            title: projectTitleRef.current.innerHTML
        };
        setProject(nextProject);
    }

    const projectPreviewOnChange = () => {
        const nextProject = {
            ...project,
            projectPreview: projectPreviewRef.current.innerHTML
        };
        setProject(nextProject);
    }

    const putProject = () => {
        setLoading(true);
        try {
            instance.put('/api/projects/' + project.id, project).then(response => {
                console.log(response.data);
                navigate('/projects/' + category + '/' + id);
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

     if (loading) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.project_container}>
                    <div className={style.project_area}>
                        <h3 className={style.project_title} contentEditable={true} ref={projectTitleRef} onInput={titleOnChange}></h3>
                        <div className={style.project}>
                            <div className={style.project_contents}>
                                <h4 className={style.project_subject_title}>주제</h4>
                                <textarea className={style.project_subject} rows="1" defaultValue={project.projectField} name="projectField" onChange={projectOnChange}></textarea>
                                {project.isCompleted && <h4 className={style.project_repository_title}>저장소</h4>}
                                {project.isCompleted && <textarea className={style.project_repository} rows="1" defaultValue={project.repositoryName} name="repositoryName" onChange={projectOnChange}></textarea>}
                                <h4 className={style.project_preview_title}>내용</h4>
                                <div className={style.project_preview} ref={projectPreviewRef} contentEditable name="projectPreview" onInput={projectPreviewOnChange}></div>
                                {!project.isCompleted && <h4 className={style.project_plan_title}>계획</h4>}
                                {!project.isCompleted && <ul className={style.project_plan}>
                                    {
                                        projectPlans.map(projectPlan => (
                                            <ProjectPlanItem key={projectPlan.id} projectPlan={projectPlan} process="edit" fetchProjectPlansData={fetchProjectPlansData} />
                                        ))
                                    }
                                    <ProjectPlanItemEdit id={id} fetchProjectPlansData={fetchProjectPlansData} />
                                </ul>}
                                {!project.isCompleted && <h4 className={style.project_rule_title}>규칙</h4>}
                                {!project.isCompleted && <ul className={style.project_rule}>
                                    {
                                        projectRules.map(projectRule => (
                                            <ProjectRuleItem key={projectRule.id} projectRule={projectRule} process="edit" fetchProjectRulesData={fetchProjectRulesData} />
                                        ))
                                    }
                                    <ProjectRuleItemEdit id={id} fetchProjectRulesData={fetchProjectRulesData} />
                                </ul>}
                            </div>
                            <div className={style.project_info}>
                                <ProjectMember project={project} projectMembers={projectMembers} />
                                <div className={style.project_access}>
                                    <button className={style.modify_btn} onClick={() => putProject()}>수정 완료</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProjectEdit;