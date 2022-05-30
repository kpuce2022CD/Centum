import React, { useEffect, useRef, useState } from 'react';
import style from '../../css/board/recruit_edit_setting.module.css';

const RecruitEditSetting = () => {
    const today = new Date();
    const currentDate = new Date(today.getFullYear(), today.getMonth(), 0).getDate();
    const [recruitCount, setRecruitCount] = useState("1");
    const [autoMatching, setAutoMatching] = useState(false);
    const [years, setYears] = useState();
    const [months, setMonths] = useState();
    const [dates, setDates] = useState();
    const [deadline, setDeadline] = useState({
        year: today.getFullYear(),
        month: today.getMonth()+1,
        date: today.getDate()
    });
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

    const onChangeDeadline = (e) => {
        const nextDeadline = {
            ...deadline,
            year: year.current.value,
            month: month.current.value,
            date: date.current.value
        };
        setDeadline(nextDeadline);
    };

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
    }, [deadline]);

    if (!years || !months || !dates) {
        return null;
    }
    return (
        <>
            <div className={style.member_total_area}>
                <h4>모집 인원</h4>
                <input className={style.total1} type="radio" onClick={e => setRecruitCount(e.target.value)} checked={recruitCount === "1" ? true : false} value="1" readOnly/>
                <label onClick={() => setRecruitCount("1")}>1명</label>
                <input className={style.total2} type="radio" onClick={e => setRecruitCount(e.target.value)} checked={recruitCount === "2" ? true : false} value="2" readOnly/>
                <label>2명</label>
                <input className={style.total3} type="radio" onClick={e => setRecruitCount(e.target.value)} checked={recruitCount === "3" ? true : false} value="3" readOnly/>
                <label>3명</label>
                <input className={style.total4} type="radio" onClick={e => setRecruitCount(e.target.value)} checked={recruitCount === "4" ? true : false} value="4" readOnly/>
                <label>4명</label>
                <input className={style.total5} type="radio" onClick={e => setRecruitCount(e.target.value)} checked={recruitCount === "5" ? true : false} value="5" readOnly/>
                <label>5명</label>
                <input className={style.total6} type="radio" onClick={e => setRecruitCount(e.target.value)} checked={recruitCount === "6" ? true : false} value="6" readOnly/>
                <label>6명</label>
            </div>
            <div className={style.deadline_area}>
                <h4>마감 기한</h4>
                <select name="deadline-year" className={style.deadline_year} defaultValue={deadline.year} onChange={e => onChangeDeadline(e)} ref={year}>
                    {years.map((year,index) => (<option key={index} value={year}>{year}년</option>))}
                </select>
                <select name="deadline-month" className={style.deadline_month} defaultValue={deadline.month} onChange={e => onChangeDeadline(e)} ref={month}>
                    {months.map((month, index) => (<option key={index} value={month}>{month}월</option>))}
                </select>
                <select name="deadline-day" className={style.deadline_day} defaultValue={deadline.date} ref={date}>
                    {dates.map((date, index) => (<option key={index} value={date}>{date}일</option>))}
                </select>
            </div>
            <div className={style.auto_matching_area}>
                <h4>자동 매칭</h4>
                <input className={[style.auto_matching_status, "switch"].join(' ')} type="checkbox" onClick={() => setAutoMatching(!autoMatching)} checked={autoMatching} readOnly/>
            </div>
        </>
    );
};

export default RecruitEditSetting;