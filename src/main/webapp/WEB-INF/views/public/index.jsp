<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../fragments/publicIndexHeader.jsp" />

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
											<form>
												<div class="form-group">
													<label class="control-label">Kalkýþ</label> <select
														class="form-control" name="kalkis">
														<option value="">Þehir Seçiniz</option>
														<option value="s">Ankara</option>
														<option value="m">Ýstanbul</option>
														<option value="l">Bolu</option>
														<option value="xl">Düzce</option>
													</select>
												</div>
												<div class="form-group">
													<label class="control-label">Varýþ</label> <select
														class="form-control" name="varis">
														<option value="">Þehir Seçiniz</option>
														<option value="s">Ankara</option>
														<option value="m">Ýstanbul</option>
														<option value="l">Bolu</option>
														<option value="xl">Düzce</option>
													</select>
												</div>
												<div class="form-group">
													<label class="control-label">Tarih</label>
													<div class="input-group">
														<span class="input-group-addon"> <span
															class="glyphicon glyphicon-calendar"></span>
														</span> <input class="form-control date" type="date"></input>
													</div>
												</div>
												<div class="form-group">
													<a href="<c:url value="/seferler"/>" class="btn btn-primary form-control">Üye
														Olmadan Bilet Al</a>
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
												<div class="row">
													<div class="col-xs-8">
														<a href="#">Þifremi Unuttum</a><br/>
														<a href="<c:url value="/uyeKayit"/>">Üye Ol</a>
													</div>
												</div>
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
	<jsp:include page="../fragments/requiredScripts.jsp" />
	
	<script>
		$(function() {
			// Datepicker - voyage
			$('.date').datepicker({
				language : 'tr',
				autoclose : true
			});
		});
	</script>

</body>
</html>