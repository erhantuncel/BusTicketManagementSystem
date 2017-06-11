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
		<h1>Müþteriler</h1>
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
							<form id="searchByTcNumberForm" class="form-inline" action="${home}admin/musteriAra" method="POST">
								<div class="form-group">
									<div class="input-group" style="width: 150px;">
										<input type="text" name="tcNumber" class="form-control input-sm pull-right" placeholder="T.C Numarasý">
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										<div class="input-group-btn">
											<button class="btn btn-sm btn-success btn-flat">
												<i class="fa fa-search"></i>
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive" style="height: 62vh;">
						<table id="customerListTable" class="table table-hover" style="text-align: center;">
							<thead>
								<tr>
									<th style="width: 18%; text-align: center;">T.C. Numarasý</th>
									<th style="width: 18%; text-align: center;">Ad Soyad</th>
									<th style="width: 18%; text-align: center;">Doðum Tarihi</th>
									<th style="width: 18%; text-align: center;">Kayýt Tarihi</th>
									<th style="width: 18%; text-align: center;">Son Giriþ Tarihi</th>
									<th style="width: 10%; text-align: center;">Ýþlemler</th>
								</tr>							
							</thead>
							<tbody>
								<c:forEach items="${customerList}" var="customer">
									<tr>
										<td>${customer.tcNumber }</td>
										<td>${customer.name}&nbsp;${customer.surname}</td>
										<td>
											<fmt:formatDate value="${customer.dateOfBirth}" type="date" pattern="dd.MM.yyyy" var="dateOfBirth"/>
											${dateOfBirth}
										</td>
										<td>
											<fmt:formatDate value="${customer.dateOfRegister}" type="date" pattern="dd.MM.yyyy HH:mm:ss" var="dateOfRegister"/>
											${dateOfRegister}
										</td>
										<td>
											<fmt:formatDate value="${customer.timeOfLastOnline}" type="date" pattern="dd.MM.yyyy HH:mm:ss" var="timeOfLastOnline"/>
											${timeOfLastOnline}
										</td>
										<td>
											<a href="<c:url value="/admin/musteri/${customer.id}/detay"/>" class="btn btn-xs btn-success btn-flat" title="Detay"><i class="fa fa-search"></i></a>
										</td>
									</tr>
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

<div class="modal" id="deleteCustomerModal" tabindex="-1" role="dialog" aria-labelledby="deleteCustomerModalLabel">
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
					<h4 class="modal-title">Araç Sil</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-6">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">T.C Numarasý :</th>
									<td style="width: 15%">21547899635</td>
								</tr>
								<tr>
									<th style="width: 30%">Ad Soyad :</th>
									<td style="width: 15%">Erhan TUNÇEL</td>
								</tr>
								<tr>
									<th style="width: 30%">Doðum Tarihi :</th>
									<td style="width: 15%">29.11.1984</td>
								</tr>
								<tr>
									<th style="width: 30%">Cep Telefonu :</th>
									<td style="width: 15%">05478896633</td>
								</tr>
								<tr>
									<th style="width: 30%">E-Posta :</th>
									<td style="width: 15%">erhan@abc.com</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-6 text-center">
							<span class="fa fa-remove text-red" style="font-size: 2.5em;"></span>
							<h3 class="text-red"><strong>Müþteri Sil!</strong></h3>								
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger pull-left"
						data-dismiss="modal">Vazgeç</button>
					<button type="submit" class="btn btn-primary">Onayla</button>
				</div>
			</div>
		<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

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
	$(document).ready(function() {
		var dataTable = $("#customerListTable").DataTable({
			"language": {
			      "emptyTable": "Müþteri bulunamadý."
			    },
			"paging" : false,
			"info" : false,
			"ordering": false,
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

