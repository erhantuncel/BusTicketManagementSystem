<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

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
			<div class="col-sm-5">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">Bilgiler</h3>
						<div class="box-tools" style="font-size: medium;"></div>
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

			<div class="col-sm-7">
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
									<th>Koltuk No</th>
									<th>Ad Soyad</th>
									<th>Kalkýþ</th>
									<th>Varýþ</th>
									<th>Durum</th>
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
													<a class="btn btn-xs btn-warning btn-flat btn-block"
														data-toggle="modal" data-id="${ticket.id}" data-target="#ticketModal">Rezervasyon</a>
												</c:when>
												<c:otherwise>
													<a class="btn btn-xs btn-primary btn-flat btn-block"
														data-toggle="modal" data-id="${ticket.id}" data-target="#ticketModal">Bilet</a>
												</c:otherwise>
											</c:choose></td>
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
			<!-- Otobüs Þemasý -->


			<!-- /.box -->
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!-- Ticket Modal -->
<div class="modal" id="ticketModal" tabindex="-1" role="dialog" aria-labelledby="ticketModalLabel">
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
					<h4 class="modal-title">Bilet Detaylarý</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-8">
							<p>Bilet detaylarý gelecek</p>
						</div>
						<div class="col-sm-2"></div>
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


<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />

<script>
	$(document).ready(function() {
		var dataTable = $("#passangerTable").DataTable({
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
	
	
</script>

</body>
</html>