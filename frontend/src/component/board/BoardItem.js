import React from 'react';
import { Link } from 'react-router-dom';
import style from '../../css/board/board_list_item.module.css';

const BoardItem = (props) => {
    const { post, index } = props;
    const { category } = props;
    return (
        <tr>
            <td className={style.td_idx}>{index}</td>
            <td className={style.td_title}>
                <Link to={"/board/" + category + "/" + post.id}>{post.title}</Link>
                <span className={style.td_reply}>[{post.commentTally}]</span>
            </td>
            <td className={style.td_name}>
                <Link to="#">{post.member.nickname}</Link>
            </td>
            <td className={style.td_date}>{post.createdDate.split(' ')[1]}</td>
            <td className={style.td_view}>{post.viewTally}</td>
            <td className={style.td_like}>{post.starTally}</td>
            <td className={style.td_scrap}>{post.scrapTally}</td>
        </tr>
    );
};

export default BoardItem;