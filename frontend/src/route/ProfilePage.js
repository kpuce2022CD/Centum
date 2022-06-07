import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../component/common/Header';
import Profile from '../component/member/Profile';
import ProfileAbility from '../component/member/ProfileAbility';
import ProfileArchive from '../component/member/ProfileArchive';
import ProfileRecord from '../component/member/ProfileRecord';
import AuthenticationService from '../component/security/AuthenticationService';

const ProfilePage = () => {
    const navigate = useNavigate();
    
    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login');
        }
    }, []);

    return (
        <>
            <Header />
            <Profile />
            <ProfileAbility />
            <ProfileArchive />
            <ProfileRecord />
        </>
    );
};

export default ProfilePage;