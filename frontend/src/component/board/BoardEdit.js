import React, { useEffect, useState } from 'react';
import style from '../../css/board/board_edit.module.css';
import BoardEditSetting from './BoardEditSetting';
import ProjectEdit from './ProjectEdit';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const BoardEdit = (props) => {
    const { process } = props;
    const id = useParams();
    const [postStatus, setPostStatus] = useState("free");
    const [board, setBoard] = useState({});

    useEffect(() => {
        if (process === "modify") {
            axios.get('api/modify/' + id).then(response => {
                setBoard(response.data);
            });
        }
    })

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
                    <textarea className={style.board_title} cols="30" placeholder="제목을 입력해 주세요."></textarea>
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
                        <div className={style.editor} contentEditable>
                        </div>
                        {postStatus === "recruit" ? <ProjectEdit /> : null}
                    </div>
                </div>
                <BoardEditSetting postStatus={postStatus} />
                <div className={style.access_container}>
                    <button className={style.save_edit}>저장</button>
                    <button className={style.cancel_edit}>취소</button>
                </div>
            </div>
        </section>
    );
};

export default BoardEdit;