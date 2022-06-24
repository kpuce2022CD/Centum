import React, { useRef } from 'react';
import style from '../../css/portfolio/portfolio_video.module.css';

const PortfolioVideo = (props) => {
    const { portfolioRow } = props;
    const videoInput = useRef();
    return (
        <div className={style.video_box}>
            <div className={style.video_wrap}>
                <video className={style.video} src={portfolioRow.contents} autoPlay controls loop muted preload="none" ref={videoInput}>
                    <source src="" type="video/mp4" />
                    브라우저가 동영상 업로드를 지원하지 않습니다
                </video>
            </div>
        </div>
    );
};

export default PortfolioVideo;