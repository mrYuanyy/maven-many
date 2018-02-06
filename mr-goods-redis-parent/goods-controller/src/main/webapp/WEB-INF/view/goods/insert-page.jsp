<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"  src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath() %>/js/jquery/jquery-1.11.3.min.js"></script>
</head>
<body>
	<form id="goods_insert_form">
		<table border="50"  align="center">
			<tr>
				<td>商品名称:</td>
				<td><input type="text"  name="goodsName"/></td>
			</tr>
			<tr>
				<td>商品价格:</td>
				<td><input type="text"  name="goodsPrice"/></td>
			</tr>	
			<tr>
				<td>商品库存:</td>
				<td><input type="text"  name="goodsCount"/></td>
			</tr>		
			<tr>
				<td>出厂时间:</td>
				<td><input  class="Wdate"  name="goodsDate"   type="text"  onClick="WdatePicker()"></td>
			</tr>		
			<tr>
				<td>商品类型:</td>
				<td>
					<select  name="goodsTypeId">
						<option  value="-1">--请选择--</option>
						<c:forEach  items="${typeList }"   var="goodsType">
							<option  value="${goodsType.goodsTypeId }">${goodsType.goodsTypeName }</option>
						</c:forEach>
					</select>
				</td>
			</tr>	
			<tr align="center">
				<td colspan="2">
					<input type="button"   value="新增"   onclick="insertGoods()"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	function insertGoods(){
		  $.ajax({
	              url: "<%=request.getContextPath()%>/goods/insertGoods.do",
	              type:"post",
	              data:$("#goods_insert_form").serialize(),//表单序列化提交
	              dataType:"text",//规定返回值格式
	            async:false,//同步上传
	              success:function (data){//成功回调函数
	                          alert(data);
	              },
	              error :function(){//错误回调函数
	                  alert("系统错误,请联系管理员")
	              }
 	     });
	}
</script>
</html>