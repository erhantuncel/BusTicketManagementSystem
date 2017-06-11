<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../fragments/header.jsp" />

<jsp:include page="../fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Sefer Detaylarý</h1>
	</section>
	
	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-sm-4">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">Bilgiler</h3>
						<div class="box-tools" style="font-size: medium;">
							<a class="btn btn-xs btn-success btn-flat" title="Gider Ekle" href="<c:url value="/admin/giderEkle/sefer/${voyage.id}"/>">
								<i class="fa fa-plus"></i>&nbsp;&nbsp;Gider Ekle
							</a>					
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive" style="height: 67vh;">
						<table class="table table-condensed" style="font-size: medium;">
							<tr>
								<th style="width: 48%">Sefer No:</th>
								<td style="width: 52%">${voyage.id}</td>
							</tr>
							<tr>
								<th>Tarih:</th>
								<td><fmt:formatDate value="${voyage.departureTime}"
										type="date" pattern="dd.MM.yyyy" var="departureDate" />
									${departureDate}</td>
							</tr>
							<tr>
								<th>Saat :</th>
								<td>
									<fmt:formatDate value="${voyage.departureTime}"
										type="date" pattern="HH:mm" var="departureHour" />
									${departureHour}
								</td>
							</tr>
							<tr>
								<th>
									Rota /<a class="text-green" data-toggle="collapse"
									href="#stopCollapse" aria-expanded="false"
									aria-controls="stopCollapse"> <small><b>Duraklar</b></small></a>
									:
								</th>
								<td>${voyage.route.routeName}</td>
							</tr>
							<tr class="collapse" id="stopCollapse">
								<th>Duraklar :</th>
								<td>
									<c:forEach items="${stopMap}" var="stopMap">
										<fmt:formatDate value="${stopMap.value}" type="date"
											pattern="HH:mm" var="stopHour" />
		                  				${stopMap.key} - ${stopHour} <br>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<th>Araç Plakasý :</th>
								<td>${voyage.vehicle.plateCode}</td>
							</tr>
							<tr>
								<th>Araç Marka-Model :</th>
								<td>${voyage.vehicle.model.brand.name}-${voyage.vehicle.model.modelName}</td>
							</tr>
							<tr>
								<th>Bilet :</th>
								<td>${ticketCount}</td>
							</tr>
							<tr>
								<th>Rezervasyon :</th>
								<td>${reservationCount}</td>
							</tr>
							
							<c:if test="${not empty incomeTotal}">
								<tr>
									<th>Gelir:</th>
									<td><fmt:formatNumber value="${incomeTotal}" type="number"
											maxFractionDigits="2" minFractionDigits="2" var="income" />
										&#8378;${income}</td>
								</tr>
								<tr>
									<th>Gider:</th>
									<td>
										<c:set var="expenseTotal" value="${0}" /> 
										<c:forEach items="${voyage.expenseList}" var="expense">
											<c:set var="expenseTotal" value="${expenseTotal + expense.price}" />
										</c:forEach> 
										<fmt:formatNumber value="${expenseTotal}" type="number" 
											maxFractionDigits="2" minFractionDigits="2" var="expense" />
										&#8378;${expense}
									</td>
								</tr>
								<tr>
									<th>Kar / Zarar:</th>
									<td>
										<c:set var="difference" value="${incomeTotal - expenseTotal}" /> 
										<c:choose>
											<c:when test="${difference > 0}">
												<span class="text-green">&#8378;${difference}</span>
											</c:when>
											<c:when test="${difference < 0}">
												<span class="text-red">&#8378;${difference * -1}</span>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
			</div>

			<div class="col-sm-8">
				<div class="box box-success">
					<div class="box-header ">
						<h3 class="box-title">Yolcular</h3>
						<div class="box-tools">

							<div class="input-group input-xs" style="width: 150px;">
								<input type="text" id="searchPassanger" name="table_search"
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
					<div class="box-body table-responsive" style="height: 67vh">
						<table id="passangerTable"
							class="table table-condensed table-hover">
							<thead>
								<tr>
									<th style="width: 5%">No</th>
									<th>Ad Soyad</th>
									<th>Kalkýþ</th>
									<th>Varýþ</th>
									<th>Rezerv. Bitiþ</th>
									<th style="width: 9%">Ýþlemler</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${ticketSet}" var="ticket" >
									<tr>
										<td>${ticket.seatNumber}</td>
										<td style="text-align: left;">${ticket.passangerName}
											${ticket.passangerSurname}</td>
										<td>${ticket.departure.cityName}</td>
										<td>${ticket.arrival.cityName}</td>
										<td><c:choose>
												<c:when test="${ticket.isReservation}">
													<fmt:formatDate value="${ticket.reservExpirationDate}"
														type="date" pattern="dd.MM.yyyy HH:mm:ss" var="ticketReservExpressionDate" />
													${ticketReservExpressionDate}
												</c:when>
											</c:choose>
										</td>
										<td>
											<a class="btn btn-xs btn-success btn-flat ticketDetails" title="Detay" data-id="${ticket.id}">
												<i class="fa fa-search"></i>&nbsp;Detay
											</a>	
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.box-body -->
					<!-- 
					<div class="box-footer clearfix">
						<ul class="pagination pagination-sm no-margin pull-right">
							<li class="disabled"><a href="#">&laquo;</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>
					</div>
						 -->
				</div>
				<!-- /.box -->
			</div>
			<!-- /.box -->
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!-- Ticket Modal -->
<div class="modal bs-example-modal-lg" id="ticketModal" tabindex="-1" role="dialog" aria-labelledby="ticketModalLabel">
	<div class="modal-dialog modal-lg">
		<form class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Bilet Detaylarý</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-8">
							<table class="table table-condensed">
								<tr>
									<th style="width: 20%">Bilet No:</th>
									<td id="ticketNumber" style="width: 30%"></td>
									<th style="width: 20%">T.C. No:</th>
									<td id="tcNumber" style="width: 30%"></td>
								</tr>
								<tr>
									<th >Sefer No:</th>
									<td id="voyageId"></td>
									<th >Yolcu Ad:</th>
									<td id="passangerName"></td>
								</tr>
								<tr>
									<th >Rota:</th>
									<td id="routeName"></td>
									<th >Yolcu Soyad:</th>
									<td id="passangerSurname"></td>
								</tr>
								<tr>
									<th >Kalkýþ:</th>
									<td id="departure"></td>
									<th >Yolcu Cinsiyet:</th>
									<td id="passangerGender"></td>
								</tr>
								<tr>
									<th >Varýþ:</th>
									<td id="arrival"></td>
									<th >Koltuk No:</th>
									<td id="seatNumber"></td>
								</tr>
								<tr>
									<th >Tarih:</th>
									<td id="departureDate"></td>
									<th >Kayýt Zamaný:</th>
									<td id="registerTime"></td>
								</tr>
								<tr>
									<th >Saat:</th>
									<td id="departureTime"></td>
									<th >Ücret:</th>
									<td id="price"></td>
								</tr>
								<tr>
									<th >Plaka:</th>
									<td id="plateCode"></td>
									<th id="reservExpirationLabel">Rezervasyon:</th>
									<td id="reservExpirationDate"></td>
								</tr>
							</table>
						</div>
						<div class="col-sm-2"></div>
					</div>
					<div id="cancelWarning" class="row">
						<h4 class="text-red text-center" ><strong>Bilet silinecek. Onaylýyor musunuz?</strong></h4>
					</div>
				</div>
				<div class="modal-footer">
					<a id="printButton" class="btn btn-flat btn-primary">Yazdýr</a>
					<a id="deleteButton" class="btn btn-flat btn-danger">Ýptal Et</a>
					<a id="closeButton" data-dismiss="modal" class="btn btn-flat btn-primary">Kapat</a>
					<a id="cancelButton" data-dismiss="modal" class="btn btn-flat btn-default">Vazgeç</a>
					<a id="okButton" class="btn btn-flat btn-danger">Onay</a>
				</div>
			</div>
		<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal-dialog -->
</div>

 <!-- 
<div style="height: 15px;"></div>
 -->

<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

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
		var dataTable = $("#passangerTable").DataTable({
			"language": {
			      "emptyTable": "Bu seferde kayýtlý yolcu bulunamadý."
			    },
			"paging" : false,
			"info" : false,
			"search" : {
				"caseInsensitive" : true
			},
			"sDom" : "ltipr"
		});

		$("#searchPassanger").keyup(function() {
			dataTable.search(this.value).draw();
		});
	});
	
	$(".ticketDetails").click(function(e) {
		e.preventDefault();
		$("#cancelButton").hide();
		$("#okButton").hide();
		$("#cancelWarning").hide();
		$("#printButton").show();
		$("#closeButton").show();
		$("#deleteButton").show();
		var ticketId = $(this).data("id");
		var deleteUrl = "${home}/admin/bilet/" + ticketId + "/sil";
		$("#okButton").attr("href", deleteUrl)
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}admin/biletDetay/"+ticketId,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				populateTicket(data);
				if(data.ticket.isReservation) {
					$("#reservExpirationLabel").html("Rezervasyon:");
					$("#reservExpirationDate").css("color", "red");
					$("#printButton").hide();
				} else {
					$("#reservExpirationLabel").html(" ");
					$("#reservExpirationDate").html(" ");
					$("#printButton").show();
				}
				$("#ticketModal").modal("show");
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
	
	function populateTicket(data) {
		$("#ticketNumber").html(data.ticket.id);
		$("#tcNumber").html(data.ticket.passangerTcNumber);
		$("#voyageId").html(data.ticket.voyage.id);
		$("#passangerName").html(data.ticket.passangerName);
		$("#routeName").html(data.ticket.voyage.route.routeName);
		$("#passangerSurname").html(data.ticket.passangerSurname);
		$("#departure").html(data.ticket.departure.cityName);
		$("#passangerGender").html(data.ticket.passangerGender);
		$("#arrival").html(data.ticket.arrival.cityName);
		$("#seatNumber").html(data.ticket.seatNumber);
		var departureTimeArr = data.ticket.voyage.departureTime.split(" ");
		$("#departureDate").html(departureTimeArr[0]);
		$("#registerTime").html(data.ticket.registerTime);
		$("#departureTime").html(departureTimeArr[1]);
		$("#price").html(data.ticket.price);
		$("#plateCode").html(data.ticket.voyage.vehicle.plateCode);
		$("#price").html(data.ticket.price);
		if(data.ticket.isReservation) {
			$("#reservExpirationDate").html(data.ticket.reservExpirationDate);
		}
	}
	
	$("#deleteButton").click(function(e) {
		$("#cancelButton").show();
		$("#okButton").show();
		$("#cancelWarning").show();
		$("#printButton").hide();
		$("#closeButton").hide();
		$("#deleteButton").hide();
		
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