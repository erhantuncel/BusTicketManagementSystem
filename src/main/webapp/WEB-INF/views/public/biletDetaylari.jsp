<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../fragments/publicIndexHeader.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Your Page Content Here -->
				<div class="row vertical-center">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<div class="row">
							<div class="col-sm-12">
								<div class="box box-primary"> 
									<div class="box-body table-responsive" style="height: 70vh;">
										<div class="row">
											<div class="col-sm-12">
												<table class="table table-condensed tableCentered">
													<thead>
														<tr>
															<th>Bilet No</th>
															<th>Yolcu Ad Soyad</th>
															<th>Kalkýþ</th>
															<th>Varýþ</th>
															<th>Kalkýþ Zamaný</th>
															<th>Koltuk No</th>
															<th>Ücret</th>
															<th>Ýþlemler</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${createdTicketList}" var="ticket">
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
																	<a class="btn btn-xs btn-danger printTicketButton" style="width: 85px;" 
																			data-id="${ticket.id}">
																		<i class="glyphicon glyphicon-print"></i>&nbsp;Yazdýr
																	</a>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- <div class="col-sm-3" style=""></div>  -->
							
						</div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- ./wrapper -->
	

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
										<th></th>
										<td></td>
									</tr>
								</table>
							</div>
							<div class="col-sm-2"></div>
						</div>
					</div>
					<div class="modal-footer">
						<a id="printButton" class="btn btn-flat btn-primary">Yazdýr</a>
						<a id="closeButton" data-dismiss="modal" class="btn btn-flat btn-primary">Kapat</a>
					</div>
				</div>
			<!-- /.modal-content -->
			</form>
		</div>
		<!-- /.modal-dialog -->
	</div>
	
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
					align: "center"
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
					enter: 'animated fadeInDown',
					exit: 'animated fadeOutUp'
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
		
		$(".printTicketButton").click(function(e) {
			e.preventDefault();
			$("#printButton").show();
			$("#closeButton").show();
			var ticketId = $(this).data("id");
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${home}biletDetay/"+ticketId,
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					populateTicket(data);
					$("#printButton").show();
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
			var departureTimeArr = data.ticket.departureTime.split(" ");
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
	</script>

</body>
</html>