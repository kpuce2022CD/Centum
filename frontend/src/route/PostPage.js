import React, { useEffect } from 'react';
import Post from '../component/board/Post';
import Header from '../component/common/Header';

const PostPage = (props) => {
    const { category } = props;

    return (
        <>
            <Header />
            <Post category={category} />
        </>
    );
};

export default PostPage;