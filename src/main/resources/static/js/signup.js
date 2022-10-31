function signUpCheck(){

    let name = document.getElementById("name").value
    let id = document.getElementById("id").value
    let pw = document.getElementById("pw").value
    let checkpw = document.getElementById("checkpw").value
    let birthday = document.getElementById("birthday").value
    let major = document.getElementById("major").value
    let session = document.getElementById("session").value
    let year = document.getElementById("year").value
    let phone = document.getElementById("phone").value
    let studentNum = document.getElementById("studentNum").value



    let position_manager = document.getElementById("position_manager").value
    let position_user = document.getElementById("position_user").value
    let gender_man = document.getElementById("gender_man").checked
    let gender_woman = document.getElementById("gender_woman").checked
    let check = true;


    // 이름 확인
    if(name===""){
        document.getElementById("nameError").innerHTML="이름이 올바르지 않습니다."
        check = false
    }else{
        document.getElementById("nameError").innerHTML=""
    }

    // 아이디 확인
    if(id===""){
        document.getElementById("idError").innerHTML="아이디가 올바르지 않습니다."
        check = false
    }else{
        document.getElementById("idError").innerHTML=""
    }


    // 비밀번호 확인
    if(pw !== checkpw){
        document.getElementById("passwordError").innerHTML=""
        document.getElementById("passwordCheckError").innerHTML="비밀번호가 동일하지 않습니다."
        check = false
    }else{
        document.getElementById("passwordError").innerHTML=""
        document.getElementById("passwordCheckError").innerHTML=""
    }

    if(pw===""){
        document.getElementById("passwordError").innerHTML="비밀번호를 입력해주세요."
        check = false
    }else{
        //document.getElementById("passwordError").innerHTML=""
    }
    if(checkpw===""){
        document.getElementById("passwordCheckError").innerHTML="비밀번호를 다시 입력해주세요."
        check = false
    }else{
        //document.getElementById("passwordCheckError").innerHTML=""
    }


    // 연락처 확인
    if(phone === "연락처 확인"){
        document.getElementById("phoneError").innerHTML="연락처 확인"
        check = false
    }else{
        document.getElementById("phoneError").innerHTML=""
    }

    // 학번선택 확인
    if(studentNum === "학번 선택"){
        document.getElementById("studentNumError").innerHTML="학번 선택"
        check = false
    }else{
        document.getElementById("studentNumError").innerHTML=""
    }


    // 학과선택 확인
    if(major === "학과 선택"){
        document.getElementById("majorError").innerHTML="학과 선택"
        check = false
    }else{
        document.getElementById("majorError").innerHTML=""
    }

    // 기수선택 확인
    if(year === "기수 선택"){
        document.getElementById("yearError").innerHTML="기수 선택"
        check = false
    }else{
        document.getElementById("yearError").innerHTML=""
    }

    // 세션선택 확인
    if(session === "세션 선택"){
        document.getElementById("sessionError").innerHTML="세션 선택"
        check = false
    }else{
        document.getElementById("sessionError").innerHTML=""
    }

    // 생년월일 확인
    if(birthday === "생년월일 확인"){
        document.getElementById("birthdayError").innerHTML="생년월일 확인"
        check = false
    }else{
        document.getElementById("birthdayError").innerHTML=""
    }

    // 직급확인
    if(!position_manager && !position_user){
        document.getElementById("positionError").innerHTML="직급 선택"
        check = false
    }else{
        document.getElementById("positionError").innerHTML=""
    }


    // 성별체크확인
    if(!gender_man && !gender_woman){
        document.getElementById("genderError").innerHTML="성별 선택"
        check = false
    }else{
        document.getElementById("genderError").innerHTML=""
    }

    if(check){
        document.getElementById("nameError").innerHTML=""
        document.getElementById("idError").innerHTML=""
        document.getElementById("passwordError").innerHTML=""
        document.getElementById("passwordCheckError").innerHTML=""
        document.getElementById("phoneError").innerHTML=""
        document.getElementById("studentNumError").innerHTML=""
        document.getElementById("majorError").innerHTML=""
        document.getElementById("yearError").innerHTML=""
        document.getElementById("sessionError").innerHTML=""
        document.getElementById("positionError").innerHTML=""
        document.getElementById("genderError").innerHTML=""

        //비동기 처리이벤트
        setTimeout(function() {
            alert("가입이 완료되었습니다.")
        },0);
    }
}