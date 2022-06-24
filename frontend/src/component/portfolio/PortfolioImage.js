import React, { useRef } from 'react';
import style from '../../css/portfolio/portfolio_image.module.css';

const PortfolioImage = (props) => {
    const { portfolioRow } = props;
    const imageInput = useRef();

    return (
        <div className={style.image_box}>
            <div className={style.image_wrap}>
                <div className={style.image_container}>
                    <img className={style.image_show} src={portfolioRow.contents} alt="" ref={imageInput}/>
                </div>
            </div>
        </div>
    );
};

export default PortfolioImage;