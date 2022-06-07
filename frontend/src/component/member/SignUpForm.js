import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/member/signup_form.module.css';

const SignUpForm = (props) => {
    const passwdRef = useRef();
    const passwdCheckRef = useRef();
    const passwdCheckErrorRef = useRef();
    const navigate = useNavigate();
    const [signupData, setSignupData] = useState({
        githubId: ""
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        console.log(signupData);
    }, [signupData]);

    const postSignUpForm = async () => {
        setLoading(true);
        try {
            await axios.post('/api/signup', signupData).then(response => {
                console.log(response.data.data.member);
                if (response.data.success === 0) {
                    navigate('/signup/auth', {
                        state: {
                            email: response.data.data.member.email
                        }
                    });
                }
            })
            // navigate('/signup/auth');
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    if (loading) { 
        return null;
    }

    const setId = (e) => {
        const nextData = {
            ...signupData,
            loginId: e.target.value
        };
        setSignupData(nextData);
    }
    
    const setPassword = () => {
        const nextData = {
            ...signupData,
            passwd: passwdRef.current.value === passwdCheckRef.current.value ? passwdRef.current.value : null
        };
        setSignupData(nextData);

        if (passwdRef.current.value !== passwdCheckRef.current.value) {
            passwdCheckErrorRef.current.style.display = 'block';
        } else {
            passwdCheckErrorRef.current.style.display = 'none';
        }
    }

    const setRealName = (e) => {
        const nextData = {
            ...signupData,
            realName: e.target.value
        };
        setSignupData(nextData);
    }

    const setNickname = (e) => {
        const nextData = {
            ...signupData,
            nickname: e.target.value
        };
        setSignupData(nextData);
    }

    const setPhone = (e) => {
        const nextData = {
            ...signupData,
            phone: e.target.value
        };
        setSignupData(nextData);
    }

    const setEmail = (e) => {
        const nextData = {
            ...signupData,
            email: e.target.value
        };
        setSignupData(nextData);
    }

    const setInterestField = (e) => {
        const nextData = {
            ...signupData,
            interestField: e.target.value
        };
        setSignupData(nextData);
    }

    const setYear = (e) => {
        const nextData = {
            ...signupData,
            year: e.target.value
        };
        setSignupData(nextData);
    }

    const setMonth = (e) => {
        const nextData = {
            ...signupData,
            month: e.target.value
        };
        setSignupData(nextData);
    }

    const setDay = (e) => {
        const nextData = {
            ...signupData,
            day: e.target.value
        };
        setSignupData(nextData);
    }

    const setSex = (e) => {
        const nextData = {
            ...signupData,
            sex: e.target.value
        };
        setSignupData(nextData);
    }

    const setGithubId = (e) => {
        const nextData = {
            ...signupData,
            githubId: e.target.value
        };
        setSignupData(nextData);
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <div className={style.signup_wrap}>
                        <div className={style.signup_header}>
                            <h2>회원가입</h2>
                        </div>
                        <div className={style.id_area}>
                            <h3 className={style.join_title}>아이디</h3>
                            <span className={style.join_box}>
                                <input type="text" className={style.id} maxLength="20" onChange={setId}/>
                            </span>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.pw_area}>
                            <h3 className={style.join_title}>비밀번호</h3>
                            <div className={style.join_box}>
                                <input type="password" className={style.passwd} maxLength="20" ref={passwdRef} onChange={setPassword}/>
                            </div>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.check_pw_area}>
                            <h3 className={style.join_title}>비밀번호 재확인</h3>
                            <div className={style.join_box}>
                                <input type="password" className={style.passwd_check} maxLength="20" ref={passwdCheckRef} onChange={setPassword}/>
                            </div>
                            <span className={style.error_msg} ref={passwdCheckErrorRef}>비밀번호가 일치하지 않습니다.</span>
                        </div>
                        <div className={style.name_area}>
                            <h3 className={style.join_title}>이름</h3>
                            <div className={style.join_box}>
                                <input type="text" className={style.realname} maxLength="20" onChange={setRealName}/>
                            </div>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.nickname_area}>
                            <h3 className={style.join_title}>닉네임</h3>
                            <div className={style.join_box}>
                                <input type="text" className={style.nickname} onChange={setNickname}/>
                            </div>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.phone_area}>
                            <h3 className={style.join_title}>전화번호</h3>
                            <div className={style.join_box}>
                                <input type="number" className={style.phone} onChange={setPhone}/>
                            </div>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.email_area}>
                            <h3 className={style.join_title}>이메일</h3>
                            <div className={[style.join_box, style.email].join(' ')}>
                                <input type="email" className={style.email} onChange={setEmail} />
                            </div>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.interest_area}>
                            <h3 className={style.join_title}>관심 분야</h3>
                            <select className={[style.join_box, style.interest].join(' ')} onChange={setInterestField}>
                                <option value="">관심 분야</option>
                                <option value="frontend">웹/프론트엔드</option>
                                <option value="backend">웹/백엔드</option>
                                <option value="android">앱/안드로이드</option>
                                <option value="ios">앱/IOS</option>
                                <option value="embedded">하드/임베디드</option>
                                <option value="iot">하드/IOT</option>
                                <option value="ai">AI</option>
                                <option value="security">보안</option>
                            </select>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.birthday_area}>
                            <h3 className={style.join_title}>생년월일</h3>
                            <div className={style.birth_wrap}>
                                <div className={style.birth_year}>
                                    <input type="text" className={style.year} placeholder="년" maxLength="4" onChange={setYear}/>
                                </div>
                                <div className={style.birth_month}>
                                    <input type="text" className={style.month} placeholder="월" maxLength="2" onChange={setMonth} />
                                </div>
                                <div className={style.birth_day}>
                                    <input type="text" className={style.day} placeholder="일" maxLength="2" onChange={setDay} />
                                </div>
                            </div>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.gender_area}>
                            <h3 className={style.join_title}>성별</h3>
                            <select className={[style.join_box, style.gender].join(' ')} onChange={setSex}>
                                <option value="">성별</option>
                                <option value="1">남자</option>
                                <option value="2">여자</option>
                            </select>
                            <span className={style.error_msg}>필수 정보입니다.</span>
                        </div>
                        <div className={style.github_area}>
                            <h3 className={style.join_title}>github 아이디<span className={style.choice}>(선택)</span></h3>
                            <div className={style.join_box}>
                                <input type="text" className={style.github} onChange={setGithubId} />
                            </div>
                        </div>
                        <div className={style.signup_area}>
                            <button className={style.signup_btn} onClick={() => postSignUpForm()}>가입하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default SignUpForm;