<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="fragments/header.jsp" />

<jsp:include page="fragments/mainSideBar.jsp" />

<c:url var="home" value="/" scope="request" />

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
	       				<form:form class="form-horizontal" modelAttribute="updateCustomerForm" action="${home}admin/musteri/${updateCustomerForm.id}/guncelle" method="POST">
	       					<form:hidden path="password"/>
	       					<form:hidden path="enabled"/>
	       					<form:hidden path="dateOfRegister"/>
	       					<form:hidden path="timeOfLastOnline"/>
	       					
			          		<spring:bind path="id">
				          		<div class="form-group">
									<label for="customerNumber" class="col-sm-2 control-label">Müþteri No</label>
									<div class="col-sm-4">
										<form:input path="id" type="text" class="form-control" id="customerNumber" readonly="true"/>
									</div>
									<!-- /.input group -->
								</div>			          		
			          		</spring:bind>
			          		<spring:bind path="tcNumber">			          		
					          	<div class="form-group">
									<label for="tcNumber" class="col-sm-2 control-label">T.C Numarasý</label>
									<div class="col-sm-4">
										<form:input path="tcNumber" type="text" class="form-control" id="tcNumber" readonly="true"/>
									</div>
									<!-- /.input group -->
								</div>
			          		</spring:bind>
			          		<spring:bind path="name">			          		
				          		<div class="form-group">
									<label for="name" class="col-sm-2 control-label">Ad</label>
									<div class="col-sm-4">
										<form:input path="name" type="text" class="form-control" id="customerName" readonly="true"/>
									</div>
									<!-- /.input group -->
								</div>
			          		</spring:bind>
			          		<spring:bind path="surname">			          		
								<div class="form-group">
									<label for="surname" class="col-sm-2 control-label">Soyad</label>
									<div class="col-sm-4">
										<form:input path="surname" type="text" class="form-control" id="customerSurname" readonly="true"/>
									</div>
									<!-- /.input group -->
								</div>
			          		</spring:bind>
			          		<spring:bind path="gender">
								<div class="form-group">
									<label for="gender" class="col-sm-2 control-label">Cinsiyet</label>
									<div class="col-sm-4">
										<form:select path="gender" class="form-control" id="customerGender" readonly="true">
											<form:options items="${genderValues}"/>
										</form:select>
									</div>
								</div>
			          		</spring:bind>
			          		<spring:bind path="dateOfBirth">
								<div class="form-group">
									<label for="dob" class="col-sm-2 control-label">Doðum Tarihi</label>
									<div class="col-sm-4">
										<div class="input-group">
											<form:input path="dateOfBirth" type="text" class="form-control datepicker" id="customerDateOfBirth" readonly="true"/>
											<span class="input-group-addon"><i
												class="fa fa-calendar"></i> </span>
										</div>
									</div>
								</div>			          		
			          		</spring:bind>
							<spring:bind path="mobileNumber">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="mobileNumber" class="col-sm-2 control-label">Cep Telefonu</label>
									<div class="col-sm-4">
										<form:input path="mobileNumber" type="text" class="form-control" id="mobileNumber"/>
										<form:errors path="mobileNumber" cssClass="control-label error"/>
									</div>
									<!-- /.input group -->
								</div>							
							</spring:bind>
							<spring:bind path="eMail">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="eMail" class="col-sm-2 control-label">E-mail</label>
									<div class="col-sm-4">
										<form:input path="eMail" type="text" class="form-control" id="eMail"/>
										<form:errors path="eMail" cssClass="control-label error"/>
									</div>
									<!-- /.input group -->
								</div>							
							</spring:bind>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
									<button type="submit" class="btn btn-success btn-flat">Müþteri Güncelle</button>
								</div>
							</div>
						</form:form>  			
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

