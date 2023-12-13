<%@ page import="model.Entitiy.Store" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Food Ordering App</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <style>
        .category-button {
            background-color: #f87171;
        }
        .category-button:hover {
            background-color: #ef4444;
        }
        .category-button.active {
            background-color: #10b981;
        }
        .food-item-description {
            color: #6b7280; /* Tailwind gray-500 */
        }
        .rating-star {
            color: #fbbf24; /* Tailwind's amber-400 */
        }
    </style>
</head>
<body class="bg-gray-50">
<div class="max-w-md mx-auto p-4">
    <!-- Header -->
    <div class="flex items-center justify-between mb-4">
        <button class="p-2 rounded-full bg-gray-200">
            <span class="material-icons">arrow_back</span>
        </button>
        <h1 class="text-2xl font-bold">
            <% //Store name
                Store store = (Store) request.getAttribute("store");
            %>
            <%= store.getStoreName() %>
        </h1>
        <div></div>
        <!-- Placeholder for alignment -->
    </div>

    <!-- Search Bar -->
    <div class="mt-4">
        <input
                type="search"
                placeholder="음식 또는 가게를 입력해주세요"
                class="w-full p-3 rounded-full border-2 border-gray-300 focus:outline-none focus:border-gray-400"
        />
    </div>

    <!-- Categories -->
    <div class="mt-4 grid grid-cols-3 gap-4">
        <%= store.getDescription() %>
    </div>


    <!-- New Menu -->
    <div class="mt-8">
        <h2 class="text-xl font-semibold mb-4">신규 메뉴</h2>
        <div class="grid grid-cols-2 gap-4">
            <div class="p-4">
                <img
                        src="https://source.unsplash.com/featured/?sushi"
                        alt="Sushi"
                        class="h-32 w-full rounded-lg"
                />
                <div class="flex justify-between items-center mt-2">
                    <h3 class="font-semibold">초밥메뉴</h3>
                    <div class="flex items-center">
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                class="h-5 w-5 rating-star"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    stroke-width="2"
                                    d="M11.049 2.927c.339-.689 1.462-.689 1.8 0l1.34 2.72a1.5 1.5 0 001.342.83h3.068c.758 0 1.133.917.61 1.463l-2.493 2.512a1.5 1.5 0 00-.433 1.33l.588 3.421c.17.993-.874 1.752-1.752 1.286l-3.073-1.617a1.5 1.5 0 00-1.396 0l-3.073 1.617c-.878.466-1.922-.293-1.752-1.286l.588-3.421a1.5 1.5 0 00-.433-1.33l-2.493-2.512c-.523-.546-.148-1.463.61-1.463h3.068a1.5 1.5 0 001.342-.83l1.34-2.72z"
                            />
                        </svg>
                        <span class="ml-1">4.5</span>
                    </div>
                </div>
            </div>
            <div class="p-4">
                <img
                        src="https://source.unsplash.com/featured/?pasta"
                        alt="Pasta"
                        class="h-32 w-full rounded-lg"
                />
                <div class="flex justify-between items-center mt-2">
                    <h3 class="font-semibold">오일 파스타</h3>
                    <div class="flex items-center">
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                class="h-5 w-5 rating-star"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                        >
                            <path
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    stroke-width="2"
                                    d="M11.049 2.927c.339-.689 1.462-.689 1.8 0l1.34 2.72a1.5 1.5 0 001.342.83h3.068c.758 0 1.133.917.61 1.463l-2.493 2.512a1.5 1.5 0 00-.433 1.33l.588 3.421c.17.993-.874 1.752-1.752 1.286l-3.073-1.617a1.5 1.5 0 00-1.396 0l-3.073 1.617c-.878.466-1.922-.293-1.752-1.286l.588-3.421a1.5 1.5 0 00-.433-1.33l-2.493-2.512c-.523-.546-.148-1.463.61-1.463h3.068a1.5 1.5 0 001.342-.83l1.34-2.72z"
                            />
                        </svg>
                        <span class="ml-1">4.5</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
