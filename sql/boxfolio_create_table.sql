use boxfolio;

CREATE TABLE IF NOT EXISTS member_ability(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    cohesion INT NOT NULL DEFAULT 0,
    coupling INT NOT NULL DEFAULT 0,
    complexity INT NOT NULL DEFAULT 0,
    redundancy INT NOT NULL DEFAULT 0,
    standard INT NOT NULL DEFAULT 0,
    member_level INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS member(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    login_id VARCHAR(20) NOT NULL,
    passwd VARCHAR(30) NOT NULL,
    real_name VARCHAR(20) NOT NULL,
    nickname VARCHAR(10) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    birth DATE NOT NULL,
    sex CHAR(1) NOT NULL,
    github_id VARCHAR(20) NOT NULL DEFAULT "",
    interest_field VARCHAR(20) NOT NULL,
    progress_field VARCHAR(20) NOT NULL DEFAULT "",
    email_verified TINYINT NOT NULL DEFAULT 0,
    member_ability_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_ability_id) REFERENCES member_ability(id)
);

CREATE TABLE IF NOT EXISTS portfolio(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    contents MEDIUMTEXT NOT NULL,
    updated_date DATETIME NOT NULL,
    visibility VARCHAR(10) NOT NULL,
    star_tally INT UNSIGNED NOT NULL DEFAULT 0,
    scrap_tally INT UNSIGNED NOT NULL DEFAULT 0,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS portfolio_star(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	portfolio_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS portfolio_scrap(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	portfolio_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS project_analysis(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    cohesion INT NOT NULL DEFAULT 0,
    coupling INT NOT NULL DEFAULT 0,
    complexity INT NOT NULL DEFAULT 0,
    redundancy INT NOT NULL DEFAULT 0,
    standard INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS project(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    project_field VARCHAR(20) NOT NULL,
    updated_date DATETIME NOT NULL,
    from_repository TINYINT NOT NULL,
    repository_addr VARCHAR(50) NOT NULL,
    portfolio_id INT UNSIGNED,
    member_id INT UNSIGNED NOT NULL,
    project_analysis_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (project_analysis_id) REFERENCES project_analysis(id)
);

CREATE TABLE IF NOT EXISTS skill_type(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS skill(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	skill_name VARCHAR(30) NOT NULL,
    skill_type_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (skill_type_id) REFERENCES skill_type(id)
);

CREATE TABLE IF NOT EXISTS project_skill(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    project_id INT UNSIGNED NOT NULL,
    skill_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id)
);

CREATE TABLE IF NOT EXISTS member_skill(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    skill_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (skill_id) REFERENCES skill(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS title(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title_name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS member_title(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	title_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS board(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    contents MEDIUMTEXT NOT NULL,
    created_date DATETIME NOT NULL,
    comment_allow TINYINT NOT NULL,
    scrap_allow TINYINT NOT NULL,
    star_tally INT UNSIGNED NOT NULL,
    comment_tally INT UNSIGNED NOT NULL,
    scrap_tally INT UNSIGNED NOT NULL,
    view_tally INT UNSIGNED NOT NULL,
    visibility VARCHAR(10) NOT NULL,
    board_type VARCHAR(20) NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS recruitment(
	board_id INT UNSIGNED PRIMARY KEY,
    auto_matching_status TINYINT NOT NULL,
    deadline_status TINYINT NOT NULL,
    deadline_date DATETIME NOT NULL,
    member_tally INT UNSIGNED NOT NULL,
    member_total INT UNSIGNED NOT NULL,
    project_subject VARCHAR(50) NOT NULL,
    project_field VARCHAR(20) NOT NULL,
    project_preview MEDIUMTEXT NOT NULL,
    project_level TINYINT NOT NULL,
    required_member_level TINYINT NOT NULL,
    expected_period VARCHAR(10) NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS project_rule(
	id INT UNSIGNED PRIMARY KEY,
    rule_order TINYINT NOT NULL,
    contents TEXT NOT NULL,
    board_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS project_plan(
	id INT UNSIGNED PRIMARY KEY,
    plan_order INT NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    contents TEXT NOT NULL,
    board_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS free(
	board_id INT UNSIGNED PRIMARY KEY,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS information(
	board_id INT UNSIGNED PRIMARY KEY,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS project_member(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    board_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS board_star(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    board_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS board_scrap(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    board_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (board_id) REFERENCES board(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS board_comment(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    contents TEXT NOT NULL,
    created_date DATETIME NOT NULL,
    comment_class TINYINT NOT NULL,
    comment_order INT UNSIGNED NOT NULL,
    group_num INT UNSIGNED,
    parent_id INT UNSIGNED,
    board_id INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (parent_id) REFERENCES board_comment(id),
    FOREIGN KEY (board_id) REFERENCES board(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS confirmation_token(
	id VARCHAR(40) PRIMARY KEY,
    expiration_date DATETIME NOT NULL,
    expired TINYINT NOT NULL,
    created_date DATETIME NOT NULL,
    last_modified_date DATETIME NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS portfolio_files(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	portfolio_id INT UNSIGNED NOT NULL,
    src_order INT UNSIGNED NOT NULL,
    src varchar(1000),
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id)
);