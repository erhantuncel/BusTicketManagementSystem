<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Bilet Al</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-sm-3">
				<div class="box box-success">
					<div class="box-header with-border">
						<h3 class="box-title">Otobüs Þemasý</h3>
						<div class="box-tools"></div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="col-xs-12">
							<table class="busSchema">
								<tr>
									<td class="singleSeat" data-col="1"><img
										src="<c:url value="/resources/icon/woman-icon-24.png"/>">
										01</td>
									<td class="hole" rowspan="13"></td>
									<td class="doubleSeat" data-col="2">&nbsp;<input
										type="checkbox" value="02">&nbsp;&nbsp;&nbsp;02
									</td>
									<td class="doubleSeat" data-col="3" data-gender="m"><img
										src="<c:url value="/resources/icon/man-icon-24.png"/>">&nbsp;03</td>
								</tr>
								<tr>
									<td class="singleSeat" data-col="1">&nbsp;<input
										type="checkbox" value="04">&nbsp;&nbsp;&nbsp;04
									</td>
									<td class="singleSeat" data-col="2"><img
										src="<c:url value="/resources/icon/man-icon-24.png"/>">&nbsp;05</td>
									<td class="singleSeat" data-col="3" data-gender="f"><img
										src="<c:url value="/resources/icon/woman-icon-24.png"/>">&nbsp;06</td>
								</tr>
								<tr>
									<td class="singleSeat" data-col="1">&nbsp;<input
										type="checkbox" value="07">&nbsp;&nbsp;&nbsp;07
									</td>
									<td class="singleSeat" data-col="2" data-gender="f"><img
										src="<c:url value="/resources/icon/woman-icon-24.png"/>">&nbsp;08</td>
									<td class="singleSeat" data-col="3">&nbsp;<input
										type="checkbox" value="09">&nbsp;&nbsp;&nbsp;09
									</td>
								</tr>
								<tr>
									<td class="singleSeat" data-col="1"><img
										src="<c:url value="/resources/icon/man-icon-24.png"/>">
										10</td>
									<td class="singleSeat" data-col="2"><img
										src="<c:url value="/resources/icon/woman-icon-24.png"/>">
										11</td>
									<td class="singleSeat" data-col="3"><img
										src="<c:url value="/resources/icon/woman-icon-24.png"/>">
										12</td>
								</tr>
								<tr>
									<td class="singleSeat" data-col="1">&nbsp;<input
										type="checkbox">&nbsp;&nbsp;&nbsp;13
									</td>
									<td class="singleSeat" data-col="2" data-gender="m"><img
										src="<c:url value="/resources/icon/man-icon-24.png"/>">&nbsp;14</td>
									<td class="singleSeat" data-col="3">&nbsp;<input
										type="checkbox">&nbsp;&nbsp;&nbsp;15
									</td>
								</tr>
								<tr>
									<td class="singleSeat" data-col="1">&nbsp;<input
										type="checkbox" value="16">&nbsp;&nbsp;&nbsp;16
									</td>
									<td class="singleSeat" data-col="2">&nbsp;<input
										type="checkbox" value="19">&nbsp;&nbsp;&nbsp;19
									</td>
									<td class="singleSeat" data-col="3">&nbsp;<input
										type="checkbox" value="20">&nbsp;&nbsp;&nbsp;20
									</td>
								</tr>
								<tr>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;17
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;22
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;23
									</td>
								</tr>
								<tr>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;18
									</td>
									<td class="singleSeat"></td>
									<td class="singleSeat"></td>
								</tr>
								<tr>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;21
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;25
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;26
									</td>
								</tr>
								<tr>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;24
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;28
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;29
									</td>
								</tr>
								<tr>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;27
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;31
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;32
									</td>
								</tr>
								<tr>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;30
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;33
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;34
									</td>
								</tr>
								<tr>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;35
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;36
									</td>
									<td class="singleSeat">&nbsp;<input type="checkbox">&nbsp;&nbsp;&nbsp;37
									</td>
								</tr>
							</table>
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

			<div class="col-sm-9">
				<div class="box box-success">
					<div class="box-header">
						<div class="col-xs-4"><h3 class="box-title"><strong>Rota :</strong> Bolu - Ankara</h3></div>
						<div class="col-xs-4"><h3 class="box-title"><strong>Tarih : </strong>02.02.2016 Pazartesi</h3></div>
						<div class="col-xs-4"><h3 class="box-title pull-right"><strong>Saat : </strong> 19:00</h3></div>
						<div class="box-tools"></div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive">
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
								<tr class="alertSeatSelection">
									<td colspan="6">
										<div class="alert alert-info alertInfoSize" role="alert">
											<span class="glyphicon glyphicon-info-sign"
												style="font-size: 4.5em;"></span>
											<h3>Koltuk seçiniz.</h3>
										</div>
									</td>
								</tr>

								<!-- Hidden row template -->
								<tr class="rowtemplate">
									<td><label id="seatNumber"></label></td>
									<td><select class="form-control input-sm" name="gender">
											<option value="female">Kadýn</option>
											<option value="male">Erkek</option>
									</select></td>
									<td><input type="text" class="form-control input-sm"
										id="tcNumber" placeholder="T.C. No"></td>
									<td><input type="text" class="form-control input-sm"
										id="passengerName" placeholder="Yolcu Adý"></td>
									<td><input type="text" class="form-control input-sm"
										id="passengerSurname" placeholder="Yolcu Soyadý"></td>
									<td><label id="ucret">28,00 <span
											class="fa fa-turkish-lira"></span></label></td>
								</tr>

								<!-- Hidden row template for Male Only -->
								<tr class="rowtemplateForMale">
									<td><label id="seatNumber"></label></td>
									<td><select class="form-control input-sm" name="gender">
											<option value="male">Erkek</option>
									</select></td>
									<td><input type="text" class="form-control input-sm"
										id="tcNumber" placeholder="T.C. No"></td>
									<td><input type="text" class="form-control input-sm"
										id="passengerName" placeholder="Yolcu Adý"></td>
									<td><input type="text" class="form-control input-sm"
										id="passengerSurname" placeholder="Yolcu Soyadý"></td>
									<td><label id="ucret">28,00 <span
											class="fa fa-turkish-lira"></span></label></td>
								</tr>

								<!-- Hidden row template for Female Only -->
								<tr class="rowtemplateForFemale">
									<td><label id="seatNumber"></label></td>
									<td><select class="form-control input-sm" name="cinsiyet">
											<option value="female">Kadýn</option>
									</select></td>
									<td><input type="text" class="form-control input-sm"
										id="tcNumber" placeholder="T.C. No"></td>
									<td><input type="text" class="form-control input-sm"
										id="passengerName" placeholder="Yolcu Adý"></td>
									<td><input type="text" class="form-control input-sm"
										id="passengerSurname" placeholder="Yolcu Soyadý"></td>
									<td><label id="ucret">28,00 <span
											class="fa fa-turkish-lira"></span></label></td>
								</tr>

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
						<div class="row">
							<div class="col-sm-3">
								<button class="btn btn-primary btn-sm">Seferler</button>
							</div>
							<div class="col-sm-9 pull-right">
								<strong>Toplam :</strong> <label id="toplamUcret">56,00
									<span class="fa fa-turkish-lira"></span>
								</label>&nbsp;&nbsp;&nbsp;
								<button id="btnOdeme" type="submit"
									class="btn btn-success btn-sm" disabled="disabled"
									data-toggle="modal" data-target="#odemeModal">Ödeme
									Yap</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.box -->

		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />

<script>
	$(document).ready(function() {
		$("input[type='checkbox']").change(function() {
			var table = $("#passengerInfo");
			var id = $(this).val();

			if ($(this).is(":checked")) { // checked

				var td = $(this).parent("td");
				var tdIndex = td.attr("data-col");

				var prot = null;
				if (tdIndex == 1) {
					// Single seat
					prot = table.find(".rowtemplate").clone();
				} else if (tdIndex == 2) {
					var gender = td.next().attr("data-gender");
					if (gender == "m") {
						prot = table.find(".rowtemplateForMale").clone();
					} else if (gender == "f") {
						prot = table.find(".rowtemplateForFemale").clone();
					} else {
						prot = table.find(".rowtemplate").clone()
					}
				} else if (tdIndex == 3) {
					var gender = td.prev().attr("data-gender");
					if (gender == "m") {
						prot = table.find(".rowtemplateForMale").clone();
					} else if (gender == "f") {
						prot = table.find(".rowtemplateForFemale").clone();
					} else {
						prot = table.find(".rowtemplate").clone()
					}
				}

				prot.attr("class", "")
				prot.find("#seatNumber").text(id);

				$(".alertSeatSelection").addClass("hide");
				table.find("tbody").append(prot);
				$("#btnOdeme").removeAttr("disabled");

			} else { // unchecked
				var label = table.find("label");
				//alert("label size = " + label.length);

				label.each(function() {
					var labelText = $(this).text();
					if (labelText == id) {

						//alert("labelText " + labelText);
						$(this).parents("tr").remove();
					}
				});
			}
		})
	})
</script>
</body>
</html>

