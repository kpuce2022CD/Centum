import React from 'react';
import Board from '../component/board/Board';
import Header from '../component/common/Header';

const BoardPage = (props) => {
    const { category } = props;

    return (
        <>
            <Header />
            <Board category={category} />
        </>
    );
};

export default BoardPage;