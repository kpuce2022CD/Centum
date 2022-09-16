import React from 'react';
import PostEdit from '../component/board/PostEdit';
import Header from '../component/common/Header';

const PostEditPage = (props) => {
    const { process } = props;

    return (
        <>
            <Header />
            <PostEdit process={process} />
        </>
    );
};

export default PostEditPage;