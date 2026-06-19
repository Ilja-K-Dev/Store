<#import "template.ftl" as layout>

<@layout.registrationLayout displayInfo=false displayMessage=true; section>

<#if section = "form">

<link rel="stylesheet" href="${url.resourcesPath}/css/style.css">

<div class="kc-wrapper">

    <div class="kc-card">

        <div class="kc-header">
            <h1>Welcome back</h1>
            <p>Sign in to continue</p>
        </div>

        <form id="kc-form-login"
              action="${url.loginAction}"
              method="post">

            <div class="kc-field">
                <label>Username</label>
                <input class="kc-input"
                       name="username"
                       type="text"
                       value="${(login.username!'')}"
                       autofocus />
            </div>

            <div class="kc-field">
                <label>Password</label>
                <input class="kc-input"
                       name="password"
                       type="password"/>
            </div>

            <button class="kc-button" type="submit">
                Sign in
            </button>

        </form>

    </div>

</div>

</#if>

</@layout.registrationLayout>