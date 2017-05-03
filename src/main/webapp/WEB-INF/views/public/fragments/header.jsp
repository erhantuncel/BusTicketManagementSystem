<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">
<!-- Animate 3.5.1 -->	
<link rel="stylesheet"
	href="<c:url value="/resources/css/animate.min.css"/>">	
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- Bootstrap datetimepicker -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>">
<!-- Datepicker -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Bootstrap time Picker -->
<link rel="stylesheet"
	href="<c:url value="/resources/js/plugins/timepicker/bootstrap-timepicker.min.css"/>">
<!-- Theme style -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/AdminLTE.min.css"/>">
<!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->

<link rel="stylesheet"
	href="<c:url value="/resources/css/skins/skin-blue.min.css"/>">

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
<body class="hold-transition skin-blue layout-top-nav fixed">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">
			<nav class="navbar navbar-static-top">
				<div class="container">
					<div class="navbar-header">
						<a href="<c:url value="/"/>" class="navbar-brand"><b>Online
								Bilet Sistemi</b></a>
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar-collapse">
							<i class="fa fa-bars"></i>
						</button>
					</div>

					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse pull-left"
						id="navbar-collapse">
						<ul class="nav navbar-nav">
							<li class="active">
								<a href="<c:url value="/seferler"/>">Bilet Al <span class="sr-only">(current)</span></a>
							</li>
							<li><a href="<c:url value="/seferler"/>">Rezervasyon Yap</a></li>
							<li><a href="<c:url value="/biletlerim"/>">Biletlerim</a></li>
						</ul>
					</div>
					
					<sec:authorize access="hasRole('USER')">
						<div class="navbar-custom-menu">
							<ul class="nav navbar-nav">
								<li>
				                    <!-- Menu Toggle Button -->
				                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
				                      <!-- The user image in the navbar-->
				                      <span>${customer.name}&nbsp;${customer.surname }
				                      </span>
				                    </a>
				                    <ul class="dropdown-menu">
				                      <li><a href="#">Bilgilerimi Güncelle</a></li>
				                      <li role="separator" class="divider"></li>
				                      <li>
				                      	<form action="<c:url value="/logout" />" method="POST" id="logoutForm">
				                      		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
				                      	</form>
				                      	<script>
				                      		function logout() {
				                      			document.getElementById("logoutForm").submit();
				                      		}
				                      	</script>
				                      <a href="javascript:logout()">Çıkış</a></li>
				                    </ul>
				                  </li>
							</ul>
						</div>					
					</sec:authorize>
					
					
				</div>
				<!-- /.container-fluid -->
			</nav>
		</header>