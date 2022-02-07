// 회원가입 유효성 검사 - 필수 입력칸, 비밀번호 확인, 아이디 중복확인

/* 변수 선언 */
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
var signupBtn = document.getElementById("signup-btn");

var error = document.querySelectorAll(".error-msg");

/* 이벤트 핸들러 */
id.addEventListener('input', required_id);
pw.addEventListener('input', required_pw);
pw_check.addEventListener('input', check_passwd);
realname.addEventListener('input', required_name);
nickname.addEventListener('input', required_nickname);
phone.addEventListener('input', required_phone);
email.addEventListener('input', isEmailCorrect);
interest.addEventListener('change', isInterestSelected);
year.addEventListener('input', isBirthCompleted);
month.addEventListener('input', isBirthCompleted);
day.addEventListener('input', isBirthCompleted);
gender.addEventListener('change', isGenderSelected);

/* 함수 */
function required_id() {
  if(id.value === "") {
    error[0].style.display = 'block';
    return false;
  } else {
    error[0].style.display = 'none';
    return true;
  }
}

function required_pw() {
  if(pw.value === "") {
    error[1].style.display = 'block';
    return false;
  } else {
    error[1].style.display = 'none';
    return true;
  }
}

// 비밀번호 재확인
function check_passwd() {
  if(pw_check.value === "") {
    error[2].style.display = 'block';
    error[2].innerText = "필수 정보입니다.";
    return false;
  }
  else if(pw.value !== pw_check.value) {
    error[2].innerText = "비밀번호가 일치하지 않습니다.";
    error[2].style.display = 'block';
    return false;
  } 
  else {
    error[2].style.display = 'none';
    return true;
  }
}

function required_name() {
  if(realname.value === "") {
    error[3].style.display = 'block';
    return false;
  } else {
    error[3].style.display = 'none';
    return true;
  }
}

function required_nickname() {
  if(nickname.value === "") {
    error[4].style.display = 'block';
    return false;
  } else {
    error[4].style.display = 'none';
    return true;
  }
}

function required_phone() {
  var phonePattern = /01([01679]{1})([0-9]{3,4})([0-9]{4})/;
  if(phone.value === "") {
    error[5].innerText = "필수 정보입니다.";
    error[5].style.display = 'block';
    return false;
  } 
  else if (!phonePattern.test(phone.value)) {
    error[5].innerText = "형식에 맞지 않는 번호입니다.";
    error[5].style.display = 'block';
    return false;
  } 
  else {
    error[5].style.display = 'none';
    return true;
  }
}

function isEmailCorrect() {
  var emailPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;

    if(email.value === "") { 
        error[6].style.display = 'block';
        return false;
    } 
    else if(!emailPattern.test(email.value)) {
        error[6].style.display = 'block';
        return false;
    } 
    else {
        error[6].style.display = 'none';
        return true;
    }
}

function isInterestSelected() {
  if(interest.value === "") {
    error[7].style.display = 'block';
    return false;
} else {
    error[7].style.display = 'none';
    return true;
}
}

function isBirthCompleted() {
  var yearPattern = /[0-9]{4}/;
  var nowYear = new Date();

  if(year.value === "") {
    error[8].style.display = 'block';
    return false;
  }
  else if(!yearPattern.test(year.value) || Number(year.value) < 1900 || Number(year.value) > nowYear.getFullYear()) {
    error[8].innerText = "태어난 년도 4자리를 정확하게 입력하세요.";
    error[8].style.display = 'block';
    return false;
  } 
  else {
    return isMonthCompleted();
  }
}

function isMonthCompleted() {
  var monthPattern = /\d{1,2}/;

  if(month.value === "" || !monthPattern.test(month.value) || Number(month.value) > 12 || Number(month.value) < 1) {
    error[8].innerText = "태어난 월 2자리를 정확하게 입력하세요.";
    error[8].style.display = 'block';
    return false;
  } else {
    return isDateCompleted();
  }
}

function isDateCompleted() {
  var datePattern = /\d{1,2}/;

  if(day.value === "" || !datePattern.test(day.value) || Number(day.value) < 1 || Number(day.value) > 31) {
    error[8].innerText = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
    error[8].style.display = 'block';
    return false;
  } else {
    error[8].style.display = 'none';
    return true;
  }
}

function isGenderSelected() {
  if(gender.value === "0") {
    error[9].style.display = 'block';
    return false;
  } else {
    error[9].style.display = 'none';
    return true;
  }
}

signupBtn.addEventListener('click', (e) => {
  let status = true;
  status &= required_id() & required_pw() & check_passwd() & required_name() & required_nickname() &
  isEmailCorrect() & isInterestSelected() & isBirthCompleted() & isGenderSelected();

  if (!status) {
    e.preventDefault();
  }
});