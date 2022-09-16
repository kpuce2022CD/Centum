import React from 'react';
import Header from '../component/common/Header';
import ProjectDetail from '../component/project/ProjectDetail';

const ProjectDetailPage = (props) => {
    const { category } = props;
    
    return (
        <>
            <Header />
            <ProjectDetail category={category} />
        </>
    );
};

export default ProjectDetailPage;