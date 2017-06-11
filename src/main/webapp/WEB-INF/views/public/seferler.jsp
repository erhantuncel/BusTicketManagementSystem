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
					<div class="col-sm-3">
						<div class="box box-primary">
							<div class="box-body table-responsive" style="height: 74vh;">
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
											</span> <input class="form-control datepicker" type="date" id="seferTarih"></input>
										</div>
									</div>
									<div class="form-group">
										<button type="submit" class="btn btn-primary form-control">Sefer
											Ara</button>
									</div>
								</form>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<div class="col-sm-9">
						<div class="box box-primary">
							<div class="box-body table-responsive" style="height: 74vh;">
								<table class="table table-hover table-condensed">
									<thead>
										<tr>
											<th>Sefer No</th>
											<th>Saat</th>
											<th>Kalkýþ</th>
											<th>Varýþ</th>
											<th>Ücret</th>
											<th></th>
										</tr>
									</thead>
									<c:forEach var="i" begin="1" end="15">
										<tr>
											<td>${1545 + i }</td>
											<td>07:00</td>
											<td>Bolu</td>
											<td>Ankara</td>
											<td>28,00 <span
												class="fa fa-turkish-lira"></span></td>
											<td><a href="<c:url value="/biletAl"/>" class="btn btn-primary btn-xs">Seç</a></td>
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

