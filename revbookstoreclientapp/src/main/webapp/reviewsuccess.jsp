<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Review Submission Confirmation" />
</jsp:include>

<!-- Main Content -->
<div class="container mt-5 text-center">
    <div class="review-confirmation mt-4">
        <!-- Animated green circle with checkmark GIF -->
        <div class="checkmark-container">
            <img src="${pageContext.request.contextPath}/images/orderplaced.gif" alt="Review Submitted Successfully" class="checkmark-image" />
        </div>

        <!-- Success message -->
        <h1 class="mt-4 text-success">Review Submitted Successfully!</h1>
        <p class="lead">Thank you for your feedback. Your review has been submitted, and it will appear shortly after moderation.</p>
        
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/BuyerInventory" class="btn btn-primary">Continue Shopping</a>
        </div>
    </div>
</div>



<!-- CSS Styling -->
<style>
    .checkmark-container {
        position: relative;
        display: inline-block;
        margin-top: 20px;
    }

    .checkmark-image {
        width: 150px; /* Adjust width as needed */
        height: 150px; /* Adjust height as needed */
    }
</style>