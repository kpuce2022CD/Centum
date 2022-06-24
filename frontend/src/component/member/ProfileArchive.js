import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/member/profile_archive.module.css';
import instance from '../security/Interceptor';

const ProfileArchive = () => {
    const navigate = useNavigate();
    const [postScraps, setPostScraps] = useState([]);
    const [portfolioScraps, setPortfolioScraps] = useState([]);
    const [loading, setLoading] = useState(false);
    const postCategory = {
        "free": "free",
        "information": "info",
        "recruitment": "recruit"
    };

    useEffect(() => {
        const fetchPostScrapData = () => {
            setLoading(true);
            try {
                instance.get('/api/members/my/postScraps').then(response => {
                    console.log(response.data.data.postScraps);
                    setPostScraps(response.data.data.postScraps);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchPortfolioScrapData = () => {
            setLoading(true);
            try {
                instance.get('/api/members/my/portfolioScraps').then(response => {
                    console.log(response.data.data.portfolioScraps);
                    setPortfolioScraps(response.data.data.portfolioScraps);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchPostScrapData();
        fetchPortfolioScrapData();
    }, []);

    if (loading) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.archive_container}>
                    <h2 className={style.archive_title}>
                        내 보관함
                    </h2>
                    <div className={style.archive_area}>
                        <div className={style.scrap_portfolio_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 스크랩 한 포트폴리오</h3>
                            <ul className={style.scrap_portfolios}>
                                {
                                    portfolioScraps.map(portfolioScrap => (
                                        <li key={portfolioScrap.id} onClick={() => navigate('/portfolios/' + portfolioScrap.portfolioId)}>{portfolioScrap.portfolioTitle} - {portfolioScrap.portfolioWriterNickname}</li>
                                    ))
                                }
                                {!portfolioScraps.length && <p>스크랩 한 포트폴리오가 없습니다.</p>}
                            </ul>
                        </div>
                        <div className={style.scrap_post_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 스크랩 한 게시글</h3>
                            <ul className={style.scrap_posts}>
                                {
                                    postScraps.map(postScrap => (
                                        <li key={postScrap.id} onClick={() => navigate('/board/' + postCategory[postScrap.postType] + '/' + postScrap.postId)}>{postScrap.postTitle} - {postScrap.postWriterNickname}</li>
                                    ))
                                }
                                {!postScraps.length && <p>스크랩 한 게시글이 없습니다.</p>}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProfileArchive;