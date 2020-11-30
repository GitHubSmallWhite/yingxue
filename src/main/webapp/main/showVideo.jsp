<%--
  Created by IntelliJ IDEA.
  User: 武斌
  Date: 2020/11/23
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#videos").jqGrid({
            editurl:"${pageContext.request.contextPath}/video/setV",
            url:"${pageContext.request.contextPath}/video/showVideo",
            edit:"",
            datatype: "json",
            rowNum:3,
            rowList:[3,5,10,20,30],
            pager: '#videoPage',
            sortname: 'id',
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            viewrecords: true,  //是否展示总条数书
            colNames:['ID','标题', '简介', '封面','视频','上传时间','点赞次数','播放次数','所属类别','所属用户','所属分组','当前状态'],
            colModel:[
                {name:'id',index:'id', width:55},
                {name:'title',index:'title', width:90,editable:true},
                {name:'brief',index:'brief', width:90,editable:true},
                {name:'coverPath',index:'coverPath', width:100,edittype:"file",
                    formatter:function(cellvalue, options, rowObject){
                        return "<img src='"+cellvalue+"' width='200px' height=100px'>";
                    }
                },
                {name:'videoPath',index:'videoPath', width:100,editable:true,edittype:"file",
                    formatter:function(cellvalue, options, rowObject){
                        return "<video src='"+cellvalue+"' controls='controls' width='200px' height=100px'>";
                    }
                },
                {name:'uploadTime',index:'uploadTime', width:80, align:"center",sortable:false},
                {name:'likeCount',index:'likeCount', width:80, align:"center"},
                {name:'playCount',index:'playCount', width:80,align:"center"},
                {name:'categoryId',index:'categoryId', width:80,align:"center",editable:true},
                {name:'userId',index:'userId', width:150,align:"center",editable:true},
                {name:'groupId',index:'groupId', width:150,align:"center",editable:true},
                {name:'status',index:'status', width:150,align:"center",editable:true}
            ]
        });
        $("#videos").jqGrid('navGrid','#videoPage',{edit:true,add:true,del:true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
                afterSubmit:function (res) {
                    if ($("#videoPath").val()!=""){
                          $.ajaxFileUpload({
                              url:"${pageContext.request.contextPath}/video/uploadingV",
                              type:"post",
                              data:{"id":res.responseText},
                              fileElementId:"videoPath",
                              success:function () {
                                  $("#videos").trigger("reloadGrid")
                              }
                          });
                      }
                    return "hello";
                }
            },  //修改
            {
                closeAfterAdd: true,
                afterSubmit:function (res) {
                    console.log(res);
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/video/uploadingV",
                        type:"post",
                        data:{"id":res.responseText},
                        fileElementId:"videoPath",
                        success:function () {
                            $("#videos").trigger("reloadGrid")
                        }
                    });
                    return "hello";
                },
            }, //添加
            {
                closeAfterDelete: true,
                reloadAfterSubmit: true,
            }, //删除
        );
    });
</script>
<%--面板--%>
<div class="panel panel-info" style="border:#e6b258 1px solid">
    <%--面板头--%>
    <div class="panel-heading" style="background-color:#f1ca85ad">
        <h2 style="color:#f1ab34;">视频信息</h2>
    </div>
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">视频管理</a></li>
    </ul>
<%--表--%>
<table id="videos" style="border: black 1px solid"/>
<%--分页栏--%>
<div id="videoPage"/>
</div>