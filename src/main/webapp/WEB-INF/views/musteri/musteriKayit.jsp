<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="../fragments/publicIndexHeader.jsp" />

<c:url var="home" value="/" scope="request" />

		<!-- Content Wrapper. Contains page content -->
		<!-- style="background: url('<c:url value="/resources/img/bg-road-2.jpg"/>');" -->
		<div class="content-wrapper publicIndexContetWrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header publicIndexHeaderKayit">
			</section>

			<!-- Main content -->
			<section class="content">

				<!-- Your Page Content Here -->
				<div class="row vertical-center">
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="row">
							<div class="col-sm-12">
								<div class="box box-primary box-solid">
									<div class="box-header text-center" style="font-size: 18pt;">
										<strong>MÜÞTERÝ KAYIT</strong>
									</div>
									<div class="box-body">
										<div class="col-sm-12">
											<form:form class="form-horizontal" modelAttribute="customerForm" 
														action="${home}musteriKayit" method="POST">
												<!-- 
												<form:hidden path="enabled"/>
												<form:hidden path="id"/>
												<form:hidden path="dateOfRegister"/>
	       										<form:hidden path="timeOfLastOnline"/>
	       										 -->
												<spring:bind path="tcNumber">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="tc" class="col-sm-4 control-label">T.C Numarasý</label>
														<div class="col-sm-6">
															<form:input path="tcNumber" type="text" class="form-control" id="tc"/>
															<form:errors path="tcNumber" cssClass="control-label error" />
														</div>
														<!-- /.input group -->
													</div>
												</spring:bind>
												<spring:bind path="name">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="customerName" class="col-sm-4 control-label">Ad</label>
														<div class="col-sm-6">
															<form:input path="name" type="text" class="form-control" id="customerName"/> 
															<form:errors path="name" cssClass="control-label error" />
														</div>
														<!-- /.input group -->
													</div>
												</spring:bind>
												<spring:bind path="surname">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="customerSurname" class="col-sm-4 control-label">Soyad</label>
														<div class="col-sm-6">
															<form:input path="surname" type="text" class="form-control" id="customerSurname"/>
															<form:errors path="surname" cssClass="control-label error" />
														</div>
														<!-- /.input group -->
													</div>
												</spring:bind>
												<spring:bind path="gender">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="gender" class="col-sm-4 control-label">Cinsiyet</label>
														<div class="col-sm-6">
															<form:select path="gender" class="form-control" id="customerGender">
																<form:option value=""/>
																<form:options items="${genderValues}"/>
															</form:select>
															<form:errors path="gender" cssClass="control-label error"/>
														</div>
													</div>
								          		</spring:bind>
								          		<spring:bind path="dateOfBirth">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="dob" class="col-sm-4 control-label">Doðum Tarihi</label>
														<div class="col-sm-6">
															<div class="input-group">
																<form:input path="dateOfBirth" type="text" class="form-control datepicker" 
																		id="customerDateOfBirth"/>
																<span class="input-group-addon"><i
																	class="fa fa-calendar"></i> </span>
															</div>
															<form:errors path="dateOfBirth" cssClass="control-label error"/>
														</div>
													</div>			          		
								          		</spring:bind>
												<spring:bind path="mobileNumber">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="mobileNumber" class="col-sm-4 control-label">Cep Telefonu</label>
														<div class="col-sm-6">
															<form:input path="mobileNumber" type="text" class="form-control" id="mobileNumber"/>
															<form:errors path="mobileNumber" cssClass="control-label error"/>
														</div>
														<!-- /.input group -->
													</div>							
												</spring:bind>
												<spring:bind path="eMail">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="eMail" class="col-sm-4 control-label">E-mail</label>
														<div class="col-sm-6">
															<form:input path="eMail" type="text" class="form-control" id="eMail"/>
															<form:errors path="eMail" cssClass="control-label error"/>
														</div>
														<!-- /.input group -->
													</div>							
												</spring:bind>
												<spring:bind path="password">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label for="customerPassword" class="col-sm-4 control-label">Þifre</label>
														<div class="col-sm-6">
															<form:input path="password" type="password" class="form-control" id="customerPassword"/>
															<form:errors path="password" cssClass="control-label error"/>
														</div>
														<!-- /.input group -->
													</div>
												</spring:bind>
												<div class="form-group">
													<div class="col-sm-offset-4 col-sm-6">
														<button type="submit" class="btn btn-primary">Kayýt Ol</button>
													</div>
												</div>
											</form:form>
										</div>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
							<!-- <div class="col-sm-3" style=""></div>  -->
							
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- ./wrapper -->
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