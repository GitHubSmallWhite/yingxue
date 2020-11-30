<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <p class="navbar-brand"><font size="3" color="#aba4a8">应学视频App后台管理系统</font></p>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><p class="navbar-text">欢迎：<font color="blue">${sessionScope.admin.username}</font></p></li>
                    <li><a href="${path}/admin/loginOut">退出系统&nbsp;<span class="glyphicon glyphicon-log-out"></span></a></li>
                </ul>
            </div>
        </div>
        </div>
    </nav>
    <!--栅格系统-->
    <div class="row">
        <!--左边手风琴部分-->
        <div class="col-md-3">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default" >
                    <div class="panel-heading" role="tab" id="headingOne" style="background-color: #f3cecd;text-align: center">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="color: #a94442">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <div class="list-group col-md-4 col-md-offset-4" style="text-align:center">
                                <a href="javascript:$('#show').load('${path}/main/showUser.jsp')" class="list-group-item" style="background-color: #eabe72;color: #1d5987">用户展示</a>
                                <a href="#" class="list-group-item" style="margin-top: 20px;background-color: #eabe72;color: #1d5987">用户统计</a>
                                <a href="#" class="list-group-item" style="margin-top: 20px;background-color: #eabe72;color: #1d5987">用户分布</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo" style="background-color: #c7f7c8;text-align: center">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" style="color: #2b542c">
                                分类管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <div class="list-group col-md-4 col-md-offset-4" style="text-align:center">
                                <a href="javascript:$('#show').load('${path}/main/showCategory.jsp')" class="list-group-item" style="background-color: #5aaf5c;color: #1d5987">分类展示</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree" style="background-color: #fdeac9;text-align: center">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="color: #666600;">
                                视频管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <div class="list-group col-md-4 col-md-offset-4" style="text-align:center">
                                <a href="javascript:$('#show').load('${path}/main/showVideo.jsp')" class="list-group-item" style="background-color: #e6b258;color: #1d5987">视频展示</a>
                                <a href="javascript:$('#show').load('${path}/main/showVideo.jsp')" class="list-group-item" style="background-color: #e6b258;color: #1d5987;margin-top: 20px">视频搜索</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour" style="background-color: #dc1713;text-align: center">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseFour" style="color: #a70906">
                                反馈管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour" >
                        <div class="panel-body">

                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive" style="background-color: #bfdcea;text-align: center">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive" style="color: #1d5987">
                                日志管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive" >
                        <div class="panel-body">
                            <div class="list-group col-md-4 col-md-offset-4" style="text-align:center">
                                <a href="javascript:$('#show').load('${path}/main/showLogs.jsp')" class="list-group-item" style="background-color: #3f98e4;color: #1d5987">日志展示</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-9" id="show">
        <!--巨幕开始-->
            <div class="jumbotron col-md-12">
                <div class="container">
                    <h1><p>欢迎来到应学视频App后台管理系统</p></h1>
                </div>
            </div>
        <!--右边轮播图部分-->
            <div class="col-md-12" style="height: 500px">
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" >
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active" >

                            <img src="${path}/img/1.jpg" alt="..." style="height: 500px;width:100% ">
                            <div class="carousel-caption">

                            </div>
                        </div>
                        <div class="item">
                            <img src="${path}/img/2.jpg" alt="..." style="height: 500px;width:100% ">
                            <div class="carousel-caption">

                            </div>
                        </div>
                        <div class="item">
                            <img src="${path}/img/3.jpg" alt="..." style="height: 500px;width:100% ">
                            <div class="carousel-caption">

                            </div>
                        </div>
                        <div class="item">
                            <img src="${path}/img/4.jpg" alt="..." style="height: 500px;width:100% ">
                            <div class="carousel-caption">

                            </div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>

    <!--栅格系统-->
    </div>
    <!--页脚-->
    <nav class="panel panel-footer" style="min-height: 15px;text-align: center" >
            @百知教育zhangcn@zparkhr.com
    </nav>
</body>
</html>
