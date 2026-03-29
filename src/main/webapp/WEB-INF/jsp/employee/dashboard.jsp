<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employees</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            color: #1f2937;
        }
        .topbar {
            background: #111827;
            color: #ffffff;
            padding: 12px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
        }
        .topbar-title {
            font-size: 18px;
            font-weight: 600;
        }
        .topbar-right {
            display: flex;
            align-items: center;
            gap: 12px;
        }
        .username-pill {
            background: #1f2937;
            border: 1px solid #374151;
            border-radius: 999px;
            padding: 6px 12px;
            font-size: 14px;
        }
        .logout-form {
            margin: 0;
        }
        .logout-btn {
            border: 1px solid #ef4444;
            background: #ef4444;
            color: #ffffff;
            border-radius: 6px;
            padding: 6px 12px;
            cursor: pointer;
            font-size: 14px;
        }
        .logout-btn:hover {
            background: #dc2626;
            border-color: #dc2626;
        }
        .container {
            max-width: 1100px;
            margin: 24px auto 32px;
            padding: 0 16px;
        }
        h2 {
            margin: 0 0 16px;
        }
        .meta {
            margin-bottom: 16px;
            color: #4b5563;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
        }
        thead {
            background: #2563eb;
            color: #ffffff;
        }
        th, td {
            padding: 12px 14px;
            text-align: left;
            border-bottom: 1px solid #e5e7eb;
            vertical-align: top;
        }
        tbody tr:hover {
            background: #f9fafb;
        }
        .empty {
            background: #ffffff;
            border: 1px solid #e5e7eb;
            border-radius: 8px;
            padding: 20px;
            color: #4b5563;
        }
    </style>
</head>
<body>
<div class="topbar">
    <div class="topbar-title">Employee Dashboard</div>
    <div class="topbar-right">
        <div class="username-pill">
            Username: <strong><c:out value="${empty pageContext.request.userPrincipal ? 'User' : pageContext.request.userPrincipal.name}"/></strong>
        </div>
        <form class="logout-form" action="<c:url value='/logout'/>" method="post">
            <button class="logout-btn" type="submit">Logout</button>
        </form>
    </div>
</div>

<div class="container">
    <h2>Employee List</h2>
    <div class="meta">Total Employees: <strong><c:out value="${employeeCount}"/></strong></div>

    <c:choose>
        <c:when test="${not empty employees}">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Email</th>
                    <th>Department</th>
                    <th>Role</th>
                    <th>Location</th>
                    <th>Joining Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td><c:out value="${employee.id}"/></td>
                        <td><c:out value="${employee.name}"/></td>
                        <td><c:out value="${employee.gender.value}"/></td>
                        <td><c:out value="${employee.emailId}"/></td>
                        <td><c:out value="${employee.department}"/></td>
                        <td><c:out value="${employee.role}"/></td>
                        <td><c:out value="${employee.location}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty employee.joiningDate}">
                                    <fmt:parseDate value="${employee.joiningDate}" pattern="yyyy-MM-dd" var="parsedDate"/>
                                    <fmt:formatDate value="${parsedDate}" pattern="dd-MMM-yyyy"/>
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="empty">No employees found.</div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

