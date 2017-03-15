<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Müþteri Detaylarý</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->

		<div class="row">
			<div class="col-sm-12">
				<div class="box box-success">
					<!-- 
					<div class="box-header">
						<h3 class="box-title">Bilgiler</h3>
						<div class="box-tools"></div>
					</div>
					 -->
					<!-- /.box-header -->
					<div class="box-body">
						<div class="col-xs-4">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">T.C Numarasý :</th>
									<td style="width: 15%">54896322475</td>
								</tr>
								<tr>
									<th>Ad :</th>
									<td>Erhan</td>
								</tr>
								<tr>
									<th>Soyad :</th>
									<td>TUNÇEL</td>
								</tr>
								<tr>
									<th>Doðum Tarihi :</th>
									<td>29.11.1984</td>
								</tr>

							</table>
						</div>
						<div class="col-xs-4">
							<table class="table table-condensed" style="font-size: medium;">
								<tr>
									<th style="width: 30%">Cep Telefonu :</th>
									<td style="width: 15%">5476698522</td>
								</tr>
								<tr>
									<th>E-Posta :</th>
									<td>erhan@abc.com</td>
								</tr>
								<tr>
									<th>Üyelik Tarihi :</th>
									<td>24.10.2015 10:05:15</td>
								</tr>
								<tr>
									<th>Son Giriþ :</th>
									<td>04.02.2016 13:50:21</td>
								</tr>
							</table>
						</div>
						<div class="col-xs-4"></div>
					</div>
				</div>
			</div>
			<!-- /.box -->
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="box box-success">
					<div class="box-header ">
						<h3 class="box-title">Ýþlemler</h3>
						<div class="box-tools"></div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<table class="table table-condensed tableCentered">
							<thead>
								<tr>
									<th style="width: 11%">Bilet No</th>
									<th style="width: 11%">Ad Soyad</th>
									<th style="width: 11%">Rota</th>
									<th style="width: 11%">Tarih</th>
									<th style="width: 11%">Saat</th>
									<th style="width: 11%">Koltuk No</th>
									<th style="width: 11%">Ýþlem Zamaný</th>
									<th style="width: 11%">Durum</th>
									<th style="width: 4%">Ýþlem</th>
								</tr>
							</thead>
							<tbody>
								<tr>
										<td>1</td>
										<td>Erhan TUNÇEL</td>
										<td>Bolu - Ankara</td>
										<td>15.10.2015</td>
										<td>18:00</td>
										<td>15</td>
										<td>10.10.2015 15:34</td>
										<td><span class="label label-warning">Rezervasyon</span></td>
										<td><a class="btn btn-xs btn-danger btn-flat" title="Sil"><i
												class="fa fa-remove"></i></a></td>
									</tr>
								<c:forEach var="no" begin="2" end="5">
									<tr>
										<td>${no}</td>
										<td>Erhan TUNÇEL</td>
										<td>Bolu - Ankara</td>
										<td>15.10.2015</td>
										<td>18:00</td>
										<td>15</td>
										<td>10.10.2015 15:34</td>
										<td><span class="label label-info">Bilet</span></td>
										<td><a class="btn btn-xs btn-danger btn-flat" title="Sil"><i
												class="fa fa-remove"></i></a></td>
									</tr>
								</c:forEach>
							</tbody>
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


<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />
</body>
</html>