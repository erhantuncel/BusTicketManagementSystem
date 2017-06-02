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
            Gider ${not empty expenseForm.id ? 'Güncelle' : 'Ekle'}
          </h1>
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
	       				</div>
	       			</div>
	       			<c:choose>
	       				<c:when test="${not empty expenseForm.id}">
	       					<c:set var="actionUrl" value="${home}admin/gider/${expenseForm.id}/guncelle" />
	       				</c:when>
	       				<c:otherwise>
	       					<c:set var="actionUrl" value="${home}admin/giderEkle" />
	       				</c:otherwise>
	       			</c:choose>
	       			<div class="box-body" style="height: 67vh">
	       				<form:form class="form-horizontal" modelAttribute="expenseForm" action="${actionUrl}" method="POST">
	      
	      					<!--  	
	       					<form:hidden path="registeredTime"/>
	       					-->
	       					<c:choose>
	       						<c:when test="${not empty expenseForm.id}">
					          		<spring:bind path="id">
						          		<div class="form-group">
											<label for="expenseid" class="col-sm-2 control-label">Gider No</label>
											<div class="col-sm-4">
												<form:input path="id" type="text" class="form-control" id="expenseid" 
															readonly="true"/>
											</div>
										</div>			          		
					          		</spring:bind>
	       						</c:when>
	       						<c:otherwise>
	       							<form:hidden path="id"/>
	       						</c:otherwise>
	       					</c:choose>
			          		<spring:bind path="voyage">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for=expenseVoyage class="col-sm-2 control-label">Sefer Numarasý</label>
									<div class="col-sm-4">
										<form:input path="voyage" type="text" class="form-control" id="expenseVoyage" 
											 	readonly="${not empty expenseForm.id ? 'true' : 'false'}"/>
										<form:errors path="type" cssClass="control-label error"/>
									</div>
								</div>			          		
			          		</spring:bind>
			          		<spring:bind path="type">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="expenseType" class="col-sm-2 control-label">Gider Tipi</label>
									<div class="col-sm-4">
										<form:select path="type" class="form-control" id="expenseType">
											<form:option value="" label="Gider Tipi Seçiniz"/>
	       									<form:options items="${expenseTypeMap}"/>
										</form:select>
										<form:errors path="type" cssClass="control-label error"/>
									</div>
								</div>
			          		</spring:bind>
			          		<spring:bind path="price">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label for="expensePrice" class="col-sm-2 control-label">Tutar</label>
									<div class="col-sm-4">
										<form:input path="price" type="text" class="form-control" id="expensePrice"/>
										<form:errors path="type" cssClass="control-label error"/>
									</div>
								</div>			          		
			          		</spring:bind>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-4">
									<button type="submit" class="btn btn-success btn-flat">${not empty expenseForm.id ? 'Güncelle' : 'Ekle'}</button>
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

</script>

  </body>
</html>

