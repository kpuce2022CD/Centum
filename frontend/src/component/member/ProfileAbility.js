import React, { useEffect, useRef, useState } from 'react';
import style from '../../css/member/profile_ability.module.css';
import Chart from 'chart.js/auto';

const ProfileAbility = () => {
    const chartRef = useRef();
    const [cohesion, setCohesion] = useState(93);
    const [coupling, setCoupling] = useState(71);
    const [complexity, setComplexity] = useState(51)
    const [redundancy, setRedundancy] = useState(37);
    const [standard, setStandart] = useState(13);

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
                        data: [cohesion, coupling, complexity, redundancy, standard],
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
    }, []);
    
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
                                    <li></li>
                                    <p>아직 기술 스택이 없습니다.</p>
                                </ul>
                            </div>
                            <div className={style.title_stack}>
                                <h3><i className="fa-solid fa-caret-right"></i> 칭호</h3>
                                <ul className={style.titles}>
                                    <li></li>
                                    <p>아직 칭호가 없습니다.</p>
                                </ul>
                            </div>
                        </div>
                        <div className={style.project_area}>
                            <div className={style.complete_project_list}>
                                <h3><i className="fa-solid fa-caret-right"></i> 완료된 프로젝트 목록</h3>
                                <ul className={style.complete_projects}>
                                    <li>일상 기록 애플리케이션</li>
                                </ul>
                            </div>
                            <div className={style.progress_project_list}>
                                <h3><i className="fa-solid fa-caret-right"></i> 진행중인 프로젝트 목록</h3>
                                <ul className={style.progress_projects}>
                                    <li></li>
                                    <p>진행 중인 프로젝트가 없습니다.</p>
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