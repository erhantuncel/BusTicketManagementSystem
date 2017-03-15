<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Giderler</h1>
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-xs-7">
				<div class="box box-success">
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
					<div class="box-body table-responsive no-padding"  style="height:60vh;">
						<table class="table table-hover" style="text-align: center;">
							<tr>
								<th style="width: 22%; text-align: center;">Sefer No</th>
								<th style="width: 22%; text-align: center;">Tarih</th>
								<th style="width: 22%; text-align: center;">Saat</th>
								<th style="width: 22%; text-align: center;">Rota</th>
								<th style="width: 12%; text-align: center;">Ýþlemler</th>
							</tr>

							<tr class="success">
									<td>1546</td>
									<td>15.06.2016</td>
									<td>11:00</td>
									<td>Ankara-Bolu</td>
									<td>
										<a href="<c:url value="/admin/seferDetay"/>" class="btn btn-xs btn-success btn-flat" title="Detay"><i class="fa fa-search"></i></a>
									</td>
								</tr>
							<c:forEach begin="1" end="10">
								<tr>
									<td>1546</td>
									<td>15.06.2016</td>
									<td>11:00</td>
									<td>Ankara-Bolu</td>
									<td>
										<a href="<c:url value="/admin/seferDetay"/>" class="btn btn-xs btn-success btn-flat" title="Detay"><i class="fa fa-search"></i></a>
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
			
			<div class="col-xs-5">
				<div class="box box-success">
					<div class="box-header" style="padding-bottom: 0px;">
						<h3 class="box-title">Sefer No : 1546</h3>
						<div class="box-tools">
							<a class="btn btn-xs btn-success btn-flat" data-toggle="modal" data-target="#addExpenseModal"><i class="fa fa-upload"></i>&nbsp;Gider Ekle</a>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body table-responsive" style="height:56vh;">
						<div class="row">						
							<table class="table table-hover" style="text-align: center;">
								<tr>
									<th style="width: 10%; text-align: center;">No</th>
									<th style="width: 40%; text-align: center;">Gider Tipi</th>
									<th style="width: 40%; text-align: center;">Tutar</th>
								</tr>
								
								<c:forEach begin="1" end="15">
									<tr>
										<td>1</td>
										<td>Yakýt</td>
										<td>500,00</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer" style="padding-top: 0px;">
						<table class="table table-condensed" style="text-align: center;">
							<tr>
			                    <th style="width:10%;">TOPLAM</th>
			                    <th style="width:40%; text-align: center;"></th>
			                    <th style="width:40%; text-align: center;">1500,00</th>
		                  	</tr>
		                </table>
						<button type="submit" class="btn btn-success btn-sm btn-flat pull-right">Toplam Gider Ekle</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<div class="modal" id="addExpenseModal" tabindex="-1" role="dialog" aria-labelledby="addExpenseModalLabel">
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
					<h4 class="modal-title">Gider Ekle</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-8">
							<div class="form-group">
								<label for="expenseType" class="col-sm-4 control-label">Gider Tipi</label>
								<div class="col-sm-8">
									<select class="form-control">
										<option selected="selected">Gider Tipi Seçiniz</option>
										<option>Yakýt</option>
										<option>Bakým</option>
										<option>Ýkram</option>
										<option>Terminal</option>
										<option>Ceza</option>
										<option>Diðer</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="name" class="col-sm-4 control-label"></label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="name" placeholder="Yeni Gider Tipi">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="price" class="col-sm-4 control-label">Tutar</label>
								<div class="col-sm-8">
									<div class="input-group">
										<input type="text" class="form-control" id="price">
										<div class="input-group-addon">
											<i class="fa fa-turkish-lira"></i>
										</div>
									</div>
								</div>
								<!-- /.input group -->
							</div>
						</div>
						<div class="col-sm-2"></div>
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

