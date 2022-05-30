import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import style from '../../css/board/board_detail.module.css';
import BoardComment from './BoardComment';
import BoardCommentEdit from './BoardCommentEdit';
import BoardDetailAccess from './BoardDetailAccess';
import BoardDetailContents from './BoardDetailContents';
import BoardDetailTitle from './BoardDetailTitle';

const BoardDetail = (props) => {
    const { category } = props;
    const { id } = useParams();
    const [post, setPost] = useState(null);
    const [commentList, setCommentList] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchBoard = async () => {
            setLoading(true);
            try {
                await axios.get('/api/board/' + category + '/' + id).then(response => {
                    if (response.data.resultCode === 0) {
                        setPost(response.data.data.post);
                        axios.get('/api/board/comment/' + response.data.data.post.id).then(response => {
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
                <BoardDetailTitle category={category} post={post}/>
                <BoardDetailContents category={category} post={post}/>
                <BoardDetailAccess post={post}/>
                <div className={style.comment_container}>
                    <ul className={style.comments}>
                        {
                            commentList.map(comment => (
                                <BoardComment key={comment.id} comment={comment}/>
                            ))
                        }
                    </ul>
                    <BoardCommentEdit />
                </div>
            </div>
        </section>
    );
};

export default BoardDetail;