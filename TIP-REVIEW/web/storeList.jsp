<%@ page import="model.Entitiy.Store" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Restaurant Listings</title>
</head>
<body>
<div class="bg-light">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center py-3">
            <div onclick="location.href='/home'">
                <h1 class="navbar-brand" >TIP-REVIEW</h1>
            </div>
            <div>
                <% String username = (String) session.getAttribute("username");
                    if (username != null && !username.isEmpty()) { %>
                <span><%= "환영합니다, " + username + "님" %></span>
                <a href="javascript:void(0);" onclick="logout()">로그아웃</a>
                <% } else { %>
                <a href="/User/loginForm.jsp">로그인</a>
                <a href="/User/signUpForm.jsp">회원가입</a>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script>
    function logout() {
        fetch('/userController?action=logout', { method: 'POST' })
            .then(response => {
                window.location.href = '/home';
            })
            .catch(error => console.error('Error during logout:', error));
    }
</script>


<div class="container w-75 mt-5 mx-auto">
        <div class="d-flex justify-content-between" style="padding-bottom: 1rem">
            <h2 style="font-size: 40px;">가게 목록</h2>
            <div class="btn-group mt-3">
                <button id="ratingButton" type="button" class="btn btn-secondary" onclick="location.href='/home?action=listByRating';" style="background-color: #686868;"> 별점 높은 순</button>
                <button id="reviewCountButton" type="button" class="btn btn-secondary" onclick="location.href='/home?action=listByReviewCount';" style="background-color: #686868;">리뷰 많은 순</button>
            </div>
        </div>
        <hr>
        <ul id="storeListUl" class="list-group">
            <%
                Store[] storeList = (Store[]) request.getAttribute("storeList");
                if(storeList != null) {
                    for(int i = 0; i < storeList.length; i++) {
                        Store store = storeList[i];
            %>
            <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                <div class="bg-white shadow rounded-lg p-4 flex justify-between items-center" style="width:inherit" onclick="location.href='/storeDetail?action=getReviewList&storeId=<%=store.getStoreId()%>';">
                    <div>
                        <h2 class="text-lg font-semibold"><%= store.getStoreName() %></h2>
                        <p class="text-gray-600"><%= store.getAddress() %></p>
                    </div>
                    <div class="text-right">
                        <div class="flex items-center mb-1">
                            <span class="rating-star">⭐️</span>
                            <span class="ml-1 font-semibold"><%= store.getRating() %></span>
                        </div>
                        <div class="text-sm text-gray-500">리뷰 <%= store.getReviewCount() %>개</div>
                    </div>
                </div>
            </li>
            <%
                    }
                }
            %>
        </ul>
        <hr>
        <%
            String error = (String) request.getAttribute("error");
            if(error != null && !error.isEmpty()) {
        %>
        <div class="alert alert-danger alert-dismissible fade show mt-3">
            에러 발생: <%= error %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } %>
    </div>
</body>
</html>