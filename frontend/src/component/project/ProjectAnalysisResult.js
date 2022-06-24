import React, { useEffect, useRef, useState } from 'react';
import Chart from 'chart.js/auto';
import style from '../../css/project/project_analysis_result.module.css';

const ProjectAnalysisResult = (props) => {
    const { projectSkills } = props;
    const chartRef = useRef();
    const qualityRef = useRef();
    const [projectAbility, setProjectAbility] = useState({});

    useEffect(() => {
        let prevChart = Chart.getChart(chartRef.current);
        if (prevChart !== undefined) {
            prevChart.destroy();
        }
        new Chart(chartRef.current.getContext("2d"), {
            type: 'pie',
            data: {
                labels: projectSkills.map(projectSkill => projectSkill.skillName),
                datasets: [
                    {
                        label: "현재 구현도",
                        fill: true,
                        backgroundColor: projectSkills.map(() => createRandomColor()),
                        borderColor: "rgba(200,222,255,1)",
                        data: projectSkills.map(projectSkill => projectSkill.quantity),
                        hoverOffset: projectSkills.length
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
    }, [projectSkills]);

    useEffect(() => {
        let prevChart = Chart.getChart(qualityRef.current);
        if (prevChart !== undefined) {
            prevChart.destroy();
        }
        new Chart(qualityRef.current.getContext("2d"), {
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
                        data: [projectAbility.cohesion, projectAbility.coupling, projectAbility.complexity, projectAbility.redundancy, projectAbility.standard],
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
    }, [projectAbility]);

    const createRandomColor = () => {
        let r = Math.floor(Math.random() * 255);
        let g = Math.floor(Math.random() * 255);
        let b = Math.floor(Math.random() * 255);
        return "rgba(" + r + "," + g + "," + b + ",0.5)";
    }
    
    if (!projectSkills) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className={style.ability_container}>
                <h2 className={style.ability_title}>
                    프로젝트 분석
                </h2>
                <div className={style.ability_area}>
                    <div className={style.code_chart}>
                        <h3><i className="fa-solid fa-caret-right"></i> 기술 사용도</h3>
                        <canvas ref={chartRef} width="350" height="350"></canvas>
                    </div>
                    <div className={style.quality_chart}>
                        <h3><i className="fa-solid fa-caret-right"></i> 코드 품질</h3>
                        <canvas ref={qualityRef} width="350" height="350"></canvas>
                    </div>
                    <div className={style.stack_area}>
                        <div className={style.skill_stack}>
                            <h3><i className="fa-solid fa-caret-right"></i> 기술 스택</h3>
                            <ul className={style.skills}>
                                {
                                    projectSkills.map(projectSkill => (
                                        <li key={projectSkill.id}>{projectSkill.skillName}</li>
                                    ))
                                }
                                {!projectSkills.length && <p>아직 기술 스택이 없습니다.</p>}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProjectAnalysisResult;