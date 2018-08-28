<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- Sidebar user panel (optional) -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="<c:url value="/resources/img/avatar5.png"/>" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>${admin.name}&nbsp;${admin.surname }</p>
              <p></p>
            </div>
          </div>

          <!-- search form (Optional) -->

          <!-- /.search form -->

          <!-- Sidebar Menu -->
          <ul class="sidebar-menu">
            <li class="header">İŞLEMLER</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="treeview">
              <a href="#"><i class="fa fa-random"></i> <span>Sefer Yönetimi</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="<c:url value="/admin/seferler"/>">Seferler</a></li>
                <li><a href="<c:url value="/admin/seferEkle"/>">Sefer Ekle</a></li>
                <li><a href="<c:url value="/admin/guzergahlar"/>">Güzergâhlar</a></li>
                <li><a href="<c:url value="/admin/guzergahEkle"/>">Güzergâh Ekle</a></li>
                <li><a href="<c:url value="/admin/aktifSeferler"/>">Aktif Seferler</a></li>
                <li><a href="<c:url value="/admin/yaklasanSeferler"/>">Yaklaşan Seferler</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-bus"></i> <span>Araç Yönetimi</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="<c:url value="/admin/araclar"/>">Araçlar</a></li>
                <li><a href="<c:url value="/admin/aracEkle"/>">Araç Ekle</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-user"></i> <span>Müşteri Yönetimi</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="<c:url value="/admin/musteriler"/>">Müşteriler</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-bank"></i> <span>Finans Yönetimi</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="<c:url value="/admin/gelirler"/>">Gelirler</a></li>
                <li><a href="<c:url value="/admin/giderler"/>">Giderler</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-file-text-o"></i> <span>Raporlar</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="gelirler.html">Gelir Raporları</a></li>
                <li><a href="giderler.html">Gider Raporları</a></li>
                <li><a href="giderler.html">Bilet Raporları</a></li>
                <li><a href="giderler.html">Kullanıcı Raporları</a></li>
              </ul>
            </li>
            <li>
            	<form action="<c:url value="/logout"/>" method="POST" id="logoutForm">
            		<input type="hidden"
							name="${_csrf.parameterName}"
							value="${_csrf.token}" />
            	</form>
            	
            	<script>
            		function logout() {
            			document.getElementById("logoutForm").submit();
            		}
            	</script>
            	<a href="javascript:logout()"><i class="fa fa-sign-out"></i> <span>Çıkış</span></a>
            </li>
          </ul><!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
      </aside>