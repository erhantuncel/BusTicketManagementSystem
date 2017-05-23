<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<c:url var="home" value="/" scope="request" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Müþteri Bilgileri</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->

		<div class="row">
			<div class="col-sm-12">
				<div class="box box-success">
					<!-- 
					<div class="box-header">
						<h3 class="box-title">Bilgiler</h3>
						<div class="box-tools"></div>
					</div>
					 -->
					<!-- /.box-header -->
					<div class="box-body">
						<div class="col-xs-4">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">T.C Numarasý :</th>
									<td style="width: 15%">${customerForDetails.tcNumber}</td>
								</tr>
								<tr>
									<th>Ad :</th>
									<td>${customerForDetails.name}</td>
								</tr>
								<tr>
									<th>Soyad :</th>
									<td>${customerForDetails.surname}</td>
								</tr>
								<tr>
									<th>Doðum Tarihi :</th>
									<td>
										<fmt:formatDate value="${customerForDetails.dateOfBirth}"
										type="date" pattern="dd.MM.yyyy" var="dateOfBirth" />
										${dateOfBirth}
									</td>
								</tr>

							</table>
						</div>
						<div class="col-xs-4">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">Cep Telefonu :</th>
									<td style="width: 15%">
										<c:set value="${customerForDetails.mobileNumber}" var="mobileNumber"/>
										<c:out value="${fn:substring(mobileNumber, 0, 3)} ${fn:substring(mobileNumber, 3, 6)} ${fn:substring(mobileNumber, 6, 11)}"></c:out>
									</td>
								</tr>
								<tr>
									<th>E-Posta :</th>
									<td>${customerForDetails.eMail}</td>
								</tr>
								<tr>
									<th>Üyelik Tarihi :</th>
									<td>
										<fmt:formatDate value="${customerForDetails.dateOfRegister}" type="date" pattern="dd.MM.yyyy HH:mm:ss" var="dateOfRegister"/>
										${dateOfRegister}
									</td>
								</tr>
								<tr>
									<th>Son Giriþ :</th>
									<td>
										<fmt:formatDate value="${customerForDetails.timeOfLastOnline}" type="date" pattern="dd.MM.yyyy HH:mm:ss" var="timeOfLastOnline"/>
										${timeOfLastOnline}
									</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-4">
							<a class="btn btn-success btn-flat" href="${home}admin/musteri/${customerForDetails.id}/guncelle" 
								title="Bilgileri Güncelle"></i>Bilgleri Güncelle</a>
						</div>
					</div>
				</div>
			</div>
			<!-- /.box -->
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="box box-success">
					<div class="box-header ">
						<h3 class="box-title">Biletler</h3>
						<div class="box-tools">
							<div class="input-group input-xs" style="width: 150px;">
								<input type="text" id="searchTicket" name="table_search"
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
					<div class="box-body table-responsive" style="height: 38vh;">
						<table id="ticketListTable" class="table table-condensed tableCentered">
							<thead>
								<tr>
									<th style="width: 8%">Bilet No</th>
									<th style="width: 14%">Yolcu Ad Soyad</th>
									<th style="width: 9%">Kalkýþ</th>
									<th style="width: 9%">Varýþ</th>
									<th style="width: 5%">Tarih</th>
									<th style="width: 5%">Saat</th>
									<th style="width: 10%">Koltuk No</th>
									<th style="width: 14%">Ýþlem Zamaný</th>
									<th style="width: 14%">Rezerv. Bitiþ</th>
									<th style="width: 4%">Ýþlem</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${customerForDetails.ticketList}" var="ticket">
									<tr>
										<td>${ticket.id}</td>
										<td>${ticket.passangerName} ${ticket.passangerSurname}</td>
										<td>${ticket.departure.cityName}</td>
										<td>${ticket.arrival.cityName}</td>
										<td>
											<fmt:formatDate value="${ticket.voyage.departureTime}" type="date" pattern="dd.MM.yyyy" var="departureDate" />
												${departureDate}
										</td>
										<td><fmt:formatDate value="${ticket.voyage.departureTime}" type="date" pattern="HH:mm" var="departureHour" />
											${departureHour}
										</td>
										<td>${ticket.seatNumber}</td>
										<td>
											<fmt:formatDate value="${ticket.registerTime}" type="date" pattern="dd.MM.yyyy HH:mm:ss" var="registerTime"/>
											${dateOfRegister}
										</td>
										<td>
											<fmt:formatDate value="${ticket.reservExpirationDate}" type="date" pattern="dd.MM.yyyy HH:mm:ss" var="reservExpirationDate"/>
											${reservExpirationDate}
										</td>
										<td>
											<a class="btn btn-xs btn-success btn-flat ticketDetails" title="Detay" data-id="${ticket.id}">
												<i class="fa fa-search"></i>
											</a>
										</td>
									</tr>
								</c:forEach>
								<!-- 
								<c:forEach begin="2" end="20" var="no">
									<tr>
										<td>${no}</td>
										<td>Erhan TUNÇEL</td>
										<td>BOLU</td>
										<td>ANKARA</td>
										<td>
											26.05.2017
										</td>
										<td>17:00
										</td>
										<td>10</td>
										<td>
											22.05.2017 14:56:06
										</td>
										<td>
											25.05.2017 14:56:06
										</td>
										<td><a class="btn btn-xs btn-danger btn-flat" title="Sil"><i
												class="fa fa-remove"></i></a></td>
									</tr>
								</c:forEach>	
								 -->
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
	$(document).ready(function() {
		var dataTable = $("#ticketListTable").DataTable({
			"language": {
			      "emptyTable": "Bilet bulunamadý."
			    },
			"paging" : false,
			"info" : false,
			"order": [[7, "desc"]],
			"search" : {
				"caseInsensitive" : true
			},
			"sDom" : "ltipr"
		});

		$("#searchTicket").keyup(function() {
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