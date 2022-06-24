import React, { useEffect, useRef, useState } from 'react';
import style from '../../css/board/recruit_edit_setting.module.css';

const RecruitEditSetting = (props) => {
    const { post, setPost } = props;
    const today = new Date();
    const currentDate = new Date(today.getFullYear(), today.getMonth(), 0).getDate();
    const [years, setYears] = useState();
    const [months, setMonths] = useState();
    const [dates, setDates] = useState();
    const year = useRef();
    const month = useRef();
    const date = useRef();

    useEffect(() => {
        let yearList = [];
        let monthList = [];
        let dateList = [];
        for (let y = today.getFullYear(); y < today.getFullYear() + 5; y++) {
            yearList.push(y);
        }
        for (let m = 1; m <= 12; m++) {
            monthList.push(m);
        }
        for (let d = 1; d <= currentDate; d++) {
            dateList.push(d);
        }
        setYears(yearList);
        setMonths(monthList);
        setDates(dateList);
    }, [])

    useEffect(() => {
        if (year.current && month.current) {
            let date;
            let dateList = [];
            date = new Date(year.current.value, month.current.value, 0).getDate();
            for (let d = 1; d <= date; d++) {
                dateList.push(d);
            }
            setDates(dateList);
        }
    }, [post.deadlineDate]);

    const onChangeDeadline = (e) => {
        const nextPost = {
            ...post,
            deadlineDate: year.current.value + "." + month.current.value + "." + date.current.value + " 00:00:00"
        };
        setPost(nextPost);
    };
    
    const boardSettingOnChange = (e) => {
        let value = e.target.value;

        if (!isNaN(value)) {
            value = Number(e.target.value);
        }
        const nextPost = {
            ...post,
            [e.target.name]: value
        };
        setPost(nextPost);
    }

    if (!years || !months || !dates) {
        return null;
    }

    return (
        <>
            <div className={style.member_total_area}>
                <h4>모집 인원</h4>
                <input className={style.total1} type="radio" name="memberTotal" onClick={e => boardSettingOnChange(e)} checked={post.memberTotal === 1 ? true : false} value={1} readOnly/>
                <label>1명</label>
                <input className={style.total2} type="radio" name="memberTotal" onClick={e => boardSettingOnChange(e)} checked={post.memberTotal === 2 ? true : false} value={2} readOnly/>
                <label>2명</label>
                <input className={style.total3} type="radio" name="memberTotal" onClick={e => boardSettingOnChange(e)} checked={post.memberTotal === 3 ? true : false} value={3} readOnly/>
                <label>3명</label>
                <input className={style.total4} type="radio" name="memberTotal" onClick={e => boardSettingOnChange(e)} checked={post.memberTotal === 4 ? true : false} value={4} readOnly/>
                <label>4명</label>
                <input className={style.total5} type="radio" name="memberTotal" onClick={e => boardSettingOnChange(e)} checked={post.memberTotal === 5 ? true : false} value={5} readOnly/>
                <label>5명</label>
                <input className={style.total6} type="radio" name="memberTotal" onClick={e => boardSettingOnChange(e)} checked={post.memberTotal === 6 ? true : false} value={6} readOnly/>
                <label>6명</label>
            </div>
            <div className={style.deadline_area}>
                <h4>마감 기한</h4>
                <select name="deadline-year" className={style.deadline_year} defaultValue={post.deadlineDate.split('.')[0]} onChange={e => onChangeDeadline(e)} ref={year}>
                    {years.map((year,index) => (<option key={index} value={year}>{year}년</option>))}
                </select>
                <select name="deadline-month" className={style.deadline_month} defaultValue={post.deadlineDate.split('.')[1]} onChange={e => onChangeDeadline(e)} ref={month}>
                    {months.map((month, index) => (<option key={index} value={String(month).padStart(2, '0')}>{month}월</option>))}
                </select>
                <select name="deadline-day" className={style.deadline_day} defaultValue={post.deadlineDate.split('.')[2].split(' ')[0]} onChange={e => onChangeDeadline(e)} ref={date}>
                    {dates.map((date, index) => (<option key={index} value={String(date).padStart(2, '0')}>{date}일</option>))}
                </select>
            </div>
            <div className={style.auto_matching_area}>
                <h4>자동 매칭</h4>
                <input className={[style.auto_matching_status, "switch"].join(' ')} type="checkbox" name="autoMatchingStatus" onClick={() => setPost({...post, autoMatchingStatus: !post.autoMatchingStatus})} checked={post.autoMatchingStatus} readOnly/>
            </div>
        </>
    );
};

export default RecruitEditSetting;