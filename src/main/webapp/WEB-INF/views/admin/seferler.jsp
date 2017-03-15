<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Seferler</h1>
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
							<form class="form-inline">
								<div class="form-group">
									<select class="form-control input-sm" style="width: 150px;">
										<option>Kalkýþ</option>
										<option>Bolu</option>
										<option>Ankara</option>
										<option>Ýstanbul</option>
										<option>Düzce</option>
										<option>Ýzmir</option>
										<option>Konya</option>
										<option>Zonguldak</option>
									</select>
								</div>
								<div class="form-group">
									<select class="form-control input-sm" style="width: 150px;">
										<option>Varýþ</option>
										<option>Bolu</option>
										<option>Ankara</option>
										<option>Ýstanbul</option>
										<option>Düzce</option>
										<option>Ýzmir</option>
										<option>Konya</option>
										<option>Zonguldak</option>
									</select>
								</div>
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
								<th style="width: 18%; text-align: center;">Sefer No</th>
								<th style="width: 18%; text-align: center;">Saat</th>
								<th style="width: 18%; text-align: center;">Rota</th>
								<th style="width: 18%; text-align: center;">Ücret</th>
								<th style="width: 18%; text-align: center;">Bilet/Rezervasyon</th>
								<th style="width: 10%; text-align: center;">Ýþlemler</th>
							</tr>

							<c:forEach begin="1" end="10">
								<tr>
									<td>1546</td>
									<td>07:00</td>
									<td>Ankara-Ýzmit</td>
									<td><span class="fa fa-turkish-lira"></span>33,00</td>
									<td>21/42</td>
									<td><a href="<c:url value="/admin/seferDetay"/>"
										class="btn btn-xs btn-success btn-flat" title="Detay"><i class="fa fa-search"></i></a>
										<a href="<c:url value="/admin/seferGuncelle"/>" class="btn btn-xs btn-success btn-flat" title="Güncelle"><i class="fa fa-refresh"></i></a>
										<a class="btn btn-xs btn-danger btn-flat" title="Sil" data-toggle="modal" data-target="#deleteVoyageModal" ><i class="fa fa-remove"></i></a>
									</td>
									<!-- 
									<td><a href="<c:url value="/admin/seferDetay"/>"
										class="btn btn-xs btn-primary btn-flat"><i class="fa fa-search"></i>&nbsp;Detay</a>
										<a href="<c:url value="/admin/seferGuncelle"/>" class="btn btn-xs btn-warning btn-flat"><i class="fa fa-refresh"></i>&nbsp;Güncelle</a>
										<a class="btn btn-xs btn-danger btn-flat" data-toggle="modal" data-target="#deleteVoyageModal" ><i class="fa fa-remove"></i>&nbsp;Sil</a>
									</td>
									 -->
								</tr>
							</c:forEach>

						</table>
					</div>
					<!-- /.box-body -->
					<div class="box-footer clearfix">
						<ul class="pagination pagination-green pagination-sm no-margin pull-right">
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

<div class="modal" id="deleteVoyageModal" tabindex="-1" role="dialog" aria-labelledby="deleteVoyageModalLabel">
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
					<h4 class="modal-title">Sefer Sil</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-6">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">Sefer No :</th>
									<td style="width: 15%">1546</td>
								</tr>
								<tr>
									<th style="width: 30%">Rota :</th>
									<td style="width: 15%">Bolu - Ankara</td>
								</tr>
								<tr>
									<th style="width: 30%">Sefer Tarihi :</th>
									<td style="width: 15%">24.10.2015</td>
								</tr>
								<tr>
									<th style="width: 30%">Sefer Saati :</th>
									<td style="width: 15%">13:00</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-6 text-center">
							<span class="fa fa-remove text-red" style="font-size: 2.5em;"></span>
							<h3 class="text-red"><strong>Sefer Sil!</strong></h3>								
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

