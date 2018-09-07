<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../fragments/publicIndexHeader.jsp" />

<c:url var="home" value="/" scope="request" />

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Your Page Content Here -->
				<div class="row vertical-center">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-sm-12">
								<div class="box box-primary box-solid">
									<div class="box-header text-center" style="font-size: 18pt;">
										<strong>ONLINE BÝLET SÝSTEMÝ</strong>
									</div>
									<div class="box-body table-responsive">
										<div class="col-sm-6" style="border-right: thin dotted silver;">
											<form action="${home}seferler" method="POST">
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
												<!-- 
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
												 -->
												<div class="form-group">
													<button class="btn btn-primary btn-flat form-control">Hemen Bilet Al</button>
												</div>
											</form>
										</div>
										<div class="col-sm-6">
											<form name="loginForm" action="<c:url value="/login" />" method="POST">
												<c:if test="${param.error != null }">
													<div class="alert alert-danger">
														<p><strong>T.C. No ya da þifre geçersiz.</strong>
													</div>
												</c:if>
												<div class="form-group">
													<label for="tcNumber" class="control-label">T.C. No</label>
													<input type="text" class="form-control" name="username">
												</div>
												<div class="form-group has-feedback">
													<label for="password" class="control-label">Þifre</label>
													<input type="password" class="form-control" id="password" name="password">
												</div>
												<div class="row">
													<div class="col-xs-8">
														<div class="checkbox">
															<label> <input type="checkbox">Beni Hatýrla</label>
														</div>
													</div>
													<div class="col-xs-4">
														<input type="submit" class="btn btn-primary btn-block btn-flat" value="Giriþ">
													</div>
												</div>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											</form>
											<div class="row">
												<div class="col-xs-8">
													<a href="#">Þifremi Unuttum</a><br/>
													<a href="<c:url value="/musteriKayit"/>">Üye Ol</a>
												</div>
											</div>
										</div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- <div class="col-sm-3" style=""></div>  -->
							
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- ./wrapper -->
	<jsp:include page="../fragments/requiredScripts.jsp" />
	
	<c:if test="${not empty msg }">
		<script>
			$(function() {
				callNotify("${msg}", "${warningType}");
			});
		</script>
	</c:if>

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
		
	function callNotify(message, type) {
		$.notify({
			// options
			message: message,
		},{
			// settings
			element: 'body',
			position: null,
			type: type,
			allow_dismiss: true,
			newest_on_top: false,
			showProgressbar: false,
			placement: {
				from: "top",
				align: "center"
			},
			offset: {
				x: 5,
				y: 53
			},
			spacing: 10,
			z_index: 1031,
			delay: 5000,
			timer: 1000,
			url_target: '_blank',
			mouse_over: null,
			animate: {
				enter: 'animated fadeInDown',
				exit: 'animated fadeOutUp'
			},
			onShow: null,
			onShown: null,
			onClose: null,
			onClosed: null,
			icon_type: 'class',
			template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert" style="height: 50px;">' +
				'<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
				'<span data-notify="icon"></span> ' +
				'<span data-notify="title">{1}</span> ' +
				'<span data-notify="message">{2}</span>' +
				'<div class="progress" data-notify="progressbar">' +
					'<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
				'</div>' +
				'<a href="{3}" target="{4}" data-notify="url"></a>' +
			'</div>' 
		});
	}
	</script>

</body>
</html>