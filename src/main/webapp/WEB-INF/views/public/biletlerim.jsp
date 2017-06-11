<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="../fragments/header.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<!-- 
		<h1>Özet</h1>
		 -->
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="box box-primary">
							<div class="box-body table-responsive" style="height: 74vh;">
								<table class="table table-condensed tableCentered">
									<thead>
										<tr>
											<th>Bilet No</th>
											<th>Ad Soyad</th>
											<th>Rota</th>
											<th>Tarih</th>
											<th>Saat</th>
											<th>Koltuk No</th>
											<th>Ücret</th>
											<th>Durum</th>
											<th>Ýþlemler</th>
										</tr>
									</thead>
										<tr>
											<td>1545</td>
											<td>Erhan TUNÇEL</td>
											<td>Bolu - Ankara</td>
											<td>15.10.2015</td>
											<td>18:00</td>
											<td>14</td>
											<td>28,00 <span
												class="fa fa-turkish-lira"></span>
											</td>
											<td><span class="label label-warning">Rezervasyon</span></td>
											<td>
												<a class="btn btn-xs btn-danger"><i class="fa fa-remove"></i>&nbsp;Sil</a>
												<a class="btn btn-xs btn-primary"><i class="fa fa-ticket"></i>&nbsp;Bilete Çevir</a>
											</td>
										</tr>
									<c:forEach var="i" begin="1" end="15">
										<tr>
											<td>${1545 + i }</td>
											<td>Erhan TUNÇEL</td>
											<td>Bolu - Ankara</td>
											<td>15.10.2015</td>
											<td>18:00</td>
											<td>20</td>
											<td>28,00 <span
												class="fa fa-turkish-lira"></span>
											</td>
											<td><span class="label label-success">Alýndý</span></td>
											<td>
												<a class="btn btn-xs btn-danger"><i class="fa fa-remove"></i>&nbsp;Sil</a>
												<a class="btn btn-xs btn-info"><i class="fa fa-ticket"></i>&nbsp;Bileti Gör</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
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
	});
</script>

</body>
</html>

