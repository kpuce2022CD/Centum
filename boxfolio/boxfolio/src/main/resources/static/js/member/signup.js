// 회원가입 유효성 검사 - 필수 입력칸, 비밀번호 확인, 아이디 중복확인

// 변수 선언
var id = document.getElementById("id");
var pw = document.getElementById("passwd");
var pw_check = document.getElementById("passwd-check");
var realname = document.getElementById("realname");
var nickname = document.getElementById("nickname");
var phone = document.getElementById("phone");
var email = document.getElementById("email");
var interest = document.getElementById("interest");
var year = document.getElementById("year");
var month = document.getElementById("month");
var day = document.getElementById("day");
var gender = document.getElementById("gender");

var error = document.querySelectorAll(".error-msg");

// 이벤트 핸들러
id.addEventListener('focusout', required_id);
pw.addEventListener('focusout', required_pw);
pw_check.addEventListener('focusout', check_passwd);
realname.addEventListener('focusout', required_name);
nickname.addEventListener('focusout', required_nickname);
phone.addEventListener('focusout', required_phone);

// 필수 입력칸
function required_id() {
  if(id.value === "") {
    error[0].style.display = 'block';
  } else {
    error[0].style.display = 'none';
  }
}

function required_pw() {
  if(pw.value === "") {
    error[1].style.display = 'block';
  } else {
    error[1].style.display = 'none';
  }
}

function required_pw_check() {
  if(pw_check.value === "") {
    error[2].style.display = 'block';
  } else {
    error[2].style.display = 'none';
  }
}

function required_name() {
  if(realname.value === "") {
    error[3].style.display = 'block';
  } else {
    error[3].style.display = 'none';
  }
}

function required_nickname() {
  if(nickname.value === "") {
    error[4].style.display = 'block';
  } else {
    error[4].style.display = 'none';
  }
}

function required_phone() {
  if(phone.value === "") {
    error[5].style.display = 'block';
  } else {
    error[5].style.display = 'none';
  }
}

// 비밀번호 재확인
function check_passwd() {
  if(pw_check.value === "") {
    error[2].style.display = 'block';
    error[2].innerText = "필수 정보 입니다.";
  } else if(pw.value !== pw_check.value) {
    error[2].innerText = "비밀번호가 일치하지 않습니다.";
    error[2].style.display = 'block';
  } else {
    error[2].style.display = 'none';
  }
}