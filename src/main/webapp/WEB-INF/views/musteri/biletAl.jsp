<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../fragments/header.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1></h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="col-sm-3">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">Bilet Bilgileri</h3>
							<div class="box-tools"></div>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive" style="height: 74vh;">
							<div class="col-xs-12">
								<table class="table table-condensed" style="font-size: medium;">
									<tr>
										<th style="width: 42%">Tarih :</th>
										<td style="width: 58%"><fmt:formatDate value="${ticketForSave.voyage.departureTime}"
												type="date" pattern="dd.MM.yyyy" var="departureDate" />
											${departureDate}</td>
									</tr>
									<tr>
										<th>Saat :</th>
										<td>
											<fmt:formatDate value="${ticketForSave.voyage.departureTime}"
												type="date" pattern="HH:mm" var="departureHour" />
											${departureHour}
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
										<th>Sefer No:</th>
										<td>${ticketForSave.voyage.id}</td>
									</tr>
									<tr>
										<th>Rota :</th>
										<td>${ticketForSave.voyage.route.routeName}</td>
									</tr>
									<tr>
										<th>Rota :</th>
										<td>${checkedSeatNumberArray[0]}, ${checkedSeatNumberArray[1]}, ${checkedSeatNumberArray[2]}</td>
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
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">Yolcu Bilgileri</h3>
							<div class="box-tools"></div>
						</div>
						<!-- /.box-header -->
						<div class="box-body table-responsive" style="height: 67vh;">
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
			<div class="col-md-1"></div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

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

