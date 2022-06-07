import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import BoardEdit from '../component/board/BoardEdit';
import Header from '../component/common/Header';
import AuthenticationService from '../component/security/AuthenticationService';

const BoardEditPage = (props) => {
    const { process } = props;
    const navigate = useNavigate();

    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login');
        }
    }, []);

    return (
        <>
            <Header />
            <BoardEdit process={process} />
        </>
    );
};

export default BoardEditPage;