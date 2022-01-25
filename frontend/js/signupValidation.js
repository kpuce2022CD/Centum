// 변수 선언
var id = document.getElementById("id");
var pw = document.getElementById("passwd");
var pw_check = document.getElementById("passwd_check");
var username = document.getElementById("username");
var nickname = document.getElementById("nickname");
var mobile = document.getElementById("mobile");
var email = document.getElementById("email");
var interest = document.getElementById("interest");
var bir_yy = document.getElementById("yy");
var bir_mm = document.getElementById("mm");
var bir_dd = document.getElementById("dd");
var gender = document.getElementById("gender");

var error = document.querySelectorAll(".error-msg");

// 이벤트 핸들러
id.addEventListener("focusout", checkId);
pw.addEventListener("focusout", checkPw);
pw_check.addEventListener("focusout", comparePw);
username.addEventListener("focusout", checkName);
nickname.addEventListener("focusout", checkNicename);
mobile.addEventListener("focusout", checkPhoneNum);
interest.addEventListener("focusout", checkInterest);
bir_yy.addEventListener("focusout", isBirthCompleted);
bir_mm.addEventListener("focusout", isBirthCompleted);
bir_dd.addEventListener("focusout", isBirthCompleted);
gender.addEventListener("focusout", function() {
  if(gender.value === "성별") {
    error[9].style.display = "block";
  } else {
    error[9].style.display = "none";
  }
})

// 콜백 함수
function checkId() {
  var idPattern = /[a-zA-Z0-9_-]{5,20}/;

  if(id.value === "") {
      error[0].innerHTML = "필수 정보입니다.";
      error[0].style.display = "block";
  } else if(!idPattern.test(id.value)) {
      error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
      error[0].style.display = "block";
  }
}
