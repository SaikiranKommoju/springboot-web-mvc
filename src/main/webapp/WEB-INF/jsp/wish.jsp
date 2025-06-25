<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <script>
        function message() {
            document.getElementById("message").innerHTML = "<h1 style='text-align:center;color:green'> Hi </h1>";
        }

        function messageBye() {
            document.getElementById("message").innerHTML = "<h1 style='text-align:center;color:green'> Bye </h1>";
        }

        function message2(rollNo) {
            document.getElementById("message").innerHTML = "<h1 style='text-align:center;color:green'> Hi " + rollNo + "</h1>";
        }

        function message3(name) {
            document.getElementById("message").innerHTML = "<h1 style='text-align:center;color:green'> Hi " + name + "</h1>";
        }


        function message4(rollNo, name) {
            document.getElementById("message").innerHTML = "<h1 style='text-align:center;color:green'> Hi " + rollNo + " :: " + name + "</h1>";
        }

        /*var idleTime = 0;

        // Setup timerIncrement function to be called every minute
        setInterval(timerIncrement, 1000);  // 1 sec

        // Attach event listeners to user actions
        window.addEventListener('mousemove', resetIdleTime, false);
        window.addEventListener('keypress', resetIdleTime, false);

        // Function to reset idleTime to 0
        function resetIdleTime() {
            idleTime = 0;
        }

        // Function to increment idleTime and check if it is greater than 19
        function timerIncrement() {
            console.log("timer incremented");
            idleTime++;
            if(idleTime > 9) {  // 10 sec
                console.log("idle time: " + idleTime);
                window.location.reload();
                // or
                // window.location.href = '/logout';
            }
        }*/
    </script>

    <body>

            <!--<div>
                <c:set var="function" value=""/>
                <c:choose>
                    <c:when test="${region eq 'X'}">
                        <c:set var="function" value="message3('${name}')"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="function" value="message4(${rollNo}, '${name}')"/>
                    </c:otherwise>
                </c:choose>
                <input type="button" onclick="${function}" value="Say Hi"/>
            </div>-->

        <div id="message"></div>
        <!--<div>
            <input type="button" onclick="${region eq 'X' ? 'message2(rollNo)' : ''}" value="Say Hi"/>
        </div>-->
        <div>
            <!--<a href="${loginUrl}">Login</a>-->
            <a href="#" onclick="window.open('${loginUrl}')">Login</a>
            <input type="button" onclick="${sayHi ? 'message()' : 'messageBye()'}" value="Wish"/>
        </div>
    </body>
</html>
