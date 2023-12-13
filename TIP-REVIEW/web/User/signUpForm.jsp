<%@ page import="javax.persistence.Query" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>회원가입</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


        <!-- MDB icon -->
        <link rel="icon" href="img/mdb-favicon.ico" type="image/x-icon" />
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
        <!-- Google Fonts Roboto -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap" />
        <style>
            i{
                color: #0D6EFD;
            }
            .gender{
                display: flex;
                justify-content: space-between;
            }
        </style>
    </head>
    <body>

        <section class="" style="background-color: #eee; padding: 30px 0">
            <div class="container">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="card text-black" style="border-radius: 25px; width: 600px;">
                        <div class="card-body p-md-5">
                            <div class="row justify-content-center">
                                <a class="text-center h1 fw-bold mb-5 mx-1 mx-md-4" href="/" class="logo" style="color: #0D6EFD">
                                    <h1 class="blind">ShoppingMall</h1>
                                </a>
                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4"></p>

                                <form class="mx-1 mx-md-4" action="/register" method="post" id="registerForm">
                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="text" name="registerName" id="registerName" class="form-control" />
                                            <label class="form-label" for="registerName">이름</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="email" name="registerEmail" id="registerEmail" class="form-control" oninput="valid()"/>
                                            <label class="form-label" for="registerEmail">이메일</label>
                                            <label class="form-label" for="registerEmail" id="validCheck" style="color: red"></label>
                                        </div>
                                        <script>
                                            function valid() {
                                                let email = document.getElementById("registerEmail").value;
                                                let validCheck = document.getElementById("validCheck");
                                                $.ajax({
                                                    type:"post",
                                                    url: "http://localhost:8080/valid/email?email=" + email,
                                                }).done((res)=>{
                                                    if(res == "True") {
                                                        validCheck.innerText = "중복";
                                                        document.getElementById("btnRegister").disabled = true;
                                                    }else if (res == "False"){
                                                        validCheck.innerText = "";
                                                        document.getElementById("btnRegister").disabled = false;
                                                    }
                                                    // 필요한 경우 페이지를 새로고침하거나 업데이트합니다.
                                                }).fail((err)=>{
                                                    console.log(err);
                                                });
                                            }
                                        </script>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name="registerPassword" id="registerPassword" class="form-control" />
                                            <label class="form-label" for="registerPassword">비밀번호</label>
                                            <label class="form-label col-form-label-sm" for="registerPassword" style="color: red; margin-left: 10px">비밀번호의 길이는 8~16자 이내로 가능합니다.</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <input type="password" name="registerRePassword" id="registerRePassword" class="form-control" />
                                            <label class="form-label" for="registerRePassword">비밀번호 확인</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-venus-mars fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <div class="form-control gender" >
                                                <input type="radio" name="registerGender" id="male" checked="" value="M">남자
                                                <input type="radio" name="registerGender" id="female" value="F">여자
                                                <div></div>
                                            </div>
                                            <label class="form-label">성별</label>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center mb-4">
                                        <i class="fas fa-person fa-lg me-3 fa-fw"></i>
                                        <div class="form-outline flex-fill mb-0">
                                            <select class="form-control" name="registerAge" >
                                                <%for (int i = 18; i < 60; i++){%>
                                                    <option value="<%=i%>"><%=i%>세</option>
                                                <%}%>
                                            </select>

                                            <label class="form-label">나이</label>
                                        </div>
                                    </div>


                                    <div class="form-check d-flex justify-content-left mb-5" style="padding-right: 20px;">
                                        <input type="checkbox" value="true" name="registerServiceAgree" id="registerServiceAgree" style="margin-right: 10px;"/>
                                        <label class="form-check-label" for="registerServiceAgree">
                                            <a href="#" onclick="function showTerms() {
                                                document.getElementById('term').showModal();
                                            }
                                            showTerms()">이용 약관</a>에 모두 동의 합니다.
                                        </label>
                                        <dialog id="term">
                                            <form>
                                                <button type="button" class="btn-close" aria-label="Close" onclick="function closeTerms() {
                                                    document.getElementById('term').close();
                                                }
                                                closeTerms()"></button>
                                                <p>
                                                    <label>
                                                        제 1조 (목적)<br>
                                                        이 약관은 회사가 제공하는 서비스를 이용함에 있어 회원과 회사 간의 권리, 의무, 책임사항, 서비스 이용 절차 등을 규정함을 목적으로 합니다.<br>
                                                        <br>
                                                        제 2조 (정의)
                                                        <ol>
                                                           <li>"회원"이란 본 약관에 동의하고 회사의 서비스를 이용하는 개인 또는 단체를 의미합니다.</li>
                                                           <li>"서비스"란 회사가 제공하는 온라인 플랫폼 및 그와 관련된 부가 서비스를 의미합니다.</li>
                                                        </ol>
                                                        <br>
                                                        제 3조 (회원가입 절차)<br>
                                                        <ol>
                                                            <li>회원가입은 본 약관에 동의한 후, 회사가 정한 가입 양식에 필요한 정보를 제공하여야 합니다.</li>
                                                            <li>회사는 회원의 제공한 정보를 확인한 후, 가입 승인 여부를 결정합니다.</li>
                                                        </ol>
                                                        <br>
                                                        제 4조 (회원의 의무)<br>
                                                        <ol>
                                                            <li>회원은 본인의 개인정보를 정확하게 제공하여야 합니다.</li>
                                                            <li>회원은 부정한 방법으로 서비스를 이용하거나 타인의 정보를 도용해서는 안 됩니다.</li>
                                                            <li>회원은 회사의 서비스 이용 규정을 준수해야 합니다.</li>
                                                            <li>회원은 자신의 계정과 비밀번호를 관리하고 부정사용을 방지하기 위해 노력해야 합니다.</li>
                                                        </ol>
                                                        <br>
                                                        제 5조 (회사의 의무)<br>
                                                        <ol>
                                                            <li>회사는 회원의 개인정보를 적절히 보호하며, 개인정보 보호 관련 법규를 준수합니다.</li>
                                                            <li>회사는 안정적인 서비스 제공을 위해 최선을 다하며, 시스템 장애나 서비스 중단 시 빠른 조치를 취하겠습니다.</li>
                                                        </ol>
                                                        <br>
                                                        제 6조 (이용 제한)<br>
                                                        <ol>
                                                            <li>회사는 회원이 본 약관을 위반하거나 서비스 이용 목적에 반하는 행위를 할 경우, 회원의 서비스 이용을 제한할 수 있습니다.</li>
                                                            <li>회사는 부적절한 내용 게시, 타인에게 피해를 주는 행위에 대해 해당 내용을 삭제하고 서비스 이용을 제한할 수 있습니다.</li>
                                                        </ol>
                                                        <br>
                                                        제 7조 (개인정보 보호)<br>
                                                        <ol>
                                                            <li>회사는 회원의 개인정보를 적절히 보호하며, 개인정보 처리에 대한 규정은 별도의 개인정보 처리방침에서 정합니다.</li>
                                                            <li>회원은 개인정보를 보호하기 위해 관리에 상당한 주의를 기울여야 합니다.</li>
                                                        </ol>
                                                        <br>
                                                    </label>
                                                </p>
                                            </form>
                                        </dialog>
                                    </div>

                                    <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                        <button type="button" class="btn btn-primary btn-lg" id="btnRegister" onclick="checkPassword()">가입하기</button>
                                        <script>
                                            function checkPassword() {
                                                let password = document.getElementById("registerPassword").value

                                                if (!document.getElementById("registerServiceAgree").checked) {
                                                    alert("약관에 동의 해주세요.")
                                                }else if(document.getElementById("registerEmail").value == ""||document.getElementById("registerName").value == ""||password == ""){
                                                    alert("빈칸에 입력해주세요");
                                                }else if (password != document.getElementById("registerRePassword").value){
                                                    alert("패스워드가 일치하지 않습니다" + document.getElementById("registerPassword").value+ " " + document.getElementById("registerRePassword").value);
                                                }else{
                                                    //비밀번호 검사
                                                    if (password.length > 20 || password.length < 8)
                                                        alert("길이 이슈");
                                                    else {
                                                        alert("회원가입이 완료되었습니다.")
                                                        document.getElementById("registerForm").submit();
                                                    }
                                                }
                                            }
                                        </script>
                                    </div>

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
          </section>
    </body>
</html>