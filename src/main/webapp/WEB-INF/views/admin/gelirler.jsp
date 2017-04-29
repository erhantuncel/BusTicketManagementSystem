<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Gelirler</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<!-- box-header -->
					<div class="box-header">
						<h3 class="box-title"></h3>
						<div class="box-tools">
							<form class="form-inline">
								<div class="form-group">
									<div class="input-group date">
										<input type="text" class="form-control input-sm date">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
									</div>
								</div>
								<button class="btn btn-success btn-sm btn-flat">Sefer Ara</button>
							</form>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover" style="text-align: center;">
							<tr>
								<th style="width: 13%; text-align: center;">Sefer No</th>
								<th style="width: 13%; text-align: center;">Tarih</th>
								<th style="width: 13%; text-align: center;">Saat</th>
								<th style="width: 13%; text-align: center;">Güzergâh</th>
								<th style="width: 13%; text-align: center;">Bilet Ücreti</th>
								<th style="width: 13%; text-align: center;">Satýlan Bilet</th>
								<th style="width: 13%; text-align: center;">Bilet Geliri</th>
								<th style="width: 9%; text-align: center;">Ýþlemler</th>
							</tr>

							<c:forEach begin="1" end="10">
								<tr>
									<td>1546</td>
									<td>15.06.2016</td>
									<td>11:00</td>
									<td>Ankara-Bolu</td>
									<td><span class="fa fa-turkish-lira"></span>33,00</td>
									<td>24</td>
									<td><span class="fa fa-turkish-lira"></span>792,00</td>
									<td>
										<a class="btn btn-xs btn-success btn-flat" title="Güncelle" data-toggle="modal" data-target="#updateIncome"><i class="fa fa-refresh"></i></a>
										<a class="btn btn-xs btn-success btn-flat" title="Detay" href="<c:url value="/admin/seferDetay"/>"><i class="fa fa-search"></i></a>
									</td>
								</tr>
							</c:forEach>

						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer clearfix">
						<ul class="pagination pagination-sm no-margin pull-right">
							<li class="disabled"><a href="#">&laquo;</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>
					</div>
				</div>
				<!-- /.box -->
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<div class="modal" id="updateIncome" tabindex="-1" role="dialog" aria-labelledby="updateIncomeLabel">
	<div class="modal-dialog">
		<form class="form-horizontal">
			<div class="modal-content">
				<div class="modal-header">
					<!-- 
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					 -->
					<h4 class="modal-title">Gelir Onayla</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-3">				
						</div>
						<div class="col-xs-6">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">Sefer No :</th>
									<td style="width: 15%">1546</td>
								</tr>
								<tr>
									<th>Sefer Tarihi :</th>
									<td>15.06.2016</td>
								</tr>
								<tr>
									<th>Sefer Saati :</th>
									<td>11:00</td>
								</tr>
								<tr>
									<th>Güzergâh :</th>
									<td>Ankara - Bolu</td>
								</tr>
								<tr>
									<th>Satýlan Bilet :</th>
									<td>24</td>
								</tr>
								<tr>
									<th>Bilet Ücreti :</th>
									<td><span class="fa fa-turkish-lira"></span>33,00</td>
								</tr>
								<tr>
									<th>Toplam Gelir :</th>
									<td><span class="fa fa-turkish-lira"></span>792,00</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-3">				
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger pull-left"
						data-dismiss="modal">Vazgeç</button>
					<button type="submit" class="btn btn-primary">Onayla</button>
				</div>
			</div>
		<!-- /.modal-content -->
		</form>
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />

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

