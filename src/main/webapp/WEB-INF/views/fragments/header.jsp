<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="role" value="role_guest" />
<sec:authorize access="hasRole('USER')">
	<c:set var="role" value="role_user" />
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
	<c:set var="role" value="role_admin" />
</sec:authorize>

<sec:authentication var="loggedCustomer" property="principal"/>

<c:set var="reqURL" value="${pageContext.request.requestURL}" />

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>${title}</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- CSRF Token for Spring Security When Ajax Request -->
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />

<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">
<!-- Animate 3.5.1 -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/animate.min.css"/>">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Datepicker -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Datatables -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/datatables/dataTables.bootstrap.css"/>">
<!-- Bootstrap datetimepicker -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>">
<!-- Bootstrap time Picker -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/timepicker/bootstrap-timepicker.min.css"/>">
<!-- Select2 -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/select2/css/select2.min.css"/>">
<!-- Bootstrap slider -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/bootstrap-slider/bootstrap-slider.min.css"/>">
<!-- Theme style -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/AdminLTE.min.css"/>">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->

<c:choose>
	<c:when test="${fn:contains(reqURL, 'admin')}">
		<link rel="stylesheet" href="<c:url value="/resources/css/skins/skin-green.min.css"/>">
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" href="<c:url value="/resources/css/skins/skin-blue.min.css"/>">
	</c:otherwise>
</c:choose>

<!-- Custom -->
<link rel="stylesheet" href="<c:url value="/resources/css/custom.css"/>">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<!--
  BODY TAG OPTIONS:
  =================
  Apply one or more of the following classes to get the
  desired effect
  |---------------------------------------------------------|
  | SKINS         | skin-blue                               |
  |               | skin-black                              |
  |               | skin-purple                             |
  |               | skin-yellow                             |
  |               | skin-red                                |
  |               | skin-green                              |
  |---------------------------------------------------------|
  |LAYOUT OPTIONS | fixed                                   |
  |               | layout-boxed                            |
  |               | layout-top-nav                          |
  |               | sidebar-collapse                        |
  |               | sidebar-mini                            |
  |---------------------------------------------------------|
  -->
<body
	class="hold-transition 
				${fn:contains(reqURL, 'admin') ? 'skin-green' : 'skin-blue' } 
				${fn:contains(reqURL, 'admin') ? 'sidebar-mini' : 'layout-top-nav fixed' }">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<c:choose>
				<c:when test="${fn:contains(reqURL, 'admin')}">
					<!-- Logo -->
					<a href="<c:url value="/admin"/>" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
						<span class="logo-mini"><b>OBYS</b></span> <!-- logo for regular state and mobile devices -->
						<span class="logo-lg"><b>OBYS</b> Yönetim Paneli</span>
					</a>

					<!-- Header Navbar -->
					<nav class="navbar navbar-static-top" role="navigation">
						<!-- Sidebar toggle button-->
						<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
							role="button"> <span class="sr-only">Toggle navigation</span>
						</a>
						<!-- Navbar Right Menu -->
						<div class="navbar-custom-menu">
							<ul class="nav navbar-nav">
								<li></li>

								<!-- Control Sidebar Toggle Button -->

							</ul>
						</div>
					</nav>
				</c:when>
				<c:otherwise>
					<nav class="navbar navbar-static-top">
						<div class="container">
							<div class="navbar-header">
								<a href="<c:url value="/musteri"/>" class="navbar-brand"><b>Otobüs Bilet Yönetim Sistemi</b></a>
								<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
									<i class="fa fa-bars"></i>
								</button>
							</div>

							<sec:authorize access="hasAnyRole('USER', 'ADMIN')">
								<!-- Collect the nav links, forms, and other content for toggling -->
								<div class="collapse navbar-collapse pull-left"
									id="navbar-collapse">
									<ul class="nav navbar-nav">
										<li><a href="<c:url value="/musteri/seferler"/>">Bilet Al<span class="sr-only">(current)</span>
										</a></li>
										<li><a href="<c:url value="/musteri/biletlerim"/>">Biletlerim</a></li>
										<li><a href="<c:url value="/musteri/bilgiGuncelle"/>">Bilgilerimi Güncelle</a></li>
										<li>
											<form action="<c:url value="/logout" />" method="POST" id="logoutForm">
												<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											</form> 
											<script>
												function logout() {
													document.getElementById("logoutForm").submit();
												}
											</script> <a href="javascript:logout()">Çıkış</a>
										</li>
									</ul>
								</div>
								
								<div class="navbar-custom-menu">
									<ul class="nav navbar-nav">
										<li>
											<!-- Menu Toggle Button --> 
											<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
												<!-- The user image in the navbar-->
												<span>${customer.name}&nbsp;${customer.surname } </span>
											</a>
										</li>
									</ul>
								</div>								
							</sec:authorize>
						</div>
						<!-- /.container-fluid -->
					</nav>
				</c:otherwise>
			</c:choose>
		</header>