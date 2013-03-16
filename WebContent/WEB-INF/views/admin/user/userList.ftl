<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<link href="${rc.getContextPath()}/resources/styles/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script src="${rc.getContextPath()}/resources/scripts/jquery.js" type="text/javascript"></script>
		<script src="${rc.getContextPath()}/resources/scripts/ligerui.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		 function itemclick(item){
            alert(item.text);
        }
        $(function () {
            window['g'] = 
            $("#maingrid").ligerGrid({
                checkbox: true,
                columns: [
                	{ display: '用户名', name: 'USERNAME', minWidth: 60, width: '40%' },
                	{ display: '密码', name: 'PSWD', minWidth: 60,width: '40%',align:'left' }
                ],
                toolbar: { items: [
                		{ text: '增加', click: itemclick, img: '${rc.getContextPath()}/resources/styles/skins/icons/add.gif' },
                		{ line: true },
                		{ text: '修改', click: itemclick, img: '${rc.getContextPath()}/resources/styles/skins/icons/modify.gif' },
                		{ line: true },
                		{ text: '删除', click: itemclick, img: '${rc.getContextPath()}/resources/styles/skins/icons/delete.gif' }
                	]
                },
                dataAction:'server',
                pageSize:10,
                method:'GET',
                rownumbers:true,
                alternatingRow:true,
                url:'${rc.getContextPath()}/admin/user/getUserList.html',
                width: '100%',
                height: '100%', 
                isChecked: isChecked,
                onCheckRow: onCheckRow,
                onCheckAllRow: onCheckAllRow
            });
            $("#pageloading").hide();
        });
        function onCheckAllRow(checked){
            for (var rowid in this.records){
                if(checked){
                    addChecked(this.records[rowid]['KID']);
                }else{
                    removeChecked(this.records[rowid]['KID']);
                }
            }
        }
		
        /*
        该例子实现 表单分页多选
        即利用onCheckRow将选中的行记忆下来，并利用isChecked将记忆下来的行初始化选中
        */
        var checked = [];
        function findChecked(kid){
            for(var i =0;i<checked.length;i++){
                if(checked[i] == kid){
                	return i;
               	}
            }
            return -1;
        }
        function addChecked(kid){
            if(findChecked(kid) == -1){
                checked.push(kid);
            }
        }

        function removeChecked(kid){
            var i = findChecked(kid);
            if(i==-1) return;
            checked.splice(i,1);
        }

        function isChecked(rowdata){
            if (findChecked(rowdata.KID) == -1){
                return false;
            }
            return true;
        }

        function onCheckRow(checked, data){
            if (checked){
            	addChecked(data.KID);
            }else{
            	removeChecked(data.KID);
            }
        }
        function getChecked(){
            alert(checked.join(','));
        }
    </script>
	</head>
	<body style="overflow:hidden;">
		<div class="l-loading" style="display:block" id="pageloading"></div> 
  		<form>
    		<div id="maingrid" style="margin:0; padding:0;overflow-x:hidden"></div>
  		</form>
	</body>
</html>