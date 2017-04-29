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
            Güzergâh Ekle
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
								<label for="routeName" class="col-sm-2 control-label">Güzergâh Adý</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="routeName">
								</div>
								<!-- /.input group -->
							</div>
							<div class="form-group">
								<label for="stopCount" class="col-sm-2 control-label">Durak Sayýsý</label>
								<div class="col-sm-4">
									<input id="stopCountSlider" type="text" value="" class="form-control" 
										data-slider-id="stopCountSlider" data-slider-min="2" data-slider-max="10" 
										data-slider-step="1" data-slider-value="2" data-slider-orientation="horizontal" >
								</div>
							</div>
							<div class="form-group">
								<label for="stop1" class="col-sm-2 control-label">1. Durak</label>
								<div class="col-sm-2">
									<select class="form-control" id="stop1">
										<option selected="selected">Þehir Seçiniz</option>
										<option>Ankara</option>
										<option>Ýstanbul</option>
										<option>Bolu</option>
										<option>Bursa</option>
									</select>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="durationMin1" placeHolder="Dakika">
								</div>
							</div>
							<div class="form-group">
								<label for="stop2" class="col-sm-2 control-label">2. Durak</label>
								<div class="col-sm-2">
									<select class="form-control" id="stop2">
										<option selected="selected">Þehir Seçiniz</option>
										<option>Ankara</option>
										<option>Ýstanbul</option>
										<option>Bolu</option>
										<option>Bursa</option>
									</select>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="durationMin2" placeHolder="Dakika">
								</div>
							</div>
							<div class="form-group">
								<label for="stop3" class="col-sm-2 control-label">3. Durak</label>
								<div class="col-sm-2">
									<select class="form-control" id="stop3">
										<option selected="selected">Þehir Seçiniz</option>
										<option>Ankara</option>
										<option>Ýstanbul</option>
										<option>Bolu</option>
										<option>Bursa</option>
									</select>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="durationMin3" placeHolder="Dakika">
								</div>
							</div>
							<div class="form-group">
								<label for="stop4" class="col-sm-2 control-label">4. Durak</label>
								<div class="col-sm-2">
									<select class="form-control" id="stop4">
										<option selected="selected">Þehir Seçiniz</option>
										<option>Ankara</option>
										<option>Ýstanbul</option>
										<option>Bolu</option>
										<option>Bursa</option>
									</select>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="durationMin1" placeHolder="Dakika">
								</div>
							</div>
							<div class="form-group">
								<label for="stop5" class="col-sm-2 control-label">5. Durak</label>
								<div class="col-sm-2">
									<select class="form-control" id="stop5">
										<option selected="selected">Þehir Seçiniz</option>
										<option>Ankara</option>
										<option>Ýstanbul</option>
										<option>Bolu</option>
										<option>Bursa</option>
									</select>
								</div>
								<div class="col-sm-2">
									<input type="text" class="form-control" id="durationMin1" placeHolder="Dakika">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-6">
									<button type="submit" class="btn btn-success btn-flat">Güzergâh Ekle</button>
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
		// bootstrap-slider - stopCount
		$('#stopCountSlider').slider();
	});
</script>

  </body>
</html>

