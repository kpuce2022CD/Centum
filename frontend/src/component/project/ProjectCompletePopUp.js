import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/project/project_complete_popup.module.css';
import instance from '../security/Interceptor';

const ProjectCompletePopUp = (props) => {
    const { setPopup } = props;
    const { project, setProject } = props;
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        console.log(project);
    }, [project]);

    useEffect(() => {
        if (project.isCompleted) {
            putProject();
        }
    }, [project])

    const setRepositoryName = (e) => {
        const nextProject = {
            ...project,
            repositoryName: e.target.value,
            fromRepository: e.target.value === "" ? false : true,

        };
        setProject(nextProject);
    }

    const setComplete = () => {
        const nextProject = {
            ...project,
            isCompleted: true
        };
        setProject(nextProject);
    }

    const putProject = async () => {
        let confirm;
        if (!project.fromRepository) {
            confirm = window.confirm('저장소 이름을 입력하지 않았습니다. 완료하시겠습니까?');
        } else {
            confirm = window.confirm('완료하시겠습니까?');
        }
        if (confirm) {
            setLoading(true);
            try {
                instance.put('/api/projects/' + project.id, project).then(response => {
                    console.log(response.data);
                    navigate('/projects');
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        } else {
            const nextProject = {
            ...project,
            isCompleted: false
        };
        setProject(nextProject);
        }
    }

    if (loading) {
        return null;
    }

    return (
        <div className={style.complete_popup}>
            <div className={style.complete_popup_header}>
                <button className={style.exit_button} onClick={() => setPopup(false)}><i className="fa-solid fa-xmark"></i></button>
            </div>
            <div className={style.main_container}>
                <textarea className={style.repository_input} rows="1" onChange={setRepositoryName}></textarea>
                <div>Github 저장소 이름을 입력해주세요.</div>
            </div>
            <div className={style.access_container}>
                <button className={style.cancel_button} onClick={() => setPopup(false)}>취소</button>
                <button className={style.complete_button} onClick={() => setComplete()}>확인</button>
            </div>
        </div>
    );
};

export default ProjectCompletePopUp;