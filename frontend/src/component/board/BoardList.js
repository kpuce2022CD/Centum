import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import BoardListItem from './BoardListItem';
import style from '../../css/board/board_list.module.css';
import BoardTitle from './BoardTitle';
import MatchingList from './MatchingList';
import axios from 'axios';
import instance from '../security/Interceptor';

const BoardList = (props) => {
    const { category } = props;
    const navigate = useNavigate();
    const [board, setBoard] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchBoard = async () => {
            setLoading(true);
            try {
                await instance.get('/api/board/' + category).then(response => {
                    setBoard(response.data.data.board);
                })
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        }
        fetchBoard();
    }, [category]);

    if (loading) {
        return null;
    }

    if (!board) {
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <BoardTitle category={category}/>
                <div className={style.board_container}>
                    <table className={style.board}>
                        <colgroup>
                            <col style={{ width: "70px" }}/>
                            <col/>
                            <col style={{ width: "110px" }}/>
                            <col style={{ width: "100px" }}/>
                            <col style={{ width: "80px" }}/>
                            <col style={{ width: "70px" }}/>
                            <col style={{ width: "70px" }}/>
                        </colgroup>
                        <thead>
                            <tr className={style.board_head}>
                                <th>#</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회</th>
                                <th>추천</th>
                                <th>스크랩</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                board.map((post,index) => (
                                    <BoardListItem key={post.id} post={post} index={index + 1} category={category}/>
                                ))
                            }
                        </tbody>
                    </table>
                    {category === "recruit" ? <MatchingList /> : null}
                </div>
                <div className={style.access_container}>
                    <button className={style.post_btn} type="button" title="글쓰기" onClick={() => navigate('/board/create', { state: { category: category } })}>
                        <i className="far fa-edit"></i>
                        <span>글쓰기</span>
                    </button>
                </div>
            </div>
        </section>
    );
};

export default BoardList;