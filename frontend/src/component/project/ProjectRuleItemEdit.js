import React, { useEffect, useState } from 'react';
import style from '../../css/project/project_rule_item_edit.module.css';
import instance from '../security/Interceptor';

const ProjectRuleItemEdit = (props) => {
    const { id } = props;
    const { fetchProjectRulesData } = props;
    const [projectRule, setProjectRule] = useState({
        ruleOrder: null,
        contents: ""
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        console.log(projectRule);
    }, []);

    const projectRuleOnChange = (e) => {
        const nextProjectRule = {
            ...projectRule,
            [e.target.name]: e.target.value
        };
        setProjectRule(nextProjectRule);
    };

    const postProjectRule = () => {
        if (!projectRule.ruleOrder) {
            alert('순서를 입력해주세요');
            return;
        }
        setLoading(true);
        try {
            instance.post('/api/projects/' + id + '/rules', projectRule).then(response => {
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
            <textarea className={style.rule_order} rows="1" placeholder="#" name="ruleOrder" onChange={projectRuleOnChange}></textarea>
            <textarea className={style.rule_contents} rows="1" placeholder="규칙을 입력하세요" name="contents" onChange={projectRuleOnChange}></textarea>
            <button className={style.rule_save_btn} onClick={() => postProjectRule()}>저장</button>
        </li>
    );
};

export default ProjectRuleItemEdit;