<%--
  Created by IntelliJ IDEA.
  User: 武斌
  Date: 2020/11/20
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function(){
        //页面加载调用一级类别的jqGrid
        pageInit();
    });
    //一级类别表
    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${path}/admin/showOneC",
            datatype : "json",
            rowNum : 8,
            rowList : [ 8, 10, 20, 30 ],
            pager : '#catePage',
            sortname : 'id',
            viewrecords : true,
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [  'ID', '类别名', '类别级别' ],
            colModel : [
                {name : 'id',index : 'id',  width : 55,},
                {name : 'cateName',index : 'cateName',width : 90,editable:true},
                {name : 'levels',index : 'levels',width : 100}
            ],
            editurl:"${path}/admin/setOneC"
            ,
            subGrid : true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded : function(subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add : true,edit : true,del : true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                closeAfterDelete: true,
                reloadAfterSubmit: true,
                afterComplete:function (result) {
                    if(result.responseJSON.no!=null){
                        confirm(result.responseJSON.no);
                    }
                }
            }, //删除
        );
    }
    //二级类别表（子表）
    function addSubGrid(subgridId, rowId){

        var subgridTableId= subgridId + "Table";
        var pagerId= subgridId+"Page";


        $("#"+subgridId).html("" +
            "<table id='"+subgridTableId+"' />" +
            "<div id='"+pagerId+"' />"
        );



        $("#" + subgridTableId).jqGrid({
            url : "${path}/admin/showTwoC?id=" + rowId,
            datatype : "json",
            rowNum : 20,
            pager : "#"+pagerId,
            sortname : 'num',
            sortorder : "asc",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [  'ID', '类别名', '类别级别','所属类别ID'],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cateName',index : 'cateName',width : 90,editable: true},
                {name : 'levels',index : 'levels',width : 100},
                {name : 'parentId',index : 'levels',width : 100,editable:true,edittype:'select',editoptions: {
                    dataUrl:"${path}/admin/findNameC"}
                }
            ],
            editurl: "${path}/admin/setTwoC"
        });

        $("#" + subgridTableId).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                closeAfterDelete: true,
                reloadAfterSubmit: true,

            }//删除
        );
    }
</script>
<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable" />
    <%--分页工具栏--%>
    <div id="catePage" />

</div>