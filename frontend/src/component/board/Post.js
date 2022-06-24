import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import style from '../../css/board/board_detail.module.css';
import PostComment from './PostComment';
import PostCommentEdit from './PostCommentEdit';
import PostAccess from './PostAccess';
import PostContents from './PostContents';
import PostTitle from './PostTitle';
import instance from '../security/Interceptor';

const Post = (props) => {
    const { category } = props;
    const { id } = useParams();
    const [post, setPost] = useState(null);
    const [commentList, setCommentList] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchBoard = async () => {
            setLoading(true);
            try {
                await instance.get('/api/board/' + category + '/' + id).then(response => {
                    if (response.data.success === 0) {
                        setPost(response.data.data.post);
                        instance.get('/api/board/' + response.data.data.post.id + '/comments').then(response => {
                            console.log(response.data);
                            setCommentList(response.data.data.comments);
                        });
                    }
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchBoard();
    }, []);

    if (loading) {
        return null;
    }

    if (!post || !commentList) {
        return null;
    }
    
    return (
        <section className={style.main}>
            <div className="wrap">
                <PostTitle category={category} post={post}/>
                <PostContents category={category} post={post}/>
                <PostAccess post={post}/>
                <div className={style.comment_container}>
                    <ul className={style.comments}>
                        {
                            commentList.map(comment => (
                                <PostComment key={comment.id} comment={comment} post={post} />
                            ))
                        }
                    </ul>
                    <PostCommentEdit post={post} />
                </div>
            </div>
        </section>
    );
};

export default Post;