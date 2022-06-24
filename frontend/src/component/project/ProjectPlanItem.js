import React, { useState } from 'react';
import style from '../../css/project/project_plan_item.module.css';
import instance from '../security/Interceptor';

const ProjectPlanItem = (props) => {
    const { projectPlan, process } = props;
    const { fetchProjectPlansData } = props;
    const [loading, setLoading] = useState(false);

    const deletePlan = () => {
        setLoading(true);
        try {
            instance.delete('/api/projects/plans/' + projectPlan.id).then(response => {
                console.log(response.data);
                fetchProjectPlansData();
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
        <li className={style.plan_area}>
            <div className={style.plan_start_date}>{projectPlan.startDate.split(' ')[0]}</div>
            <span><i className="fa-solid fa-angle-right"></i></span>
            <div className={style.plan_end_date}>{projectPlan.endDate.split(' ')[0]}</div>
            <div className={style.plan_contents}>{projectPlan.contents}</div>
            { process === "edit" && <button className={style.plan_delete_btn} onClick={() => deletePlan()}>삭제</button> }
        </li>
    );
};

export default ProjectPlanItem;