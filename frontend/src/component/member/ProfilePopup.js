import React, { useRef, useState } from 'react';
import style from '../../css/member/profile_popup.module.css';
import instance from '../security/Interceptor';

const ProfilePopup = (props) => {
    const { setPopup } = props;
    const { member, setMember } = props;
    const personalTokenRef = useRef();
    const [loading, setLoading] = useState(false);

    const postPersonalToken = () => {
        setLoading(true);
        try {
            instance.post('/api/members/my/personalToken', member).then(response => {
                console.log(response.data);
                setPopup(false);
            })
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    if (loading) {
        return null;
    }

    return (
        <div className={style.profile_popup}>
            <div className={style.profile_popup_header}>
                <button className={style.exit_button} onClick={() => setPopup(false)}><i className="fa-solid fa-xmark"></i></button>
            </div>
            <div className={style.main_container}>
                <textarea className={style.personal_token_input} rows="1" defaultValue={member.personalToken} ref={personalTokenRef} onChange={e => setMember({...member, personalToken: e.target.value})}></textarea>
                <div>Personal Token을 입력해주세요.</div>
            </div>
            <div className={style.access_container}>
                <button className={style.cancel_button} onClick={() => setPopup(false)}>취소</button>
                <button className={style.complete_button} onClick={() => postPersonalToken()}>확인</button>
            </div>
        </div>
    );
};

export default ProfilePopup;