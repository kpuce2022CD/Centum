import React from 'react';
import { useNavigate } from 'react-router-dom';

const MatchingListItem = (props) => {
    const { post, index } = props;
    const navigate = useNavigate();

    return (
        <tr onClick={() => navigate('/board/recruit/' + post.id)}>
            <th>{index}</th>
            <th>{post.projectSubject}</th>
            <th>
                {
                    {
                        1: "하",
                        2: "중",
                        3: "상"
                    }[post.projectLevel]
                }
            </th>
        </tr>
    );
};

export default MatchingListItem;