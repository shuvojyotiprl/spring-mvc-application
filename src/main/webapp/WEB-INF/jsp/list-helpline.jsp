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
						<td><a type="button" class="btn btn-success" href="/update-ticket?id=${task.id}"
							>Update</a></td>
						<td><a type="button" class="btn btn-warning" href="/delete-ticket?id=${task.id}"
							>Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="button" href="/create-ticket">Create A Request</a>
		</div>
	</div>
<%@ include file="common/footer.jspf" %>