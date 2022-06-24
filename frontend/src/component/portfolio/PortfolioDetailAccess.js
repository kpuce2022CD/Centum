import React, { useState } from 'react';
import DefaultMemberImage from '../../image/member.png';
import style from '../../css/portfolio/portfolio_detail_access.module.css';
import { Link, useNavigate } from 'react-router-dom';
import instance from '../security/Interceptor';

const PortfolioDetailAccess = (props) => {
    const { portfolio } = props;
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const deletePortfolio = async () => {
        setLoading(true);
        try {
            await instance.delete('/api/portfolios/' + portfolio.id).then(response => {
                console.log(response.data);
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
        navigate('/portfolios');
    };

     const countStar = () => {
        setLoading(true);
        try {
            instance.get('/api/portfolios/' + portfolio.id + '/star').then(response => {
                console.log(response.data);
                if (response.data.data.portfolioStar === null) {
                    alert("추천을 취소했습니다.");
                } else {
                    alert("포트폴리오를 추천했습니다.");
                }
                window.location.reload();
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    const countScrap = () => {
        setLoading(true);
        try {
            instance.get('/api/portfolios/' + portfolio.id + '/scrap').then(response => {
                console.log(response.data);
                if (response.data.data.portfolioScrap === null) {
                    alert("스크랩이 취소되었습니다.");
                } else {
                    alert("스크랩 목록에 추가되었습니다.");
                }
                window.location.reload();
            });
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    if (loading) {
        return null;
    }

    return (
        <div className={style.access_area}>
            <div className={style.member_info}>
                <img className={style.member_img} src={DefaultMemberImage}/>
                <p className={style.member_nickname}>{portfolio.member.nickname}</p>
            </div>
            <ul className={style.portfolio_icons}>
                <li className={style.portfolio_star} onClick={() => countStar()}>
                    <i className="far fa-star"></i>
                </li>
                <li className={style.portfolio_scrap} onClick={() => countScrap()}>
                    <i className="far fa-bookmark"></i>
                </li>
                <li className={style.send_email}>
                    <a href={"mailto: " + portfolio.member.email}><i className="far fa-envelope"></i></a>
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
                <button className={style.modify_btn} onClick={() => navigate('/portfolios/modify/' + portfolio.id)}>수정</button>
                <button className={style.delete_btn} onClick={() => deletePortfolio()}>삭제</button>
            </div>
            <div className={style.updated_info}>
                최근 수정일 : <span className={style.updated_date}>{portfolio.updatedDate}</span>
            </div>
        </div>
    );
};

export default PortfolioDetailAccess;