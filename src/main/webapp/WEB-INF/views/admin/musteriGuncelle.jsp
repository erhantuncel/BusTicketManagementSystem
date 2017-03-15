<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Müþteri Güncelle
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">

          <!-- Your Page Content Here -->
          <div class="row">
          	<div class="col-xs-12">
	       		<div class="box box-success">
	       			<!-- 
	       			<div class="box-header">
	       				<h3 class="box-title"></h3>
	       				<div class="box-tools"> 
	       				</div>
	       			</div>
	       			 -->
	       			<div class="box-body">
			          	<form class="form-horizontal">
			          		<div class="form-group">
								<label for="customerNumber" class="col-sm-2 control-label">Müþteri No</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="customerNumber" disabled>
								</div>
								<!-- /.input group -->
							</div>
				          	<div class="form-group">
								<label for="tcNumber" class="col-sm-2 control-label">T.C Numarasý</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="tcNumber">
								</div>
								<!-- /.input group -->
							</div>
			          		<div class="form-group">
								<label for="name" class="col-sm-2 control-label">Ad</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="name">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="surname" class="col-sm-2 control-label">Soyad</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="surname">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="gender" class="col-sm-2 control-label">Cinsiyet</label>
								<div class="col-sm-4">
									<select class="form-control" id="gender">
										<option selected="selected">Cinsiyet Seçiniz</option>
										<option>Kadýn</option>
										<option>Erkek</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="dob" class="col-sm-2 control-label">Doðum Tarihi</label>
								<div class="col-sm-4">
									<div class="input-group">
										<input type="text" class="form-control datepicker" id="dob">
										<span class="input-group-addon"><i
											class="fa fa-calendar"></i> </span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="gsm" class="col-sm-2 control-label">Cep Telefonu</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="gsm">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">E-mail</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="email">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-2 control-label">Þifre</label>
								<div class="col-sm-4">
									<input type="password" class="form-control" id="password">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="password2" class="col-sm-2 control-label">Þifre Tekrar</label>
								<div class="col-sm-4">
									<input type="password" class="form-control" id="password2">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
									<button type="submit" class="btn btn-primary">Müþteri Güncelle</button>
								</div>
							</div>
						</form>   			
	       			</div>
	       		</div>
          	</div>
          </div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->


<jsp:include page="fragments/mainFooter.jsp" />

<jsp:include page="fragments/requiredScripts.jsp" />

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

