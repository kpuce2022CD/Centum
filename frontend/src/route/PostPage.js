import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Post from '../component/board/Post';
import Header from '../component/common/Header';
import AuthenticationService from '../component/security/AuthenticationService';

const PostPage = (props) => {
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
            <Post category={category} id={id} />
        </>
    );
};

export default PostPage;