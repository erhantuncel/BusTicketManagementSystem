<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="../fragments/header.jsp" />

<jsp:include page="../fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Sefer Güncelle</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->

		<div class="box box-success">
			<div class="box-header with-border">
				<h3 class="box-title">Sefer Güncelle</h3>
				<div class="box-tools"></div>
			</div>
			<!-- /.box-header -->
			<div class="box-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="voyageId" class="col-sm-2 control-label">Sefer No</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="voyageId" disabled>
						</div>
						<!-- /.input group -->
					</div>
					<div class="form-group">
						<label for="vehicle" class="col-sm-2 control-label">Araç</label>
						<div class="col-sm-4">
							<select class="form-control">
								<option selected="selected">Araç Seçiniz</option>
								<option>14 AJ 779</option>
								<option>14 DS 943</option>
								<option>06 KKV 33</option>
								<option>06 EZV 41</option>
								<option>14 AS 875</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="fromCity" class="col-sm-2 control-label">Kalkýþ</label>
						<div class="col-sm-4">
							<select class="form-control" id="fromCity">
								<option>Þehir Seçiniz</option>
								<option>Bolu</option>
								<option>Ankara</option>
								<option>Ýstanbul</option>
								<option>Düzce</option>
								<option>Ýzmir</option>
								<option>Konya</option>
								<option>Zonguldak</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="toCity" class="col-sm-2 control-label">Varýþ</label>
						<div class="col-sm-4">
							<select class="form-control" id="toCity">
								<option>Þehir Seçiniz</option>
								<option>Bolu</option>
								<option>Ankara</option>
								<option>Ýstanbul</option>
								<option>Düzce</option>
								<option>Ýzmir</option>
								<option>Konya</option>
								<option>Zonguldak</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="voyageDate" class="col-sm-2 control-label">Tarih</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control datepicker"
									id="voyageDate"> <span class="input-group-addon"><i
									class="fa fa-calendar"></i> </span>
							</div>
						</div>
					</div>
					<div class="bootstrap-timepicker">
						<div class="form-group">
							<label for="voyageFromTime" class="col-sm-2 control-label">Kalkýþ
								Saati</label>
							<div class="col-sm-4">
								<div class="input-group" id="timepicker">
									<input type="text" class="form-control timepicker"
										id="voyageFromTime">
									<div class="input-group-addon">
										<i class="fa fa-clock-o"></i>
									</div>
								</div>
							</div>
							<!-- /.input group -->
						</div>
						<!-- /.form group -->
					</div>
					<div class="bootstrap-timepicker">
						<div class="form-group">
							<label for="duration" class="col-sm-2 control-label">Süre</label>
							<div class="col-sm-4">
								<div class="row">
									<div class="col-lg-6">
										<input type="text" class="form-control" id="durationHour"
											placeHolder="Saat">
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control" id="durationHour"
											placeHolder="Dakika">
									</div>
								</div>
							</div>
							<!-- /.input group -->
						</div>
						<!-- /.form group -->
					</div>
					<div class="form-group">
						<label for="price" class="col-sm-2 control-label">Ücret</label>
						<div class="col-sm-4">
							<div class="input-group">
								<input type="text" class="form-control" id="price">
								<div class="input-group-addon">
									<i class="fa fa-turkish-lira"></i>
								</div>
							</div>
						</div>
						<!-- /.input group -->
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<button type="submit" class="btn btn-primary">Sefer Ekle</button>
						</div>
					</div>
				</form>
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
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

<script>
	$(function() {
		// Datepicker - voyage
		$('.datepicker').datepicker({
			language : 'tr',
			autoclose : true
		});

		//Timepicker
		$(".timepicker").timepicker({
			showMeridian : false,
			template : 'dropdown',
			appendWidgetTo : '#timepicker',
			showInputs : false,
			defaultTime : false
		});
	});
</script>

</body>
</html>

