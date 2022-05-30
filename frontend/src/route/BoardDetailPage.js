import React from 'react';
import { useParams } from 'react-router-dom';
import BoardDetail from '../component/board/BoardDetail';
import Header from '../component/common/Header';

const BoardDetailPage = (props) => {
    const { category } = props;
    const { id } = useParams();
    return (
        <>
            <Header />
            <BoardDetail category={category} id={id} />
        </>
    );
};

export default BoardDetailPage;