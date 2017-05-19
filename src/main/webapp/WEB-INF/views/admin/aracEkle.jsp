<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<c:url var="home" value="/" scope="request" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Sefer Ekle</h1>
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
								<div class="col-sm-4">
									<select class="form-control" id="vehicleBrandSelect">
										<option val="">Marka Seçiniz</option>
										<c:forEach items="${vehicleBrandMap}" var="brand">
											<option value="${brand.key}">${brand.value}</option>																					
										</c:forEach>
									</select>
								</div>
							</div>
							<spring:bind path="model">							
								<div class="form-group">
									<label for="vehicleModelSelect" class="col-sm-2 control-label">Model</label>
									<div class="col-sm-4">
										<select class="form-control" id="vehicleModelSelect">
											<option value="">Model Seçiniz</option>
										</select>
									</div>
								</div>
							</spring:bind>
							<spring:bind path="year">
								<div class="form-group">
									<label for="year" class="col-sm-2 control-label">Model Yýlý</label>
									<div class="col-sm-4">
										<form:input path="year" cssClass="form-control"/>
									</div>
								</div>								
							</spring:bind>
							<spring:bind path="plateCode">							
				          		<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="plateCode" class="col-sm-2 control-label">Plaka</label>
									<div class="col-sm-4">
										<form:input path="plateCode" cssClass="form-control"/>
										<form:errors path="plateCode" cssClass="control-label error"/>
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
	       				<!-- 
			          	<form class="form-horizontal">
							<div class="form-group">
								<label for="voyageDate" class="col-sm-2 control-label">Tarih</label>
								<div class="col-sm-4">
									<div class="input-group">
										<input type="text" class="form-control datepicker" id="voyageDate">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
									<button type="submit" class="btn btn-success btn-flat">Sefer Ekle</button>
								</div>
							</div>
						</form>  
						 --> 			
	       			</div>
	       		</div>
          	</div>
          </div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />

<script>
	$(function() {
		// Datepicker - voyage
		$('.datepicker').datetimepicker({
			language : 'tr',
			autoclose : 1,
			pickerPosition: "bottom auto",
			todayHighlight: 1,
			startDate: new Date(),
			format: "dd.mm.yyyy hh:ii:00"
		});
	});
</script>

</body>
</html>

