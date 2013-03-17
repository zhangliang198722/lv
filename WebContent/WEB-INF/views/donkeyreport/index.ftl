<SCRIPT LANGUAGE="JavaScript">
Ext.onReady(function(){
	var p = mainPanel; //Ext.getCmp("vega:dsm:query:dsm_task-panel");
	function panelCallBack(){
//     		var pageCount =document.getElementById("t_pageCount").value;
//        	var thisPage = document.getElementById("t_pageNum").value;
//        	Ext.getCmp("t_count").setValue(pageCount);
//        	Ext.getCmp("t_thisPage").setValue(thisPage);
//        	document.getElementById("t_first").disabled=false;
//    		document.getElementById("t_left").disabled=false;
//    		document.getElementById("t_last").disabled=false;
//    		document.getElementById("t_right").disabled=false;
//        	if(thisPage=="1"){
//        		document.getElementById("t_first").disabled=true;
//        		document.getElementById("t_left").disabled=true;
//        	}else if(pageCount == thisPage){
//        		document.getElementById("t_last").disabled=true;
//        		document.getElementById("t_right").disabled=true;
//        	}
     }
	var taskPanel = new Ext.Panel({
		title:'项目任务查询',
		id:'task_search_panel',
        border:false,
        scope: this,
        autoScroll:true,
        autoWidht:true,
        height:p.getInnerHeight(),
        tbar:new Ext.Toolbar({items: [
				'自定义检索','-','项目名称：',{id:'proName',xtype:'textfield',width:100,name: 'proName'},
		 		'-','任务名称：',{id:'taskName',xtype:'textfield',width:100,name: 'taskName'},
				'-','负责人：',{id:'userName',xtype:'textfield',width:100,name: 'userName'},
				'-','实际结束时间从：',{id:'bdate',xtype:'datefield',width:100,format:'Y-m-d',name: 'bdate'},
				'至：',{id:'edate',xtype:'datefield',width:100,format:'Y-m-d',name: 'edate'},
				'-',
				new Ext.Button({
					text:'查询',
					pressed:false,
					handler:function(){
						try{
//							var taskName = Ext.getCmp("taskName").getValue();
//							var proName = Ext.getCmp("proName").getValue();
//							var userName = Ext.getCmp("userName").getValue();
//							var bdate = Ext.getCmp("bdate").getValue();
//							var edate = Ext.getCmp("edate").getValue();
//							if(bdate!=''||edate!=''){
//								if(bdate==''||edate==''){
//									Ext.Msg.alert("错误提示","查询时间不能为空");
//								}
//							}
							taskPanel.load({url:'donkeyreport/result',scripts:true,params:{},callback:panelCallBack,scope:this});
						}catch(e){
						}
					}
				}) ,
				'->','-',
        		{iconCls:"vega_pwms_ui_export_excel",text:"导出Excel",handler:function(){
        			var str = taskPanel.body.dom.innerHTML;
        			if(str.indexOf("<form") == -1){
    					doExportExcel("项目任务.xls", str);
        			}
        		}},'-'
				]}),
				bbar:[
        	new Ext.Button({
        		text:'首页',
        		id:'t_first',
        		iconCls:'vega_dsm_ui_first',
        		handler:function(){
//        			var taskName = Ext.getCmp("taskName").getValue();
//					var proName = Ext.getCmp("proName").getValue();
//					var userName = Ext.getCmp("userName").getValue();
//					var bdate = Ext.getCmp("bdate").getValue();
//					var edate = Ext.getCmp("edate").getValue();
//					taskPanel.load({url:'$vlink.setRelative("/dms/query/task.vega").setFunction("result")',scripts:true,params:{taskName:taskName,proName:proName,userName:userName,edate:edate,bdate:bdate,absolutePage:1},callback:panelCallBack,scope:this});
        		}
        	}),'-',
        	new Ext.Button({
        		text:'上页',
        		id:'t_left',
        		iconCls:'vega_dsm_ui_left',
        		handler:function(){
//        			var absolutePage = document.getElementById("t_pageNum").value;
//        			var taskName = Ext.getCmp("taskName").getValue();
//					var proName = Ext.getCmp("proName").getValue();
//					var userName = Ext.getCmp("userName").getValue();
//					var bdate = Ext.getCmp("bdate").getValue();
//					var edate = Ext.getCmp("edate").getValue();
//					absolutePage = parseInt(absolutePage)-1;
//		        	taskPanel.load({url:'$vlink.setRelative("/dms/query/task.vega").setFunction("result")',scripts:true,params:{taskName:taskName,proName:proName,userName:userName,edate:edate,bdate:bdate,absolutePage:absolutePage},callback:panelCallBack,scope:this});
        		}
        	}),'-',
        	new Ext.Button({
        		text:'下页',
        		id:'t_right',
        		iconCls:'vega_dsm_ui_right',
        		handler:function(){
//        			var absolutePage = document.getElementById("t_pageNum").value;
//        			var taskName = Ext.getCmp("taskName").getValue();
//					var proName = Ext.getCmp("proName").getValue();
//					var userName = Ext.getCmp("userName").getValue();
//					var bdate = Ext.getCmp("bdate").getValue();
//					var edate = Ext.getCmp("edate").getValue();
//					absolutePage = parseInt(absolutePage)+1;
//		        	taskPanel.load({url:'$vlink.setRelative("/dms/query/task.vega").setFunction("result")',scripts:true,params:{taskName:taskName,proName:proName,userName:userName,edate:edate,bdate:bdate,absolutePage:absolutePage},callback:panelCallBack,scope:this});
        		}
        	}),'-',
        	new Ext.Button({
        		text:'末页',
        		id:'t_last',
        		iconCls:'vega_dsm_ui_last',
        		handler:function(){
//        			var absolutePage = document.getElementById("t_pageCount").value;
//        			var taskName = Ext.getCmp("taskName").getValue();
//					var proName = Ext.getCmp("proName").getValue();
//					var userName = Ext.getCmp("userName").getValue();
//					var bdate = Ext.getCmp("bdate").getValue();
//					var edate = Ext.getCmp("edate").getValue();
//		        	taskPanel.load({
//						url:'$vlink.setRelative("/dms/query/task.vega").setFunction("result")',
//						scripts:true,
//						params:{taskName:taskName,proName:proName,userName:userName,edate:edate,bdate:bdate,absolutePage:absolutePage},
//						callback:panelCallBack,scope:this
//					});
        		}
        	}),'-','共',{id:'t_count',width:'50px',xtype:'numberfield',value:''},'页','-','当前页', {id:'t_thisPage',width:'50px',xtype:'numberfield',value:''},
        	new Ext.Button({
        		text:'翻页',
        		iconCls:'vega_dsm_ui_forward',
        		handler:function(){
//        			var pageCount =document.getElementById("t_pageCount").value;
//        			var absolutePage = Ext.getCmp("t_thisPage").getValue();
//        			if(parseInt(absolutePage)>parseInt(pageCount)||parseInt(absolutePage)<1){
//        				Ext.Msg.alert("系统提示","请输入正确的页数！！");
//        				return;
//        			}
//        			var taskName = Ext.getCmp("taskName").getValue();
//					var proName = Ext.getCmp("proName").getValue();
//					var userName = Ext.getCmp("userName").getValue();
//					var bdate = Ext.getCmp("bdate").getValue();
//					var edate = Ext.getCmp("edate").getValue();
//		        	taskPanel.load({url:'$vlink.setRelative("/dms/query/task.vega").setFunction("result")',scripts:true,params:{taskName:taskName,proName:proName,userName:userName,edate:edate,bdate:bdate,absolutePage:absolutePage},callback:panelCallBack,scope:this});
        		}
        	})  
        ],
        renderTo: p.body,
        autoLoad:{url:'donkeyreport/toList',scripts:true,callback:panelCallBack,scope:this}
     });
	 /*var bbar2 = new Ext.Toolbar({  
            renderTo:taskPanel.tbar,
			items:[
				'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
				'-','计划开始时间：',{id:'bdate',xtype:'datefield',width:100,format:'Y-m-d',name: 'bdate'},
				'-','计划结束时间：',{id:'edate',xtype:'datefield',width:100,format:'Y-m-d',name: 'edate'},
				'-',
				new Ext.Button({
					text:'查询',
					pressed:false,
					handler:function(){
						try{
							var taskName = Ext.getCmp("taskName").getValue();
							var proName = Ext.getCmp("proName").getValue();
							var userName = Ext.getCmp("userName").getValue();
							var bdate = Ext.getCmp("bdate").getValue();
							var edate = Ext.getCmp("edate").getValue();
							documentPanel.load({url:'$vlink.setRelative("/dms/query/task.vega").setFunction("result")',scripts:true,params:{taskName:taskName,proName:proName,userName:userName,edate:edate,bdate:bdate}});
						}catch(e){
						}
					}
				}) 
            ]  
        });  */
});
</SCRIPT>
