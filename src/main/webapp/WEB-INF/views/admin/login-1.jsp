<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

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
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<div class="row">
							<div class="col-sm-12">
								<div class="box box-success box-solid">
									<div class="box-header text-center" style="font-size: 18pt;">
										<strong>OBS - YÖNETÝM SAYFASI</strong>
									</div>
									<div class="box-body table-responsive">
										<div class="col-sm-2"></div>
										<div class="col-sm-8">
											<form>
												<div class="form-group">
													<label for="userName" class="control-label">Kullanýcý Adý</label>
													<input type="text" class="form-control">
												</div>
												<div class="form-group has-feedback">
													<label for="password" class="control-label">Þifre</label>
													<input type="password" class="form-control" id="password">
												</div>
												<div class="row">
													<div class="col-xs-8">
													</div>
													<div class="col-xs-4">
														<a href="<c:url value="/admin/index"/>" class="btn btn-success btn-flat form-control">Giriþ</a>
													</div>
												</div>
											</form>
										</div>
										<div class="col-sm-2"></div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- <div class="col-sm-3" style=""></div>  -->
							
						</div>
					</div>
					<div class="col-md-4"></div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	<!-- 
	</div>
	 -->
	<!-- ./wrapper -->
	<jsp:include page="../fragments/requiredScripts.jsp" />
</body>
</html>