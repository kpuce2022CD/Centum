import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import PostEdit from '../component/board/PostEdit';
import Header from '../component/common/Header';
import AuthenticationService from '../component/security/AuthenticationService';

const PostEditPage = (props) => {
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
            <PostEdit process={process} />
        </>
    );
};

export default PostEditPage;