<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<c:set var="title" value="Error Page" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
<div class="page-content container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-wrapper">
                <div class="box">
                    <div class="content-wrap">
                        <h6>Sign Up</h6>
                        <form method="post" action="controller?action=registration">
                        <input class="form-control" type="text" placeholder="E-mail address">
                        <input class="form-control" type="password" placeholder="Password">
                        <input class="form-control" type="password" placeholder="Confirm Password">
                        <div class="action">
                               <button class="btn btn-primary" type="submit">Sign up</button>
                        </div>
                        </form>
                    </div>
                </div>
                <div class="already">
                    <p>Have an account already?</p>
                    <a href="login.html">Login</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>