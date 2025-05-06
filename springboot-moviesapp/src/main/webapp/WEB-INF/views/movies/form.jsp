<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="card">
    <div class="card-header">
        <h4>${movie.id == null ? 'Add New Movie' : 'Edit Movie'}</h4>
    </div>
    <div class="card-body">
        <form:form method="post" modelAttribute="movie" action="${movie.id == null ? '/movies' : '/movies/'.concat(movie.id)}">
            <div class="form-group">
                <label for="title">Title</label>
                <form:input path="title" id="title" class="form-control" required="true" />
                <form:errors path="title" class="text-danger" />
            </div>
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="releaseDate">Release Date</label>
                        <form:input path="releaseDate" id="releaseDate" class="form-control" type="date" />
                        <form:errors path="releaseDate" class="text-danger" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="genre">Genre</label>
                        <form:select path="genre" id="genre" class="form-control">
                            <form:option value="">-- Select Genre --</form:option>
                            <form:option value="Action">Action</form:option>
                            <form:option value="Adventure">Adventure</form:option>
                            <form:option value="Comedy">Comedy</form:option>
                            <form:option value="Crime">Crime</form:option>
                            <form:option value="Drama">Drama</form:option>
                            <form:option value="Fantasy">Fantasy</form:option>
                            <form:option value="Horror">Horror</form:option>
                            <form:option value="Mystery">Mystery</form:option>
                            <form:option value="Romance">Romance</form:option>
                            <form:option value="Sci-Fi">Sci-Fi</form:option>
                            <form:option value="Thriller">Thriller</form:option>
                            <form:option value="War">War</form:option>
                        </form:select>
                        <form:errors path="genre" class="text-danger" />
                    </div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="durationMinutes">Duration (minutes)</label>
                        <form:input path="durationMinutes" id="durationMinutes" class="form-control" type="number" min="1" />
                        <form:errors path="durationMinutes" class="text-danger" />
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="rating">Rating (0-10)</label>
                        <form:input path="rating" id="rating" class="form-control" type="number" step="0.1" min="0" max="10" />
                        <form:errors path="rating" class="text-danger" />
                    </div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="description">Description</label>
                <form:textarea path="description" id="description" class="form-control" rows="3" />
                <form:errors path="description" class="text-danger" />
            </div>
            
            <div class="form-group">
                <label for="director">Director</label>
                <form:select path="director" id="director" class="form-control" required="true">
                    <form:option value="">-- Select Director --</form:option>
                    <form:options items="${directors}" itemValue="id" itemLabel="firstName + ' ' + lastName" />
                </form:select>
                <form:errors path="director" class="text-danger" />
            </div>
            
            <div class="form-group mt-4">
                <button type="submit" class="btn btn-primary">Save</button>
                <a href="<c:url value='/movies' />" class="btn btn-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</div>

<%@ include file="../common/footer.jsp" %> 