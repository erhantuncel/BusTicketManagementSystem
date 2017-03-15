<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/publicHeader.jsp" />

		<!-- Content Wrapper. Contains page content -->
		<!-- style="background: url('<c:url value="/resources/img/bg-road-2.jpg"/>');" -->
		<div class="content-wrapper publicIndexContetWrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header publicIndexHeaderKayit">
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
										<strong>ÜYE KAYIT</strong>
									</div>
									<div class="box-body table-responsive">
										<div class="col-sm-12">
											<form class="form-horizontal">
									          	<div class="form-group">
													<label for="tcNumber" class="col-sm-4 control-label">T.C Numarasý</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="tcNumber">
													</div>
													<!-- /.input group -->
												</div>
								          		<div class="form-group">
													<label for="name" class="col-sm-4 control-label">Ad</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="name">
													</div>
													<!-- /.input group -->
												</div>
												<div class="form-group">
													<label for="surname" class="col-sm-4 control-label">Soyad</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="surname">
													</div>
													<!-- /.input group -->
												</div>
												<div class="form-group">
													<label for="gender" class="col-sm-4 control-label">Cinsiyet</label>
													<div class="col-sm-6">
														<select class="form-control" id="gender">
															<option selected="selected">Cinsiyet Seçiniz</option>
															<option>Kadýn</option>
															<option>Erkek</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="dob" class="col-sm-4 control-label">Doðum Tarihi</label>
													<div class="col-sm-6">
														<div class="input-group">
															<input type="text" class="form-control datepicker" id="dob">
															<span class="input-group-addon"><i
																class="fa fa-calendar"></i> </span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label for="gsm" class="col-sm-4 control-label">Cep Telefonu</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="gsm">
													</div>
													<!-- /.input group -->
												</div>
												<div class="form-group">
													<label for="email" class="col-sm-4 control-label">E-mail</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="email">
													</div>
													<!-- /.input group -->
												</div>
												<div class="form-group">
													<label for="password" class="col-sm-4 control-label">Þifre</label>
													<div class="col-sm-6">
														<input type="password" class="form-control" id="password">
													</div>
													<!-- /.input group -->
												</div>
												<div class="form-group">
													<label for="password2" class="col-sm-4 control-label">Þifre Tekrar</label>
													<div class="col-sm-6">
														<input type="password" class="form-control" id="password2">
													</div>
													<!-- /.input group -->
												</div>
												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-6">
														<button type="submit" class="btn btn-primary">Kayýt Ol</button>
													</div>
												</div>
											</form>
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
	<jsp:include page="fragments/requiredScripts.jsp" />
	
	<script>
		$(function() {
			// Datepicker - voyage
			$('.datepicker').datepicker({
				language : 'tr',
				autoclose : true
			});
		});
	</script>

</body>
</html>