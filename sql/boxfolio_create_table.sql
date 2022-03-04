use boxfolio;

CREATE TABLE IF NOT EXISTS member_ability(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    cohesion INT NOT NULL DEFAULT 0,
    coupling INT NOT NULL DEFAULT 0,
    complexity INT NOT NULL DEFAULT 0,
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

CREATE TABLE IF NOT EXISTS project(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    repository_addr VARCHAR(50) NOT NULL,
    updated_date DATETIME NOT NULL,
    portfolio_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (portfolio_id) REFERENCES portfolio(id)
);

CREATE TABLE IF NOT EXISTS project_analysis(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    cohesion INT NOT NULL,
    coupling INT NOT NULL,
    complexity INT NOT NULL,
    project_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id)
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
    member_id INT UNSIGNED NOT NULL,
    skill_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id)
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
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS general(
	board_id INT UNSIGNED PRIMARY KEY,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS information(
	board_id INT UNSIGNED PRIMARY KEY,
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS board_star(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    member_id INT UNSIGNED NOT NULL,
    board_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS board_scrap(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    member_id INT UNSIGNED NOT NULL,
    board_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS board_comment(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    contents TEXT NOT NULL,
    created_date DATETIME NOT NULL,
    reply_tally INT UNSIGNED NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    board_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (board_id) REFERENCES board(id)
);

CREATE TABLE IF NOT EXISTS board_reply(
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    contents TEXT NOT NULL,
    created_date DATETIME NOT NULL,
    member_id INT UNSIGNED NOT NULL,
    board_comment_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (board_comment_id) REFERENCES board_comment(id)
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
	id VARCHAR(40) PRIMARY KEY,
	portfoilo_id INT UNSIGNED NOT NULL,
    src_order INT UNSIGNED NOT NULL,
    src blob NOT NULL,
    FOREIGN KEY (portfoilo_id) REFERENCES portfolio(id)
)