import React, { useState } from 'react';
import Header from '../component/common/Header';
import Profile from '../component/member/Profile';
import ProfileAbility from '../component/member/ProfileAbility';
import ProfileArchive from '../component/member/ProfileArchive';
import ProfileEdit from '../component/member/ProfileEdit';
import ProfileRecord from '../component/member/ProfileRecord';

const ProfilePage = () => {
    const [editStatus, setEditStatus] = useState(false);

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