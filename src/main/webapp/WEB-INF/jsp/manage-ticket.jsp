<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	
	 
	
	<div class="container">
	<div>
		Welcome User : ${name}
	</div>
		<table class="table table-striped">
			<caption>Help-line Tickets</caption>
		<thead>
				<tr>
					<th>Id</th>
					<th>Category</th>
					<th>Description</th>
					<th>Status</th>
					<th>Resolution</th>
					<th>LastUpdated</th>
					<th>Affected user</th>
					<th>Assignee</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tasks}" var="task"> 
					<tr>
						<td>${task.id}</td>
						<td>${task.category}</td>
						<td>${task.description}</td>
						<td>${task.status}</td>
						<td>${task.resoultion}</td>
						<td><fmt:formatDate value="${task.lastupdated}" pattern="dd/MM/yyyy hh:mm:s"/></td> 
						<td>${task.user}</td>
						<td>${task.assignee}</td>
						<td><a type="button" class="btn btn-success" href="/assign-ticket?id=${task.id}"
							>Assign</a></td>
						<td><a type="button" class="btn btn-warning" href="/resolve-ticket?id=${task.id}"
							>Resolve</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
<%@ include file="common/footer.jspf" %>