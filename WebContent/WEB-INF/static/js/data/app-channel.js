//时间控件
$('#reservation').daterangepicker({
}, function(start, end, label) {
  $("#startTime").val(start.format('YYYY-MM-DD'));
  $("#endTime").val(end.format('YYYY-MM-DD'));
  $('#table_channel').DataTable().draw();
});
//渠道下拉数据填充
$.ajax({
	url:"active/channel",
	type:"GET",
	dataType:"json",
	contentType:"application/json;charset=utf-8", 
	success:function(data){
		for(var i=0;i<data.length;i++){
			$("#channel").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
		}
		appIdSelect(data[0].id);
		$("#channel").find("option").eq(0).attr("selected",true);
		baseGrid();
	}
});
//渠道变化  联动应用
$("#channel").change(function(){
	//刷新应用列表
	var channelId=$(this).val();
	appIdSelect(channelId);
	$('#table_channel').DataTable().draw();
});
//应用变化 刷新表格
$("#appId").change(function(){
	$('#table_channel').DataTable().draw();
});
//通过渠道号刷新应用列表
function appIdSelect(channelId){
	$("#channel").children().each(function(index,element){
		if($(this).val()==channelId){
			$(this).attr("selected",true);
			$(this).siblings().removeAttr("selected");
		}
	});
	$("#appId").html('');
	//应用下拉
	$.ajax({
		url:"active/app",
		data:{"channelId":channelId},
		type:"GET",
		dataType:"json",
		success:function(data){
			$("#appId").append('<option value="">请选择应用</option>');
			for(var i=0;i<data.length;i++){
				$("#appId").append('<option value="'+data[i].id+'">'+data[i].name+'</option>');
			}
		}
	});
}

//表格初始化
function baseGrid(){
	var channel=$("#channel").val();
	var table=$('#table_channel').DataTable({
		"dom": 'frBtlip',
		"buttons": [ {
			"text":"导出Excel",
            "extend": "excel",
            "title":"统计数据",
            "className":"btn btn-sm btn-success",
            "exportOptions": {
                columns: [ 1,2,3,4,5 ]
            }
            /*customize: function( xlsx ) {
                var sheet = xlsx.xl.worksheets['sheet1.xml'];
                $('row c[r^="C"]', sheet).attr( 's', '2' );
            }*/
        } ],
		"processing": true,
        "serverSide": true,
        "ajax": {
        			"url":"active/channelData",
        			"data":function(d){
        				d.channel=$("#channel").val();
        				d.startTime=$('#startTime').val();
        				d.endTime=$("#endTime").val();
        				d.appId=$("#appId").val();
        			}
        		},
        "columns": [
                    { "data":null },
                    { "data": "time" },
                    { "data": "name" },
                    { "data": "click" },
                    { "data": "active"},
                    { "data": null, render: function ( data, type, row ) {
                        return ((data.active/data.click)*100).toFixed(2);
                    }},
                    { "data": "level",render: function(data,type,row){
                    	return '<span style="margin-right:8%" onclick="des(this,'+row.id+')"><i class="icon-minus"></i></span><span>'+data+'</span><span style="margin-left:8%" onclick="asc(this,'+row.id+')"><i class="icon-plus"></i></span>'              		
                    }}
                ],
		"drawCallback": function( settings ) {
			var table=this;
	        var api = this.api();
	        var length=Number(api.column(0).nodes().length);
			if(length>0){
		        var col3=0;var col0=0;var col4=0;var col5=0;
		        api.column(0).nodes().each(function(cell, i) {cell.innerHTML =  i + 1;});
		        api.column(3).nodes().each(function(cell, i) {col3=Number(cell.innerHTML)+Number(col3)});
		        api.column(4).nodes().each(function(cell, i) {col4=Number(cell.innerHTML)+Number(col4)});
		        api.column(5).nodes().each(function(cell, i) {col5=Number(cell.innerHTML)+Number(col5)});
		        $("tbody").append('<tr role="row" class="odd"><td>'+(length+1)+'</td><td></td><td>总计</td><td>'+col3+'</td><td>'+col4+'</td><td>'+(col5/length).toFixed(2)+'</td></tr>')
	        }
	    },
			"lengthMenu": [ 50, 100, 200 ],
			"responsive":true,
			"searching":false,
			"scrollCollapse":false,
			"ordering":false,
			"language": {
			"processing": "处理中...",
		    "emptyTable": "表中数据为空",
		    "info":"当前页:_PAGE_,共_PAGES_页",
		    "loadingRecords": "载入中...",
		    "pagingType": "full_numbers",
		    "paginate": {
		        "first": "首页",
		        "previous": "上页",
		        "next": "下页",
		        "last": "末页"
		    }
		}
	});
	//添加table的样式
	$('#table_channel').addClass('table table-striped table-bordered table-hover center');
    $("#channel").change(function(){
    	visiableCol6();
    });
    visiableCol6();
}

function asc(i,id){
	var value=$(i).prev().text();
	if(value<10){
		$(i).prev().text(parseInt(value, 10) + 1);
		edit(id,$(i).prev().text());
	}
}
function des(i,id){
	var value=$(i).next().text();
	if(value>0){
		$(i).next().text(parseInt(value, 10) - 1);
		edit(id,$(i).next().text());
	}
}

function edit(id,num){
	$.ajax({
		url:"active/editLevel",
		data:{"id":id,"num":num},
		type:"GET",
		dataType:"json",
		success:function(data){
		},
		error:function(){
			bootbox.alert("修改失败,请刷新重新尝试!");
		}
	});
}
