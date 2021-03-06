<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 2.1.4 -->
    <script src="<c:url value="/resources/js/jquery-1.12.0.min.js"/>"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <!-- Bootstrap-Notify 3.1.3 -->
    <script src="<c:url value="/resources/js/bootstrap-notify.min.js"/>"></script>
	<!-- Datatables -->
	<script src="<c:url value="/resources/js/plugins/datatables/jquery.dataTables.min.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/datatables/dataTables.bootstrap.min.js"/>"></script>
    <!-- ChartJS 1.0.1 -->
    <script src="<c:url value="/resources/js/plugins/chartjs/Chart.min.js"/>"></script>
    <!-- AdminLTE App -->
    <script src="<c:url value="/resources/js/app.min.js"/>"></script>

    <!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->
	
	<!-- Bootstrap-datetimepicker -->
    <script src="<c:url value="/resources/js/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"/>" charset="UTF-8"></script>
	
    <!-- Datepicker -->
    <script src="<c:url value="/resources/js/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"/>" charset="UTF-8"></script>
    <script src="<c:url value="/resources/js/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.tr.min.js"/>" charset="UTF-8"></script>
        
    
    <!-- bootstrap time picker -->
    <script src="<c:url value="/resources/js/plugins/timepicker/bootstrap-timepicker.min.js"/>"></script>
    
    <!-- Select2 -->
    <script src="<c:url value="/resources/js/plugins/select2/js/select2.js"/>"></script>
    
    <!-- Bootstrap slider -->
    <script src="<c:url value="/resources/js/plugins/bootstrap-slider/bootstrap-slider.min.js"/>"></script>
    
<!-- Script for CSRF header -->
<script>

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

</script>
    