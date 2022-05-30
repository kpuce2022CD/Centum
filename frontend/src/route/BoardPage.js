import React from 'react';
import BoardList from '../component/board/BoardList';
import Header from '../component/common/Header';

const BoardPage = (props) => {
    const { category } = props;
    return (
        <>
            <Header />
            <BoardList category={category} />
        </>
    );
};

export default BoardPage;