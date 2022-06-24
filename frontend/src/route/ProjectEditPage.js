import React, { useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import Header from '../component/common/Header';
import ProjectEdit from '../component/project/ProjectEdit';
import AuthenticationService from '../component/security/AuthenticationService';

const ProjectEditPage = (props) => {
    const { id } = useParams();
    const { category } = props;
    const navigate = useNavigate();
    

    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login?redirect=/projects/' + category + '/modify/' + id);
        }
    }, []);
    
    return (
        <>
            <Header />
            <ProjectEdit category={category} id={id} />
        </>
    );
};

export default ProjectEditPage;