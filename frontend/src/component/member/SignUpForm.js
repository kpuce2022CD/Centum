import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import style from '../../css/member/signup_form.module.css';

const SignUpForm = (props) => {
    const passwdRef = useRef();
    const passwdCheckRef = useRef();
    const loginIdErrorRef = useRef();
    const passwdCheckErrorRef = useRef();
    const realNameErrorRef = useRef();
    const nicknameErrorRef = useRef();
    const emailErrorRef = useRef();
    const yearRef = useRef();
    const monthRef = useRef();
    const dateRef = useRef();
    const location = useLocation();
    const state = location.state;
    const navigate = useNavigate();
    const [signupData, setSignupData] = useState({
        sex: "0",
        interestField: "",
        phone: ""
    });
    const [validateLoginId, setValidateLoginId] = useState(false);
    const [validatePasswd, setValidatePasswd] = useState(false);
    const [validateRealName, setValidateRealName] = useState(false);
    const [validateNickname, setValidateNickname] = useState(false);
    const [validateEmail, setValidateEmail] = useState(false);
    const [allowSignup, setAllowSignup] = useState(false);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const checkTermChecked = () => {
            if (!state) {
                alert('잘못된 접근입니다.');
                navigate('/');
                return;
            }

            if (!state.termChecked) {
                alert('약관을 동의해주세요.');
                navigate(-1);
            }
        }
        checkTermChecked();
    }, []);

    useEffect(() => {
        console.log(signupData);
    }, [signupData]);


    useEffect(() => {
        console.table({
            validateEmail: validateEmail,
            validateLoginId: validateLoginId,
            validateNickname: validateNickname,
            validatePasswd: validatePasswd,
            validateRealName: validateRealName
        })

        setAllowSignup(validateLoginId && validatePasswd && validateRealName && validateNickname && validateEmail);
    }, [validateEmail, validateLoginId, validateNickname, validatePasswd, validateRealName]);
    useEffect(() => {
        console.log(allowSignup);
    }, [allowSignup]);

    const postSignUpForm = async () => {
        setLoading(true);
        try {
            if (allowSignup) {
                await axios.post('/api/members/signup', signupData).then(response => {
                    console.log(response.data);
                    if (response.data.success === 0) {
                        navigate('/signup/auth', {
                            state: {
                                email: response.data.data.member.email
                            }
                        });
                    }
                })
                setAllowSignup(false);
            }
            // navigate('/signup/auth');
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    const checkLoginIdDuplication = (e) => {
        try {
            axios.post('/api/members/loginId', {loginId: e.target.value}).then(response => {
                if (response.data.success === 0) {
                    if (response.data.data.memberDuplication.isExist) {
                        loginIdErrorRef.current.style.display = 'block';
                    } else {
                        loginIdErrorRef.current.style.display = 'none';
                    }
                    setValidateLoginId(!response.data.data.memberDuplication.isExist && Boolean(e.target.value));
                }
            })
        } catch (e) {
            console.log(e);
        }
    }

    const setId = (e) => {
        const nextData = {
            ...signupData,
            loginId: e.target.value
        };
        setSignupData(nextData);
    }
    
    const setPassword = () => {
        const passwdValue = passwdRef.current.value === passwdCheckRef.current.value ? passwdRef.current.value : null
        const nextData = {
            ...signupData,
            passwd: passwdValue
        };
        setSignupData(nextData);

        if (passwdValue) {
            passwdCheckErrorRef.current.style.display = 'none';
            setValidatePasswd(true);
        } else {
            passwdCheckErrorRef.current.style.display = 'block';
            setValidatePasswd(false);
        }
    }

    const setRealName = (e) => {
        const nextData = {
            ...signupData,
            realName: e.target.value
        };
        setSignupData(nextData);

        if (e.target.value) {
            realNameErrorRef.current.style.display = 'none';
            setValidateRealName(true);
        } else {
            realNameErrorRef.current.style.display = 'block';
            setValidateRealName(false);
        }
    }

    const setNickname = (e) => {
        const nextData = {
            ...signupData,
            nickname: e.target.value
        };
        setSignupData(nextData);

        if (e.target.value) {
            nicknameErrorRef.current.style.display = 'none';
            setValidateNickname(true);
        } else {
            nicknameErrorRef.current.style.display = 'block';
            setValidateNickname(false);
        }
    }

    const setEmail = (e) => {
        const nextData = {
            ...signupData,
            email: e.target.value
        };
        setSignupData(nextData);

        if (e.target.value) {
            emailErrorRef.current.style.display = 'none';
            setValidateEmail(true);
        } else {
            emailErrorRef.current.style.display = 'block';
            setValidateEmail(false);
        }
    }

    const setPhone = (e) => {
        const nextData = {
            ...signupData,
            phone: e.target.value
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

    const setBirth = () => {
        const nextData = {
            ...signupData,
            birth: yearRef.current.value && monthRef.current.value && dateRef.current.value ?
                String(yearRef.current.value).padStart(4, '0') + '.' + String(monthRef.current.value).padStart(2, '0') + '.' + String(dateRef.current.value).padStart(2, '0') : null
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

    if (loading) { 
        return null;
    }

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.main_container}>
                    <div className={style.signup_wrap}>
                        <div className={style.required_area}>
                            <div className={style.id_area}>
                                <h3 className={style.join_title}>아이디<span className={style.required}>*</span></h3>
                                <div className={style.join_box}>
                                    <input type="text" className={style.id} maxLength="20" onChange={setId} onBlur={checkLoginIdDuplication}/>
                                </div>
                                <span className={style.error_msg} ref={loginIdErrorRef}>이미 존재하는 ID 입니다.</span>
                            </div>
                            <div className={style.pw_area}>
                                <h3 className={style.join_title}>비밀번호<span className={style.required}>*</span></h3>
                                <div className={style.join_box}>
                                    <input type="password" className={style.passwd} maxLength="20" ref={passwdRef} onChange={setPassword}/>
                                </div>
                                <span className={style.error_msg}>필수 정보입니다.</span>
                            </div>
                            <div className={style.check_pw_area}>
                                <h3 className={style.join_title}>비밀번호 재확인<span className={style.required}>*</span></h3>
                                <div className={style.join_box}>
                                    <input type="password" className={style.passwd_check} maxLength="20" ref={passwdCheckRef} onChange={setPassword}/>
                                </div>
                                <span className={style.error_msg} ref={passwdCheckErrorRef}>비밀번호가 일치하지 않습니다.</span>
                            </div>
                            <div className={style.name_area}>
                                <h3 className={style.join_title}>이름<span className={style.required}>*</span></h3>
                                <div className={style.join_box}>
                                    <input type="text" className={style.realname} maxLength="20" onChange={setRealName}/>
                                </div>
                                <span className={style.error_msg} ref={realNameErrorRef}>필수 정보입니다.</span>
                            </div>
                            <div className={style.nickname_area}>
                                <h3 className={style.join_title}>닉네임<span className={style.required}>*</span></h3>
                                <div className={style.join_box}>
                                    <input type="text" className={style.nickname} onChange={setNickname}/>
                                </div>
                                <span className={style.error_msg} ref={nicknameErrorRef}>필수 정보입니다.</span>
                            </div>
                            <div className={style.email_area}>
                                <h3 className={style.join_title}>이메일<span className={style.required}>*</span></h3>
                                <div className={[style.join_box, style.email].join(' ')}>
                                    <input type="email" className={style.email} onChange={setEmail} />
                                </div>
                                <span className={style.error_msg} ref={emailErrorRef}>필수 정보입니다.</span>
                            </div>
                        </div>
                        <div className={style.optional_area}>
                            <div className={style.phone_area}>
                                <h3 className={style.join_title}>전화번호</h3>
                                <div className={style.join_box}>
                                    <input type="number" className={style.phone} onChange={setPhone}/>
                                </div>
                                <span className={style.error_msg}>형식이 잘못되었습니다.</span>
                            </div>
                            <div className={style.birthday_area}>
                                <h3 className={style.join_title}>생년월일</h3>
                                <div className={style.birth_wrap}>
                                    <div className={style.birth_year}>
                                        <input type="text" className={style.year} placeholder="년" maxLength="4" onChange={setBirth} ref={yearRef} />
                                    </div>
                                    <div className={style.birth_month}>
                                        <input type="text" className={style.month} placeholder="월" maxLength="2" onChange={setBirth} ref={monthRef} />
                                    </div>
                                    <div className={style.birth_day}>
                                        <input type="text" className={style.day} placeholder="일" maxLength="2" onChange={setBirth} ref={dateRef} />
                                    </div>
                                </div>
                                <span className={style.error_msg}>필수 정보입니다.</span>
                            </div>
                            <div className={style.interest_area}>
                                <h3 className={style.join_title}>관심 분야</h3>
                                <div className={style.join_box}>
                                    <select className={style.interest} onChange={setInterestField}>
                                        <option value="">관심 분야</option>
                                        <option value="웹/프론트엔드">웹/프론트엔드</option>
                                        <option value="웹/백엔드">웹/백엔드</option>
                                        <option value="앱/안드로이드">앱/안드로이드</option>
                                        <option value="앱/IOS">앱/IOS</option>
                                        <option value="임베디드">임베디드</option>
                                        <option value="IOT">IOT</option>
                                        <option value="AI">AI</option>
                                        <option value="보안">보안</option>
                                    </select>
                                </div>
                                <span className={style.error_msg}>필수 정보입니다.</span>
                            </div>
                            <div className={style.gender_area}>
                                <h3 className={style.join_title}>성별</h3>
                                <div className={style.join_box}>
                                    <button onClick={e => setSex(e)} className={signupData.sex === "1" ? style.sex_clicked : null} value="1">남자</button>
                                    <button onClick={e => setSex(e)} className={signupData.sex === "2" ? style.sex_clicked : null} value="2">여자</button>
                                </div>
                                <span className={style.error_msg}>필수 정보입니다.</span>
                            </div>
                        </div>
                        <div className={style.access_area}>
                            <button className={style.signup_btn} onClick={() => postSignUpForm()} disabled={!allowSignup}>가입하기</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default SignUpForm;