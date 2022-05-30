import React, { useRef, useState } from 'react';
import PortfolioDeleteButton from './PortfolioDeleteButton';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_youtube.module.css';

const PortfolioYoutube = (props) => {
    const { row, deleteRow } = props;
    const { setYoutube } = props;
    const youtubeIframe = useRef();
    const [youtubeLoading, setYoubuteLoading] = useState(false);

    const inputYoutube = (e) => {
        setYoubuteLoading(true);
        if (e.keyCode === 13) {
            const youtubeURL = e.target.value.replace('watch?v=', 'embed/');
            youtubeIframe.current.src = youtubeURL;
            youtubeIframe.current.style.display = 'block';
            e.target.style.display = 'none';
            setYoutube(e, row, youtubeURL);
        }
        setYoubuteLoading(false);
    }

    if (youtubeLoading) {
        return null;
    }

    return (
        <div className={style.youtube_box}>
            <PortfolioOrder order={row.index} />
            <div className={style.youtube_container}>
                <iframe className={style.youtube} src="" ref={youtubeIframe}>
                </iframe>
                <input className={style.youtube_input} size="100" type="url" placeholder="유튜브 src를 입력하세요" onKeyUp={e => inputYoutube(e)}/>
            </div>
            <PortfolioDeleteButton order={row.index} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioYoutube;