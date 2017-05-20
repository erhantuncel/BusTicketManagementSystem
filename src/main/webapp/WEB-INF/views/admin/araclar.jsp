<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Araçlar</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title"></h3>
						<div class="pull-left" style="padding-top: 0px;">
							<a class="btn btn-sm btn-success btn-flat" title="Araç Ekle" href="<c:url value="/admin/aracEkle"/>">
								<i class="fa fa-plus"></i>&nbsp;&nbsp;Araç Ekle
							</a>													
						</div>
						<div class="box-tools">
							<div class="input-group input-xs" style="width: 150px;">
								<input type="text" id="searchVehicle" name="table_search"
									class="form-control input-sm" placeholder="Ara">
								<div class="input-group-btn">
									<button class="btn btn-sm btn-success btn-flat">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive" style="height: 60vh;">
						<table id="vehicleListTable" class="table table-hover" style="text-align: center;">
							<thead>
								<tr>
									<th style="width: 15%; text-align: center;">Plaka</th>
									<th style="width: 15%; text-align: center;">Marka</th>
									<th style="width: 15%; text-align: center;">Model</th>
									<th style="width: 15%; text-align: center;">Model Yýlý</th>
									<th style="width: 15%; text-align: center;">Mesafe</th>
									<th style="width: 10%; text-align: center;">Ýþlemler</th>
								</tr>							
							</thead>
							<tbody>							
								<c:forEach items="${vehicleList}" var="vehicle">
									<tr>
										<td>${vehicle.plateCode}</td>
										<td>${vehicle.model.brand.name}</td>
										<td>${vehicle.model.modelName}</td>
										<td>${vehicle.year}</td>
										<td>${vehicle.milage}</td>
										<td>
											<a class="btn btn-xs btn-success btn-flat updateVehicle" title="Güncelle" data-id="${vehicle.id}">
												<i class="fa fa-refresh"></i>
											</a>
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

<div class="modal bs-example" id="vehicleModal" tabindex="-1" role="dialog" aria-labelledby="vehicleModalLabel">
	<div class="modal-dialog">
		<form:form id="updateForm" class="form-horizontal" modelAttribute="updateMilageForm" action="${home}admin/aracEkle" method="POST">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Araç Detaylarý</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<table class="table table-condensed">
								<tr>
									<th style="width: 20%">Araç No:</th>
									<td id="vehicleNumber" style="width: 30%"></td>
									<th style="width: 20%">Marka</th>
									<td id="vehicleBrand" style="width: 30%"></td>
								</tr>
								<tr>
									<th >Plaka</th>
									<td id="plateCode"></td>
									<th >Model</th>
									<td id="vehicleModel"></td>
								</tr>
								<tr>
									<th >Model Yýlý</th>
									<td id="year"></td>
									<th >Mesafe</th>
									<td id="milage">
	       									<spring:bind path="milage">
	       										<form:input path="milage" type="text" class="form-control" id="milageInput"/>
	       									</spring:bind>
									</td>
								</tr>
							</table>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="updateButton" class="btn btn-flat btn-success">Güncelle</a>
				</div>
			</div>
		<!-- /.modal-content -->
		</form:form>
	</div>
	<!-- /.modal-dialog -->
</div>


<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />

<c:url var="home" value="/" scope="request" />

<c:if test="${not empty msg }">
	<script>
		$(function() {
			callNotify("${msg}", "${warningType}");
		});
	</script>
</c:if>

<script>	
	$(document).ready(function() {
		var dataTable = $("#vehicleListTable").DataTable({
			"language": {
			      "emptyTable": "Herhangi bir araç bulunamadý."
			    },
			"paging" : false,
			"info" : false,
			"order": [[0, "asc"]],
			"search" : {
				"caseInsensitive" : true
			},
			"sDom" : "ltipr"
		});
		
		$("#searchVehicle").keyup(function() {
			dataTable.search(this.value).draw();
		});
	
	});
	
	$(".updateVehicle").click(function(e) {
		e.preventDefault();
		var vehicleId = $(this).data("id");
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}admin/aracDetay/"+vehicleId,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				populateVehicleData(data);
				$("#vehicleModal").modal("show");
				$("#milageInput").focus();
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$("#ticketNumber").html(e);
				$("#ticketModal").modal("show");
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
	
	$("#updateButton").on("click", function(e) {
		e.preventDefault();
		var vehicleId = $("#vehicleNumber").html();
		var actionURL = "${home}/admin/arac/" + vehicleId + "/guncelle";
		$("#updateForm").attr("action", actionURL).submit();
	})
	
	function populateVehicleData(data) {
		$("#vehicleNumber").html(data.vehicle.id);
		$("#vehicleBrand").html(data.vehicle.model.brand.name);
		$("#plateCode").html(data.vehicle.plateCode);
		$("#vehicleModel").html(data.vehicle.model.modelName);
		$("#year").html(data.vehicle.year);
		$("#milageInput").val(data.vehicle.milage);
	}
	
	
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

