import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import BoardDetail from '../component/board/BoardDetail';
import Header from '../component/common/Header';
import AuthenticationService from '../component/security/AuthenticationService';

const BoardDetailPage = (props) => {
    const { category } = props;
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login?redirect=/board/' + category + '/' + id);
        }
    }, []);

    return (
        <>
            <Header />
            <BoardDetail category={category} id={id} />
        </>
    );
};

export default BoardDetailPage;