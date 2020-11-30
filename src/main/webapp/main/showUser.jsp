<%--
  Created by IntelliJ IDEA.
  User: 武斌
  Date: 2020/11/19
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#users").jqGrid({
            url:"${pageContext.request.contextPath}/admin/showUser",
            edit:"",
            datatype: "json",
            rowNum:3,
            rowList:[3,5,10,20,30],
            pager: '#userPage',
            sortname: 'id',
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            viewrecords: true,  //是否展示总条数书
            colNames:['ID','昵称', '头像', '手机号','描述','学分','状态','注册时间'],
            colModel:[
                {name:'id',index:'id', width:55},
                {name:'nickName',index:'invdate', width:90},
                {name:'picImg',index:'name asc, invdate', width:100,
                    formatter:function(cellvalue, options, rowObject){
                        return "<img src='${pageContext.request.contextPath}/"+cellvalue+"' width='200px' height=100px'>";
                    }
                },
                {name:'phone',index:'amount', width:80, align:"center"},
                {name:'brief',index:'tax', width:80, align:"center"},
                {name:'score',index:'total', width:80,align:"center"},
                {name:'status',index:'total', width:80,align:"center",
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==1){
                            return "<button class='btn btn-info' onclick='x("+rowObject.id+")' id='"+rowObject.id+"bt' >解冻</button>";
                        }else{
                            return "<button class='btn btn-success' onclick='x("+rowObject.id+")' id='"+rowObject.id+"bt' >冻结</button>";
                        }
                    }
                },
                {name:'createDate',index:'note', width:150, sortable:false,align:"center"}
            ]
        });
        $("#users").jqGrid('navGrid','#userPage',{edit:false,add:false,del:false});

    })
    function x(id) {
        $.ajax({
            url:"${pageContext.request.contextPath}/admin/set",
            type:"POST",
            data:"id="+id,
            datatype: "JSON",
            success:function (result) {
                if(result.status==1){
                $("#"+result.id+"bt").prop("class","btn btn-info").text("解冻");
                }else {
                $("#"+result.id+"bt").prop("class","btn btn-success").text("冻结");
                }
            }
        })
    }
    function code(){
        var phone=$("#phone").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/admin/phoneCode",
            type:"post",
            data:"phone="+phone,
            datatype:"json",
            success:function (res) {
                if (res.status==200){
                    $("#phone").val("");
                    alert("验证码:"+res.code);
                }else {
                    alert("发送失败,原因："+res.message);
                }
            }
        })
    }
    function out111() {
       $.ajax({
           url:"${pageContext.request.contextPath}/admin/userExport",
           type:"post",
           datatype:"json",
           success:function (res) {
               alert("导出成功");
           }
       })

    }
    function import111() {
        $.ajaxFileUpload({
            url:"${pageContext.request.contextPath}/admin/userImport",
            type:"post",
            datatype:"json",
            success:function (r) {
                alert("导入成功");
                $("#users").trigger("reloadGrid")
            }
        })
    }
</script>
<%--面板--%>
<div class="panel panel-info">
    <%--面板头--%>
    <div class="panel-heading">
        <h2>用户信息</h2>
    </div>
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>
    <div class="row" style="height:40px;margin-top: 8px">
        <div class="col-md-6">
            <button class="btn btn-info" type="button" onclick="out111()">导出用户信息</button>
            <button class="btn btn-success" type="button" onclick="import111()">导入用户信息</button>
            <button class="btn btn-warning">测试按钮</button>
        </div>
        <div class="col-lg-6">
            <form action="" method="post">
            <div class="input-group">
                   <input type="text" class="form-control" id="phone" placeholder="手机号">
                   <span class="input-group-btn">
                 <button class="btn btn-default" type="button" style="color: #1c94c4"  onclick="code()">发送验证码</button>
                 </span>
            </div><!-- /input-group -->
            </form>
        </div><!-- /.col-lg-6 -->
    </div>
    <%--表--%>
    <table id="users" style="border: black 1px solid"/>
    <%--分页栏--%>
    <div id="userPage"/>
</div>