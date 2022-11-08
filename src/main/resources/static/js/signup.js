// 휴대폰 번호 입력 부분
function changePhone() {
    const phone = document.getElementById("phone").value
    if (phone.length === 11) {
        document.getElementById("studentNum").focus();
    }
}

function changeStudentNum() {
    const studentNum = document.getElementById("studentNum").value
    if (studentNum.length === 8) {
        document.getElementById("major").focus();
    }
}


function changeBirthday() {
    const birthday = document.getElementById("birthday").value
    if (birthday.length === 8) {
        document.getElementById("phone").focus();
    }
}

function signUpCheck() {

    // let pwdCheck;
    // pwdCheck = /^(?=.*\d)(?=.*[a-z])(?=\\S+$).{8,20}$/;
    let name = document.getElementById("name").value
    let id = document.getElementById("id").value
    let pw = document.getElementById("pw").value
    let checkpw = document.getElementById("checkpw").value
    let major = document.getElementById("major").value
    let session = document.getElementById("session").value
    let year = document.getElementById("year").value
    let phone = document.getElementById("phone").value
    let birthday = document.getElementById("birthday").value

    let studentNum = document.getElementById("studentNum").value


    let position_manager = document.getElementById("position_manager").checked
    let position_user = document.getElementById("position_user").checked
    let gender_man = document.getElementById("gender_man").checked
    let gender_woman = document.getElementById("gender_woman").checked

    let name_error = document.getElementById("nameError").value
    let id_error = document.getElementById("idError").value
    let pw_error = document.getElementById("pwError").value
    let checkpw_error = document.getElementById("checkpwError").value
    let birthday_error = document.getElementById("birthdayError").value
    let phone_error = document.getElementById("phoneError").value
    let studentNum_error = document.getElementById("studentNumError").value
    let major_error = document.getElementById("majorError").value
    let year_error = document.getElementById("yearError").value
    let session_error = document.getElementById("sessionError").value
    let position_error = document.getElementById("positionError").value
    let gender_error = document.getElementById("genderError").value

    let pwd1 = document.querySelector('#pw');
    let pwd2 = document.querySelector('#checkpw');
    let signup_form = document.querySelector('#form');


    if (name_error == "" && id_error == "" && birthday_error == "" &&
        phone_error == "" && studentNum_error == "" && major_error == "" && year_error == "" && session_error == "" &&
        position_error == "" && gender_error == "" && pw_error == "" && checkpw_error == "비밀번호 일치함" && pwd1.value == pwd2.value) {
        signup_form.submit();
    }

    // 이름 확인
    if (name == "") {
        document.getElementById("nameError").innerHTML = "이름이 올바르지 않습니다."
        document.getElementById("name").focus();
        return false
    } else {
        document.getElementById("nameError").innerHTML = ""
    }

    // 아이디 확인
    if (id == "") {
        document.getElementById("idError").innerHTML = "아이디가 올바르지 않습니다."
        document.getElementById("id").focus();
        return false
    } else {
        document.getElementById("idError").innerHTML = ""
    }

    if (checkpw !== pw || checkpw == "" || pw == "") {
        document.getElementById("checkpwError").style.color = "rgba(248,42,42,0.7)";
        document.getElementById("pwError").innerHTML = "비밀번호를 다시 입력해주세요."
        document.getElementById("checkpwError").innerHTML = ""
        document.getElementById("pw").innerHTML = ""
        document.getElementById("checkpw").innerText = ""
        document.getElementById("pw").focus();
        return false
    } else if(checkpw == pw){
        document.getElementById("checkpwError").style.color = "rgba(16,201,197,0.7)";
        document.getElementById("checkpwError").innerHTML = "비밀번호 일치함"
        document.getElementById("pwError").innerHTML = ""
    }

    // if (!pwdCheck.test(pw.value) || pw == "") {
    //     document.getElementById("checkpwError").style.color = "rgba(248,42,42,0.7)";
    //     document.getElementById("pwError").innerHTML = "영문+숫자+특수문자 하나이상 8~20자리 사용해야합니다."
    //     document.getElementById("checkpwError").innerText = ""
    //     document.getElementById("pw").innerText = ""
    //     document.getElementById("checkpw").innerText = ""
    //     document.getElementById("pw").focus();
    //     return false
    // } else {
    //     document.getElementById("pwError").innerHTML = ""
    //     document.getElementById("checkpw").innerText = ""
    // }


    // 연락처 확인
    if (phone == "" || phone.length !== 11) {
        document.getElementById("phoneError").innerHTML = "연락처 확인"
        document.getElementById("phone").focus();
        return false
    } else {
        document.getElementById("phoneError").innerHTML = ""
    }

    // 학번입력 확인
    if (studentNum == "" || studentNum.length !== 8) {
        document.getElementById("studentNumError").innerHTML = "학번 입력"
        document.getElementById("studentNum").focus();
        return false
    } else {
        document.getElementById("studentNumError").innerHTML = ""
    }


    // 학과선택 확인
    if (major == "") {
        document.getElementById("majorError").innerHTML = "학과 선택"
        document.getElementById("major").focus();
        return false
    } else {
        document.getElementById("majorError").innerHTML = ""
    }

    // 기수선택 확인
    if (year == "") {
        document.getElementById("yearError").innerHTML = "기수 선택"
        document.getElementById("years").focus();
        return false
    } else {
        document.getElementById("yearError").innerHTML = ""
    }

    // 세션선택 확인
    if (session == "") {
        document.getElementById("sessionError").innerHTML = "세션 선택"
        document.getElementById("session").focus();
        return false
    } else {
        document.getElementById("sessionError").innerHTML = ""
    }

    // 생년월일 확인
    if (birthday == "" || birthday.length !== 8) {
        document.getElementById("birthdayError").innerHTML = "생년월일 확인"
        document.getElementById("birthday").focus();
        return false
    } else {
        document.getElementById("birthdayError").innerHTML = ""
    }


    // 직급확인
    if (!position_manager && !position_user) {
        document.getElementById("positionError").innerHTML = "직급 선택"
        return false
    } else {
        document.getElementById("positionError").innerHTML = ""
    }


    // 성별체크확인
    if (!gender_man && !gender_woman) {
        document.getElementById("genderError").innerHTML = "성별 선택"
        return false
    } else {
        document.getElementById("genderError").innerHTML = ""
    }

    signup_form.submit();

}