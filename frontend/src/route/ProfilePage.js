import React from 'react';
import Header from '../component/common/Header';
import Profile from '../component/member/Profile';
import ProfileAbility from '../component/member/ProfileAbility';
import ProfileArchive from '../component/member/ProfileArchive';
import ProfileRecord from '../component/member/ProfileRecord';

const ProfilePage = () => {
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