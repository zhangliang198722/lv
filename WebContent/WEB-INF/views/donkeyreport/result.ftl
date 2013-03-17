
<br />
<center><font size="5">驴驹查询</font></center>
<br />
<table border="1" width="120%" bordercolor="#000000" align="center" style="background-color:#FFFFFF;border-collapse:collapse;">
<tr height="40" align="center" bgcolor="#4977B4" style="color:white;">
	<td width="7%">驴号</td>
	<td width="7%">性别</td>
	<td width="7%">出生日期</td>
	<td width="7%">驴圈</td>
	<td width="7%">父亲驴</td>
	<td width="7%">母亲驴</td>
	<td width="7%">毛色</td>
	<td width="7%">接产人</td>
	<td width="7%">死亡日期</td>
	<td width="17%">死亡原因</td>
	<td width="20">备注</td>
</tr>
#foreach($donkey in $list)
	<tr align="center" height="25">
		<td  width="7%">$!donkey.DONKEYNUM</td>
		<td width="7%">$!donkey.sex</td>
		<td width="7%">$!donkey.birth</td>
		<td width="7%">$!donkey.house</td>
		<td width="7%">$!donkey.monthid</td>
		<td width="7%">$!donkey.fatherid</td>
		<td width="7%">$!donkey.coat</td>
		<td width="7%">$!donkey.deliverid</td>
		<td width="7%">$!donkey.death</td>
		<td width="17%">$!donkey.deathcause</td>
		<td width="20">$!donkey.remark</td>
	</tr>
#end
</table>

	<input type="hidden" id="t_pageNum" name="pageNum" value="$form.pageNum" />
	<input type="hidden" id="t_pageCount" name="pageCount" value="$form.pageCount" />
	<input type="hidden" id="t_startRow" name="startRow" value="$form.startRow" />
	<input type="hidden" id="t_endRow" name="endRow" value="$form.endRow" />
	<!-- <input type="text" id="t_count" name="count" value="$form.count" /> -->
		<br />
		<br />