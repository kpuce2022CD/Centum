import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/project/project_list_item.module.css';

const ProjectListItem = (props) => {
    const navigate = useNavigate();
    const { project } = props;
    const [category, setCategory] = useState("");

    useEffect(() => {
        if (project.isCompleted) {
            setCategory("complete");
        } else {
            setCategory("progress");
        }
    }, []);

    return (
        <li className={style.project_item} onClick={() => navigate('/projects/' + category + '/' + project.id)}>
            <div className={style.project_info}>
                <div className={style.project_title_area}>
                    <h3 className={style.project_title}>
                        {project.title}
                    </h3>
                </div>
                <div className={style.project_repository_area}>
                    <p className={style.project_repository}>
                        {project.repositoryName === "" ? "저장소가 없습니다." : project.repositoryName}
                    </p>
                </div>
                <div className={style.project_field_area}>
                    <p className={style.project_field}>{project.projectField}</p>
                </div>
                <div className={style.project_updated}>
                    <p className={style.created_date}>{project.updatedDate}</p>
                </div>
            </div>
        </li>
    );
};

export default ProjectListItem;