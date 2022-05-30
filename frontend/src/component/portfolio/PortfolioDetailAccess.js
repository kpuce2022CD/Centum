import React from 'react';
import DefaultMemberImage from '../../image/member.png';
import style from '../../css/portfolio/portfolio_detail_access.module.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const PortfolioDetailAccess = (props) => {
    const { portfolio } = props;
    const navigate = useNavigate();

    const deletePortfolio = async () => {
        try {
            await axios.delete('/api/portfolio/' + portfolio.id).then(response => {
                console.log(response.data);
            })
        } catch (e) {
            console.log(e);
        }
        navigate(-1);
    };

    return (
        <div className={style.access_area}>
            <div className={style.member_info}>
                <img className={style.member_img} src={DefaultMemberImage}/>
                <p className={style.member_nickname}>{portfolio.member.nickname}</p>
            </div>
            <ul className={style.portfolio_icons}>
                <li className={style.portfolio_star}>
                    <i className="far fa-star"></i>
                </li>
                <li className={style.portfolio_scrap}>
                    <i className="far fa-bookmark"></i>
                </li>
                <li className={style.send_email}>
                    <i className="far fa-envelope"></i>
                </li>
                <li className={style.send_comment}>
                    <i className="far fa-comment"></i>
                </li>
            </ul>
            <div className={style.public_status}>
                {
                    {
                        true: <div className={style.public_mark}>public</div>,
                        false: <div className={style.private_mark}>private</div>
                    }[portfolio.visibility]
                }
            </div>
            <div className={style.manage_area}>
                <button className={style.modify_btn} onClick={() => navigate('/portfolio/modify/' + portfolio.id)}>수정</button>
                <button className={style.delete_btn} onClick={() => deletePortfolio()}>삭제</button>
            </div>
            <div className={style.updated_info}>
                최근 수정일 : <span className={style.updated_date}>{portfolio.updatedDate}</span>
            </div>
        </div>
    );
};

export default PortfolioDetailAccess;