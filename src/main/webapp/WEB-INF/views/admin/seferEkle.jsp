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
	       				
	       				<form:form class="form-horizontal" modelAttribute="voyageForm" 
	       							action="${home}admin/seferEkle" method="POST">
	       					<!-- 
	       					<form:hidden path="id"/>
	       					 -->
	       					 
	       					<spring:bind path="vehicle">
	       						<div class="form-group ${status.error ? 'has-error' : ''}">
	       							<label for="vehicle" class="col-sm-2 control-label">Araç</label>
	       							<div class="col-sm-4">
	       								<form:select path="vehicle" class="form-control">
	       									<form:option value="" label="Araç Seçiniz" />
	       									<form:options items="${vehicleMap}"/>
	       								</form:select>
	       								<form:errors path="vehicle" cssClass="control-label error" />
	       							</div>
	       						</div>
	       					</spring:bind>
	       					
	       					<spring:bind path="route">
	       						<div class="form-group ${status.error ? 'has-error' : ''}">
	       							<label for="route" class="col-sm-2 control-label">Güzergâh</label>
	       							<div class="col-sm-4">
	       								<form:select path="route" class="form-control">
	       									<form:option value="" label="Güzergâh Seçiniz" />
	       									<form:options items="${routeMap}"/>
	       								</form:select>
	       								<form:errors path="route" cssClass="control-label error" />
	       							</div>
	       						</div>
	       					</spring:bind>
	       					
	       					<spring:bind path="departureTime">
	       						<div class="form-group ${status.error ? 'has-error' : ''}">
	       							<label for="departureTime" class="col-sm-2 control-label">Kalkýþ Zamaný</label>
	       							<div class="col-sm-4">
	       								<div class="input-group">
		       								<form:input path="departureTime" type="text" class="form-control datepicker" id="voyageDate"/>
		       								<span class="input-group-addon"><i class="fa fa-calendar"></i> </span>	       								
	       								</div>
	       								<form:errors path="departureTime" cssClass="control-label error"/>
	       							</div>
	       						</div>
	       					</spring:bind>
	       					
	       					<!-- 
	       					<form:hidden path="registerTime"/>
	       					<form:hidden path="expenseList"/>
	       					<form:hidden path="ticketList"/>
	       					 -->
	       					 
	       					<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
									<button type="submit" class="btn btn-success btn-flat">Sefer Oluþtur</button>
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


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

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

