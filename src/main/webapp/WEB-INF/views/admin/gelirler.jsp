<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../fragments/header.jsp" />

<jsp:include page="../fragments/mainSideBar.jsp" />

<c:url var="home" value="/" scope="request" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Gelirler</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<!-- box-header -->
					<div class="box-header">
						<h3 class="box-title"></h3>
						<div class="box-tools">
							<form id="searchForm" class="form-inline" action="${home}admin/gelirler" method="POST">
								<div class="form-group">
									<div class="input-group date">
										<input id="dateString" type="text" name="date" class="form-control input-sm date">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<button id="searchByDateButton" class="btn btn-success btn-sm btn-flat">Gelir Ara</button>
							</form>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding" style="height: 67vh">
						<table id="incomeListTable" class="table table-hover" style="text-align: center;">
							<thead>
								<tr>
									<th style="width: 13%; text-align: center;">Kayýt Zamaný</th>
									<th style="width: 4%; text-align: center;">Sefer No</th>
									<th style="width: 13%; text-align: center;">Sefer Kalkýþ Zamaný</th>
									<th style="width: 22%; text-align: center;">Güzergâh</th>
									<th style="width: 13%; text-align: left;">Sefer Geliri</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${incomeList}" var="income">
									<tr>
										<td>
											<fmt:formatDate value="${income.registeredTime}"
														type="date" pattern="dd.MM.yyyy HH:mm:ss" var="incomeRegisteredTime" />
													${incomeRegisteredTime}
										</td>
										<td>${income.voyage.id}</td>
										<td><fmt:formatDate value="${income.voyage.departureTime}"
														type="date" pattern="dd.MM.yyyy HH:mm:ss" var="voyageDepartureTime" />
													${voyageDepartureTime}</td>
										<td>${income.voyage.route.routeName}</td>
										<td class="text-left"><span class="fa fa-turkish-lira"></span> ${income.price}</td>
									</tr>
									<!-- 
									<tr>
										<td>15.06.2016 11:00</td>
										<td>1546</td>
										<td>15.06.2016 21:30</td>
										<td>Ankara-Bolu</td>
										<td><span class="fa fa-turkish-lira"></span>792,00</td>
									</tr>
									 -->
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer clearfix">
						<!-- 
						<ul class="pagination pagination-sm no-margin pull-right">
							<li class="disabled"><a href="#">&laquo;</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>
						 -->
					</div>
				</div>
				<!-- /.box -->
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

<script>
	$(function() {
		// Datepicker - voyage
		$('.date').datepicker({
			language : 'tr',
			autoclose : true
		});
	});
	
	$(document).ready(function() {
		var dataTable = $("#incomeListTable").DataTable({
			"language": {
			      "emptyTable": "Herhangi bir gelir kaydý bulunamadý."
			    },
			"paging" : false,
			"info" : false,
			"order": [[0, "desc"]],
			"searching" : false,
		});

	});
</script>
</body>
</html>

