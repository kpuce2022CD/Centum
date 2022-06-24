import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/member/profile_record.module.css';
import instance from '../security/Interceptor';

const ProfileRecord = () => {
    const navigate = useNavigate();
    const [posts, setPosts] = useState([]);
    const [comments, setComments] = useState([]);
    const [loading, setLoading] = useState(false);
    const postCategory = {
        "free": "free",
        "information": "info",
        "recruitment": "recruit"
    };

    useEffect(() => {
        const fetchPostData = () => {
            setLoading(true);
            try {
                instance.get('/api/members/my/posts').then(response => {
                    console.log(response.data.data.posts);
                    setPosts(response.data.data.posts);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        const fetchCommentData = () => {
            setLoading(true);
            try {
                instance.get('/api/members/my/comments').then(response => {
                    console.log(response.data.data.comments);
                    setComments(response.data.data.comments);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchPostData();
        fetchCommentData();
    }, []);

    if (loading) {
        return null;
    }
    
    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.record_container}>
                    <h2 className={style.record_title}>
                        내 기록
                    </h2>
                    <div className={style.record_area}>
                        <div className={style.post_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 내 게시글</h3>
                            <ul className={style.posts}>
                                {
                                    posts.map(post => (
                                        <li key={post.id} onClick={() => navigate('/board/' + postCategory[post.postType] + '/' + post.id)}>{post.title}</li>
                                    ))
                                }
                                {!posts.length && <p>게시글이 없습니다.</p>}
                            </ul>
                        </div>
                        <div className={style.comment_list}>
                            <h3><i className="fa-solid fa-caret-right"></i> 내 댓글</h3>
                            <ul className={style.comments}>
                                {
                                    comments.map(comment => (
                                        <li key={comment.id} onClick={() => navigate('/board/' + postCategory[comment.postType] + '/' + comment.postId)} >{comment.contents}</li>
                                    ))
                                }
                                {!comments.length && <p>댓글이 없습니다.</p>}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default ProfileRecord;