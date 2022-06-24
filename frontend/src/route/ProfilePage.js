import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../component/common/Header';
import Profile from '../component/member/Profile';
import ProfileAbility from '../component/member/ProfileAbility';
import ProfileArchive from '../component/member/ProfileArchive';
import ProfileEdit from '../component/member/ProfileEdit';
import ProfileRecord from '../component/member/ProfileRecord';
import AuthenticationService from '../component/security/AuthenticationService';

const ProfilePage = () => {
    const navigate = useNavigate();
    const [editStatus, setEditStatus] = useState(false);
    
    useEffect(() => {
        if (!AuthenticationService.isUserLoggedIn()) {
            navigate('/login');
        }
    }, []);

    return (
        <>
            <Header />
            {!editStatus && <Profile setEditStatus={setEditStatus} />}
            {editStatus && <ProfileEdit setEditStatus={setEditStatus} />}
            <ProfileAbility />
            <ProfileArchive />
            <ProfileRecord />
        </>
    );
};

export default ProfilePage;