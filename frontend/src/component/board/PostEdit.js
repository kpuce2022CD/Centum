import React, { useEffect, useRef, useState } from 'react';
import style from '../../css/board/board_edit.module.css';
import PostEditSetting from './PostEditSetting';
import ProjectEdit from './ProjectEdit';
import instance from '../security/Interceptor';
import { useLocation, useNavigate, useParams } from 'react-router-dom';

const PostEdit = (props) => {
    const { process } = props;
    const { id } = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const { category } = location.state;
    const contentsRef = useRef();
    const [postStatus, setPostStatus] = useState("");
    const [post, setPost] = useState({
        title: "",
        visibility: true,
        commentAllow: true,
        scrapAllow: false,
        starTally: 0,
        scrapTally: 0,
        viewTally: 0,
        commentTally: 0
    });
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setPostStatus(category);
        setLoading(true);
        if (process === "modify") {
            instance.get('/api/board/' + category + '/' + id).then(response => {
                let { member, ...updatedPost } = response.data.data.post;
                setPost(updatedPost);
                contentsRef.current.innerHTML = response.data.data.post.contents;
                setPostStatus(category);
            });
        }
        setLoading(false);
    }, []);

    useEffect(() => {
        if (postStatus === "recruit") {
            const today = new Date();
            const nextBoard = {
                ...post,
                autoMatchingStatus: false,
                deadlineDate: today.getFullYear() + "." + String(today.getMonth() + 1).padStart(2, '0') + "." + String(today.getDate()).padStart(2, '0') + " 00:00:00",
                memberTotal: 1,
                projectSubject: "",
                projectField: "앱/안드로이드",
                projectLevel: 1,
                requiredMemberLevel: 0,
                expectedPeriod: "1개월 이하"
            }
            setPost(nextBoard);
        }
    }, [postStatus]);

    useEffect(() => {
        console.log(post);
    }, [post]);

    const titleOnChange = (e) => {
        const nextBoard = {
            ...post,
            title: e.target.value
        };
        setPost(nextBoard);
    }

    const checkEnter = (e) => {
        if (e.keyCode === 13) {
            e.preventDefault();
        }
    }

    const contentsOnKeyDown = (e) => {
        const nextBoard = {
            ...post,
            contents: e.target.innerHTML
        };
        setPost(nextBoard);
    }

    const postPostData = () => {
        setLoading(true);
        try {
            instance.post('/api/board/' + category, post).then(response => {
                console.log(response.data);
                if (response.data.success === 0) {
                    navigate('/board/' + category + '/' + response.data.data.post.id);
                }
            })
            // navigate('/signup/auth');
        } catch (e) {
            console.log(e);
        }
        setLoading(false);
    }

    const modifyPostData = () => {
        setLoading(true);
        try {
            instance.put('/api/board/' + category + '/' + id, post).then(response => {
                console.log(response.data);
                if (response.data.success === 0) {
                    navigate('/board/' + category + '/' + response.data.data.post.id);
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

    return (
        <section className={style.main}>
            <div className="wrap">
                <div className={style.edit_title_container}>
                    <h2 className={style.edit_title}>게시글 작성</h2>
                </div>
                <div className={style.edit_head_container}>
                    <select className={style.category} value={postStatus} onChange={e => setPostStatus(e.target.value)} readOnly>
                        <option value="free">자유 게시판</option>
                        <option value="info">정보 게시판</option>
                        <option value="recruit">구인 게시판</option>
                    </select>
                </div>
                <div className={style.edit_container}>
                    <textarea className={style.post_title} cols="30" rows="1" placeholder="제목을 입력해 주세요." maxLength="60" onChange={titleOnChange} onKeyDown={checkEnter} defaultValue={post.title}></textarea>
                    <div className={style.edit_tool}>
                        <select className={style.tool_fontsize}>
                            <option value="">폰트 사이즈</option>
                            <option value="1">10px</option>
                            <option value="2">13px</option>
                            <option value="3">16px</option>
                            <option value="4">18px</option>
                            <option value="5">24px</option>
                            <option value="6">32px</option>
                            <option value="7">48px</option>
                        </select>
                        <select className={style.tool_font}>
                            <option value="">폰트</option>
                            <option value="Black Han Sans">Black Han Sans</option>
                            <option value="Noto Sans KR">Noto Sans KR</option>
                            <option value="Nanum Gothic">Nanum Gothic</option>
                            <option value="Nanum Myeongjo">Nanum Myeongjo</option>
                            <option value="Nanum Pen Script">Nanum Pen Script</option>
                            <option value="맑은 고딕">맑은 고딕</option>
                        </select>
                        <button className={style.tool_bold}>
                            <i className="fas fa-bold"></i>
                        </button>
                        <button className={style.tool_italic}>
                            <i className="fas fa-italic"></i>
                        </button>
                        <button className={style.tool_under}>
                            <i className="fas fa-underline"></i>
                        </button>
                        <button className={style.tool_cancel}>
                            <i className="fas fa-strikethrough"></i>
                        </button>
                        <button className={style.tool_orderlist}>
                            <i className="fas fa-list-ol"></i>
                        </button>
                        <button className={style.tool_unorderlist}>
                            <i className="fas fa-list-ul"></i>
                        </button>
                        <span className={style.tool_image}>
                            <label>
                                <i className="far fa-image"></i>
                            </label>
                            <input type="file" className={style.input_image} accept="image/*"/>
                        </span>
                        <button className={style.tool_full}>
                            <i className="fas fa-align-justify"></i>
                        </button>
                        <button className={style.tool_left}>
                            <i className="fas fa-align-left"></i>
                        </button>
                        <button className={style.tool_center}>
                            <i className="fas fa-align-center"></i>
                        </button>
                        <button className={style.tool_right}>
                            <i className="fas fa-align-right"></i>
                        </button>
                        <button className={style.tool_undo}>
                            <i className="fas fa-undo"></i>
                        </button>
                        <button className={style.tool_redo}>
                            <i className="fas fa-redo"></i>
                        </button>
                    </div>
                    <div className={style.contents_area}>
                        <div className={style.editor} contentEditable onKeyDown={contentsOnKeyDown} ref={contentsRef}>
                        </div>
                        {postStatus === "recruit" ? <ProjectEdit post={post} setPost={setPost}/> : null}
                    </div>
                </div>
                <PostEditSetting postStatus={postStatus} post={post} setPost={setPost} />
                <div className={style.access_container}>
                    <button className={style.save_edit} onClick={process === "create" ? () => postPostData() : () => modifyPostData()}>저장</button>
                    <button className={style.cancel_edit}>취소</button>
                </div>
            </div>
        </section>
    );
};

export default PostEdit;