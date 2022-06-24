import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Header from '../component/common/Header';
import ProjectDetail from '../component/project/ProjectDetail';
import AuthenticationService from '../component/security/AuthenticationService';

const ProjectDetailPage = (props) => {
    const { id } = useParams();
    const { category } = props;
    const navigate = useNavigate();
    
    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login?redirect=/projects/' + category + '/' + id);
        }
    }, []);
    
    return (
        <>
            <Header />
            <ProjectDetail category={category} id={id} />
        </>
    );
};

export default ProjectDetailPage;