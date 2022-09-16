import axios from 'axios';
import React, { useEffect, useState } from 'react';
import style from '../../css/project/project_list.module.css';
import ProjectListItem from './ProjectListItem';

const ProjectList = () => {
    const [completeProjects, setCompleteProjects] = useState([]);
    const [progressProjects, setProgressProjects] = useState([]);
    const [loading, setLoading] = useState(false);
    useEffect(() => {
        const fetchProjectList = () => {
            setLoading(true);
            try {
                axios.get('/api/projects').then(response => {
                    if (response.data.success === 0) {
                        setCompleteProjects(response.data.data.projects.filter(project => project.isCompleted === true));
                        setProgressProjects(response.data.data.projects.filter(project => project.isCompleted === false));
                    }
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        
        fetchProjectList();
    }, [])

    if (loading) {
        return null;
    }

    if (!completeProjects || !progressProjects) {
        return null;
    }

    return (
        <section>
            <div className="wrap">
                <div className={style.project_container}>
                    <div className={style.complete_project}>
                        <p className={style.project_title}>완료된 프로젝트</p>
                        <ul className={style.project_list}>
                            {
                                completeProjects.map(project => (
                                    <ProjectListItem key={project.id} project={project} />
                                ))
                            }
                        </ul>
                    </div>
                    <div className={style.progress_project}>
                        <p className={style.project_title}>진행중인 프로젝트</p>
                        <ul className={style.project_list}>
                            {
                                progressProjects.map(project => (
                                    <ProjectListItem key={project.id} project={project} />
                                ))
                            }
                        </ul>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProjectList;