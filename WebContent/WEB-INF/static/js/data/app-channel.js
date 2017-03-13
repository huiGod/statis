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
                columns: [ 'time:name' ,'name:name' ,'appchannel_active:name' ]
            }},
            {
            	"text":"刷新",
            	"className":"btn btn-sm btn-danger btn_left_padding clickFresh",
            }],
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
                    { "data":null ,"name":"index"},
                    { "data": "time","name":"time" },
                    { "data": "name","name":"name"},
                    { "data": "allclick","name":"appchannel_allclick" },
                    { "data": "click","name":"appchannel_click" },
                    { "data": "active","name":"appchannel_active"},
                    { "data": null, render: function ( data, type, row ) {
                        return ((data.active/data.click)*100).toFixed(2);
                    },"name":"appchannel_activecr"},
                    { "data": null, render: function ( data, type, row ) {
                        return ((data.active/data.allclick)*100).toFixed(2);
                    },"name":"appchannel_allactivecr"},
                    { "data": "level",render: function(data,type,row){
                    	return '<span style="margin-right:8%" onclick="des(this,'+row.id+')"><i class="icon-minus"></i></span><span>'+data+'</span><span style="margin-left:8%" onclick="asc(this,'+row.id+')"><i class="icon-plus"></i></span>'              		
                    },"name":"appchannel_level"}
                ],
		"drawCallback": function( settings ) {
			var table=this;
	        var api = this.api();
	        var length=Number(api.column(0).nodes().length);
			if(length>0){
		        var index=0,appchannel_allclick=0,appchannel_click=0,appchannel_active=0,appchannel_activecr=0,appchannel_allactivecr=0;
		        api.column("index:name").nodes().each(function(cell, i) {cell.innerHTML =  i + 1;});
		        api.column("appchannel_allclick:name").nodes().each(function(cell, i) {appchannel_allclick=Number(cell.innerHTML)+Number(appchannel_allclick)});
		        api.column("appchannel_click:name").nodes().each(function(cell, i) {appchannel_click=Number(cell.innerHTML)+Number(appchannel_click)});
		        api.column("appchannel_active:name").nodes().each(function(cell, i) {appchannel_active=Number(cell.innerHTML)+Number(appchannel_active)});
		        api.column("appchannel_activecr:name").nodes().each(function(cell, i) {appchannel_activecr=Number(cell.innerHTML)+Number(appchannel_activecr)});
		        api.column("appchannel_allactivecr:name").nodes().each(function(cell, i) {appchannel_allactivecr=Number(cell.innerHTML)+Number(appchannel_allactivecr)});
		        $("tbody").append('<tr role="row" class="odd"><td>'+(length+1)+'</td><td></td><td>总计:</td><td class="appchannel_allclick_td">'+appchannel_allclick+'</td><td>'+appchannel_click+'</td><td>'+appchannel_active+'</td><td>'+(appchannel_activecr/length).toFixed(2)+'</td><td class="appchannel_allactivecr_td">'+(appchannel_allactivecr/length).toFixed(2)+'</td><td class="appchannel_level_td"></td></tr>')
		        if(!$('#table_channel').DataTable().column("appchannel_level:name").visible()){
		        	$(".appchannel_level_td").remove();
		        }
		        if(!$('#table_channel').DataTable().column("appchannel_allclick:name").visible()){
		        	$(".appchannel_allclick_td").remove();
		        }
		        if(!$('#table_channel').DataTable().column("appchannel_allactivecr:name").visible()){
		        	$(".appchannel_allactivecr_td").remove();
		        }
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
    	visiableCol();
    });
    visiableCol();
    //刷新当前表格数据
    $(".clickFresh").click(function(){
    	table.ajax.reload();
    });
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
