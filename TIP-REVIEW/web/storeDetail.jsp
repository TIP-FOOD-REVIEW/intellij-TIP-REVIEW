<%@ page import="model.Entitiy.Store" %>
<%@ page import="model.Entitiy.Review" %>
<%@ page import="model.Entitiy.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Entitiy.Food" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Food Ordering App</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.16/dist/tailwind.min.css">
</head>
<body class="bg-gray-50">
<div class="max-w-md mx-auto p-1" style="max-width:34rem">
    <!-- Header -->
    <div class="flex justify-between items-center mb-6">
        <div style="padding-right:5rem">
            <button class="p-2 rounded-full bg-gray-200" onclick="location.href='/home';">
                <span class="material-icons">arrow_back</span>
            </button>
        </div>
        <h1 class="text-2xl font-bold" style="font-size: 2.5rem">
            <% //Store name
                Store store = (Store) request.getAttribute("store");
            %>
            <%= store.getStoreName() %>
        </h1>
        <div>
            <% Long storeId = store.getStoreId(); %>
            <button class="border rounded-full px-4 py-1 hover:bg-gray-200" onclick="location.href='/reviewController?action=setReview&storeId=<%=storeId%>';">리뷰 작성하기</button>
        </div>
    </div>

    <!-- Categories -->
    <div class="mb-4">
        <div class="w-full p-2 border rounded h-24">
            <%= store.getDescription() %>
        </div>
    </div>

    <!-- Search Bar -->
    <div class="mt-4" style="margin-bottom:1rem">
        <input
                type="search"
                placeholder="음식 이름을 입력해주세요"
                class="w-full p-3 rounded-full border-2 border-gray-300 focus:outline-none focus:border-gray-400"
                id="searchInput" name="name"
        />
    </div>

    <div class="flex justify-between mb-6">
        <button id="openModal" class="bg-red-500 text-white rounded-full px-4 py-2">메뉴 목록</button>
        <button class="bg-green-500 text-white rounded-full px-4 py-2" onclick="searchByName();">검색</button>
        <!-- 모달 창 -->
        <div id="myModal" class="modal hidden fixed z-50 left-0 top-0 w-full h-full overflow-auto bg-gray-600 bg-opacity-50">
            <!-- 모달 콘텐츠 -->
            <div class="modal-content bg-white w-1/2 mx-auto mt-20 p-5 border border-gray-300 rounded">
                <span id="closeModal" class="material-icons cursor-pointer float-right">close</span>

                <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    <div class="bg-white shadow rounded-lg p-4 flex justify-between items-center" style="width:inherit">
                        <div>
                            <%
                                Food[] foodList = (Food[]) request.getAttribute("foodList");
                                if(foodList != null){
                                    for(Food food : foodList) {
                            %>
                            <h2 class="text-lg font-semibold"> ※ <%= food.getFoodName() %></h2>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>
                </li>

            </div>
        </div>
        <script>
            // 모달 열기 버튼
            var modal = document.getElementById("myModal");
            var btn = document.getElementById("openModal");

            // 모달 닫기 버튼
            var span = document.getElementById("closeModal");

            // 버튼 클릭 시 모달 열기
            btn.onclick = function() {
                modal.style.display = "block";
            }

            // X 버튼 클릭 시 모달 닫기
            span.onclick = function() {
                modal.style.display = "none";
            }

            // 모달 외부 클릭 시 모달 닫기
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>
    </div>
    <script>
        function searchByName() {
            var inputValue = document.getElementById("searchInput").value;
            // URL에 입력 필드의 값을 추가하여 이동
            window.location.href = '/storeDetail?action=search&storeId=<%=storeId%>&name=' + encodeURIComponent(inputValue);
        }
    </script>
    <!-- New Menu -->
    <div class="mt-8">
        <div class="flex justify-between items-center mb-2">
            <h2 class="text-xl font-semibold">리뷰 목록</h2>
            <div>
                <!-- 별점 순 정렬 버튼 -->
                <a href="storeDetail?action=getReviewListByRatingDESC&storeId=<%=storeId%>" class="text-sm px-2">낮은 별점 순</a>
                <a href="storeDetail?action=getReviewListByRatingASC&storeId=<%=storeId%>" class="text-sm px-2">높은 별점 순</a>
                <a href="storeDetail?action=getReviewList&storeId=<%=storeId%>" class="text-sm px-2">최신 순</a>
                <!-- 추가적인 정렬 옵션 가능 -->
            </div>
        </div>
        <div class="grid grid-cols-2 gap-4">
            <%
                Review[] reviewList = (Review[]) request.getAttribute("reviewList");
                if(reviewList != null) {
                    for(Review review : reviewList) {
                        User user = (User) request.getAttribute("user_" + review.getReviewId());
                        // 리뷰 렌더링
                        String ImgUrl = review.getImage();
            %>
            <div class="p-4" style="background-color: #f1f1f1">
                <img src="images/<%=ImgUrl%>" alt="<%=ImgUrl%>" class="h-32 w-full rounded-lg" >
                <div class="flex justify-between items-center mt-2">
                    <h3 class="font-semibold"><%= user.getUsername() %></h3>
                    <div class="flex items-center">
                        <!-- 별점 표시 -->
                        <% for(int i = 0; i < review.getRating(); i++) { %>
                        <img alt="star" src="data:image/jpeg;base64,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" class="h-5 w-5">
                        <% } %>
                        <span class="ml-1"><%= review.getRating() %></span>
                    </div>
                </div>
                <div>
                    <p >
                    <%
                        if(foodList != null){
                            for(Food food : foodList) {
                    %>
                    <div style="display:flex; justify-content: flex-end;"><%= food.getFoodName() %></div>
                    <%
                            }
                        }
                    %>
                    </p>
                    <p style="font-weight:bold">리뷰 내용</p>
                    <p><%= review.getContent() %></p>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>