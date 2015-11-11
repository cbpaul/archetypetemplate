var App = function() {
	var pageContentBody = $(".page-content .page-content-body");
	var ajaxReq = function(url, params, type, dataType,successfun) {
		$.ajax({
			"type" : type,
			"url" : url,
			"data" : params,
			"dataType" : dataType,
			"success" : function(res) {
				successfun(res);
			}
		});
	};
	var registAjaxDefClick= function() {
		$("body").on("click", " .ajaxDef", function(e) {
			e.preventDefault();
			var method = $(this).attr("ajax-type") || "get";
			var url = $(this).attr("ajax-url") || $(this).attr("href");
			var form = $(this).attr("ajax-param-formid");
			pageContentBody = $(this).attr("ajax-load-node")||pageContentBody;
			var params = "";
			if (form && form.length > 0) {
				params = $(form).serialize();
			}
			pageTableLoad(url,params,method,"html");
			pageContentBody = $(".page-content .page-content-body");
		});
		$("body").on("click", " .list-delete", function(e) {
			var tableId = $(this).parents("table").attr("id");
			var url = $(this).attr("ajax-url");
			ajaxReq(url,"","get","json",function(res){
				if(res.code==200){
					reloadPageTable(tableId);
				}
			});
		});
	};
	var pageTableLoad = function(url,params,method,datatype){
		ajaxReq(url, params, method, datatype,function(res) {
			if (res == "sessionout") {
				location.href = "";
				return;
			}
			pageContentBody.html(res.trim());
		});
	};
	var reloadPageTable = function(tableId){
		var formForTable = $("form[fortable='"+tableId+"']");
		var params= "";
		if(formForTable && formForTable.length>0){
			params = formForTable.serialize();//得到table的查询条件
		}
		var url = getTableCurPage(tableId).attr("ajax-url");
		pageTableLoad(url,params,"post","html");
	};
	var getTableCurPage = function(tableId){//得到当前页a标签节点
		return $("#"+tableId).parents("div.table-responsive").find(".pagination .active a");
	};
	var navlistHandle = function(){
		$(".nav-list a.ajaxDef").click(function(e){
			$(".nav-list .active").removeClass("active");
			$(this).parent("li").addClass('active');
			$(this).parents(".open").addClass('active');
		});
	};
	return {
		init : function() {
			//全局处理ajax事件
			$.ajaxSetup({
				timeout : 3000,
				success : function() {
					App.unblockUI(pageContentBody);
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader('Content-Type', 'application/xml;charset=utf-8');
					App.scrollTop();
					App.blockUI(pageContentBody, false);
				},
				error : function(xhr, status, e) {
					App.unblockUI(pageContentBody);
				}
			});
			//初始化需要ajax请求的a标签
			registAjaxDefClick();
			navlistHandle();
		},
		blockUI : function(el, centerY) {
			var el = jQuery(el);
			el.block({
				message : '<img src="assets/imgs/select2-spinner.gif" align="">',
				centerY : centerY != undefined ? centerY : true,
				css : {
					top : '10%',
					border : 'none',
					padding : '2px',
					backgroundColor : 'none'
				},
				overlayCSS : {
					backgroundColor : '#000',
					opacity : 0.05,
					cursor : 'wait'
				}
			});
		},

		unblockUI : function(el) {
			jQuery(el).unblock({
				onUnblock : function() {
					jQuery(el).removeAttr("style");
				}
			});
		},
		scrollTo : function(el, offeset) {
			pos = el ? el.offset().top : 0;
			jQuery('html,body').animate({
				scrollTop : pos + ( offeset ? offeset : 0)
			}, 'slow');
		},

		scrollTop : function() {
			App.scrollTo();
		},
	};
}();
