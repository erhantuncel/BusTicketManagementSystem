<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="<c:url value="resources/css/bootstrap.min.css"/>">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Datepicker -->
    <link rel="stylesheet" href="<c:url value="resources/js/plugins/datepicker/datepicker3.css"/>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="resources/css/AdminLTE.min.css"/>">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->

    <link rel="stylesheet" href="<c:url value="resources/css/skins/skin-blue.min.css"/>">

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
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <!-- Main Header -->
      <header class="main-header">

        <!-- Logo -->
        <a href="admin.html" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>O</b>BS</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>OBS</b>Yönetim Paneli</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->

              <!-- User Account Menu -->
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <img src="<c:url value="resources/img/avatar5.png"/>" class="user-image" alt="User Image">
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">Erhan TUNÇEL</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- The user image in the menu -->
                  <li class="user-header">
                    <img src="<c:url value="resources/img/avatar5.png"/>" class="img-circle" alt="User Image">
                    <p>
                      Erhan TUNÇEL - Yönetici
                    </p>
                  </li>

                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profil</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat">Çıkış</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- Sidebar user panel (optional) -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="<c:url value="resources/img/avatar5.png"/>" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
              <p>Erhan TUNÇEL</p>
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
                <li><a href="seferler.html">Seferler</a></li>
                <li><a href="seferEkle.html">Sefer Ekle</a></li>
                <li><a href="aktifSeferler.html">Aktif Seferler</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-bus"></i> <span>Otobüs Yönetimi</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="otobusler.html">Otobüsler</a></li>
                <li><a href="otobusEkle.html">Otobüs Ekle</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-user"></i> <span>Müşteri Yönetimi</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="musteriler.html">Müşteriler</a></li>
                <li><a href="musteriEkle.html">Müşteri Ekle</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-ticket"></i> <span>Bilet Yönetimi</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="biletler.html">Biletler</a></li>
                <li><a href="biletAl.html">Bilet Al</a></li>
                <li><a href="rezervasyon.html">Rezervasyon Yap</a></li>
              </ul>
            </li>
            <li class="treeview">
              <a href="#"><i class="fa fa-bank"></i> <span>Finans</span> <i class="fa fa-angle-left pull-right"></i></a>
              <ul class="treeview-menu">
                <li><a href="gelirler.html">Gelirler</a></li>
                <li><a href="giderler.html">Giderler</a></li>
              </ul>
            </li>
            <li><a href="raporlar.html"><i class="fa fa-file-text-o"></i> <span>Raporlar</span></a></li>
          </ul><!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Özet
          </h1>
        </section>

        <!-- Main content -->
        <section class="content">

          <!-- Your Page Content Here -->
          <div class="row">
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-aqua">
                <div class="inner">
                  <h3>3257</h3>
                  <p>Satılan Bilet</p>
                </div>
                <div class="icon">
                  <i class="fa fa-ticket"></i>
                </div>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-yellow">
                <div class="inner">
                  <h3>1548</h3>
                  <p>Kayıtlı Kullanıcı</p>
                </div>
                <div class="icon">
                  <i class="ion ion-person-add"></i>
                </div>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-green">
                <div class="inner">
                  <h3>1500<sup style="font-size: 20px">TL</sup></h3>
                  <p>Gelir</p>
                </div>
                <div class="icon">
                  <i class="fa fa-download"></i>
                  <!--<i class="fa fa-turkish-lira"></i>-->
                </div>
              </div>
            </div><!-- ./col -->
            <div class="col-lg-3 col-xs-6">
              <!-- small box -->
              <div class="small-box bg-red">
                <div class="inner">
                  <h3>750<sup style="font-size: 20px">TL</sup></h3>
                  <p>Gider</p>
                </div>
                <div class="icon">
                  <i class="fa fa-upload"></i>
                  
                  <!--<i class="fa fa-turkish-lira"></i>-->
                </div>
              </div>
            </div><!-- ./col -->
          </div><!-- ./row -->

          <div class="row">
            <div class="col-xs-6">
              <div class="box box-info">
                <div class="box-header with-border">
                  <h3 class="box-title">Aktif Seferler</h3>
                  <div class="box-tools pull-right">
                  </div><!-- /.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                  <table class="table table-bordered table-condensed">
                    <tr>
                      <th style="width:20%;">Sefer No</th>
                      <th>Rota</th>
                      <th style="width:25%;">Süre</th>
                      <th style="width:5%;">İşlem</th>
                    </tr>
                    <tr>
                      <td>1545</td>
                      <td>Bolu - Ankara</td>
                      <td>
                        <div class="progress progress-xs">
                          <div class="progress-bar progress-bar-danger" style="width: 55%"></div>
                        </div>
                      </td>
                      <td><button class="btn btn-xs btn-primary"><i class="fa fa-search"></i>&nbsp;Detay</button></td>
                    </tr>
                    <tr>
                      <td>1658</td>
                      <td>İstanbul - İzmir</td>
                      <td>
                        <div class="progress progress-xs progress-striped active">
                          <div class="progress-bar progress-bar-primary" style="width: 30%"></div>
                        </div>
                      </td>
                      <td><button class="btn btn-xs btn-primary"><i class="fa fa-search"></i>&nbsp;Detay</button></td>
                    </tr>
                    <tr>
                      <td>1874</td>
                      <td>Antalya - Konya</td>
                      <td>
                        <div class="progress progress-xs progress-striped active">
                          <div class="progress-bar progress-bar-success" style="width: 90%"></div>
                        </div>
                      </td>
                      <td><button class="btn btn-xs btn-primary"><i class="fa fa-search"></i>&nbsp;Detay</button></td>
                    </tr>
                  </table>
                </div><!-- /.box-body -->
                <div class="box-footer text-center">
                  <a href="javascript::;" class="uppercase">Tüm Seferleri Göster</a>
                </div><!-- /.box-footer -->
              </div><!-- /.box -->

              <div class="box box-danger">
                <div class="box-header with-border">
                  <h3 class="box-title">Bilet / Rezervasyon</h3>
                  <div class="box-tools pull-right">
                  </div><!-- /.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                  <table class="table table-bordered table-condensed">
                    <tr>
                      <th style="width:30%;">Ad Soyad</th>
                      <th>Rota</th>
                      <th style="width:15%;">Durum</th>
                      <th style="width:5%;">İşlem</th>
                    </tr>
                    <tr>
                      <td>Erhan TUNÇEL</td>
                      <td>Bolu - Ankara</td>
                      <td><span class="label label-success">Satıldı</span></td>
                      <td><button class="btn btn-xs btn-primary"><i class="fa fa-search"></i>&nbsp;Detay</button></td>
                    </tr>
                    <tr>
                      <td>Serhan TUNÇEL</td>
                      <td>Ankara - Bolu</td>
                      <td><span class="label label-warning">Rezerve</span></td>
                      <td><button class="btn btn-xs btn-primary"><i class="fa fa-search"></i>&nbsp;Detay</button></td>
                    </tr>
                    <tr>
                      <td>Ahmet Yılmaz</td>
                      <td>İzmit - Sakarya</td>
                      <td><span class="label label-warning">Rezerve</span></td>
                      <td><button class="btn btn-xs btn-primary"><i class="fa fa-search"></i>&nbsp;Detay</button></td>
                    </tr>
                  </table>
                </div><!-- /.box-body -->
                <div class="box-footer text-center">
                  <a href="javascript::;" class="uppercase">Tüm Biletleri Göster</a>
                </div><!-- /.box-footer -->
              </div><!-- /.box -->
            </div>
            <div class="col-xs-6">
              <div class="box box-primary">
                <div class="box-header with-border">
                  <h3 class="box-title">Aylık Satılan Bilet Sayıları</h3>
                  <div class="box-tools pull-right">
                  </div><!-- /.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body" style="text-align:center;">
                  <div class="chart">
                    <canvas id="lineChartTicket" style="height:150px"></canvas>
                  </div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
              <div class="box box-warning">
                <div class="box-header with-border">
                  <h3 class="box-title">Aylık Kullanıcı Kayıtları</h3>
                  <div class="box-tools pull-right">
                  </div><!-- /.box-tools -->
                </div><!-- /.box-header -->
                <div class="box-body" style="text-align:center;">
                  <div class="chart">
                    <canvas id="lineChartUser" style="height:150px"></canvas>
                  </div>
                </div><!-- /.box-body -->
              </div><!-- /.box -->
            </div>
          </div>
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

      <!-- Main Footer -->
      <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
          
        </div>
        <!-- Default to the left -->
        <strong>2015 - <a href="#">Erhan TUNÇEL</a></strong>
      </footer>
    </div><!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->

    <!-- jQuery 2.1.4 -->
    <script src="<c:url value="resources/js/jquery-1.12.0.min.js"/>"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<c:url value="resources/js/bootstrap.min.js"/>"></script>
    <!-- ChartJS 1.0.1 -->
    <script src="<c:url value="resources/js/plugins/chartjs/Chart.min.js"/>"></script>
    <!-- AdminLTE App -->
    <script src="<c:url value="resources/js/app.min.js"/>"></script>

    <!-- Optionally, you can add Slimscroll and FastClick plugins.
         Both of these plugins are recommended to enhance the
         user experience. Slimscroll is required when using the
         fixed layout. -->

    <!-- Datepicker -->
    <script src="<c:url value="resources/js/plugins/datepicker/bootstrap-datepicker.js"/>"></script>

    <script>
      $(function () {
        // Datepicker - voyage
        $('#voyage').datepicker({
            language: "tr"
        });

        /* ChartJS
         * -------
         * Here we will create a few charts using ChartJS
         */

        var lineChartDataTicket = {
          labels: ["Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz"],
          datasets: [
            {
              label: "Satılan Bilet",
              fillColor: "rgba(60,141,188,0.9)",
              strokeColor: "rgba(60,141,188,0.8)",
              pointColor: "#3b8bba",
              pointStrokeColor: "rgba(60,141,188,1)",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(60,141,188,1)",
              data: [28, 48, 40, 45, 86, 67, 90]
            }
          ] 
        };

        var lineChartDataUser = {
          labels: ["Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz"],
          datasets: [
            {
              label: "Satılan Bilet",
              fillColor: "rgba(60,141,188,0.9)",
              strokeColor: "rgba(60,141,188,0.8)",
              pointColor: "#3b8bba",
              pointStrokeColor: "rgba(60,141,188,1)",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(60,141,188,1)",
              data: [228, 148, 155, 268, 275, 286, 390]
            }
          ] 
        };

        var lineChartOptions = {
          //Boolean - If we should show the scale at all
          showScale: true,
          //Boolean - Whether grid lines are shown across the chart
          scaleShowGridLines: false,
          //String - Colour of the grid lines
          scaleGridLineColor: "rgba(0,0,0,.05)",
          //Number - Width of the grid lines
          scaleGridLineWidth: 1,
          //Boolean - Whether to show horizontal lines (except X axis)
          scaleShowHorizontalLines: true,
          //Boolean - Whether to show vertical lines (except Y axis)
          scaleShowVerticalLines: true,
          //Boolean - Whether the line is curved between points
          bezierCurve: true,
          //Number - Tension of the bezier curve between points
          bezierCurveTension: 0.3,
          //Boolean - Whether to show a dot for each point
          pointDot: true,
          //Number - Radius of each point dot in pixels
          pointDotRadius: 4,
          //Number - Pixel width of point dot stroke
          pointDotStrokeWidth: 1,
          //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
          pointHitDetectionRadius: 20,
          //Boolean - Whether to show a stroke for datasets
          datasetStroke: true,
          //Number - Pixel width of dataset stroke
          datasetStrokeWidth: 2,
          //Boolean - Whether to fill the dataset with a color
          datasetFill: true,
          //String - A legend template
          legendTemplate: "",
          //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
          maintainAspectRatio: true,
          //Boolean - whether to make the chart responsive to window resizing
          responsive: true
        };

        //-------------
        //- LINE CHART -
        //--------------
        var lineChartCanvasTicket = $("#lineChartTicket").get(0).getContext("2d");
        var lineChart = new Chart(lineChartCanvasTicket);
        lineChartOptions.datasetFill = false;
        lineChart.Line(lineChartDataTicket, lineChartOptions);

        var lineChartCanvasUser = $("#lineChartUser").get(0).getContext("2d");
        var lineChart = new Chart(lineChartCanvasUser);
        lineChartOptions.datasetFill = false;
        lineChart.Line(lineChartDataUser, lineChartOptions);

      });
    </script>
  </body>
</html>
