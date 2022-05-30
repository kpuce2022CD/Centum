import React from 'react';
import MatchingListItem from './MatchingListItem';
import style from '../../css/board/matching_list.module.css';

const MatchingList = () => {
    return (
        <div className={style.matching_list_area}>
            <div className={style.matching_list_title}>
                <p>나에게 맞는 프로젝트</p>
            </div>
            <table className={style.matching_list}>
                <colgroup>
                    <col style={{width: "40px"}}/>
                    <col/>
                    <col style={{width: "60px"}}/>
                </colgroup>
                <thead>
                <tr className={style.matching_list_head}>
                    <th>#</th>
                    <th>주제</th>
                    <th>난이도</th>
                </tr>
                </thead>
                <tbody>
                    <MatchingListItem />
                </tbody>
            </table>
        </div>
    );
};

export default MatchingList;