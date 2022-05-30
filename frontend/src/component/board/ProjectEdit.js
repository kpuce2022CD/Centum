import React from 'react';
import style from '../../css/board/project_edit.module.css';

const ProjectEdit = () => {
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
                        <td><input type="text" className={style.project_subject} placeholder="주제를 입력해 주세요"/></td>
                    </tr>
                    <tr className={style.project_field}>
                        <th>분야</th>
                        <td>
                            <select name="project-field" className={style.project_field}>
                                <option value="android">앱/안드로이드</option>
                                <option value="ios">앱/IOS</option>
                                <option value="web">웹</option>
                                <option value="ai">AI</option>
                                <option value="embedded">임베디드</option>
                            </select>
                        </td>
                    </tr>
                    <tr className={style.project_level}>
                        <th>난이도</th>
                        <td>
                            <select name="project-level" className={style.project_level}>
                                <option value="1">하</option>
                                <option value="2">중</option>
                                <option value="3">상</option>
                            </select>
                        </td>
                    </tr>
                    <tr className={style.required_member_level}>
                        <th>최소등급</th>
                        <td>
                            <select name="required-member-level" className={style.required_member_level}>
                                <option value="0">입문개발자</option>
                                <option value="1">초급개발자</option>
                                <option value="2">중급개발자</option>
                                <option value="3">상급개발자</option>
                            </select>
                        </td>
                    </tr>
                    <tr className={style.expected_period}>
                        <th>예상기간</th>
                        <td>
                            <select name="expected-period" className={style.expected_period}>
                                <option value="two-w">2주 이하</option>
                                <option value="one-m">1개월 이하</option>
                                <option value="three-m">3개월 이하</option>
                                <option value="six-m">6개월 이하</option>
                                <option value="lower-one-y">1년 이하</option>
                                <option value="higher-one-y">1년 이상</option>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    );
};

export default ProjectEdit;