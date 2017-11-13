<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../fragments/header.jsp" />

<c:url var="home" value="/" scope="request" />
<c:choose>
	<c:when test="${not empty ticketForSave.departure}">
		<c:set var="voyageId" value="${ticketForSave.voyage.id}" />
		<c:set var="departureId" value="${ticketForSave.departure.id}" />
		<c:set var="arrivalId" value="${ticketForSave.arrival.id}" />
		<c:set var="isReservation" value="${ticketForSave.isReservation}" />
	</c:when>
	<c:otherwise>
		<c:set var="voyageId" value="0" />
		<c:set var="departureId" value="0" />
		<c:set var="arrivalId" value="0" />
		<c:set var="isReservation" value="false" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${ticketForSave.isReservation}">
		<c:set var="formButtonText" value="Rezervasyon Yap" />
	</c:when>
	<c:otherwise>
		<c:set var="formButtonText" value="Ödeme Yap" />
	</c:otherwise>
</c:choose>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1></h1>
	</section>

	<!-- Main content -->
	<section class="content">
		<form:form action="${home}musteri/odemeRezervasyon" modelAttribute="buyTicketForm" method="POST">
			<!-- Your Page Content Here -->
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div class="col-sm-8">
						<div class="box box-primary">
							<!-- 
							<div class="box-header">
								
							</div>
							 -->
							<!-- /.box-header -->
							<div class="box-body table-responsive" style="height: 75vh;">
								<table class="table tableCentered passengerInfo" id="passengerInfo">
									<thead>
										<tr>
											<th>Koltuk No</th>
											<th>Cinsiyet</th>
											<th>T.C. Kimlik No</th>
											<th>Yolcu Adý</th>
											<th>Yolcu Soyadý</th>
											<th>Ücret</th>
										</tr>
									</thead>
									<tbody>
										<!-- 
										<tr class="alertSeatSelection">
											<td colspan="6">
												<div class="alert alert-info alertInfoSize" role="alert">
													<span class="glyphicon glyphicon-info-sign"
														style="font-size: 4.5em;"></span>
													<h3>Koltuk seçiniz.</h3>
												</div>
											</td>
										</tr>
										 -->
										<c:forEach items="${buyTicketForm.seatList}" var="seat" varStatus="row">
											<tr>	
												<form:hidden path="seatList[${row.index}].seatNumber"/>
												<td>
													<label id="seatNumber">${seat.seatNumber}</label>
												<td>
													<spring:bind path="seatList[${row.index}].passangerGender">
														<form:select path="seatList[${row.index}].passangerGender" class="form-control input-sm">
															<form:option value=""></form:option>
															<form:options items="${genderValues}"/>
														</form:select>
													</spring:bind>
												</td>
												<td>
													<spring:bind path="seatList[${row.index}].passangerTcNumber">
														<div class="${status.error ? 'has-error' : ''}">
															<form:input path="seatList[${row.index}].passangerTcNumber" type="text" class="form-control input-sm" placeholder="Yolcu T.C. No"/> 
														</div>
													</spring:bind>
												</td>
												<td>
													<spring:bind path="seatList[${row.index}].passangerName">
														<div class="${status.error ? 'has-error' : ''}">
															<form:input path="seatList[${row.index}].passangerName" type="text" class="form-control input-sm" placeholder="Yolcu Adý"/>
														</div>
													</spring:bind>
												</td>
												<td>
													<spring:bind path="seatList[${row.index}].passangerSurname">
														<div class="${status.error ? 'has-error' : ''}">
															<form:input path="seatList[${row.index}].passangerSurname" type="text" class="form-control input-sm" placeholder="Yolcu Soyadý"/>
														</div>
													</spring:bind>
												</td>
												<td>
													<label id="ticketPrice">
														&#8378;
														<fmt:setLocale value="tr_TR"/>
			         									<fmt:formatNumber type="currency" currencySymbol="" maxFractionDigits="2" groupingUsed="true" value="${ticketForSave.price}" />
													</label>
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
					</div>
					<!-- /.box -->
					<div class="col-sm-4">
						<div class="box box-primary">
							<!-- 
							<div class="box-header with-border">
								<h3 class="box-title"></h3>
								<div class="box-tools"></div>
							</div>
							 -->
							<!-- /.box-header -->
							<div class="box-body table-responsive" style="height: 75vh;">
								<div class="col-xs-12">
									<div class="row">
										<table class="table table-condensed" style="font-size: medium;">
											<tr>
												<th style="width: 48%">Tarih :</th>
												<td style="width: 52%">
													<fmt:formatDate value="${ticketForSave.departureTime}" 
														type="date" pattern="dd.MM.yyyy" var="departureDate" />${departureDate}
												</td>
											</tr>
											<tr>
												<th>Saat :</th>
												<td>
													<fmt:formatDate value="${ticketForSave.departureTime}" 
														type="date" pattern="HH:mm" var="departureHour" />${departureHour}
												</td>
											</tr>
											<tr>
												<th>Kalkýþ :</th>
												<td>${ticketForSave.departure.cityName}</td>
											</tr>
											<tr>
												<th>Varýþ :</th>
												<td>${ticketForSave.arrival.cityName}</td>
											</tr>
											<tr>
												<th>Bilet Adeti :</th>
												<td>${fn:length(buyTicketForm.seatList)}</td>
											</tr>
											<tr>
												<th>Toplam Ücret :</th>
												<td>
													<c:set var="totalPrice" value="${fn:length(buyTicketForm.seatList)*ticketForSave.price}" />
													<span class="fa fa-turkish-lira"></span>&nbsp;
														<fmt:formatNumber value="${totalPrice}" type="number" maxFractionDigits="2" minFractionDigits="2" var="totalPriceInTwoDigits" />${totalPriceInTwoDigits}
												</td>
											</tr>
										</table>
									</div>
									<div class="row">
										<c:choose>
											<c:when test="${not ticketForSave.isReservation}">							
												<div class="form-group">
													<label for="nameOfCard" class="control-label">Kart Üzerindeki Ýsim</label>
													<input type="text" class="form-control input-sm" name="nameOfCard" disabled="disabled"/>
												</div>
												<div class="form-group">
													<label for="cardNumber" class="control-label">Kart Numarasý</label>
													<input type="text" class="form-control input-sm" name="cardNumber" disabled="disabled"/>
												</div>
												<div class="row">
													<div class="form-group col-xs-6">
														<label for="month" class="control-label">Ay</label>
														<input type="text" class="form-control input-sm" name="month" disabled="disabled"/>
													</div>
													<div class="form-group col-xs-6">
														<label for="year" class="control-label">Yýl</label>
														<input type="text" class="form-control input-sm" name="year" disabled="disabled"/>
													</div>
												</div>
												<div class="row">
													<div class="col-xs-12">
														<div class="checkbox">
															<label> <input name="rules" type="checkbox" disabled="disabled">Þartlarý okudum</label>
														</div>
													</div>
												</div>
											</c:when>
										</c:choose>
										<div class="row">
											<div class="col-xs-12">
												<input type="submit" class="btn btn-primary btn-block" value="${formButtonText}">
											</div>
										</div>
									</div>
								</div>
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
				</div>
				<div class="col-md-1"></div>
			</div>
		</form:form>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

<script>
	
</script>
</body>
</html>

