// 회원가입 유효성 검사 - 필수 입력칸, 비밀번호 확인, 아이디 중복확인

// 변수 선언
var id = document.getElementById("id");
var pw = document.getElementById("passwd");
var pw_check = document.getElementById("passwd-check");
var realname = document.getElementById("realname");
var nickname = document.getElementById("nickname");
var phone = document.getElementById("mobile");
var email = document.getElementById("email");
var interest = document.getElementById("interest");
var year = document.getElementById("year");
var month = document.getElementById("month");
var day = document.getElementById("day");
var gender = document.getElementById("gender");

var error = document.querySelectorAll(".error-msg");

// 이벤트 핸들러
pw_check.addEventListener('focusout', check_passwd);


// 비밀번호 재확인
function check_passwd() {
  if(pw.value !== pw_check.value) {
    error[2].style.display = 'block';
  } else {
    error[2].style.display = 'none';
  }
}