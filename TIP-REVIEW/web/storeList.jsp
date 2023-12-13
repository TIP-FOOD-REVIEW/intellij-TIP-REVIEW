<%@ page import="model.Entitiy.Store" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TIP-REVIEW</title>

    <script>
        var sortByRating = false;
        var sortByReviewCount = false;

        function toggleSortByRating() {
            sortByRating = !sortByRating;
            sortByReviewCount = false;
            updateSortButtons();
            console.log("별점 높은 순으로 정렬: " + sortByRating);
            window.location.href = '/StoreController?action=listByRating';
        }

        function toggleSortByReviewCount() {
            sortByReviewCount = !sortByReviewCount;
            sortByRating = false;
            updateSortButtons();
            console.log("리뷰 많은 순으로 정렬: " + sortByReviewCount);
            fetchSortedStoreList(sortByRating, sortByReviewCount);
        }

        function updateSortButtons() {
            var ratingButton = document.getElementById("ratingButton");
            var reviewCountButton = document.getElementById("reviewCountButton");

            ratingButton.classList.toggle("btn-primary", sortByRating);
            ratingButton.classList.toggle("btn-secondary", !sortByRating);

            reviewCountButton.classList.toggle("btn-primary", sortByReviewCount);
            reviewCountButton.classList.toggle("btn-secondary", !sortByReviewCount);
        }
    </script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">TIP-REVIEW</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="#">로그인</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">회원가입</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container w-75 mt-5 mx-auto">
        <div class="d-flex justify-content-between">
            <h2>가게 목록</h2>
            <div class="btn-group mt-3">
                <button id="ratingButton" type="button" class="btn btn-secondary" onclick="toggleSortByRating()">별점 높은 순</button>
                <button id="reviewCountButton" type="button" class="btn btn-secondary" onclick="toggleSortByReviewCount()">리뷰 많은 순</button>
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
                <a href="?action=getStore&storeId=<%= store.getStoreId() %>" class="text-decoration-none">[<%= (i + 1) %>] <%= store.getStoreName() %> <%= store.getRating() %> <%= store.getReviewCount() %></a>
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