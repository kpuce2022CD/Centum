import React, { useRef, useState } from 'react';
import PortfolioDeleteButton from './PortfolioDeleteButton';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_video.module.css';

const PortfolioVideo = (props) => {
    const { row, deleteRow } = props;
    const { setVideo } = props;
    const videoInput = useRef();
    const [videoLoading, setVideoLoading] = useState(false);

    const loadVideo = (e) => {
        setVideoLoading(true);
        // const formData = new FormData();
        const file = e.target.files[0];
        videoInput.current.src = URL.createObjectURL(file);
        e.target.style.display = "none";
        videoInput.current.style.display = 'block';

        setVideo(e, row, URL.createObjectURL(file));
        setVideoLoading(false);
    };

    if (videoLoading) {
        return null;
    }

    return (
        <div className={style.video_box}>
            <PortfolioOrder order={row.index} />
            <div className={style.video_wrap}>
                <div className={style.video_container}>
                    <div>
                        <input type="file" className={style.video_input} accept="video.mp4" onChange={e => loadVideo(e)}/>
                    </div>
                </div>
                <video className={style.video} autoPlay controls loop muted preload="none" ref={videoInput}>
                    <source src="" type="video/mp4" />
                    브라우저가 동영상 업로드를 지원하지 않습니다
                </video>
            </div>
            <PortfolioDeleteButton order={row.index} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioVideo;