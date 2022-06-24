import React, { useState } from 'react';
import style from '../../css/project/project_rule_item.module.css';
import instance from '../security/Interceptor';

const ProjectRuleItem = (props) => {
    const { projectRule, process } = props;
    const { fetchProjectRulesData } = props;
    const [loading, setLoading] = useState();

    const deleteRule = () => {
        setLoading(true);
        try {
            instance.delete('/api/projects/rules/' + projectRule.id).then(response => {
                console.log(response.data);
                fetchProjectRulesData();
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
        <li className={style.rule_area}>
            <div className={style.rule_order}>{projectRule.ruleOrder}</div>
            <div className={style.rule_contents}>{projectRule.contents}</div>
            { process === "edit" && <button className={style.rule_delete_btn} onClick={() => deleteRule()}>삭제</button> }
        </li>
    );
};

export default ProjectRuleItem;