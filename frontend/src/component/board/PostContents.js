import React from 'react';
import style from '../../css/board/board_detail_contents.module.css';
import ProjectInfo from './ProjectInfo';

const PostContents = (props) => {
    const { category, post } = props;

    const createMarkup = () => {
        return { __html: post.contents };
    }

    return (
        <div className={style.contents_container}>
            <div className={style.contents} dangerouslySetInnerHTML={createMarkup()}>
               
            </div>
            {category === "recruit" ? <ProjectInfo post={post}/> : null}
        </div>
    );
};

export default PostContents;