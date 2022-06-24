import React, { useEffect, useRef, useState } from 'react';
import PortfolioDeleteButton from './PortfolioDeleteButton';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_youtube_edit.module.css';

const PortfolioYoutubeEdit = (props) => {
    const { row, deleteRow } = props;
    const { setYoutube } = props;
    const { process } = props;
    const youtubeIframe = useRef();
    const youtubeInput = useRef();
    const [youtubeLoading, setYoubuteLoading] = useState(false);

    useEffect(() => {
        setYoubuteLoading(true);
        if (process === "modify") {
            youtubeIframe.current.src = row.contents;
            youtubeIframe.current.style.display = 'block';
            youtubeInput.current.style.display = 'none';
        }
        setYoubuteLoading(false);
    }, []);

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
            <PortfolioOrder order={row.rowOrder} />
            <div className={style.youtube_container}>
                <iframe className={style.youtube} src="" ref={youtubeIframe}>
                </iframe>
                <input className={style.youtube_input} size="100" type="url" placeholder="유튜브 URL 입력 후 엔터를 눌러주세요" onKeyUp={e => inputYoutube(e)} ref={youtubeInput} />
            </div>
            <PortfolioDeleteButton order={row.rowOrder} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioYoutubeEdit;