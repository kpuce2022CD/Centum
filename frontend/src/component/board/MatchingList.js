import React, { useEffect, useState } from 'react';
import MatchingListItem from './MatchingListItem';
import style from '../../css/board/matching_list.module.css';
import instance from '../security/Interceptor';

const MatchingList = (props) => {
    const { board } = props;
    const { member } = props;
    const [memberAbility, setMemberAbility] = useState({});
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchMemberData = () => {
            setLoading(true);
            try {
                instance.get('/api/members/my/ability').then(response => {
                    setMemberAbility(response.data.data.memberAbility);
                });
            } catch (e) {
                console.log(e);
            }
            setLoading(false);
        };
        fetchMemberData();
    }, []);

    useEffect(() => {
        console.log(memberAbility);
        console.log(board);
    }, [memberAbility]);

    if (loading) {
        return null;
    }

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
                    {
                        board.map((post, index) => {
                            if (memberAbility.memberLevel >= post.requiredMemberLevel && post.visibility && post.member.id !== member.id) {
                                return (<MatchingListItem key={post.id} post={post} index={index+1} />);
                            }
                        })
                    }
                </tbody>
            </table>
        </div>
    );
};

export default MatchingList;