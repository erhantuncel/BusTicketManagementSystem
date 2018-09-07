<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../fragments/header.jsp" />

<c:url var="home" value="/" scope="request" />
<c:choose>
	<c:when test="${not empty ticketForSave.departure}">
		<c:set var="departureId" value="${ticketForSave.departure.id}" />
		<c:set var="arrivalId" value="${ticketForSave.arrival.id}" />
	</c:when>
	<c:otherwise>
		<c:set var="departureId" value="0" />
		<c:set var="arrivalId" value="0" />
	</c:otherwise>
</c:choose>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<!-- 
		<h1>Özet</h1>
		 -->
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-3">
						<div class="box box-primary">
							<div class="box-body table-responsive" style="height: 74vh;">
								<form id="searchForm" action="${home}seferler" method="POST">
									<div class="form-group">
										<label class="control-label">Kalkýþ</label> 
										<select id="departureCitySelect" class="form-control" name="departureCity">
		       								<option value="${city.key}">${city.value}</option>
											<c:forEach items="${cityMap}" var="city">
												<option value="${city.key}">${city.value}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label class="control-label">Varýþ</label> 
										<select id="arrivalCitySelect" class="form-control" name="arrivalCity">
		       								<option value="${city.key}">${city.value}</option>
											<c:forEach items="${cityMap}" var="city">
												<option value="${city.key}">${city.value}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label class="control-label">Tarih</label>
										<div class="input-group date">
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span> 
											<input class="form-control datepicker" id="voyageDate" name="date" />
										</div>
									</div>
									<sec:authorize access="isAuthenticated()">
										<div class="checkbox">
											<label> <input type="checkbox" name="isReservation" value="ticketIsReservation">Rezervasyon Yap</label>
										</div>										
									</sec:authorize>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<div class="form-group">
										<button class="btn btn-primary btn-flat form-control">Sefer Ara</button>
									</div>
								</form>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<div class="col-sm-6">
						<div class="box box-primary">
							<div class="box-body table-responsive" style="height: 74vh;">
								<div class="row" style="font-size: medium; padding-bottom: 10px;">
									<div class="col-sm-5">
										<b>Kalkýþ : </b> ${departureCityForVoyageList.cityName}
									</div>
									<div class="col-sm-1">
									</div>
									<div class="col-sm-5">
										<b>Varýþ : </b> ${arrivalCityForVoyageList.cityName}
									</div>
									<div class="col-sm-1">
									</div>
								</div>
								<table id="voyageListTable" class="table table-condensed">
									<thead>
										<tr>
											<th style="width: 15%">Sefer No</th>
											<th style="width: 20%">Tarih</th>
											<th style="width: 20%">Saat</th>
											<th style="width: 20%">Ücret</th>
											<th style="width: 20%">Ýþlemler</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty voyageMap}">
												<c:forEach items="${voyageMap}" var="voyageMap">
													<tr>
														<td>${voyageMap.key}</td>
														<td><fmt:formatDate value="${voyageMap.value}"
																type="date" pattern="dd.MM.yyyy" var="voyageDepartureDate" />
															${voyageDepartureDate}
														</td>
														<td>
															<fmt:formatDate value="${voyageMap.value}"
																type="date" pattern="HH:mm" var="voyageDepartureTime" />
															${voyageDepartureTime}							
														</td>
														<td>&#8378;
															<fmt:setLocale value="tr_TR"/>
	                  										<fmt:formatNumber type="currency" currencySymbol="" maxFractionDigits="2" groupingUsed="true" value="${ticketForSave.price}" />
														</td>
														<td class="text-center"> 
															<a class="btn btn-xs btn-success btn-flat showStopsButton" data-id="${voyageMap.key}">Duraklar</a>
															<!-- 
															<a class="btn btn-primary btn-xs btn-flat selectVoyageButton" href="${home}musteri/yolcubilgileri/sefer/${voyageMap.key}" data-id="${voyageMap.key}">Seç</a>	
															 -->
															<a class="btn btn-primary btn-xs btn-flat selectVoyageButton" data-id="${voyageMap.key}">Seç</a>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td class="text-center" colspan="5">Seçtiðiniz kriterlere uygun sefer bulunamadý.</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<form id="seatNumberForm" action="${home}/yolcubilgileri" method="POST">
							<div class="box box-primary">
								<div class="box-body table-responsive" style="height: 67vh;">
									<div id="busSchemaContainer" class="col-xs-12">
										<!-- Otobüs Þemasý - Dinamik -->
										<table class="busSchema">
											<c:set var="seatNumber" value="${1}"/>
											<c:forEach var="i" begin="0" end="12">
												<tr>
													<c:forEach var="j" begin="0" end="2">
														<fmt:formatNumber minIntegerDigits="2" value="${seatNumber}" var="number"/>
														<c:choose>
															<c:when test="${i == 7 && j != 0}">
																<td class="doubleSeat"></td>
															</c:when>
															<c:otherwise>
																<td class="${(j==0)?'singleSeat':'doubleSeat'}" data-col="${j+1}" data-seatnumber="${number}">
																	&nbsp;<input type="checkbox" name="selectedSeatNumbers" value="${number}">&nbsp;&nbsp;&nbsp;${number}
																</td>
																<c:set var="seatNumber" value="${seatNumber+1}" />
															</c:otherwise>
														</c:choose>
														<c:if test="${i == 0 && j == 0}">
															<td class="hole" rowspan="13"></td>
														</c:if>
													</c:forEach>
												</tr>	
											</c:forEach>
										</table>
									</div>
								</div>
								<div class="overlay">
								</div>
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
									<div class="row">
										<div class="col-sm-12">								
											<button class="btn btn-primary btn-sm btn-block" type="submit">Yolcu Bilgileri</button>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<table class="busSchemaTemplate hide">
	<c:set var="seatNumber" value="${1}"/>
	<c:forEach var="i" begin="0" end="12">
		<tr>
			<c:forEach var="j" begin="0" end="2">
				<fmt:formatNumber minIntegerDigits="2" value="${seatNumber}" var="number"/>
				<c:choose>
					<c:when test="${i == 7 && j != 0}">
						<td class="doubleSeat"></td>
					</c:when>
					<c:otherwise>
						<td class="${(j==0)?'singleSeat':'doubleSeat'}" data-col="${j+1}" data-seatnumber="${number}">
							&nbsp;<input type="checkbox" name="selectedSeatNumbers" value="${number}">&nbsp;&nbsp;&nbsp;${number}
						</td>
						<c:set var="seatNumber" value="${seatNumber+1}" />
					</c:otherwise>
				</c:choose>
				<c:if test="${i == 0 && j == 0}">
					<td class="hole" rowspan="13"></td>
				</c:if>
			</c:forEach>
		</tr>	
	</c:forEach>
</table>


<div class="modal" id="stopsModal" tabindex="-1" role="dialog" aria-labelledby="stopsModalLabel">
	<div class="modal-dialog modal-sm">
		<form class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<!-- 
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					 -->
					<h4 class="modal-title">Duraklar</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-1">
							
						</div>
						<div class="col-xs-10 text-center">
							<table id="stopsModalTable" class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 28%">17:00 - </th>
									<td class="text-left" style="width: 75%">BOLU</td>
								</tr>
								<tr>
									<th style="width: 28%">17:00 - </th>
									<td class="text-left" style="width: 75%">BOLU</td>
								</tr>
							</table>								
						</div>
						<div class="col-xs-1">
							
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="closeButton" data-dismiss="modal" class="btn btn-flat btn-danger">Kapat</a>
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
 


<script>
	
	$(document).ready(function() {	
		
		callSelect2();
		
		// Datepicker - voyage
		$('.datepicker').datepicker({
			language : 'tr',
			autoclose : true,
			orientation: "bottom auto",
			todayHighlight: true,
			startDate: new Date(),
		});
		
		$("#departureCitySelect").on("change", function() {
			$("#arrivalCitySelect").select2("open");
		});
		
		$("#arrivalCitySelect").on("change", function() {
			$('.datepicker').focus();
		});
	});
	
	
	function callSelect2() {
		$("#departureCitySelect").select2({
			placeholder: "Þehir Seçiniz",
			language: "tr"
		});
		
		$("#arrivalCitySelect").select2({
			placeholder: "Þehir Seçiniz",
			language: "tr"
		});
	}
	
	$(".showStopsButton").click(function(e) {
		var voyageId = $(this).data("id");
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : "${home}/duraklar/sefer/" + voyageId,
			dataType : 'json',
			success : function(data) {
				$("#stopsModalTable").empty();
				$.each(data.stopMap, function(cityName, time) {
					// console.log("Durak = " + cityName + " Saat = " + time);
					$("#stopsModalTable").append('<tr><th style="width: 28%">' + time + 
							' - </th><td class="text-left" style="width: 75%">' + cityName + '</td></tr>');
				});
				
			},
			error : function(e) {
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
		
		$("#stopsModal").modal("show");
	});
	
	$(".selectVoyageButton").click(function(e) {
		e.preventDefault();
		clearBusScheme();
		var voyageId = $(this).data("id");			
		var departureId = "${departureId}";
		var arrivalId = "${arrivalId}"
		if((arrivalId != 0) && (departureId != 0) && (arrivalId != 0) ) {			
			$.ajax({
				type : "GET",
				contentType : "application/json",
				url : "${home}/koltukNumaralari/sefer/" + voyageId + "/kalkis/" + departureId + "/varis/" + arrivalId,
				dataType : 'json',
				success : function(data) {
					$.each(data.seatNumbersAndGender, function(number, genderTr) {
						// console.log("seat number = " + seatNumber + " gender = " + gender);
						var gender;
						if(genderTr == "ERKEK") {
							gender = "male";
						} else {
							gender = "female";
						}
						var seatNumber = (number < 10 ? '0' : '') + number;
						var column = $('.busSchema td[data-seatnumber="' + seatNumber + '"]');
						column.attr('data-gender', gender);
						column.html('<img src="${home}/resources/icon/' + gender + '-icon-24.png">&nbsp;' + seatNumber);
					});
					$(".overlay").hide();
				},
				error : function(e) {
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		}
	});
	
	function clearBusScheme() {
		$("table.busSchema").remove();
		var emptyBusScheme = $(".busSchemaTemplate").clone();
		emptyBusScheme.toggleClass("busSchemaTemplate busSchema");
		emptyBusScheme.removeClass("hide");
		$("#busSchemaContainer").append(emptyBusScheme);
	}
	
</script>

</body>
</html>

