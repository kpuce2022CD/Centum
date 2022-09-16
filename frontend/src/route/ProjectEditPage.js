import React from 'react';
import Header from '../component/common/Header';
import ProjectEdit from '../component/project/ProjectEdit';

const ProjectEditPage = (props) => {
    const { category } = props;
    
    return (
        <>
            <Header />
            <ProjectEdit category={category} />
        </>
    );
};

export default ProjectEditPage;