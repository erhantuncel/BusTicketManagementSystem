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

<c:if test="${not empty msg }">
	<script>
		$(function() {
			callNotify("${msg}", "${warningType}");
		});
	</script>
</c:if>

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
	
	function callNotify(message, type) {
		$.notify({
			// options
			message: message,
		},{
			// settings
			element: 'body',
			position: null,
			type: type,
			allow_dismiss: true,
			newest_on_top: false,
			showProgressbar: false,
			placement: {
				from: "top",
				align: "right"
			},
			offset: {
				x: 5,
				y: 53
			},
			spacing: 10,
			z_index: 1031,
			delay: 5000,
			timer: 1000,
			url_target: '_blank',
			mouse_over: null,
			animate: {
				enter: 'animated fadeInRight',
				exit: 'animated fadeOutRight'
			},
			onShow: null,
			onShown: null,
			onClose: null,
			onClosed: null,
			icon_type: 'class',
			template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert" style="height: 50px;">' +
				'<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
				'<span data-notify="icon"></span> ' +
				'<span data-notify="title">{1}</span> ' +
				'<span data-notify="message">{2}</span>' +
				'<div class="progress" data-notify="progressbar">' +
					'<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
				'</div>' +
				'<a href="{3}" target="{4}" data-notify="url"></a>' +
			'</div>' 
		});
	}
</script>
</body>
</html>

