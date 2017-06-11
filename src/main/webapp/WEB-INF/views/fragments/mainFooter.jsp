<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="reqURL" value="${pageContext.request.requestURL}" />

<!-- Main Footer -->
      <footer class="main-footer ${fn:contains(reqURL, 'admin') ? 'text-green' : '' }">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
          
        </div>
        <!-- Default to the left -->
        <strong>2015 - <a href="#" class="${fn:contains(reqURL, 'admin') ? 'text-green' : '' }">Erhan TUNÇEL</a></strong>
      </footer>
    </div><!-- ./wrapper -->