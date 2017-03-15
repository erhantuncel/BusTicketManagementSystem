<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Araçlar</h1>
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
									<div class="input-group" style="width: 150px;">
										<input type="text" id="numberPlate"
											class="form-control input-sm pull-right"
											placeholder="Plaka">
										<div class="input-group-btn">
											<button class="btn btn-sm btn-success btn-flat">
												<i class="fa fa-search"></i>
											</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover" style="text-align: center;">
							<tr>
								<th style="width: 15%; text-align: center;">Plaka</th>
								<th style="width: 15%; text-align: center;">Koltuk Sayýsý</th>
								<th style="width: 15%; text-align: center;">Marka</th>
								<th style="width: 15%; text-align: center;">Model</th>
								<th style="width: 15%; text-align: center;">Model Yýlý</th>
								<th style="width: 15%; text-align: center;">Mesafe</th>
								<th style="width: 10%; text-align: center;">Ýþlemler</th>
							</tr>

							<c:forEach begin="1" end="10">
								<tr>
									<td>14DS943</td>
									<td>42</td>
									<td>Volkswagen</td>
									<td>Golf</td>
									<td>2005</td>
									<td>85475</td>
									<td>
										<a href="<c:url value="/admin/aracGuncelle"/>" class="btn btn-xs btn-success btn-flat" title="Güncelle">
											<i class="fa fa-refresh"></i></a>
										<a class="btn btn-xs btn-danger btn-flat" title="Sil" data-toggle="modal" data-target="#deleteVehicleModal">
											<i class="fa fa-remove"></i></a>
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

<div class="modal" id="deleteVehicleModal" tabindex="-1" role="dialog" aria-labelledby="deleteVehicleModalLabel"
		data-backdrop="static" data-keyboard="false">
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
					<h4 class="modal-title">Araç Sil</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-6">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">Plaka :</th>
									<td style="width: 15%">14DS943</td>
								</tr>
								<tr>
									<th style="width: 30%">Marka :</th>
									<td style="width: 15%">Volkswagen</td>
								</tr>
								<tr>
									<th style="width: 30%">Model :</th>
									<td style="width: 15%">Golf V</td>
								</tr>
								<tr>
									<th style="width: 30%">Model Yýlý :</th>
									<td style="width: 15%">2005</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-6 text-center">
							<span class="fa fa-remove text-red" style="font-size: 2.5em;"></span>
							<h3 class="text-red"><strong>Araç Sil!</strong></h3>								
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

</body>
</html>

