import React from 'react';
import style from '../../css/project/project_member.module.css';

const ProjectMember = (props) => {
    const { project, projectMembers } = props;   

    return (
       <div className={style.project_member}>
            <h4 className={style.project_member_title}>프로젝트 멤버</h4>
            <div className={style.member_count}>
                <div>
                    {project.memberTally >= 1 && <i className={[style.entered_member, "fas fa-user"].join(' ')}></i>}
                    {project.memberTally >= 2 && <i className={[style.entered_member, "fas fa-user"].join(' ')}></i>}
                    {project.memberTally >= 3 && <i className={[style.entered_member, "fas fa-user"].join(' ')}></i>}
                    {project.memberTally >= 4 && <i className={[style.entered_member, "fas fa-user"].join(' ')}></i>}
                    {project.memberTally >= 5 && <i className={[style.entered_member, "fas fa-user"].join(' ')}></i>}
                    {project.memberTally >= 6 && <i className={[style.entered_member, "fas fa-user"].join(' ')}></i>}
                </div>
                <p className={style.member_tally}>{project.memberTally}명</p>
            </div>
            <ul className={style.project_member_list}>
                {
                    projectMembers.map(projectMember => (
                        <li key={projectMember.id}>
                            <p className={style.member_nickname}>{projectMember.memberNickname}</p>
                            <p className={style.member_level}>
                                {
                                    {
                                        0: "입문개발자",
                                        1: "초급개발자",
                                        2: "중급개발자",
                                        3: "고급개발자",
                                        4: "관리자"
                                    }[projectMember.memberLevel]
                                }
                            </p>
                        </li>
                    ))
                }
            </ul>
        </div>
    );
};

export default ProjectMember;