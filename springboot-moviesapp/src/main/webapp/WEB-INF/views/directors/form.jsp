<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="card">
    <div class="card-header">
        <h4>${director.id == null ? 'Add New Director' : 'Edit Director'}</h4>
    </div>
    <div class="card-body">
        <form:form method="post" modelAttribute="director" action="${director.id == null ? '/directors' : '/directors/'.concat(director.id)}">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <form:input path="firstName" id="firstName" class="form-control" required="true" />
                        <form:errors path="firstName" class="text-danger" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <form:input path="lastName" id="lastName" class="form-control" required="true" />
                        <form:errors path="lastName" class="text-danger" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="dateOfBirth">Date of Birth</label>
                <form:input path="dateOfBirth" id="dateOfBirth" class="form-control" type="date" />
                <form:errors path="dateOfBirth" class="text-danger" />
            </div>
            
            <div class="form-group">
                <label for="nationality">Nationality</label>
                <form:input path="nationality" id="nationality" class="form-control" />
                <form:errors path="nationality" class="text-danger" />
            </div>
            
            <div class="form-group">
                <label for="biography">Biography</label>
                <form:textarea path="biography" id="biography" class="form-control" rows="4" />
                <form:errors path="biography" class="text-danger" />
            </div>
            
            <div class="form-group mt-4">
                <button type="submit" class="btn btn-primary">Save</button>
                <a href="<c:url value='/directors' />" class="btn btn-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="../common/footer.jsp" %> 