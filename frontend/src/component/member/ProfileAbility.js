import React, { useEffect, useRef, useState } from 'react';
import style from '../../css/member/profile_ability.module.css';
import Chart from 'chart.js/auto';
import instance from '../security/Interceptor';
import AuthenticationService from '../security/AuthenticationService';

const ProfileAbility = () => {
    const chartRef = useRef();
    const [loading, setLoading] = useState(false);
    const [memberAbility, setMemberAbility] = useState({});
    const [memberTitles, setMemberTitles] = useState([]);
    const [memberSkills, setMemberSkills] = useState([]);
    const [completeProjects, setCompleteProjects] = useState([]);
    const [progressProjects, setProgressProjects] = useState([]);

    useEffect(() => {
        const fetchMemberAbilityData = () => {
            setLoading(true);
            try {
                instance.get('/api/members/my/ability').then(response => {
                    setMemberAbility(response.data.data.memberAbility);
                });
                instance.get('/api/members/my/skills').then(response => {
                    setMemberSkills(response.data.data.memberSkills);
                });
                instance.get('/api/members/my/titles').then(response => {
                    setMemberTitles(response.data.data.memberTitles);
                });
                instance.get('/api/members/my/projects').then(response => {
                    setCompleteProjects(response.data.data.projects.filter(project => project.fromRepository === true));
                    setProgressProjects(response.data.data.projects.filter(project => project.fromRepository === false));
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        };
        fetchMemberAbilityData();
    }, []);

    useEffect(() => {
        let prevChart = Chart.getChart(chartRef.current);
        if (prevChart !== undefined) {
            prevChart.destroy();
        }
        new Chart(chartRef.current.getContext("2d"), {
            type: 'radar',
            data: {
                labels: ["응집도", "비결합도", "비복잡도", "비중복도", "표준화"],
                datasets: [
                    {
                        label: "현재 구현도",
                        fill: true,
                        backgroundColor: "rgba(200,222,255,0.2)",
                        borderColor: "rgba(200,222,255,1)",
                        pointBorderColor: "#fff",
                        pointBackgroundColor: "rgba(179,181,198,1)",
                        data: [memberAbility.cohesion, memberAbility.coupling, memberAbility.complexity, memberAbility.redundancy, memberAbility.standard],
                    },
                    {
                        label: "",
                        fill: false,
                        data: [0, 100],
                        backgroundColor: "#fff0",
                        borderColor: "#fff0",
                        pointBorderColor: "#fff0",
                        pointBackgroundColor: "#fff0"
                    }
                ]
            }
        });
    }, [memberAbility])

    if (loading) {
        return null;
    }
    
    if (!memberAbility || !memberSkills || !memberTitles) {
        return null;
    }
    
    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.ability_container}>
                    <h2 className={style.ability_title}>
                        프로젝트 분석
                    </h2>
                    <div className={style.ability_area}>
                        <div className={style.code_chart}>
                            <h3><i className="fa-solid fa-caret-right"></i> 코드 구현도</h3>
                            <canvas ref={chartRef} width="350" height="350"></canvas>
                        </div>
                        <div className={style.stack_area}>
                            <div className={style.skill_stack}>
                                <h3><i className="fa-solid fa-caret-right"></i> 기술 스택</h3>
                                <ul className={style.skills}>
                                    {
                                        memberSkills.map(memberSkill => (
                                            <li key={memberSkill.id}>{memberSkill.skillName}</li>
                                        ))
                                    }
                                    {!memberSkills.length && <p>아직 기술 스택이 없습니다.</p>}
                                </ul>
                            </div>
                            <div className={style.title_stack}>
                                <h3><i className="fa-solid fa-caret-right"></i> 칭호</h3>
                                <ul className={style.titles}>
                                    {
                                        memberTitles.map(memberTitle => (
                                            <li key={memberTitle.id}>{memberTitle.titleName}</li>
                                        ))
                                    }
                                    {!memberTitles.length && <p>아직 칭호가 없습니다.</p>}
                                </ul>
                            </div>
                        </div>
                        <div className={style.project_area}>
                            <div className={style.complete_project_list}>
                                <h3><i className="fa-solid fa-caret-right"></i> 완료된 프로젝트 목록</h3>
                                <ul className={style.complete_projects}>
                                    {
                                        completeProjects.map(completeProject => (
                                            <li key={completeProject.id}>{completeProject.title}</li>
                                        ))
                                    }
                                    {!completeProjects.length && <p>완료된 프로젝트가 없습니다.</p>}
                                </ul>
                            </div>
                            <div className={style.progress_project_list}>
                                <h3><i className="fa-solid fa-caret-right"></i> 진행중인 프로젝트 목록</h3>
                                <ul className={style.progress_projects}>
                                    {
                                        progressProjects.map(progressProject => (
                                            <li key={progressProject.id}>{progressProject.title}</li>
                                        ))
                                    }
                                    {!progressProjects.length && <p>진행 중인 프로젝트가 없습니다.</p>}
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProfileAbility;