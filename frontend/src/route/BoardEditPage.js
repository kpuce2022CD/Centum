import React from 'react';
import BoardEdit from '../component/board/BoardEdit';
import Header from '../component/common/Header';

const BoardEditPage = (props) => {
    const { process } = props;
    return (
        <>
            <Header />
            <BoardEdit process={process} />
        </>
    );
};

export default BoardEditPage;