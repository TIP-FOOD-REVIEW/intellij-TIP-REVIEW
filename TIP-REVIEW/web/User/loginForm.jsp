<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>로그인</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.9.0/css/all.min.css">
        <style>
            * {
                margin: 0;
                padding: 0;
            }
            .container{
                width: 350px;
                margin: 0 auto;
                padding-bottom: 50px;
            }
            .blind{
                margin-top: 60px;
                margin-bottom: 15px;
                text-align: center;
            }

        </style>
    </head>
    <body>
    <section class="vh-100" style="background-color: #eee; padding: 30px 0">
        <div class="container">
            <div class="row d-flex justify-content-center align-items-center">
                <div class="p-4" style="border-radius: 25px; width: 600px; background-color: white">
                    <div class="card-body p-md-5">
                        <div class="row justify-content-center">
                            <form class="container" action="/userController?action=login" method="post">
                                <a href="/" class="logo">
                                    <h1 class="blind">ShoppingMall</h1>
                                </a>
                                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4"></p>
                                <!-- Email input -->
                                <div class="form-outline mb-4">
                                    <input type="text" id="form2Example1" name="username" class="form-control" name="email"/>
                                    <label class="form-label" for="form2Example1">아이디</label>
                                </div>

                                <!-- Password input -->
                                <div class="form-outline mb-4">
                                    <input type="text" id="form2Example2" name = "password" class="form-control" name="password"/>
                                    <label class="form-label" for="form2Example2">비밀번호</label>
                                </div>

                                <!-- 2 column grid layout for inline styling -->
                                <div class="row mb-4">
                                    <div class="col d-flex justify-content-center">
                                        <!-- Checkbox -->
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" value="true" id="form2Example31" checked name="rememberCustomer"/>
                                            <label class="form-check-label" for="form2Example31"> 이 계정 기억하기 </label>
                                        </div>
                                    </div>
                                </div>

                                <!-- Submit button -->
                                <button type="submit" class="btn btn-primary btn-block mb-4">로그인</button>

                                <!-- Register buttons -->
                                <div class="text-center">
                                    <p>계정이 없으십니까? <a href="/register">회원가입</a></p>
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
