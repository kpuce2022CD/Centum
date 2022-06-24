import React, { useEffect, useRef, useState } from 'react';
import PortfolioDeleteButton from './PortfolioDeleteButton';
import PortfolioOrder from './PortfolioOrder';
import style from '../../css/portfolio/portfolio_image_edit.module.css';

const PortfolioImageEdit = (props) => {
    const { row, deleteRow } = props;
    const { setImage } = props;
    const { process } = props;
    const imageRef = useRef();
    const imageInput = useRef();
    const [imageLoading, setImageLoading] = useState(false);

    useEffect(() => {
        if (process === "modify") {
            imageInput.current.style.display = "none";
        }
    })

    const loadImage = (e) => {
        setImageLoading(true);
        // const formData = new FormData();
        const file = e.target.files[0];
        imageRef.current.src = URL.createObjectURL(file);
        e.target.style.display = "none";

        setImage(e, row, URL.createObjectURL(file));
        setImageLoading(false);
    };

    if (imageLoading) {
        return null;
    }

    return (
        <div className={style.image_box}>
            <PortfolioOrder order={row.rowOrder} />
            <div className={style.image_wrap}>
                <div className={style.image_container}>
                    <div className={style.image_upload}>
                        <div>
                            <input multiple="multiple" type="file" className={style.choose_file} accept="image/*" onChange={e => loadImage(e)} ref={imageInput} />
                            <p className={style.file_name}></p>
                        </div>
                    </div>
                    <img className={style.image_show} src={row.contents} alt="" ref={imageRef}/>
                </div>
            </div>
            <PortfolioDeleteButton order={row.rowOrder} deleteRow={deleteRow}/>
        </div>
    );
};

export default PortfolioImageEdit;