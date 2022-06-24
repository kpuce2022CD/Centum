import React from 'react';
import style from '../../css/project/project_analysis_popup.module.css';

const ProjectAnalysisPopup = () => {
    return (
        <div className={style.analysis_popup}>
            <div className={style.main_container}>
                <div className={style.loading_image}>
                    <i class="fa-solid fa-spinner"></i>
                </div>
                <h1>분석중입니다. 잠시만 기다려주세요</h1>
            </div>
        </div>
    );
};

export default ProjectAnalysisPopup;