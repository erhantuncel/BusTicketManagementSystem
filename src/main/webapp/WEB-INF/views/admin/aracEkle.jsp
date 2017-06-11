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
		<h1>Araç Ekle</h1>
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
	       			 
	       			<div class="box-body" style="height: 64vh">
	       				<form:form class="form-horizontal" modelAttribute="addVehicleForm" 
	       							action="${home}admin/aracEkle" method="POST">
							<div class="form-group">
								<label for="vehicleBrandSelect" class="col-sm-2 control-label">Marka</label>
								<div class="col-sm-3">
									<select class="form-control" id="vehicleBrandSelect" data-placeholder="Marka Seçiniz">
										<option></option>
										<c:forEach items="${vehicleBrandMap}" var="brand">
											<option value="${brand.key}">${brand.value}</option>																					
										</c:forEach>
									</select>
								</div>
							</div>
							<spring:bind path="model">							
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="vehicleModelSelect" class="col-sm-2 control-label">Model</label>
									<div class="col-sm-3">
										<form:select path="model" class="form-control" id="vehicleModelSelect" data-placeholder="Model Seçiniz">
										</form:select>
										<form:errors path="model" cssClass="control-label error"/>
									</div>
								</div>
							</spring:bind>
							<spring:bind path="year">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="year" class="col-sm-2 control-label">Model Yýlý</label>
									<div class="col-sm-3">
										<form:input path="year" cssClass="form-control"/>
										<form:errors path="year" cssClass="control-label error"/>
									</div>
								</div>								
							</spring:bind>
							<spring:bind path="plateCode">							
				          		<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="plateCode" class="col-sm-2 control-label">Plaka</label>
									<div class="col-sm-3">
										<form:input path="plateCode" cssClass="form-control"/>
										<form:errors path="plateCode" cssClass="control-label error"/>
									</div>
									<!-- /.input group -->
								</div>
							</spring:bind>
							<spring:bind path="milage">							
				          		<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="milage" class="col-sm-2 control-label">Mesafe</label>
									<div class="col-sm-3">
										<form:input path="milage" cssClass="form-control"/>
										<form:errors path="milage" cssClass="control-label error"/>
									</div>
									<!-- /.input group -->
								</div>
							</spring:bind>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
									<button type="submit" class="btn btn-success btn-flat">Araç Ekle</button>
								</div>
							</div>
						</form:form>		
	       			</div>
	       		</div>
          	</div>
          </div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

<script>
	var BrandSelect = $("#vehicleBrandSelect");
	var ModelSelect = $("#vehicleModelSelect");
	callSelect2(BrandSelect);
	callSelect2(ModelSelect);
	
	$("#vehicleBrandSelect").on("change", (function() {
		var brandId = $(this).val();
		// console.log("Selected Id : " + brandId);
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}admin/vehicleModel/brand/"+brandId,
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				// console.log("SUCCESS: ", data);
				//var ModelSelect = $("#vehicleModelSelect");
				ModelSelect.empty();
				ModelSelect.append($("<option>").text("").attr("value", ""));
				$.each(data.modelMap, function(key, value) {
					// console.log("key: " + key + " value: " + value);
					ModelSelect.append($("<option>").text(value).attr("value", key));
				});
				callSelect2(ModelSelect);
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}))
	
	
	function callSelect2(selectInput) {
		$(selectInput).select2({
			language: "tr"
		})
	}
</script>

</body>
</html>

