<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../fragments/header.jsp" />

<c:url var="home" value="/" scope="request" />
<jsp:useBean id="now" class="java.util.Date"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Biletlerim</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="box box-primary">
							<div class="box-body table-responsive" style="height: 70vh;">
								<table id="ticketListTable" class="table table-condensed tableCentered">
									<thead>
										<tr>
											<th>Bilet No</th>
											<th>Yolcu Ad Soyad</th>
											<th>Kalkýþ</th>
											<th>Varýþ</th>
											<th>Kalkýþ Zamaný</th>
											<th>Koltuk No</th>
											<th>Ücret</th>
											<th>Rezerv. Bitiþ</th>
											<th>Ýþlemler</th>
										</tr>
									</thead>
									<tbody>
										<!-- 
										<tr>
											<td>1545</td>
											<td>Erhan TUNÇEL</td>
											<td>Bolu</td>
											<td>Ankara</td>
											<td>15.10.2015 18:00</td>
											<td>14</td>
											<td><span class="fa fa-turkish-lira"></span> 28.00</td>
											<td><span class="label label-warning">Rezervasyon</span></td>
											<td>
												<a class="btn btn-xs btn-primary" style="width: 85px;"><i class="fa fa-ticket"></i>&nbsp;Bilete Çevir</a>
											</td>
										</tr>
										 -->
										<c:forEach items="${ticketLists}" var="ticket">
											<tr>
												<td>${ticket.id}</td>
												<td>${ticket.passangerName}&nbsp;${ticket.passangerSurname}</td>
												<td>${ticket.departure.cityName}</td>
												<td>${ticket.arrival.cityName}</td>
												<td id="depatureColumn">
													<fmt:formatDate value="${ticket.departureTime}"
															type="date" pattern="dd.MM.yyyy HH:mm" var="ticketDepartureTime"/>
														${ticketDepartureTime}
												</td>
												<td>${ticket.seatNumber}</td>
												<td>
													<c:if test="${not ticket.isReservation}">
														<span class="fa fa-turkish-lira"></span> ${ticket.price}												
													</c:if>
												</td>
												<td>
													<c:choose>
														<c:when test="${ticket.isReservation}">
															<fmt:formatDate value="${ticket.reservExpirationDate}"
																	type="date" pattern="dd.MM.yyyy HH:mm:ss" var="ticketReservExpirationDate"/>
																${ticketReservExpirationDate}													
														</c:when>													
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${ticket.isReservation}">													
															<a class="btn btn-xs btn-primary convertTicketButton" style="width: 85px;" 
																	data-id="${ticket.id}">
																<i class="fa fa-ticket"></i>&nbsp;Bilete Çevir
															</a>
														</c:when>
														<c:otherwise>													
															<a class="btn btn-xs btn-danger deleteTicketButton" style="width: 85px;" 
																	data-id="${ticket.id}">
																<i class="glyphicon glyphicon-trash"></i>&nbsp;Sil
															</a>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<div class="modal" id="deleteTicketModal" tabindex="-1" role="dialog" aria-labelledby="deleteTicketModalLabel">
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
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-1">
							
						</div>
						<div class="col-xs-10 text-center">
							<span class="fa fa-warning text-red" style="font-size: 2.5em;"></span>
							<h3 class="text-red deleteMessage"><strong>-- tarihli bilet silinecek. Onaylýyor musunuz?</strong></h3>								
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

<div class="modal" id="convertTicketModal" tabindex="-1" role="dialog" aria-labelledby="convertTicketModalLabel">
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
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-1">
							
						</div>
						<div class="col-xs-10 text-center">
							<div class="form-group">
       							<label for="cardNumber" class="col-sm-2 control-label">Kart Numarasý</label>
       							<div class="col-sm-4">
       								<input id="cardNumber" type="text" class="form-control" value="0000000000000000">
       							</div>
       						</div>
       						<div class="form-group">
       							<label for="month" class="col-sm-2 control-label">Ay</label>
       							<div class="col-sm-4">
       								<input id="month" type="text" class="form-control" value="11">
       							</div>
       						</div>
       						<div class="form-group">
       							<label for="year" class="col-sm-2 control-label">Yýl</label>
       							<div class="col-sm-4">
       								<input id="year" type="text" class="form-control" value="2019">
       							</div>
       						</div>
       						<div class="form-group">
       							<label for="cvc" class="col-sm-2 control-label">CVC</label>
       							<div class="col-sm-4">
       								<input id="cvc" type="password" class="form-control" value="374">
       							</div>
       						</div>							
						</div>
						<div class="col-xs-1">
							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="cancelButton" data-dismiss="modal" class="btn btn-flat btn-danger">Vazgeç</a>
					<a id="payButton" class="btn btn-flat btn-primary" href="">Ödeme Yap</a>
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
		var dataTable = $("#ticketListTable").DataTable({
			"language": {
			      "emptyTable": "Bilet bulunamadý."
			    },
			"paging" : false,
			"info" : false,
			"order": [[4, "desc"]],
			"searching" : false,
		});
	
	});

	$(".deleteTicketButton").click(function(e) {
		var ticketId = $(this).data("id");
		var departureTime = $("#depatureColumn").text();
		var deleteUrl = "${home}musteri/bilet/" + ticketId + "/sil";
		$(".deleteMessage").html(departureTime + " tarihli bilet silinecek. Onaylýyor musunuz?");
		$("#okButton").attr("href", deleteUrl);
		$("#deleteTicketModal").modal("show");
	});
	
	$(".convertTicketButton").click(function(e) {
		var ticketId = $(this).data("id");
		var convertUrl = "${home}musteri/bileteCevir/" + ticketId;
		$("#payButton").attr("href", convertUrl);
		$("#convertTicketModal").modal("show");
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

