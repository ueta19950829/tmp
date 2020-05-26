<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show All Songs</title>
</head>
<body>

	<table style='border-spacing:0;border-collapse:collapse;' border='1'>
	<tr><td>NO</td><td>タイトル</td><td>発売日</td></tr>

	<c:forEach items ="${songs}" var="song">
		<tr><td>${song.code}</td><td>${song.name}</td><td>${song.releasedate}</td></tr>
	</c:forEach>

	</table>

</body>
</html>