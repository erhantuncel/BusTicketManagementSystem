<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Müþteriler</h1>
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
										<input type="text" name="table_search"
											class="form-control input-sm pull-right"
											placeholder="T.C Numarasý">
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
								<th style="width: 18%; text-align: center;">T.C. Numarasý</th>
								<th style="width: 18%; text-align: center;">Ad Soyad</th>
								<th style="width: 18%; text-align: center;">Doðum Tarihi</th>
								<th style="width: 18%; text-align: center;">Cep Telefonu</th>
								<th style="width: 18%; text-align: center;">E-Posta</th>
								<th style="width: 10%; text-align: center;">Ýþlemler</th>
							</tr>

							<c:forEach begin="1" end="10">
								<tr>
									<td>21756984547</td>
									<td>Erhan TUNÇEL</td>
									<td>29.11.1984</td>
									<td>5489657841</td>
									<td>erhan@abc.com</td>
									<td>
										<a href="<c:url value="/admin/musteriDetay"/>" class="btn btn-xs btn-success btn-flat" title="Detay"><i class="fa fa-search"></i></a>
										<a href="<c:url value="/admin/musteriGuncelle"/>" class="btn btn-xs btn-success btn-flat" title="Güncelle"><i class="fa fa-refresh"></i></a>
										<a class="btn btn-xs btn-danger btn-flat" title="Sil" data-toggle="modal" data-target="#deleteCustomerModal"><i class="fa fa-remove"></i></a>
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

<div class="modal" id="deleteCustomerModal" tabindex="-1" role="dialog" aria-labelledby="deleteCustomerModalLabel">
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
									<th style="width: 30%">T.C Numarasý :</th>
									<td style="width: 15%">21547899635</td>
								</tr>
								<tr>
									<th style="width: 30%">Ad Soyad :</th>
									<td style="width: 15%">Erhan TUNÇEL</td>
								</tr>
								<tr>
									<th style="width: 30%">Doðum Tarihi :</th>
									<td style="width: 15%">29.11.1984</td>
								</tr>
								<tr>
									<th style="width: 30%">Cep Telefonu :</th>
									<td style="width: 15%">05478896633</td>
								</tr>
								<tr>
									<th style="width: 30%">E-Posta :</th>
									<td style="width: 15%">erhan@abc.com</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-6 text-center">
							<span class="fa fa-remove text-red" style="font-size: 2.5em;"></span>
							<h3 class="text-red"><strong>Müþteri Sil!</strong></h3>								
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

