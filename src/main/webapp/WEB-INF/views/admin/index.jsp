<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../fragments/header.jsp" />

<jsp:include page="../fragments/mainSideBar.jsp" />

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="year"/>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<!-- 
	<section class="content-header">
		<h1></h1>
	</section>
	 -->
	 
	<!-- Main content -->
	<section class="content" style="padding-bottom: 0px;">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-lg-3 col-xs-6">
				<!-- info box -->
				<div class="info-box">
	                <span class="info-box-icon bg-green"><i class="fa fa-ticket"></i></span>
	                <div class="info-box-content">
	                  <span class="info-box-text">SATILAN BÝLET</span>
	                  <span class="info-box-number">${ticketCount }</span>
	                </div><!-- /.info-box-content -->
				</div><!-- /.info-box -->				
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- info box -->
				<div class="info-box">
	                <span class="info-box-icon bg-green"><i class="ion ion-person-add"></i></span>
	                <div class="info-box-content">
	                  <span class="info-box-text">KAYITLI KULLANICI</span>
	                  <span class="info-box-number">${customerCount }</span>
	                </div><!-- /.info-box-content -->
				</div><!-- /.info-box -->
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- info box -->
				<div class="info-box">
	                <span class="info-box-icon bg-green"><i class="fa fa-download"></i></span>
	                <div class="info-box-content">
	                  <span class="info-box-text">YILLIK GELÝR - ${year}</span>
	                  <span class="info-box-number">&#8378; 
	                  	<fmt:setLocale value="tr_TR"/>
	                  	<fmt:formatNumber type="currency" currencySymbol="" maxFractionDigits="2" groupingUsed="true" value="${incomeAnnual}" />
	                  </span>
	                </div><!-- /.info-box-content -->
				</div><!-- /.info-box -->
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-6">
				<!-- info box -->
				<div class="info-box">
	                <span class="info-box-icon bg-green"><i class="fa fa-upload"></i></span>
	                <div class="info-box-content">
	                  <span class="info-box-text">YILLIK GÝDER - ${year}</span>
	                  <span class="info-box-number">&#8378; 
	                  	<fmt:setLocale value="tr_TR"/>
	                  	<fmt:formatNumber type="currency" currencySymbol="" maxFractionDigits="2" groupingUsed="true" value="${expenseAnnual}" />
	                  	</span>
	                </div><!-- /.info-box-content -->
				</div><!-- /.info-box -->
			</div>
			<!-- ./col -->
		</div>
		<!-- ./row -->
		<div class="row">
			<div class="col-xs-6">
				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title">Aktif Seferler</h3>
						<div class="box-tools pull-right"></div>
						<!-- /.box-tools -->
					</div>
					<!-- /.box-header -->
					<div class="box-body no-padding" style="height: 133px;">
						<table class="table table-bordered table-condensed">
							<tr>
								<th style="width: 13%;">Sefer No</th>
								<th style="width: 35%;">Güzergâh</th>
								<th>Kalkýþ</th>
								<th>Kalan Süre</th>
								<th style="width: 5%;">Ýþlem</th>
							</tr>
							<c:forEach var="voyageMap" items="${voyageMapStartedFiveHour}" begin="0" end="2">
								<tr>
									<td>${voyageMap.key.id}</td>
									<td>${voyageMap.key.route.routeName}</td>
									<fmt:formatDate value="${voyageMap.key.departureTime}" type="date" var="departureTime" pattern="dd.MM.yyy HH:mm"/>
									
									<td>${departureTime}</td>
									<td>
										${voyageMap.value}
									</td>
									<td><a href="<c:url value="/admin/sefer/${voyageMap.key.id}/detay"/>"
										class="btn btn-xs btn-success btn-flat"><i class="fa fa-search"></i>&nbsp;Detay</a></td>
								<tr>
							</c:forEach>
						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer text-center">
						<a href="<c:url value="/admin/aktifSeferler"/>" class="uppercase text-green"><b>Tüm Seferleri Göster</b></a>
					</div>
					<!-- /.box-footer -->
				</div>
				<!-- /.box -->

				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title">Yaklaþan Seferler</h3>
						<div class="box-tools pull-right"></div>
						<!-- /.box-tools -->
					</div>
					<!-- /.box-header -->
					<div class="box-body no-padding">
						<table class="table table-bordered table-condensed">
							<tr>
								<th style="width: 20%;">Sefer No</th>
								<th>Güzergâh</th>
								<th style="width: 25%;">Kalkýþ</th>
								<th style="width: 5%;">Ýþlem</th>
							</tr>
							<c:forEach items="${voyagesNextTime}" var="voyage" begin="0" end="2">
								<tr>
									<td>${voyage.id}</td>
									<td>${voyage.route.routeName}</td>
									<td>
										<fmt:formatDate value="${voyage.departureTime}" type="date" pattern="dd.MM.yyy HH:mm" var="departureTime"/>
										${departureTime}
									</td>
									<td><a href="<c:url value="/admin/sefer/${voyage.id}/detay"/>"
										class="btn btn-xs btn-success btn-flat"><i class="fa fa-search"></i>&nbsp;Detay</a></td>
								</tr>								
							</c:forEach>
						</table>
					</div>
					<!-- /.box-body -->

					<div class="box-footer text-center">
						<a href="<c:url value="/admin/yaklasanSeferler"/>" class="uppercase text-green"><b>Tüm Seferleri Göster</b></a>
					</div>
					<!-- /.box-footer -->
				</div>
				<!-- /.box -->
			</div>
			<div class="col-xs-6">
				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title">Aylara Göre Satýlan Bilet Sayýlarý - ${year}</h3>
						<div class="box-tools pull-right"></div>
						<!-- /.box-tools -->
					</div>
					<!-- /.box-header -->
					<div class="box-body" style="text-align: center;">
						<div class="chart">
							<canvas id="lineChartTicket" style="height: 149px;"></canvas>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title">Aylara Göre Kullanýcý Kayýtlarý - ${year}</h3>
						<div class="box-tools pull-right"></div>
						<!-- /.box-tools -->
					</div>
					<!-- /.box-header -->
					<div class="box-body" style="text-align: center;">
						<div class="chart">
							<canvas id="lineChartUser" style="height: 149px;"></canvas>
						</div>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

<script>
	$(document).ready(function() {
		var year = new Date().getFullYear();
		var ticketPointsUrl = "${home}admin/biletSayisi/yil/"+ year;
		drawLineChart(ticketPointsUrl, "#lineChartTicket");
		
		var customerPoitsUrl = "${home}admin/musteriSayisi/yil/"+ year;
		drawLineChart(customerPoitsUrl, "#lineChartUser");
	});
	

	function drawLineChart(jsonURL, canvasId) {	
		var jsonData = $.ajax({
			type : "GET",
			contentType : "application/json",
			url : jsonURL,
			dataType : 'json',
			success : function(results) {
				// console.log("SUCCESS: ", results);
				var linePoints = [];
				results.data.forEach(function(ticketCount) {
					linePoints.push(parseInt(ticketCount));
				});
				
				var lineData = {
						labels : [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
						datasets : [ {
							label : "Satýlan Bilet",
							fillColor : "rgba(0,166,90, 0.9)",
							strokeColor : "rgba(0,166,90, 0.8)",
							pointColor : "#00a65a",
							pointStrokeColor : "rgba(0,166,90, 1)",
							pointHighlightFill : "#fff",
							pointHighlightStroke : "rgba(0,166,90, 1)",
							// data : [ "28", "48", "40", "45", "86", "67", "90" ]
							data : linePoints
						} ]
				};
				
				var lineChartOptions = {
						//Boolean - If we should show the scale at all
						showScale : true,
						//Boolean - Whether grid lines are shown across the chart
						scaleShowGridLines : true,
						//String - Colour of the grid lines
						scaleGridLineColor : "rgba(0,0,0,.05)",
						//Number - Width of the grid lines
						scaleGridLineWidth : 1,
						//Boolean - Whether to show horizontal lines (except X axis)
						scaleShowHorizontalLines : true,
						//Boolean - Whether to show vertical lines (except Y axis)
						scaleShowVerticalLines : true,
						//Boolean - Whether the line is curved between points
						bezierCurve : true,
						//Number - Tension of the bezier curve between points
						bezierCurveTension : 0.3,
						//Boolean - Whether to show a dot for each point
						pointDot : true,
						//Number - Radius of each point dot in pixels
						pointDotRadius : 4,
						//Number - Pixel width of point dot stroke
						pointDotStrokeWidth : 1,
						//Number - amount extra to add to the radius to cater for hit detection outside the drawn point
						pointHitDetectionRadius : 20,
						//Boolean - Whether to show a stroke for datasets
						datasetStroke : true,
						//Number - Pixel width of dataset stroke
						datasetStrokeWidth : 2,
						//Boolean - Whether to fill the dataset with a color
						datasetFill : true,
						//String - A legend template
						legendTemplate : "",
						//Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
						maintainAspectRatio : true,
						//Boolean - whether to make the chart responsive to window resizing
						responsive : true
					};
				
				//-------------
				//- LINE CHART -
				//--------------
				var lineChartCanvasTicket = $(canvasId).get(0).getContext(
						"2d");
				var lineChart = new Chart(lineChartCanvasTicket);
				lineChartOptions.datasetFill = false;
				lineChart.Line(lineData, lineChartOptions);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});	
	}
</script>

</body>
</html>

