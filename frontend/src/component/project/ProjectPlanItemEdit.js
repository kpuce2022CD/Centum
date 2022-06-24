import React, { useEffect, useState } from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { ko } from "date-fns/esm/locale";
import style from '../../css/project/project_plan_item_edit.module.css';
import instance from '../security/Interceptor';

const ProjectPlanItemEdit = (props) => {
    const { id } = props;
    const { fetchProjectPlansData } = props;
    const today = new Date();
    const [startDate, setStartDate] = useState(today);
    const [endDate, setEndDate] = useState(today);
    const [projectPlan, setProjectPlan] = useState({
        startDate: today.getFullYear() + '.' + String(today.getMonth() + 1).padStart(2, '0') + '.' + String(today.getDate()).padStart(2, '0') + ' ' + String(today.getHours()).padStart(2, '0') + ':' + String(today.getMinutes()).padStart(2, '0') + ':' + String(today.getSeconds()).padStart(2, '0'),
        endDate: today.getFullYear() + '.' + String(today.getMonth()+1).padStart(2, '0') + '.' + String(today.getDate()).padStart(2, '0') + ' ' + String(today.getHours()).padStart(2, '0') + ':' + String(today.getMinutes()).padStart(2, '0') + ':' + String(today.getSeconds()).padStart(2, '0'),
        contents: ""
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (startDate.getTime() > endDate.getTime()) {
            alert('종료일이 시작일보다 빠를 수 없습니다.');
            setEndDate(startDate);
        }
        const nextProjectPlan = {
            ...projectPlan,
            startDate: startDate.getFullYear() + '.' + String(startDate.getMonth()+1).padStart(2, '0') + '.' + String(startDate.getDate()).padStart(2, '0') + ' ' + String(startDate.getHours()).padStart(2, '0') + ':' + String(startDate.getMinutes()).padStart(2, '0') + ':' + String(startDate.getSeconds()).padStart(2, '0')
        }
        setProjectPlan(nextProjectPlan);
    }, [startDate]);

    useEffect(() => {
        if (startDate.getTime() > endDate.getTime()) {
            alert('종료일이 시작일보다 빠를 수 없습니다.');
            setEndDate(startDate);
        } else {
            const nextProjectPlan = {
                ...projectPlan,
                endDate: endDate.getFullYear() + '.' + String(endDate.getMonth()+1).padStart(2, '0') + '.' + String(endDate.getDate()).padStart(2, '0') + ' ' + String(endDate.getHours()).padStart(2, '0') + ':' + String(endDate.getMinutes()).padStart(2, '0') + ':' + String(endDate.getSeconds()).padStart(2, '0')
            }
            setProjectPlan(nextProjectPlan);
        }
    }, [endDate]);

    const projectPlanOnChange = (e) => {
        const nextProjectPlan = {
            ...projectPlan,
            [e.target.name]: e.target.value
        };
        setProjectPlan(nextProjectPlan);
    };

    const postProjectPlan = () => {
        setLoading(true);
        try {
            instance.post('/api/projects/' + id + '/plans', projectPlan).then(response => {
                console.log(response.data);
                fetchProjectPlansData();
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    };
    
    if (loading) {
        return null;
    }
    
    return (
        <li className={style.plan_area}>
            <div>
                <div>
                    <span>시작일 </span>
                    <DatePicker className={style.plan_start_date} selected={startDate} onChange={setStartDate} dateFormat="yyyy.MM.dd (eee)" locale={ko} minDate={new Date()} />
                    <span>종료일 </span>
                    <DatePicker className={style.plan_end_date} selected={endDate} onChange={setEndDate} dateFormat="yyyy.MM.dd (eee)" locale={ko} minDate={new Date()} />
                </div>
                <textarea className={style.plan_contents} rows="1" maxLength="50" placeholder="계획을 입력하세요" name="contents" onChange={projectPlanOnChange}></textarea>
            </div>
            <button className={style.plan_save_btn} onClick={() => postProjectPlan()}>저장</button>
        </li>
    );
};

export default ProjectPlanItemEdit;