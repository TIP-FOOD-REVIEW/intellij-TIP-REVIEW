<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 작성</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">리뷰 작성</h2>

    <form action="/reviewController?action=postReview" method="post">
        <!-- Hidden fields for userId and storeId -->

        <input type="hidden" name="userId" value="<%= session.getAttribute("userId") %>">
        <!-- <input type="hidden" name="storeId" value="<%= request.getAttribute("storeId") %>"> -->
        <input type="hidden" name="storeId" value="1">

        <!-- Content input -->
        <div class="mb-3">
            <label for="content" class="form-label">리뷰 내용</label>
            <textarea class="form-control" id="content" name="content" rows="3" required></textarea>
        </div>

        <!-- Rating input -->
        <div class="mb-3">
            <label for="rating" class="form-label">평점</label>
            <input type="number" class="form-control" id="rating" name="rating" min="1" max="5" required>
        </div>

        <!-- Image input -->
        <div class="mb-3">
            <label for="image" class="form-label">이미지 업로드</label>
            <input type="file" class="form-control" id="image" name="image">
        </div>

        <button type="submit" class="btn btn-primary">리뷰 작성</button>
    </form>
</div>

</body>
</html>
