<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<div class="span12">
	<input type="hidden" id="baseUrl" value="${base}">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
		</h3>
		<ul class="breadcrumb">
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>

<!-- END PAGE HEADER-->

<!-- BEGIN PAGE CONTENT-->

<div class="row-fluid">
	<div class="span12 responsive" data-tablet="span12 fix-offset" data-desktop="span12">
		<!-- BEGIN EXAMPLE TABLE PORTLET-->
		<div class="portlet box grey">
			<div class="portlet-title">
				<div class="caption"><i class="icon-table"></i>角色管理</div>
				<div class="actions">
					<roleCheck:roleCheck url="${base}/add">
						<a href="${base}/add" type="get" class="btn blue ajaxDef"><i class="icon-pencil"></i> 新建角色</a>
					</roleCheck:roleCheck>
					<roleCheck:roleCheck url="${base}/delete">
						<a fortableId="manager_role_listTable" class="btn red batch_delete"><i class="icon-trash"></i> 批量删除</a>
					</roleCheck:roleCheck>
					<div class="btn-group"></div>
				</div>
			</div>
			<div class="portlet-body">
				<table class="table table-striped table-hover table-bordered" id="manager_role_listTable">
					<thead>
						<tr>
							<th style="width:8px;"><input type="checkbox" class="group-checkable" data-set="#manager_role_listTable .checkboxes" /></th>
							<th>角色名称</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<!-- END EXAMPLE TABLE PORTLET-->
	</div>
</div>
<script>
	jQuery(document).ready(function() {  
	   TableCommon.delayInit("manager_role_listTable",
	   	"${base}/list",null,
		   	[
		   		{ 	
			   		"mData": "id",
			   		"mRender": function(data, type, full) {
						return data != 1?'<input type="checkbox" class="checkboxes" value="'+data+'" />':'';
					}
			   	},
			   	{ 	
			   		"mData": "roleName"
			   	},
			   	{ 
			   		"mData": "id",
			   		"mRender": function(data, type, full) {
			   			var ret ='';
			   			<roleCheck:roleCheck url="${base}/show">
		   			 		ret += '<a class="btn mini green ajaxDef" type="get" href="${base}/show?id='+data+'"><i class="icon-search"></i> 查看</a> ';
		   				</roleCheck:roleCheck>
			   			if(data != 1){
			   				<roleCheck:roleCheck url="${base}/edit">
			   			 		ret += '<a class="btn mini green ajaxDef" type="get" href="${base}/edit?id='+data+'"><i class="icon-edit"></i> 修改</a> ';
			   				</roleCheck:roleCheck>
			   				<roleCheck:roleCheck url="${base}/delete">
			   					ret += '<a class="btn mini red deleteItem" fortableId="manager_role_listTable" delId="'+data+'"><i class="icon-trash"></i> 删除</a> ';
			   				</roleCheck:roleCheck>
			   			}
						return ret;
					} 
			   	}
	   		],
	   		true
	   	);
	   	
	});
	
</script>
<!-- END PAGE CONTENT-->

