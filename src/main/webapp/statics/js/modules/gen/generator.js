var lastrow, lastcell; 
$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/generator/list',
        datatype: "json",
        colModel: [			
			{ label: '表名', name: 'tableName', width: 100, key: true },
			{ label: 'PackageName', name: 'packageName', width: 120, editable:true,edittype:"text"},
			{ label: 'Engine', name: 'engine', width: 50,},
			{ label: '表备注', name: 'tableComment', width: 100 },
			{ label: '创建时间', name: 'createTime', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50,100,200],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        cellEdit:true,
        cellsubmit:"clientArray",
        beforeEditCell:function(rowid,cellname,v,iRow,iCol){
        	lastrow = iRow; 
        	lastcell = iCol; 
        },    
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
		q:{
			tableName: null
		}
	},
	methods: {
		query: function () {
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'tableName': vm.q.tableName},
                page:1 
            }).trigger("reloadGrid");
		},
		generator: function() {
			var tableNames = getSelectedRows();
			if(tableNames == null){
				return ;
			}
			$("#jqGrid").jqGrid("saveCell",lastrow,lastcell);
			$("#jqGrid").jqGrid("restoreCell",lastrow,lastcell);
			
			var data = [];
			var ids=$('#jqGrid').jqGrid('getGridParam','selarrrow');
			for(var i=0;i<ids.length;i++){
				var rowData = $('#jqGrid').jqGrid('getRowData',ids[i]);
				data.push(rowData);
				console.log(data);
			}
			location.href = baseURL + "sys/generator/code?tables=" + JSON.stringify(data);
		}
	}
});

