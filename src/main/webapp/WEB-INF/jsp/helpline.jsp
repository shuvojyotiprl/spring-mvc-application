<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
	<form:form method="post" modelAttribute="helpline">
		<form:hidden path="id" />
		<fieldset class="form-group">
			<form:label path="category">Category</form:label>
			<br>
			<form:select path="category" class="form-select"
				aria-label="Default select example">
				<option value="technical">Technical</option>
				<option value="process">Process</option>
				<option value="payroll">Payroll</option>
			</form:select>


			<form:errors path="category" cssClass="text-warning" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="description">Description</form:label>
			<form:input path="description" type="text" class="form-control"
				required="required" />
			<form:errors path="description" cssClass="text-warning" />

		</fieldset>

		<button type="submit" class="btn btn-success">Create</button>

	</form:form>
</div>
<%@ include file="common/footer.jspf"%>