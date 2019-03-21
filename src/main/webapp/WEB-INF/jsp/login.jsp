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
                        <h6>Sign In man</h6>
                        <form method="post" action="controller?action=login">
                            <input class="form-control" type="text" name="email" placeholder="E-mail address">
                            <input class="form-control" type="password" name="password" placeholder="Password">
                            <div class="action">
                                <button class="btn btn-primary" type="submit">Login</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="already">
                    <p>Don't have an account yet?</p>
                    <a href="signup.html">Sign Up</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>