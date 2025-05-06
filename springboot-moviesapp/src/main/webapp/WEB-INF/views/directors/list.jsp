<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h4>Directors</h4>
        <a href="<c:url value='/directors/new' />" class="btn btn-primary">Add New Director</a>
    </div>
    <div class="card-body">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Date of Birth</th>
                    <th>Nationality</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="director" items="${directors}">
                    <tr>
                        <td>${director.id}</td>
                        <td>${director.firstName} ${director.lastName}</td>
                        <td>
                            <fmt:parseDate value="${director.dateOfBirth}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                            <fmt:formatDate value="${parsedDate}" type="date" pattern="MMM dd, yyyy" />
                        </td>
                        <td>${director.nationality}</td>
                        <td>
                            <a href="<c:url value='/directors/${director.id}/edit' />" class="btn btn-sm btn-warning">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="../common/footer.jsp" %> 