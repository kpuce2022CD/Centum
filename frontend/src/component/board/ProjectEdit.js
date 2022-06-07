import React from 'react';
import style from '../../css/board/project_edit.module.css';

const ProjectEdit = (props) => {
    const { board, setBoard } = props;
    
    const projectOnChange = (e) => {
        const nextBoard = {
            ...board,
            [e.target.name]: e.target.value
        };
        setBoard(nextBoard);
    }

    return (
        <div className={style.project_area}>
            <table className={style.project_info}>
                <colgroup>
                    <col style={{width: "70px"}}/>
                    <col/>
                </colgroup>
                <tbody>
                    <tr>
                        <th className={style.project_subject}>주제</th>
                        <td><input type="text" className={style.project_subject} placeholder="주제를 입력해 주세요" name="projectSubject" defaultValue={board.projectSubject} onChange={projectOnChange}/></td>
                    </tr>
                    <tr className={style.project_field}>
                        <th>분야</th>
                        <td>
                            <select name="projectField" className={style.project_field} onChange={projectOnChange} value={board.projectField}>
                                <option value="앱/안드로이드">앱/안드로이드</option>
                                <option value="앱/IOS">앱/IOS</option>
                                <option value="앱/크로스플랫폼">앱/크로스플랫폼</option>
                                <option value="웹">웹</option>
                                <option value="게임">게임</option>
                                <option value="AI">AI</option>
                                <option value="임베디드">임베디드</option>
                            </select>
                        </td>
                    </tr>
                    <tr className={style.project_level}>
                        <th>난이도</th>
                        <td>
                            <select name="projectLevel" className={style.project_level} onChange={projectOnChange} value={board.projectLevel}>
                                <option value={1}>하</option>
                                <option value={2}>중</option>
                                <option value={3}>상</option>
                            </select>
                        </td>
                    </tr>
                    <tr className={style.required_member_level}>
                        <th>최소등급</th>
                        <td>
                            <select name="requiredMemberLevel" className={style.required_member_level} onChange={projectOnChange} value={board.requiredMemberLevel}>
                                <option value={0}>입문개발자</option>
                                <option value={1}>초급개발자</option>
                                <option value={2}>중급개발자</option>
                                <option value={3}>상급개발자</option>
                                <option value={4}>관리자</option>
                            </select>
                        </td>
                    </tr>
                    <tr className={style.expected_period}>
                        <th>예상기간</th>
                        <td>
                            <select name="expectedPeriod" className={style.expected_period} onChange={projectOnChange} value={board.expectedPeriod}>
                                <option value="2주 이하">2주 이하</option>
                                <option value="1개월 이하">1개월 이하</option>
                                <option value="3개월 이하">3개월 이하</option>
                                <option value="6개월 이하">6개월 이하</option>
                                <option value="1년 이하">1년 이하</option>
                                <option value="1년 이상">1년 이상</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
};

export default ProjectEdit;