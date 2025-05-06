<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h4>Movies</h4>
        <a href="<c:url value='/movies/new' />" class="btn btn-primary">Add New Movie</a>
    </div>
    <div class="card-body">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Release Date</th>
                    <th>Genre</th>
                    <th>Duration (min)</th>
                    <th>Rating</th>
                    <th>Director</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="movie" items="${movies}">
                    <tr>
                        <td>${movie.id}</td>
                        <td>${movie.title}</td>
                        <td>
                            <fmt:parseDate value="${movie.releaseDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                            <fmt:formatDate value="${parsedDate}" type="date" pattern="MMM dd, yyyy" />
                        </td>
                        <td>${movie.genre}</td>
                        <td>${movie.durationMinutes}</td>
                        <td>${movie.rating}</td>
                        <td>${movie.director.firstName} ${movie.director.lastName}</td>
                        <td>
                            <a href="<c:url value='/movies/${movie.id}/edit' />" class="btn btn-sm btn-warning">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="../common/footer.jsp" %> 