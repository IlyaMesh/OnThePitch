package com.onthepitch.frontend.gwt.startpage.client.login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Binder;
import com.onthepitch.frontend.gwt.startpage.client.post.PostView;
import com.onthepitch.shared.model.LoginRequest;
import com.onthepitch.shared.model.LoginResult;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

public class LoginView extends Composite {
    @UiField
    TextBox login;
    @UiField
    PasswordTextBox password;
    private UserRest userRest;
    private LoginViewUiBinder loginViewUiBinder;

    @Inject
    @Autowired
    LoginView(LoginViewUiBinder loginViewUiBinder, UserRest userRest) {
        this.loginViewUiBinder = loginViewUiBinder;
        this.userRest = userRest;
    }
    public final LoginView init(){
        super.initWidget(this.loginViewUiBinder.createAndBindUi(this));
        return this;
    }
    @UiHandler("submit")
    void onSubmitClicked(ClickEvent event) {
        userRest.login(
                new LoginRequest(login.getText(), password.getText()),
                new MethodCallback<LoginResult>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Wrong login or password.");
                    }

                    @Override
                    public void onSuccess(Method method, LoginResult loginResult) {
                        Window.alert("You sucsessfully logined" + loginResult.getUsername());
                    }
                });
        //getUiHandlers().login(login.getText(), password.getText());
    }

    interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
    }
}
