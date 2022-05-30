import React, { useRef, useState } from 'react';
import PortfolioDeleteButton from './PortfolioDeleteButton';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_image.module.css';

const PortfolioImage = (props) => {
    const { row, deleteRow } = props;
    const { setImage } = props;
    const imageInput = useRef();
    const [imageLoading, setImageLoading] = useState(false);

    const loadImage = (e) => {
        setImageLoading(true);
        // const formData = new FormData();
        const file = e.target.files[0];
        imageInput.current.src = URL.createObjectURL(file);
        e.target.style.display = "none";

        setImage(e, row, URL.createObjectURL(file));
        setImageLoading(false);
    };

    if (imageLoading) {
        return null;
    }

    return (
        <div className={style.image_box}>
            <PortfolioOrder order={row.index} />
            <div className={style.image_wrap}>
                <div className={style.image_container}>
                    <div className={style.image_upload}>
                        <div>
                            <input multiple="multiple" type="file" className={style.choose_file} accept="image/*" onChange={e => loadImage(e)}/>
                            <p className={style.file_name}></p>
                        </div>
                    </div>
                    <img className={style.image_show} src="" alt="" ref={imageInput}/>
                </div>
            </div>
            <PortfolioDeleteButton order={row.index} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioImage;