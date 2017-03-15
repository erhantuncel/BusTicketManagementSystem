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
            Araç Ekle
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
								<label for="numberPlate" class="col-sm-2 control-label">Plaka</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="numberPlate">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="seatCount" class="col-sm-2 control-label">Koltuk Miktarý</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="seatCount" value="37" disabled="disabled">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="vehicleBrand" class="col-sm-2 control-label">Marka</label>
								<div class="col-sm-4">
									<select class="form-control" id="vehicleBrand">
										<option selected="selected">Marka Seçiniz</option>
										<option>Mercedes</option>
										<option>Setra</option>
										<option>Neoplan</option>
										<option>Man</option>
										<option>Temsa</option>
										<option>Temsa</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="vehicleModel" class="col-sm-2 control-label">Model</label>
								<div class="col-sm-4">
									<select class="form-control" id="vehicleModel">
										<option>Model Seçiniz</option>
										<option>Tourismo 15 RHD</option>
										<option>Tourismo 16 RHD</option>
										<option>Tourismo 17 RHD</option>
										<option>O403</option>
										<option>Travego 15 SHD</option>
										<option>Travego 17 SHD</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="modelYear" class="col-sm-2 control-label">Model Yýlý</label>
								<div class="col-sm-4">
									<select class="form-control" id="modelYear">
										<option>Model Yýlý Seçiniz</option>
										<option>2015</option>
										<option>2014</option>
										<option>2013</option>
										<option>2012</option>
										<option>2011</option>
										<option>2010</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
									<button type="submit" class="btn btn-success btn-flat">Araç Ekle</button>
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

  </body>
</html>

