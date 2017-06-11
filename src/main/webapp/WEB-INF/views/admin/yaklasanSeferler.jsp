<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../fragments/header.jsp" />

<jsp:include page="../fragments/mainSideBar.jsp" />

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>Yaklaþan Seferler</h1>
	</section>

	<!-- Main content -->
	<section class="content">
		<!-- Your Page Content Here -->
		<div class="row">
			<div class="col-xs-12">
				<div class="box box-success">
					<!-- 
                <div class="box-header">
                  <h3 class="box-title">Aktif Seferler</h3>
                  <div class="box-tools"> 
                    
                  </div>
                </div><!-- /.box-header -->
					<div class="box-body table-responsive no-padding">
						<table class="table table-hover" style="text-align: center;">
							<tr>
								<th style="width: 21%; text-align: center;">Sefer No</th>
								<th style="width: 21%; text-align: center;">Güzergâh</th>
								<th style="width: 21%; text-align: center;">Kalkýþ</th>
								<th style="width: 6%; text-align: center;">Ýþlemler</th>
							</tr>

							<c:forEach items="${upComingvoyages}" var="voyage" begin="0" end="10">
								<tr>
									<td>${voyage.id}</td>
									<td>${voyage.route.routeName}</td>
									<td>
										<fmt:formatDate value="${voyage.departureTime}" type="date" var="departureTime" pattern="dd.MM.yyy HH:mm"/>
										${departureTime}
									</td>
									<td><a href="<c:url value="/admin/seferDetay/${voyage.id}"/>"
										class="btn btn-xs btn-success btn-flat" title="Detay"><i
											class="fa fa-search"></i></a></td>
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
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->


<jsp:include page="../fragments/mainFooter.jsp" />

<jsp:include page="../fragments/requiredScripts.jsp" />

</body>
</html>

