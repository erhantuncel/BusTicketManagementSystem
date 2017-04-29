<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<c:url var="home" value="/" scope="request" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Seferler</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title"></h3>
						<div class="box-tools">
							<form id="searchForm" class="form-inline" action="${home}admin/seferler" method="POST">
								<div class="form-group">
									<div class="input-group date">
										<input id="dateString" type="text" name="date" class="form-control input-sm date">
										<span class="input-group-addon"><i class="fa fa-calendar"></i> </span>
									</div>
								</div>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<button id="searchByDateButton" class="btn btn-success btn-sm btn-flat">Sefer Ara</button>
							</form>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive" style="height: 67vh">
						<table id="voyageListTable" class="table table-hover" style="text-align: center;">
							<thead>
								<tr>
									<th style="width: 15%; text-align: center;">Sefer No</th>
									<th style="width: 15%; text-align: center;">Tarih</th>
									<th style="width: 15%; text-align: center;">Saat</th>
									<th style="width: 15%; text-align: center;">Rota</th>
									<th style="width: 15%; text-align: center;">Araç Plakasý</th>
									<th style="width: 10%; text-align: center;">Ýþlemler</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach items="${voyageList}" var="voyage" >
									<tr>
										<td>${voyage.id}</td>
										<td data-order="${voyage.departureTime.time}">
											<fmt:formatDate value="${voyage.departureTime}"
														type="date" pattern="dd.MM.yyyy" var="voyageDepartureDate" />
													${voyageDepartureDate}
										</td>
										<td><fmt:formatDate value="${voyage.departureTime}"
														type="date" pattern="HH:mm" var="voyageDepartureTime" />
													${voyageDepartureTime}</td>
										<td>${voyage.route.routeName}</td>
										<td>${voyage.vehicle.plateCode}</td>
										
										<td><a href="<c:url value="/admin/sefer/${voyage.id}/detay"/>"
											class="btn btn-xs btn-success btn-flat" title="Detay"><i class="fa fa-search"></i></a>
											<a class="btn btn-xs btn-danger btn-flat" title="Sil" id="deleteButton" data-id="${voyage.id}" ><i class="fa fa-remove"></i></a>
										</td>
										<!-- 
										<td><a href="<c:url value="/admin/seferDetay"/>"
											class="btn btn-xs btn-primary btn-flat"><i class="fa fa-search"></i>&nbsp;Detay</a>
											<a href="<c:url value="/admin/seferGuncelle"/>" class="btn btn-xs btn-warning btn-flat"><i class="fa fa-refresh"></i>&nbsp;Güncelle</a>
											<a class="btn btn-xs btn-danger btn-flat" data-toggle="modal" data-target="#deleteVoyageModal" ><i class="fa fa-remove"></i>&nbsp;Sil</a>
										</td>
										 -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer clearfix">
						<!-- 
						<ul class="pagination pagination-green pagination-sm no-margin pull-right">
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

<div class="modal" id="deleteVoyageModal" tabindex="-1" role="dialog" aria-labelledby="deleteVoyageModalLabel">
	<div class="modal-dialog">
		<form class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<!-- 
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					 -->
					<h4 class="modal-title">Sefer Sil</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-1">
							
						</div>
						<div class="col-xs-10 text-center">
							<span class="fa fa-warning text-red" style="font-size: 2.5em;"></span>
							<h3 class="text-red"><strong>Bu sefere ait biletler de silinecek. Onaylýyor musunuz?</strong></h3>								
						</div>
						<div class="col-xs-1">
							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="cancelButton" data-dismiss="modal" class="btn btn-flat btn-danger">Vazgeç</a>
					<a id="okButton" class="btn btn-flat btn-primary" href="">Onay</a>
				</div>
			</div>
		<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />

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
			autoclose : true,
		});
	});
	
	$(document).ready(function() {
		var dataTable = $("#voyageListTable").DataTable({
			"paging" : false,
			"info" : false,
			"order": [[1, "asc"]],
			"searching" : false,
		});

	});
	
	$("#deleteButton").click(function(e) {
		var voyageId = $(this).data("id");
		var deleteUrl = "${home}/admin/sefer/" + voyageId + "/sil";
		$("#okButton").attr("href", deleteUrl)
		$("#deleteVoyageModal").modal("show");
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

