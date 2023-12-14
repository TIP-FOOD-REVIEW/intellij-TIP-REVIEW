<%@ page import="model.Entitiy.Food" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 작성</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/forms@0.3.4/dist/tailwindcss.forms.min.js"></script>
    <script src="https://unpkg.com/@fortawesome/fontawesome-free/js/all.js"></script>
    <style>
        .star-rating {
            font-size: 2em;
            cursor: pointer;
        }
        .star-rating .fa-star {
            color: #d1d5db;
        }
        .star-rating .fa-star.checked {
            color: #fbbf24;
        }
    </style>
</head>
<body class="bg-gray-100">
    <%
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null){
            response.sendRedirect("/User/loginForm.jsp");
        }
        Long storeId = Long.valueOf(request.getParameter("storeId"));
    %>
    <form action="/reviewController?action=postReview" method="post" enctype="multipart/form-data">
        <input type="hidden" name="userId" value="<%= userId %>">
        <input type="hidden" name="storeId" value="<%= storeId %>">
        <div class="container mx-auto p-8">
            <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <div class="mb-4">
                    <button id="openModal" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">음식 선택 모달창 버튼</button>
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
                                        <div class="flex items-center">
                                            <input type="checkbox" name="selectedFoods" value="<%= food.getFoodId() %>" class="mr-2">
                                            <h2 class="text-lg font-semibold"><%= food.getFoodName() %></h2>
                                        </div>
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
                        btn.onclick = function(event) {
                            // 폼 제출 또는 네비게이션 방지
                            event.preventDefault();
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

                        // 선택한 음식 ID를 저장할 배열
                        var selectedFoods = [];

                        // 체크박스가 클릭되었을 때 처리
                        var checkboxes = document.querySelectorAll('input[name="selectedFoods"]');
                        checkboxes.forEach(function(checkbox) {
                            checkbox.addEventListener('change', function() {
                                if (this.checked) {
                                    // 체크된 경우 배열에 추가
                                    selectedFoods.push(this.value);
                                } else {
                                    // 체크 해제된 경우 배열에서 제거
                                    var index = selectedFoods.indexOf(this.value);
                                    if (index !== -1) {
                                        selectedFoods.splice(index, 1);
                                    }
                                }
                            });
                        });
                    </script>
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="imageSelection">
                        이미지 선택 박스
                    </label>
                    <input name="imageFile" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="imageSelection" type="file" accept="image/*">
                </div>
                <div class="mb-4">
                    <label class="block text-gray-700 text-sm font-bold mb-2" for="contentInput">
                        내용 입력
                    </label>
                    <textarea name="reviewContent" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="contentInput" rows="4"></textarea>
                </div>
                <div class="mb-4">
                    <div class="star-rating">
                        <span class="star" data-value="1">&#9733;</span>
                        <span class="star" data-value="2">&#9733;</span>
                        <span class="star" data-value="3">&#9733;</span>
                        <span class="star" data-value="4">&#9734;</span>
                        <span class="star" data-value="5">&#9734;</span>
                    </div>
                    <input type="hidden" id="ratingField" name="rating">
                    <script>
                        const stars = document.querySelectorAll('.star');
                        let rating = 0;

                        stars.forEach(star => {
                            star.addEventListener('click', function() {
                                const ratingValue = parseInt(this.getAttribute('data-value'));
                                updateRating(ratingValue);
                            });
                        });

                        function updateRating(value) {
                            rating = value;
                            document.getElementById('ratingField').value = rating;
                            stars.forEach((star, index) => {
                                if (index < value) {
                                    star.innerHTML = '&#9733;';
                                    star.classList.add('text-star-500');
                                } else {
                                    star.innerHTML = '&#9734;';
                                    star.classList.remove('text-star-500');
                                }
                            });
                            console.log('Selected Rating:', rating);
                        }
                    </script>
                </div>
                <div class="mb-4">
                    <button type="submit" id="saveReviewButton" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">리뷰 저장 버튼</button>
                </div>
            </div>
        </div>
    </form>
</body>
</html>