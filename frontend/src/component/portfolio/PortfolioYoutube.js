import React, { useRef } from 'react';
import style from '../../css/portfolio/portfolio_youtube.module.css';

const PortfolioYoutube = (props) => {
    const { portfolioRow } = props;
    const youtubeIframe = useRef();
    return (
        <div className={style.youtube_box}>
            <div className={style.youtube_container}>
                <iframe className={style.youtube} src={portfolioRow.contents} ref={youtubeIframe}></iframe>
            </div>
        </div>
    );
};

export default PortfolioYoutube;