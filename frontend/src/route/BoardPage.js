import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import BoardList from '../component/board/BoardList';
import Header from '../component/common/Header';
import AuthenticationService from '../component/security/AuthenticationService';

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