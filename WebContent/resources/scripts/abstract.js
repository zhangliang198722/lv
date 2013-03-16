// 表单
function getDetail(params){
	var parentElement = Ext.getCmp("smart.ui.Window");
	return new Ext.FormPanel({
        frame:true,
        id:"form_"+params.formName+"_edit_form",
        autoWidth:true,
        border:false,
        height:parentElement.getInnerHeight(),
        defaultType: 'textfield',
        autoScroll:true,
        items: [{
            xtype:'fieldset',
            autoHeight:true,
            title:"必填信息",
            defaultType: 'textfield',
            collapsible: true,
            items:params.notNull
            },{
            xtype:'fieldset',
            title: '选填信息',
            collapsible: true,
            collapsed: false,
            autoHeight:true,
            defaults: {width: 210},
            defaultType: 'textfield',
            items :params.optional
          }]
    });
}

// 窗口
function operator(parent,params){
	params = params || {title:'增加',iconCls:'add'};
	var hiddenButton = (params.operator_action=="detail")?true:false;
	var xwindow = parent.xwindow({
		title:(params.title),
		iconCls : (params.iconCls),
		width:700,
		height:500,
		id:'smart:dongrun:'+params.id+':add:window',
		hiddenButton:hiddenButton,
		autoLoad:{url:loadUrl,params:params,scripts:true},
		submit:function(){
			var top = Ext.getCmp("form_"+formName+"_edit_form");
        	if(top.form.isValid()){
        		top.getForm().submit({
           			url:parmas.submitUrl,
           			params:params,
           			success:params.success,
           			waitMsg:'正在保存......',
           			failure: function(form_instance_create,action){
           				 json_response = action.result;
           				 Smart.app.showError(json_response.error);
           			}
           		});
        	}
		}
	});
};

