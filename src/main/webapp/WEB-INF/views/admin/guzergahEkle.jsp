<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="../fragments/header.jsp" />

<jsp:include page="../fragments/mainSideBar.jsp" />

<c:url var="home" value="/" scope="request" />

<!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Güzergâh Ekle
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">

          <!-- Your Page Content Here -->
          <div class="row">
          	<div class="col-xs-12">
	       		<div class="box box-success">
	       			<div class="box-header">
	       				<h3 class="box-title"></h3>
	       				<div class="box-tools"> 
	       				</div>
	       			</div>
	       			<div class="box-body" style="height: 67vh">
	       				
	       				<div class="col-md-1"></div>
	       				<div class="col-md-10">
	       					<div class="row">
	       						
	       						<div class="col-sm-5">
	       							<div class="row" style="padding-bottom: 15px;">
		       							<div class="input-group">
			       							<select id="citySelect" class="form-control">
			       								<option value="${city.key}">${city.value}</option>
												<c:forEach items="${cityMap}" var="city">
													<option value="${city.key}">${city.value}</option>
												</c:forEach>
											</select>
											<span class="input-group-btn" style="padding-left: 10px;">										
												<button id="buttonSehirEkle" class="btn btn-primary btn-flat">Durak Ekle</button>	       								       								
											</span>		       							
		       							</div>
	       							</div>
	       							
	       							<div class="row table-responsive" style="padding-bottom: 20px; max-height: 49vh;">
	       								<table class="table table-bordered table-condensed" id="stopTable">
		       								<thead>
												<tr>
													<th style="width: 25%;">Plaka Kodu</th>
													<th>Þehir</th>
													<th style="width: 10%;">Sil</th>
												</tr>		       									
		       								</thead>
		       								<tbody>
		       									<tr id="alertEmptyList">
													<td colspan="3" class="text-center">Durak listesi boþ!</td>
												</tr>
												<!-- Row Template -->
												<tr class="hide" id="rowTemplate" >
													<td id="plakaKodu">Plaka</td>
													<td id="sehirAdi">Þehir</td>
													<td class="deleteCell">
														<button id="deleteCityButton" class="btn btn-xs btn-danger btn-flat" title="Sil">
															<i class="glyphicon glyphicon-trash"></i>
														</button>
													</td>
												</tr>
		       								</tbody>
										</table>	
	       							</div>
	       							<div class="row"> 
	       								<form:form method="POST" modelAttribute="addRouteForm" action="${home}admin/guzergahEkle">
	       								<spring:bind path="stopList">
	       									<div class="${status.error ? 'has-error' : ''}">	       									
		       									<form:select path="stopList" multiple="true" class="hide" id="stopListHiddenSelect">
		       									</form:select>
		       									<form:errors path="stopList" class="control-label"/>
	       									</div>
	       								</spring:bind>
	       							</div>
	       							<div class="row" style="padding-top: 10px;">
										<button id="calculateDistanceAndDuration" class="btn btn-primary btn-flat pull-right disabled">Mesafe ve Süre Hesapla</button>
									</div>
	       						</div>
	       						<div class="col-sm-1">
	       						</div>
	       						<div class="col-sm-5">
	       							<table id="routeInfo" class="table table-condensed" style="font-size: medium;">
	       								<tr>
											<th style="width: 48%">Mesafe :</th>
											<td id="totalDistance" style="width: 52%">--</td>
										</tr>
										<tr>
											<th>Süre :</th>
											<td id="duration">--</td>
										</tr>
										<tr>
											<th>Güzergâh Adý :</th>
											<td>
												<spring:bind path="routeName">
													<div class="${status.error ? 'has-error' : ''}">
														<form:input path="routeName" class="form-control" id="routeName"/>
														<form:errors path="routeName" class="cotrol-label"/>
													</div>
												</spring:bind>
											</td>
										</tr>
										<tr>
											<td></td>
											<td>
												<button id="addRoute" type="submit" class="btn btn-success btn-flat disabled">Güzergâh Ekle</button>
											</td>
										</tr>
	       							</table>
	       							
	       						</div>	
	       						</form:form>	
	       					</div>
	       				</div>
	       				<div class="col-md-1"></div>	
	       			</div>
	       		</div>
          	</div>
          </div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

<script>
	$(function() {
		// bootstrap-slider - stopCount
		$('#stopCountSlider').slider();
	});
	
	/*
	$("#citySelect").select2({
		placeholder: "Þehir Seçiniz",
		language: "tr"
	});
	*/
	callSelect2();
	
	$("#buttonSehirEkle").click(function(e) {
		e.preventDefault();
		var selectedCity = $("#citySelect option:selected").text(); 
		var selectedCityPlateCode = $("#citySelect option:selected").val();
		$("#stopListHiddenSelect").append($("<option>", 
			{
				value : selectedCityPlateCode,
				selected : "selected",
				text : selectedCity
		}));
		var row = $("#rowTemplate").clone();
		row.find("#plakaKodu").text(selectedCityPlateCode);
		row.find("#sehirAdi").text(selectedCity);
		var table = $("#stopTable");
		$("#alertEmptyList").attr("class", "hide");
		row.removeAttr("class");
		row.removeAttr("id");
		row.attr("data-id", selectedCityPlateCode);
		table.find("tbody").append(row);
		$("#citySelect option:selected").attr("disabled", "disabled");
		var rowCount = $("#stopTable tr").length - 1;
		console.log("Row Count", rowCount);
		if(rowCount >= 4) {
			$("#calculateDistanceAndDuration").removeClass("disabled");
		}
		callSelect2();
		$("#citySelect").val(null).trigger("change");
	});	
	
	$("#stopTable").on("click", "#deleteCityButton", function() {
		var row = $(this).closest("tr");
		row.remove(); 
		var rowCount = $("#stopTable tr").length - 1;
		console.log("Row Count", rowCount);
		if(rowCount == 2) {
			$("#alertEmptyList").removeClass("hide");
		}
		var id = row.data("id");
		$("#citySelect option[value=" + id + "]").removeAttr("disabled");
		callSelect2();
	})
	
	$("#calculateDistanceAndDuration").click(function(e) {
		e.preventDefault();
		var ids = [];
		var cities = [];
		$("#stopTable tr").each(function(index, item) {
			if($(item).attr("data-id")) {
				var id = $(item).data("id");
				var city = $(item).find("#sehirAdi").text();
				ids.push(id);
				cities.push(city);
			}
		});
		console.log("IDs : ", ids);
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}admin/rotaMesafeHesapla",
			data : JSON.stringify(ids),
			success : function(data) {
				console.log("SUCCESS: ", data);
				var distance = data.distance;
				var duration = data.duration;
				$("#totalDistance").text(distance + " km");
				/*
				var durationMin = Math.floor((distance / 85)*60);
				var hours = Math.floor(durationMin/60);
				var minutes = durationMin % 60;
				var durationStr = hours + " sa. " + minutes + " dk.";
				*/
				$("#duration").text(duration);
				var routeName;
				$.each(cities, function(index, value) {
					if(index == 0) {
						routeName = value;
					} else {
						routeName = routeName + "-" + value;
					}
				});
				$("#routeName").val(routeName);
				$("#addRoute").removeClass("disabled");
			},
			error : function(e) {
				console.log("ERROR: ", e);
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}) ;
	
	function callSelect2() {
		$("#citySelect").select2({
			placeholder: "Þehir Seçiniz",
			language: "tr"
		});
	}
	
</script>

  </body>
</html>

