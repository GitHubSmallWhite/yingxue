<%--
  Created by IntelliJ IDEA.
  User: 武斌
  Date: 2020/11/24
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#logsS").jqGrid({
            url:"${pageContext.request.contextPath}/logs/showLogs",
            edit:"",
            datatype: "json",
            rowNum:3,
            rowList:[3,5,10,20,30],
            pager: '#logPage',
            sortname: 'id',
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            viewrecords: true,  //是否展示总条数书
            colNames:['ID','管理员', '操作', '时间','操作状态'],
            colModel:[
                {name:'id',index:'id', width:55},
                {name:'operator',index:'operator', width:90,editable:true},
                {name:'operation',index:'operation', width:90,editable:true},
                {name:'dateTime',index:'dateTime', width:100,editable:true},
                {name:'res',index:'res', width:100,editable:true}
            ],
            editurl:"${pageContext.request.contextPath}/logs/setLogs"
        });
        $("#logsS").jqGrid('navGrid','#logPage',{edit:false,add:false,del:true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit:true,
            }, //添加
            {
                closeAfterDelete: true,
                reloadAfterSubmit: true,
            }, //删除
        );
    });
</script>
<%--面板--%>
<div class="panel panel-info" style="border:#3786ca 1px solid">
    <%--面板头--%>
    <div class="panel-heading" style="background-color:#3786ca">
        <h2 style="color:#075ea9;">日志信息</h2>
    </div>
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">视频管理</a></li>
    </ul>
    <%--表--%>
    <table id="logsS" style="border: black 1px solid"/>
    <%--分页栏--%>
    <div id="logPage"/>
</div>