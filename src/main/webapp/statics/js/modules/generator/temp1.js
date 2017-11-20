$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'temp1/list',
        datatype: "json",
        colModel: [			
			{ label: '编码', name: 'code', index: 'code', width: 50, key: true },
			{ label: '名称', name: 'name', index: 'name', width: 80 }, 			
			{ label: '大对象', name: 'jobData', index: 'job_data', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		temp1: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.temp1 = {};
		},
		update: function (event) {
			var code = getSelectedRow();
			if(code == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(code)
		},
		saveOrUpdate: function (event) {
			if(!$("#saveOrUpdateForm").valid()){
        		parent.layer.msg('红色框为必填内容');
	        	return false;
			}
			var url = vm.temp1.code == null ? "temp1/save" : "temp1/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.temp1),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var codes = getSelectedRows();
			if(codes == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "temp1/delete",
				    contentType: "application/json",
				    data: JSON.stringify(codes),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(code){
			$.get(baseURL + "temp1/info/"+code, function(r){
                vm.temp1 = r.temp1;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});